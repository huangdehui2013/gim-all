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
package org.gogym.getty.channel.socket;

import org.gogym.getty.channel.ChannelHandlerContext;
import org.gogym.getty.channel.ChannelInboundHandler;
import org.gogym.getty.util.internal.UnstableApi;

/**
 * Special event which will be fired and passed to the
 * {@link ChannelInboundHandler#userEventTriggered(ChannelHandlerContext, Object)} methods once the output of
 * a {@link SocketChannel} was shutdown.
 */
@UnstableApi
public final class ChannelOutputShutdownEvent {
    public static final ChannelOutputShutdownEvent INSTANCE = new ChannelOutputShutdownEvent();

    private ChannelOutputShutdownEvent() {
    }
}
