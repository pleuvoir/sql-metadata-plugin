package io.github.pleuvoir.sql.support.metadata;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

import io.github.pleuvoir.sql.bean.MetaData;
import io.github.pleuvoir.sql.support.convert.TypeHandler;

public class DefaultResultSetMetaDataService extends AbstractResultSetMetaDataService {

	@Override
	public MetaData query(String sql, TypeHandler typeHandler) {
		return super.excuteSql(sql, typeHandler, null);
	}

	@Override
	public MetaData querySingleTable(String tableName, TypeHandler typeHandler) {

		return super.excuteSql("select * from ".concat(tableName), typeHandler, new EnhanceCallBack() {
			// 增加主键属性
			@Override
			public void doInConnection(Connection connection, MetaData metaData) throws SQLException {
				// 获取主键名 要将表名转为大写才能正确取出主键来
				DatabaseMetaData dbmd = connection.getMetaData();
				ResultSet rs = dbmd.getPrimaryKeys(null, null, tableName.toUpperCase());
				while (rs.next()) {
					metaData.setPrimaryKey(rs.getString(4));
				}
				if (rs != null) {
					rs.close();
				}
			}
		});
	}

}
