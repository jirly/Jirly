package com.xuanwu.cmp.domain.repo;

import com.xuanwu.cmp.db.EntityRepository;
import com.xuanwu.cmp.domain.entity.PhraseSample;

import java.util.List;

/**
 * @Description PhraseSampleRepo
 * @author <a href="mailto:jiangpeng@wxchina.com">Peng.Jiang</a>
 * @date 2016-08-10
 * @version 1.0.0
 */
public interface PhraseSampleRepo extends EntityRepository<PhraseSample> {

    public List<PhraseSample> findAll();

    public int remove(Integer id);

}
