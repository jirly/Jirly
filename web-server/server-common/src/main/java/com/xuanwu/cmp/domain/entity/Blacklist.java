/*   
 * Copyright (c) 2012 by XUANWU INFORMATION TECHNOLOGY CO. 
 *             All rights reserved                         
 */
package com.xuanwu.cmp.domain.entity;

import com.xuanwu.cmp.domain.AbstractEntity;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;

/**
 * @Description 区域号段
 * 林泽强
 */
public class Blacklist extends AbstractEntity {

	/** primary id */
	private Integer id;

	/** 缓存phone primary id */
	private Long desID;

	/** 企业ID */
	private Integer enterpriseID;

	/** 企业名称 */
	private String enterpriseName;

	/** 企业帐号 */
	private String domain;

	/** 手机号码 */
	private String phone;

	/** 创建时间 */
	private Date createTime;

	/** 所属用户 */
	private String user;

	/** 所属用户名字 */
	private String userName;

	/** 操作平台 */
	private Integer handleFrom;

	/** 归属目标 */
	private Integer target;

	/** 目标名称 */
	private String targetName;

	/** 通道名称 */
	private String channelName;

	/** 通道ID */
	private Integer channelId;

	/** 黑名单类型 */
	private Integer type = -1;

	private BlacklistType blacklistType;

	private Boolean isRemove;
	/** 备注 */
	private String remark;

	private Date handleTime;

	/** 保存压缩后的数据 */
	private Long zipmes;

	private String sourcePhone;

	private Integer sourceType;

	private Integer sourceTarget;

	private Integer removeId;

	private String tmpTypeName;
	// add by shen 20170910 查询条件 开始时间、结束时间
	private String beginTime;
	private String endTime;
	private Integer dataFrom;// 数据来源查询条件

	private String addOrDelName;

	private Boolean isDisplay;


	public enum BlacklistType {
		/* 0--用户黑名单,1--白名单,2--企业黑名单,3--全局黑名单,4--通道黑名单,5--业务类型黑名单,6--企业后台黑名单 */
		Illegal(-1), User(0), Enterprise(2), Global(3), Channel(4), BizType(5), BackendEnterprise(
				6);

		private Integer index;

		private BlacklistType(int index) {
			this.index = index;
		}

		public Integer getIndex() {
			return index;
		}

		public static BlacklistType getType(Integer index) {
			switch (index) {
			case 0:
				return User;
			case 2:
				return Enterprise;
			case 3:
				return Global;
			case 4:
				return Channel;
			case 5:
				return BizType;
			case 6:
				return BackendEnterprise;
			default:
				return Illegal;
			}
		}

		public static BlacklistType getType(String typeName) {

			if (StringUtils.isEmpty(typeName)) {
				return Illegal;
			}

			String tempTypeName = typeName.trim();
			if ("用户".equals(tempTypeName)) {
				return User;
			} else if ("企业".equals(tempTypeName)) {
				return Enterprise;
			} else if ("全局".equals(tempTypeName)) {
				return Global;
			} else if ("通道".equals(tempTypeName)) {
				return Channel;
			} else if ("业务类型".equals(tempTypeName)) {
				return BizType;
			} else {
				return Illegal;
			}
		}

		public static String getTypeName(int type) {
			switch (type) {
			case 0:
				return "用户";
			case 2:
				return "企业";
			case 3:
				return "全局";
			case 4:
				return "通道";
			case 5:
				return "业务类型";
			default:
				return "企业";
			}
		}
	}

	public String getTargetName() {
		return targetName;
	}

	public void setTargetName(String targetName) {
		this.targetName = targetName;
	}

	@Override
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Long getDesID() {
		return desID;
	}

	public void setDesID(Long desID) {
		this.desID = desID;
	}

	public Integer getEnterpriseID() {
		return enterpriseID;
	}

	public void setEnterpriseID(Integer enterpriseID) {
		this.enterpriseID = enterpriseID;
	}

	public String getEnterpriseName() {
		return enterpriseName;
	}

	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getHandleFrom() {
		return handleFrom;
	}

	public void setHandleFrom(Integer handleFrom) {
		this.handleFrom = handleFrom;
	}

	public Integer getTarget() {
		return target;
	}

	public void setTarget(Integer target) {
		this.target = target;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public Integer getChannelId() {
		return channelId;
	}

	public void setChannelId(Integer channelId) {
		this.channelId = channelId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public BlacklistType getBlacklistType() {
		return blacklistType;
	}

	public void setBlacklistType(BlacklistType blacklistType) {
		this.blacklistType = blacklistType;
		this.type = blacklistType.getIndex();
	}

	public Boolean getRemove() {
		return isRemove;
	}

	public void setRemove(Boolean remove) {
		isRemove = remove;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getHandleTime() {
		return handleTime;
	}

	public void setHandleTime(Date handleTime) {
		this.handleTime = handleTime;
	}

	public Long getZipmes() {
		return zipmes;
	}

	public void setZipmes(Long zipmes) {
		this.zipmes = zipmes;
	}

	public String getSourcePhone() {
		return sourcePhone;
	}

	public void setSourcePhone(String sourcePhone) {
		this.sourcePhone = sourcePhone;
	}

	public Integer getSourceType() {
		return sourceType;
	}

	public void setSourceType(Integer sourceType) {
		this.sourceType = sourceType;
	}

	public Integer getSourceTarget() {
		return sourceTarget;
	}

	public void setSourceTarget(Integer sourceTarget) {
		this.sourceTarget = sourceTarget;
	}

	public Integer getRemoveId() {
		return removeId;
	}

	public void setRemoveId(Integer removeId) {
		this.removeId = removeId;
	}

	public String getTmpTypeName() {
		return tmpTypeName;
	}

	public void setTmpTypeName(String tmpTypeName) {
		this.tmpTypeName = tmpTypeName;
	}

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public Integer getDataFrom() {
		return dataFrom;
	}

	public void setDataFrom(Integer dataFrom) {
		this.dataFrom = dataFrom;
	}

	public String getAddOrDelName() {
		return addOrDelName;
	}

	public void setAddOrDelName(String addOrDelName) {
		this.addOrDelName = addOrDelName;
	}

	public Boolean getDisplay() {
		return isDisplay;
	}

	public void setDisplay(Boolean display) {
		isDisplay = display;
	}
}
