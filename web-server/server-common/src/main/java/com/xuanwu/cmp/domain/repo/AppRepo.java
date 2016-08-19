package com.xuanwu.cmp.domain.repo;

import com.xuanwu.cmp.db.EntityRepository;
import com.xuanwu.cmp.domain.entity.App;
import com.xuanwu.cmp.domain.entity.App.AppState;
import com.xuanwu.cmp.utils.QueryParameters;

/**
 * @Description AppRepo
 * @author <a href="mailto:jiji@javawind.com">XueFang.Xu</a>
 * @date 2016-08-10
 * @version 1.0.0
 */
public interface AppRepo extends EntityRepository<App> {

	public void insertEnablers(App app);

	public int updateState(int appId, int enterpriseId, AppState state);

	App getByIdentify(QueryParameters param);
}
