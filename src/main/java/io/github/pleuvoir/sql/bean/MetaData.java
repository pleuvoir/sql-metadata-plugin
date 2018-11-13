package io.github.pleuvoir.sql.bean;

import java.util.List;

import com.alibaba.fastjson.JSON;

public class MetaData {

	private List<ColumnExtend> columnExtendList;

	public MetaData() {
		super();
	}

	public MetaData(List<ColumnExtend> columnExtendList) {
		this.columnExtendList = columnExtendList;
	}

	public List<ColumnExtend> getColumnExtendList() {
		return columnExtendList;
	}

	public void setColumnExtendList(List<ColumnExtend> columnExtendList) {
		this.columnExtendList = columnExtendList;
	}

	public String toJSON() {
		return JSON.toJSONString(this);
	}
}
