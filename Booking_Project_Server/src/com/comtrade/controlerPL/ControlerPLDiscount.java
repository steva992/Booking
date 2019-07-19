package com.comtrade.controlerPL;

import com.comtrade.constants.TransferClass_Message;
import com.comtrade.domain.GeneralDomain;
import com.comtrade.domain.discount.Discount;
import com.comtrade.genericClasses.GenericList;
import com.comtrade.so.GeneralSystemOperation;
import com.comtrade.so.discount.DeleteDiscountSO;
import com.comtrade.so.discount.EnterDiscountSO;
import com.comtrade.transfer.TransferClass;

public class ControlerPLDiscount {
private static volatile ControlerPLDiscount instance;
	
	private ControlerPLDiscount() {
		
	}
	
	public static ControlerPLDiscount getInstance() {
		if(instance==null) {
			synchronized (ControlerPLDiscount.class) {
				if(instance==null) {
					instance=new ControlerPLDiscount();
				}
			}
		}
		return instance;
		
	}

	public TransferClass CheckTheOperation(TransferClass transferClass) {
		TransferClass transferClass2=new TransferClass();
		Discount discount=new Discount();
		switch (transferClass.getType_Of_operation()) {
		case REGISTRATION_DISCOUNT:
			discount=(Discount) transferClass.getClient_Object_Request();
			transferClass2=enterDiscount(discount);
			break;
		case DELETE_DISCOUNT:
			discount=(Discount) transferClass.getClient_Object_Request();
			transferClass2=deleteDiscount(discount);
			break;	

		default:
			break;
		}
		return transferClass2;
	}

	private TransferClass deleteDiscount(Discount discount) {
		TransferClass transferClass=new TransferClass();
		GeneralSystemOperation<Discount>generalSO=new DeleteDiscountSO();
		try {
			generalSO.runSO(discount);
			transferClass.setMessage(TransferClass_Message.SUCCESSFUL_DELETE.getValue());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return transferClass;
	}

	private TransferClass enterDiscount(Discount discount) {
		TransferClass transferClass=new TransferClass();
		GenericList<GeneralDomain>list=new GenericList<GeneralDomain>();
		GeneralSystemOperation<GenericList<GeneralDomain>>generalSO=new EnterDiscountSO();
		try {
			list.add(discount);
			generalSO.runSO(list);
			transferClass.setMessage(TransferClass_Message.SUCCESSFUL_REGISTRATION.getValue());
			transferClass.setServer_Object_Response(list.get(1));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return transferClass;
	}
	
	
}
