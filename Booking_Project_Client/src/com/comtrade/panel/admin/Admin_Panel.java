package com.comtrade.panel.admin;

import javax.swing.JPanel;

import com.comtrade.commonmethod.CommonMethod;
import com.comtrade.constants.AbsolutePath;
import com.comtrade.constants.Discount_Contstants;
import com.comtrade.constants.Panel_Dimension;
import com.comtrade.constants.PicturesURL;
import com.comtrade.constants.Regular_Expression;
import com.comtrade.constants.Room_Constants;
import com.comtrade.constants.Threads_Constant;
import com.comtrade.constants.TransferClass_Message;
import com.comtrade.controlerComboBox.ControlerComboBox;
import com.comtrade.controlerKI.ControlerKI;
import com.comtrade.cyrcleList.CyrclularList;
import com.comtrade.domain.GeneralDomain;
import com.comtrade.domain.discount.Discount;
import com.comtrade.domain.property.Adress;
import com.comtrade.domain.property.GeoLocation;
import com.comtrade.domain.property.Property;
import com.comtrade.domain.property.Property_Picutre_Album;
import com.comtrade.domain.room.decorator.ApartmentDecorator;
import com.comtrade.domain.room.decorator.LuxuryRoomDecorator;
import com.comtrade.domain.room.decorator.OrdinaryRoomDecorator;
import com.comtrade.domain.room.decorator.Room_Info_Decorator;
import com.comtrade.domain.user.User;
import com.comtrade.domain.user.User_Info;
import com.comtrade.doman.room.Room;
import com.comtrade.doman.room.Room_Info;
import com.comtrade.genericClasses.GenericList;
import com.comtrade.genericClasses.GenericMap;
import com.comtrade.panel.common.Login;
import com.comtrade.panel.common.Property_Created;
import com.comtrade.panel.user.User_Panel;
import com.comtrade.render.ComboBoxClass;
import com.comtrade.render.Render;
import com.comtrade.transfer.TransferClass;
import com.comtrade.view.frame.Application;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.Image;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;

import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.JFormattedTextField;
import javax.swing.JTabbedPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.Component;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JCalendar;

public class Admin_Panel extends JPanel {

	private User user;
	private int row;
	private int idRooms,Number_of_bed;
	private String Type;
	private double Price_per_night;
	private User_Info user_info;
	private GeoLocation geoLocation;
	private Adress adress;
	private Property property;
	private GenericList<Property_Picutre_Album>listAlbum=new GenericList<Property_Picutre_Album>();
	private TransferClass transferClass=new TransferClass();
	private CyrclularList cyrcleList=new CyrclularList();
	private JLabel lblmainHotelPicture,lblAftermain,lblBeforeMain,lblCountry,lblPicture;
	private JComboBox cbTypeOfRoom;
	private JTextField tfPricePerNight;
	private JTextField tfNumberOfBed;
	private JComboBox cbWhichProperty;
	private String WhichProperty;
	private GenericMap<String,GenericList<GeneralDomain>>map;
	private JTextField tfName;
	private JTextField tfSurname;
	private JTextField tfEmail;
	private JTextField tfMobileNumber;
	private JTextField tfCountry;
	private JTextField tfCity;
	private JTextField tfStreet;
	private JTextField tfNumber;
	private JTextField tfLongitude;
	private JTextField tfLatitude;
	private int counter=0;
	private JTextField tfAmount;
	private JTable tableRooms;
	private JTable tableDiscount;
	private DefaultTableModel dtmRoom=new DefaultTableModel();
	private DefaultTableModel dtmDiscount=new DefaultTableModel();
	private JDateChooser calendarFrom,CalendarTo;
	GenericList<GeneralDomain>listRoom=new GenericList<GeneralDomain>();
	GenericList<GeneralDomain>listDiscount=new GenericList<GeneralDomain>();
	private String idDiscount,date_from,date_to,amount;
	
	
	public Admin_Panel(User user) throws ClassNotFoundException, IOException, URISyntaxException {
		
		this.user=user;
		transferClass=ControlerKI.getInstance().BackUserInfo_ForUser(user);
		this.user_info=(User_Info) transferClass.getServer_Object_Response();
		transferClass=ControlerKI.getInstance().AllAboutProperty(user);
		map=(GenericMap<String, GenericList<GeneralDomain>>) transferClass.getServer_Object_Response();
		
		
		setBounds(Panel_Dimension.X.getValue(),Panel_Dimension.Y.getValue(),1000,713);
		this.user=user;
		setLayout(null);

		
		lblPicture = new JLabel("New label");
		lblPicture.setBounds(10, 11, 129, 110);
		add(lblPicture);
		CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+user_info.getPictureURL(), lblPicture);
		

		lblBeforeMain = new JLabel("New label");
		lblBeforeMain.setBounds(494, 68, 80, 76);
		add(lblBeforeMain);
		
		lblAftermain = new JLabel("New label");
		lblAftermain.setBounds(755, 122, 78, 74);
		add(lblAftermain);
		
		lblmainHotelPicture = new JLabel("Main");
		lblmainHotelPicture.setBounds(584, 68, 161, 128);
		add(lblmainHotelPicture);
		
		
		JButton btnNewButton = new JButton("UPLOAD PICTURE");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				User_Panel.addNewPicture(user_info, user, lblPicture,btnNewButton);
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
		btnNewButton.setBounds(10, 132, 129, 23);
		add(btnNewButton);
		
		
		
		JButton btnNewButton_1 = new JButton("<<<");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+cyrcleList.previous().getPicutre_URL(), lblmainHotelPicture);
				CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+cyrcleList.next().getPicutre_URL(), lblAftermain);
				CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+cyrcleList.previousX2().getPicutre_URL(), lblBeforeMain);
				cyrcleList.next();
			}
		});
		btnNewButton_1.setBounds(494, 212, 57, 23);
		add(btnNewButton_1);
		
		JButton button = new JButton(">>>");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+cyrcleList.next().getPicutre_URL(), lblmainHotelPicture);
				CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+cyrcleList.previous().getPicutre_URL(), lblBeforeMain);
				CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+cyrcleList.nextX2().getPicutre_URL(), lblAftermain);
				cyrcleList.previous();
			}
		});
		button.setBounds(776, 212, 57, 23);
		add(button);
		
		JButton btnNewButton_2 = new JButton("UPLOAD PICTURE");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addNewPicture(cyrcleList.current(),property,lblmainHotelPicture,btnNewButton_2);
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
		btnNewButton_2.setBounds(584, 207, 161, 30);
		add(btnNewButton_2);
		
		lblCountry = new JLabel("New label");
		lblCountry.setBounds(374, 68, 52, 32);
		add(lblCountry);
		
		
		JScrollPane scrollPane = new JScrollPane(tableRooms);
		scrollPane.setBounds(374, 495, 597, 84);
		add(scrollPane);
		
		cbTypeOfRoom = new JComboBox();
		cbTypeOfRoom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(counter>0) {
					EnterRoomInTable(listRoom);
				}
				counter++;
			}
		});
		
		tableRooms = new JTable(dtmRoom);
		tableRooms.setBounds(0, 0, 0, 0);
		scrollPane.setViewportView(tableRooms);
		tableRooms.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				row=tableRooms.getSelectedRow();
				idRooms=Integer.parseInt(tableRooms.getModel().getValueAt(row,0).toString());
				Type=tableRooms.getModel().getValueAt(row,1).toString();
				Price_per_night=Double.parseDouble(tableRooms.getModel().getValueAt(row,2).toString());
				Number_of_bed=Integer.parseInt(tableRooms.getModel().getValueAt(row,3).toString());
				tfPricePerNight.setText(String.valueOf(Price_per_night));
				tfNumberOfBed.setText(String.valueOf(Number_of_bed));
			}
		});
		cbTypeOfRoom.setFont(new Font("Tahoma", Font.BOLD, 12));
		cbTypeOfRoom.setBounds(374, 335, 123, 30);
		add(cbTypeOfRoom);
		
		tfPricePerNight = new JTextField();
		tfPricePerNight.setBounds(374, 454, 123, 30);
		add(tfPricePerNight);
		tfPricePerNight.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Price Per Night");
		lblNewLabel.setBounds(374, 430, 129, 23);
		add(lblNewLabel);
		
		JLabel lblNumberOfBeed = new JLabel("Number of Beed");
		lblNumberOfBeed.setBounds(377, 376, 129, 23);
		add(lblNumberOfBeed);
		
		tfNumberOfBed = new JTextField();
		tfNumberOfBed.setColumns(10);
		tfNumberOfBed.setBounds(374, 395, 123, 30);
		add(tfNumberOfBed);
		
		JButton btnNewButton_4 = new JButton("Enter Rooms");
		btnNewButton_4.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String type=cbTypeOfRoom.getSelectedItem().toString();
				double pricePerNight=Double.parseDouble(tfPricePerNight.getText());
				int numberOfBed=Integer.parseInt(tfNumberOfBed.getText());
				Room room=new Room();
				room.setId_property(property.getId());
				room.setNumber_of_bed(numberOfBed);
				room.setPrice_per_night(pricePerNight);
				Room_Info room_Info=new Room_Info();
				GenericList<GeneralDomain>listRooms=new GenericList<GeneralDomain>();
				if(type.equals(Room_Constants.ORDINARY_ROOM.getValue())) {
					
					room.setType(Room_Constants.ORDINARY_ROOM.getValue());
					Room_Info_Decorator room_info_decorator=new OrdinaryRoomDecorator(room_Info);
					room_Info=room_info_decorator.addnewPropertiesForRoom();
					EnterRoomAndRoomInfo(listRooms,room,room_Info);
					
				}else if(type.equals(Room_Constants.LUXURY_ROOM.getValue())) {
					
					room.setType(Room_Constants.LUXURY_ROOM.getValue());
					Room_Info_Decorator room_info_decorator=new LuxuryRoomDecorator(room_Info);
					room_Info=room_info_decorator.addnewPropertiesForRoom();
					EnterRoomAndRoomInfo(listRooms,room,room_Info);
					
				}else if(type.equals(Room_Constants.APARTMENT.getValue())) {
					
					room.setType(Room_Constants.APARTMENT.getValue());
					Room_Info_Decorator room_info_decorator=new ApartmentDecorator(room_Info);
					room_Info=room_info_decorator.addnewPropertiesForRoom();
					EnterRoomAndRoomInfo(listRooms,room,room_Info);
					
				}
			}
		});
		btnNewButton_4.setBounds(510, 450, 121, 30);
		add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("Update Rooms");
		btnNewButton_5.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Room room=new Room();
				room.setId(idRooms);
				String type=cbTypeOfRoom.getSelectedItem().toString();
				int number_of_bed=Integer.parseInt(tfNumberOfBed.getText());
				double price_per_night=Double.parseDouble(tfPricePerNight.getText());
				room.setNumber_of_bed(number_of_bed);
				room.setPrice_per_night(price_per_night);
				room.setType(type);
				room.setId_property(property.getId());
				GenericList<GeneralDomain>listRooms=new GenericList<GeneralDomain>();
				Room_Info room_Info=new Room_Info();
				if(type.equals(Room_Constants.ORDINARY_ROOM.getValue())) {
					
					Room_Info_Decorator room_info_decorator=new OrdinaryRoomDecorator(room_Info);
					room_Info=room_info_decorator.addnewPropertiesForRoom();
					updateRooms(listRooms,room,room_Info);
					
				}else if(type.equals(Room_Constants.LUXURY_ROOM.getValue())) {
					
					Room_Info_Decorator room_info_decorator=new LuxuryRoomDecorator(room_Info);
					room_Info=room_info_decorator.addnewPropertiesForRoom();
					updateRooms(listRooms,room,room_Info);
					
				}else if(type.equals(Room_Constants.APARTMENT.getValue())) {
					
					Room_Info_Decorator room_info_decorator=new ApartmentDecorator(room_Info);
					room_Info=room_info_decorator.addnewPropertiesForRoom();
					updateRooms(listRooms,room,room_Info);
					
				}
				
			}
		});
		btnNewButton_5.setBounds(510, 395, 121, 30);
		add(btnNewButton_5);
		
		
		
		
		JButton btnUpdateMyInfo = new JButton("UPDATE MY INFO");
		btnUpdateMyInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name=tfName.getText();
				String surname=tfSurname.getText();
				String email=tfEmail.getText();
				String gender=user_info.getGender();
				String pictureUrl=user_info.getPictureURL();
				String mobileNumber=tfMobileNumber.getText();
				boolean allField=allFieldComplete(name,surname,email,mobileNumber);
				boolean testField=testAllField(name,surname,email,mobileNumber);
				if(allField) {
					if(testField) {
						try {
							User_Info user_info=new User_Info();
							user_info.setId_User(user.getId());
							user_info.setName(name);
							user_info.setSurname(surname);
							user_info.setGender(gender);
							user_info.setEmail(email);
							user_info.setPictureURL(pictureUrl);
							user_info.setMobileNumber(mobileNumber);
							TransferClass transferClass=ControlerKI.getInstance().updateUser(user_info);
							String message=transferClass.getMessage();
							JOptionPane.showMessageDialog(null,message);
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
		});
		btnUpdateMyInfo.setBounds(101, 382, 161, 30);
		add(btnUpdateMyInfo);
		
		
		
		JButton btnNewButton_3 = new JButton("ENTER NEW PROPERTY");
		btnNewButton_3.setFont(new Font("Tahoma", Font.PLAIN, 8));
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JPanel propertyJPanel=new Property_Created(user, user_info);
				Application.setPanelOnLayeredPane(propertyJPanel);
				backAllForThisProperty();
			}
		});
		btnNewButton_3.setBounds(155, 673, 123, 29);
		add(btnNewButton_3);
		
		JButton btnNewButton_6 = new JButton("UPDATE PROPERTY");
		btnNewButton_6.setFont(new Font("Tahoma", Font.PLAIN, 8));
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String country=tfCountry.getText();
				String city=tfCity.getText();
				String street=tfStreet.getText();
				String number=tfNumber.getText();
				String latitude=tfLatitude.getText();
				String longitude=tfLongitude.getText();
				boolean allField=allFieldCompleteProperty(country,city,street,number,latitude,longitude);
				boolean testField=testAllFieldProperty(country,city,street,number,latitude,longitude);
				if(allField) {
					if(testField) {
						GenericList<GeneralDomain>list=new GenericList<GeneralDomain>();
						Adress adress1=new Adress();
						adress1.setId_property(property.getId());
						adress1.setCountry(country);
						adress1.setCity(city);
						adress1.setStreet(street);
						adress1.setHouseNumber(Integer.parseInt(number));
						GeoLocation geoLocation=new GeoLocation();
						geoLocation.setId_adress(adress.getId_adress());
						geoLocation.setLatitude(Double.parseDouble(latitude));
						geoLocation.setLongitude(Double.parseDouble(longitude));
						list.add(adress1);
						list.add(geoLocation);
						try {
							TransferClass trnasfClass=ControlerKI.getInstance().updateProperty(list);
							String message=trnasfClass.getMessage();
							JOptionPane.showMessageDialog(null,message);
							((Adress) map.get(WhichProperty).get(1)).setCity(city);
							((Adress) map.get(WhichProperty).get(1)).setStreet(street);
							((Adress) map.get(WhichProperty).get(1)).setCountry(country);
							((Adress) map.get(WhichProperty).get(1)).setHouseNumber(Integer.parseInt(number));
							((GeoLocation) map.get(WhichProperty).get(2)).setLatitude(Double.parseDouble(latitude));
							((GeoLocation) map.get(WhichProperty).get(2)).setLongitude(Double.parseDouble(longitude));
							refreshGlobalVariables();
							refreshPropertyCountryPicture();
						} catch (ClassNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}else {
						JOptionPane.showMessageDialog(null,TransferClass_Message.INCORECT_ENTER_DATA.getValue());
					}
				}else {
					JOptionPane.showMessageDialog(null,TransferClass_Message.ALL_FIELDS_FILL.getValue());
				}
			}
		});
		btnNewButton_6.setBounds(10, 672, 129, 30);
		add(btnNewButton_6);
		cbWhichProperty = new JComboBox();
		cbWhichProperty.setBounds(374, 11, 588, 46);
		cbWhichProperty.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 dtmRoom.setRowCount(0);
				 backAllForThisProperty();
			}
		});
		add(cbWhichProperty);
		
		JLabel lblNewLabel_1 = new JLabel("Name");
		lblNewLabel_1.setBounds(10, 210, 78, 23);
		add(lblNewLabel_1);
		
		tfName = new JTextField();
		tfName.setEnabled(false);
		tfName.setBounds(98, 210, 164, 20);
		add(tfName);
		tfName.setColumns(10);
		addMouseListener(tfName);
		
		JLabel lblSurname = new JLabel("Surname");
		lblSurname.setBounds(10, 251, 78, 23);
		add(lblSurname);
		
		tfSurname = new JTextField();
		tfSurname.setEnabled(false);
		tfSurname.setColumns(10);
		tfSurname.setBounds(98, 252, 164, 20);
		add(tfSurname);
		addMouseListener(tfSurname);
		
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(10, 285, 78, 23);
		add(lblEmail);
		
		tfEmail = new JTextField();
		tfEmail.setEnabled(false);
		tfEmail.setColumns(10);
		tfEmail.setBounds(98, 285, 164, 20);
		add(tfEmail);
		addMouseListener(tfEmail);
		
		
		tfMobileNumber = new JTextField();
		tfMobileNumber.setEnabled(false);
		tfMobileNumber.setColumns(10);
		tfMobileNumber.setBounds(98, 329, 164, 20);
		add(tfMobileNumber);
		addMouseListener(tfMobileNumber);
		
		JLabel lblMobileNumber = new JLabel("Mobile Number");
		lblMobileNumber.setBounds(10, 328, 107, 23);
		add(lblMobileNumber);
		
		JLabel lblCountry_1 = new JLabel("Country");
		lblCountry_1.setBounds(10, 454, 78, 23);
		add(lblCountry_1);
		
		tfCountry = new JTextField();
		tfCountry.setEnabled(false);
		tfCountry.setText((String) null);
		tfCountry.setColumns(10);
		tfCountry.setBounds(98, 454, 164, 20);
		add(tfCountry);
		addMouseListener(tfCountry);
		
		JLabel lblCity = new JLabel("City");
		lblCity.setBounds(10, 495, 78, 23);
		add(lblCity);
		
		tfCity = new JTextField();
		tfCity.setEnabled(false);
		tfCity.setText((String) null);
		tfCity.setColumns(10);
		tfCity.setBounds(98, 496, 164, 20);
		add(tfCity);
		addMouseListener(tfCity);
		
		JLabel lblStreet = new JLabel("Street");
		lblStreet.setBounds(10, 529, 78, 23);
		add(lblStreet);
		
		tfStreet = new JTextField();
		tfStreet.setEnabled(false);
		tfStreet.setText((String) null);
		tfStreet.setColumns(10);
		tfStreet.setBounds(98, 529, 164, 20);
		add(tfStreet);
		addMouseListener(tfStreet);
		
		JLabel lblNumber = new JLabel("Number");
		lblNumber.setBounds(10, 572, 80, 23);
		add(lblNumber);
		
		tfNumber = new JTextField();
		tfNumber.setEnabled(false);
		tfNumber.setText((String) null);
		tfNumber.setColumns(10);
		tfNumber.setBounds(98, 573, 164, 20);
		add(tfNumber);
		addMouseListener(tfNumber);
		
		JLabel Latitude = new JLabel("Latitude");
		Latitude.setBounds(10, 605, 78, 23);
		add(Latitude);
		
		JLabel Longitude = new JLabel("Longitude");
		Longitude.setBounds(10, 639, 80, 23);
		add(Longitude);
		
		tfLongitude = new JTextField();
		tfLongitude.setText((String) null);
		tfLongitude.setEnabled(false);
		tfLongitude.setColumns(10);
		tfLongitude.setBounds(98, 640, 164, 20);
		add(tfLongitude);
		addMouseListener(tfLongitude);
		
		tfLatitude = new JTextField();
		tfLatitude.setText((String) null);
		tfLatitude.setEnabled(false);
		tfLatitude.setColumns(10);
		tfLatitude.setBounds(98, 609, 164, 20);
		add(tfLatitude);
		addMouseListener(tfLatitude);
		
		JScrollPane scrollPane_1 = new JScrollPane(tableDiscount);
		scrollPane_1.setBounds(374, 602, 597, 76);
		add(scrollPane_1);
		
		tableDiscount = new JTable(dtmDiscount);
		scrollPane_1.setViewportView(tableDiscount);
		tableDiscount.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				int rowDiscount=tableDiscount.getSelectedRow();
				idDiscount=tableDiscount.getModel().getValueAt(rowDiscount,0).toString();
				date_from=tableDiscount.getModel().getValueAt(rowDiscount, 1).toString();
				date_to=tableDiscount.getModel().getValueAt(rowDiscount, 2).toString();
				amount=tableDiscount.getModel().getValueAt(rowDiscount, 3).toString();
				tfAmount.setText(amount);
				calendarFrom.setDate(Date.valueOf(date_from));
				CalendarTo.setDate(Date.valueOf(date_to));
				
			}
		});
		
		JButton btnEnterDiscount = new JButton("Enter Discount");
		btnEnterDiscount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SimpleDateFormat sdf=new  SimpleDateFormat("yyyy-MM-dd");
				Date from_date=Date.valueOf(sdf.format(calendarFrom.getDate()));
				Date to_date=Date.valueOf(sdf.format(CalendarTo.getDate()));
				Double amount=Double.parseDouble(tfAmount.getText());
				int idProperty=property.getId();
				if(to_date.compareTo(from_date) > 0) {
					Discount discount=new Discount();
					discount.setId_property(idProperty);
					discount.setFrom_Date(from_date);
					discount.setTo_Date(to_date);
					discount.setAmount_of_dosicount(amount);
					try {
						TransferClass transferClass=ControlerKI.getInstance().enterDiscount(discount);
						String message=transferClass.getMessage();
						JOptionPane.showMessageDialog(null,message);
						clearTFDiscount();
						Discount discount2=(Discount) transferClass.getServer_Object_Response();
						listDiscount.add(discount2);
						EnterDiscountOnTable(listDiscount);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else {
					JOptionPane.showMessageDialog(null,TransferClass_Message.INCORECT_DATE.getValue());
				}
				
			}
		});
		btnEnterDiscount.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btnEnterDiscount.setBounds(729, 444, 104, 30);
		add(btnEnterDiscount);
		
		JButton btnUpdateDiscount = new JButton("Delete Discount");
		btnUpdateDiscount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Discount discount=new Discount();
				discount.setId(Integer.parseInt(idDiscount));
				discount.setAmount_of_dosicount(Double.parseDouble(amount));
				discount.setFrom_Date(Date.valueOf(date_from));
				discount.setTo_Date(Date.valueOf(date_to));
				try {
					TransferClass transferClass=ControlerKI.getInstance().deleteDiscount(discount);
					String message=transferClass.getMessage();
					JOptionPane.showMessageDialog(null,message);
					for(int i=0;i<listDiscount.size();i++) {
						if(listDiscount.get(i) instanceof Discount) {
							Discount discount1=(Discount) listDiscount.get(i);
							if(discount1.getId()==discount.getId()) {
								listDiscount.delete(i);
							}
							EnterDiscountOnTable(listDiscount);
							clearTFDiscount();
						}
					}
					EnterRoomInTable(listRoom);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnUpdateDiscount.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btnUpdateDiscount.setBounds(843, 447, 116, 30);
		add(btnUpdateDiscount);
		
		calendarFrom = new JDateChooser();
		calendarFrom.setDateFormatString("yyyy,MM dd");
		calendarFrom.setBounds(855, 354, 107, 30);
		calendarFrom.setMinSelectableDate(Date.valueOf(LocalDate.now()));
		add(calendarFrom);
		
		CalendarTo = new JDateChooser();
		CalendarTo.setDateFormatString("yyyy,MM dd");
		CalendarTo.setMinSelectableDate(Date.valueOf(LocalDate.now()));
		CalendarTo.setBounds(855, 398, 107, 30);
		add(CalendarTo);
		
		
		tfAmount = new JTextField();
		tfAmount.setBounds(740, 390, 86, 20);
		add(tfAmount);
		tfAmount.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("% of Discount");
		lblNewLabel_2.setBounds(740, 354, 86, 23);
		add(lblNewLabel_2);
		
		JButton btnDeletePicture = new JButton("DELETE PICTURE");
		btnDeletePicture.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!cyrcleList.current().getPicutre_URL().equals(PicturesURL.PROFILE_PICTURE_DEFAULT.getValue()+"/"+property.getClass().getSimpleName()+".jpg")) {
						deletePictureForServer(AbsolutePath.absolutePath()+cyrcleList.current().getPicutre_URL());
						cyrcleList.current().setPicutre_URL(AbsolutePath.absolutePath()+PicturesURL.PROFILE_PICTURE_DEFAULT.getValue()+"/"+property.getClass().getSimpleName()+".jpg");
						String url=cyrcleList.current().getPicutre_URL();
						CommonMethod.setNewPicutreOnLabel(url, lblmainHotelPicture);
						cyrcleList.current().setPicutre_URL(PicturesURL.PROFILE_PICTURE_DEFAULT.getValue()+"/"+property.getClass().getSimpleName()+".jpg");
						try {
							ControlerKI.getInstance().changPictureURLHotel(cyrcleList.current());
						} catch (ClassNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				
			}
		});
		btnDeletePicture.setBounds(584, 251, 161, 30);
		add(btnDeletePicture);
		createColumnForDTM();
		fillcombobox();
		fillCountryComboBox();
		backAllForThisProperty();
		
	}
	
	

	protected void deletePictureForServer(String picutre_URL) {
		File file=new File(picutre_URL);
		file.delete();
		if(file.exists()) {
			file.delete();
			JOptionPane.showMessageDialog(null,TransferClass_Message.SUCCESSFUL_DELETE.getValue());
		}
		
	}



	protected void clearTFDiscount() {
		tfAmount.setText("");
		calendarFrom.setDate(null);
		CalendarTo.setDate(null);
		
	}



	protected void updateRooms(GenericList<GeneralDomain> listRooms1, Room room, Room_Info room_Info) {
		room_Info.setId_room(room.getId());
		listRooms1.add(room);
		listRooms1.add(room_Info);
		try {
			TransferClass transferClass=ControlerKI.getInstance().updateRoom(listRooms1);
			String poruka=transferClass.getMessage();
			JOptionPane.showMessageDialog(null,poruka);
			clearTFRooms();
			for(int i=0;i<listRoom.size();i++) {
				if(listRoom.get(i) instanceof Room) {
					Room room1=(Room) listRoom.get(i);
					Room_Info room_info=(Room_Info) listRoom.get(i+1);
					if(room1.getId()==room.getId()) {
						listRoom.set(i,room);
						listRoom.set(i+1,room_Info);
					}
				}
			}
			EnterRoomInTable(listRoom);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}



	private void clearTFRooms() {
		tfNumberOfBed.setText("");
		tfPricePerNight.setText("");
		
	}



	protected boolean testAllFieldProperty(String country, String city, String street, String number, String latitude,
			String longitude) {
		if( country.length()<1 || city.length()<1 || street.length()<1 || number.length()<1 || latitude.length()<1 || longitude.length()<1) {
			return false;
		}
		return true;
	}



	protected boolean allFieldCompleteProperty(String country, String city, String street, String number, String latitude, String longitude) {
		backBorderToGrayProperty();
		boolean country1=Pattern.matches(Regular_Expression.ADRESS.getValue(),country);
		boolean city1=Pattern.matches(Regular_Expression.ADRESS.getValue(),city);
		boolean street1=Pattern.matches(Regular_Expression.ADRESS.getValue(),street);
		boolean number1=Pattern.matches(Regular_Expression.ONLY_NUMBER_VALUES.getValue(),number);
		boolean latitude1=Pattern.matches(Regular_Expression.LATITUDE_LONGITUDE.getValue(),latitude);
		boolean longitude1=Pattern.matches(Regular_Expression.LATITUDE_LONGITUDE.getValue(),longitude);
		if(country1 && city1 && street1 && number1 && latitude1 && longitude1) {
			return true;
		}
		
		if(country1==false) {
			tfCountry.setBorder(BorderFactory.createLineBorder(Color.RED,4));
		}
		
		if(city1==false) {
			tfCity.setBorder(BorderFactory.createLineBorder(Color.RED,4));
		}
		
		
		if(street1==false) {
			tfStreet.setBorder(BorderFactory.createLineBorder(Color.RED,4));
		}
		
		if(number1==false) {
			tfNumber.setBorder(BorderFactory.createLineBorder(Color.RED,4));
		}
		
		if(latitude1==false) {
			tfLatitude.setBorder(BorderFactory.createLineBorder(Color.RED,4));
		}
		
		if(longitude1==false) {
			tfLongitude.setBorder(BorderFactory.createLineBorder(Color.RED,4));
		}
		return false;
	}



	private void backBorderToGrayProperty() {
		tfCountry.setBorder(BorderFactory.createLineBorder(Color.GRAY,1));
		tfCity.setBorder(BorderFactory.createLineBorder(Color.GRAY,1));
		tfStreet.setBorder(BorderFactory.createLineBorder(Color.GRAY,1));
		tfNumber.setBorder(BorderFactory.createLineBorder(Color.GRAY,1));
		tfLatitude.setBorder(BorderFactory.createLineBorder(Color.GRAY,1));
		tfLongitude.setBorder(BorderFactory.createLineBorder(Color.GRAY,1));
	}



	protected boolean testAllField(String name, String surname, String email, String mobileNumber) {
		backBorderToGray();
		boolean name1=Pattern.matches(Regular_Expression.ONLY_TEXT_VALUES.getValue(),name);
		boolean surname1=Pattern.matches(Regular_Expression.ONLY_TEXT_VALUES.getValue(),surname);
		boolean email1=Pattern.matches(Regular_Expression.EMAIL.getValue(),email);
		boolean mobileNumber1=Pattern.matches(Regular_Expression.PHONE_NUMBER.getValue(),mobileNumber);
		if(name1 && surname1 && email1 && mobileNumber1) {
			return true;
		}
		
		if(name1==false) {
			tfName.setBorder(BorderFactory.createLineBorder(Color.RED,4));
		}
		
		if(surname1==false) {
			tfSurname.setBorder(BorderFactory.createLineBorder(Color.RED,4));
		}
		
		
		if(email1==false) {
			tfEmail.setBorder(BorderFactory.createLineBorder(Color.RED,4));
		}
		
		if(mobileNumber1==false) {
			tfMobileNumber.setBorder(BorderFactory.createLineBorder(Color.RED,4));
		}
		
		return false;
	}



	private void backBorderToGray() {
		tfEmail.setBorder(BorderFactory.createLineBorder(Color.GRAY,1));
		tfName.setBorder(BorderFactory.createLineBorder(Color.GRAY,1));
		tfSurname.setBorder(BorderFactory.createLineBorder(Color.GRAY,1));
		tfMobileNumber.setBorder(BorderFactory.createLineBorder(Color.GRAY,1));
		
	}



	protected boolean allFieldComplete(String name, String surname, String email, String mobileNumber) {
		if( name.length()<1 || surname.length()<1 || email.length()<1 || mobileNumber.length()<1) {
			return false;
		}
		return true;
	}



	private void fillCountryComboBox() {
		GenericList<GeneralDomain>list1=new GenericList<GeneralDomain>();
		Property property=new Property();
		String name;
		for(Map.Entry<String,GenericList<GeneralDomain>> entry : map.entrySet()) {
			list1=entry.getValue();
			property=(Property) list1.get(0);
			name=property.getName().toString();
					cbWhichProperty.addItem(name);
			}
		}




	private void addMouseListener(JTextField tfName2) {
		tfName2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				tfName2.setEnabled(false);
			}
		});
		tfName2.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				tfName2.setEnabled(true);
			}
			
		});
		
	}



	protected void backAllForThisProperty() {
		 refreshGlobalVariables();
		 refreshHotelPicture();
		 refreshPropertyCountryPicture();
		 refreshUserInfoField();
		 refreshHotelInfoField();
		 refreshRoomAndDiscount();
		 EnterRoomInTable(listRoom);
		 EnterDiscountOnTable(listDiscount);
	}




	private void refreshRoomAndDiscount() {
		listRoom=null;
		listRoom=new GenericList<GeneralDomain>();
		listDiscount=null;
		listDiscount=new GenericList<GeneralDomain>();
		int number=map.get(WhichProperty).size();
		for(int i=8;i<number;i++) {
			if(map.get(WhichProperty).get(i) instanceof Room || map.get(WhichProperty).get(i) instanceof Room_Info) {
				listRoom.add(map.get(WhichProperty).get(i));
			}else {
				listDiscount.add(map.get(WhichProperty).get(i));
			}
		}
		
	}



	private void refreshGlobalVariables() {
		 cyrcleList.remove();
		 WhichProperty=cbWhichProperty.getSelectedItem().toString();
		 GenericList<GeneralDomain>list=map.get(WhichProperty);
		 this.property=(Property) list.get(0);
		 this.adress=(Adress) list.get(1);
		 this.geoLocation=(GeoLocation) list.get(2);
		 
		 this.listAlbum.add((Property_Picutre_Album) list.get(3));
		 this.listAlbum.add((Property_Picutre_Album) list.get(4));
		 this.listAlbum.add((Property_Picutre_Album) list.get(5));
		 this.listAlbum.add((Property_Picutre_Album) list.get(6));
		 this.listAlbum.add((Property_Picutre_Album) list.get(7));
		 for(int i=0;i<5;i++) {
		 	cyrcleList.append(listAlbum.get(i));
		 }
		 listAlbum=null;
		 listAlbum=new GenericList<Property_Picutre_Album>();
		
	}



	private void refreshHotelInfoField() {
		tfCountry.setText(adress.getCountry());
		tfCity.setText(adress.getCity());
		tfStreet.setText(adress.getStreet());
		tfNumber.setText(String.valueOf(adress.getHouseNumber()));
		tfLatitude.setText(String.valueOf(geoLocation.getLatitude()));
		tfLongitude.setText(String.valueOf(geoLocation.getLongitude()));
		
	}



	private void refreshUserInfoField() {
		tfName.setText(user_info.getName());
		tfSurname.setText(user_info.getSurname());
		tfEmail.setText(user_info.getEmail());
		tfMobileNumber.setText(user_info.getMobileNumber());
	}



	private void refreshPropertyCountryPicture() {
		CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+PicturesURL.PROFILE_PICTURE_USER_COUNTRYES.getValue()+"/"+adress.getCountry()+".jpg", lblCountry);
		
	}



	private void refreshHotelPicture() {
		CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+cyrcleList.current().getPicutre_URL(),lblmainHotelPicture);
		CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+cyrcleList.next().getPicutre_URL(), lblAftermain);
		CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+cyrcleList.previousX2().getPicutre_URL(), lblBeforeMain);
		cyrcleList.next();
	}



	private void EnterDiscountOnTable(GenericList<GeneralDomain> listDiscount2) {
		Discount discount=new Discount();
		dtmDiscount.setRowCount(0);
		for(int i=0;i<listDiscount2.size();i++) {
			discount=(Discount) listDiscount2.get(i);
			Object[]row= {discount.getId(),discount.getFrom_Date(),discount.getTo_Date(),discount.getAmount_of_dosicount()};
			dtmDiscount.addRow(row);
		}
	}



	private void EnterRoomInTable(GenericList<GeneralDomain> listRoom2) {
		Room room=new Room();
		dtmRoom.setRowCount(0);
		for(int i=0;i<listRoom2.size();i=i+2) {
			room=(Room) listRoom2.get(i);
			if(room.getType().equals(cbTypeOfRoom.getSelectedItem().toString())) {
				Object[]row= {room.getId(),room.getType(),room.getPrice_per_night(),room.getNumber_of_bed()};
				dtmRoom.addRow(row);
			}
		}
		
	}



	protected void EnterRoomAndRoomInfo(GenericList<GeneralDomain> list, Room room, Room_Info room_Info) {
		list.add(room);
		list.add(room_Info);
		try {
			TransferClass transferClass=ControlerKI.getInstance().enterRoomAndRoomInfo(list);
			GenericList<GeneralDomain>listRooms=new GenericList<GeneralDomain>();
			listRooms=(GenericList<GeneralDomain>) transferClass.getServer_Object_Response();
			String message=transferClass.getMessage();
			Room enteredRoom=new Room();
			enteredRoom=(Room) listRooms.get(2);
			JOptionPane.showMessageDialog(null,message);
			if(message.equals(TransferClass_Message.SUCCESSFUL_REGISTRATION.getValue())) {
				map.get(WhichProperty).add(enteredRoom);
				map.get(WhichProperty).add(room_Info);
			}
			clearTFRooms();
			backAllForThisProperty();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	private void fillcombobox() {
		cbTypeOfRoom.addItem(Room_Constants.ORDINARY_ROOM.getValue());
		cbTypeOfRoom.addItem(Room_Constants.LUXURY_ROOM.getValue());
		cbTypeOfRoom.addItem(Room_Constants.APARTMENT.getValue());
	}



	private void createColumnForDTM() {
		Object[]columnsRooms= {
				Room_Constants.ID_ROOM.getValue(),
				Room_Constants.ROOM_TYPE.getValue(),
				Room_Constants.PRICE_PER_NIGHT.getValue(),
				Room_Constants.NUMBER_OF_BEED.getValue()
		  };
		
		dtmRoom.addColumn(columnsRooms[0]);
		dtmRoom.addColumn(columnsRooms[1]);
		dtmRoom.addColumn(columnsRooms[2]);
		dtmRoom.addColumn(columnsRooms[3]);
		
		Object[]columnsDiscount= {
				Discount_Contstants.ID_DISCOUNT.getValue(),
				Discount_Contstants.FROM_DATE.getValue(),
				Discount_Contstants.TO_DATE.getValue(),
				Discount_Contstants.AMOUNT_OF_DISCOUNT.getValue()
		};
		
		dtmDiscount.addColumn(columnsDiscount[0]);
		dtmDiscount.addColumn(columnsDiscount[1]);
		dtmDiscount.addColumn(columnsDiscount[2]);
		dtmDiscount.addColumn(columnsDiscount[3]);
		
	}



	public static void addNewPicture(Property_Picutre_Album property_picutre_album,Property property,JLabel label, JButton btnNewButton_2) {
		JFileChooser fileChooser=new JFileChooser();
		fileChooser.setFileFilter(new FileNameExtensionFilter("png","jpg"));
		fileChooser.showOpenDialog(btnNewButton_2);
		String newPictureURL=fileChooser.getSelectedFile().getAbsolutePath();
		if(newPictureURL.substring(newPictureURL.length()-3,newPictureURL.length()).equals("jpg") ||
		 newPictureURL.substring(newPictureURL.length()-3,newPictureURL.length()).equals("png")	) {
			createPictureForServer(newPictureURL,property,property_picutre_album);
			CommonMethod.setNewPicutreOnLabel(newPictureURL, label);
			property_picutre_album.setPicutre_URL(PicturesURL.PROFILE_PICTURE_HOTELS.getValue()+"/"+property.getType_Of_Property()+"/"+property.getName()+"/"+property_picutre_album.getNumber()+".jpg");
		}
	}
	
	
	public static void createPictureForServer(String newPictureURL,Property property, Property_Picutre_Album property_picutre_album){
		try {
			FileInputStream in=new FileInputStream(newPictureURL);
			FileOutputStream out=new FileOutputStream(AbsolutePath.absolutePath()+PicturesURL.PROFILE_PICTURE_HOTELS.getValue()+"/"+property.getType_Of_Property()+"/"+property.getName()+"/"+property_picutre_album.getNumber()+".jpg");
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
