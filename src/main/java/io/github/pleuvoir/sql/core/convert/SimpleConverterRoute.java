package io.github.pleuvoir.sql.core.convert;

public class SimpleConverterRoute implements ConverterRoute {

	public ConverterService route(String driverClassName) {
		switch (driverClassName) {
		case "oracle.jdbc.driver.OracleDriver": {
			return new ConverterService() {

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
			return new ConverterService() {

				@Override
				public String convert(String columnTypeName) {
					throw new RuntimeException("期待你的实现");
				}
			};
		}
		default:
			throw new RuntimeException("不支持的数据库类型");
		}
	}

}
