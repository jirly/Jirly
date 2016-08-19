package com.xuanwu.cmp.domain.repo.impl;

import com.xuanwu.cmp.db.GsmsMybatisEntityRepository;
import com.xuanwu.cmp.domain.entity.UserSign;
import com.xuanwu.cmp.domain.repo.UserSignRepo;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

/**
 * UserSignRepo 企业签名
 * @author <a href="mailto:jiangpeng@wxchina.com">Peng.Jiang</a>
 * @date 2016-08-10
 * @version 1.0.0
 */
@Repository
public class UserSignRepoImpl extends GsmsMybatisEntityRepository<UserSign> implements UserSignRepo {

	private Logger logger = LoggerFactory.getLogger(UserSignRepoImpl.class);

	@Override
	protected String namesapceForSqlId() {
		return "com.xuanwu.cmp.mapper.UserSignMapper";
	}

	@Override
	public List<UserSign> findByEnterprise(Integer enterpriseId) {
		List<UserSign> signList = null;
		try (SqlSession session = sqlSessionFactory.openSession()) {
			signList = session.selectList(fullSqlId("findByEnterprise"),enterpriseId);
		} catch (Exception e) {
			logger.error("find UserSign By Enterprise Id: ", e);
		}
		return signList;
	}

	@Override
	public int remove(Integer id, Integer enterpriseId) {
		try (SqlSession session = sqlSessionFactory.openSession()) {
			HashMap<String, Serializable> params = new HashMap<String, Serializable>();
			params.put("id", id);
			params.put("enterpriseId", enterpriseId);
			return session.update(fullSqlId("remove"),params);
		} catch (Exception e) {
			logger.error("find UserSign By Enterprise Id: ", e);
		}
		return 0;
	}

	@Override
	public boolean checkSign(Integer enterpriseId, String sign) {
		try (SqlSession session = sqlSessionFactory.openSession()) {
			HashMap<String, String> params = new HashMap<String, String>();
			params.put("enterpriseId", enterpriseId.toString());
			params.put("sign", sign);
			return session.selectOne(fullSqlId("findBySign"),params) == null ;
		} catch (Exception e) {
			logger.error("checkSign : ", e);
		}
		return false;
	}
}
