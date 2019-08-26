package com.comtrade.controlerClient;

import org.omg.CosNaming.IstringHelper;

import com.comtrade.domain.GeneralDomain;
import com.comtrade.domain.property.Property;
import com.comtrade.domain.user.User;
import com.comtrade.domain.user.User_Info;
import com.comtrade.genericClasses.GenericList;
import com.comtrade.panel.admin.Admin_Panel;
import com.comtrade.panel.superAdmin.Super_Admin_Panel;
import com.comtrade.panel.user.User_Panel;
import com.comtrade.reservation.Reservation;
import com.comtrade.transfer.TransferClass;

public class ControlerReservation {
	
	private static volatile ControlerReservation instance;
	private volatile Property property;
	private volatile Reservation reservation;
	private volatile User user;
	private volatile User_Info user_info;
	private volatile String message;
	private Admin_Panel adminPanel;
	private User_Panel userPanel;
	private Super_Admin_Panel superAdmin;
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
	
	public synchronized void setSuperAdmin(Super_Admin_Panel superAdmin) {
		this.superAdmin = superAdmin;
	}


	public synchronized String getMessage() {
		while(number==0) {
			getNumber();
		}
		return message;
	}

	public synchronized Property getProperty() {
		while(number==0) {
			getNumber();
		}
		return property;
	}

	public synchronized Reservation getReservation() {
		while(number==0) {
			getNumber();
		}
		return reservation;
	}

	private ControlerReservation() {
		 number=0;
	}

	public static ControlerReservation getInstance() {
			if(instance==null) {
				synchronized (ControlerReservation.class) {
					if(instance==null) {
						instance=new ControlerReservation();
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
				reservation=(Reservation) list.get(1);
				user=(User) list.get(2);
				user_info=(User_Info) list.get(3);
				message=transferClass.getMessage();
				
				
				if(adminPanel != null) {
					adminPanel.addReservation(property.getName(),reservation,user,user_info);
				}
				if(userPanel != null) {
					userPanel.addReservation(property.getName(),reservation);
				}
				if(superAdmin != null) {
					superAdmin.addReservation(property.getName(),reservation);
				}
				this.number=1;
				break;
				
		case DELETE:
				list=(GenericList<GeneralDomain>) transferClass.getServer_Object_Response();
				property=(Property) list.get(0);
				reservation=(Reservation) list.get(1);
				user=(User) list.get(2);
				user_info=(User_Info) list.get(3);
				message=transferClass.getMessage();
				
				if(adminPanel != null) {
					adminPanel.deleteReservation(property.getName(),reservation,user,user_info);
				}
				if(userPanel != null) {
					userPanel.deleteReservation(property.getName(),reservation);
				}
				if(superAdmin != null) {
					superAdmin.deleteReservation(property.getName(),reservation);
				}
				this.number=1;
				break;
		}	
	}

}	
