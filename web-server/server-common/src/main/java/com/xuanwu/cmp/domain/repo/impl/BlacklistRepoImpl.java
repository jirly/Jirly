package com.xuanwu.cmp.domain.repo.impl;

import com.xuanwu.cmp.db.ListMybatisEntityRepository;
import com.xuanwu.cmp.domain.entity.Blacklist;
import com.xuanwu.cmp.domain.entity.RegionCarrierMap;
import com.xuanwu.cmp.domain.repo.BlacklistRepo;
import com.xuanwu.cmp.domain.repo.RegionCarrierMapRepo;
import org.springframework.stereotype.Repository;

/**
 * Created by 林泽强 on 2016/8/16.
 */
@Repository
public class BlacklistRepoImpl extends ListMybatisEntityRepository<Blacklist> implements BlacklistRepo {
    @Override
    protected String namesapceForSqlId() {
        return "com.xuanwu.cmp.mapper.BlacklistMapper";
    }
}
