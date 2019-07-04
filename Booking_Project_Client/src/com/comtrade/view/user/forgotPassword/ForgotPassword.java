package com.comtrade.view.user.forgotPassword;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.comtrade.constants.Regular_Expression;
import com.comtrade.constants.TransferClass_Message;
import com.comtrade.controlerKI.ControlerKI;
import com.comtrade.domain.User;
import com.comtrade.genericClasses.GenericList;
import com.comtrade.importantCommonMethod.ImportantCommonMethod;
import com.comtrade.transfer.TransferClass;
import com.comtrade.view.LoginForm;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.JCheckBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.regex.Pattern;

public class ForgotPassword extends JFrame {

	private JPanel contentPane;
	private JTextField tfUsername;
	private JPasswordField pfOldPassword;
	private JPasswordField pfNewPassword;
	private JCheckBox cbHideUnhide;

	
	public ForgotPassword() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 618, 337);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		tfUsername = new JTextField();
		tfUsername.setBounds(42, 42, 374, 33);
		contentPane.add(tfUsername);
		tfUsername.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(42, 11, 374, 25);
		contentPane.add(lblNewLabel);
		
		JLabel lblOldPassword = new JLabel("Old Password");
		lblOldPassword.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblOldPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblOldPassword.setBounds(42, 86, 374, 25);
		contentPane.add(lblOldPassword);
		
		JLabel lblNewPassword = new JLabel("New Password");
		lblNewPassword.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewPassword.setBounds(42, 161, 374, 25);
		contentPane.add(lblNewPassword);
		
		JButton btnNewButton = new JButton("Change Your Password");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username=tfUsername.getText();
				String oldPassword=String.copyValueOf(pfOldPassword.getPassword());
				String newPassword=String.copyValueOf(pfNewPassword.getPassword());
				boolean testfield=fieldValidity(username,oldPassword,newPassword);
				boolean fieldsComplete=FieldComplete(username,oldPassword,newPassword);
				if(oldPassword.equals(newPassword)) {
					JOptionPane.showMessageDialog(null,"!!! Incorect password Input !!!");
				}else {
					if(fieldsComplete) {
						if(testfield) {
							User user=new User();
							user.setUsername(username);
							user.setPassword(oldPassword);
							User user1=new User();
							user1.setUsername(username);
							user1.setPassword(newPassword);
							GenericList<User>listUser=new GenericList<User>();
							listUser.add(user);
							listUser.add(user1);
							try {
								TransferClass transferClass=ControlerKI.getInstance().changePassword(listUser);
								user=(User) transferClass.getServer_Object_Response();
								if(user.getId()==0) {
									JOptionPane.showMessageDialog(null,TransferClass_Message.WRONG_USERNAME_OR_PASSWORD.getValue());
								}else {
									JOptionPane.showMessageDialog(null,TransferClass_Message.SUCCESSFUL_CHANGE.getValue());
									dispose();
									ImportantCommonMethod.startLoginForm();
								}
							} catch (ClassNotFoundException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}else {
							JOptionPane.showMessageDialog(null,TransferClass_Message.INCORECT_ENTER_DATA.getValue());
						}
					}else {
						JOptionPane.showMessageDialog(null,TransferClass_Message.ALL_FIELDS_FILL.getValue());
					}
					
					
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton.setBounds(99, 252, 270, 23);
		contentPane.add(btnNewButton);
		
		pfOldPassword = new JPasswordField();
		pfOldPassword.setBounds(42, 122, 374, 33);
		contentPane.add(pfOldPassword);
		
		pfNewPassword = new JPasswordField();
		pfNewPassword.setBounds(42, 197, 374, 33);
		contentPane.add(pfNewPassword);
		
		cbHideUnhide = new JCheckBox("Hide / Unhide Password");
		cbHideUnhide.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(cbHideUnhide.isSelected()) {
					pfNewPassword.setEchoChar((char) 0);
					pfOldPassword.setEchoChar((char) 0);
				}else {
					pfNewPassword.setEchoChar('*');
					pfOldPassword.setEchoChar('*');
				}
			}
		});
		cbHideUnhide.setHorizontalAlignment(SwingConstants.CENTER);
		cbHideUnhide.setBounds(444, 109, 152, 59);
		contentPane.add(cbHideUnhide);
		
		JButton btnNewButton_1 = new JButton("Back To Login");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				LoginForm loginForm=new LoginForm();
				loginForm.setVisible(true);
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnNewButton_1.setBounds(444, 12, 152, 46);
		contentPane.add(btnNewButton_1);
	}


	protected boolean FieldComplete(String username, String oldPassword, String newPassword) {
		if(username.length()<1 || oldPassword.length()<1 || newPassword.length()<1 ) {
			return false;
		}
		return true;
	}


	protected boolean fieldValidity(String username, String oldPassword, String newPassword) {
		backBorderToGray();
		boolean username1=Pattern.matches(Regular_Expression.USRERNAME.getValue(),username);
		
		if(username1) {
			return true;
		}else if(username1==false){
			tfUsername.setBorder(BorderFactory.createLineBorder(Color.RED,4));
		}
		return false;
	}


	private void backBorderToGray() {
		tfUsername.setBorder(BorderFactory.createLineBorder(Color.GRAY,1));
	}
}
