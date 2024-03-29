/*
 * Copyright 2017 The Netty Project
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
package org.gogym.getty.bootstrap;

import java.net.SocketAddress;

import org.gogym.getty.channel.AbstractChannel;
import org.gogym.getty.channel.ChannelConfig;
import org.gogym.getty.channel.ChannelMetadata;
import org.gogym.getty.channel.ChannelOutboundBuffer;
import org.gogym.getty.channel.ChannelPromise;
import org.gogym.getty.channel.EventLoop;
import org.gogym.getty.channel.defaults.DefaultChannelConfig;

final class FailedChannel extends AbstractChannel {
    private static final ChannelMetadata METADATA = new ChannelMetadata(false);
    private final ChannelConfig config = new DefaultChannelConfig(this);

    FailedChannel() {
        super(null);
    }

    @Override
    protected AbstractUnsafe newUnsafe() {
        return new FailedChannelUnsafe();
    }

    @Override
    protected boolean isCompatible(EventLoop loop) {
        return false;
    }

    @Override
    protected SocketAddress localAddress0() {
        return null;
    }

    @Override
    protected SocketAddress remoteAddress0() {
        return null;
    }

    @Override
    protected void doBind(SocketAddress localAddress) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected void doDisconnect() {
        throw new UnsupportedOperationException();
    }

    @Override
    protected void doClose() {
        throw new UnsupportedOperationException();
    }

    @Override
    protected void doBeginRead() {
        throw new UnsupportedOperationException();
    }

    @Override
    protected void doWrite(ChannelOutboundBuffer in) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ChannelConfig config() {
        return config;
    }

    @Override
    public boolean isOpen() {
        return false;
    }

    @Override
    public boolean isActive() {
        return false;
    }

    @Override
    public ChannelMetadata metadata() {
        return METADATA;
    }

    private final class FailedChannelUnsafe extends AbstractUnsafe {
        @Override
        public void connect(SocketAddress remoteAddress, SocketAddress localAddress, ChannelPromise promise) {
            promise.setFailure(new UnsupportedOperationException());
        }
    }
}
