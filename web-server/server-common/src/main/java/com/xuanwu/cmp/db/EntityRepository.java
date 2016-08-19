/*
 * Copyright (c) 2015 by XuanWu Wireless Technology Co., Ltd. 
 *             All rights reserved                         
 */
package com.xuanwu.cmp.db;

import java.io.Serializable;
import java.util.List;

import com.xuanwu.cmp.domain.Entity;
import com.xuanwu.cmp.utils.QueryParameters;

/**
 * 仓储访问接口, 提供通用仓储方法
 * 
 * @author <a href="mailto:liushuaiying@139130.net">Shuaiying.Liu</a>
 * @Data 2015年5月27日
 * @Version 1.0.0
 */
public interface EntityRepository<T extends Entity> {

	// 添加一个实体
	public T save(T t);

	// 添加一个实体
	public T saveByPath(T t, String updatePath, String insertPath);

	// 批量添加实体
	@SuppressWarnings("unchecked")
	public int saveBatch(T... t);

	// 更新一个实体
	public int updateSpecify(T t);

	// 更新一个实体
	public int updateSpecifyByPath(T t, String path);

	// 移除一个实体
	public int delete(T t);

	// 移除一个实体
	public int deleteByPath(T t, String path);

	// 根据实体ID，删除实体
	public int deleteById(Serializable id, Integer enterpriseId);

	// 根据实体ID，删除实体
	public int deleteByIdWithPath(Serializable id, Integer enterpriseId, String path);

	// 根据实体ID，查找实体
	public T getById(Serializable id, Integer enterpriseId);

	// 根据实体ID，查找实体
	public T getByIdWithPath(Serializable id, Integer enterpriseId, String path);

	// 查询符合查询参数的实体结果集数量
	public int findResultCount(QueryParameters param);

	// 查询符合查询参数的实体结果集数量
	public int findResultCountByPath(QueryParameters param, String path);

	// 查询符合查询参数的实体结果集
	public List<T> findResults(QueryParameters param);

	// 查询符合查询参数的实体结果集
	public List<T> findResultsByPath(QueryParameters param, String path);
}
