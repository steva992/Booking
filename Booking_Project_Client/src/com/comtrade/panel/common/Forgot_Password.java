package com.comtrade.panel.common;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;

import com.comtrade.constants.Panel_Dimension;
import com.comtrade.constants.Regular_Expression;
import com.comtrade.constants.TransferClass_Message;
import com.comtrade.controlerKI.ControlerKI;
import com.comtrade.domain.user.User;
import com.comtrade.genericClasses.GenericList;
import com.comtrade.transfer.TransferClass;
import com.comtrade.view.frame.Application;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Forgot_Password extends JPanel {
	private JTextField tfUsername;
	private JPasswordField pfOldPassword;
	private JPasswordField pfNewPassword;
	private JPanel login=new Login();

	/**
	 * Create the panel.
	 */
	public Forgot_Password() {
		setLayout(null);
		
		JLabel label = new JLabel("Username");
		setBounds(Panel_Dimension.X.getValue(),Panel_Dimension.Y.getValue(),Panel_Dimension.WIDTH.getValue(),Panel_Dimension.HEIGHT.getValue());
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Tahoma", Font.BOLD, 11));
		label.setBounds(42, 44, 374, 25);
		add(label);
		
		tfUsername = new JTextField();
		tfUsername.setColumns(10);
		tfUsername.setBounds(42, 75, 374, 33);
		add(tfUsername);
		
		JLabel label_1 = new JLabel("Old Password");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_1.setBounds(42, 119, 374, 25);
		add(label_1);
		
		pfOldPassword = new JPasswordField();
		pfOldPassword.setBounds(42, 155, 374, 33);
		add(pfOldPassword);
		
		JLabel label_2 = new JLabel("New Password");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_2.setBounds(42, 194, 374, 25);
		add(label_2);
		
		pfNewPassword = new JPasswordField();
		pfNewPassword.setBounds(42, 230, 374, 33);
		add(pfNewPassword);
		
		JButton button = new JButton("Change Your Password");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
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
									Application.setPanelOnLayeredPane(login);
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
		button.setFont(new Font("Tahoma", Font.BOLD, 11));
		button.setBounds(99, 285, 270, 23);
		add(button);
		
		JCheckBox cbHideUnhide = new JCheckBox("Hide / Unhide Password");
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
		cbHideUnhide.setBounds(444, 142, 152, 59);
		add(cbHideUnhide);
		
		JButton button_1 = new JButton("Back To Login");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Application.setPanelOnLayeredPane(login);
			}
		});
		button_1.setFont(new Font("Tahoma", Font.BOLD, 10));
		button_1.setBounds(444, 45, 152, 46);
		add(button_1);

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
