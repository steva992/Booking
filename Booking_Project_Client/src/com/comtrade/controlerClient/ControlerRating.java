package com.comtrade.controlerClient;

import com.comtrade.domain.GeneralDomain;
import com.comtrade.domain.property.Property;
import com.comtrade.genericClasses.GenericList;
import com.comtrade.panel.admin.Admin_Panel;
import com.comtrade.panel.user.User_Panel;
import com.comtrade.rating.Client_Rating;
import com.comtrade.transfer.TransferClass;
import com.comtrade.view.frame.Rating;

public class ControlerRating {
	private static volatile ControlerRating instance;
	private volatile Client_Rating rating;
	private volatile Property property;
	private volatile String message;
	private Admin_Panel adminPanel;
	private User_Panel userPanel;
	private volatile int number=0;
	
	
	
	
	public synchronized void setUserPanel(User_Panel userPanel) {
		this.userPanel = userPanel;
	}

	public synchronized int getNumber() {
		return number;
	}

	public synchronized void setNumber(int number) {
		this.number = number;
	}

	public synchronized void setAdminPanel(Admin_Panel adminPanel) {
		this.adminPanel = adminPanel;
	}

	public synchronized String getMessage() {
		while(number==0) {
			getNumber();
		}
		return message;
	}

	public synchronized Client_Rating getRating() {
		while(number==0) {
			getNumber();
		}
		return rating;
	}

	public synchronized Property getProperty() {
		while(number==0) {
			getNumber();
		}
		return property;
	}

	private ControlerRating() {
	
	}

	public static ControlerRating getInstance() {
		if(instance==null) {
			synchronized (ControlerRating.class) {
				if(instance==null) {
					instance=new ControlerRating();
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
			property=(Property) list.get(0);
			rating=(Client_Rating) list.get(1);
			message=transferClass.getMessage();
			if(adminPanel != null) {
				adminPanel.addRating(property.getName(), rating);
			}
			if(userPanel != null) {
				userPanel.addRating(property.getName(),rating);
			}
			this.number=1;
			break;
			
			default:
				break;
		}	
}

	
	
}	
