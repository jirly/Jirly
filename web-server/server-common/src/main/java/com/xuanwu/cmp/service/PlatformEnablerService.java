package com.xuanwu.cmp.service;

import java.util.Collection;

import com.xuanwu.cmp.domain.entity.PlatformEnabler;
import com.xuanwu.cmp.utils.QueryParameters;

/**
 * @Description PlatformEnablerService
 * @author <a href="mailto:jiji@javawind.com">XueFang.Xu</a>
 * @date 2016-08-11
 * @version 1.0.0
 */
public interface PlatformEnablerService {

	public Collection<PlatformEnabler> list(QueryParameters params);

}
