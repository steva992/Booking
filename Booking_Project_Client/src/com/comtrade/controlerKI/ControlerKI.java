package com.comtrade.controlerKI;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

import com.comtrade.communication.Communication;
import com.comtrade.constants.Type_Of_Data;
import com.comtrade.constants.Type_Of_Operation;
import com.comtrade.domain.GeneralDomain;
import com.comtrade.domain.User;
import com.comtrade.domain.User_Additional_Features;
import com.comtrade.genericClasses.GenericList;
import com.comtrade.transfer.TransferClass;

public class ControlerKI {
	private static ControlerKI instance;
	private TransferClass transferClass=new TransferClass();
	
	private ControlerKI() {
		
	}
	
	public static ControlerKI getInstance() {
		if(instance==null) {
			instance=new ControlerKI();
		}
		return instance;
		
	}

	public void enterTheUserAndAdditionalUser(GenericList<GeneralDomain> list) {
		transferClass.setType_Of_operation(Type_Of_Operation.REGISTRATION_USER);
		transferClass.setType_Of_Data(Type_Of_Data.USER);
		transferClass.setClient_Object_Request(list);
		Communication.getInstance().send(transferClass);
	}

	public TransferClass login(User user) throws ClassNotFoundException, IOException {
		transferClass.setType_Of_operation(Type_Of_Operation.LOGIN_USER);
		transferClass.setType_Of_Data(Type_Of_Data.USER);
		transferClass.setClient_Object_Request(user);
		return backDataToJFrame(transferClass);
	}

	private TransferClass backDataToJFrame(TransferClass transferClass) throws ClassNotFoundException, IOException {
		Communication.getInstance().send(transferClass);
		TransferClass transferClass2=Communication.getInstance().read();
		return transferClass2;
	}

}
