package com.xuanwu.cmp.rest.reponse;

import com.xuanwu.cmp.rest.reponse.entity.AbstractRespEntity;
import com.xuanwu.cmp.rest.reponse.entity.ErrorRespEntity;
import com.xuanwu.cmp.rest.reponse.entity.SuccessRespEntity;

/**
 * the rest response entity factory
 *
 * @Author <a href="zhangjinxiu@wxchina.com">Drizzt</a>
 * @Date 2016-08-11
 * @Version 1.0.0
 */
public class SuccessRespEntityFactory implements RespEntityFactory<ErrorRespEntity> {

    private static final int SUCCESS_CODE = 0;

    private static SuccessRespEntityFactory successRespEntityFactory = new SuccessRespEntityFactory();

    private SuccessRespEntityFactory() {
    }

    @Override
    public AbstractRespEntity genRespEntity() {
        return new SuccessRespEntity(SUCCESS_CODE);
    }

    public static SuccessRespEntityFactory getSuccessRespEntityFactory() {
        return successRespEntityFactory;
    }
}
