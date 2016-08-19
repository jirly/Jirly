package com.xuanwu.cmp.rest.reponse.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * the reponse of success
 *
 * @Author <a href="zhangjinxiu@wxchina.com">Drizzt</a>
 * @Date 2016-08-11
 * @Version 1.0.0
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "success")
public class SuccessRespEntity extends AbstractRespEntity {

    private static final String SUCCESS = "success";

    @XmlElement
    protected String msg;

    @XmlElement
    protected String msgId;

    public SuccessRespEntity(int code) {
        super(code);
        this.setMsg(SUCCESS);
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    @Override
    public String toString() {
        return "SuccessRespEntity{" +
                "msg='" + msg + '\'' +
                ", msgId='" + msgId + '\'' +
                '}';
    }
}
