package io.github.pleuvoir.sql.core.convert;

public interface ConverterRoute {

	ConverterService route(String driverClassName);
}
