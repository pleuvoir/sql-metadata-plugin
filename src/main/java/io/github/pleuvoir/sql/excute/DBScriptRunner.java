package io.github.pleuvoir.sql.excute;

import io.github.pleuvoir.sql.bean.MetaData;
import io.github.pleuvoir.sql.core.convert.DbTypeEnum;

public interface DBScriptRunner {

	MetaData excute(String sql, DbTypeEnum dbTypeEnum);
}
