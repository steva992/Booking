package com.comtrade.genericClasses;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.comtrade.domain.GeneralDomain;

public class GenericMap<K extends String,V extends GenericList<GeneralDomain>> implements Serializable,Generic<GenericMap<String, GenericList<GeneralDomain>>>{
		private Map<K,V>map=new HashMap<>();
		
		public void put(K key,V value) {
			map.put(key, value);
		}
		
		public GenericList<GeneralDomain> get(K key) {
			return map.get(key);
		}
		
		public Collection<Entry<K,V>> entrySet() {
			return map.entrySet();
		}
		
		public void remove(K key) {
			map.remove(key);
		}
}
