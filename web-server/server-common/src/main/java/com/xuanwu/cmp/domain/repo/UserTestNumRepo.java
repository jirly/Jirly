package com.xuanwu.cmp.domain.repo;

import java.util.Collection;

import com.xuanwu.cmp.db.EntityRepository;
import com.xuanwu.cmp.domain.entity.App;
import com.xuanwu.cmp.domain.entity.Phrase;
import com.xuanwu.cmp.domain.entity.UserTestNum;

/**
 * @Description UserTestNumRepo.java
 * @author <a href="mailto:miaojiepu@wxchina.com">Jiepu.Miao</a>
 * @date 2016年8月16日
 * @version 1.0.0
 */
public interface UserTestNumRepo extends EntityRepository<UserTestNum> {

	// 通过电话号码获取一个测试号码
	public UserTestNum getByNumber(String number);

	public App getTestApp(Integer enterpriseId);

	public Collection<Phrase> getTestPhrases();


}
