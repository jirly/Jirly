package com.xuanwu.cmp.domain.repo.impl;

import com.xuanwu.cmp.db.ListMybatisEntityRepository;
import com.xuanwu.cmp.domain.entity.Keyword;
import com.xuanwu.cmp.domain.repo.KeywordRepo;
import org.springframework.stereotype.Repository;

/**
 * Created by 林泽强 on 2016/8/17.
 */
@Repository
public class KeywordRepoImpl extends ListMybatisEntityRepository<Keyword> implements KeywordRepo {
    @Override
    protected String namesapceForSqlId() {
        return "com.xuanwu.cmp.mapper.KeywordMapper";
    }
}
