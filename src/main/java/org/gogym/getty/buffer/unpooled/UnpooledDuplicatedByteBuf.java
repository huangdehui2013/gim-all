/*
 * Copyright 2015 The Netty Project
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
package org.gogym.getty.buffer.unpooled;

import org.gogym.getty.buffer.DuplicatedByteBuf;
import org.gogym.getty.buffer.abs.AbstractByteBuf;

/**
 * {@link DuplicatedByteBuf} implementation that can do optimizations because it
 * knows the duplicated buffer is of type {@link AbstractByteBuf}.
 */
public class UnpooledDuplicatedByteBuf extends DuplicatedByteBuf {
	public UnpooledDuplicatedByteBuf(AbstractByteBuf buffer) {
		super(buffer);
	}

	@Override
	public AbstractByteBuf unwrap() {
		return (AbstractByteBuf) super.unwrap();
	}

	@Override
	public byte _getByte(int index) {
		return unwrap()._getByte(index);
	}

	@Override
	public short _getShort(int index) {
		return unwrap()._getShort(index);
	}

	@Override
	public short _getShortLE(int index) {
		return unwrap()._getShortLE(index);
	}

	@Override
	public int _getUnsignedMedium(int index) {
		return unwrap()._getUnsignedMedium(index);
	}

	@Override
	public int _getUnsignedMediumLE(int index) {
		return unwrap()._getUnsignedMediumLE(index);
	}

	@Override
	public int _getInt(int index) {
		return unwrap()._getInt(index);
	}

	@Override
	public int _getIntLE(int index) {
		return unwrap()._getIntLE(index);
	}

	@Override
	public long _getLong(int index) {
		return unwrap()._getLong(index);
	}

	@Override
	public long _getLongLE(int index) {
		return unwrap()._getLongLE(index);
	}

	@Override
	public void _setByte(int index, int value) {
		unwrap()._setByte(index, value);
	}

	@Override
	public void _setShort(int index, int value) {
		unwrap()._setShort(index, value);
	}

	@Override
	public void _setShortLE(int index, int value) {
		unwrap()._setShortLE(index, value);
	}

	@Override
	public void _setMedium(int index, int value) {
		unwrap()._setMedium(index, value);
	}

	@Override
	public void _setMediumLE(int index, int value) {
		unwrap()._setMediumLE(index, value);
	}

	@Override
	public void _setInt(int index, int value) {
		unwrap()._setInt(index, value);
	}

	@Override
	public void _setIntLE(int index, int value) {
		unwrap()._setIntLE(index, value);
	}

	@Override
	public void _setLong(int index, long value) {
		unwrap()._setLong(index, value);
	}

	@Override
	public void _setLongLE(int index, long value) {
		unwrap()._setLongLE(index, value);
	}
}
