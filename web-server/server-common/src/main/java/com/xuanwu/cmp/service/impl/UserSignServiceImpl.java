package com.xuanwu.cmp.service.impl;

import com.xuanwu.cmp.domain.entity.UserSign;
import com.xuanwu.cmp.domain.repo.UserSignRepo;
import com.xuanwu.cmp.service.UserSignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * PhraseServiceImpl 企业签名
 * @author <a href="mailto:jiangpeng@wxchina.com">Peng.Jiang</a>
 * @date 2016-08-10
 * @version 1.0.0
 */
@Service
public class UserSignServiceImpl implements UserSignService {

	@Autowired
	private UserSignRepo userSignRepo;

	@Override
	public List<UserSign> findByEnterprise(int enterpriseId) {
		return userSignRepo.findByEnterprise(enterpriseId);
	}

	public UserSign get(Integer id, Integer enterpriseId) {
		return userSignRepo.getById(id, enterpriseId);
	}

	public UserSign save(final  UserSign userSign) {
		if(userSign.getId() == null) {
			userSign.setCreateTime(new Date());
			userSign.setIsRemove(Boolean.FALSE);
			userSign.setState(UserSign.SignState.CHECKING);
		}
		return userSignRepo.save(userSign);
	}


	public int remove(Integer id , Integer enterpriseId) {
		return userSignRepo.remove(id, enterpriseId);
	}

	@Override
	public boolean checkSign(Integer enterpriseId, String sign) {
		return userSignRepo.checkSign(enterpriseId, sign);
	}
}
