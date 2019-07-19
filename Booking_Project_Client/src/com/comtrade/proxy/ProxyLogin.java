package com.comtrade.proxy;

import java.io.IOException;
import java.net.URISyntaxException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.comtrade.constants.TransferClass_Message;
import com.comtrade.controlerKI.ControlerKI;
import com.comtrade.domain.user.User;
import com.comtrade.panel.admin.Admin_Panel;
import com.comtrade.panel.superAdmin.Super_Admin_Panel;
import com.comtrade.panel.user.User_Panel;
import com.comtrade.transfer.TransferClass;
import com.comtrade.view.frame.Application;


public class ProxyLogin implements IProxy {

	@Override
	public void login(User user) throws ClassNotFoundException, IOException, URISyntaxException {
		TransferClass transferClass= ControlerKI.getInstance().login(user);
		User user2=(User) transferClass.getServer_Object_Response();
		String message=transferClass.getMessage();
		if(user2.getUsername() != null && user2.getPassword() != null && user2.getStatus().equals("admin")) {
			JPanel adminPanel=new Admin_Panel(user2);
			Application.setPanelOnLayeredPane(adminPanel);
			JOptionPane.showMessageDialog(null,message);
		}else if(user2.getUsername() != null && user2.getPassword() != null && user2.getStatus().equals("super_admin")) {
			JPanel superAdminPanel=new Super_Admin_Panel(user2);
			Application.setPanelOnLayeredPane(superAdminPanel);
			JOptionPane.showMessageDialog(null,message);
		}else if(user2.getUsername() != null && user2.getPassword() != null && user2.getStatus().equals("user")) {
			JPanel userPanel=new User_Panel(user2);
			Application.setPanelOnLayeredPane(userPanel);
			JOptionPane.showMessageDialog(null,message);
		}else if(user2.getUsername() != null && user2.getPassword() != null && user2.getStatus().equals(null)){
			JOptionPane.showMessageDialog(null,TransferClass_Message.EXCIST_USERNAME.getValue());
		}else {
			JOptionPane.showMessageDialog(null,TransferClass_Message.WRONG_USERNAME_OR_PASSWORD.getValue());
		}
	}

	

}
