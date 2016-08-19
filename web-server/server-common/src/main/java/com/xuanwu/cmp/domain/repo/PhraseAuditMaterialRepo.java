package com.xuanwu.cmp.domain.repo;

import com.xuanwu.cmp.db.EntityRepository;
import com.xuanwu.cmp.domain.entity.PhraseAuditMaterial;

/**
 * 模板验证资料 PhraseAuditMaterialRepo
 * @author <a href="mailto:jiangpeng@wxchina.com">Peng.Jiang</a>
 * @version 1.0.0
 * @date 2016/8/12
 */
public interface PhraseAuditMaterialRepo extends EntityRepository<PhraseAuditMaterial> {

    public PhraseAuditMaterial findByPhrase(int phraseId);

    public void deleteByPhrase(int phraseId);
}
