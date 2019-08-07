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

import java.util.Set;

import org.gogym.getty.channel.Channel;
import org.gogym.getty.channel.ChannelId;

public interface ChannelGroup extends Set<Channel>, Comparable<ChannelGroup> {

	/**
	 * Returns the name of this group. A group name is purely for helping you to
	 * distinguish one group from others.
	 */
	String name();

	/**
	 * Returns the {@link Channel} which has the specified {@link ChannelId}.
	 *
	 * @return the matching {@link Channel} if found. {@code null} otherwise.
	 */
	Channel find(ChannelId id);

}
