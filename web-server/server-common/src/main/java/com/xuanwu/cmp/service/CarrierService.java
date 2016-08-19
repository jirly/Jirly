package com.xuanwu.cmp.service;

import java.util.Collection;

import com.xuanwu.cmp.domain.entity.Carrier;
import com.xuanwu.cmp.utils.QueryParameters;

/**
 * Created by 林泽强 on 2016/8/11.
 */
public interface CarrierService {

	public Collection<Carrier> list(QueryParameters params);

}
