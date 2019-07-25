package com.comtrade.controlerPL;

import com.comtrade.constants.TransferClass_Message;
import com.comtrade.domain.GeneralDomain;
import com.comtrade.genericClasses.GenericList;
import com.comtrade.reservation.Reservation;
import com.comtrade.so.GeneralSystemOperation;
import com.comtrade.so.reservation.AddNewReservationSO;
import com.comtrade.transfer.TransferClass;

public class ControlerPLReservation {
private static volatile ControlerPLReservation instance;
	
	private ControlerPLReservation() {
		
	}
	
	public static ControlerPLReservation getInstance() {
		if(instance==null) {
			synchronized (ControlerPLReservation.class) {
				if(instance==null) {
					instance=new ControlerPLReservation();
				}
			}
		}
		return instance;
		
	}

	public TransferClass CheckTheOperation(TransferClass transferClass) {
		TransferClass transferClass2=new TransferClass();
		switch(transferClass.getType_Of_operation()) {
		case ADD_NEW_REGISTRATION:
			Reservation reservation=(Reservation) transferClass.getClient_Object_Request();
			transferClass2=EnterNewReservation(reservation);
		}
		return transferClass2;
	}

	private TransferClass EnterNewReservation(Reservation reservation) {
		TransferClass transferClass=new TransferClass();
		GenericList<GeneralDomain>list=new GenericList<GeneralDomain>();
		list.add(reservation);
		GeneralSystemOperation<GenericList<GeneralDomain>>generalSO=new AddNewReservationSO();
		try {
			generalSO.runSO(list);
			transferClass.setServer_Object_Response(list.get(1));
			transferClass.setMessage(TransferClass_Message.SUCCESSFUL_REGISTRATION.getValue());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return transferClass;
	}
	
	
}
