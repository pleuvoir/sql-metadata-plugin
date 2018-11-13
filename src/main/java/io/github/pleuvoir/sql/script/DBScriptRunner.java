package io.github.pleuvoir.sql.script;

import io.github.pleuvoir.sql.bean.MetaData;
import io.github.pleuvoir.sql.support.convert.DbTypeEnum;
import io.github.pleuvoir.sql.support.convert.TypeHandlerFactory;

public interface DBScriptRunner {

	MetaData excute(String sql, DbTypeEnum dbTypeEnum);
	
	MetaData excute(String sql, String driverClassName);

	void setTypeHandlerFactory(TypeHandlerFactory typeHandlerFactory);
}
