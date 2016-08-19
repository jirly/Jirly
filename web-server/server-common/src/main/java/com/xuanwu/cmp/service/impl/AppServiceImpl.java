package com.xuanwu.cmp.service.impl;

import com.xuanwu.cmp.domain.entity.App;
import com.xuanwu.cmp.domain.entity.App.AppState;
import com.xuanwu.cmp.domain.repo.AppRepo;
import com.xuanwu.cmp.domain.repo.UserTrustIpRepo;
import com.xuanwu.cmp.service.AppService;
import com.xuanwu.cmp.utils.QueryParameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * @Description AppServiceImpl
 * @author <a href="mailto:jiji@javawind.com">XueFang.Xu</a>
 * @date 2016-08-10
 * @version 1.0.0
 */
@Service
public class AppServiceImpl implements AppService {

	@Autowired
	private AppRepo appRepo;

	@Autowired
	private UserTrustIpRepo userTrustIpRepo;

	public Integer count(QueryParameters params) {
		return appRepo.findResultCount(params);
	}

	public Collection<App> list(QueryParameters params) {
		return appRepo.findResults(params);
	}

	public App get(Integer id, Integer enterpriseId) {
		return appRepo.getById(id, enterpriseId);
	}

	public App save(final App app) {
		if (app.getTrustIps() == null || app.getTrustIps().length == 0) {
			app.setEnabledTrustIp(false);
		} else {
			app.setEnabledTrustIp(true);
		}
		appRepo.save(app);
		if (app.isSaveSuccess()) {
			appRepo.insertEnablers(app);
			userTrustIpRepo.updateAppTrustIps(app);
		}
		return app;
	}

	public int updateSpecify(final App app) {
		return appRepo.updateSpecify(app);
	}

	public int deleteById(Integer id, Integer enterpriseId) {
		return appRepo.deleteById(id, enterpriseId);
	}

	public int updateState(int appId, int enterpriseId, AppState state) {
		return appRepo.updateState(appId, enterpriseId, state);
	}

	public App getByIdentify(QueryParameters params) {
		return appRepo.getByIdentify(params);
	}
}
