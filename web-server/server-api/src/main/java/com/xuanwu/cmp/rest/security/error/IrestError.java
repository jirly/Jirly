package com.xuanwu.cmp.rest.security.error;

/**
 * define the error behavior
 *
 * @Author <a href="zhangjinxiu@wxchina.com">Drizzt</a>
 * @Date 2016-08-11
 * @Version 1.0.0
 */
public interface IrestError {

    /**
     * Get the error code
     *
     * @return error code
     */
    int getCode();

    /**
     * Get the error msg
     *
     * @return error msg
     */
    String getMessage();

    /**
     * Get the error suggest
     *
     * @return error suggest
     */
    String getSuggest();

}

