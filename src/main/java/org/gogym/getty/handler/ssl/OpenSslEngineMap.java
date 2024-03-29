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
package org.gogym.getty.handler.ssl;

interface OpenSslEngineMap {

    /**
     * Remove the {@link OpenSslEngine} with the given {@code ssl} address and
     * return it.
     */
    ReferenceCountedOpenSslEngine remove(long ssl);

    /**
     * Add a {@link OpenSslEngine} to this {@link OpenSslEngineMap}.
     */
    void add(ReferenceCountedOpenSslEngine engine);

    /**
     * Get the {@link OpenSslEngine} for the given {@code ssl} address.
     */
    ReferenceCountedOpenSslEngine get(long ssl);
}
