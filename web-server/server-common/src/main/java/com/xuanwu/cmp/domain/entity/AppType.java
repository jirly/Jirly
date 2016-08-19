package com.xuanwu.cmp.domain.entity;


/**
 * 模板-应用类型:1-已上线网页/APP,2-未上线网页/APP
 * @author <a href="mailto:jiangpeng@wxchina.com">Peng.Jiang</a>
 * @date 2016-07-07
 * @version 1.0.0
 */
public enum AppType {

		ONLINE(1, "online"),
		OFFLINE(2, "offline");

		private final int value;
		private final String type;

		private AppType(int value, String type) {
			this.value = value;
			this.type = type;
		}

		public static AppType getType(int value) {
			switch (value) {
				case 1:
					return ONLINE;
				case 2:
					return OFFLINE;
				default:
					throw new RuntimeException("Unsupport App(Phrase) Type: " + value);
			}
		}

		public int getValue() {
			return value;
		}
	}