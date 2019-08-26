package com.comtrade.so.property;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.comtrade.cache.Cache;
import com.comtrade.domain.GeneralDomain;
import com.comtrade.genericClasses.GenericList;
import com.comtrade.genericClasses.GenericMap;
import com.comtrade.so.GeneralSystemOperation;

public class GetMapSO extends GeneralSystemOperation<GenericMap<String, GenericList<GeneralDomain>>> {

	@Override
	protected void runConcreteSO(GenericMap<String, GenericList<GeneralDomain>> object) throws SQLException, Exception {
		GenericMap<String, GenericList<GeneralDomain>>map=object;
		map.putAll(Cache.getInstance().getMap());
	}

}
