package io.github.pleuvoir.sql.script;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import io.github.pleuvoir.sql.bean.ColumnExtend;
import io.github.pleuvoir.sql.bean.MetaData;
import io.github.pleuvoir.sql.support.convert.DbTypeEnum;
import io.github.pleuvoir.sql.support.convert.SimpleTypeHandlerFactory;
import io.github.pleuvoir.sql.support.convert.TypeHandler;
import io.github.pleuvoir.sql.support.convert.TypeHandlerFactory;
import io.github.pleuvoir.sql.support.metadata.ResultSetMetaDataService;

public class DefaultDBScriptRunner implements DBScriptRunner {

	@Autowired
	private ResultSetMetaDataService resultSetMetaDataService;
	
	private TypeHandlerFactory typeHandlerFactory;

	@Override
	public MetaData excute(String sql, DbTypeEnum dbTypeEnum) {
		return excute(sql, dbTypeEnum.getDriverClassName());
	}

	@Override
	public MetaData excute(String sql, String driverClassName) {
		
		this.typeHandlerFactory = (this.typeHandlerFactory == null ? new SimpleTypeHandlerFactory() : this.typeHandlerFactory);
		MetaData metaData = new MetaData();
		try {
			TypeHandler typeHandler = typeHandlerFactory.route(driverClassName);
			Assert.notNull(typeHandler, "we can't find suitable typeHandler, please check driverClassName or typeHandlerFactory.");
			List<ColumnExtend> columnExtendList = resultSetMetaDataService.query(sql, typeHandler);
			metaData.setColumnExtendList(columnExtendList);
		} catch (SQLException e) {
			e.printStackTrace(System.err);
		}
		return metaData;
	}
	
	@Override
	public void setTypeHandlerFactory(TypeHandlerFactory typeHandlerFactory) {
		this.typeHandlerFactory = typeHandlerFactory;
	}
}
