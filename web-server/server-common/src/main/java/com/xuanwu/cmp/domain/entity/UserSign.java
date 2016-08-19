/*   
* Copyright (c) 2016/8/12 by XuanWu Wireless Technology Co., Ltd 
*             All rights reserved  
*/
package com.xuanwu.cmp.domain.entity;

import com.xuanwu.cmp.domain.AbstractEntity;

import java.util.Date;

/**
 * 企业签名
 * @author <a href="mailto:jiangpeng@wxchina.com">Peng.Jiang</a>
 * @version 1.0.0
 * @date 2016/8/12
 */
public class UserSign extends AbstractEntity {

    private static final long serialVersionUID = 8032888028246235469L;

    private Integer id; // ID
    private Integer enterpriseId; //企业ID
    private String sign; //签名
    private SignType type; //签名类型
    private SignState state; //签名状态:1：通过审核，2：未通过审核
    private String CertifyFile; //证明函文件路径
    private Date createTime; //创建日期
    private Boolean isRemove; //是否已删除


    public enum SignState {

        CHECKING(0, "checking"), // 待审核
        IS_CHECKED(1, "ischecked"), // 通过审核
        UNCHECK(2, "uncheck"); // 未通过审核

        private final int value;
        private final String state;

        private SignState(int value, String state) {
            this.value = value;
            this.state = state;
        }

        public static UserSign.SignState getState(int value) {
            switch (value) {
                case 0:
                    return CHECKING;
                case 1:
                    return IS_CHECKED;
                case 2:
                    return UNCHECK;
                default:
                    throw new RuntimeException("Unsupport UserSign state: " + value);
            }
        }

        public int getValue() {
            return value;
        }
    }


    //签名类型：下拉加载项{商标证、网站、app、其他}
    public enum SignType {

        BRAND(1, "brand"),
        SITES(2, "sites"),
        APP(3, "app"),
        OTHER(4, "other");

        private final int value;
        private final String type;

        private SignType(int value, String type) {
            this.value = value;
            this.type = type;
        }

        public static SignType getType(int value) {
            switch (value) {
                case 1:
                    return BRAND;
                case 2:
                    return SITES;
                case 3:
                    return APP;
                case 4:
                    return OTHER;
                default:
                    throw new RuntimeException("Unsupport Sign Type: " + value);
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

    public Integer getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Integer enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public SignType getType() {
        return type;
    }

    public void setType(SignType type) {
        this.type = type;
    }

    public void setTypeIndex(Integer index) {
        this.type = SignType.getType(index);
    }

    public SignState getState() {
        return state;
    }

    public void setState(SignState state) {
        this.state = state;
    }

    public void setStateIndex(Integer index) {
        this.state = SignState.getState(index);
    }

    public String getCertifyFile() {
        return CertifyFile;
    }

    public void setCertifyFile(String certifyFile) {
        CertifyFile = certifyFile;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Boolean getIsRemove() {
        return isRemove;
    }

    public void setIsRemove(Boolean isRemove) {
        this.isRemove = isRemove;
    }

    @Override
    public String toString() {
        return "UserSign{" +
                "isRemove=" + isRemove +
                ", createTime=" + createTime +
                ", CertifyFile='" + CertifyFile + '\'' +
                ", state=" + state +
                ", type=" + type +
                ", sign='" + sign + '\'' +
                ", enterpriseId='" + enterpriseId + '\'' +
                '}';
    }
}
