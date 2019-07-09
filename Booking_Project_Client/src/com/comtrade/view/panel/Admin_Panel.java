package com.comtrade.view.panel;

import javax.swing.JPanel;


import com.comtrade.constants.PicturesURL;
import com.comtrade.controlerKI.ControlerKI;
import com.comtrade.cyrcleList.CyrclularList;
import com.comtrade.domain.GeneralDomain;
import com.comtrade.domain.property.Property;
import com.comtrade.domain.property.Property_Info;
import com.comtrade.domain.property.Property_Picutre_Album;
import com.comtrade.domain.user.User;
import com.comtrade.domain.user.User_Info;
import com.comtrade.genericClasses.GenericList;
import com.comtrade.transfer.TransferClass;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.Image;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Admin_Panel extends JPanel {

	private User user;
	private User_Info user_info;
	private Property property;
	private Property_Info property_Info;
	private GenericList<Property_Picutre_Album>listAlbum=new GenericList<Property_Picutre_Album>();
	
	private TransferClass transferClass=new TransferClass();
	private JLabel lblPicture;
	private JTable table;
	private CyrclularList cyrcleList=new CyrclularList();
	
	private JLabel lblmainHotelPicture;
	private JLabel lblBeforeMain;
	private JLabel lblAftermain;
	private static int counter=0;
	
	public Admin_Panel(User user) throws ClassNotFoundException, IOException {
		this.user=user;
		transferClass=ControlerKI.getInstance().BackUserInfo_ForUser(user);
		this.user_info=(User_Info) transferClass.getServer_Object_Response();
		transferClass=ControlerKI.getInstance().AllAboutProperty(user);
		GenericList<GeneralDomain>list=(GenericList<GeneralDomain>) transferClass.getServer_Object_Response();
		this.property=(Property) list.get(1);
		this.property_Info=(Property_Info) list.get(2);
		listAlbum.add((Property_Picutre_Album) list.get(3));
		listAlbum.add((Property_Picutre_Album) list.get(4));
		listAlbum.add((Property_Picutre_Album) list.get(5));
		listAlbum.add((Property_Picutre_Album) list.get(6));
		listAlbum.add((Property_Picutre_Album) list.get(7));
		for(int i=0;i<5;i++) {
			cyrcleList.append(listAlbum.get(i));
		}
		setBounds(100,100,800,600);
		this.user=user;
		setLayout(null);
		
		lblPicture = new JLabel("New label");
		lblPicture.setBounds(10, 22, 195, 136);
		add(lblPicture);
		

		lblBeforeMain = new JLabel("New label");
		lblBeforeMain.setBounds(329, 81, 92, 92);
		add(lblBeforeMain);
		
		lblAftermain = new JLabel("New label");
		lblAftermain.setBounds(691, 88, 80, 85);
		add(lblAftermain);
		
		lblmainHotelPicture = new JLabel("Main");
		lblmainHotelPicture.setBounds(438, 30, 213, 197);
		add(lblmainHotelPicture);
		
		User_Panel.setNewPicutreOnLabel(user_info.getPictureURL(),lblPicture);
		User_Panel.setNewPicutreOnLabel(cyrcleList.current().getPicutre_URL(), lblmainHotelPicture);
		User_Panel.setNewPicutreOnLabel(cyrcleList.next().getPicutre_URL(), lblAftermain);
		User_Panel.setNewPicutreOnLabel(cyrcleList.previousX2().getPicutre_URL(), lblBeforeMain);
		cyrcleList.next();
		
		
		JButton btnNewButton = new JButton("UPLOAD PICTURE");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				User_Panel.addNewPicture(user_info, user, lblPicture);
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
		btnNewButton.setBounds(10, 169, 195, 23);
		add(btnNewButton);
		
		
		
		JButton btnNewButton_1 = new JButton("<<<");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setNewPicutreOnLabel(cyrcleList.previous().getPicutre_URL(), lblmainHotelPicture);
				setNewPicutreOnLabel(cyrcleList.next().getPicutre_URL(), lblAftermain);
				setNewPicutreOnLabel(cyrcleList.previousX2().getPicutre_URL(), lblBeforeMain);
				cyrcleList.next();
			}
		});
		btnNewButton_1.setBounds(359, 232, 66, 23);
		add(btnNewButton_1);
		
		JButton button = new JButton(">>>");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setNewPicutreOnLabel(cyrcleList.next().getPicutre_URL(), lblmainHotelPicture);
				setNewPicutreOnLabel(cyrcleList.previous().getPicutre_URL(), lblBeforeMain);
				setNewPicutreOnLabel(cyrcleList.nextX2().getPicutre_URL(), lblAftermain);
				cyrcleList.previous();
			}
		});
		button.setBounds(663, 232, 66, 23);
		add(button);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 461, 780, 128);
		add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnNewButton_2 = new JButton("UPLOAD PICTURE");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addNewPicture(cyrcleList.current(),property,lblmainHotelPicture);
				try {
					ControlerKI.getInstance().changPictureURLHotel(cyrcleList.current());
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_2.setBounds(438, 228, 213, 30);
		add(btnNewButton_2);
		
	}
	
	public static void addNewPicture(Property_Picutre_Album property_picutre_album,Property property,JLabel label) {
		String newPictureURL=JOptionPane.showInputDialog("Enter your location of Picture: ");
		newPictureURL.replace('\\','/');
		if(newPictureURL.substring(newPictureURL.length()-3,newPictureURL.length()).equals("jpg") ||
		 newPictureURL.substring(newPictureURL.length()-3,newPictureURL.length()).equals("png")	) {
			createPictureForServer(newPictureURL,property);
			setNewPicutreOnLabel(newPictureURL, label);
			property_picutre_album.setPicutre_URL(PicturesURL.PROFILE_PICTURE_HOTELS.getValue()+"/"+property.getName()+"/"+"HotelPicture"+counter+".jpg");
		}
	}
	
	
	public static void createPictureForServer(String newPictureURL,Property property){
		int counter=0;
		try {
			FileInputStream in=new FileInputStream(newPictureURL);
			FileOutputStream out=new FileOutputStream(PicturesURL.PROFILE_PICTURE_HOTELS.getValue()+"/"+property.getName()+"/"+"HotelPicture"+counter+".jpg");
			BufferedInputStream bin=new BufferedInputStream(in);
			BufferedOutputStream bou=new BufferedOutputStream(out);
			counter++;
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
	
	public static void setNewPicutreOnLabel(String PictureURL, JLabel lblPicture) {
		ImageIcon image= new ImageIcon(PictureURL);
		image.getImage().flush();
		Image image1 = image.getImage();
		Image newImg=image1.getScaledInstance(lblPicture.getWidth(),lblPicture.getHeight(),Image.SCALE_SMOOTH);
		lblPicture.setIcon(new ImageIcon(newImg));
		
	}
}
