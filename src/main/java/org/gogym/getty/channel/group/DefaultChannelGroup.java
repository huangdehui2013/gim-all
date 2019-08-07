/*
 * Copyright 2013 The Netty Project
 *
 * The Netty Project licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package org.gogym.getty.channel.group;

import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

import org.gogym.getty.channel.Channel;
import org.gogym.getty.channel.ChannelFuture;
import org.gogym.getty.channel.ChannelFutureListener;
import org.gogym.getty.channel.ChannelId;
import org.gogym.getty.channel.ServerChannel;
import org.gogym.getty.util.concurrent.EventExecutor;
import org.gogym.getty.util.internal.PlatformDependent;
import org.gogym.getty.util.internal.StringUtil;

/**
 * The default {@link ChannelGroup} implementation.
 */
public class DefaultChannelGroup extends AbstractSet<Channel> implements
		ChannelGroup {

	private static final AtomicInteger nextId = new AtomicInteger();
	private final String name;
	private final ConcurrentMap<ChannelId, Channel> serverChannels = PlatformDependent
			.newConcurrentHashMap();
	private final ConcurrentMap<ChannelId, Channel> nonServerChannels = PlatformDependent
			.newConcurrentHashMap();
	private final ChannelFutureListener remover = new ChannelFutureListener() {
		@Override
		public void operationComplete(ChannelFuture future) throws Exception {
			remove(future.channel());
		}
	};
	private final boolean stayClosed;
	private volatile boolean closed;

	/**
	 * Creates a new group with a generated name and the provided
	 * {@link EventExecutor} to notify the {@link ChannelGroupFuture}s.
	 */
	public DefaultChannelGroup(EventExecutor executor) {
		this(executor, false);
	}

	/**
	 * Creates a new group with the specified {@code name} and
	 * {@link EventExecutor} to notify the {@link ChannelGroupFuture}s. Please
	 * note that different groups can have the same name, which means no
	 * duplicate check is done against group names.
	 */
	public DefaultChannelGroup(String name, EventExecutor executor) {
		this(name, executor, false);
	}

	/**
	 * Creates a new group with a generated name and the provided
	 * {@link EventExecutor} to notify the {@link ChannelGroupFuture}s.
	 * {@code stayClosed} defines whether or not, this group can be closed more
	 * than once. Adding channels to a closed group will immediately close them,
	 * too. This makes it easy, to shutdown server and child channels at once.
	 */
	public DefaultChannelGroup(EventExecutor executor, boolean stayClosed) {
		this("group-0x" + Integer.toHexString(nextId.incrementAndGet()),
				executor, stayClosed);
	}

	/**
	 * Creates a new group with the specified {@code name} and
	 * {@link EventExecutor} to notify the {@link ChannelGroupFuture}s.
	 * {@code stayClosed} defines whether or not, this group can be closed more
	 * than once. Adding channels to a closed group will immediately close them,
	 * too. This makes it easy, to shutdown server and child channels at once.
	 * Please note that different groups can have the same name, which means no
	 * duplicate check is done against group names.
	 */
	public DefaultChannelGroup(String name, EventExecutor executor,
			boolean stayClosed) {
		if (name == null) {
			throw new NullPointerException("name");
		}
		this.name = name;
		this.stayClosed = stayClosed;
	}

	@Override
	public String name() {
		return name;
	}

	@Override
	public Channel find(ChannelId id) {
		Channel c = nonServerChannels.get(id);
		if (c != null) {
			return c;
		} else {
			return serverChannels.get(id);
		}
	}

	@Override
	public boolean isEmpty() {
		return nonServerChannels.isEmpty() && serverChannels.isEmpty();
	}

	@Override
	public int size() {
		return nonServerChannels.size() + serverChannels.size();
	}

	@Override
	public boolean contains(Object o) {
		if (o instanceof ServerChannel) {
			return serverChannels.containsValue(o);
		} else if (o instanceof Channel) {
			return nonServerChannels.containsValue(o);
		}
		return false;
	}

	@Override
	public boolean add(Channel channel) {
		ConcurrentMap<ChannelId, Channel> map = channel instanceof ServerChannel ? serverChannels
				: nonServerChannels;

		boolean added = map.putIfAbsent(channel.id(), channel) == null;
		if (added) {
			channel.closeFuture().addListener(remover);
		}

		if (stayClosed && closed) {

			// First add channel, than check if closed.
			// Seems inefficient at first, but this way a volatile
			// gives us enough synchronization to be thread-safe.
			//
			// If true: Close right away.
			// (Might be closed a second time by ChannelGroup.close(), but this
			// is ok)
			//
			// If false: Channel will definitely be closed by the ChannelGroup.
			// (Because closed=true always happens-before ChannelGroup.close())
			//
			// See https://github.com/netty/netty/issues/4020
			channel.close();
		}

		return added;
	}

	@Override
	public boolean remove(Object o) {
		Channel c = null;
		if (o instanceof ChannelId) {
			c = nonServerChannels.remove(o);
			if (c == null) {
				c = serverChannels.remove(o);
			}
		} else if (o instanceof Channel) {
			c = (Channel) o;
			if (c instanceof ServerChannel) {
				c = serverChannels.remove(c.id());
			} else {
				c = nonServerChannels.remove(c.id());
			}
		}

		if (c == null) {
			return false;
		}

		c.closeFuture().removeListener(remover);
		return true;
	}

	@Override
	public void clear() {
		nonServerChannels.clear();
		serverChannels.clear();
	}

	@Override
	public Object[] toArray() {
		Collection<Channel> channels = new ArrayList<Channel>(size());
		channels.addAll(serverChannels.values());
		channels.addAll(nonServerChannels.values());
		return channels.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		Collection<Channel> channels = new ArrayList<Channel>(size());
		channels.addAll(serverChannels.values());
		channels.addAll(nonServerChannels.values());
		return channels.toArray(a);
	}

	@Override
	public int hashCode() {
		return System.identityHashCode(this);
	}

	@Override
	public boolean equals(Object o) {
		return this == o;
	}

	@Override
	public int compareTo(ChannelGroup o) {
		int v = name().compareTo(o.name());
		if (v != 0) {
			return v;
		}

		return System.identityHashCode(this) - System.identityHashCode(o);
	}

	@Override
	public String toString() {
		return StringUtil.simpleClassName(this) + "(name: " + name()
				+ ", size: " + size() + ')';
	}

	@Override
	public Iterator<Channel> iterator() {

		if (nonServerChannels != null) {
			return nonServerChannels.values().iterator();
		} else {
			return serverChannels.values().iterator();
		}
	}
}
