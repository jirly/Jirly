package com.xuanwu.cmp.domain.repo.impl;

import com.xuanwu.cmp.domain.entity.Region;
import com.xuanwu.cmp.utils.QueryParameters;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.xuanwu.cmp.db.GsmsMybatisEntityRepository;
import com.xuanwu.cmp.domain.entity.CarrierChannel;
import com.xuanwu.cmp.domain.repo.ChannelInfoRepo;

import java.io.Serializable;
import java.util.List;

/**
 * @Description ChannelInfoRepoImpl
 * @author <a href="jiangziyuan@wxchina.com">ZiYuan.Jiang</a>
 * @date 2016-08-11
 * @version 1.0.0
 */
@Repository
public class ChannelInfoRepoImpl extends GsmsMybatisEntityRepository <CarrierChannel> implements ChannelInfoRepo {
	private Logger logger = LoggerFactory.getLogger(ChannelInfoRepoImpl.class);

	@Override
	protected String namesapceForSqlId() {
		return "com.xuanwu.cmp.mapper.ChannelInfoMapper";
	}

	@Override
	public List<Region> listAllRegion() {
		List<Region> regionList = null;
		try (SqlSession session = sqlSessionFactory.openSession()) {
			//regionList = session.selectList(fullSqlId("findByEnterprise"),id);
			regionList = session.selectList(fullSqlId("listAllRegion"));
		} catch (Exception e) {
			logger.error("find Region : ", e);
		}
		return regionList;
	}

	@Override
	public List<Region> listAllCitys(QueryParameters params) {
		List<Region> regionList = null;
		try (SqlSession session = sqlSessionFactory.openSession()) {
			regionList = session.selectList(fullSqlId("listAllCitys"),params);
		} catch (Exception e) {
			logger.error("find RegionMap: ", e);
		}
		return regionList;
	}

	@Override
	public int deleteById(Serializable id, Integer enterpriseId) {
		return 0;
	}

	@Override
	public int deleteByIdWithPath(Serializable id, Integer enterpriseId, String path) {
		return 0;
	}

	@Override
	public CarrierChannel getById(Serializable id, Integer enterpriseId) {
		return null;
	}

	@Override
	public CarrierChannel getByIdWithPath(Serializable id, Integer enterpriseId, String path) {
		return null;
	}
}
