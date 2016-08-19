package com.xuanwu.cmp.service.impl;

import com.xuanwu.cmp.domain.entity.Blacklist;
import com.xuanwu.cmp.domain.repo.BlacklistRepo;
import com.xuanwu.cmp.service.BlacklistService;
import com.xuanwu.cmp.utils.QueryParameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * Created by 林泽强 on 2016/8/16.
 */
@Service
public class BlacklistServiceImpl implements BlacklistService {

    @Autowired
    private BlacklistRepo blacklistRepo;
    @Override
    public Integer count(QueryParameters params) {
        return blacklistRepo.findResultCount(params);
    }

    @Override
    public Collection<Blacklist> list(QueryParameters params) {
        return blacklistRepo.findResults(params);
    }
}
