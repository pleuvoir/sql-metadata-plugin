package io.github.pleuvoir.sql.support.metadata;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.sql.DataSource;

import io.github.pleuvoir.sql.bean.ColumnExtend;
import io.github.pleuvoir.sql.bean.MetaData;
import io.github.pleuvoir.sql.support.convert.TypeHandler;

public abstract class AbstractResultSetMetaDataService implements ResultSetMetaDataService {

	protected DataSource dataSource;

	protected MetaData excuteSql(String sql, TypeHandler typeHandler, EnhanceCallBack callBack) {
		Connection connection = null;
		PreparedStatement ps = null;
		MetaData resuleMetaData = new MetaData();
		try {
			connection = dataSource.getConnection();
			ps = connection.prepareStatement(sql);
			ResultSetMetaData metaData = ps.getMetaData();
			resuleMetaData.setColumnExtendList(assemblyColumnExtends(metaData, typeHandler));
			if (callBack != null) {
				callBack.doInConnection(connection, resuleMetaData);
			}
		} catch (Exception e) {
			e.printStackTrace(System.err);
		} finally {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace(System.err);
			}
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace(System.err);
			}
		}
		return resuleMetaData;
	}

	interface EnhanceCallBack {
		void doInConnection(Connection connection, MetaData metaData) throws SQLException;
	}

	List<ColumnExtend> assemblyColumnExtends(ResultSetMetaData metaData, TypeHandler typeHandler) throws SQLException {
		int columnCount = metaData.getColumnCount();
		List<ColumnExtend> columnExtendList = new LinkedList<ColumnExtend>();
		for (int i = 1; i <= columnCount; i++) {
			ColumnExtend columnExtend = new ColumnExtend();
			columnExtend.setColumnName(metaData.getColumnName(i));
			columnExtend.setColumnClassName(metaData.getColumnClassName(i));
			columnExtend.setColumnTypeName(metaData.getColumnTypeName(i));
			columnExtend.setIsNullable(metaData.isNullable(i) == 1 ? "true" : "false");
			columnExtend.setPrecision(metaData.getPrecision(i));
			columnExtend.setScale(metaData.getPrecision(i));
			columnExtend.setColumnDisplaySize(metaData.getColumnDisplaySize(i));
			columnExtend.setConvertedType(typeHandler.convert(metaData.getColumnTypeName(i))); // 转换后的类型
			columnExtend.setField(toCamelCase(metaData.getColumnName(i))); // 字段名
			columnExtendList.add(columnExtend);
		}
		return columnExtendList;
	}

	protected String toCamelCase(String s) {
		final char SEPARATOR = '_';
		if (s == null) {
			return null;
		}
		s = s.toLowerCase();
		StringBuilder sb = new StringBuilder(s.length());
		boolean upperCase = false;
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c == SEPARATOR) {
				upperCase = true;
			} else if (upperCase) {
				sb.append(Character.toUpperCase(c));
				upperCase = false;
			} else {
				sb.append(c);
			}
		}
		return sb.toString();
	}

	@Override
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
}
