package com.comtrade.controlerClient;

import java.util.concurrent.atomic.AtomicInteger;

import com.comtrade.domain.GeneralDomain;
import com.comtrade.domain.discount.Discount;
import com.comtrade.domain.user.User;
import com.comtrade.domain.user.User_Info;
import com.comtrade.genericClasses.GenericList;
import com.comtrade.panel.admin.Admin_Panel;
import com.comtrade.panel.superAdmin.Super_Admin_Panel;
import com.comtrade.panel.user.User_Panel;
import com.comtrade.proxy.ProxyLogin;
import com.comtrade.threads.ClientThreadResponse;
import com.comtrade.transfer.TransferClass;
import com.comtrade.view.frame.UserInfo;

public class ControlerUser {
	private static volatile ControlerUser instance;
	private volatile User user;
	private volatile User_Info user_info;
	private volatile String message;
	private volatile int userOnlineNumber;
	private User_Panel userPanel;
	private Admin_Panel adminPanel;
	private volatile int number=0;
	

	public synchronized int getNumber() {
		return number;
	}

	public synchronized void setNumber(int number) {
		this.number = number;
	}
	

	

	public synchronized void setAdminPanel(Admin_Panel adminPanel) {
		this.adminPanel = adminPanel;
	}

	public synchronized void setUserPanel(User_Panel userPanel) {
		this.userPanel = userPanel;
	}
	
	public synchronized int getUserOnlineNumber() {
		while(number==0) {
			getNumber();
		}
		return userOnlineNumber;
	}

	public synchronized User getUser() {
		while(number==0) {
			getNumber();
		}
		
		return user;
	}

	public synchronized User_Info getUser_info() {
		while(number==0) {
			getNumber();
		}
		
		return user_info;
	}

	public synchronized String getMessage() { 
		while(number==0) {
			getNumber();
		}
	
		return message;
	}

	private ControlerUser() {
		
	}

		public static ControlerUser getInstance() {
			if(instance==null) {
				synchronized (ControlerUser.class) {
					if(instance==null) {
						instance=new ControlerUser();
					}
				}
			}
			return instance;
		}

	public void CheckTheOperation(TransferClass transferClass) {
		TransferClass transferClass2=new TransferClass();
		GenericList<GeneralDomain>list=new GenericList<GeneralDomain>();
		switch (transferClass.getType_Of_operation()) {
		
		case ADD:
			
			list=(GenericList<GeneralDomain>) transferClass.getServer_Object_Response();
			user=(User) list.get(0);
			user_info=(User_Info) list.get(1);
			message=transferClass.getMessage();
			
			if(adminPanel != null) {
				adminPanel.addUser(list);
			}
			
			if(userPanel != null) {
				userPanel.addUser(list);
			}
			this.number=1;
			break;
			
		case UPDATE:
			
			list=(GenericList<GeneralDomain>) transferClass.getServer_Object_Response();
			user=(User) list.get(0);
			user_info=(User_Info) list.get(1);
			message=transferClass.getMessage();
			this.number=1;
			break;	
			
		case DELETE:
			
			list=(GenericList<GeneralDomain>) transferClass.getServer_Object_Response();
			user=(User) list.get(0);
			user_info=(User_Info) list.get(1);
			message=transferClass.getMessage();	
			this.number=1;
			break;	
			
		case CHANGE_PICTURE_URL_USER:
			
			list=(GenericList<GeneralDomain>) transferClass.getServer_Object_Response();
			user_info=(User_Info) list.get(1);
			message=transferClass.getMessage();
			this.number=1;
			break;
			
		case LOGIN_USER:
			
			user=(User) transferClass.getServer_Object_Response();
			message=transferClass.getMessage();
			this.number=1;
			break;	
			
		case CHANGE_PASSWORD_USER:
			
			message=transferClass.getMessage();
			this.number=1;
			break;	
			
		case ADD_PAYMENT_CARD:
			
			message=transferClass.getMessage();
			this.number=1;
			break;	
		
		case CHECK_USER_ONLINE:
			
			userOnlineNumber=(int) transferClass.getServer_Object_Response();
			this.number=1;
			break;
		
		case REMOVE_ONLINE_USER:
			
			list=(GenericList<GeneralDomain>) transferClass.getServer_Object_Response();
			User user=(User) list.get(0);
			if(userPanel != null) {
				userPanel.removeOnlineUser(message,user);
			}
			
			this.number=1;
			break;	
			
		case DEACTIVATE_USER:
			
			message=transferClass.getMessage();
			this.number=1;
			break;
			
		default:
			break;
		}
	}

	




}
