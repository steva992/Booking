package com.comtrade.panel.superAdmin;

import javax.swing.JPanel;

import com.comtrade.constants.Panel_Dimension;
import com.comtrade.domain.user.User;

import javax.swing.JButton;

public class Super_Admin_Panel extends JPanel {

	private User user;
	
	public Super_Admin_Panel(User user) {
		setBounds(Panel_Dimension.X.getValue(),Panel_Dimension.Y.getValue(),Panel_Dimension.WIDTH.getValue(),Panel_Dimension.HEIGHT.getValue());
		this.user=user;
		
		JButton btnNewButton = new JButton("super_admin");
		add(btnNewButton);
	}

}
