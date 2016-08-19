package com.xuanwu.cmp.rest.security.error;

/**
 * IrestError type enum
 *
 * @Author <a href="zhangjinxiu@wxchina.com">Drizzt</a>
 * @Date 2016-08-11
 * @Version 1.0.0
 */
public enum ErrorType {

    SERVICE_CURRENTLY_UNAVAILABLE(9999),
    INVALID_HEADS(1001),
    INVALID_ARGUMENTS(1000),
    BUSINESS_LOGIC_ERROR(2000),
    NON_WHITE_LIST_IP(2001),
    WITHOUT_ENABLER(2002);

    /**
     * this code will be reponse to the client
     */
    private int code;

    ErrorType(int code) {
        this.code = code;
    }

    /**
     * value of this error type
     *
     * @return error code
     */
    public int value() {
        return this.code;
    }
}

