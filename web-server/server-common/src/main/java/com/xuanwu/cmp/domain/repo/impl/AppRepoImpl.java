package com.xuanwu.cmp.domain.repo.impl;

import java.io.Serializable;
import java.util.HashMap;

import com.xuanwu.cmp.utils.QueryParameters;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.xuanwu.cmp.db.GsmsMybatisEntityRepository;
import com.xuanwu.cmp.domain.entity.App;
import com.xuanwu.cmp.domain.entity.App.AppState;
import com.xuanwu.cmp.domain.repo.AppRepo;

/**
 * @Description AppRepoImpl
 * @author <a href="mailto:jiji@javawind.com">XueFang.Xu</a>
 * @date 2016-08-10
 * @version 1.0.0
 */
@Repository
public class AppRepoImpl extends GsmsMybatisEntityRepository<App> implements AppRepo {

	@Override
	protected String namesapceForSqlId() {
		return "com.xuanwu.cmp.mapper.AppMapper";
	}

	@Override
	public void insertEnablers(App app) {
		Integer appId = app.getId();
		try (SqlSession session = sqlSessionFactory.openSession()) {
			session.delete(fullSqlId("deleteEnablers"), appId);
			for (Integer enabler : app.getEnablers()) {
				HashMap<String, Integer> map = new HashMap<String, Integer>();
				map.put("appId", appId);
				map.put("enablerType", enabler);
				session.insert(fullSqlId("addEnablers"), map);
			}
			session.commit();
		}
	}

	public int updateState(int appId, int enterpriseId, AppState state) {
		int count = 0;
		try (SqlSession session = sqlSessionFactory.openSession()) {
			HashMap<String, Integer> map = new HashMap<String, Integer>();
			map.put("appId", appId);
			map.put("enterpriseId", enterpriseId);
			map.put("state", state.getIndex());
			count = session.update(fullSqlId("updateState"), map);
			session.commit();
		}
		return count;
	}

	@Override
	public App getByIdentify(QueryParameters params) {
		try (SqlSession session = sqlSessionFactory.openSession()) {
			return session.selectOne(fullSqlId("getByIdentify"), params);
		}
	}

}
