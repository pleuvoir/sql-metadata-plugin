package io.github.pleuvoir.sql;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.util.Assert;

import io.github.pleuvoir.sql.script.DBScriptRunner;
import io.github.pleuvoir.sql.script.DefaultDBScriptRunner;
import io.github.pleuvoir.sql.support.metadata.DefaultResultSetMetaDataService;
import io.github.pleuvoir.sql.support.metadata.ResultSetMetaDataService;

public class MetaDataConfiguration {

	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Bean(name = "resultSetMetaDataService")
	public ResultSetMetaDataService resultSetMetaDataService() {
		Assert.notNull(dataSource, "dataSource must be non-null.");
		DefaultResultSetMetaDataService resultSetMetaDataService = new DefaultResultSetMetaDataService();
		resultSetMetaDataService.setDataSource(this.dataSource);
		return resultSetMetaDataService;
	}

	@Bean(name = "defaultDBScriptRunner")
	public DBScriptRunner defaultDBScriptRunner() {
		return new DefaultDBScriptRunner();
	}
}
