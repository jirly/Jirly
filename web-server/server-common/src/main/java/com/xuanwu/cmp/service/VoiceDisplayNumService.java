package com.xuanwu.cmp.service;

import java.util.Collection;

import com.xuanwu.cmp.domain.entity.VoiceDisplayNum;
import com.xuanwu.cmp.utils.QueryParameters;

/**
 * @Description VoiceDisplayNumService.java
 * @author <a href="mailto:miaojiepu@wxchina.com">Jiepu.Miao</a>
 * @date 2016年8月16日
 * @version 1.0.0
 */
public interface VoiceDisplayNumService {

	public Collection<VoiceDisplayNum> list(QueryParameters params);

	public int count(QueryParameters params);

	public int deleteById(Integer id, Integer enterpriseId);

	public VoiceDisplayNum getByNumber(String displayNum);

	public VoiceDisplayNum save(VoiceDisplayNum voiceDisplayNum);

}
