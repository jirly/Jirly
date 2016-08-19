package com.xuanwu.cmp.domain.repo;

import com.xuanwu.cmp.db.EntityRepository;
import com.xuanwu.cmp.domain.entity.App;
import com.xuanwu.cmp.domain.entity.UserTrustIp;

/**
 * @Description UserTrustIpRepo
 * @author <a href="mailto:jiji@javawind.com">XueFang.Xu</a>
 * @date 2016-08-16
 * @version 1.0.0
 */
public interface UserTrustIpRepo extends EntityRepository<UserTrustIp> {

	public void updateAppTrustIps(App app);

}
