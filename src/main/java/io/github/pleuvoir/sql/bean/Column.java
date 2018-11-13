package io.github.pleuvoir.sql.bean;

public class Column {

	/** 列名 */
	private String columnName;

	/** 数据库字段类型  */
	private String columnTypeName;

	/** 对应的java类型  */
	private String columnClassName;

	/** 在数据库中类型的最大字符个数  */
	private int columnDisplaySize;

	/** 类型的精确度(类型的长度)  */
	private int precision;

	/**小数点后的位数  */
	private int scale;

	/** 是否可为空 true:可为空  */
	private String isNullable;

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getColumnTypeName() {
		return columnTypeName;
	}

	public void setColumnTypeName(String columnTypeName) {
		this.columnTypeName = columnTypeName;
	}

	public String getColumnClassName() {
		return columnClassName;
	}

	public void setColumnClassName(String columnClassName) {
		this.columnClassName = columnClassName;
	}

	public int getColumnDisplaySize() {
		return columnDisplaySize;
	}

	public void setColumnDisplaySize(int columnDisplaySize) {
		this.columnDisplaySize = columnDisplaySize;
	}

	public int getPrecision() {
		return precision;
	}

	public void setPrecision(int precision) {
		this.precision = precision;
	}

	public int getScale() {
		return scale;
	}

	public void setScale(int scale) {
		this.scale = scale;
	}

	public String getIsNullable() {
		return isNullable;
	}

	public void setIsNullable(String isNullable) {
		this.isNullable = isNullable;
	}

	@Override
	public String toString() {
		return "Column [columnName=" + columnName + ", columnTypeName=" + columnTypeName + ", columnClassName="
				+ columnClassName + ", columnDisplaySize=" + columnDisplaySize + ", precision=" + precision + ", scale="
				+ scale + ", isNullable=" + isNullable + "]";
	}
	
}
