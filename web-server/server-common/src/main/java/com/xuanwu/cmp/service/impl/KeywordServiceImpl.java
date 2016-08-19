package com.xuanwu.cmp.service.impl;

import com.xuanwu.cmp.domain.entity.Keyword;
import com.xuanwu.cmp.domain.repo.KeywordRepo;
import com.xuanwu.cmp.service.KeywordService;
import com.xuanwu.cmp.utils.QueryParameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;

/**
 * Created by 林泽强 on 2016/8/17.
 */
@Service
public class KeywordServiceImpl implements KeywordService{

    @Autowired
    private KeywordRepo keywordRepo;
    @Override
    public Integer count(QueryParameters params) {
        return keywordRepo.findResultCount(params);
    }

    @Override
    public Collection<Keyword> list(QueryParameters params) {
        return keywordRepo.findResults(params);
    }

    @Override
    public Keyword save(Keyword keyword) {
        return keywordRepo.save(keyword);
    }
}
