package com.comtrade.controlerPL;

import com.comtrade.view.ServerForm;

public class ControlerPLServer {
	
	private static volatile ControlerPLServer instance;
	private ServerForm serverForm;
	
	private ControlerPLServer() {
		
	}
	
	public static ControlerPLServer getInstance() {
		if(instance==null) {
			synchronized (ControlerPLServer.class) {
				if(instance==null) {
					instance=new ControlerPLServer();
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
