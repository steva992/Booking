package com.comtrade.controlerKI;

import java.util.ArrayList;
import java.util.List;

public class ControlerChat {
	private volatile static ControlerChat instance;
	private List<String>list;
	
	public void add(String username) {
		list.add(username);
	}

	private ControlerChat() {
		list=new ArrayList<String>();
	}

	public List<String> getList() {
		return list;
	}

	public void setList(List<String> list) {
		this.list = list;
	}
	
	public static ControlerChat getIstanca() {
		if(instance==null) {
			synchronized (ControlerChat.class) {
				if(instance==null) {
					instance=new ControlerChat();
				}
			}
		}
		return instance;
		
	}
	
}
