package com.comtrade.view.frame;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.comtrade.commonmethod.CommonMethod;
import com.comtrade.constants.AbsolutePath;
import com.comtrade.constants.URLS;
import com.comtrade.domain.user.User_Info;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

public class UserInfo extends JFrame {

	private JPanel contentPane;
	private User_Info user_info;
	private JLabel lblPicture,lblName,lblSurname,email,lblMobilePhone;
	private JLabel lblBackGround;

	
	public UserInfo(User_Info userInfoReservation) {
		this.user_info=userInfoReservation;
		setBounds(100, 100, 572,421);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblPicture = new JLabel("New label");
		lblPicture.setBounds(47, 96, 221, 190);
		contentPane.add(lblPicture);
		
		lblName = new JLabel("New label");
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		lblName.setForeground(Color.BLACK);
		lblName.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblName.setBounds(319, 98, 192, 36);
		contentPane.add(lblName);
		
		lblSurname = new JLabel("New label");
		lblSurname.setHorizontalAlignment(SwingConstants.CENTER);
		lblSurname.setForeground(Color.BLACK);
		lblSurname.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSurname.setBounds(319, 154, 192, 27);
		contentPane.add(lblSurname);
		
		JLabel lblSurname_1 = new JLabel("SURNAME");
		lblSurname_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSurname_1.setForeground(Color.WHITE);
		lblSurname_1.setBounds(319, 128, 154, 27);
		contentPane.add(lblSurname_1);
		
		JLabel lblEmail = new JLabel("EMAIL");
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblEmail.setForeground(Color.WHITE);
		lblEmail.setBounds(319, 184, 154, 20);
		contentPane.add(lblEmail);
		
		JLabel lblPhoneNumber = new JLabel("PHONE NUMBER");
		lblPhoneNumber.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPhoneNumber.setForeground(Color.WHITE);
		lblPhoneNumber.setBounds(319, 230, 154, 20);
		contentPane.add(lblPhoneNumber);
		
		JLabel lblNewLabel = new JLabel("NAME");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(319, 76, 154, 27);
		contentPane.add(lblNewLabel);
		
		email = new JLabel("New label");
		email.setHorizontalAlignment(SwingConstants.CENTER);
		email.setForeground(Color.BLACK);
		email.setFont(new Font("Tahoma", Font.BOLD, 14));
		email.setBounds(318, 201, 193, 27);
		contentPane.add(email);
		
		lblMobilePhone = new JLabel("New label");
		lblMobilePhone.setHorizontalAlignment(SwingConstants.CENTER);
		lblMobilePhone.setForeground(Color.BLACK);
		lblMobilePhone.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblMobilePhone.setBounds(318, 250, 193, 27);
		contentPane.add(lblMobilePhone);
		
		lblBackGround = new JLabel("");
		lblBackGround.setForeground(Color.WHITE);
		lblBackGround.setBounds(0, 0, 556, 382);
		CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+URLS.USER_CARD_PICTURE.getValue(), lblBackGround);
		contentPane.add(lblBackGround);
		setDataOnLabel();
		
	}


	private void setDataOnLabel() {
		lblName.setText(user_info.getName());
		lblSurname.setText(user_info.getSurname());
		lblMobilePhone.setText(user_info.getMobileNumber());
		email.setText(user_info.getEmail());
		CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+user_info.getPictureURL(), lblPicture);
		
	}
}
