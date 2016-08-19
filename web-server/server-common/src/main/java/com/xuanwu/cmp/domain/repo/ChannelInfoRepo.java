package com.xuanwu.cmp.domain.repo;

import com.xuanwu.cmp.db.EntityRepository;
import com.xuanwu.cmp.domain.entity.CarrierChannel;
import com.xuanwu.cmp.domain.entity.Region;
import com.xuanwu.cmp.utils.QueryParameters;

import java.util.List;

/**
 * @Description ChannelInfoRepo
 * <a href="jiangziyuan@wxchina.com">ZiYuan.Jiang</a>
 * @date 2016-08-11
 * @version 1.0.0
 */
public interface ChannelInfoRepo extends EntityRepository<CarrierChannel> {
    public List<Region> listAllRegion();

    public List<Region> listAllCitys(QueryParameters params);

}
