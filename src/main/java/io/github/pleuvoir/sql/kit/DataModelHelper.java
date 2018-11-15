package io.github.pleuvoir.sql.kit;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;

import org.apache.commons.io.IOUtils;

import com.alibaba.fastjson.JSON;

import freemarker.template.TemplateException;

public interface DataModelHelper {

	default String render(String ftlFileName) throws TemplateException, IOException {
		return ApplicationContextUtil.getBean(TemplateFactory.class).processToString(ftlFileName, this);
	};

	default public String toJSON() {
		return JSON.toJSONString(this);
	}

	default void write(String ftlFileName, String filePath)
			throws FileNotFoundException, IOException, TemplateException {
		IOUtils.write(render(ftlFileName), new FileOutputStream(new File(filePath)), Charset.forName("UTF-8"));
	}
}
