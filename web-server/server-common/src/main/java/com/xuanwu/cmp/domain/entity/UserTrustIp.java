package com.xuanwu.cmp.domain.entity;

import java.util.Date;

import com.xuanwu.cmp.domain.AbstractEntity;

/**
 * @Description 信任IP
 * @author <a href="mailto:jiji@javawind.com">XueFang.Xu</a>
 * @date 2016-08-16
 * @version 1.0.0
 */
public class UserTrustIp extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	private Integer id;// id主键
	private int enterpriseId;// 企业id
	private int appId;// 应用id
	private String trustIp;// 信任ip
	private Platform platform;
	private Integer platformId;// FrontKit:2,Backend:0
	private Date createTime;// 创建时间
	private Date updateTime;// 修改时间

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(int enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public int getAppId() {
		return appId;
	}

	public void setAppId(int appId) {
		this.appId = appId;
	}

	public String getTrustIp() {
		return trustIp;
	}

	public void setTrustIp(String trustIp) {
		this.trustIp = trustIp;
	}

	public Platform getPlatform() {
		return platform;
	}

	public void setPlatform(Platform platform) {
		this.platform = platform;
		this.platformId = platform.getIndex();
	}

	public Integer getPlatformId() {
		return platformId;
	}

	public void setPlatformId(Integer platformId) {
		this.platform = Platform.getType(platformId);
		this.platformId = platformId;
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

}
