package com.sc.dubbo.svcb.config;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.alibaba.druid.pool.DruidDataSource;

@Configuration
@MapperScan(basePackages = "com.sc.dubbo.svcb.mapper.master", sqlSessionTemplateRef = "masterSqlSessionTemplate")
public class MasterDbDataSource {

	@Autowired
	private Environment env;

	// @Primary 确定此数据源为master
	@Bean(name = "masterDataSource")
	//@ConfigurationProperties(prefix = "spring.datasource.master-db")
	@Primary
	public DataSource masterDataSource() {
		MasterDataSourceProperties mp = getMasterDs();
		DruidDataSource datasource = new DruidDataSource();
		datasource.setUrl(mp.getUrl());
		datasource.setUsername(mp.getUsername());
		datasource.setPassword(mp.getPassword());
		datasource.setDriverClassName(mp.getDriverClassName());
		
	

		// configuration
		datasource.setInitialSize(mp.getInitialSize());
		datasource.setMinIdle(mp.getMinIdle());
		datasource.setMaxActive(mp.getMaxActive());
		datasource.setMaxWait(mp.getMaxWait());
		datasource.setTimeBetweenEvictionRunsMillis(mp.getTimeBetweenEvictionRunsMillis());
		datasource.setMinEvictableIdleTimeMillis(mp.getMinEvictableIdleTimeMillis());
		datasource.setValidationQuery(mp.getValidationQuery());
		datasource.setTestWhileIdle(mp.isTestWhileIdle());
		datasource.setTestOnBorrow(mp.isTestOnBorrow());
		datasource.setTestOnReturn(mp.isTestOnReturn());
		datasource.setPoolPreparedStatements(mp.isPoolPreparedStatements());
		datasource.setMaxPoolPreparedStatementPerConnectionSize(mp.getMaxPoolPreparedStatementPerConnectionSize());
		try {
			datasource.setFilters(mp.getFilters());
		} catch (SQLException e) {
			System.err.println("druid configuration initialization filter: " + e);
		}
		datasource.setConnectionProperties(mp.getConnectionProperties());
		return datasource;

		/**
		 * supported types:
		 * org.apache.tomcat.jdbc.pool.DataSource", "com.zaxxer.hikari.HikariDataSource", "org.apache.commons.dbcp.BasicDataSource", "org.apache.commons.dbcp2.BasicDataSource"

		 */
		// return DataSourceBuilder.create().build();
	}

	@Bean(name = "masterSqlSessionFactory")
	// @Primary
	public SqlSessionFactory masterSqlSessionFactory(@Qualifier("masterDataSource") DataSource dataSource)
			throws Exception {
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(dataSource);

		//n  加载全局的配置文件
		bean.setConfigLocation(new DefaultResourceLoader().getResource("classpath:mybatis-config.xml"));

		//n 可以注释掉了通过xml配置扩展sql的方式。 如果频繁使用多表连接查询
		//bean.setMapperLocations(
		//		new PathMatchingResourcePatternResolver().getResources("classpath:mapping/master/*.xml"));
		return bean.getObject();
	}

	// 配置事务管理器
	@Bean(name = "masterTransactionManager")
	// @Primary
	public DataSourceTransactionManager masterTransactionManager(@Qualifier("masterDataSource") DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}

	@Bean(name = "masterSqlSessionTemplate")
	// @Primary
	public SqlSessionTemplate masterSqlSessionTemplate(
			@Qualifier("masterSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
		return new SqlSessionTemplate(sqlSessionFactory);
	}
	
	
	
	@Bean
	//n 解决 spring.datasource.filters=stat,wall,log4j 无法正常注册进去
	@ConfigurationProperties(prefix = "spring.datasource.master-db")
    public MasterDataSourceProperties getMasterDs() {
        return new MasterDataSourceProperties();
    }

}


class MasterDataSourceProperties {
	private String url;
	private String username;
	private String password;
	private String driverClassName;
	private int initialSize;
	private int minIdle;
	private int maxActive;
	private int maxWait;
	private int timeBetweenEvictionRunsMillis;
	private int minEvictableIdleTimeMillis;
	private String validationQuery;
	private boolean testWhileIdle;
	private boolean testOnBorrow;
	private boolean testOnReturn;
	private boolean poolPreparedStatements;
	private int maxPoolPreparedStatementPerConnectionSize;
	private String filters;
	private String connectionProperties;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDriverClassName() {
		return driverClassName;
	}

	public void setDriverClassName(String driverClassName) {
		this.driverClassName = driverClassName;
	}

	public int getInitialSize() {
		return initialSize;
	}

	public void setInitialSize(int initialSize) {
		this.initialSize = initialSize;
	}

	public int getMinIdle() {
		return minIdle;
	}

	public void setMinIdle(int minIdle) {
		this.minIdle = minIdle;
	}

	public int getMaxActive() {
		return maxActive;
	}

	public void setMaxActive(int maxActive) {
		this.maxActive = maxActive;
	}

	public int getMaxWait() {
		return maxWait;
	}

	public void setMaxWait(int maxWait) {
		this.maxWait = maxWait;
	}

	public int getTimeBetweenEvictionRunsMillis() {
		return timeBetweenEvictionRunsMillis;
	}

	public void setTimeBetweenEvictionRunsMillis(int timeBetweenEvictionRunsMillis) {
		this.timeBetweenEvictionRunsMillis = timeBetweenEvictionRunsMillis;
	}

	public int getMinEvictableIdleTimeMillis() {
		return minEvictableIdleTimeMillis;
	}

	public void setMinEvictableIdleTimeMillis(int minEvictableIdleTimeMillis) {
		this.minEvictableIdleTimeMillis = minEvictableIdleTimeMillis;
	}

	public String getValidationQuery() {
		return validationQuery;
	}

	public void setValidationQuery(String validationQuery) {
		this.validationQuery = validationQuery;
	}

	public boolean isTestWhileIdle() {
		return testWhileIdle;
	}

	public void setTestWhileIdle(boolean testWhileIdle) {
		this.testWhileIdle = testWhileIdle;
	}

	public boolean isTestOnBorrow() {
		return testOnBorrow;
	}

	public void setTestOnBorrow(boolean testOnBorrow) {
		this.testOnBorrow = testOnBorrow;
	}

	public boolean isTestOnReturn() {
		return testOnReturn;
	}

	public void setTestOnReturn(boolean testOnReturn) {
		this.testOnReturn = testOnReturn;
	}

	public boolean isPoolPreparedStatements() {
		return poolPreparedStatements;
	}

	public void setPoolPreparedStatements(boolean poolPreparedStatements) {
		this.poolPreparedStatements = poolPreparedStatements;
	}

	public int getMaxPoolPreparedStatementPerConnectionSize() {
		return maxPoolPreparedStatementPerConnectionSize;
	}

	public void setMaxPoolPreparedStatementPerConnectionSize(int maxPoolPreparedStatementPerConnectionSize) {
		this.maxPoolPreparedStatementPerConnectionSize = maxPoolPreparedStatementPerConnectionSize;
	}

	public String getFilters() {
		return filters;
	}

	public void setFilters(String filters) {
		this.filters = filters;
	}

	public String getConnectionProperties() {
		return connectionProperties;
	}

	public void setConnectionProperties(String connectionProperties) {
		this.connectionProperties = connectionProperties;
	}

}