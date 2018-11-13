
# sql-metadata-plugin

Execute SQL to return result set metadata easily.

[![HitCount](http://hits.dwyl.io/pleuvoir/sql-metadata-plugin.svg)](http://hits.dwyl.io/pleuvoir/sql-metadata-plugin) 
[![GitHub issues](https://img.shields.io/github/issues/pleuvoir/sql-metadata-plugin.svg)](https://github.com/pleuvoir/sql-metadata-plugin/issues)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg?label=license)](https://github.com/pleuvoir/sql-metadata-plugin/blob/master/LICENSE)
[![Maven Central](https://img.shields.io/maven-central/v/io.github.pleuvoir/sql-metadata-plugin.svg?label=maven%20central)](https://oss.sonatype.org/#nexus-search;quick~sql-metadata-plugin)
[![Download](https://img.shields.io/badge/downloads-master-green.svg)](https://codeload.github.com/pleuvoir/sql-metadata-plugin/zip/master)

## Usage

### import dependency

```xml
<dependency>
	<groupId>io.github.pleuvoir</groupId>
	<artifactId>sql-metadata-plugin</artifactId>
	<version>1.0.0</version>
</dependency>
```

### register in spring container

```xml
<bean class="io.github.pleuvoir.sql.MetaDataConfiguration">
	<property name="dataSource" ref="dataSource"/>
</bean>
```

### junit test

```java
@Autowired
private DBScriptRunner dBScriptRunner;

@Test
public void contextTest() {
	MetaData metaData = dBScriptRunner.excute("select * from pub_param", DbTypeEnum.ORACLE);
	System.out.println(metaData.toJSON());
}
```

you can realize your TypeHandlerFactory to support more database type. look like this:

```java
dBScriptRunner.setTypeHandlerFactory(new MyTypeHandlerFactory());
dBScriptRunner.excute("select * from pub_param", "org.h2.Driver");
```

## Support

now we only support `ORACLE` and `MYSQL`. All supported database type can be found in class `DbTypeEnum`.

