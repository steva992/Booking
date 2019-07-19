package com.comtrade.panel.common;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

import com.comtrade.constants.Panel_Dimension;
import com.comtrade.domain.user.User;
import com.comtrade.proxy.IProxy;
import com.comtrade.proxy.ProxyLogin;
import com.comtrade.view.frame.Application;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JCheckBox;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URISyntaxException;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Login extends JPanel {
	private JTextField tfUsernameLogin;
	private JPasswordField pfPasswordLogin;
	private JCheckBox chckbxNewCheckBox_1;

	
	public Login() {
		setLayout(null);
		setBounds(Panel_Dimension.X.getValue(),Panel_Dimension.Y.getValue(),Panel_Dimension.WIDTH.getValue(),Panel_Dimension.HEIGHT.getValue());
		JLabel label = new JLabel("Username");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Tahoma", Font.BOLD, 15));
		label.setBounds(229,81,328,36);
		add(label);
		
		tfUsernameLogin = new JTextField();
		tfUsernameLogin.setColumns(10);
		tfUsernameLogin.setBounds(229, 128, 328, 36);
		add(tfUsernameLogin);
		
		JLabel label_1 = new JLabel("Password");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		label_1.setBounds(229, 167, 328, 41);
		add(label_1);
		
		pfPasswordLogin = new JPasswordField();
		pfPasswordLogin.setBounds(229, 211, 328, 36);
		add(pfPasswordLogin);
		
		JButton button = new JButton("Forgot Password ?");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JPanel forgotPassword=new Forgot_Password();
				Application.setPanelOnLayeredPane(forgotPassword);
			}
		});
		button.setForeground(Color.WHITE);
		button.setFont(new Font("Tahoma", Font.BOLD, 11));
		button.setBackground(Color.RED);
		button.setBounds(229, 276, 143, 23);
		add(button);
		
		
		JButton btnLogin = new JButton("Log In");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username=tfUsernameLogin.getText();
				String password=String.valueOf(pfPasswordLogin.getPassword());
				User user=new User();
				user.setUsername(username);
				user.setPassword(password);
				IProxy iProxy=new ProxyLogin();
				try {
					try {
						iProxy.login(user);
					} catch (URISyntaxException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnLogin.setForeground(Color.RED);
		btnLogin.setBounds(304, 409, 157, 41);
		add(btnLogin);
		
		JButton btnNewButton = new JButton("Sign Up");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JPanel signUp=new SignUp();
				Application.setPanelOnLayeredPane(signUp);
			}
		});
		btnNewButton.setBounds(304, 477, 157, 46);
		add(btnNewButton);
		
		chckbxNewCheckBox_1 = new JCheckBox("Hide / Unhide");
		chckbxNewCheckBox_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(chckbxNewCheckBox_1.isSelected()) {
					pfPasswordLogin.setEchoChar((char) 0);
				}else {
					pfPasswordLogin.setEchoChar('*');
				}
			}
		});
		chckbxNewCheckBox_1.setBounds(425, 276, 118, 23);
		add(chckbxNewCheckBox_1);
		

	}
}
