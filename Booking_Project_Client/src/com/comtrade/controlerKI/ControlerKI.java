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
import com.comtrade.domain.User_Info;
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
	
	private TransferClass backDataToJFrame(TransferClass transferClass) throws ClassNotFoundException, IOException {
		Communication.getInstance().send(transferClass);
		TransferClass transferClass2=Communication.getInstance().read();
		return transferClass2;
	}

	public TransferClass enterTheUserAndAdditionalUser(GenericList<GeneralDomain> list) throws ClassNotFoundException, IOException {
		transferClass.setType_Of_operation(Type_Of_Operation.REGISTRATION_USER);
		transferClass.setType_Of_Data(Type_Of_Data.USER);
		transferClass.setClient_Object_Request(list);
		return backDataToJFrame(transferClass);
	}

	public TransferClass login(User user) throws ClassNotFoundException, IOException {
		transferClass.setType_Of_operation(Type_Of_Operation.LOGIN_USER);
		transferClass.setType_Of_Data(Type_Of_Data.USER);
		transferClass.setClient_Object_Request(user);
		return backDataToJFrame(transferClass);
	}


	public TransferClass enterProperty(GenericList<GeneralDomain> list) throws ClassNotFoundException, IOException {
		transferClass.setType_Of_operation(Type_Of_Operation.REGISTRATION_PROPERTY);
		transferClass.setType_Of_Data(Type_Of_Data.PROPERTY);
		transferClass.setClient_Object_Request(list);
		return backDataToJFrame(transferClass);
	}


	public TransferClass checkIfUserExcist(GeneralDomain generalDomain) throws ClassNotFoundException, IOException {
		transferClass.setType_Of_operation(Type_Of_Operation.CHECK_USER);
		transferClass.setType_Of_Data(Type_Of_Data.USER);
		transferClass.setClient_Object_Request(generalDomain);
		return backDataToJFrame(transferClass);
	}

	public TransferClass changePassword(GenericList<User> listUser) throws ClassNotFoundException, IOException {
		transferClass.setType_Of_operation(Type_Of_Operation.CHANGE_PASSWORD_USER);
		transferClass.setType_Of_Data(Type_Of_Data.USER);
		transferClass.setClient_Object_Request(listUser);
		return backDataToJFrame(transferClass);
	}

	public TransferClass BackUserInfo_ForUser(User user) throws ClassNotFoundException, IOException {
		transferClass.setType_Of_operation(Type_Of_Operation.RETURN_USER_INFO);
		transferClass.setType_Of_Data(Type_Of_Data.USER);
		transferClass.setClient_Object_Request(user);
		return backDataToJFrame(transferClass);
	}
	

	
}
