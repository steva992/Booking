package com.comtrade.proxy;

import java.io.IOException;

import javax.swing.JOptionPane;

import com.comtrade.controlerKI.ControlerKI;
import com.comtrade.domain.User;
import com.comtrade.view.MedjuForma;

public class ProxyLogin implements IProxy {

	@Override
	public void login(User user) throws ClassNotFoundException, IOException {
		User user2=(User) ControlerKI.getInstance().login(user).getServer_Object_Response();
		if(user2.getUsername() != null && user2.getPassword() != null && user2.getStatus().equals("admin")) {
			MedjuForma medju=new MedjuForma();
			medju.setVisible(true);
		}else if(user2.getUsername() != null && user2.getPassword() != null && user2.getStatus().equals("super_admin")) {
			
		}else if(user2.getUsername() != null && user2.getPassword() != null && user2.getStatus().equals("user")) {
			
		}else if(user2.getUsername() != null && user2.getPassword() != null && user2.getStatus().equals(null)){
			JOptionPane.showMessageDialog(null,"!!! That user does not exist !!!");
		}else {
			JOptionPane.showMessageDialog(null,"!!! Wrong Password or Email !!!");
		}
	}

}
