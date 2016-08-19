package com.xuanwu.cmp.domain.repo.impl;

import org.springframework.stereotype.Repository;

import com.xuanwu.cmp.db.GsmsMybatisEntityRepository;
import com.xuanwu.cmp.domain.entity.PlatformEnabler;
import com.xuanwu.cmp.domain.repo.PlatformEnablerRepo;

/**
 * @Description PlatformEnablerRepoImpl
 * @author <a href="mailto:jiji@javawind.com">XueFang.Xu</a>
 * @date 2016-08-11
 * @version 1.0.0
 */
@Repository
public class PlatformEnablerRepoImpl extends GsmsMybatisEntityRepository<PlatformEnabler> implements PlatformEnablerRepo {

	@Override
	protected String namesapceForSqlId() {
		return "com.xuanwu.cmp.mapper.PlatformEnablerMapper";
	}

}
