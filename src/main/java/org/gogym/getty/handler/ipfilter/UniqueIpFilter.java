/*
 * Copyright 2014 The Netty Project
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
package org.gogym.getty.handler.ipfilter;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.Set;

import org.gogym.getty.channel.Channel;
import org.gogym.getty.channel.ChannelFuture;
import org.gogym.getty.channel.ChannelFutureListener;
import org.gogym.getty.channel.ChannelHandler;
import org.gogym.getty.channel.ChannelHandlerContext;
import org.gogym.getty.util.internal.ConcurrentSet;

/**
 * This class allows one to ensure that at all times for every IP address there is at most one
 * {@link Channel} connected to the server.
 */
@ChannelHandler.Sharable
public class UniqueIpFilter extends AbstractRemoteAddressFilter<InetSocketAddress> {

    private final Set<InetAddress> connected = new ConcurrentSet<InetAddress>();

    @Override
    protected boolean accept(ChannelHandlerContext ctx, InetSocketAddress remoteAddress) throws Exception {
        final InetAddress remoteIp = remoteAddress.getAddress();
        if (!connected.add(remoteIp)) {
            return false;
        } else {
            ctx.channel().closeFuture().addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture future) throws Exception {
                    connected.remove(remoteIp);
                }
            });
            return true;
        }
    }
}
