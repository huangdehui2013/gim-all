/*
 * Copyright 2012 The Netty Project
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

import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;

import org.gogym.getty.util.NetUtil;

/**
 * Internet Protocol (IP) families used byte the {@link DatagramChannel}
 */
public enum InternetProtocolFamily {
    IPv4(Inet4Address.class, 1, NetUtil.LOCALHOST4),
    IPv6(Inet6Address.class, 2, NetUtil.LOCALHOST6);

    private final Class<? extends InetAddress> addressType;
    private final int addressNumber;
    private final InetAddress localHost;

    InternetProtocolFamily(Class<? extends InetAddress> addressType, int addressNumber, InetAddress localHost) {
        this.addressType = addressType;
        this.addressNumber = addressNumber;
        this.localHost = localHost;
    }

    /**
     * Returns the address type of this protocol family.
     */
    public Class<? extends InetAddress> addressType() {
        return addressType;
    }

    /**
     * Returns the
     * <a href="http://www.iana.org/assignments/address-family-numbers/address-family-numbers.xhtml">address number</a>
     * of the family.
     */
    public int addressNumber() {
        return addressNumber;
    }

    /**
     * Returns the {@link InetAddress} that represent the {@code LOCALHOST} for the family.
     */
    public InetAddress localhost() {
        return localHost;
    }

    /**
     * Returns the {@link InternetProtocolFamily} for the given {@link InetAddress}.
     */
    public static InternetProtocolFamily of(InetAddress address) {
        if (address instanceof Inet4Address) {
            return IPv4;
        }
        if (address instanceof Inet6Address) {
            return IPv6;
        }
        throw new IllegalArgumentException("address " + address + " not supported");
    }
}
