package com.comtrade.proxy;

import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.comtrade.controlerKI.ControlerKI;
import com.comtrade.domain.User;
import com.comtrade.view.admin.AdminForm;
import com.comtrade.view.superAdmin.SuperAdminForm;
import com.comtrade.view.user.UserForm;

public class ProxyLogin implements IProxy {

	@Override
	public void login(User user) throws ClassNotFoundException, IOException {
		User user2=(User) ControlerKI.getInstance().login(user).getServer_Object_Response();
		if(user2.getUsername() != null && user2.getPassword() != null && user2.getStatus().equals("admin")) {
			AdminForm adminForm=new AdminForm(user2);
			openForm(adminForm);
		}else if(user2.getUsername() != null && user2.getPassword() != null && user2.getStatus().equals("super_admin")) {
			SuperAdminForm superAdminForm=new SuperAdminForm(user2);
			openForm(superAdminForm);
		}else if(user2.getUsername() != null && user2.getPassword() != null && user2.getStatus().equals("user")) {
			UserForm userForm=new UserForm(user2);
			openForm(userForm);
		}else if(user2.getUsername() != null && user2.getPassword() != null && user2.getStatus().equals(null)){
			JOptionPane.showMessageDialog(null,"!!! That user does not exist !!!");
		}else {
			JOptionPane.showMessageDialog(null,"!!! Wrong Password or Email !!!");
		}
	}

	private void openForm(JFrame form) {
		form.setVisible(true);
		
	}

}
