/*   
* Copyright (c) 2016/8/12 by XuanWu Wireless Technology Co., Ltd 
*             All rights reserved  
*/
package com.xuanwu.cmp.service;

import com.xuanwu.cmp.domain.entity.PhraseAuditMaterial;

/**
 * 模板验证资料 PhraseAuditMaterialService
 * @author <a href="mailto:jiangpeng@wxchina.com">Peng.Jiang</a>
 * @version 1.0.0
 * @date 2016/8/12
 */
public interface PhraseAuditMaterialService {

    public PhraseAuditMaterial save(final PhraseAuditMaterial phraseAuditMaterial);

    public PhraseAuditMaterial findByPhrase(int phraseId);

    public void deleteByPhrase(int phraseId);
}
