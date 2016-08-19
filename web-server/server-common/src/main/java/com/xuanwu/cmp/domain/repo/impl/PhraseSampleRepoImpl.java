package com.xuanwu.cmp.domain.repo.impl;

import com.xuanwu.cmp.db.GsmsMybatisEntityRepository;
import com.xuanwu.cmp.domain.entity.PhraseSample;
import com.xuanwu.cmp.domain.repo.PhraseSampleRepo;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

/**
 * @Description PhraseSampleRepoImpl
 * @author <a href="mailto:jiangpeng@wxchina.com">Peng.Jiang</a>
 * @date 2016-08-10
 * @version 1.0.0
 */
@Repository
public class PhraseSampleRepoImpl extends GsmsMybatisEntityRepository<PhraseSample> implements PhraseSampleRepo {

	private Logger logger = LoggerFactory.getLogger(PhraseSampleRepoImpl.class);
	@Override
	protected String namesapceForSqlId() {
		return "com.xuanwu.cmp.mapper.PhraseSampleMapper";
	}

	@Override
	public int remove(Integer id) {
		try (SqlSession session = sqlSessionFactory.openSession()) {
			HashMap<String, Serializable> params = new HashMap<String, Serializable>();
			return session.update(fullSqlId("remove"),id);
		} catch (Exception e) {
			logger.error("remove PhraseSample By Id: ", e);
		}
		return 0;
	}

	@Override
	public List<PhraseSample> findAll() {
		try (SqlSession session = sqlSessionFactory.openSession()) {
			return session.selectList(fullSqlId("findAll"));
		} catch (Exception e) {
			logger.error("findAll PhraseSample: ", e);
		}
		return null;
	}
}
