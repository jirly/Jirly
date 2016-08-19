package com.xuanwu.cmp.domain.repo.impl;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.xuanwu.cmp.db.GsmsMybatisEntityRepository;
import com.xuanwu.cmp.domain.entity.VoiceDisplayNum;
import com.xuanwu.cmp.domain.repo.VoiceDisplayNumRepo;

/**
 * @Description VoiceDisplayNumImpl.java
 * @author <a href="mailto:miaojiepu@wxchina.com">Jiepu.Miao</a>
 * @date 2016年8月16日
 * @version 1.0.0
 */
@Repository
public class VoiceDisplayNumImpl extends GsmsMybatisEntityRepository<VoiceDisplayNum> implements VoiceDisplayNumRepo {

	@Override
	protected String namesapceForSqlId() {
		return "com.xuanwu.cmp.mapper.VoiceDisplayNumMapper";
	}

	@Override
	public VoiceDisplayNum getByNumber(String displayNum) {
		try (SqlSession session = sqlSessionFactory.openSession()) {
			return session.selectOne(fullSqlId("getByNumber"), displayNum);
		}

	}

}
