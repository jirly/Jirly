package com.xuanwu.cmp.domain.entity;

import java.util.Arrays;
import java.util.Date;

import com.xuanwu.cmp.domain.AbstractEntity;

/**
 * @Description 应用
 * @author <a href="mailto:jiji@javawind.com">XueFang.Xu</a>
 * @date 2016-08-10
 * @version 1.0.0
 */
public class App extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	private Integer id;// id主键
	private String name;// 应用名称
	private String identify;// 应用标识
	private int enterpriseId;// 企业ID
	private String callbackUrl;// 回调地址
	private boolean enabledTrustIp;// 启用应用服务器信用IP
	private AppType type;// 类型: 0-默认应用,1-客户应用
	private AppState state;// 应用状态,0-未上线,1-运营中,2-暂停
	private Date createTime;// 创建时间
	private Date updateTime;// 最后更新时间
	private boolean test;// 是否为测试应用

	private Integer[] enablers;
	private String[] trustIps;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdentify() {
		return identify;
	}

	public void setIdentify(String identify) {
		this.identify = identify;
	}

	public int getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(int enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public String getCallbackUrl() {
		return callbackUrl;
	}

	public void setCallbackUrl(String callbackUrl) {
		this.callbackUrl = callbackUrl;
	}

	public boolean isEnabledTrustIp() {
		return enabledTrustIp;
	}

	public void setEnabledTrustIp(boolean enabledTrustIp) {
		this.enabledTrustIp = enabledTrustIp;
	}

	public AppType getType() {
		return type;
	}

	public void setType(AppType type) {
		this.type = type;
	}

	public void setTypeIdx(int idx) {// int to enum
		this.type = AppType.getType(idx);
	}

	public AppState getState() {
		return state;
	}

	public void setState(AppState state) {
		this.state = state;
	}

	public void setStateIdx(int idx) {// int to enum
		this.state = AppState.getState(idx);
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

	public boolean isTest() {
		return test;
	}

	public void setTest(boolean test) {
		this.test = test;
	}

	public Integer[] getEnablers() {
		return enablers;
	}

	public void setEnablers(Integer[] enablers) {
		this.enablers = enablers;
	}

	public String[] getTrustIps() {
		return trustIps;
	}

	public void setTrustIps(String[] trustIps) {
		this.trustIps = trustIps;
	}

	@Override
	public String toString() {
		return "App [id=" + id + ", name=" + name + ", identify=" + identify + ", enterpriseId=" + enterpriseId
				+ ", callbackUrl=" + callbackUrl + ", enabledTrustIp=" + enabledTrustIp + ", type=" + type + ", state="
				+ state + ", createTime=" + createTime + ", updateTime=" + updateTime + ", test=" + test + ", enablers="
				+ Arrays.toString(enablers) + ", trustIps=" + Arrays.toString(trustIps) + "]";
	}

	public static enum AppType {// 应用类型
		DEFAULT(0), CLIENT(1);

		private int index;

		private AppType(int index) {
			this.index = index;
		}

		public static AppType getType(int index) {
			for (AppType type : AppType.values()) {
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

	public static enum AppState {// 应用状态
		OFFLINE(0), ONLINE(1), PAUSED(2);

		private int index;

		private AppState(int index) {
			this.index = index;
		}

		public static AppState getState(int index) {
			for (AppState state : AppState.values()) {
				if (state.getIndex() == index) {
					return state;
				}
			}
			return null;
		}

		public int getIndex() {
			return index;
		}
	}

}
