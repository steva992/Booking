package com.comtrade.controlerPL;

import java.util.List;

import com.comtrade.cache.Cache;
import com.comtrade.constants.TransferClass_Message;
import com.comtrade.constants.Type_Of_Data;
import com.comtrade.domain.GeneralDomain;
import com.comtrade.domain.property.Property;
import com.comtrade.genericClasses.GenericList;
import com.comtrade.rating.Client_Rating;
import com.comtrade.reservation.Reservation;
import com.comtrade.so.GeneralSystemOperation;
import com.comtrade.so.rating.EnterRatingSO;
import com.comtrade.threads.ClientThreadRequest;
import com.comtrade.transfer.TransferClass;

public class ControlerBLRating {
private static volatile ControlerBLRating instance;
	
	private ControlerBLRating() {
		
	}
	
	public static ControlerBLRating getInstance() {
		if(instance==null) {
			synchronized (ControlerBLRating.class) {
				if(instance==null) {
					instance=new ControlerBLRating();
				}
			}
		}
		return instance;
		
	}

	public void CheckTheOperation(TransferClass transferClass, ClientThreadRequest clientThread) {
		TransferClass transferClass2=new TransferClass();
		GenericList<GeneralDomain>list=new GenericList<GeneralDomain>();
		switch(transferClass.getType_Of_operation()) {
		case ADD_NEW_RATING:
			list=(GenericList<GeneralDomain>) transferClass.getClient_Object_Request();
			EnterNewRating(list,clientThread);
			break;
			default:
				break;
		}
	}

	private void EnterNewRating(GenericList<GeneralDomain> list, ClientThreadRequest clientThread) {
		try {
			
			GeneralSystemOperation<GenericList<GeneralDomain>>generalSO=new EnterRatingSO();
			generalSO.runSO(list);
			Controler_Thread.getInstance().sendToClientForADD(Type_Of_Data.RATING, clientThread, TransferClass_Message.SUCCESSFUL_RATING,list);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
