package com.comtrade.controlerPL;

import java.util.List;

import com.comtrade.cache.Cache;
import com.comtrade.constants.TransferClass_Message;
import com.comtrade.constants.Type_Of_Data;
import com.comtrade.constants.Type_Of_Operation;
import com.comtrade.domain.GeneralDomain;
import com.comtrade.domain.discount.Discount;
import com.comtrade.domain.property.Property;
import com.comtrade.genericClasses.GenericList;
import com.comtrade.so.GeneralSystemOperation;
import com.comtrade.so.discount.DeleteDiscountSO;
import com.comtrade.so.discount.InsertDiscountSO;
import com.comtrade.threads.ClientThreadRequest;
import com.comtrade.transfer.TransferClass;

public class ControlerBLDiscount {
	private static volatile ControlerBLDiscount instance;
	
	private ControlerBLDiscount() {
		
	}
	
	public static ControlerBLDiscount getInstance() {
		if(instance==null) {
			synchronized (ControlerBLDiscount.class) {
				if(instance==null) {
					instance=new ControlerBLDiscount();
				}
			}
		}
		return instance;
		
	}

	public void CheckTheOperation(TransferClass transferClass, ClientThreadRequest clientThread) {
		TransferClass transferClass2=new TransferClass();
		Discount discount=new Discount();
		GenericList<GeneralDomain>list=new GenericList<GeneralDomain>();
		switch (transferClass.getType_Of_operation()) {
		case REGISTRATION_DISCOUNT:
			list=(GenericList<GeneralDomain>) transferClass.getClient_Object_Request();
			enterDiscount(list,clientThread);
			break;
		case DELETE_DISCOUNT:
			list=(GenericList<GeneralDomain>) transferClass.getClient_Object_Request();
			deleteDiscount(list,clientThread);
			break;	

		default:
			break;
		}
	}

	private void deleteDiscount(GenericList<GeneralDomain> list, ClientThreadRequest clientThread) {
		try {
			GeneralSystemOperation<GenericList<GeneralDomain>>generalSO=new DeleteDiscountSO();
			generalSO.runSO(list);
			Controler_Thread.getInstance().sendToClientForDELETE(Type_Of_Data.DISCOUNT,clientThread, TransferClass_Message.SUCCESSFUL_DELETE,list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

	private void enterDiscount(GenericList<GeneralDomain> list, ClientThreadRequest clientThread) {
		try {
			GeneralSystemOperation<GenericList<GeneralDomain>>generalSO=new InsertDiscountSO();
			generalSO.runSO(list);
			Controler_Thread.getInstance().sendToClientForADD(Type_Of_Data.DISCOUNT,clientThread,TransferClass_Message.SUCCESSFUL_REGISTRATION,list);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	
}
