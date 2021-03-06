package com.xuanwu.cmp.db;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.xuanwu.cmp.domain.Entity;

public abstract class ListMybatisEntityRepository<T extends Entity> extends MybatisEntityRepository<T> {

	@Autowired
	@Qualifier("listSqlSessionFactory")
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}

}
