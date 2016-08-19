package com.xuanwu.cmp.service.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xuanwu.cmp.domain.entity.App;
import com.xuanwu.cmp.domain.entity.Phrase;
import com.xuanwu.cmp.domain.entity.UserTestNum;
import com.xuanwu.cmp.domain.repo.UserTestNumRepo;
import com.xuanwu.cmp.service.UserTestNumService;
import com.xuanwu.cmp.utils.QueryParameters;

/**
 * @Description UserTestNumServiceImpl.java
 * @author <a href="mailto:miaojiepu@wxchina.com">Jiepu.Miao</a>
 * @date 2016年8月16日
 * @version 1.0.0
 */
@Service
public class UserTestNumServiceImpl implements UserTestNumService {
	@Autowired
	private UserTestNumRepo userTestNumRepo;

	@Override
	public Collection<UserTestNum> list(QueryParameters params) {
		return userTestNumRepo.findResults(params);
	}

	@Override
	public int count(QueryParameters params) {
		return userTestNumRepo.findResultCount(params);
	}

	public int deleteById(Integer id, Integer enterpriseId) {
		return userTestNumRepo.deleteById(id, enterpriseId);
	}

	@Override
	public UserTestNum save(UserTestNum userTestNum) {
		return userTestNumRepo.save(userTestNum);
	}

	@Override
	public UserTestNum getByNumber(String number) {
		return userTestNumRepo.getByNumber(number);
	}

	@Override
	public App getTestApp(Integer enterpriseId) {
		return userTestNumRepo.getTestApp(enterpriseId);
	}

	@Override
	public Collection<Phrase> getTestPhrases() {
		return userTestNumRepo.getTestPhrases();
	}

}
