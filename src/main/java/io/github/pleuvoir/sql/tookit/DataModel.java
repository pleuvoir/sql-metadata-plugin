package io.github.pleuvoir.sql.tookit;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import lombok.Data;

@Data
public class DataModel implements DataModelHelper {

	private Map<String, Object> dataModel = new ConcurrentHashMap<>();

	public DataModel addData(String key, Object value) {
		dataModel.put(key, value);
		return this;
	}

}
