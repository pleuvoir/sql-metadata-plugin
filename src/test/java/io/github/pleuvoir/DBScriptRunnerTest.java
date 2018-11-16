package io.github.pleuvoir;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import freemarker.template.TemplateException;
import io.github.pleuvoir.sql.bean.MetaData;
import io.github.pleuvoir.sql.script.DBScriptRunner;
import io.github.pleuvoir.sql.support.convert.DbTypeEnum;
import io.github.pleuvoir.sql.tookit.DataModel;

public class DBScriptRunnerTest extends BaseTest {

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

}
