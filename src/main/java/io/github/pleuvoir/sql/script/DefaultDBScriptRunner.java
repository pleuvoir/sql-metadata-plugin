package io.github.pleuvoir.sql.script;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import io.github.pleuvoir.sql.bean.MetaData;
import io.github.pleuvoir.sql.support.convert.SimpleTypeHandlerFactory;
import io.github.pleuvoir.sql.support.convert.TypeHandler;
import io.github.pleuvoir.sql.support.convert.TypeHandlerFactory;
import io.github.pleuvoir.sql.support.metadata.ResultSetMetaDataService;
import io.github.pleuvoir.sql.tookit.DataSourceConfigHolder;

public class DefaultDBScriptRunner implements DBScriptRunner {

	@Autowired
	private ResultSetMetaDataService resultSetMetaDataService;
	
	private TypeHandlerFactory typeHandlerFactory;

	@Override
	public MetaData excute(String sql, String driverClassName) {
		this.typeHandlerFactory = (this.typeHandlerFactory == null ? new SimpleTypeHandlerFactory() : this.typeHandlerFactory);
		TypeHandler typeHandler = typeHandlerFactory.route(driverClassName);
		Assert.notNull(typeHandler, "we can't find suitable typeHandler, please check driverClassName or typeHandlerFactory.");
		return resultSetMetaDataService.query(sql, typeHandler);
	}
	
	@Override
	public MetaData excute(String sql) {
		return excute(sql, DataSourceConfigHolder.get().getDriverClass());
	}

	@Override
	public MetaData excuteSingleTableQuery(String tableName) {
		return excuteSingleTableQuery(tableName, DataSourceConfigHolder.get().getDriverClass());
	}

	@Override
	public MetaData excuteSingleTableQuery(String tableName, String driverClassName) {
		this.typeHandlerFactory = (this.typeHandlerFactory == null ? new SimpleTypeHandlerFactory() : this.typeHandlerFactory);
		TypeHandler typeHandler = typeHandlerFactory.route(driverClassName);
		Assert.notNull(typeHandler, "we can't find suitable typeHandler, please check driverClassName or typeHandlerFactory.");
		return resultSetMetaDataService.querySingleTable(tableName, typeHandler);
	}
	
	@Override
	public void setTypeHandlerFactory(TypeHandlerFactory typeHandlerFactory) {
		this.typeHandlerFactory = typeHandlerFactory;
	}
}
