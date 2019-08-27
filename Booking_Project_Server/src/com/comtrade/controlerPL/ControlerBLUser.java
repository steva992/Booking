package com.comtrade.controlerPL;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.comtrade.cache.Cache;
import com.comtrade.constants.TransferClass_Message;
import com.comtrade.constants.Type_OF_Operation_TXT;
import com.comtrade.constants.Type_Of_Data;
import com.comtrade.constants.Type_Of_Operation;
import com.comtrade.constants.URLS;
import com.comtrade.domain.GeneralDomain;
import com.comtrade.domain.user.PaymentUserCard;
import com.comtrade.domain.user.User;
import com.comtrade.domain.user.User_Info;
import com.comtrade.genericClasses.GenericList;
import com.comtrade.so.GeneralSystemOperation;
import com.comtrade.so.user.AddPaymentCardSO;
import com.comtrade.so.user.ChangePasswordSO;
import com.comtrade.so.user.ChangePictureURLSO;
import com.comtrade.so.user.CheckOnlineUsersSO;
import com.comtrade.so.user.CheckUserSO;
import com.comtrade.so.user.DeactivatedUserSO;
import com.comtrade.so.user.EnterUserSO;
import com.comtrade.so.user.LogInSO;
import com.comtrade.so.user.RemoveOnlineUserSO;
import com.comtrade.so.user.UpdateuserSO;
import com.comtrade.threads.ClientThreadRequest;
import com.comtrade.transfer.TransferClass;
import com.mysql.cj.jdbc.result.CachedResultSetMetaData;

public class ControlerBLUser {
	private static ControlerBLUser instance;
	
	private ControlerBLUser() {
		
	}
	
	public static ControlerBLUser getInstance() {
		if(instance==null) {
			synchronized (ControlerBLUser.class) {
				if(instance==null) {
					instance=new ControlerBLUser();
				}
			}
		}
		return instance;
		
	}

	public void CheckTheOperation(TransferClass transferClass, ClientThreadRequest clientThread) throws Exception{	
		TransferClass transferClass2=new TransferClass();
		User user=new User();
		User_Info user_info=new User_Info();
		GenericList<GeneralDomain>list=new GenericList<GeneralDomain>();
		switch(transferClass.getType_Of_operation()) {
			case REGISTRATION_USER:
				list=(GenericList<GeneralDomain>) transferClass.getClient_Object_Request();
				enterTheUser(list,clientThread);
				break;
			case LOGIN_USER:
				user=(User) transferClass.getClient_Object_Request();
				logInUser(user,clientThread);
				break;
			case CHECK_USER:
				list=(GenericList<GeneralDomain>) transferClass.getClient_Object_Request();
				user=(User) list.get(0);
				checkUser(user,clientThread);
				break;
			case CHANGE_PASSWORD_USER:
				list=(GenericList<GeneralDomain>) transferClass.getClient_Object_Request();
				changeUserPassword(list,clientThread);
				break;
			case CHANGE_PICTURE_URL_USER:
				list=(GenericList<GeneralDomain>) transferClass.getClient_Object_Request();
				changePictureURL(list,clientThread);
				break;
			case UPDATE_USER:
				list=(GenericList<GeneralDomain>) transferClass.getClient_Object_Request();
				updateUser(list,clientThread);
				break;
			case ADD_PAYMENT_CARD:
				list=(GenericList<GeneralDomain>) transferClass.getClient_Object_Request();
				AddPaymentCard(list,clientThread);
				break;
			case CHECK_USER_ONLINE:
				 user=(User) transferClass.getClient_Object_Request();
				checkOnlineUser(user,clientThread);
				break;	
			case REMOVE_ONLINE_USER:
				 user=(User) transferClass.getClient_Object_Request();
				removeOnlineUser(user,clientThread);
				break;
			case DEACTIVATE_USER:
				 user=(User) transferClass.getClient_Object_Request();
				deactivateUser(user,clientThread);
				break;		
				
			default:
				break;	
				
		}
		
	}

	private void deactivateUser(User user, ClientThreadRequest clientThread) throws Exception {
		TransferClass transferClass=new TransferClass();
		GeneralSystemOperation<User>generalSO=new DeactivatedUserSO();
		generalSO.runSO(user);
		
		transferClass.setMessage(TransferClass_Message.USER_DEACTIVATED.getValue());
		transferClass.setType_Of_Data(Type_Of_Data.USER);
		transferClass.setType_Of_operation(Type_Of_Operation.DEACTIVATE_USER);
		
		clientThread.send(transferClass);
		
	}

	private void removeOnlineUser(User user, ClientThreadRequest clientThread) {
		TransferClass transferClass=new TransferClass();
		GeneralSystemOperation<User>generalSO=new RemoveOnlineUserSO();
		try {
			generalSO.runSO(user);
			
			GenericList<GeneralDomain>list=new GenericList<GeneralDomain>();
			list.add(user);
			Controler_Thread.getInstance().sendMESSAGE(Type_Of_Data.USER,clientThread,TransferClass_Message.USER_IS_OFFLINE,list);
			
			ControlerBLServer.getInstance().entrerMessageOnTextArea("\n"+"[ "+LocalDate.now()+" "+LocalTime.now()+" ] : "+ user.getUsername()+ " left server\n");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void checkOnlineUser(User user, ClientThreadRequest clientThread) {
			TransferClass transferClass=new TransferClass();
			User user1=user;
			GeneralSystemOperation<User>generalSO=new CheckOnlineUsersSO();
			try {
				generalSO.runSO(user1);
				
				transferClass.setServer_Object_Response(user1.getId());
				transferClass.setType_Of_operation(Type_Of_Operation.CHECK_USER_ONLINE);
				transferClass.setType_Of_Data(Type_Of_Data.USER);
				clientThread.send(transferClass);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}

	private void AddPaymentCard(GenericList<GeneralDomain> list, ClientThreadRequest clientThread) {
		TransferClass transferClass=new TransferClass();
		try {
			GeneralSystemOperation<GenericList<GeneralDomain>>generalSO=new AddPaymentCardSO();
			generalSO.runSO(list);
			
			transferClass.setServer_Object_Response(list);
			transferClass.setType_Of_operation(Type_Of_Operation.ADD_PAYMENT_CARD);
			transferClass.setMessage(TransferClass_Message.SUCCESSFUL_REGISTRATION.getValue());
			transferClass.setType_Of_Data(Type_Of_Data.USER);
			clientThread.send(transferClass);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	

	private void updateUser(GenericList<GeneralDomain> list, ClientThreadRequest clientThread) {
		TransferClass transferClass=new TransferClass();
		try {
			GeneralSystemOperation<GenericList<GeneralDomain>>generalSO=new UpdateuserSO();
			generalSO.runSO(list);
			
			transferClass.setServer_Object_Response(list);
			transferClass.setType_Of_operation(Type_Of_Operation.UPDATE);
			transferClass.setMessage(TransferClass_Message.SUCCESSFUL_CHANGE.getValue());
			transferClass.setType_Of_Data(Type_Of_Data.USER);
			clientThread.send(transferClass);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private void changePictureURL(GenericList<GeneralDomain> list, ClientThreadRequest clientThread) {
		TransferClass transferClass=new TransferClass();
		User_Info user_info=(User_Info) list.get(1);
		try {
			GeneralSystemOperation<GenericList<GeneralDomain>>generalSO=new ChangePictureURLSO();
			generalSO.runSO(list);
			
			transferClass.setServer_Object_Response(list);
			transferClass.setType_Of_Data(Type_Of_Data.USER);
			
			
			if(user_info.getPictureURL().equals(URLS.PROFILE_PICTURE_DEFAULT.getValue()+"/"+user_info.getGender()+".jpg")) {
				transferClass.setMessage(TransferClass_Message.SUCCESSFUL_DELETE.getValue());
			}else {
				transferClass.setMessage(TransferClass_Message.SUCCESSFUL_CHANGE.getValue());
			}
			
			transferClass.setType_Of_operation(Type_Of_Operation.CHANGE_PICTURE_URL_USER);
			clientThread.send(transferClass);
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}


	private void changeUserPassword(GenericList<GeneralDomain> list, ClientThreadRequest clientThread) {
		TransferClass transferClass=new TransferClass();
		try {
			GeneralSystemOperation<GenericList<GeneralDomain>>generalSO=new ChangePasswordSO();
			generalSO.runSO(list);
			
			if(((User) list.get(1)).getStatus().equals("true")) {
				
				transferClass.setMessage(TransferClass_Message.SUCCESSFUL_CHANGE.getValue());
				transferClass.setType_Of_Data(Type_Of_Data.USER);
				transferClass.setType_Of_operation(Type_Of_Operation.CHANGE_PASSWORD_USER);
				List<ClientThreadRequest> listThread=Controler_Thread.getInstance().getListActiveThread();
				clientThread.send(transferClass);
				
			}else {
				wrongUsernameOrPassword(transferClass,clientThread);
			}
			
		} catch (Exception e) {
			
			wrongUsernameOrPassword(transferClass,clientThread);
		}
	}
	
	

	private void wrongUsernameOrPassword(TransferClass transferClass, ClientThreadRequest clientThread) {
		transferClass.setMessage(TransferClass_Message.WRONG_USERNAME_OR_PASSWORD.getValue());
		transferClass.setType_Of_Data(Type_Of_Data.USER);
		transferClass.setType_Of_operation(Type_Of_Operation.CHANGE_PASSWORD_USER);
		clientThread.send(transferClass);
		
	}

	private void checkUser(User user, ClientThreadRequest clientThread) {
		TransferClass transferClass=new TransferClass();
		try {
			User user1=user;
			GeneralSystemOperation<User>generalSO=new CheckUserSO();
			generalSO.runSO(user);
			
			if(user1.getStatus().equals("excist")) {
				transferClass.setMessage(TransferClass_Message.EXCIST_USERNAME.getValue());
				transferClass.setType_Of_Data(Type_Of_Data.USER);
				transferClass.setType_Of_operation(Type_Of_Operation.LOGIN_USER);
				transferClass.setServer_Object_Response(user);
				clientThread.send(transferClass);
			}else {
				transferClass.setMessage(TransferClass_Message.SUCCESSFUL_FIRST_STEP.getValue());
				transferClass.setType_Of_Data(Type_Of_Data.USER);
				transferClass.setType_Of_operation(Type_Of_Operation.LOGIN_USER);
				transferClass.setServer_Object_Response(user);
				clientThread.send(transferClass);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void enterTheUser(GenericList<GeneralDomain> list, ClientThreadRequest clientThread){
		TransferClass transferClass=new TransferClass();
		try {
			GeneralSystemOperation<GenericList<GeneralDomain>>generalSO=new EnterUserSO();
			generalSO.runSO(list);
			
			transferClass.setServer_Object_Response(list);
			transferClass.setMessage(TransferClass_Message.SUCCESSFUL_REGISTRATION.getValue());
			transferClass.setType_Of_Data(Type_Of_Data.USER);
			transferClass.setType_Of_operation(Type_Of_Operation.ADD);
			
			List<ClientThreadRequest>list1=Controler_Thread.getInstance().getListAllThread();
			for(int i=0;i<list1.size();i++) {
				list1.get(i).send(transferClass);
			}	
		} catch (Exception e) {
			
			transferClass.setMessage(TransferClass_Message.EXCIST_USERNAME.getValue());
			clientThread.send(transferClass);
			
		}
	}
	

	private void logInUser(User user, ClientThreadRequest clientThread) {
		TransferClass transferClass=new TransferClass();
		try {
			
			User user1=user;
			GeneralSystemOperation<User>generalSO=new LogInSO();
			generalSO.runSO(user1);
			
			if(user1.getStatus().equals("admin") || user1.getStatus().equals("user") || user1.getStatus().equals("super_admin") || user1.getStatus().equals("deactivated")) {
				
				Controler_Thread.getInstance().addNewThread(clientThread);
				Controler_Thread.getInstance().addInMapActiveThread(user1.getUsername(), clientThread);
				transferClass.setType_Of_operation(Type_Of_Operation.LOGIN_USER);
				transferClass.setMessage(TransferClass_Message.SUCCESSFUL_LOGIN.getValue());
				transferClass.setType_Of_Data(Type_Of_Data.USER);
				transferClass.setServer_Object_Response(user);
				clientThread.send(transferClass);
				
				ControlerBLServer.getInstance().entrerMessageOnTextArea(("\n"+"[ "+LocalDate.now()+" "+LocalTime.now()+" ] : " + user1.getUsername()+ " is on server\n"));
				
			}else {
				setWrongUser(transferClass,clientThread);
			}
		} catch (Exception e) {
			setWrongUser(transferClass,clientThread);
		}
		
	}

	private void setWrongUser(TransferClass transferClass,ClientThreadRequest clientThread) {
		transferClass.setType_Of_operation(Type_Of_Operation.LOGIN_USER);
		transferClass.setMessage(TransferClass_Message.WRONG_USERNAME_OR_PASSWORD.getValue());
		transferClass.setType_Of_Data(Type_Of_Data.USER);
		transferClass.setServer_Object_Response(null);
		clientThread.send(transferClass);
		
	}
	
	

	

	
	
	
}
