package com.xuanwu.cmp.domain.entity;

public enum Platform {// 平台类型

	BACKEND(0), FRONTKIT(2);

	private int index;

	private Platform(int index) {
		this.index = index;
	}

	public static Platform getType(int index) {
		for (Platform type : Platform.values()) {
			if (type.getIndex() == index) {
				return type;
			}
		}
		return null;
	}

	public int getIndex() {
		return index;
	}
}