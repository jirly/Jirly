package com.xuanwu.cmp.rest.reponse.entity;

import javax.xml.bind.annotation.XmlElement;

/**
 * Comment Data Transfer Object
 *
 * @Author <a href="zhangjinxiu@wxchina.com">Drizzt</a>
 * @Date 2016-08-11
 * @Version 1.0.0
 */
public abstract class AbstractRespEntity implements RestEntity {

    @XmlElement
    protected int code;

    @XmlElement
    protected long timestamp;

    public AbstractRespEntity() {
    }

    public AbstractRespEntity(int code) {
        this.code = code;
    }

    public AbstractRespEntity(int code, long timestamp) {
        this.code = code;
        this.timestamp = timestamp;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
