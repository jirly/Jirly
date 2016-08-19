package com.xuanwu.cmp.service;

import com.xuanwu.cmp.domain.entity.App;
import com.xuanwu.cmp.utils.QueryParameters;

import java.util.Collection;

/**
 * @Description AppService
 * @author <a href="mailto:jiji@javawind.com">XueFang.Xu</a>
 * @date 2016-08-10
 * @version 1.0.0
 */
public interface AppService {

	public Integer count(QueryParameters params);

	public Collection<App> list(QueryParameters params);

	public App get(Integer id, Integer enterpriseId);

	public App save(final App user);

	public int updateSpecify(final App user);

	public int deleteById(Integer id, Integer enterpriseId);

	public int updateState(int appId, int enterpriseId, App.AppState state);

	public App getByIdentify(QueryParameters params);

}
