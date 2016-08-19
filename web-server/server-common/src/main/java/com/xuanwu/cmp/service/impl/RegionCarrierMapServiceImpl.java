package com.xuanwu.cmp.service.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xuanwu.cmp.domain.entity.RegionCarrierMap;
import com.xuanwu.cmp.domain.repo.RegionCarrierMapRepo;
import com.xuanwu.cmp.service.RegionCarrierMapService;
import com.xuanwu.cmp.utils.QueryParameters;

/**
 * Created by 林泽强 on 2016/8/15.
 */
@Service
public class RegionCarrierMapServiceImpl implements RegionCarrierMapService {

	@Autowired
	private RegionCarrierMapRepo regionCarrierMapRepo;

	@Override
	public Integer count(QueryParameters params) {
		return regionCarrierMapRepo.findResultCount(params);
	}

	@Override
	public Collection<RegionCarrierMap> list(QueryParameters params) {
		return regionCarrierMapRepo.findResults(params);
	}

	@Override
	public RegionCarrierMap save(RegionCarrierMap regionCarrierMap) {
		return regionCarrierMapRepo.save(regionCarrierMap);
	}

	@Override
	public int deleteById(Integer id, Integer enterpriseId) {
		return regionCarrierMapRepo.deleteById(id,enterpriseId);
	}

	@Override
	public RegionCarrierMap get(Integer id, Integer enterpriseId) {
		return regionCarrierMapRepo.getById(id,enterpriseId);
	}


}
