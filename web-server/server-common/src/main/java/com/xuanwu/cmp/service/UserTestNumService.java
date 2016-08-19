package com.xuanwu.cmp.service;

import java.util.Collection;

import com.xuanwu.cmp.domain.entity.App;
import com.xuanwu.cmp.domain.entity.Phrase;
import com.xuanwu.cmp.domain.entity.UserTestNum;
import com.xuanwu.cmp.utils.QueryParameters;

/**
 * @Description UserTestNumService.java
 * @author <a href="mailto:miaojiepu@wxchina.com">Jiepu.Miao</a>
 * @date 2016年8月16日
 * @version 1.0.0
 */
public interface UserTestNumService {

	public Collection<UserTestNum> list(QueryParameters params);

	public int count(QueryParameters params);

	public UserTestNum save(UserTestNum userTestNum);

	public UserTestNum getByNumber(String number);

	public App getTestApp(Integer enterpriseId);

	public Collection<Phrase> getTestPhrases();

	public int deleteById(Integer id, Integer enterpriseId);

}
