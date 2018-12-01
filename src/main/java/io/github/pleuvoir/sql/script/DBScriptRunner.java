package io.github.pleuvoir.sql.script;

import io.github.pleuvoir.sql.bean.MetaData;
import io.github.pleuvoir.sql.support.convert.TypeHandlerFactory;

public interface DBScriptRunner {

	MetaData excute(String sql);

	MetaData excute(String sql, String driverClassName);

	MetaData excuteSingleTableQuery(String tableName);

	MetaData excuteSingleTableQuery(String tableName, String driverClassName);

	void setTypeHandlerFactory(TypeHandlerFactory typeHandlerFactory);
}
