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
	MetaData metaData = dBScriptRunner.excute("select * from pay_order", DbTypeEnum.ORACLE);
	System.out.println(metaData.toJSON());
}
```

## Support

now we only support `ORACLE` and `MYSQL`.All supported database type can be found in class `DbTypeEnum`.

