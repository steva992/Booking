package com.comtrade.controlerPL;

import java.sql.SQLException;

import com.comtrade.domain.GeneralDomain;
import com.comtrade.domain.User;
import com.comtrade.genericClasses.GenericList;
import com.comtrade.so.GeneralSystemOperation;
import com.comtrade.so.property.EntertPropertySO;
import com.comtrade.transfer.TransferClass;

public class ControlerPLProperty {
private static ControlerPLProperty instance;
	
	private ControlerPLProperty() {
		
	}
	
	public static ControlerPLProperty getInstance() {
		if(instance==null) {
			synchronized (ControlerPLProperty.class) {
				if(instance==null) {
					instance=new ControlerPLProperty();
				}
			}
		}
		return instance;
		
	}

	public TransferClass CheckTheOperation(TransferClass transferClass) throws Exception{
		TransferClass transferClass2=new TransferClass();
		switch(transferClass.getType_Of_operation()) {
			case REGISTRATION_PROPERTY:
				GenericList<GeneralDomain>list=(GenericList<GeneralDomain>) transferClass.getClient_Object_Request();
				transferClass2=enterTheProperty(list);
				break;
		}
		return transferClass2;
		
	}

	private TransferClass enterTheProperty(GenericList<GeneralDomain> list) throws Exception {
		TransferClass transferClass=new TransferClass();
		try {
		GenericList<GeneralDomain>list1=list;
		GeneralSystemOperation<GenericList<GeneralDomain>>generalSO=new EntertPropertySO();
		generalSO.runSO(list);
		} catch (Exception e) {
			transferClass.setMessage("!!! That Name of Property Allready Excist !!!");
			e.printStackTrace();
		}
		return transferClass;
	}
	
	

}
