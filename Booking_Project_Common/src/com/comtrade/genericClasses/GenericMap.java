package com.comtrade.genericClasses;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.comtrade.domain.GeneralDomain;
import com.comtrade.domain.property.Property;
import com.comtrade.domain.user.User;

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
		
		public void removeInList(String type,GeneralDomain generalDomain) {
			map.get(type).delete(generalDomain);
		}
		
		public void putAll(GenericMap<String, GenericList<GeneralDomain>>mapInput) {
			for(Map.Entry<String,GenericList<GeneralDomain>> entry : mapInput.entrySet()) {
				if(entry.getValue().get(0) instanceof User) {
					K key=(K) ((User) entry.getValue().get(0)).getUsername();
					V value= (V) entry.getValue();
					map.put(key,value);
				}else if(entry.getValue().get(0) instanceof Property) {
					K key=(K) ((Property) entry.getValue().get(0)).getName();
					V value= (V) entry.getValue();
					map.put(key,value);
				}
			}
		}

}
