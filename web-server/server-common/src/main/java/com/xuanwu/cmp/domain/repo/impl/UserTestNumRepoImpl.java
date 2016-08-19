package com.xuanwu.cmp.domain.repo.impl;

import java.util.Collection;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.xuanwu.cmp.db.GsmsMybatisEntityRepository;
import com.xuanwu.cmp.domain.entity.App;
import com.xuanwu.cmp.domain.entity.Phrase;
import com.xuanwu.cmp.domain.entity.UserTestNum;
import com.xuanwu.cmp.domain.repo.UserTestNumRepo;

/**
 * @Description UserTestNumRepoImpl.java
 * @author <a href="mailto:miaojiepu@wxchina.com">Jiepu.Miao</a>
 * @date 2016年8月16日
 * @version 1.0.0
 */
@Repository
public class UserTestNumRepoImpl extends GsmsMybatisEntityRepository<UserTestNum> implements UserTestNumRepo {

	@Override
	protected String namesapceForSqlId() {
		return "com.xuanwu.cmp.mapper.UserTestNumMapper";
	}

	@Override
	public UserTestNum getByNumber(String number) {
		try (SqlSession session = sqlSessionFactory.openSession()) {
			return session.selectOne(fullSqlId("getByNumber"), number);
		}
	}

	@Override
	public App getTestApp(Integer enterpriseId) {
		try (SqlSession session = sqlSessionFactory.openSession()) {
			return session.selectOne(fullSqlId("getTestApp"), enterpriseId);
		}
	}

	@Override
	public Collection<Phrase> getTestPhrases() {
		try (SqlSession session = sqlSessionFactory.openSession()) {
			return session.selectList(fullSqlId("getTestPhrase"));
		}
	}


}
