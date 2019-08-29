package com.comtrade.proxy;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.concurrent.atomic.AtomicInteger;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.comtrade.constants.TransferClass_Message;
import com.comtrade.constants.Type_Of_Data;
import com.comtrade.constants.Type_Of_Operation;
import com.comtrade.controlerClient.ControlerUI;
import com.comtrade.controlerClient.ControlerUser;
import com.comtrade.domain.user.User;
import com.comtrade.panel.admin.Admin_Panel;
import com.comtrade.panel.superAdmin.Super_Admin_Panel;
import com.comtrade.panel.user.User_Panel;
import com.comtrade.threads.ClientThreadResponse;
import com.comtrade.transfer.TransferClass;
import com.comtrade.view.frame.Application;


public class ProxyLogin implements IProxy {
	
	

	@Override
	public void login(User user) throws ClassNotFoundException, IOException, URISyntaxException {
		
		ControlerUI.getInstance().sendToServer(Type_Of_Operation.LOGIN_USER,Type_Of_Data.USER, user);
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		user=ControlerUser.getInstance().getUser();
		String message=ControlerUser.getInstance().getMessage();
		ControlerUser.getInstance().setNumber(0);
		if(user != null) {
			
			if(user.getStatus().equals("admin")) {
				
				JPanel adminPanel=new Admin_Panel(user);
				Application.setPanelOnLayeredPane(adminPanel);
				JOptionPane.showMessageDialog(null,message);
				
			}else if(user.getStatus().equals("super_admin")) {
				
				JPanel adminPanel=new Super_Admin_Panel(user);
				Application.setPanelOnLayeredPane(adminPanel);
				JOptionPane.showMessageDialog(null,message);
				
			}else if(user.getStatus().equals("user")) {
				
				JPanel userPanel=new User_Panel(user);
				Application.setPanelOnLayeredPane(userPanel);
				JOptionPane.showMessageDialog(null,message);
				
			}else if(user.getStatus().equals("deactivated")) {
				
				JOptionPane.showMessageDialog(null,"THIS USER IS DEACTIVATED");
			}
			
		}else {
			
			JOptionPane.showMessageDialog(null,message);
		}

	}
	
}	
