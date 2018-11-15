package io.github.pleuvoir.sql;

import java.io.IOException;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

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

	@Bean
	public TemplateFactory templateFactory() throws IOException {
		TemplateFactory templateFactory = new TemplateFactory();
		templateFactory
				.setLocation(new ClassPathResource(StringUtils.hasText(this.ftlLocation) ? this.ftlLocation : "ftl"));
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
