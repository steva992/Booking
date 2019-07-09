package com.comtrade.view.panel;

import javax.swing.JPanel;

import com.comtrade.domain.user.User;

import javax.swing.JButton;

public class Super_Admin_Panel extends JPanel {

	private User user;
	
	public Super_Admin_Panel(User user) {
		setBounds(100,100,800,600);
		this.user=user;
		
		JButton btnNewButton = new JButton("super_admin");
		add(btnNewButton);
	}

}
