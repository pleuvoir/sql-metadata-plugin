package io.github.pleuvoir.sql.kit;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import io.github.pleuvoir.sql.bean.MetaData;
import lombok.Data;

@Data
public class DataModel implements DataModelHelper {

	private MetaData metaData;

	private Map<String, Object> params = new ConcurrentHashMap<>();

	public DataModel(MetaData metaData) {
		this.metaData = metaData;
	}

	public DataModel addParam(String key, Object value) {
		params.put(key, value);
		return this;
	}

}
