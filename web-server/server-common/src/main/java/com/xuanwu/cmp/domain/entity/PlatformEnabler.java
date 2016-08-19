package com.xuanwu.cmp.domain.entity;

import com.xuanwu.cmp.domain.AbstractEntity;

/**
 * @Description 平台能力
 * @author <a href="mailto:jiji@javawind.com">XueFang.Xu</a>
 * @date 2016-08-11
 * @version 1.0.0
 */
public class PlatformEnabler extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	private Integer id;// id主键
	private int typeIdx;// 能力类型
	private EnablerType type;// 能力类型
	private String remark;// 能力标识

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getTypeIdx() {
		this.typeIdx = type.index;
		return typeIdx;
	}

	public void setTypeIdx(int idx) {
		this.typeIdx = idx;
		this.type = EnablerType.getType(idx);
	}

	public EnablerType getType() {
		return type;
	}

	public void setType(EnablerType type) {
		this.type = type;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public static enum EnablerType {// 能力类型
		// 1:短信通知,2:短信验证码,3:营销短信,11:语音通知,12:语音验证码
		SMS_NOTIFICATION(1), SMS_CODE(2), SMS_MARKETING(3), VOICE_NOTIFICATION(11), VOICE_CODE(12);

		private int index;

		private EnablerType(int index) {
			this.index = index;
		}

		public static EnablerType getType(int index) {
			for (EnablerType type : EnablerType.values()) {
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

}
