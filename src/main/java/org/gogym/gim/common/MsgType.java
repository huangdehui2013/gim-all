package org.gogym.gim.common;

import org.gogym.gim.utils.DynamicEnumUtil;

public enum MsgType {
	/**
	 * 文本
	 *
	 */
	MSG_TYPE_TEXT(0),
	/**
	 * 图片
	 *
	 */
	MSG_TYPE_IMG(1),
	/**
	 * 语音
	 *
	 */
	MSG_TYPE_VOICE(2),
	/**
	 * 视频
	 *
	 */
	MSG_TYPE_VIDEO(3),
	/**
	 * 音乐
	 *
	 */
	MSG_TYPE_MUSIC(4),
	/**
	 * 图文
	 *
	 */
	MSG_TYPE_NEWS(5), ;

	public final int getNumber() {
		return value;
	}

	public static MsgType valueOf(int value) {
		return forNumber(value);
	}

	public static MsgType forNumber(int value) {
		switch (value) {
		case 0:
			return MSG_TYPE_TEXT;
		case 1:
			return MSG_TYPE_IMG;
		case 2:
			return MSG_TYPE_VOICE;
		case 3:
			return MSG_TYPE_VIDEO;
		case 4:
			return MSG_TYPE_MUSIC;
		case 5:
			return MSG_TYPE_NEWS;
		default:
			return null;
		}
	}

	private final int value;

	private MsgType(int value) {
		this.value = value;
	}

	/**
	 * 
	 * Description: 动态添加枚举
	 * 
	 * @param enumName
	 * @param value
	 * @see
	 */
	public static void addEnum(String enumName, int value) {
		DynamicEnumUtil.addEnum(MsgType.class, enumName,
				new Class<?>[] { int.class }, new Object[] { value });
	}

}
