package io.github.pleuvoir.sql.tookit;

import org.springframework.core.NamedThreadLocal;

import io.github.pleuvoir.sql.bean.DataSourceConfig;

public class DataSourceConfigHolder {

	public static ThreadLocal<DataSourceConfig> LOCAL = new NamedThreadLocal<>("datasource-holder");

	public static DataSourceConfig get() {
		return LOCAL.get();
	}

	public static void set(DataSourceConfig dataSourceConfig) {
		LOCAL.set(dataSourceConfig);
	}

	public static void reset() {
		LOCAL.remove();
	}
}
