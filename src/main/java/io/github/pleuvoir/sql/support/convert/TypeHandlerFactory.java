package io.github.pleuvoir.sql.support.convert;

public interface TypeHandlerFactory {

	TypeHandler route(String driverClassName);
}
