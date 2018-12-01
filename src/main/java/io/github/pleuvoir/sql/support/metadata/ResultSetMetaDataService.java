package io.github.pleuvoir.sql.support.metadata;

import javax.sql.DataSource;

import io.github.pleuvoir.sql.bean.MetaData;
import io.github.pleuvoir.sql.support.convert.TypeHandler;

public interface ResultSetMetaDataService {

	MetaData query(String sql, TypeHandler typeHandler);

	MetaData querySingleTable(String tableName, TypeHandler typeHandler);

	void setDataSource(DataSource dataSource);

}
