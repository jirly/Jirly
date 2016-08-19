package com.xuanwu.cmp.service;

import java.util.List;

import com.xuanwu.cmp.domain.entity.CarrierChannel;
import com.xuanwu.cmp.domain.entity.Region;
import com.xuanwu.cmp.utils.QueryParameters;

/**
 * @Description ChannelInfoService
 * @author <a href="jiangziyuan@wxchina.com">ZiYuan.Jiang</a>
 * @date 2016-8-11
 * @version 1.0.0
 */
public interface ChannelInfoService {
	public Integer count(QueryParameters params);

	public List<CarrierChannel> list(QueryParameters params);

    public CarrierChannel get(Integer id, Integer enterpriseId);

	public CarrierChannel save(final CarrierChannel user);

	public int updateSpecify(final CarrierChannel user);

    public int deleteById(Integer id, Integer enterpriseId);

	public List<Region> findAllRegions();

    public List<Region> findAllCitys(QueryParameters params);
}
