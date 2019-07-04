package com.comtrade.view.user;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.comtrade.controlerKI.ControlerKI;
import com.comtrade.domain.User;
import com.comtrade.domain.User_Info;
import com.comtrade.transfer.TransferClass;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

public class UserForm extends JFrame {

	private JPanel contentPane;
	private User user;
	private User_Info userInfo;
	private TransferClass transferClass=new TransferClass();
	private JLabel lblPicture;
	
	public UserForm(User user) throws ClassNotFoundException, IOException {
		this.user=user;
		transferClass=ControlerKI.getInstance().BackUserInfo_ForUser(user);
		this.userInfo=(User_Info) transferClass.getServer_Object_Response();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 580, 369);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblPicture = new JLabel("",JLabel.CENTER);
		lblPicture.setBounds(5, 5, 250, 200);
		
		contentPane.add(lblPicture);
		ImageIcon image= new ImageIcon(userInfo.getPictureURL());
		image.getImage().flush();
		Image image1 = image.getImage();
		Image newImg=image1.getScaledInstance(lblPicture.getWidth(),lblPicture.getHeight(),Image.SCALE_SMOOTH);
		lblPicture.setIcon(new ImageIcon(newImg));
	}

}
