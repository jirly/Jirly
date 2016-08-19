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
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

/**
 * @Description DataSourceConfig
 * @author <a href="mailto:jiji@javawind.com">XueFang.Xu</a>
 * @date 2016-08-12
 * @version 1.0.0
 */
@Configuration
public class GsmsDataSourceConfig {

	@Value("${mybatis.config-location}")
	private String mybatisConfigLocation;

	@Value("${spring.datasource_gsms.name}")
	private String name;
	@Value("${spring.datasource_gsms.maxActive}")
	private int maxActive;
	@Value("${spring.datasource_gsms.maxIdle}")
	private int maxIdle;
	@Value("${spring.datasource_gsms.minIdle}")
	private int minIdle;
	@Value("${spring.datasource_gsms.initialSize}")
	private int initialSize;
	@Value("${spring.datasource_gsms.validationQuery}")
	private String validationQuery;
	@Value("${spring.datasource_gsms.testWhileIdle}")
	private boolean testWhileIdle;
	@Value("${spring.datasource_gsms.testOnBorrow}")
	private boolean testOnBorrow;
	@Value("${spring.datasource_gsms.testOnReturn}")
	private boolean testOnReturn;

	@Primary
	@Bean(name = "gsmsDataSource")
	@ConfigurationProperties(prefix = "spring.datasource_gsms")
	public DataSource gsmsDataSource() {
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

	@Bean(name = "gsmsTransactionManager")
	public DataSourceTransactionManager gsmsTransactionManager(@Qualifier("gsmsDataSource") DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}

	@Bean(name = "gsmsSqlSessionFactory")
	public SqlSessionFactory gsmsSqlSessionFactory(@Qualifier("gsmsDataSource") DataSource dataSource)
			throws Exception {
		String path = mybatisConfigLocation.replace("classpath:", "/");
		ClassPathResource resource = new ClassPathResource(path);

		SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
		factory.setDataSource(dataSource);
		factory.setConfigLocation(resource);
		return factory.getObject();
	}

	@Bean(name = "gsmsSqlSessionTemplate")
	public SqlSessionTemplate gsmsSqlSessionTemplate(
			@Qualifier("gsmsSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
		return new SqlSessionTemplate(sqlSessionFactory);
	}
}
