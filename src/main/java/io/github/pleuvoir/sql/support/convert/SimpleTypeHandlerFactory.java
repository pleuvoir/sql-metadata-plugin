package io.github.pleuvoir.sql.support.convert;

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
					case "DECIMAL":
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
			default:
				throw new IllegalArgumentException(
						"unsupported database type, driverClassName[" + driverClassName + "]");
		}
	}

}
