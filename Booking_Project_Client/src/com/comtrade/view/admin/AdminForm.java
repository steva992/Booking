package com.comtrade.view.admin;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.comtrade.domain.User;
import javax.swing.JButton;

public class AdminForm extends JFrame {

	private JPanel contentPane;
	private User user;
	
	public AdminForm(User user) {
		this.user=user;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JButton btnNewButton = new JButton("Admin");
		contentPane.add(btnNewButton, BorderLayout.CENTER);
	}

}
