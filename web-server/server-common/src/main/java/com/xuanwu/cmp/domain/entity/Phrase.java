package com.xuanwu.cmp.domain.entity;

import com.xuanwu.cmp.domain.AbstractEntity;

import java.util.Date;

/**
 * 模板实体
 * @author <a href="mailto:jiangpeng@wxchina.com">Peng.Jiang</a>
 * @date 2016-07-07
 * @version 1.0.0
 */
public class Phrase extends AbstractEntity {

	private static final long serialVersionUID = 2101348062056461908L;
	private Integer id;// id主键
	private String content;// 模版内容
	private String title;// 模板标题
	private PhraseMsgType msgType;// 信息类型：1，短信；2，彩信；3，WAP_PUSH, 4: 流量, 5:话费, 8:语音通知,9:语音验证码
	private String identify;// 模板编号(UUID)
	private PhraseType type; // 模板类型:1-短信通知，2-短信验证码,3-营销短信,11-语音通知,12-语音验证码
	private PhraseState state; // 模板状态：0:待审核，1：通过审核，2：未通过审核
	private Integer signId; // 签名ID
	private Integer appId; // 归属应用ID,is_test 为 true 时该字段值为: 0
	private Integer enterpriseId; // 归属企业ID,is_test 为 true 时该字段值为: 0
	private Date createTime; // 添加时间
    private Date updateTime; // 更新时间
	private Boolean isTest; // 是否为测试模板:0-否, 1-是
	private Boolean isRemove; // 是否已删除: 0-否, 1-是

	private String appName; // 归属应用名称
	private PhraseAuditMaterial.AppType appType; // 应用类型:1-已上线网页/APP,2-未上线网页/APP
	private String appUrl; // 应用链接
	private String appLogo; // 应用log截图链接
	private String appVerifyPage; // 应用验证截图链接
	private String auditComment; // 模板审核意见
	private String signContent; // 签名内容
	private String certifyFile; // 证明函
	private UserSign.SignType signType; // 签名类型

	private Boolean isSMS; //是否为短信模板

	public enum PhraseType {

		SMS_NOTIFICATION(1, "smsnotification"), // 短信通知
		SMS_VERIFICATION_CODE(2, "smsverificationcode"), // 短信验证码
		SMS_SALE(3, "smssale"), // 营销短信
		VOICE_NOTIFICATION(11, "voicenotification"), // 语音通知
		VOICE_VERIFICATION_CODE(12, "voiceverificationcode"); // 语音验证码

		private final int value;
        private final String type;

		private PhraseType(int value, String type) {
			this.value = value;
			this.type = type;
		}

		public static PhraseType getType(int value) {
			switch (value) {
				case 1:
					return SMS_NOTIFICATION;
				case 2:
					return SMS_VERIFICATION_CODE;
				case 3:
					return SMS_SALE;
				case 11:
					return VOICE_NOTIFICATION;
				case 12:
					return VOICE_VERIFICATION_CODE;
				default:
					throw new RuntimeException("Unsupport Phrase type: " + value);
			}
		}

		public int getValue() {
			return value;
		}
	}


	public enum PhraseState {

		CHECKING(0, "checking"), // 待审核
		IS_CHECKED(1, "ischecked"), // 通过审核
		UNCHECK(2, "uncheck"); // 未通过审核

		private final int value;
		private final String state;

		private PhraseState(int value, String state) {
			this.value = value;
			this.state = state;
		}

		public static PhraseState getState(int value) {
			switch (value) {
				case 0:
					return CHECKING;
				case 1:
					return IS_CHECKED;
				case 2:
					return UNCHECK;
				default:
					throw new RuntimeException("Unsupport Phrase state: " + value);
			}
		}

		public int getValue() {
			return value;
		}
	}

	//信息类型：1，短信；2，彩信；3，WAP_PUSH, 4: 流量, 5:话费, 8:语音通知,9:语音验证码
	public enum PhraseMsgType {

		SMS(1),
		MMS(2),
		WAP_PUSH(3),
		FLOW(4),
		TARIFFE(5),
		VOICE_NOTIFICATION(8),
		VOICE_VERIFICATION_CODE(9);

		private final int value;

		private PhraseMsgType(int value) {
			this.value = value;
		}

		public static PhraseMsgType getType(int value) {
			switch (value) {
				case 1:
					return SMS;
				case 2:
					return MMS;
				case 3:
					return WAP_PUSH;
				case 4:
					return FLOW;
				case 5:
					return TARIFFE;
				case 8:
					return VOICE_NOTIFICATION;
				case 9:
					return VOICE_VERIFICATION_CODE;
				default:
					throw new RuntimeException("Unsupport Phrase Msg Type: " + value);
			}
		}

		public int getValue() {
			return value;
		}
	}



	@Override
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public PhraseMsgType getMsgType() {
		return msgType;
	}

	public void setMsgType(PhraseMsgType msgType) {
		this.msgType = msgType;
	}

	public void setMsgTypeIndex(Integer index) {
		this.msgType = PhraseMsgType.getType(index);
	}

	public String getIdentify() {
		return identify;
	}

	public void setIdentify(String identify) {
		this.identify = identify;
	}

	public PhraseType getType() {
		return type;
	}

	public void setType(PhraseType type) {
		this.type = type;
	}

	public void setTypeIndex(int index) {
		this.type = PhraseType.getType(index);
	}

	public PhraseState getState() {
		return state;
	}

	public void setState(PhraseState state) {
		this.state = state;
	}

	public void setStateIndex(int index) {
		this.state = PhraseState.getState(index);
	}

	public Integer getSignId() {
		return signId;
	}

	public void setSignId(Integer signId) {
		this.signId = signId;
	}

	public Integer getAppId() {
		return appId;
	}

	public void setAppId(Integer appId) {
		this.appId = appId;
	}

	public Integer getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(Integer enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Boolean getIsTest() {
		return isTest;
	}

	public void setIsTest(Boolean isTest) {
		this.isTest = isTest;
	}

	public Boolean getIsRemove() {
		return isRemove;
	}

	public void setIsRemove(Boolean isRemove) {
		this.isRemove = isRemove;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public PhraseAuditMaterial.AppType getAppType() {
		return appType;
	}

	public void setAppType(PhraseAuditMaterial.AppType appType) {
		this.appType = appType;
	}

	public void setAppTypeIndex(int index) {
		this.appType = PhraseAuditMaterial.AppType.getType(index);
	}

	public String getAppUrl() {
		return appUrl;
	}

	public void setAppUrl(String appUrl) {
		this.appUrl = appUrl;
	}

	public String getAppLogo() {
		return appLogo;
	}

	public void setAppLogo(String appLogo) {
		this.appLogo = appLogo;
	}

	public String getAppVerifyPage() {
		return appVerifyPage;
	}

	public void setAppVerifyPage(String appVerifyPage) {
		this.appVerifyPage = appVerifyPage;
	}

	public String getAuditComment() {
		return auditComment;
	}

	public void setAuditComment(String auditComment) {
		this.auditComment = auditComment;
	}

	public String getSignContent() {
		return signContent;
	}

	public void setSignContent(String signContent) {
		this.signContent = signContent;
	}

	public String getCertifyFile() {
		return certifyFile;
	}

	public void setCertifyFile(String certifyFile) {
		this.certifyFile = certifyFile;
	}

	public UserSign.SignType getSignType() {
		return signType;
	}

	public void setSignType(UserSign.SignType signType) {
		this.signType = signType;
	}

	public void setSignTypeIndex(Integer signType) {
		this.signType = UserSign.SignType.getType(signType);
	}

	public Boolean getIsSMS() {
		return this.isSMS;
	}

	public void setIsSMS(Boolean isSMS) {
		this.isSMS = isSMS;
	}

	@Override
	public String toString() {
		return "Phrase{" +
				"id=" + id +
				", content='" + content + '\'' +
				", title='" + title + '\'' +
				", msgType=" + msgType +
				", identify='" + identify + '\'' +
				", type=" + type +
				", state=" + state +
				", signId=" + signId +
				", appId=" + appId +
				", enterpriseId=" + enterpriseId +
				", createTime=" + createTime +
				", updateTime=" + updateTime +
				", isTest=" + isTest +
				", isRemove=" + isRemove +
				'}';
	}

}
