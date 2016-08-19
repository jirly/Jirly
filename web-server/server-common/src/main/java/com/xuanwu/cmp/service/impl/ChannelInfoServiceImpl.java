package com.xuanwu.cmp.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xuanwu.cmp.domain.entity.CarrierChannel;
import com.xuanwu.cmp.domain.entity.Region;
import com.xuanwu.cmp.domain.repo.ChannelInfoRepo;
import com.xuanwu.cmp.service.ChannelInfoService;
import com.xuanwu.cmp.utils.QueryParameters;

/**
 * @Description ChannelInfoServiceImpl
 * @author <a href="jiangziyuan@wxchina.com">ZiYuan.Jiang</a>
 * @date 2016-08-11
 * @version 1.0.0
 */
@Service
public class ChannelInfoServiceImpl implements ChannelInfoService {

	@Autowired
	private ChannelInfoRepo channelInfoRepo;

	public Integer count(QueryParameters params) {
		return channelInfoRepo.findResultCount(params);
	}

	public List<CarrierChannel> list(QueryParameters params) {
		return channelInfoRepo.findResults(params);
	}

	public CarrierChannel get(Integer id, Integer enterpriseId) {
		return channelInfoRepo.getById(id, enterpriseId);
	}

	public CarrierChannel save(final CarrierChannel carrierChannel) {
		if (carrierChannel.getId() == 0) {// add
			carrierChannel.setCreateTime(new Date());
		}
		return channelInfoRepo.save(carrierChannel);
	}

	public int updateSpecify(final CarrierChannel app) {
		return channelInfoRepo.updateSpecify(app);
	}

	public int deleteById(Integer id,Integer enterpriseId) {
		return channelInfoRepo.deleteById(id, enterpriseId);
	}

	public List<Region> findAllRegions() {
		return channelInfoRepo.listAllRegion();
	}

	public List<Region> findAllCitys(QueryParameters params){
		return channelInfoRepo.listAllCitys(params);
	}

}
