/*
 * Copyright (c) 2015 by XuanWu Wireless Technology Co., Ltd. 
 *             All rights reserved                         
 */
package com.xuanwu.cmp.db;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xuanwu.cmp.domain.Entity;
import com.xuanwu.cmp.utils.QueryParameters;

/**
 * 基于MyBatis的基本仓储实现
 * 
 * @author <a href="mailto:liushuaiying@139130.net">Shuaiying.Liu</a>
 * @Data 2015年5月27日
 * @Version 1.0.0
 */
public abstract class MybatisEntityRepository<T extends Entity> implements EntityRepository<T> {

	private Logger logger = LoggerFactory.getLogger(MybatisEntityRepository.class);

	protected SqlSessionFactory sqlSessionFactory;

	protected abstract void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory);

	protected abstract String namesapceForSqlId();

	protected String fullSqlId(String sqlId) {
		return namesapceForSqlId() + "." + sqlId;
	}

	@Override
	public T save(T t) {
		return saveByPath(t, "update", "insert");
	}

	@Override
	public T saveByPath(T t, String updatePath, String insertPath) {
		try (SqlSession session = sqlSessionFactory.openSession()) {
			int ret = 0;
			if (t.getId() != null) {
				ret = session.update(fullSqlId(updatePath), t);
			}
			if (ret == 0) {
				ret = session.insert(fullSqlId(insertPath), t);
			}
			t.setSaveSuccess(ret > 0);
			session.commit(true);
		} catch (Exception e) {
			logger.error("Save/Update Entity failed: ", e);
			t.setSaveSuccess(false);
		}
		return t;
	}

	@Override
	@SuppressWarnings("unchecked")
	public int saveBatch(T... t) {
		int count = 0;
		try (SqlSession session = sqlSessionFactory.openSession(ExecutorType.BATCH)) {
			count = session.update(fullSqlId("insertBatch"), t);
			session.commit(true);
		} catch (Exception e) {
			logger.error("SaveBatch Entities failed: ", e);
		}
		return count;
	}

	@Override
	public int updateSpecify(T t) {
		return updateSpecifyByPath(t, "updateSpecify");
	}

	@Override
	public int updateSpecifyByPath(T t, String path) {
		int count = 0;
		try (SqlSession session = sqlSessionFactory.openSession()) {
			count = session.update(fullSqlId(path), t);
			session.commit(true);
		} catch (Exception e) {
			logger.error("UpdateSpecify Entity failed: ", e);
		}
		return count;
	}

	@Override
	public int deleteById(Serializable id, Integer enterpriseId) {
		return deleteByIdWithPath(id, enterpriseId, "deleteById");
	}

	@Override
	public int deleteByIdWithPath(Serializable id, Integer enterpriseId, String path) {
		int count = 0;
		try (SqlSession session = sqlSessionFactory.openSession()) {
			HashMap<String, Serializable> params = new HashMap<String, Serializable>();
			params.put("id", id);
			params.put("enterpriseId", enterpriseId);
			count = session.delete(fullSqlId(path), params);
			session.commit(true);
		} catch (Exception e) {
			logger.error("Remove Entity failed: ", e);
		}
		return count;
	}

	@Override
	public int delete(T t) {
		return deleteByPath(t, "delete");
	}

	@Override
	public int deleteByPath(T t, String path) {
		int count = 0;
		try (SqlSession session = sqlSessionFactory.openSession()) {
			count = session.delete(fullSqlId(path), t);
			session.commit(true);
		} catch (Exception e) {
			logger.error("Remove Entity failed: ", e);
		}
		return count;
	}

	@Override
	public T getById(Serializable id, Integer enterpriseId) {
		return getByIdWithPath(id, enterpriseId, "getById");
	}

	@Override
	public T getByIdWithPath(Serializable id, Integer enterpriseId, String path) {
		try (SqlSession session = sqlSessionFactory.openSession()) {
			HashMap<String, Serializable> params = new HashMap<String, Serializable>();
			params.put("id", id);
			params.put("enterpriseId", enterpriseId);
			return session.selectOne(fullSqlId(path), params);
		}
	}

	@Override
	public int findResultCount(QueryParameters params) {
		return findResultCountByPath(params, "findResultCount");
	}

	@Override
	public int findResultCountByPath(QueryParameters params, String path) {
		try (SqlSession session = sqlSessionFactory.openSession()) {
			return session.selectOne(fullSqlId(path), params);
		}
	}

	@Override
	public List<T> findResults(QueryParameters params) {
		return findResultsByPath(params, "findResults");
	}

	@Override
	public List<T> findResultsByPath(QueryParameters params, String path) {
		try (SqlSession session = sqlSessionFactory.openSession()) {
			return session.selectList(fullSqlId(path), params);
		}
	}

}
