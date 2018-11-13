package io.github.pleuvoir.sql.core.metadata;

import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import io.github.pleuvoir.sql.bean.ColumnExtend;
import io.github.pleuvoir.sql.core.convert.ConverterService;

public interface ResultSetMetaDataService {

	List<ColumnExtend> query(String sql, ConverterService convertTypeService) throws SQLException;

	void setDataSource(DataSource dataSource);

}
