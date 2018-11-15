package io.github.pleuvoir.sql.bean;

import java.util.List;

import io.github.pleuvoir.sql.kit.DataModel;
import lombok.Data;

@Data
public class MetaData {

	private List<ColumnExtend> columnExtendList;

	public DataModel asDataModel() {
		return new DataModel(this);
	}
}
