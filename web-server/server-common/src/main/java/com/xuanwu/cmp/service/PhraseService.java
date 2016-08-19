package com.xuanwu.cmp.service;

import com.xuanwu.cmp.domain.entity.Phrase;
import com.xuanwu.cmp.utils.QueryParameters;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @Description PhraseService
 * @author <a href="mailto:jiangpeng@wxchina.com">Peng.Jiang</a>
 * @date 2016-08-10
 * @version 1.0.0
 */
public interface PhraseService {

    public Integer count(QueryParameters params);

    public Collection<Phrase> list(QueryParameters params);

    public Phrase get(Integer id,Integer enterpriseId);

    public Phrase save(final Phrase phrase);

    public int deleteById(Integer id,Integer enterpriseId);

    public int remove(Integer id, Integer enterpriseId);

    public void saveWithSignAndAuditMaterial(Phrase phrase);

    public List<Phrase> getIdentifys(Map params);

    Phrase findByEnterpriseApp(QueryParameters params);
}

