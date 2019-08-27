package com.comtrade.genericClasses;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.comtrade.domain.GeneralDomain;

public class GenericList<E extends GeneralDomain> implements Generic<GenericList<GeneralDomain>>,Serializable {
	
		private List<E>list=new ArrayList();
		
		public void add(E value) {
			list.add(value);
		}
		
		public E get(int index) {
			return list.get(index);
		}
		
		public void set(int index,E value) {
			list.set(index,value);
		}
		
		public int size() {
			return list.size();
		}
		
		public void delete(int index) {
			list.remove(index);
		}
		
		public void delete(GeneralDomain generalDomain) {
			list.remove(generalDomain);
		}
		
		
		
		public void addAll(GenericList<E> genericList) {
			for(int i=0;i<genericList.size();i++) {
				list.add(genericList.get(i));
			}
		}
}
