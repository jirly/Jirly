package com.xuanwu.cmp.domain.repo.impl;

import java.util.Date;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.xuanwu.cmp.db.GsmsMybatisEntityRepository;
import com.xuanwu.cmp.domain.entity.App;
import com.xuanwu.cmp.domain.entity.Platform;
import com.xuanwu.cmp.domain.entity.UserTrustIp;
import com.xuanwu.cmp.domain.repo.UserTrustIpRepo;

/**
 * @Description UserTrustIpRepoImpl
 * @author <a href="mailto:jiji@javawind.com">XueFang.Xu</a>
 * @date 2016-08-16
 * @version 1.0.0
 */
@Repository
public class UserTrustIpRepoImpl extends GsmsMybatisEntityRepository<UserTrustIp> implements UserTrustIpRepo {

	@Override
	protected String namesapceForSqlId() {
		return "com.xuanwu.cmp.mapper.UserTrustIpMapper";
	}

	@Override
	public void updateAppTrustIps(App app) {
		int enterpriseId = app.getEnterpriseId();
		int appId = app.getId();
		String[] trustIps = app.getTrustIps();
		try (SqlSession session = sqlSessionFactory.openSession(ExecutorType.BATCH)) {
			session.update(fullSqlId("deleteByAppId"), appId);
			if (trustIps != null && trustIps.length > 0) {
				for (String ip : trustIps) {
					UserTrustIp userTrustIp = new UserTrustIp();
					userTrustIp.setEnterpriseId(enterpriseId);
					userTrustIp.setAppId(appId);
					userTrustIp.setTrustIp(ip.trim());
					userTrustIp.setPlatform(Platform.FRONTKIT);
					userTrustIp.setCreateTime(new Date());
					session.insert(fullSqlId("insert"), userTrustIp);
				}
			}
			session.commit(true);
		}
	}

}
