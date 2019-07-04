package com.comtrade.genericClasses;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.comtrade.domain.GeneralDomain;

public class GenericList<E extends GeneralDomain> implements Generic,Serializable {
		private List<E>list=new ArrayList();
		
		public void add(E value) {
			list.add(value);
		}
		
		public E get(int index) {
			return list.get(index);
		}
		
		public int size() {
			return list.size();
		}
}
