package com.xuanwu.cmp.service.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xuanwu.cmp.domain.entity.Carrier;
import com.xuanwu.cmp.domain.repo.CarrierRepo;
import com.xuanwu.cmp.service.CarrierService;
import com.xuanwu.cmp.utils.QueryParameters;

/**
 * Created by 林泽强 on 2016/8/11.
 */
@Service
public class CarrierServiceImpl implements CarrierService {
    @Autowired
    private CarrierRepo carrierRepo;


    @Override
    public Collection<Carrier> list(QueryParameters params) {
        return carrierRepo.findResults(params);
    }

}
