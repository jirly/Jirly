package com.xuanwu.cmp.service;

import com.xuanwu.cmp.domain.entity.UserSign;

import java.util.List;

/**
 * UserSignService 企业签名
 * @author <a href="mailto:jiangpeng@wxchina.com">Peng.Jiang</a>
 * @date 2016-08-10
 * @version 1.0.0
 */
public interface UserSignService {


    public List<UserSign> findByEnterprise(int enterpriseId);

    public UserSign get(Integer id, Integer enterpriseId);

    public UserSign save(final UserSign phrase);

    public int remove(Integer id, Integer enterpriseId);

    public boolean checkSign(Integer enterpriseId,String sign);

}

