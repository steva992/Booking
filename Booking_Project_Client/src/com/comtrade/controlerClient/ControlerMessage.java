package com.comtrade.controlerClient;

import com.comtrade.domain.user.User;
import com.comtrade.message.Message;
import com.comtrade.panel.admin.Admin_Panel;
import com.comtrade.panel.user.User_Panel;
import com.comtrade.transfer.TransferClass;

public class ControlerMessage {
	private static volatile ControlerMessage instance;
	private User_Panel userPanel;
	private Admin_Panel adminPanel;
	private volatile String message;
	private volatile User user;
	private volatile int number;
	
	
	
	public synchronized User getUser() {
		while(number==0) {
			getNumber();
		}
		return user;
	}

	public synchronized void setUserPanel(User_Panel userPanel) {
		this.userPanel = userPanel;
	}

	public synchronized void setAdminPanel(Admin_Panel adminPanel) {
		this.adminPanel = adminPanel;
	}

	public synchronized int getNumber() {
		return number;
	}

	public synchronized void setNumber(int number) {
		this.number = number;
	}

	public synchronized String getMessage() {
		while(number==0) {
			getNumber();
		}
		return message;
	}

	private ControlerMessage() {
		number=0;
	}
	
	public static ControlerMessage getInstance() {
		if(instance==null) {
			synchronized (ControlerMessage.class) {
				if(instance==null) {
					instance=new ControlerMessage();
				}
			}
		}
		return instance;
	}

	public void CheckTheOperation(TransferClass transferClass) {
		TransferClass transferClass2=new TransferClass();
		Message chatmessage=new Message();
		User user=new User();
		switch(transferClass.getType_Of_operation()) {
			case SENDMESSAGE:
				
				message=transferClass.getMessage();
				user=(User) transferClass.getServer_Object_Response();
				if(adminPanel != null) {
					adminPanel.appendMessageOnTextArea(message,user);
				}
				
				if(userPanel != null) {
					userPanel.appendToTextArea(message, user);
				}
				this.number=1;
				break;
				
			case DELETE_FROM_ADMIN_CHAT:
				user=(User) transferClass.getServer_Object_Response();
				if(adminPanel != null) {
					adminPanel.removeFromList(user);
				}
				this.number=1;
				break;
			default:
				break;
		}
		
	}
	
	
}
