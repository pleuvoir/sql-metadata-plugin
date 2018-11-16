package io.github.pleuvoir.sql.bean;

import java.util.List;

import io.github.pleuvoir.sql.tookit.DataModel;
import lombok.Data;

@Data
public class MetaData {

	private List<ColumnExtend> columnExtendList;

	public DataModel asDataModel() {
		DataModel dataModel = new DataModel();
		dataModel.addData("metaData", this);
		return dataModel;
	}

}
