package com.xuanwu.cmp.service.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xuanwu.cmp.domain.entity.PlatformEnabler;
import com.xuanwu.cmp.domain.repo.PlatformEnablerRepo;
import com.xuanwu.cmp.service.PlatformEnablerService;
import com.xuanwu.cmp.utils.QueryParameters;

/**
 * @Description PlatformEnablerServiceImpl
 * @author <a href="mailto:jiji@javawind.com">XueFang.Xu</a>
 * @date 2016-08-11
 * @version 1.0.0
 */
@Service
public class PlatformEnablerServiceImpl implements PlatformEnablerService {

	@Autowired
	private PlatformEnablerRepo platformEnablerRepo;

	public Collection<PlatformEnabler> list(QueryParameters params) {
		return platformEnablerRepo.findResults(params);
	}

}
