package com.xuanwu.cmp.rest.security.error;

/**
 * the rest error implementation
 *
 * @Author <a href="zhangjinxiu@wxchina.com">Drizzt</a>
 * @Date 2016-08-11
 * @Version 1.0.0
 */
public class RestError implements IrestError {

    /**
     * error code
     */
    private int code;

    /**
     * error msg
     */
    private String message;

    /**
     * error suggest
     */
    private String suggest;

    private RestError() {
    }

    private RestError(int code, String message, String suggest) {
        this.code = code;
        this.message = message;
        this.suggest = suggest;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public String getSuggest() {
        return suggest;
    }

    /**
     * get rest error
     *
     * @param code    code
     * @param message msg
     * @param suggest suggest
     * @return RestError
     */
    public static RestError getRestError(final int code, final String message, final String suggest) {
        return new RestError(code, message, suggest);
    }

}

