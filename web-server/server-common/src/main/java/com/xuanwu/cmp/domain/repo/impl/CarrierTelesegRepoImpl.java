package com.xuanwu.cmp.domain.repo.impl;

import org.springframework.stereotype.Repository;

import com.xuanwu.cmp.db.GsmsMybatisEntityRepository;
import com.xuanwu.cmp.domain.entity.CarrierTeleseg;
import com.xuanwu.cmp.domain.repo.CarrierTelesegRepo;

/**
 * Created by 林泽强 on 2016/8/10.
 */
@Repository
public class CarrierTelesegRepoImpl extends GsmsMybatisEntityRepository<CarrierTeleseg> implements CarrierTelesegRepo{
    @Override
    protected String namesapceForSqlId() {
        return "com.xuanwu.cmp.mapper.CarrierTelesegMapper";
    }
}
