package com.comtrade.proxy;

import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.comtrade.controlerKI.ControlerKI;
import com.comtrade.domain.user.User;
import com.comtrade.panel.admin.Admin_Panel;
import com.comtrade.panel.superAdmin.Super_Admin_Panel;
import com.comtrade.panel.user.User_Panel;
import com.comtrade.view.frame.Application;


public class ProxyLogin implements IProxy {

	@Override
	public void login(User user) throws ClassNotFoundException, IOException {
		User user2=(User) ControlerKI.getInstance().login(user).getServer_Object_Response();
		if(user2.getUsername() != null && user2.getPassword() != null && user2.getStatus().equals("admin")) {
			JPanel adminPanel=new Admin_Panel(user2);
			Application.setPanelOnLayeredPane(adminPanel);
		}else if(user2.getUsername() != null && user2.getPassword() != null && user2.getStatus().equals("super_admin")) {
			JPanel superAdminPanel=new Super_Admin_Panel(user2);
			Application.setPanelOnLayeredPane(superAdminPanel);
		}else if(user2.getUsername() != null && user2.getPassword() != null && user2.getStatus().equals("user")) {
			JPanel userPanel=new User_Panel(user2);
			Application.setPanelOnLayeredPane(userPanel);
		}else if(user2.getUsername() != null && user2.getPassword() != null && user2.getStatus().equals(null)){
			JOptionPane.showMessageDialog(null,"!!! That user does not exist !!!");
		}else {
			JOptionPane.showMessageDialog(null,"!!! Wrong Password or Email !!!");
		}
	}

	

}
