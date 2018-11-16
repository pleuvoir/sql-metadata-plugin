package io.github.pleuvoir.sql.bean;

import lombok.Data;

@Data
public class DataSourceConfig {

	private String driverClass;

	private String url;

	private String username;

	private String password;

	private int initialSize 						= 1;

	private int maxActive 							= 3;
	
	private int minIdle 							= 1;

	private long maxWait 							= 60000;

	private long timeBetweenEvictionRunsMillis		= 60000;

	private long minEvictableIdleTimeMillis 		= 25200000;

	private String validationQuery 					= "select 1 from dual";

}
