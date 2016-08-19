package com.xuanwu.cmp.service;

import com.xuanwu.cmp.domain.entity.PhraseSample;

import java.util.List;

/**
 * @Description PhraseSampleService
 * @author <a href="mailto:jiangpeng@wxchina.com">Peng.Jiang</a>
 * @date 2016-08-10
 * @version 1.0.0
 */
public interface PhraseSampleService {

    public List<PhraseSample> findAll();

    public PhraseSample get(Integer id);

    public PhraseSample save(final PhraseSample phraseSample);

    public int remove(Integer id);

}

