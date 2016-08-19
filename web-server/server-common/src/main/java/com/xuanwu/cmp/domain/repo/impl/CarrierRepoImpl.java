package com.xuanwu.cmp.domain.repo.impl;

import org.springframework.stereotype.Repository;

import com.xuanwu.cmp.db.GsmsMybatisEntityRepository;
import com.xuanwu.cmp.domain.entity.Carrier;
import com.xuanwu.cmp.domain.repo.CarrierRepo;

/**
 * Created by 林泽强 on 2016/8/11.
 */
@Repository
public class CarrierRepoImpl extends GsmsMybatisEntityRepository<Carrier> implements CarrierRepo {
    @Override
    protected String namesapceForSqlId() {
        return "com.xuanwu.cmp.mapper.CarrierMapper";
    }
}
