package com.xuanwu.cmp.service;

import com.xuanwu.cmp.domain.entity.Region;
import com.xuanwu.cmp.utils.QueryParameters;

import java.util.Collection;

/**
 * Created by 林泽强 on 2016/8/15.
 */
public interface RegionService {
    public Collection<Region> list(QueryParameters params);

}
