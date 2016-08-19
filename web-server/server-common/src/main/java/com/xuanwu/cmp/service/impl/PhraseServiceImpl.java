package com.xuanwu.cmp.service.impl;

import com.xuanwu.cmp.domain.entity.Phrase;
import com.xuanwu.cmp.domain.entity.PhraseAuditMaterial;
import com.xuanwu.cmp.domain.entity.UserSign;
import com.xuanwu.cmp.domain.repo.PhraseRepo;
import com.xuanwu.cmp.service.PhraseAuditMaterialService;
import com.xuanwu.cmp.service.PhraseService;
import com.xuanwu.cmp.service.UserSignService;
import com.xuanwu.cmp.utils.QueryParameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Description PhraseServiceImpl
 * @author <a href="mailto:jiangpeng@wxchina.com">Peng.Jiang</a>
 * @date 2016-08-10
 * @version 1.0.0
 */
@Service
public class PhraseServiceImpl implements PhraseService {

	@Autowired
	private PhraseRepo phraseRepo;

	@Autowired
	private UserSignService userSignService;

	@Autowired
	private PhraseAuditMaterialService phraseAuditMaterialService;

	@Override
	public Integer count(QueryParameters params) {
		return phraseRepo.findResultCount(params);
	}

	@Override
	public Collection<Phrase> list(QueryParameters params) {
		return phraseRepo.findResults(params);
	}

	@Override
	public Phrase get(Integer id,Integer enterpriseId) {
		return phraseRepo.getById(id, enterpriseId);
	}

	@Override
	public Phrase save(Phrase phrase) {
		if (phrase.getId() == null) {// add
			phrase.setCreateTime(new Date());
			phrase.setUpdateTime(new Date());
		} else {
			phrase.setUpdateTime(new Date());
		}
		return phraseRepo.save(phrase);
	}

	@Override
	public int deleteById(Integer id,Integer enterpriseId) {
		return phraseRepo.deleteById(id, enterpriseId);
	}

	@Override
	public int remove(Integer id, Integer enterpriseId) {
		return phraseRepo.remove(id, enterpriseId);
	}

	@Override
	public void saveWithSignAndAuditMaterial(Phrase phrase) {
		// 保存新的签名信息
		if(phrase.getSignId() == null && phrase.getIsSMS() == Boolean.TRUE) {
			UserSign userSign = new UserSign();
			userSign.setSign(phrase.getSignContent());
		    userSign.setEnterpriseId(phrase.getEnterpriseId());
			userSign.setCertifyFile(phrase.getCertifyFile());
			userSign.setType(phrase.getSignType());
			userSign = userSignService.save(userSign);
			phrase.setSignId(userSign.getId());
		}

		// 保存模板
		phrase = save(phrase);

		// 保存模板审核资料

		if(phrase.getType() == Phrase.PhraseType.SMS_NOTIFICATION ||
			phrase.getType() == Phrase.PhraseType.VOICE_NOTIFICATION	){ // 通知模板不需要审核资料，删除
			phraseAuditMaterialService.deleteByPhrase(phrase.getId());
		}else if(phrase.getType() == Phrase.PhraseType.SMS_VERIFICATION_CODE ||
				 phrase.getType() == Phrase.PhraseType.VOICE_VERIFICATION_CODE) {
			PhraseAuditMaterial material = phraseAuditMaterialService.findByPhrase(phrase.getId());
			if (material == null) {
				material = new PhraseAuditMaterial();
				material.setPhraseId(phrase.getId());
			}
			material.setAppLogo(phrase.getAppLogo());
			material.setAppType(phrase.getAppType());
			material.setAppUrl(phrase.getAppUrl());
			material.setAppVerifyPage(phrase.getAppVerifyPage());
			phraseAuditMaterialService.save(material);
		}
	}

	@Override
	public List<Phrase> getIdentifys(Map params) {
		return phraseRepo.getIdentifys(params);
	}

	@Override
	public Phrase findByEnterpriseApp(QueryParameters params) {
		return phraseRepo.findByEnterpriseApp(params);
	}
}
