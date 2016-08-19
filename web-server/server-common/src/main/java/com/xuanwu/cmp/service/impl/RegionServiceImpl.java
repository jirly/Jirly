package com.xuanwu.cmp.service.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xuanwu.cmp.domain.entity.Region;
import com.xuanwu.cmp.domain.repo.RegionRepo;
import com.xuanwu.cmp.service.RegionService;
import com.xuanwu.cmp.utils.QueryParameters;

/**
 * Created by 林泽强 on 2016/8/15.
 */
@Service
public class RegionServiceImpl implements RegionService {

	@Autowired
	private RegionRepo regionRepo;

	@Override
	public Collection<Region> list(QueryParameters params) {
		return regionRepo.findResults(params);
	}
}
