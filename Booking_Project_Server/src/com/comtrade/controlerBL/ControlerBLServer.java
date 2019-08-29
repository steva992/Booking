package com.comtrade.controlerBL;

import com.comtrade.view.ServerForm;

public class ControlerBLServer {
	
	private static volatile ControlerBLServer instance;
	private ServerForm serverForm;
	
	private ControlerBLServer() {
		
	}
	
	public static ControlerBLServer getInstance() {
		if(instance==null) {
			synchronized (ControlerBLServer.class) {
				if(instance==null) {
					instance=new ControlerBLServer();
				}
			}
		}
		return instance;
		
	}
	
	public void setServerForm(ServerForm serverForm) {
		this.serverForm=serverForm;
	}

	public  void entrerMessageOnTextArea(String string) {
		serverForm.setTextOnTextArea(string);
		
	}
}
