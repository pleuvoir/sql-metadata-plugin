package io.github.pleuvoir.sql.support.metadata;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.sql.DataSource;

import io.github.pleuvoir.sql.bean.ColumnExtend;
import io.github.pleuvoir.sql.support.convert.TypeHandler;

public class DefaultResultSetMetaDataService implements ResultSetMetaDataService {

	private DataSource dataSource;

	@Override
	public List<ColumnExtend> query(String sql, TypeHandler typeHandler) throws SQLException {
		Connection connection = dataSource.getConnection();
		PreparedStatement ps = connection.prepareStatement(sql);
		ResultSetMetaData metaData = ps.getMetaData();
		int columnCount = metaData.getColumnCount();
		List<ColumnExtend> ColumnExtendList = new LinkedList<ColumnExtend>();
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
			ColumnExtendList.add(columnExtend);
		}
		ps.close();
		connection.close();
		return ColumnExtendList;
	}

	private String toCamelCase(String s) {
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
