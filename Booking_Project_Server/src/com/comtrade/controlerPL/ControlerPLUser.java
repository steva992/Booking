package com.comtrade.controlerPL;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.comtrade.constants.Type_Of_Operation;
import com.comtrade.domain.GeneralDomain;
import com.comtrade.domain.User;
import com.comtrade.domain.User_Info;
import com.comtrade.genericClasses.GenericList;
import com.comtrade.so.GeneralSystemOperation;
import com.comtrade.so.user.CheckUserSO;
import com.comtrade.so.user.EnterUserSO;
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

	public TransferClass CheckTheOperation(TransferClass transferClass) throws SQLException{
		TransferClass transferClass2=new TransferClass();
		User user=new User();
		switch(transferClass.getType_Of_operation()) {
			case REGISTRATION_USER:
				GenericList<GeneralDomain>list=(GenericList<GeneralDomain>) transferClass.getClient_Object_Request();
				transferClass2=enterTheUser(list);
				break;
			case LOGIN_USER:
				user=(User) transferClass.getClient_Object_Request();
				transferClass2.setServer_Object_Response(logInUser(user));
				break;
			case CHECK_USER:
				user=(User) transferClass.getClient_Object_Request();
				transferClass2.setServer_Object_Response(checkUser(user));
				break;
			default:
				break;	
				
		}
		return transferClass2;
		
	}

	private User checkUser(User user) {
		TransferClass transferClass=new TransferClass();
		GenericList<User>userList=new GenericList<User>();
		try {
			userList.add(user);
			GeneralSystemOperation<GenericList<User>>user1=new CheckUserSO();
			user1.runSO(userList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userList.get(1);
	}

	private TransferClass enterTheUser(GenericList<GeneralDomain> list){
		TransferClass transferClass=new TransferClass();
		try {
			GeneralSystemOperation<GenericList<GeneralDomain>>genericSO=new EnterUserSO();
			genericSO.runSO(list);
			transferClass.setMessage("!!!Successful registration!!!");
		} catch (Exception e) {
			transferClass.setMessage("!!!That Username allready excist!!!");
			e.printStackTrace();
		}
		return transferClass;
	}
	

	private User logInUser(User user) {
		GenericList<User> list=new GenericList<User>();
		list.add(user);
		GeneralSystemOperation<GenericList<User>>generalSO=new UserLogInSO();
		try {
			generalSO.runSO(list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list.get(1);
	}

	

	
	
	
}
