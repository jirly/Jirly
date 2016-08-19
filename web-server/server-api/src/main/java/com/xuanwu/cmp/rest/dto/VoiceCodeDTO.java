package com.xuanwu.cmp.rest.dto;

import com.xuanwu.cmp.util.Validator;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * Voice code Data Transfer Object
 *
 * @Author <a href="zhangjinxiu@wxchina.com">Drizzt</a>
 * @Date 2016-08-11
 * @Version 1.0.0
 */
public class VoiceCodeDTO extends CommentDTO {

    @Pattern(regexp = Validator.REGEX_MOBILE, message = "send_to需要使用手机号码格式")
    private String send_to;

    @NotNull(message = "template_id不能为空")
    private String template_id;

    @NotNull(message = "params不能为空")
    private String params;

    private String number;

    public String getSend_to() {
        return send_to;
    }

    public void setSend_to(String send_to) {
        this.send_to = send_to;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getTemplate_id() {
        return template_id;
    }

    public void setTemplate_id(String template_id) {
        this.template_id = template_id;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    @Override
    public String toString() {
        return "VoiceCodeDTO{" +
                "send_to='" + send_to + '\'' +
                ", template_id='" + template_id + '\'' +
                ", params='" + params + '\'' +
                ", number='" + number + '\'' +
                "} " + super.toString();
    }
}
