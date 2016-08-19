package com.xuanwu.cmp.rest.reponse.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * the comment rest response entity
 *
 * @Author <a href="zhangjinxiu@wxchina.com">Drizzt</a>
 * @Date 2016-08-11
 * @Version 1.0.0
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "error")
public class ErrorRespEntity extends AbstractRespEntity {

    @XmlElement
    protected String msg;

    @XmlElement
    protected String suggest;

    @XmlElement
    protected String msgId;

    public ErrorRespEntity() {
    }

    public ErrorRespEntity(int code, String msg, String suggest) {
        super(code);
        this.msg = msg;
        this.suggest = suggest;
    }

    public ErrorRespEntity(int code, long timestamp, String msg, String suggest) {
        super(code, timestamp);
        this.msg = msg;
        this.suggest = suggest;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getSuggest() {
        return suggest;
    }

    public void setSuggest(String suggest) {
        this.suggest = suggest;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    @Override
    public String toString() {
        return "ErrorRespEntity{" +
                "msg='" + msg + '\'' +
                ", suggest='" + suggest + '\'' +
                ", msgId='" + msgId + '\'' +
                '}';
    }
}
