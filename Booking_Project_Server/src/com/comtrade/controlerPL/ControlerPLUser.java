package com.comtrade.controlerPL;

import java.util.List;

import com.comtrade.constants.Type_Of_Operation;
import com.comtrade.domain.GeneralDomain;
import com.comtrade.domain.User;
import com.comtrade.genericClasses.GenericClass;
import com.comtrade.genericClasses.GenericList;
import com.comtrade.so.GeneralSystemOperation;
import com.comtrade.so.user.EnterUserAndAdditionalUser;
import com.comtrade.so.user.UserLogInSO;
import com.comtrade.transfer.TransferClass;

public class ControlerPLUser {
	private static ControlerPLUser instance;
	
	private ControlerPLUser() {
		
	}
	
	public static ControlerPLUser getInstance() {
		if(instance==null) {
			synchronized (ControlerPLUser.class) {
				if(instance==null) {
					instance=new ControlerPLUser();
				}
			}
		}
		return instance;
		
	}

	public TransferClass CheckTheOperation(TransferClass transferClass) {
		TransferClass transferClass2=new TransferClass();
		switch(transferClass.getType_Of_operation()) {
			case Type_Of_Operation.REGISTRATION_USER:
				GenericList<GeneralDomain>list=(GenericList<GeneralDomain>) transferClass.getClient_Object_Request();
				enterTheUser(list);
			case Type_Of_Operation.LOGIN_USER:
				User user=(User) transferClass.getClient_Object_Request();
				transferClass2.setServer_Object_Response(logInUser(user));	
		}
		return transferClass2;
		
	}

	private User logInUser(User user) {
		GenericList<User> list=new GenericList<User>();
		list.add(user);
		GeneralSystemOperation<GenericList<User>>generalSO=new UserLogInSO();
		generalSO.runSO(list);
		return list.get(1);
	}

	private void enterTheUser(GenericList<GeneralDomain> list) {
		GeneralSystemOperation<GenericList<GeneralDomain>> generalSO=new EnterUserAndAdditionalUser();
		generalSO.runSO(list);
	}

	
	
	
}
