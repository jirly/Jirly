package com.xuanwu.cmp.domain.entity;

import java.util.Date;

import com.xuanwu.cmp.domain.AbstractEntity;

/**
 * @Description UserTestNum.java
 * @author <a href="mailto:miaojiepu@wxchina.com">Jiepu.Miao</a>
 * @date 2016年8月16日
 * @version 1.0.0
 */
public class UserTestNum extends AbstractEntity {

	private static final long serialVersionUID = 1L;
	private Integer id;// 主键
	private String number;// 测试号码
	private Date createTime;// 创建时间
	private int enterpriseId;// 企业id
	private String code;// 验证码

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public int getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(int enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
