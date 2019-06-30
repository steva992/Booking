package com.comtrade.genericClasses;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.comtrade.domain.GeneralDomain;

public class GenericMap<K extends String,V extends GeneralDomain> implements Generic,Serializable{
		private Map<K,V>map=new HashMap<>();
		
		public void put(K key,V value) {
			map.put(key, value);
		}
		
		public void get(K key) {
			map.get(key);
		}
}
