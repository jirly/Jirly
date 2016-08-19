package com.xuanwu.cmp.service;

import com.xuanwu.cmp.domain.entity.Keyword;
import com.xuanwu.cmp.utils.QueryParameters;

import java.util.Collection;

/**
 * Created by 林泽强 on 2016/8/17.
 */
public interface KeywordService {

    public Integer count(QueryParameters params);

    public Collection<Keyword> list(QueryParameters params);

    public Keyword save(final Keyword keyword);
}
