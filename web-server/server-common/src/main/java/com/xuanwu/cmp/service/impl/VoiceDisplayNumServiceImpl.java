package com.xuanwu.cmp.service.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xuanwu.cmp.domain.entity.VoiceDisplayNum;
import com.xuanwu.cmp.domain.repo.VoiceDisplayNumRepo;
import com.xuanwu.cmp.service.VoiceDisplayNumService;
import com.xuanwu.cmp.utils.QueryParameters;

/**
 * @Description VoiceDisplayNumServiceImpl.java
 * @author <a href="mailto:miaojiepu@wxchina.com">Jiepu.Miao</a>
 * @date 2016年8月16日
 * @version 1.0.0
 */
@Service
public class VoiceDisplayNumServiceImpl implements VoiceDisplayNumService {
	@Autowired
	private VoiceDisplayNumRepo voiceDisplayNumRepo;

	@Override
	public Collection<VoiceDisplayNum> list(QueryParameters params) {
		return voiceDisplayNumRepo.findResults(params);
	}

	@Override
	public int count(QueryParameters params) {
		return voiceDisplayNumRepo.findResultCount(params);
	}

	@Override
	public int deleteById(Integer id, Integer enterpriseId) {
		return voiceDisplayNumRepo.deleteById(id, enterpriseId);
	}

	@Override
	public VoiceDisplayNum getByNumber(String displayNum) {
		return voiceDisplayNumRepo.getByNumber(displayNum);
	}

	@Override
	public VoiceDisplayNum save(VoiceDisplayNum voiceDisplayNum) {
		return voiceDisplayNumRepo.save(voiceDisplayNum);
	}

}
