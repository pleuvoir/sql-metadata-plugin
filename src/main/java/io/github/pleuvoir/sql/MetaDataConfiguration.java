package io.github.pleuvoir.sql;

import java.io.IOException;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.util.Assert;

import com.alibaba.druid.pool.DruidDataSource;

import io.github.pleuvoir.sql.bean.DataSourceConfig;
import io.github.pleuvoir.sql.script.DBScriptRunner;
import io.github.pleuvoir.sql.script.DefaultDBScriptRunner;
import io.github.pleuvoir.sql.support.metadata.DefaultResultSetMetaDataService;
import io.github.pleuvoir.sql.support.metadata.ResultSetMetaDataService;
import io.github.pleuvoir.sql.tookit.ApplicationContextUtil;
import io.github.pleuvoir.sql.tookit.DataSourceConfigHolder;
import io.github.pleuvoir.sql.tookit.TemplateFactory;
import lombok.Setter;

@Import(ApplicationContextUtil.class)
public class MetaDataConfiguration {
	
	private static Logger logger = LoggerFactory.getLogger(MetaDataConfiguration.class);
	
	@Setter
	private DataSourceConfig dataSourceConfig;
	@Setter
	private String ftlLocation;
	
	@Bean
	public ResultSetMetaDataService resultSetMetaDataService() throws ClassNotFoundException {
		logger.info("[*] MetaDataConfiguration resultSetMetaDataService start setup ... v1.0.2");
		Assert.notNull(dataSourceConfig, "dataSourceConfig must be non-null.");
		DataSourceConfigHolder.set(dataSourceConfig);
		Assert.hasLength(dataSourceConfig.getDriverClass(), "dataSourceConfig driverClass must be non-null.");
		Assert.hasLength(dataSourceConfig.getUrl(), "dataSourceConfig url must be non-null.");
		Assert.hasLength(dataSourceConfig.getUsername(), "dataSourceConfig username must be non-null.");
		Assert.hasLength(dataSourceConfig.getPassword(), "dataSourceConfig password must be non-null.");
		DefaultResultSetMetaDataService resultSetMetaDataService = new DefaultResultSetMetaDataService();
		resultSetMetaDataService.setDataSource(buildDataSource(dataSourceConfig));
		return resultSetMetaDataService;
	}

	private DataSource buildDataSource(DataSourceConfig dataSourceConfig) {
		DruidDataSource ds = new DruidDataSource();
		ds.setDriverClassName(dataSourceConfig.getDriverClass());
		ds.setUrl(dataSourceConfig.getUrl());
		ds.setUsername(dataSourceConfig.getUsername());
		ds.setPassword(dataSourceConfig.getPassword());
		ds.setInitialSize(dataSourceConfig.getInitialSize());
		ds.setMaxActive(dataSourceConfig.getMaxActive());
		ds.setMinIdle(dataSourceConfig.getMinIdle());
		ds.setMaxWait(dataSourceConfig.getMaxWait());
		ds.setTimeBetweenEvictionRunsMillis(dataSourceConfig.getTimeBetweenEvictionRunsMillis());
		ds.setMinEvictableIdleTimeMillis(dataSourceConfig.getMinEvictableIdleTimeMillis());
		ds.setValidationQuery(dataSourceConfig.getValidationQuery());
		ds.setTestOnBorrow(false);
		ds.setTestOnReturn(false);
		ds.setTestWhileIdle(true);
		return ds;
	}
	
	@Lazy
	@Bean
	public TemplateFactory templateFactory() throws IOException {
		Assert.hasLength(ftlLocation, "ftlLocation must be non-null.");
		TemplateFactory templateFactory = new TemplateFactory();
		templateFactory.setLocation(new PathMatchingResourcePatternResolver().getResource(ftlLocation));
		return templateFactory;
	}

	@Bean
	public DBScriptRunner defaultDBScriptRunner() {
		return new DefaultDBScriptRunner();
	}
	
}
