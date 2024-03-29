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
package org.gogym.getty.channel.exception;

import org.gogym.getty.util.internal.PlatformDependent;
import org.gogym.getty.util.internal.SuppressJava6Requirement;
import org.gogym.getty.util.internal.UnstableApi;

/**
 * A {@link RuntimeException} which is thrown when an I/O operation fails.
 */
public class ChannelException extends RuntimeException {

    private static final long serialVersionUID = 2908618315971075004L;

    /**
     * Creates a new exception.
     */
    public ChannelException() {
    }

    /**
     * Creates a new exception.
     */
    public ChannelException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Creates a new exception.
     */
    public ChannelException(String message) {
        super(message);
    }

    /**
     * Creates a new exception.
     */
    public ChannelException(Throwable cause) {
        super(cause);
    }

    @UnstableApi
    @SuppressJava6Requirement(reason = "uses Java 7+ RuntimeException.<init>(String, Throwable, boolean, boolean)" +
            " but is guarded by version checks")
    protected ChannelException(String message, Throwable cause, boolean shared) {
        super(message, cause, false, true);
        assert shared;
    }

    static ChannelException newStatic(String message, Throwable cause) {
        if (PlatformDependent.javaVersion() >= 7) {
            return new ChannelException(message, cause, true);
        }
        return new ChannelException(message, cause);
    }
}
