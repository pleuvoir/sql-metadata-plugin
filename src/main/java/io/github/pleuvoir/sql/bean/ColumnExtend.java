package io.github.pleuvoir.sql.bean;

import com.alibaba.fastjson.annotation.JSONField;

public class ColumnExtend extends Column {

	// 转换后为Java后的类型
	@JSONField(serialize = true)
	private String convertedType;

	// 驼峰格式属性名
	private String field;

	public String getConvertedType() {
		return convertedType;
	}

	public void setConvertedType(String convertedType) {
		this.convertedType = convertedType;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

}
