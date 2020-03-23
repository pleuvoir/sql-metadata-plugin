package io.github.pleuvoir.sql.support.convert;

import org.apache.commons.lang3.StringUtils;

public class SimpleTypeHandlerFactory implements TypeHandlerFactory {

	@Override
	public TypeHandler route(String driverClassName) {
		switch (driverClassName) {
		case "oracle.jdbc.OracleDriver": {
			return new TypeHandler() {

				@Override
				public String convert(String columnTypeName) {
					String type = "";
					switch (columnTypeName) {
					case "VARCHAR2":
						type = "String";
						break;
					case "TIMESTAMP":
						type = "LocalDateTime";
						break;
					case "DATE":
						type = "LocalDateTime";
						break;
					case "NUMBER":
						type = "BigDecimal";
						break;
					case "BLOB":
						type = "byte[]";
						break;
					default:
						type = "String";
						assert false;
					}
					return type;
				}

			};
		}
		case "com.mysql.jdbc.Driver": {
			return new TypeHandler() {

				@Override
				public String convert(String columnTypeName) {
					String type = "";
					if (StringUtils.contains(columnTypeName, "BIGINT")) {
						type = "Long";
					} else if (StringUtils.contains(columnTypeName, "INT")) {
						type = "Integer";
					} else if (StringUtils.contains(columnTypeName, "VARCHAR")) {
						type = "String";
					} else if (StringUtils.contains(columnTypeName, "DECIMAL")) {
						type = "BigDecimal";
					} else if (StringUtils.contains(columnTypeName, "DATETIME")) {
						type = "Date";
					} else {
						type = "String";
					}
					return type;
				}
			};
		}
		default:
			throw new IllegalArgumentException("unsupported database type, driverClassName[" + driverClassName + "]");
		}
	}

}
