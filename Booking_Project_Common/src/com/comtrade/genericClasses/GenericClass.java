package com.comtrade.genericClasses;

import java.io.Serializable;

import com.comtrade.domain.GeneralDomain;

public class GenericClass<E extends GeneralDomain> implements Generic,Serializable{
	private  E generalDoamin;

	public GenericClass(E generalDoamin) {
		super();
		this.generalDoamin = generalDoamin;
	}

	public E getGeneralDoamin() {
		return generalDoamin;
	}

	public void setGeneralDoamin(E generalDoamin) {
		this.generalDoamin = generalDoamin;
	}
	
	
	
}
