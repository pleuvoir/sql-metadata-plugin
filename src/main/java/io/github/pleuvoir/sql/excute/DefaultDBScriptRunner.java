package io.github.pleuvoir.sql.excute;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import io.github.pleuvoir.sql.bean.ColumnExtend;
import io.github.pleuvoir.sql.bean.MetaData;
import io.github.pleuvoir.sql.core.convert.ConverterRoute;
import io.github.pleuvoir.sql.core.convert.DbTypeEnum;
import io.github.pleuvoir.sql.core.metadata.ResultSetMetaDataService;

public class DefaultDBScriptRunner implements DBScriptRunner {

	@Autowired
	private ResultSetMetaDataService resultSetMetaDataService;
	@Autowired
	private ConverterRoute converterRoute;

	@Override
	public MetaData excute(String sql, DbTypeEnum dbTypeEnum) {
		MetaData metaData = new MetaData();
		try {
			List<ColumnExtend> columnExtendList = resultSetMetaDataService.query(sql, converterRoute.route(dbTypeEnum.getDriverClassName()));
			metaData.setColumnExtendList(columnExtendList);
		} catch (SQLException e) {
			e.printStackTrace(System.err);
		}
		return metaData;
	}
}
