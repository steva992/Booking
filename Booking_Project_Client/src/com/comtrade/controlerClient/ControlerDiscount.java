package com.comtrade.controlerClient;

import com.comtrade.domain.GeneralDomain;
import com.comtrade.domain.discount.Discount;
import com.comtrade.domain.property.Property;
import com.comtrade.genericClasses.GenericList;
import com.comtrade.panel.admin.Admin_Panel;
import com.comtrade.panel.user.User_Panel;
import com.comtrade.transfer.TransferClass;

public class ControlerDiscount {
		private static volatile ControlerDiscount instance;
		private volatile Property property;
		private volatile Discount discount1;
		private volatile String message;
		private User_Panel userPanel;
		private volatile int number=0;
		
		
		
		
		
		public synchronized  int getNumber() {
			return number;
		}

		public  synchronized void setNumber(int number) {
			
			this.number = number;
		}

		public synchronized void setUserPanel(User_Panel userPanel) {
			this.userPanel = userPanel;
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

		public synchronized Discount getDiscount1() {
			while(number==0) {
				getNumber();
			}
			return discount1;
		}

		private ControlerDiscount() {
		
		}
	
	public static ControlerDiscount getInstance() {
		if(instance==null) {
			synchronized (ControlerDiscount.class) {
				if(instance==null) {
					instance=new ControlerDiscount();
				}
			}
				instance=new ControlerDiscount();
		}
		return instance;
		
	}
	
	public void CheckTheOperation(TransferClass transferClass) {
		GenericList<GeneralDomain>list=new GenericList<GeneralDomain>();
		switch (transferClass.getType_Of_operation()) {
		case ADD:
			list=(GenericList<GeneralDomain>) transferClass.getServer_Object_Response();;
			property=(Property) list.get(0);
			discount1=(Discount) list.get(1);
			message=transferClass.getMessage();
			
			if(userPanel != null) {
				userPanel.addDiscount(property.getName(),discount1);
			}
			
			this.number=1;
			break;
		case DELETE:
			list=(GenericList<GeneralDomain>) transferClass.getServer_Object_Response();
			property=(Property) list.get(0);
			discount1=(Discount) list.get(1);
			message=transferClass.getMessage();
			
			if(userPanel != null) {
				userPanel.deleteDiscount(property.getName(),discount1);
			}
			
			this.number=1;
			break;	

		default:
			break;
		}
	}

	
	
}	
