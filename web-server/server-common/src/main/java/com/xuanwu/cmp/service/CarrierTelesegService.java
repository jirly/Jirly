package com.xuanwu.cmp.service;

import java.util.Collection;

import com.xuanwu.cmp.domain.entity.CarrierTeleseg;
import com.xuanwu.cmp.utils.QueryParameters;

/**
 * Created by 林泽强 on 2016/8/10.
 */
public interface CarrierTelesegService {
	public Integer count(QueryParameters params);

	public Collection<CarrierTeleseg> list(QueryParameters params);

	public CarrierTeleseg get(Integer id, Integer enterpriseId);

	public CarrierTeleseg save(final CarrierTeleseg carrierTeleseg);

	public int updateSpecify(final CarrierTeleseg carrierTeleseg);

	public int deleteById(Integer id, Integer enterpriseId);
}
