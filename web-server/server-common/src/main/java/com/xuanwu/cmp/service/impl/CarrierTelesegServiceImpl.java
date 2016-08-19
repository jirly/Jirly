package com.xuanwu.cmp.service.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xuanwu.cmp.domain.entity.CarrierTeleseg;
import com.xuanwu.cmp.domain.repo.CarrierTelesegRepo;
import com.xuanwu.cmp.service.CarrierTelesegService;
import com.xuanwu.cmp.utils.QueryParameters;

/**
 * Created by 林泽强 on 2016/8/10.
 */
@Service
public class CarrierTelesegServiceImpl implements CarrierTelesegService {
	@Autowired
	private CarrierTelesegRepo carrierTelesegRepo;

	@Override
	public Integer count(QueryParameters params) {
		return carrierTelesegRepo.findResultCount(params);
	}

	@Override
	public Collection<CarrierTeleseg> list(QueryParameters params) {
		return carrierTelesegRepo.findResults(params);
	}

	@Override
	public CarrierTeleseg get(Integer id, Integer enterpriseId) {
		return carrierTelesegRepo.getById(id, enterpriseId);
	}

	@Override
	public CarrierTeleseg save(CarrierTeleseg carrierTeleseg) {
		return carrierTelesegRepo.save(carrierTeleseg);
	}

	@Override
	public int updateSpecify(CarrierTeleseg carrierTeleseg) {
		return carrierTelesegRepo.updateSpecify(carrierTeleseg);
	}

	@Override
	public int deleteById(Integer id, Integer enterpriseId) {
		return carrierTelesegRepo.deleteById(id, enterpriseId);
	}
}
