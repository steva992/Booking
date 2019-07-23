package com.comtrade.controlerPL;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.comtrade.constants.TransferClass_Message;
import com.comtrade.constants.Type_Of_Operation;
import com.comtrade.domain.GeneralDomain;
import com.comtrade.domain.user.PaymentUserCard;
import com.comtrade.domain.user.User;
import com.comtrade.domain.user.User_Info;
import com.comtrade.genericClasses.GenericList;
import com.comtrade.so.GeneralSystemOperation;
import com.comtrade.so.user.AddNewCardSO;
import com.comtrade.so.user.ChangePasswordUserSO;
import com.comtrade.so.user.ChangePictureURLUserSO;
import com.comtrade.so.user.CheckUserSO;
import com.comtrade.so.user.EnterUserSO;
import com.comtrade.so.user.ReturnAdminsSO;
import com.comtrade.so.user.ReturnUserInfoSO;
import com.comtrade.so.user.UpdateUserSO;
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

	public TransferClass CheckTheOperation(TransferClass transferClass) throws Exception{		TransferClass transferClass2=new TransferClass();
		User user=new User();
		User_Info user_info=new User_Info();
		GenericList<GeneralDomain>list=new GenericList<GeneralDomain>();
		switch(transferClass.getType_Of_operation()) {
			case REGISTRATION_USER:
				list=(GenericList<GeneralDomain>) transferClass.getClient_Object_Request();
				transferClass2=enterTheUser(list);
				break;
			case LOGIN_USER:
				user=(User) transferClass.getClient_Object_Request();
				transferClass2=logInUser(user);
				break;
			case CHECK_USER:
				user=(User) transferClass.getClient_Object_Request();
				transferClass2=checkUser(user);
				break;
			case CHANGE_PASSWORD_USER:
				GenericList<User>listUser=(GenericList<User>) transferClass.getClient_Object_Request();
				transferClass2=changeserPassword(listUser);
				break;	
			case RETURN_USER_INFO:
				user=(User) transferClass.getClient_Object_Request();
				transferClass2=return_User_Info(user);
				break;	
			case CHANGE_PICTURE_URL_USER:
				user_info=(User_Info) transferClass.getClient_Object_Request();
				transferClass2=changePictureURL(user_info);
			case UPDATE_USER:
				user_info=(User_Info) transferClass.getClient_Object_Request();
				transferClass2=updateUser(user_info);
				break;
			case BACK_ALL_ADMINS:
				transferClass2=backAllAdmins();
				break;
			case ADD_PAYMENT_CARD:
				PaymentUserCard payment=(PaymentUserCard) transferClass.getClient_Object_Request();
				transferClass2=AddPaymentCard(payment);
				break;
			default:
				break;	
				
		}
		return transferClass2;
		
	}

	private TransferClass AddPaymentCard(PaymentUserCard payment) {
		TransferClass transferClass=new TransferClass();
		GeneralSystemOperation<PaymentUserCard>generalSO=new AddNewCardSO();
		try {
			generalSO.runSO(payment);
			transferClass.setMessage(TransferClass_Message.SUCCESSFUL_REGISTRATION.getValue());
			transferClass.setServer_Object_Response(payment);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return transferClass;
	}

	private TransferClass backAllAdmins() {
		TransferClass transferClass=new TransferClass();
		GenericList<GeneralDomain>listAdminsAndProperties=new GenericList<GeneralDomain>();
		GeneralSystemOperation<GenericList<GeneralDomain>>generalSO=new ReturnAdminsSO();
		try {
			generalSO.runSO(listAdminsAndProperties);
			transferClass.setServer_Object_Response(listAdminsAndProperties);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return transferClass;
	}

	private TransferClass updateUser(User_Info user_info) {
		TransferClass transferClass=new TransferClass();
		GeneralSystemOperation<User_Info>generalSO=new UpdateUserSO();
		try {
			generalSO.runSO(user_info);
			transferClass.setMessage(TransferClass_Message.SUCCESSFUL_CHANGE.getValue());
			transferClass.setServer_Object_Response(user_info);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return transferClass;
	}

	private TransferClass changePictureURL(User_Info user_info) {
		TransferClass transferClass=new TransferClass();
		GeneralSystemOperation<User_Info>genericSO=new ChangePictureURLUserSO();
		try {
			genericSO.runSO(user_info);
			transferClass.setServer_Object_Response(user_info);
			transferClass.setMessage(TransferClass_Message.SUCCESSFUL_CHANGE.getValue());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return transferClass;
		
		
	}

	private TransferClass return_User_Info(User user){
		TransferClass transferClass=new TransferClass();
		GenericList<GeneralDomain>userList=new GenericList<>();
		userList.add(user);
		GeneralSystemOperation<GenericList<GeneralDomain>>generalSO=new ReturnUserInfoSO();
		try {
			generalSO.runSO(userList);
			transferClass.setServer_Object_Response(userList.get(1));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return transferClass;
	}

	private TransferClass changeserPassword(GenericList<User> listUser) {
		TransferClass transferClass=new TransferClass();
		GenericList<User>listUser2=new GenericList<User>();
		listUser2.add(listUser.get(0));
		listUser2.add(listUser.get(1));
		try {
			GeneralSystemOperation<GenericList<User>>generalSO=new ChangePasswordUserSO();
			generalSO.runSO(listUser2);
			transferClass.setMessage(TransferClass_Message.SUCCESSFUL_CHANGE.getValue());
			transferClass.setServer_Object_Response(listUser2.get(2));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return transferClass;
	}

	private TransferClass checkUser(User user) {
		TransferClass transferClass=new TransferClass();
		GenericList<User>userList=new GenericList<User>();
		try {
			userList.add(user);
			GeneralSystemOperation<GenericList<User>>user1=new CheckUserSO();
			user1.runSO(userList);
			transferClass.setServer_Object_Response(userList.get(1));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return transferClass;
	}

	private TransferClass enterTheUser(GenericList<GeneralDomain> list){
		TransferClass transferClass=new TransferClass();
		try {
			GeneralSystemOperation<GenericList<GeneralDomain>>genericSO=new EnterUserSO();
			genericSO.runSO(list);
			transferClass.setMessage(TransferClass_Message.SUCCESSFUL_REGISTRATION.getValue());
		} catch (Exception e) {
			transferClass.setMessage(TransferClass_Message.EXCIST_USERNAME.getValue());
			e.printStackTrace();
		}
		return transferClass;
	}
	

	private TransferClass logInUser(User user) {
		TransferClass transferClass=new TransferClass();
		GenericList<User> list=new GenericList<User>();
		list.add(user);
		GeneralSystemOperation<GenericList<User>>generalSO=new UserLogInSO();
		try {
			generalSO.runSO(list);
			transferClass.setMessage(TransferClass_Message.SUCCESSFUL_LOGIN.getValue());
			transferClass.setServer_Object_Response(list.get(1));
		} catch (Exception e) {
			transferClass.setMessage(TransferClass_Message.EXCIST_USERNAME.getValue());
			e.printStackTrace();
		}
		return transferClass;
	}

	

	
	
	
}
