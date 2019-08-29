package com.comtrade.controlerBL;

import java.util.List;

import com.comtrade.cache.Cache;
import com.comtrade.constants.TransferClass_Message;
import com.comtrade.constants.Type_Of_Data;
import com.comtrade.domain.GeneralDomain;
import com.comtrade.domain.property.Property;
import com.comtrade.genericClasses.GenericList;
import com.comtrade.reservation.Reservation;
import com.comtrade.so.GeneralSystemOperation;
import com.comtrade.so.reservation.DeleteReservationSO;
import com.comtrade.so.reservation.EnterReservationSO;
import com.comtrade.threads.ClientThreadRequest;
import com.comtrade.transfer.TransferClass;

public class ControlerBLReservation {
	private static volatile ControlerBLReservation instance;
	
	private ControlerBLReservation() {
		
	}
	
	public static ControlerBLReservation getInstance() {
		if(instance==null) {
			synchronized (ControlerBLReservation.class) {
				if(instance==null) {
					instance=new ControlerBLReservation();
				}
			}
		}
		return instance;
		
	}

	public void CheckTheOperation(TransferClass transferClass, ClientThreadRequest clientThread) {
		TransferClass transferClass2=new TransferClass();
		GenericList<GeneralDomain>list=new GenericList<GeneralDomain>();
		switch(transferClass.getType_Of_operation()) {
		case ADD_NEW_REGISTRATION:
			list=(GenericList<GeneralDomain>) transferClass.getClient_Object_Request();
			EnterNewReservation(list,clientThread);
			break;
		case DELETE_RESERVATION:
			list=(GenericList<GeneralDomain>) transferClass.getClient_Object_Request();
			DeleteReservation(list,clientThread);
			break;	
			default:
				break;
				
		}
	}

	private void DeleteReservation(GenericList<GeneralDomain> list, ClientThreadRequest clientThread) {
		try {
			
			GeneralSystemOperation<GenericList<GeneralDomain>>generalSO=new DeleteReservationSO();
			generalSO.runSO(list);
			Controler_Thread.getInstance().sendToClientForDELETE(Type_Of_Data.RESERVATION, clientThread, TransferClass_Message.SUCCESSFUL_DELETE,list);
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void EnterNewReservation(GenericList<GeneralDomain> list, ClientThreadRequest clientThread) {
		try {
			
			GeneralSystemOperation<GenericList<GeneralDomain>>generalSO=new EnterReservationSO();
			generalSO.runSO(list);
			Controler_Thread.getInstance().sendToClientForADD(Type_Of_Data.RESERVATION, clientThread, TransferClass_Message.SUCCESSFUL_REGISTRATION,list);
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
