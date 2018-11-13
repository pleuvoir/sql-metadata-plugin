package io.github.pleuvoir.sql.support.metadata;

import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import io.github.pleuvoir.sql.bean.ColumnExtend;
import io.github.pleuvoir.sql.support.convert.TypeHandler;

public interface ResultSetMetaDataService {

	List<ColumnExtend> query(String sql, TypeHandler typeHandler) throws SQLException;

	void setDataSource(DataSource dataSource);

}
