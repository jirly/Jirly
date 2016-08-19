package com.xuanwu.cmp.domain.repo;

import com.xuanwu.cmp.db.EntityRepository;
import com.xuanwu.cmp.domain.entity.Phrase;
import com.xuanwu.cmp.utils.QueryParameters;

import java.util.List;
import java.util.Map;

/**
 * @Description PhraseRepo
 * @author <a href="mailto:jiangpeng@wxchina.com">Peng.Jiang</a>
 * @date 2016-08-10
 * @version 1.0.0
 */
public interface PhraseRepo extends EntityRepository<Phrase> {

    public int remove(Integer id, Integer enterpriseId);

    public List<Phrase> getIdentifys(Map params);

    Phrase findByEnterpriseApp(QueryParameters params);
}
