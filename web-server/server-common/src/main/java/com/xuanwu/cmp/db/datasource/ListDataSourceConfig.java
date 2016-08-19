package com.xuanwu.cmp.db.datasource;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

/**
 * @Description DataSourceConfig
 * @author <a href="mailto:jiji@javawind.com">XueFang.Xu</a>
 * @date 2016-08-12
 * @version 1.0.0
 */
@Configuration
public class ListDataSourceConfig {

	@Value("${mybatis.config-location}")
	private String mybatisConfigLocation;

	@Value("${spring.datasource_list.name}")
	private String name;
	@Value("${spring.datasource_list.maxActive}")
	private int maxActive;
	@Value("${spring.datasource_list.maxIdle}")
	private int maxIdle;
	@Value("${spring.datasource_list.minIdle}")
	private int minIdle;
	@Value("${spring.datasource_list.initialSize}")
	private int initialSize;
	@Value("${spring.datasource_list.validationQuery}")
	private String validationQuery;
	@Value("${spring.datasource_list.testWhileIdle}")
	private boolean testWhileIdle;
	@Value("${spring.datasource_list.testOnBorrow}")
	private boolean testOnBorrow;
	@Value("${spring.datasource_list.testOnReturn}")
	private boolean testOnReturn;

	@Bean(name = "listDataSource")
	@ConfigurationProperties(prefix = "spring.datasource_list")
	public DataSource listDataSource() {
		DataSource dataSource = DataSourceBuilder.create().build();
		org.apache.tomcat.jdbc.pool.DataSource ds = (org.apache.tomcat.jdbc.pool.DataSource) dataSource;
		ds.setName(name);
		ds.setMaxActive(maxActive);
		ds.setMaxIdle(maxIdle);
		ds.setMinIdle(minIdle);
		ds.setInitialSize(initialSize);
		ds.setValidationQuery(validationQuery);
		ds.setTestWhileIdle(testWhileIdle);
		ds.setTestOnBorrow(testOnBorrow);
		ds.setTestOnReturn(testOnReturn);
		return ds;
	}

	@Bean(name = "listTransactionManager")
	public DataSourceTransactionManager listTransactionManager(@Qualifier("listDataSource") DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}

	@Bean(name = "listSqlSessionFactory")
	public SqlSessionFactory listSqlSessionFactory(@Qualifier("listDataSource") DataSource dataSource)
			throws Exception {
		String path = mybatisConfigLocation.replace("classpath:", "/");
		ClassPathResource resource = new ClassPathResource(path);

		SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
		factory.setDataSource(dataSource);
		factory.setConfigLocation(resource);
		return factory.getObject();
	}

	@Bean(name = "listSqlSessionTemplate")
	public SqlSessionTemplate listSqlSessionTemplate(
			@Qualifier("listSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
		return new SqlSessionTemplate(sqlSessionFactory);
	}
}
