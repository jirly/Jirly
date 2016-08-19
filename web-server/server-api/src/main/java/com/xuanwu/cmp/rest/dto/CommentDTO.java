package com.xuanwu.cmp.rest.dto;

import javax.validation.constraints.NotNull;

/**
 * Comment Data Transfer Object
 *
 * @Author <a href="zhangjinxiu@wxchina.com">Drizzt</a>
 * @Date 2016-08-11
 * @Version 1.0.0
 */
public class CommentDTO {

    @NotNull(message = "app_id不能为空")
    protected String app_id;

    @NotNull(message = "sid不能为空")
    protected String sid;

    public String getApp_id() {
        return app_id;
    }

    public void setApp_id(String app_id) {
        this.app_id = app_id;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    @Override
    public String toString() {
        return "CommentDTO{" +
                "app_id='" + app_id + '\'' +
                ", sid='" + sid + '\'' +
                '}';
    }
}
