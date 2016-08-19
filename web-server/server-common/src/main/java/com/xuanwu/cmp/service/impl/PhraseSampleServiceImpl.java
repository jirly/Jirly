package com.xuanwu.cmp.service.impl;

import com.xuanwu.cmp.domain.entity.PhraseSample;
import com.xuanwu.cmp.domain.repo.PhraseSampleRepo;
import com.xuanwu.cmp.service.PhraseSampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Description PhraseSampleServiceImpl
 * @author <a href="mailto:jiangpeng@wxchina.com">Peng.Jiang</a>
 * @date 2016-08-10
 * @version 1.0.0
 */
@Service
public class PhraseSampleServiceImpl implements PhraseSampleService {

	@Autowired
	private PhraseSampleRepo phraseSampleRepo;


	@Override
	public List<PhraseSample> findAll() {
		return phraseSampleRepo.findAll();
	}

	@Override
	public PhraseSample get(Integer id) {
		return phraseSampleRepo.getById(id, 0);
	}

	@Override
	public PhraseSample save(PhraseSample phrase) {
		if (phrase.getId() == null) {// add
			phrase.setCreateTime(new Date());
			phrase.setUpdateTime(new Date());
		} else {
			phrase.setUpdateTime(new Date());
		}
		return phraseSampleRepo.save(phrase);
	}

	@Override
	public int remove(Integer id) {
		return phraseSampleRepo.remove(id);
	}

}
