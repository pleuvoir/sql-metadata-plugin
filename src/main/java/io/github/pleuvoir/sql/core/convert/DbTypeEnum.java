package io.github.pleuvoir.sql.core.convert;

public enum DbTypeEnum {

	ORACLE("oracle.jdbc.driver.OracleDriver"), 
	MYSQL("com.mysql.jdbc.Driver");

	private String driverClassName;

	private DbTypeEnum(String driverClassName) {
		this.driverClassName = driverClassName;
	}

	public String getDriverClassName() {
		return driverClassName;
	}
}
