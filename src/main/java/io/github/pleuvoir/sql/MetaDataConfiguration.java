package io.github.pleuvoir.sql;

import java.io.IOException;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.util.Assert;

import io.github.pleuvoir.sql.kit.ApplicationContextUtil;
import io.github.pleuvoir.sql.kit.TemplateFactory;
import io.github.pleuvoir.sql.script.DBScriptRunner;
import io.github.pleuvoir.sql.script.DefaultDBScriptRunner;
import io.github.pleuvoir.sql.support.metadata.DefaultResultSetMetaDataService;
import io.github.pleuvoir.sql.support.metadata.ResultSetMetaDataService;
import lombok.Setter;

public class MetaDataConfiguration {

	@Setter
	private DataSource dataSource;
	@Setter
	private String ftlLocation;

	@Bean
	public ResultSetMetaDataService resultSetMetaDataService() {
		Assert.notNull(dataSource, "dataSource must be non-null.");
		DefaultResultSetMetaDataService resultSetMetaDataService = new DefaultResultSetMetaDataService();
		resultSetMetaDataService.setDataSource(this.dataSource);
		return resultSetMetaDataService;
	}

	@Lazy
	@Bean
	public TemplateFactory templateFactory() throws IOException {
		Assert.notNull(ftlLocation, "ftlLocation must be non-null.");
		TemplateFactory templateFactory = new TemplateFactory();
		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		Resource resource = resolver.getResource(ftlLocation);
		templateFactory.setLocation(resource);
		return templateFactory;
	}

	@Bean
	public DBScriptRunner defaultDBScriptRunner() {
		return new DefaultDBScriptRunner();
	}

	@Bean
	public ApplicationContextUtil applicationContextUtil() {
		return new ApplicationContextUtil();
	}
}
