package com.comtrade.panel.common;

import javax.swing.JPanel;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.w3c.dom.UserDataHandler;

import com.comtrade.commonmethod.CommonMethod;
import com.comtrade.constants.AbsolutePath;
import com.comtrade.constants.Panel_Dimension;
import com.comtrade.constants.URL;
import com.comtrade.controlerClient.ControlerCode;
import com.comtrade.controlerClient.ControlerComboBox;
import com.comtrade.controlerClient.ControlerProperty;
import com.comtrade.controlerClient.ControlerUI;
import com.comtrade.constants.Regular_Expression;
import com.comtrade.constants.TransferClass_Message;
import com.comtrade.constants.Type_OF_Operation_TXT;
import com.comtrade.constants.Type_Of_Data;
import com.comtrade.constants.Type_Of_Operation;
import com.comtrade.constants.Type_Of_Property;
import com.comtrade.domain.GeneralDomain;
import com.comtrade.domain.property.Adress;
import com.comtrade.domain.property.GeoLocation;
import com.comtrade.domain.property.Property;
import com.comtrade.domain.property.Property_Picutre_Album;
import com.comtrade.domain.user.User;
import com.comtrade.domain.user.User_Info;
import com.comtrade.genericClasses.GenericList;
import com.comtrade.panel.admin.Admin_Panel;
import com.comtrade.transfer.TransferClass;
import com.comtrade.view.frame.Application;

import javax.swing.JComboBox;
import javax.swing.JFileChooser;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import com.toedter.calendar.JDayChooser;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class Property_Created extends JPanel {
	private JTextField tfNameOfProperty;
	private JTextField tfAdress_Street;
	private User user;
	private User_Info user_info;
	private JTextField tfAdress_Number;
	private JComboBox cbTypeOfProperty;
	private JTextField tfLatitude;
	private JTextField tfLongitude;
	private JComboBox cbCity,cbCountry;
	private List<List<String>> list = new ArrayList<>();
	private JLabel label_2,label_11,label_3,label_4,label_5;
	private int rating=0;
	private String back;

	/**
	 * Create the panel.
	 */
	public Property_Created(User user,User_Info user_info,String back) {
		this.back=back;
		list=ControlerComboBox.getInstance().fillList();
		setBounds(Panel_Dimension.X.getValue(),Panel_Dimension.Y.getValue(),Panel_Dimension.WIDTH.getValue(),Panel_Dimension.HEIGHT.getValue());
		this.user=user;
		this.user_info=user_info;
		setLayout(null);
		
		JButton btfBackToLog = new JButton("<< <  Step 1");
		btfBackToLog.setBackground(Color.WHITE);
		btfBackToLog.setForeground(Color.RED);
		btfBackToLog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JPanel signUp=new SignUp();
				Application.setPanelOnLayeredPane(signUp);
			}
		});
		btfBackToLog.setFont(new Font("Castellar", Font.BOLD, 14));
		btfBackToLog.setBounds(21, 21, 287, 42);
		CommonMethod.setNewPicutreOnButton(AbsolutePath.absolutePath()+URL.PICTURE_BACK_TO.getValue(),btfBackToLog);
		add(btfBackToLog);
		
		JLabel lblNameOfProperty = new JLabel("Name Of Property *");
		lblNameOfProperty.setFont(new Font("Castellar", Font.BOLD, 12));
		lblNameOfProperty.setBounds(21, 83, 212, 28);
		add(lblNameOfProperty);
		
		tfNameOfProperty = new JTextField();
		tfNameOfProperty.setFont(new Font("Castellar", Font.BOLD, 12));
		tfNameOfProperty.setColumns(10);
		tfNameOfProperty.setBounds(21, 122, 287, 28);
		add(tfNameOfProperty);
		
		JLabel lblTypeOfProperty = new JLabel("Type Of Property *");
		lblTypeOfProperty.setFont(new Font("Castellar", Font.BOLD, 12));
		lblTypeOfProperty.setBounds(21, 153, 212, 28);
		add(lblTypeOfProperty);
		
		cbTypeOfProperty = new JComboBox();
		cbTypeOfProperty.setFont(new Font("Castellar", Font.BOLD, 12));
		cbTypeOfProperty.setBounds(21, 183, 287, 34);
		add(cbTypeOfProperty);
		
		JLabel lblCountry = new JLabel("Country *");
		lblCountry.setFont(new Font("Castellar", Font.BOLD, 12));
		lblCountry.setBounds(21, 228, 212, 28);
		add(lblCountry);
		
		JLabel lblCity = new JLabel(" City *");
		lblCity.setFont(new Font("Castellar", Font.BOLD, 12));
		lblCity.setBounds(21, 294, 212, 28);
		add(lblCity);
		
		JLabel lblAdressstreet = new JLabel("Adress_Street *");
		lblAdressstreet.setFont(new Font("Castellar", Font.BOLD, 12));
		lblAdressstreet.setBounds(21, 362, 212, 13);
		add(lblAdressstreet);
		
		tfAdress_Street = new JTextField();
		tfAdress_Street.setFont(new Font("Castellar", Font.BOLD, 12));
		tfAdress_Street.setColumns(10);
		tfAdress_Street.setBounds(21, 381, 287, 28);
		add(tfAdress_Street);
		
		JLabel lblAdressnumber = new JLabel("Adress_Number *");
		lblAdressnumber.setFont(new Font("Castellar", Font.BOLD, 12));
		lblAdressnumber.setBounds(21, 420, 212, 20);
		add(lblAdressnumber);
		
		tfAdress_Number = new JTextField();
		tfAdress_Number.setFont(new Font("Castellar", Font.BOLD, 12));
		tfAdress_Number.setColumns(10);
		tfAdress_Number.setBounds(21, 450, 287, 28);
		add(tfAdress_Number);
		
		JButton btnSignUpYou = new JButton("Sign Up You \r\n+ Property");
		btnSignUpYou.setBackground(Color.WHITE);
		btnSignUpYou.setFont(new Font("Castellar", Font.BOLD, 10));
		btnSignUpYou.setForeground(Color.RED);
		btnSignUpYou.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name=tfNameOfProperty.getText();
				String type_Of_Property=cbTypeOfProperty.getSelectedItem().toString();
				String Country=cbCountry.getSelectedItem().toString();
				String City=cbCity.getSelectedItem().toString();
				String Adress_Number=tfAdress_Number.getText();
				String Adress_Street=tfAdress_Street.getText();
				String Latitude=tfLatitude.getText();
				String Longitude=tfLongitude.getText();
				boolean testField=fieldValidityProperty(name,Adress_Number,Adress_Street,Latitude,Longitude);
				boolean AllFieldCompleted=FieldComplete(name,Adress_Number,Adress_Street,Latitude,Longitude);
				if(rating != 0) {
					if(testField) {
						if(AllFieldCompleted) {
							JFileChooser fileChooser=new JFileChooser();
							Property property=new Property();
							property.setUser_Username(user.getUsername());
							property.setName(name);
							property.setRating(rating);
							property.setType_Of_Property(type_Of_Property); 
							Adress adress=new Adress();
							adress.setStreet(Adress_Street);
							adress.setCity(City);
							adress.setHouseNumber(Integer.parseInt(Adress_Number));
							adress.setCountry(Country);
							try {
								property.setProperty_code(Admin_Panel.setCode());
								adress.setAdress_code(Admin_Panel.setCode());
								adress.setProperty_code(property.getProperty_code());
							} catch (ClassNotFoundException e3) {
								// TODO Auto-generated catch block
								e3.printStackTrace();
							} catch (IOException e3) {
								// TODO Auto-generated catch block
								e3.printStackTrace();
							}
							GeoLocation geoLocation=new GeoLocation();
							geoLocation.setLatitude(Double.parseDouble(Latitude));
							geoLocation.setLongitude(Double.parseDouble(Longitude));
							geoLocation.setAdress_code(adress.getAdress_code());
							GenericList<GeneralDomain>list=new GenericList<GeneralDomain>();
							list.add(user);
							list.add(user_info);
							list.add(property);
							list.add(adress);
							list.add(geoLocation);
							for(int i=0;i<5;i++) {
								Property_Picutre_Album property_picture_album=new Property_Picutre_Album();
								property_picture_album.setNumber(i+1);
								property_picture_album.setProperty_code(property.getProperty_code());
								property_picture_album.setPicutre_URL(URL.PROFILE_PICTURE_USERS.getValue()+"/"+user.getUsername()+URL.PROFILE_PICTURE_HOTELS.getValue()+"/"+property.getName()+"("+property.getType_Of_Property()+")/"+property_picture_album.getNumber()+".jpg");
								list.add(property_picture_album);
							}
									String url=AbsolutePath.absolutePath()+URL.PROFILE_PICTURE_USERS.getValue()+"/"+user.getUsername()+URL.PROFILE_PICTURE_HOTELS.getValue();
									File userFile=new File(AbsolutePath.absolutePath()+URL.PROFILE_PICTURE_USERS.getValue()+"/"+user.getUsername());
									File userPropertyFile=new File(url);
									if(!userFile.exists() && !userPropertyFile.exists()) {
										userFile.mkdir();
										userPropertyFile.mkdir();
									}
									File propertyFile=new File(url+"/"+name+"("+property.getType_Of_Property()+")");
									if(!propertyFile.exists()) {
										propertyFile.mkdir();
										
									}
									JOptionPane.showMessageDialog(null,"CHOOSE 5 PICTURE FOR YOUR PROPERTY : ");
									Property_Picutre_Album property_picture_album=new Property_Picutre_Album();
									for(int i=5;i<10;i++) {
										property_picture_album=(Property_Picutre_Album) list.get(i);
										fileChooser.showOpenDialog(btnSignUpYou);
										String path=fileChooser.getSelectedFile().getAbsolutePath();
										Admin_Panel.createPictureForServer(path,property,property_picture_album,user);
									}
									if(back.equals("admin")) {
												try {
													list.delete(user);
													list.delete(user_info);
													ControlerUI.getInstance().sendToServer(Type_Of_Operation.REGISTRATION_PROPERTY,Type_Of_Data.PROPERTY, list);
													String message=ControlerProperty.getInstance().getMessage();
													ControlerProperty.getInstance().setNumber(0);
													if(message.equals(TransferClass_Message.SUCCESSFUL_REGISTRATION.getValue())) {
														user.enterDataOnTXTFle(user, Type_OF_Operation_TXT.REGISTRATION_USER_AND_PROPERTY.getValue(), property.getName());
														JOptionPane.showMessageDialog(null,message);
														JPanel admin= new Admin_Panel(user);
														Application.setPanelOnLayeredPane(admin);
														user.enterDataOnTXTFle(user, Type_OF_Operation_TXT.REGISTRATION_NEW.getValue(), property.getName());
													}else if(message.equals(TransferClass_Message.EXCIST_PROPERTY.getValue())){
														userFile.delete();
														userPropertyFile.delete();
														propertyFile.delete();
														JOptionPane.showMessageDialog(null,message);
													}
												} catch (ClassNotFoundException e1) {
													// TODO Auto-generated catch block
													e1.printStackTrace();
												} catch (IOException e1) {
													// TODO Auto-generated catch block
													e1.printStackTrace();
												} catch (URISyntaxException e1) {
													// TODO Auto-generated catch block
													e1.printStackTrace();
												}
										
										
									}else if(back.equals("login")){
										int number=sendVerificationEmail(user_info.getEmail());
										String verificatiopn="We are sent verification code on your email adress!\nPlease Enter your verification code :";
										if(JOptionPane.showInputDialog(null,verificatiopn) != null) {
											
											int answer=Integer.parseInt(JOptionPane.showInputDialog(null,verificatiopn));
											while(answer !=number) {
												JOptionPane.showMessageDialog(null,"INCORECT NUMBER,PLEASE CHECK YOUR EMAIL AGAIN");
												answer=Integer.parseInt(JOptionPane.showInputDialog(null, "We are sent verification code on your email adress!\nPlease Enter your verification code :"));
											}
											try {
												ControlerUI.getInstance().sendToServer(Type_Of_Operation.REGISTRATION_PROPERTY,Type_Of_Data.PROPERTY, list);
												String message=ControlerProperty.getInstance().getMessage();
												ControlerProperty.getInstance().setNumber(0);
												if(message.equals(TransferClass_Message.SUCCESSFUL_REGISTRATION.getValue())) {
													JPanel login=new Login();
													Application.setPanelOnLayeredPane(login);
													user.enterDataOnTXTFle(user, Type_OF_Operation_TXT.REGISTRATION_USER_AND_PROPERTY.getValue(), property.getName());
													JOptionPane.showMessageDialog(null,message);
												}else if(message.equals(TransferClass_Message.EXCIST_PROPERTY.getValue())){
													userFile.delete();
													userPropertyFile.delete();
													propertyFile.delete();
													JOptionPane.showMessageDialog(null,message);
													
												}
											} catch (ClassNotFoundException e2) {
												// TODO Auto-generated catch block
												e2.printStackTrace();
											} catch (IOException e2) {
												// TODO Auto-generated catch block
												e2.printStackTrace();
											}
										
										}	
										
									}
								
							
						}else {
							JOptionPane.showMessageDialog(null,TransferClass_Message.ALL_FIELDS_FILL.getValue());
						}
					}else {
						JOptionPane.showMessageDialog(null,TransferClass_Message.INCORECT_ENTER_DATA.getValue());
					}
				}else {
					JOptionPane.showMessageDialog(null,TransferClass_Message.RATING_NULL.getValue());
				}
				
			}
		});
		btnSignUpYou.setBounds(21, 647, 287, 42);
		CommonMethod.setNewPicutreOnButton(AbsolutePath.absolutePath()+URL.PICTURE_PROPERTY_CREATED.getValue(),btnSignUpYou);
		add(btnSignUpYou);
		
		JLabel label_6 = new JLabel("Example");
		label_6.setForeground(Color.BLACK);
		label_6.setFont(new Font("Castellar", Font.BOLD, 14));
		label_6.setBounds(346, 125, 167, 28);
		add(label_6);
		
		JLabel label_9 = new JLabel("Example Example");
		label_9.setForeground(Color.BLACK);
		label_9.setFont(new Font("Castellar", Font.BOLD, 14));
		label_9.setBounds(346, 375, 177, 28);
		add(label_9);
		
		JLabel label_10 = new JLabel("15");
		label_10.setForeground(Color.BLACK);
		label_10.setFont(new Font("Castellar", Font.BOLD, 14));
		label_10.setBounds(346, 447, 167, 28);
		add(label_10);
		
		 cbCountry = new JComboBox();
		 cbCountry.setFont(new Font("Castellar", Font.BOLD, 12));
		 cbCountry.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent arg0) {
		 		cbCity.removeAllItems();
		 		addItemToCity();
		 	}
		 });
		cbCountry.setBounds(21, 253, 287, 30);
		add(cbCountry);
		
		 cbCity = new JComboBox();
		 cbCity.setFont(new Font("Castellar", Font.BOLD, 12));
		cbCity.setBounds(21, 321, 287, 30);
		add(cbCity);
		
		JLabel lblLatitude = new JLabel("Latitude");
		lblLatitude.setFont(new Font("Castellar", Font.BOLD, 12));
		lblLatitude.setBounds(21, 489, 208, 28);
		add(lblLatitude);
		
		tfLatitude = new JTextField();
		tfLatitude.setFont(new Font("Castellar", Font.BOLD, 12));
		tfLatitude.setColumns(10);
		tfLatitude.setBounds(21, 521, 287, 34);
		add(tfLatitude);
		
		JLabel lblLongitude = new JLabel("Longitude");
		lblLongitude.setFont(new Font("Castellar", Font.BOLD, 12));
		lblLongitude.setBounds(21, 566, 212, 28);
		add(lblLongitude);
		
		label_11 = new JLabel("1");
		label_11.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent arg0) {
				CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+URL.PICTURE_PROPERTY_FULL_STAR.getValue(), label_11);
			}
		});
		label_11.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent arg0) {
				setAllToEmpty();
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				rating=1;
				JOptionPane.showMessageDialog(null,TransferClass_Message.RATING.getValue()+" "+rating);
			}
		});
		label_11.setBounds(443, 637, 56, 52);
		CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+URL.PICTURE_PROPERTY_EMPTY_STAR.getValue(), label_11);
		add(label_11);
		
		label_4 = new JLabel("New label");
		label_4.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+URL.PICTURE_PROPERTY_FULL_STAR.getValue(), label_11);
				CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+URL.PICTURE_PROPERTY_FULL_STAR.getValue(), label_2);
				CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+URL.PICTURE_PROPERTY_FULL_STAR.getValue(), label_3);
				CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+URL.PICTURE_PROPERTY_FULL_STAR.getValue(), label_4);
			}
		});
		label_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				setAllToEmpty();
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				rating=4;
				JOptionPane.showMessageDialog(null,TransferClass_Message.RATING.getValue()+" "+rating);
			}
		});
		label_4.setBounds(656, 637, 56, 52);
		CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+URL.PICTURE_PROPERTY_EMPTY_STAR.getValue(), label_4);
		add(label_4);
		
		label_3 = new JLabel("New label");
		label_3.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+URL.PICTURE_PROPERTY_FULL_STAR.getValue(), label_11);
				CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+URL.PICTURE_PROPERTY_FULL_STAR.getValue(), label_2);
				CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+URL.PICTURE_PROPERTY_FULL_STAR.getValue(), label_3);
				
			}
		});
		label_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				setAllToEmpty();
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				rating=3;
				JOptionPane.showMessageDialog(null,TransferClass_Message.RATING.getValue()+" "+rating);
			}
		});
		label_3.setBounds(582, 637, 56, 52);
		CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+URL.PICTURE_PROPERTY_EMPTY_STAR.getValue(), label_3);
		add(label_3);
		
		label_5 = new JLabel("New label");
		label_5.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+URL.PICTURE_PROPERTY_FULL_STAR.getValue(), label_11);
				CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+URL.PICTURE_PROPERTY_FULL_STAR.getValue(), label_2);
				CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+URL.PICTURE_PROPERTY_FULL_STAR.getValue(), label_3);
				CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+URL.PICTURE_PROPERTY_FULL_STAR.getValue(), label_4);
				CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+URL.PICTURE_PROPERTY_FULL_STAR.getValue(), label_5);
			}
		});
		label_5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				setAllToEmpty();
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				rating=5;
				JOptionPane.showMessageDialog(null,TransferClass_Message.RATING.getValue()+" "+rating);
			}
		});
		label_5.setBounds(733, 637, 56, 52);
		CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+URL.PICTURE_PROPERTY_EMPTY_STAR.getValue(), label_5);
		add(label_5);
		
		JLabel lblNewLabel = new JLabel("RATING");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Castellar", Font.BOLD, 23));
		lblNewLabel.setBounds(443, 582, 346, 44);
		add(lblNewLabel);
		
		label_2 = new JLabel("New label");
		label_2.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+URL.PICTURE_PROPERTY_FULL_STAR.getValue(), label_11);
				CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+URL.PICTURE_PROPERTY_FULL_STAR.getValue(), label_2);
				
			}
		});
		label_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				setAllToEmpty();
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				rating=2;
				JOptionPane.showMessageDialog(null,TransferClass_Message.RATING.getValue()+" "+rating);
			}
		});
		label_2.setBounds(509, 637, 56, 52);
		CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+URL.PICTURE_PROPERTY_EMPTY_STAR.getValue(), label_2);
		add(label_2);
		
		tfLongitude = new JTextField();
		tfLongitude.setFont(new Font("Castellar", Font.BOLD, 12));
		tfLongitude.setColumns(10);
		tfLongitude.setBounds(21, 597, 287, 28);
		add(tfLongitude);
		
		JLabel label = new JLabel("20.4464");
		label.setForeground(Color.BLACK);
		label.setFont(new Font("Castellar", Font.BOLD, 14));
		label.setBounds(346, 527, 167, 28);
		add(label);
		
		JLabel label_1 = new JLabel("40.5567");
		label_1.setForeground(Color.BLACK);
		label_1.setFont(new Font("Castellar", Font.BOLD, 14));
		label_1.setBounds(346, 597, 167, 28);
		add(label_1);
		
		JLabel lblBackground = new JLabel("");
		lblBackground.setForeground(Color.BLACK);
		lblBackground.setBounds(Panel_Dimension.X.getValue(),Panel_Dimension.Y.getValue(),Panel_Dimension.WIDTH.getValue(),Panel_Dimension.HEIGHT.getValue());
		CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+URL.PICTURE_PROPERTY_BACKGROUND.getValue(), lblBackground);
		add(lblBackground);
		addItemAtPropertyComboBox();
		addItemToCountry();
		addItemToCity();
	}
	
	



	protected int sendVerificationEmail(String email) {
		double number=Math.random()*10000000;
		String number1=String.valueOf(Math.round(number));
		Properties prop = new Properties();
        
        prop.put("mail.smtp.auth","true");
        prop.put("mail.smtp.starttls.enable","true");
        prop.put("mail.smtp.host", AbsolutePath.SERVER);
        prop.put("mail.smtp.port","587");
        
        
        Session session = Session.getInstance(prop,new Authenticator() {
        	
        	@Override
        	protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
        		return new javax.mail.PasswordAuthentication(AbsolutePath.username,AbsolutePath.password);
        	}
		});

        Message message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(AbsolutePath.username));
            message.setRecipient( Message.RecipientType.TO,new InternetAddress(email));
            message.setSubject("BOOKING VERIFICATION");
            message.setText("Your verification code is "+ number1 +"\nTHANKS FOR RESERVATION!");
			Transport.send(message);
			System.out.println("Poruka je polsata");
		} catch (MessagingException e) {
			System.out.println("Poruka nije polsata");
			e.printStackTrace();
		}
		return Integer.valueOf(number1);
	}

	protected void setAllToEmpty() {
		CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+URL.PICTURE_PROPERTY_EMPTY_STAR.getValue(), label_11);
		CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+URL.PICTURE_PROPERTY_EMPTY_STAR.getValue(), label_2);
		CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+URL.PICTURE_PROPERTY_EMPTY_STAR.getValue(), label_3);
		CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+URL.PICTURE_PROPERTY_EMPTY_STAR.getValue(), label_4);
		CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+URL.PICTURE_PROPERTY_EMPTY_STAR.getValue(), label_5);
		
	}

	private void addItemToCity() {
		String country=cbCountry.getSelectedItem().toString();
		for(int i=0;i<list.size();i++) {
			if(list.get(i).get(0).equals(country)) {
				for(int j=1;j<list.get(i).size();j++) {
					cbCity.addItem(list.get(i).get(j));
				}
			}
		}
		
	}

	private void addItemToCountry() {
		for(int i=0;i<list.size();i++) {
			cbCountry.addItem(list.get(i).get(0));
		}
		
	}

	private void addItemAtPropertyComboBox() {
		cbTypeOfProperty.addItem(Type_Of_Property.VILLA.getValue());
		cbTypeOfProperty.addItem(Type_Of_Property.HOSTEL.getValue());
		cbTypeOfProperty.addItem(Type_Of_Property.HOTEL.getValue());
		cbTypeOfProperty.addItem(Type_Of_Property.MOTEL.getValue());
	}


	protected boolean FieldComplete(String name, String adress_Number, String adress_Street,String latitude,String longitude) {
		if(name.length()<1 || adress_Street.length()<1 ||  adress_Number.length()<1 || latitude.length() < 1 || longitude.length() < 1) {
			return false;
		}
		return true;
	}


	protected boolean fieldValidityProperty(String name, String adress_Number,
			String adress_Street, String latitude, String longitude) {
		backBorderToGray();
		boolean name1=Pattern.matches(Regular_Expression.ONLY_TEXT_VALUES.getValue(),name);
		boolean adress_Street1=Pattern.matches(Regular_Expression.ADRESS.getValue(),adress_Street);
		boolean adress_Number1=Pattern.matches(Regular_Expression.ONLY_NUMBER_VALUES.getValue(),adress_Number);
		boolean latitude1=Pattern.matches(Regular_Expression.LATITUDE_LONGITUDE.getValue(),latitude);
		boolean longitude1=Pattern.matches(Regular_Expression.LATITUDE_LONGITUDE.getValue(),longitude);
		
		if(name1 && adress_Number1 && adress_Street1) {
			return true;
		}
		
		if(name1==false) {
			tfNameOfProperty.setBorder(BorderFactory.createLineBorder(Color.RED,4));
		}
		
		
		if(adress_Number1==false) {
			tfAdress_Number.setBorder(BorderFactory.createLineBorder(Color.RED,4));
		}
		
		if(adress_Street1==false) {
			tfAdress_Street.setBorder(BorderFactory.createLineBorder(Color.RED,4));
		}
		
		if(latitude1==false) {
			tfLatitude.setBorder(BorderFactory.createLineBorder(Color.RED,4));
		}
		
		if(longitude1==false) {
			tfLongitude.setBorder(BorderFactory.createLineBorder(Color.RED,4));
		}
		
		return false;
	}


	private void backBorderToGray() {
		tfAdress_Number.setBorder(BorderFactory.createLineBorder(Color.GRAY,1));
		tfAdress_Street.setBorder(BorderFactory.createLineBorder(Color.GRAY,1));
		tfNameOfProperty.setBorder(BorderFactory.createLineBorder(Color.GRAY,1));
		tfLatitude.setBorder(BorderFactory.createLineBorder(Color.GRAY,1));
		tfLongitude.setBorder(BorderFactory.createLineBorder(Color.GRAY,1));
		
	}
}
