package com.xuanwu.cmp.service;

import com.xuanwu.cmp.domain.entity.Blacklist;
import com.xuanwu.cmp.utils.QueryParameters;

import java.util.Collection;

/**
 * Created by 林泽强 on 2016/8/16.
 */
public interface BlacklistService {


    public Integer count(QueryParameters params);

    public Collection<Blacklist> list(QueryParameters params);



}
