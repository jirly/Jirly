package com.xuanwu.cmp.rest.reponse;

import com.xuanwu.cmp.rest.reponse.entity.AbstractRespEntity;

/**
 * the rest response entity factory
 *
 * @Author <a href="zhangjinxiu@wxchina.com">Drizzt</a>
 * @Date 2016-08-11
 * @Version 1.0.0
 */
public interface RespEntityFactory <T> {

    <T extends AbstractRespEntity> T genRespEntity();

}
