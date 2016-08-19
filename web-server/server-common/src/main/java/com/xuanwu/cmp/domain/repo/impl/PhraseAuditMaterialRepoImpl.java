/*   
* Copyright (c) 2016/8/12 by XuanWu Wireless Technology Co., Ltd 
*             All rights reserved  
*/
package com.xuanwu.cmp.domain.repo.impl;


import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.xuanwu.cmp.db.GsmsMybatisEntityRepository;
import com.xuanwu.cmp.domain.entity.PhraseAuditMaterial;
import com.xuanwu.cmp.domain.repo.PhraseAuditMaterialRepo;

/**
 * 模板验证资料 PhraseAuditMaterialRepoImpl
 *
 * @author <a href="mailto:jiangpeng@wxchina.com">Peng.Jiang</a>
 * @version 1.0.0
 * @date 2016/8/12
 */
@Repository
public class PhraseAuditMaterialRepoImpl extends GsmsMybatisEntityRepository<PhraseAuditMaterial> implements PhraseAuditMaterialRepo {

    private Logger logger = LoggerFactory.getLogger(PhraseAuditMaterialRepoImpl.class);

    @Override
    protected String namesapceForSqlId() {
        return "com.xuanwu.cmp.mapper.PhraseAuditMaterialMapper";
    }

    @Override
    public PhraseAuditMaterial findByPhrase(int phraseId) {
        PhraseAuditMaterial material = null;
        try (SqlSession session = sqlSessionFactory.openSession()) {
            material = session.selectOne(fullSqlId("getByPhrase"), phraseId);
        } catch (Exception e) {
            logger.error("find PhraseAuditMaterial By Phrase Id: ", e);
        }
        return material;
    }

    @Override
    public void deleteByPhrase(int phraseId) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            session.selectOne(fullSqlId("deleteByPhrase"), phraseId);
        } catch (Exception e) {
            logger.error("delete PhraseAuditMaterial By Phrase Id: ", e);
        }
    }
}
