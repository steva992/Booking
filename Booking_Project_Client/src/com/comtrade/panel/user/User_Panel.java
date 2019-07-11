package com.comtrade.panel.user;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.comtrade.commonmethod.CommonMethod;
import com.comtrade.constants.Panel_Dimension;
import com.comtrade.constants.PicturesURL;
import com.comtrade.controlerKI.ControlerKI;
import com.comtrade.domain.user.User;
import com.comtrade.domain.user.User_Info;
import com.comtrade.transfer.TransferClass;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class User_Panel extends JPanel {

	private User user;
	private User_Info user_info;
	private TransferClass transferClass=new TransferClass();
	private JLabel lblPicture ;
	
	public User_Panel(User user) throws ClassNotFoundException, IOException {
		this.user=user;
		transferClass=ControlerKI.getInstance().BackUserInfo_ForUser(user);
		this.user_info=(User_Info) transferClass.getServer_Object_Response();
		setBounds(Panel_Dimension.X.getValue(),Panel_Dimension.Y.getValue(),Panel_Dimension.WIDTH.getValue(),Panel_Dimension.HEIGHT.getValue());
		this.user_info=(User_Info) transferClass.getServer_Object_Response();
		setLayout(null);
		
		lblPicture = new JLabel("",JLabel.CENTER);
		lblPicture.setBackground(Color.CYAN);
		lblPicture.setBounds(42, 90, 423, 200);
		add(lblPicture);
		CommonMethod.setNewPicutreOnLabel(user_info.getPictureURL(),lblPicture);
		
		JButton btnNewButton = new JButton("UPLOAD PICTURE");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addNewPicture(user_info, user, lblPicture);
				try {
					ControlerKI.getInstance().changePictureURLUser(user_info);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(42, 301, 423, 38);
		add(btnNewButton);
	}

	
	public static void addNewPicture(User_Info user_info,User user,JLabel label) {
		String newPictureURL=JOptionPane.showInputDialog("Enter your location of Picture: ");
		newPictureURL.replace('\\','/');
		if(newPictureURL.substring(newPictureURL.length()-3,newPictureURL.length()).equals("jpg") ||
		 newPictureURL.substring(newPictureURL.length()-3,newPictureURL.length()).equals("png")	) {
			createPictureForServer(newPictureURL,user);
			CommonMethod.setNewPicutreOnLabel(newPictureURL, label);
			user_info.setPictureURL(PicturesURL.PROJECT_PATH.getValue()+PicturesURL.PROFILE_PICTURE_USERS.getValue()+"/"+user.getUsername()+"/"+"ProfilePicture.jpg");
		}
	}
	
	
	public static void createPictureForServer(String newPictureURL,User user){
		try {
			FileInputStream in=new FileInputStream(newPictureURL);
			FileOutputStream out=new FileOutputStream(PicturesURL.PROJECT_PATH.getValue()+PicturesURL.PROFILE_PICTURE_USERS.getValue()+"/"+user.getUsername()+"/"+"ProfilePicture.jpg");
			BufferedInputStream bin=new BufferedInputStream(in);
			BufferedOutputStream bou=new BufferedOutputStream(out);
			int b=0;
			while(b !=-1) {
				b=bin.read();
				bou.write(b);
			}
			bin.close();
			bou.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
	

}
