package com.xuanwu.cmp.domain.repo;

import com.xuanwu.cmp.db.EntityRepository;
import com.xuanwu.cmp.domain.entity.VoiceDisplayNum;

/**
 * @Description VoiceDisplayNumRepo.java
 * @author <a href="mailto:miaojiepu@wxchina.com">Jiepu.Miao</a>
 * @date 2016年8月16日
 * @version 1.0.0
 */
public interface VoiceDisplayNumRepo extends EntityRepository<VoiceDisplayNum> {

	/**
	 * @date 2016年8月15日
	 * 
	 * @todo
	 */
	VoiceDisplayNum getByNumber(String displayNum);


}
