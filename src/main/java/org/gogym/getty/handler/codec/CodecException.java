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
package org.gogym.getty.handler.codec;

/**
 * An {@link Exception} which is thrown by a codec.
 */
public class CodecException extends RuntimeException {

    private static final long serialVersionUID = -1464830400709348473L;

    /**
     * Creates a new instance.
     */
    public CodecException() {
    }

    /**
     * Creates a new instance.
     */
    public CodecException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Creates a new instance.
     */
    public CodecException(String message) {
        super(message);
    }

    /**
     * Creates a new instance.
     */
    public CodecException(Throwable cause) {
        super(cause);
    }
}
