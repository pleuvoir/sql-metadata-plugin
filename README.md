# sql-metadata-plugin
Execute SQL to return result set metadata easily.

## Usage

```xml
<bean class="io.github.pleuvoir.sql.MetaDataConfiguration">
	<property name="dataSource" ref="dataSource"/>
</bean>
```

```java
@Autowired
private DBScriptRunner dBScriptRunner;

@Test
public void contextTest() {
	MetaData metaData = dBScriptRunner.excute("select * from pub_param", DbTypeEnum.ORACLE);
	System.out.println(metaData.toJSON());
}
```

you can realize your typeHandlerFactory to support more database type. look like this:

```java
dBScriptRunner.setTypeHandlerFactory(new MyTypeHandlerFactory());
dBScriptRunner.excute("select * from pub_param", "org.h2.Driver");
```

## Support

now we only support `ORACLE` and `MYSQL`. All supported database type can be found in class `DbTypeEnum`.

