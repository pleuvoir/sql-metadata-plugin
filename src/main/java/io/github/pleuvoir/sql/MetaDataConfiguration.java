package io.github.pleuvoir.sql;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;

import io.github.pleuvoir.sql.core.convert.ConverterRoute;
import io.github.pleuvoir.sql.core.convert.SimpleConverterRoute;
import io.github.pleuvoir.sql.core.metadata.DefaultResultSetMetaDataService;
import io.github.pleuvoir.sql.core.metadata.ResultSetMetaDataService;
import io.github.pleuvoir.sql.excute.DBScriptRunner;
import io.github.pleuvoir.sql.excute.DefaultDBScriptRunner;

public class MetaDataConfiguration {

	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Bean(name = "resultSetMetaDataService")
	public ResultSetMetaDataService resultSetMetaDataService() {
		DefaultResultSetMetaDataService resultSetMetaDataService = new DefaultResultSetMetaDataService();
		resultSetMetaDataService.setDataSource(this.dataSource);
		return resultSetMetaDataService;
	}

	@Bean(name = "simpleConverterRoute")
	public ConverterRoute converterRoute() {
		return new SimpleConverterRoute();
	}

	@Bean(name = "defaultDBScriptRunner")
	public DBScriptRunner defaultDBScriptRunner() {
		return new DefaultDBScriptRunner();
	}
}
