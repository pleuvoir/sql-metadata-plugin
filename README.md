
# sql-metadata-plugin

Execute SQL to return result-set metadata easily. It also provide template engine support for code-generator.

[![HitCount](http://hits.dwyl.io/pleuvoir/sql-metadata-plugin.svg)](http://hits.dwyl.io/pleuvoir/sql-metadata-plugin) 
[![GitHub issues](https://img.shields.io/github/issues/pleuvoir/sql-metadata-plugin.svg)](https://github.com/pleuvoir/sql-metadata-plugin/issues)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg?label=license)](https://github.com/pleuvoir/sql-metadata-plugin/blob/master/LICENSE)
[![Maven Central](https://img.shields.io/maven-central/v/io.github.pleuvoir/sql-metadata-plugin.svg?label=maven%20central)](https://oss.sonatype.org/#nexus-search;quick~sql-metadata-plugin)
[![Download](https://img.shields.io/badge/downloads-master-green.svg)](https://codeload.github.com/pleuvoir/sql-metadata-plugin/zip/master)

## Usage

### Import dependency

```xml
<dependency>
	<groupId>io.github.pleuvoir</groupId>
	<artifactId>sql-metadata-plugin</artifactId>
	<version>${latest.version}</version>
</dependency>
```

### Register in spring container

```xml
<bean class="io.github.pleuvoir.sql.MetaDataConfiguration">
	<!-- <property name="dataSource" ref="dataSource" /> -->  <!-- if you have dataSource already in spring, you can reference directly -->
	<property name="dataSourceConfig" ref="dataSourceConfig" /> <!-- if you use dataSourceConfig, Be careful of database driver -->
	<property name="ftlLocation" value="classpath:ftl" />
</bean>

<bean id="dataSourceConfig" class="io.github.pleuvoir.sql.bean.DataSourceConfig">
	<property name="driverClass" value="..." />
	<property name="url" value="..." />
	<property name="username" value="..." />
	<property name="password" value="..." />
</bean>
```

### Junit test

```java
@Autowired
private DBScriptRunner dBScriptRunner;

@Test
public void contextTest() throws FileNotFoundException, IOException, TemplateException {

	// 1. get metaData
	MetaData metaData = dBScriptRunner.excute("select * from pub_param", DbTypeEnum.ORACLE);

	String entityName = "pubParamPO";
	
	// 2. convert to dataModel
	DataModel dataModel = metaData.asDataModel().addData("entityName", entityName);

	System.out.println(dataModel.toJSON());
	
	// 3. write file ..
	dataModel.write("po.ftl", "D:\\" + entityName + ".java");
}
```

you can realize your TypeHandlerFactory to support more database type. look like this:

```java
dBScriptRunner.setTypeHandlerFactory(new MyTypeHandlerFactory());
dBScriptRunner.excute("select * from pub_param", "org.h2.Driver");
```

## Support

now we only support `ORACLE` and `MYSQL`. All supported database type can be found in class `DbTypeEnum`.

## LICENSE

The sql-metadata-plugin is released under version 2.0 of the [Apache License](http://www.apache.org/licenses/LICENSE-2.0).
