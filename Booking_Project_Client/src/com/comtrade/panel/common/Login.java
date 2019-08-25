package com.comtrade.panel.common;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

import com.comtrade.commonmethod.CommonMethod;
import com.comtrade.constants.AbsolutePath;
import com.comtrade.constants.Panel_Dimension;
import com.comtrade.constants.URL;
import com.comtrade.domain.user.User;
import com.comtrade.proxy.IProxy;
import com.comtrade.proxy.ProxyLogin;
import com.comtrade.threads.ClientThreadResponse;
import com.comtrade.view.frame.Application;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.BorderFactory;
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
import java.awt.SystemColor;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;

public class Login extends JPanel {
	private JTextField tfUsernameLogin;
	private JPasswordField pfPasswordLogin;

	
	public Login() {
		setBackground(Color.WHITE);
		setLayout(null);
		setBounds(Panel_Dimension.X.getValue(),Panel_Dimension.Y.getValue(),Panel_Dimension.WIDTH.getValue(),Panel_Dimension.HEIGHT.getValue());
		
		tfUsernameLogin = new JTextField();
		tfUsernameLogin.setFont(new Font("Castellar", Font.BOLD, 18));
		tfUsernameLogin.setBackground(Color.WHITE);
		tfUsernameLogin.setColumns(10);
		tfUsernameLogin.setBounds(261, 128, 328, 36);
		add(tfUsernameLogin);
		JLabel label = new JLabel("Username");
		label.setForeground(Color.RED);
		label.setHorizontalAlignment(SwingConstants.LEFT);
		label.setFont(new Font("Castellar", Font.BOLD, 30));
		label.setBounds(261,89,328,36);
		add(label);
		
		JLabel label_1 = new JLabel("Password");
		label_1.setForeground(Color.RED);
		label_1.setHorizontalAlignment(SwingConstants.LEFT);
		label_1.setFont(new Font("Castellar", Font.BOLD, 30));
		label_1.setBounds(261, 194, 328, 41);
		add(label_1);
		
		pfPasswordLogin = new JPasswordField();
		pfPasswordLogin.setFont(new Font("Castellar", Font.BOLD, 18));
		pfPasswordLogin.setBackground(Color.WHITE);
		pfPasswordLogin.setBounds(261, 238, 328, 36);
		add(pfPasswordLogin);
		
		JButton btnForgotPassword = new JButton("Forgot Password ?!");
		btnForgotPassword.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		btnForgotPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JPanel forgotPassword=new Forgot_Password();
				Application.setPanelOnLayeredPane(forgotPassword);
			}
		});
		btnForgotPassword.setForeground(Color.WHITE);
		btnForgotPassword.setFont(new Font("Castellar", Font.BOLD, 10));
		btnForgotPassword.setBackground(Color.RED);
		btnForgotPassword.setBounds(417, 285, 172, 27);
		add(btnForgotPassword);
		
		
		JButton btnLogin = new JButton("LOGIN");
		btnLogin.setHorizontalAlignment(SwingConstants.LEFT);
		btnLogin.setBackground(Color.WHITE);
		btnLogin.setFont(new Font("Castellar", Font.BOLD, 16));
		//btnLogin.setBackground(new Color(0,0,0,0));
		btnLogin.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
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
		btnLogin.setForeground(new Color(220, 20, 60));
		btnLogin.setBounds(310, 352, 197, 41);
		CommonMethod.setNewPicutreOnButton(AbsolutePath.absolutePath()+URL.PICTURE_FOR_LOGIN_BUTTON_LOGIN.getValue(), btnLogin);
		add(btnLogin);
		JButton btnSignup = new JButton("SIGN UP");
		btnSignup.setHorizontalAlignment(SwingConstants.LEFT);
		btnSignup.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		btnSignup.setForeground(new Color(220, 20, 60));
		btnSignup.setFont(new Font("Castellar", Font.BOLD, 16));
		btnSignup.setBackground(Color.WHITE);
		btnSignup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JPanel signUp=new SignUp();
				Application.setPanelOnLayeredPane(signUp);
			}
		});
		btnSignup.setBounds(310, 417, 197, 36);
		CommonMethod.setNewPicutreOnButton(AbsolutePath.absolutePath()+URL.PICTURE_FOR_LOGIN_BUTTON_SIGN_UP.getValue(), btnSignup);
		add(btnSignup);
		
		
		JLabel lblWallpaper = new JLabel("New label");
		lblWallpaper.setFont(new Font("Castellar", Font.BOLD, 12));
		lblWallpaper.setBounds(Panel_Dimension.X.getValue(),Panel_Dimension.Y.getValue(),Panel_Dimension.WIDTH.getValue(),Panel_Dimension.HEIGHT.getValue());
		add(lblWallpaper);
		CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+URL.PICTURE_FOR_LOGIN_BACKGROUND.getValue(),lblWallpaper);
		
	}
}
