package com.xuanwu.cmp.service;

import com.xuanwu.cmp.domain.entity.RegionCarrierMap;
import com.xuanwu.cmp.utils.QueryParameters;

import java.util.Collection;

/**
 * Created by 林泽强 on 2016/8/15.
 */
public interface RegionCarrierMapService {

	public Integer count(QueryParameters params);

	public Collection<RegionCarrierMap> list(QueryParameters params);

	public RegionCarrierMap save(final RegionCarrierMap regionCarrierMap);

	public int deleteById(Integer id, Integer enterpriseId);

	public RegionCarrierMap get(Integer id, Integer enterpriseId);

}
