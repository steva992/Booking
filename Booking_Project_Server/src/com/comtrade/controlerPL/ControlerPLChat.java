package com.comtrade.controlerPL;

import java.util.ArrayList;
import java.util.List;

import com.comtrade.genericClasses.GenericList;
import com.comtrade.threads.SendingMessageThread;
import com.comtrade.view.ServerForm;

public class ControlerPLChat {
	private static volatile ControlerPLChat instance;
	private List<SendingMessageThread>list;
	private List<String>listUsername;
	private ServerForm serverForm;
	private int number=1;
	
	public List<String> getListUsername() {
		return listUsername;
	}
	public void setListUsername(List<String> listMessage) {
		this.listUsername = listMessage;
	}
	
	public static ControlerPLChat getInstance() {
		if(instance==null) {
			synchronized (ControlerPLChat.class) {
				if(instance==null) {
					instance=new ControlerPLChat();
				}
			}
		}
		return instance;
		
	}
	
	private ControlerPLChat() {
		list=new ArrayList<SendingMessageThread>();
		listUsername=new ArrayList<String>();
	}
	
	private void setServerForm(ServerForm serverForm) {
		this.serverForm=serverForm;
	}
	
	public void addClient(SendingMessageThread printingThread,String username) {
		list.add(printingThread);
		setData("\n"+number+username);
		number++;
	}
	public void setData(String string) {
		serverForm.setTextOnTextArea(string);
	}
	
	public void notifyClient(String message,SendingMessageThread printingThread) {
		for(SendingMessageThread pr : list) {
			if(!pr.equals(printingThread)) {
				pr.send(message);
			}
		}
	}
	
	public void printsUser(List<String> list1) {
		for(SendingMessageThread pr : list) {
			for(int i=0;i<list.size();i++) {
				pr.setOnScreen(list1.get(i));
			}
		}
	}
}
