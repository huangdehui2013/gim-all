/*
 * Copyright 2016 The Netty Project
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
package org.gogym.getty.buffer;

import java.nio.ByteBuffer;

import org.gogym.getty.buffer.unpooled.UnpooledUnsafeDirectByteBuf;
import org.gogym.getty.util.internal.PlatformDependent;

public final class WrappedUnpooledUnsafeDirectByteBuf extends UnpooledUnsafeDirectByteBuf {

	public WrappedUnpooledUnsafeDirectByteBuf(ByteBufAllocator alloc, long memoryAddress, int size, boolean doFree) {
        super(alloc, PlatformDependent.directBuffer(memoryAddress, size), size, doFree);
    }

    @Override
    protected void freeDirect(ByteBuffer buffer) {
        PlatformDependent.freeMemory(memoryAddress);
    }
}
