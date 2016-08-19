/*   
* Copyright (c) 2016/8/12 by XuanWu Wireless Technology Co., Ltd 
*             All rights reserved  
*/
package com.xuanwu.cmp.domain.entity;

import com.xuanwu.cmp.domain.AbstractEntity;

/**
 * @author <a href="mailto:jiangpeng@wxchina.com">Peng.Jiang</a>
 * @version 1.0.0
 * @date 2016/8/12
 */
public class PhraseAuditMaterial extends AbstractEntity {

    private static final long serialVersionUID = 8125878538412348464L;

    private Integer id; // Id
    private Integer phraseId; // 模板ID
    private AppType appType; //应用类型:1-已上线网页/APP,2-未上线网页/APP
    private String appUrl; //应用链接
    private String appLogo; //应用名截图
    private String appVerifyPage; //app 验证页面截图路径

    //已上线网页/APP,2-未上线网页/APP
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

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPhraseId() {
        return phraseId;
    }

    public void setPhraseId(Integer phraseId) {
        this.phraseId = phraseId;
    }

    public AppType getAppType() {
        return appType;
    }

    public void setAppType(AppType appType) {
        this.appType = appType;
    }

    public void setAppTypeIndex(Integer index) {
        this.appType = AppType.getType(index);
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
}
