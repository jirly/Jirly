/*   
* Copyright (c) 2016/8/12 by XuanWu Wireless Technology Co., Ltd 
*             All rights reserved  
*/
package com.xuanwu.cmp.service.impl;

import com.xuanwu.cmp.domain.entity.PhraseAuditMaterial;
import com.xuanwu.cmp.domain.repo.PhraseAuditMaterialRepo;
import com.xuanwu.cmp.service.PhraseAuditMaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 模板验证资料 PhraseAuditMaterialServiceImpl
 * @author <a href="mailto:jiangpeng@wxchina.com">Peng.Jiang</a>
 * @version 1.0.0
 * @date 2016/8/12
 */
@Service
public class PhraseAuditMaterialServiceImpl implements PhraseAuditMaterialService {

    @Autowired
    private PhraseAuditMaterialRepo phraseAuditMaterialRepo;

    @Override
    public PhraseAuditMaterial save(PhraseAuditMaterial phraseAuditMaterial) {
        return phraseAuditMaterialRepo.save(phraseAuditMaterial);
    }

    @Override
    public PhraseAuditMaterial findByPhrase(int phraseId) {
        return phraseAuditMaterialRepo.findByPhrase(phraseId);
    }

    @Override
    public void deleteByPhrase(int phraseId) {
        phraseAuditMaterialRepo.deleteByPhrase(phraseId);
    }
}
