package com.comtrade.controlerKI;

import com.comtrade.panel.admin.Admin_Panel;
import com.comtrade.view.frame.Application;

public class ControlerClient {
	private static volatile ControlerClient instance;
	private Application clientForm;
	
	private ControlerClient() {
		
	}
	
	public static ControlerClient getInstance() {
		if(instance==null) {
			synchronized (Application.class) {
				if(instance==null) {
					instance=new ControlerClient();
				}
			}
		}
		return instance;
		
	}
	
	public void setServerForm(Application serverForm) {
		this.clientForm=serverForm;
	}

	
}
