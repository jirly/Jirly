package com.xuanwu.cmp.domain.repo.impl;

import com.xuanwu.cmp.utils.QueryParameters;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.xuanwu.cmp.db.GsmsMybatisEntityRepository;
import com.xuanwu.cmp.domain.entity.Phrase;
import com.xuanwu.cmp.domain.repo.PhraseRepo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description PhraseRepoImpl
 * @author <a href="mailto:jiangpeng@wxchina.com">Peng.Jiang</a>
 * @date 2016-08-10
 * @version 1.0.0
 */
@Repository
public class PhraseRepoImpl extends GsmsMybatisEntityRepository<Phrase> implements PhraseRepo {

	private Logger logger = LoggerFactory.getLogger(PhraseRepoImpl.class);
	@Override
	protected String namesapceForSqlId() {
		return "com.xuanwu.cmp.mapper.PhraseMapper";
	}

	@Override
	public int remove(Integer id, Integer enterpriseId) {
		try (SqlSession session = sqlSessionFactory.openSession()) {
			HashMap<String, Serializable> params = new HashMap<String, Serializable>();
			params.put("id", id);
			params.put("enterpriseId", enterpriseId);
			return session.update(fullSqlId("remove"),params);
		} catch (Exception e) {
			logger.error("remove Phrase By Id: ", e);
		}
		return 0;
	}

	@Override
	public List<Phrase> getIdentifys(Map params) {
		try (SqlSession session = sqlSessionFactory.openSession()) {
			return session.selectList(fullSqlId("getIdentifys"),params);
		} catch (Exception e) {
			logger.error("remove Phrase By Id: ", e);
		}
		return null;
	}

	@Override
	public Phrase findByEnterpriseApp(QueryParameters params) {
		try (SqlSession session = sqlSessionFactory.openSession()) {
			return session.selectOne(fullSqlId("findByEnterpriseApp"), params);
		}
	}

}
