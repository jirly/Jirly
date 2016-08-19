package com.xuanwu.cmp.rest.reponse;

import com.xuanwu.cmp.rest.reponse.entity.ErrorRespEntity;
import com.xuanwu.cmp.rest.security.error.IrestError;
import com.xuanwu.cmp.utils.DateUtil;

/**
 * the error rest response entity factory
 *
 * @Author <a href="zhangjinxiu@wxchina.com">Drizzt</a>
 * @Date 2016-08-11
 * @Version 1.0.0
 */
public class ErrorRespEntityFactory implements RespEntityFactory<ErrorRespEntity> {

    private ErrorRespEntityFactory() {
    }

    private static ErrorRespEntityFactory errorRespEntityFactory = new ErrorRespEntityFactory();

    public static ErrorRespEntityFactory getErrorRespEntityFactory() {
        return errorRespEntityFactory;
    }

    @Override
    public  ErrorRespEntity genRespEntity() {
        return new ErrorRespEntity();
    }

    public static ErrorRespEntity genRespEntity(IrestError error) {
        ErrorRespEntity errorRespEntity = errorRespEntityFactory.genRespEntity();
        errorRespEntity.setMsg(error.getMessage());
        errorRespEntity.setCode(error.getCode());
        errorRespEntity.setSuggest(error.getSuggest());
        errorRespEntity.setTimestamp(DateUtil.getCurrentSecond());
        return errorRespEntity;
    }
}
