package com.xuanwu.cmp.domain.repo;

import com.xuanwu.cmp.db.EntityRepository;
import com.xuanwu.cmp.domain.entity.UserSign;

import java.util.List;

/**
 * UserSignRepo 企业签名
 * @author <a href="mailto:jiangpeng@wxchina.com">Peng.Jiang</a>
 * @date 2016-08-10
 * @version 1.0.0
 */
public interface UserSignRepo extends EntityRepository<UserSign> {
     public List<UserSign> findByEnterprise(Integer enterpriseId);

     public int remove(Integer id, Integer enterpriseId);

     public boolean checkSign(Integer enterpriseId,String sign);
}
