package io.github.pleuvoir.sql.bean;

import lombok.Data;

@Data
public class Column {

	/** 列名 */
	private String columnName;

	/** 数据库字段类型 */
	private String columnTypeName;

	/** 对应的java类型 */
	private String columnClassName;

	/** 在数据库中类型的最大字符个数 */
	private int columnDisplaySize;

	/** 类型的精确度(类型的长度) */
	private int precision;

	/** 小数点后的位数 */
	private int scale;

	/** 是否可为空 true:可为空 */
	private String isNullable;

}
