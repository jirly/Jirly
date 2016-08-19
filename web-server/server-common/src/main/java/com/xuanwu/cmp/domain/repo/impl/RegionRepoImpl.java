package com.xuanwu.cmp.domain.repo.impl;

import org.springframework.stereotype.Repository;

import com.xuanwu.cmp.db.GsmsMybatisEntityRepository;
import com.xuanwu.cmp.domain.entity.Region;
import com.xuanwu.cmp.domain.repo.RegionRepo;

/**
 * Created by 林泽强 on 2016/8/15.
 */
@Repository
public class RegionRepoImpl extends GsmsMybatisEntityRepository<Region> implements RegionRepo {
    @Override
    protected String namesapceForSqlId() {
        return "com.xuanwu.cmp.mapper.RegionMapper";
    }
}
