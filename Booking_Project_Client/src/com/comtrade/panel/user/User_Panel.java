package com.comtrade.panel.user;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.Date;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Pattern;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import com.comtrade.commonmethod.CommonMethod;
import com.comtrade.compare.CompareRating;
import com.comtrade.constants.AbsolutePath;
import com.comtrade.constants.Panel_Dimension;
import com.comtrade.constants.URL;
import com.comtrade.constants.Regular_Expression;
import com.comtrade.constants.Room_Constants;
import com.comtrade.constants.Server_Information;
import com.comtrade.constants.TransferClass_Message;
import com.comtrade.constants.Type_OF_Operation_TXT;
import com.comtrade.controlerKI.ControlerKI;
import com.comtrade.cyrcleList.CyrclularList;
import com.comtrade.domain.GeneralDomain;
import com.comtrade.domain.discount.Discount;
import com.comtrade.domain.message.Message;
import com.comtrade.domain.property.Adress;
import com.comtrade.domain.property.GeoLocation;
import com.comtrade.domain.property.Property;
import com.comtrade.domain.property.Property_Picutre_Album;
import com.comtrade.domain.user.PaymentUserCard;
import com.comtrade.domain.user.User;
import com.comtrade.domain.user.User_Info;
import com.comtrade.doman.room.Room;
import com.comtrade.doman.room.Room_Info;
import com.comtrade.genericClasses.GenericList;
import com.comtrade.genericClasses.GenericMap;
import com.comtrade.panel.admin.Admin_Panel;
import com.comtrade.panel.common.ChatPanel;
import com.comtrade.panel.common.Login;
import com.comtrade.render.ComboBoxClass;
import com.comtrade.render.RenderCB;
import com.comtrade.reservation.Reservation;
import com.comtrade.threads.ReceivingMessageThread;
import com.comtrade.threads.SendingMessageThread;
import com.comtrade.threads.TimeThread;
import com.comtrade.transfer.TransferClass;
import com.comtrade.view.frame.Application;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.JLayeredPane;
import java.awt.CardLayout;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import com.toedter.calendar.JDateChooser;
import javax.swing.event.AncestorListener;
import javax.swing.event.AncestorEvent;
import javax.swing.JSpinner;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.SpinnerNumberModel;

public class User_Panel extends JPanel {

	private User user;
	private User_Info user_info;
	private Property property;
	private Adress adress;
	private GeoLocation geoLocation;
	private PaymentUserCard payment;
	private GenericList<Property_Picutre_Album>listAlbum=new GenericList<Property_Picutre_Album>();
	private GenericList<GeneralDomain>listRoom=new GenericList<GeneralDomain>();
	private GenericList<GeneralDomain>listDiscount=new GenericList<GeneralDomain>();
	private CyrclularList cyrcleList=new CyrclularList();
	private JButton btnDiscount;
	private TransferClass transferClass=new TransferClass();
	private Message message;
	private GenericMap<String, GenericList<GeneralDomain>>map;
	private GenericList<GeneralDomain>listProperties=new GenericList<GeneralDomain>();
	private DefaultListModel dm=new DefaultListModel();
	private JList<Object> listProperty;
	private JLabel lbl1,lbl2,lbl3,lbl4,lbl5,lblAftermain,lblBeforeMain,lblmainHotelPicture,lblPicture,lblCardPicture ;
	private String whichProperty;
	private JTextField tfSurname;
	private JTextField tfMobileNumber;
	private JTextField tfEmail;
	private JTextField tfName;
	private JTextField tfCountry;
	private JTextField tfCity;
	private JTextField tfStreet;
	private JTextField tfNumber;
	private JTable tableRoom;
	private JLayeredPane layeredPane_1;
	private JComboBox cbTypeOfRoom;
	private static String url;
	private DefaultTableModel dtmRoom=new DefaultTableModel();
	private ButtonGroup bg=new ButtonGroup();
	private JRadioButton rbUserInfo,rbCardInfo,rbMyReservation,rbOffer;
	private JLayeredPane layeredPane;
	private JPanel PanelMyinfo,PanelCardInfo,Offer,MyReservation;
	private JTextField tfCardNumber;
	private JTextField tfExpirationDate;
	private JPanel CreateCard;
	private JComboBox<ComboBoxClass> cbType;
	private JTextField tfCardNumberRegistration;
	private JButton btnNewButton_1;
	private JButton btnAddNewCard ;
	private JLabel label_8;
	private long startTime,EndTime,room_code;
	private int id_room;
	private JSpinner childrenSpinner,aduJSpinner,numberSpinner;
	private JDateChooser checkIn,chckOut;
	private GenericList<GeneralDomain> listReservation;
	private double amount_per_night;
	private JLabel lblNewLabel_3;
	private ButtonGroup bg1=new ButtonGroup();
	
	
	public static String getUrl() {
		return url;
	}


	public static void setUrl(String url) {
		User_Panel.url = url;
	}


	public User_Panel(User user) throws ClassNotFoundException, IOException {
		this.user=user;
		startTime=System.currentTimeMillis();
		user.enterDataOnTXTFle(user,Type_OF_Operation_TXT.LOGIN_USER.getValue(),user.getUsername());
		transferClass=ControlerKI.getInstance().BackUserInfo_ForUser(user);
		this.user_info=(User_Info) transferClass.getServer_Object_Response();
		transferClass=ControlerKI.getInstance().backAllForUserPanel(user);
		map=(GenericMap<String, GenericList<GeneralDomain>>) transferClass.getServer_Object_Response();
		setBounds(Panel_Dimension.X.getValue(),Panel_Dimension.Y.getValue(),Panel_Dimension.WIDTH.getValue(),Panel_Dimension.HEIGHT.getValue());
		setLayout(null);
		listProperties=fillList(map,listProperties);
		JButton btnUpload = new JButton("UPLOAD");
		btnUpload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				User_Panel.addNewPicture(user_info, user, lblPicture, btnUpload);

				try {
					ControlerKI.getInstance().changePictureURLUser(user_info);
					user.enterDataOnTXTFle(user,Type_OF_Operation_TXT.CHANGE_PICTURE_URL_USER.getValue(),user_info.getPictureURL());
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnUpload.setBackground(Color.WHITE);
		btnUpload.setForeground(Color.RED);
		btnUpload.setFont(new Font("Castellar", Font.BOLD, 11));
		btnUpload.setBounds(20, 140, 143, 33);
		CommonMethod.setNewPicutreOnButton(AbsolutePath.absolutePath()+URL.PICTURE_ADMIN_UPLOAD.getValue(), btnUpload);
		add(btnUpload);
		
		JButton btnDelete = new JButton("DELETE");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!url.equals(AbsolutePath.absolutePath()+URL.PROFILE_PICTURE_DEFAULT.getValue()+"/"+user_info.getGender()+".jpg")) {
					try {
						deletePictureForServer(AbsolutePath.absolutePath()+url);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					user_info.setPictureURL(URL.PROFILE_PICTURE_DEFAULT.getValue()+"/"+user_info.getGender()+".jpg");
					CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+URL.PROFILE_PICTURE_DEFAULT.getValue()+"/"+user_info.getGender()+".jpg",lblPicture);
					try {
						ControlerKI.getInstance().changePictureURLUser(user_info);
						user.enterDataOnTXTFle(user,Type_OF_Operation_TXT.DELETE_PICTURE.getValue(),url);
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				User_Panel.setUrl(AbsolutePath.absolutePath()+URL.PROFILE_PICTURE_DEFAULT.getValue()+"/"+user_info.getGender()+".jpg");
			}
		});
		btnDelete.setBackground(Color.WHITE);
		btnDelete.setForeground(Color.RED);
		btnDelete.setFont(new Font("Castellar", Font.BOLD, 11));
		btnDelete.setBounds(20, 184, 143, 32);
		CommonMethod.setNewPicutreOnButton(AbsolutePath.absolutePath()+URL.PICTURE_ADMIN_DELETE.getValue(), btnDelete);
		add(btnDelete);
		
		JLabel lblDate = new JLabel("New label");
		lblDate.setForeground(Color.WHITE);
		lblDate.setBounds(250, 26, 131, 33);
		TimeThread time=new TimeThread(lblDate);
		time.start();
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(374, 430, 261, 264);
		add(scrollPane);
		
		listProperty = new JList<Object>();
		listProperty.setFont(new Font("Tahoma", Font.PLAIN, 9));
		listProperty.setSelectedIndex(0);
		listProperty.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				backAllForThisProperty();
			}
		});
		listProperty.setBorder(BorderFactory.createEtchedBorder());
		scrollPane.setViewportView(listProperty);
		
		lblPicture = new JLabel("New label");
		lblPicture.setBounds(20, 11, 143, 118);
		CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+user_info.getPictureURL(), lblPicture);
		add(lblPicture);
		url=AbsolutePath.absolutePath()+user_info.getPictureURL();
		add(lblDate);
		
		JLabel lblTime = new JLabel("New label");
		lblTime.setBounds(172, 11, 71, 63);
		CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+URL.PICTURE_ADMIN_WATCH.getValue(),lblTime);
		add(lblTime);
		
		lblBeforeMain = new JLabel("");
		lblBeforeMain.setBounds(604, 70, 91, 88);
		add(lblBeforeMain);
		
		lbl1 = new JLabel("New label");
		lbl1.setBounds(604, 11, 54, 48);
		add(lbl1);
		
		lbl2 = new JLabel("New label");
		lbl2.setBounds(680, 11, 54, 48);
		add(lbl2);
		
		lbl5 = new JLabel("New label");
		lbl5.setBounds(888, 11, 54, 48);
		add(lbl5);
		
		lblAftermain = new JLabel("");
		lblAftermain.setBounds(863, 134, 91, 88);
		add(lblAftermain);
		
		lblmainHotelPicture = new JLabel("");
		lblmainHotelPicture.setBounds(705, 86, 148, 134);
		add(lblmainHotelPicture);
		
		lbl4 = new JLabel("New label");
		lbl4.setBounds(824, 11, 54, 48);
		add(lbl4);
		
		lbl3 = new JLabel("New label");
		lbl3.setBounds(753, 11, 54, 48);
		add(lbl3);
		
		JButton button_1 = new JButton("<<<");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+cyrcleList.previous().getPicutre_URL(), lblmainHotelPicture);
				CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+cyrcleList.next().getPicutre_URL(), lblAftermain);
				CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+cyrcleList.previousX2().getPicutre_URL(), lblBeforeMain);
				cyrcleList.next();
			}
		});
		button_1.setForeground(Color.RED);
		button_1.setFont(new Font("Castellar", Font.BOLD, 12));
		button_1.setBackground(Color.WHITE);
		button_1.setBounds(494, 135, 80, 23);
		add(button_1);
		
		JButton button_4 = new JButton(">>>");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+cyrcleList.next().getPicutre_URL(), lblmainHotelPicture);
				CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+cyrcleList.previous().getPicutre_URL(), lblBeforeMain);
				CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+cyrcleList.nextX2().getPicutre_URL(), lblAftermain);
				cyrcleList.previous();
			}
		});
		button_4.setForeground(Color.RED);
		button_4.setFont(new Font("Castellar", Font.BOLD, 12));
		button_4.setBackground(Color.WHITE);
		button_4.setBounds(964, 134, 78, 23);
		add(button_4);
		
		JLabel label_4 = new JLabel("Country");
		label_4.setForeground(Color.BLACK);
		label_4.setFont(new Font("Castellar", Font.BOLD, 14));
		label_4.setBounds(413, 241, 151, 23);
		add(label_4);
		
		JLabel label_5 = new JLabel("City");
		label_5.setForeground(Color.BLACK);
		label_5.setFont(new Font("Castellar", Font.BOLD, 14));
		label_5.setBounds(860, 241, 151, 23);
		add(label_5);
		
		label_8 = new JLabel(" ");
		label_8.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JPanel login=new Login();
				Application.setPanelOnLayeredPane(login);
				EndTime=System.currentTimeMillis();
				long duration=EndTime-startTime;
				try {
					user.enterDataOnTXTFle(user, Type_OF_Operation_TXT.USER_LOGOUT.getValue(),String.valueOf(duration));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		label_8.setForeground(Color.WHITE);
		label_8.setBounds(250, 63, 131, 39);
		CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+URL.PICTURE_ADMIN_LOG_OUT.getValue(), label_8);
		add(label_8);
		
		rbMyReservation = new JRadioButton("MY RESERVATION");
		rbMyReservation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				layeredPane_1.removeAll();
				layeredPane_1.add(MyReservation);
				layeredPane_1.repaint();
				layeredPane_1.revalidate();
				
			}
		});
		rbMyReservation.setBounds(374, 355, 122, 23);
		add(rbMyReservation);
		bg1.add(rbMyReservation);
		
		rbOffer = new JRadioButton("ROOM OFFER");
		rbOffer.setSelected(true);
		rbOffer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				layeredPane_1.removeAll();
				layeredPane_1.add(Offer);
				layeredPane_1.repaint();
				layeredPane_1.revalidate();
			}
		});
		rbOffer.setBounds(526, 355, 109, 23);
		add(rbOffer);
		bg1.add(rbOffer);
		
		tfCountry = new JTextField();
		tfCountry.setEditable(false);
		tfCountry.setText((String) null);
		tfCountry.setColumns(10);
		tfCountry.setBounds(553, 241, 186, 20);
		add(tfCountry);
		
		btnDiscount = new JButton("!!! SEE ALL ABOUT ACTION PRICES !!!");
		btnDiscount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				StringBuffer sb=new StringBuffer();
				sb.append("!!! ACTION ACTION ACTION ACTION ACTION !!!\n");
				sb.append(" \n");
				for(int i=0;i<listDiscount.size();i++) {
					Discount discount=(Discount) listDiscount.get(i);
					sb.append(i+1+")"+" From "+discount.getFrom_Date()+" to "+discount.getTo_Date()+" amount per night of all room in this property is on discount "+discount.getAmount_of_dosicount()+" %\n");
					sb.append(" \n");
					if(i != listDiscount.size()-1) {
						sb.append("+\n");
						sb.append(" \n");
					}
				}
				JOptionPane.showMessageDialog(null,sb);
			}
		});
		
		layeredPane_1 = new JLayeredPane();
		layeredPane_1.setBounds(645, 344, 564, 363);
		layeredPane_1.setLayout(new CardLayout(0, 0));
		layeredPane_1.setBorder(BorderFactory.createEtchedBorder());
		add(layeredPane_1);
		
		Offer = new JPanel();
		layeredPane_1.add(Offer, "name_65696703528696");
		Offer.setBackground(new Color(0,0,0,0));
		Offer.setLayout(null);
		
		JButton btnNewButton_2 = new JButton("BOOK");
		btnNewButton_2.setBounds(394, 309, 157, 39);
		Offer.add(btnNewButton_2);
		btnNewButton_2.setBackground(Color.WHITE);
		btnNewButton_2.setForeground(Color.RED);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SimpleDateFormat sdf=new  SimpleDateFormat("yyyy-MM-dd");
				int idUser=user.getId();
				int id_room1=id_room;
				int numberAdults=Integer.parseInt(aduJSpinner.getValue().toString());
				int numberChildren=Integer.parseInt(childrenSpinner.getValue().toString());
				int number_nights=Integer.parseInt(numberSpinner.getValue().toString());
				Date dateFrom=Date.valueOf(sdf.format(checkIn.getDate()));
				Date dateTo=Date.valueOf(sdf.format(chckOut.getDate()));
				if(numberAdults > 0 && number_nights > 0 && amount_per_night != 0 && id_room != 0) {
					if(dateFrom.compareTo(dateTo)<0) {
						boolean checkReservation=checkReservation(id_room1,dateFrom,dateTo);
						if(checkReservation) {
							double discount=checkDiscountIfExcist(dateFrom,dateTo,amount_per_night,number_nights);
							Reservation reservation=new Reservation();
							reservation.setIdUser(idUser);
							reservation.setIdRoom(id_room1);
							reservation.setCheckIn(dateFrom);
							reservation.setCheckOut(dateTo);
							reservation.setAmount(discount);
							reservation.setNumberAdults(numberAdults);
							reservation.setNumberChildren(numberChildren);
							reservation.setNumberNights(number_nights);
							try {
								TransferClass transferClass=ControlerKI.getInstance().enterReservation(reservation);
								reservation=(Reservation) transferClass.getServer_Object_Response();
								listReservation.add(reservation);
								String message=transferClass.getMessage();
								JOptionPane.showMessageDialog(null, message);
								user.enterDataOnTXTFle(user, Type_OF_Operation_TXT.REGISTRATION_RESERVATION.getValue(),String.valueOf(reservation.getCheckIn()+" "+reservation.getCheckOut()));
								setAllClear();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (ClassNotFoundException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}else {
							JOptionPane.showMessageDialog(null,TransferClass_Message.RESERVED_ROOM.getValue());
						}
						
					}else {
						JOptionPane.showMessageDialog(null,TransferClass_Message.INCORECT_DATE.getValue());
					}
				}else {
					JOptionPane.showMessageDialog(null,TransferClass_Message.INCORECT_ENTER_DATA.getValue());
				}
				
					
			}
		});
		btnNewButton_2.setFont(new Font("Castellar", Font.BOLD, 9));
		CommonMethod.setNewPicutreOnButton(AbsolutePath.absolutePath()+URL.PICTURE_USER_RESERVATION.getValue(), btnNewButton_2);
		
		chckOut = new JDateChooser();
		chckOut.setBounds(394, 260, 157, 24);
		Offer.add(chckOut);
		chckOut.setDateFormatString("yyyy,MM dd");
		chckOut.setMinSelectableDate(Date.valueOf(LocalDate.now()));
		
		checkIn = new JDateChooser();
		checkIn.setBounds(394, 192, 157, 23);
		Offer.add(checkIn);
		checkIn.setDateFormatString("yyyy,MM dd");
		checkIn.setMinSelectableDate(Date.valueOf(LocalDate.now()));
		
		JLabel lblCheckIn = new JLabel("CHECK IN");
		lblCheckIn.setBounds(394, 162, 148, 23);
		Offer.add(lblCheckIn);
		lblCheckIn.setFont(new Font("Castellar", Font.BOLD, 10));
		lblCheckIn.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblNewLabel_2 = new JLabel("CHILDREN");
		lblNewLabel_2.setBounds(461, 7, 86, 33);
		Offer.add(lblNewLabel_2);
		lblNewLabel_2.setFont(new Font("Castellar", Font.BOLD, 10));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblAdults = new JLabel("ADULTS");
		lblAdults.setBounds(394, 7, 71, 32);
		Offer.add(lblAdults);
		lblAdults.setFont(new Font("Castellar", Font.BOLD, 10));
		lblAdults.setHorizontalAlignment(SwingConstants.CENTER);
		
		childrenSpinner = new JSpinner();
		childrenSpinner.setBounds(475, 41, 62, 33);
		Offer.add(childrenSpinner);
		childrenSpinner.setModel(new SpinnerNumberModel(0, 0, 10, 1));
		
		aduJSpinner = new JSpinner();
		aduJSpinner.setBounds(403, 41, 62, 33);
		Offer.add(aduJSpinner);
		aduJSpinner.setModel(new SpinnerNumberModel(0, 0, 10, 1));
		
		JLabel lblCheckOut_1 = new JLabel("CHECK OUT");
		lblCheckOut_1.setBounds(415, 226, 109, 23);
		Offer.add(lblCheckOut_1);
		lblCheckOut_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblCheckOut_1.setFont(new Font("Castellar", Font.BOLD, 9));
		
		JScrollPane scrollPane_1 = new JScrollPane(tableRoom);
		scrollPane_1.setBounds(10, 52, 369, 296);
		Offer.add(scrollPane_1);
		
		tableRoom = new JTable(dtmRoom);
		tableRoom.addMouseListener(new MouseListener() {
			
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
				int row=tableRoom.getSelectedRow();
				room_code=Long.parseLong(tableRoom.getModel().getValueAt(row,0).toString());
				for(int i=0;i<listRoom.size();i=i+2) {
					Room room=(Room) listRoom.get(i);
					if(room_code==room.getRoom_code()) {
						id_room=room.getId();
						amount_per_night=room.getPrice_per_night();
					}
				}
				
				
				
			}
		});
		tableRoom.setBackground(Color.BLUE);
		tableRoom.setFont(new Font("Castellar", Font.BOLD, 12));
		tableRoom.setForeground(Color.WHITE);
		JTableHeader head=tableRoom.getTableHeader();
		head.setBackground(Color.RED);
		head.setForeground(Color.WHITE);
		head.setFocusable(isLightweight());
		scrollPane_1.setViewportView(tableRoom);
		
		cbTypeOfRoom = new JComboBox();
		cbTypeOfRoom.setBounds(11, 11, 173, 30);
		Offer.add(cbTypeOfRoom);
		
		numberSpinner = new JSpinner();
		numberSpinner.setBounds(434, 118, 81, 33);
		Offer.add(numberSpinner);
		numberSpinner.setModel(new SpinnerNumberModel(0, 0, 20, 1));
		
		lblNewLabel_3 = new JLabel("NUMBER NIGHTS");
		lblNewLabel_3.setFont(new Font("Castellar", Font.BOLD, 11));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(394, 85, 157, 22);
		Offer.add(lblNewLabel_3);
		cbTypeOfRoom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EnterRoomInTable(listRoom);
			}
		});
		
		 MyReservation = new JPanel();
		layeredPane_1.add(MyReservation, "name_65696724573042");
		MyReservation.setLayout(null);
		btnDiscount.setFont(new Font("Elephant", Font.PLAIN, 10));
		btnDiscount.setBackground(Color.RED);
		btnDiscount.setForeground(Color.WHITE);
		btnDiscount.setBounds(374, 394, 261, 39);
		add(btnDiscount);
		
		tfCity = new JTextField();
		tfCity.setEditable(false);
		tfCity.setText((String) null);
		tfCity.setColumns(10);
		tfCity.setBounds(1021, 241, 186, 20);
		add(tfCity);
		
		rbUserInfo = new JRadioButton("USER_INFO");
		rbUserInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				layeredPane.removeAll();
				layeredPane.add(PanelMyinfo);
				layeredPane.repaint();
				layeredPane.revalidate();
			}
		});
		
		rbUserInfo.setSelected(true);
		rbUserInfo.setFont(new Font("Castellar", Font.BOLD, 11));
		rbUserInfo.setBounds(20, 250, 109, 23);
		add(rbUserInfo);
		bg.add(rbUserInfo);
		
		rbCardInfo = new JRadioButton("PAYMENT_INFO");
		rbCardInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				layeredPane.removeAll();
				layeredPane.add(PanelCardInfo);
				layeredPane.repaint();
				layeredPane.revalidate();
			}
		});
		
		rbCardInfo.setFont(new Font("Castellar", Font.BOLD, 11));
		rbCardInfo.setBounds(145, 251, 136, 23);
		add(rbCardInfo);
		bg.add(rbCardInfo);
		
		JLabel label_6 = new JLabel("Street");
		label_6.setForeground(Color.BLACK);
		label_6.setFont(new Font("Castellar", Font.BOLD, 14));
		label_6.setBounds(413, 275, 151, 23);
		add(label_6);
		
		tfStreet = new JTextField();
		tfStreet.setEditable(false);
		tfStreet.setText((String) null);
		tfStreet.setColumns(10);
		tfStreet.setBounds(553, 275, 186, 20);
		add(tfStreet);
		
		JLabel label_7 = new JLabel("Number");
		label_7.setForeground(Color.BLACK);
		label_7.setFont(new Font("Castellar", Font.BOLD, 14));
		label_7.setBounds(863, 274, 151, 23);
		add(label_7);
		
		tfNumber = new JTextField();
		tfNumber.setEditable(false);
		tfNumber.setText((String) null);
		tfNumber.setColumns(10);
		tfNumber.setBounds(1021, 275, 186, 20);
		add(tfNumber);
		
		layeredPane = new JLayeredPane();
		layeredPane.setBounds(20, 282, 296, 412);
		layeredPane.setBorder(BorderFactory.createEtchedBorder());
		add(layeredPane);
		layeredPane.setLayout(new CardLayout(0, 0));
		
		PanelMyinfo = new JPanel();
		layeredPane.add(PanelMyinfo, "name_448208450181970");
		PanelMyinfo.setBackground(new Color(0,0,0,0));
		PanelMyinfo.setLayout(null);
		
		tfMobileNumber = new JTextField();
		tfMobileNumber.setBounds(10, 313, 201, 33);
		PanelMyinfo.add(tfMobileNumber);
		tfMobileNumber.setText((String) null);
		tfMobileNumber.setEnabled(false);
		tfMobileNumber.setColumns(10);
		
		tfSurname = new JTextField();
		tfSurname.setBounds(10, 223, 201, 33);
		PanelMyinfo.add(tfSurname);
		tfSurname.setText((String) null);
		tfSurname.setEnabled(false);
		tfSurname.setColumns(10);
		
		tfEmail = new JTextField();
		tfEmail.setBounds(10, 145, 201, 33);
		PanelMyinfo.add(tfEmail);
		tfEmail.setText((String) null);
		tfEmail.setEnabled(false);
		tfEmail.setColumns(10);
		
		tfName = new JTextField();
		tfName.setEditable(false);
		tfName.setEnabled(false);
		tfName.setBounds(10, 55, 201, 36);
		PanelMyinfo.add(tfName);
		tfName.setText((String) null);
		tfName.setColumns(10);
		
		JButton button = new JButton("UPDATE");
		button.setBounds(10, 364, 139, 33);
		PanelMyinfo.add(button);
		button.addActionListener(new ActionListener() {
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
							user.enterDataOnTXTFle(user,Type_OF_Operation_TXT.UPDATE_USER.getValue(),user.getUsername());
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
		button.setForeground(Color.RED);
		button.setFont(new Font("Castellar", Font.BOLD, 10));
		CommonMethod.setNewPicutreOnButton(AbsolutePath.absolutePath()+URL.PICTURE_ADMIN_UPDATE.getValue(), button);
		button.setBackground(Color.WHITE);
		
		JLabel label = new JLabel("Name");
		label.setBounds(10, 21, 161, 23);
		PanelMyinfo.add(label);
		label.setForeground(Color.BLACK);
		label.setFont(new Font("Castellar", Font.BOLD, 14));
		
		JLabel label_2 = new JLabel("Email");
		label_2.setBounds(10, 111, 161, 23);
		PanelMyinfo.add(label_2);
		label_2.setForeground(Color.BLACK);
		label_2.setFont(new Font("Castellar", Font.BOLD, 14));
		
		JLabel label_3 = new JLabel("Mobile Number");
		label_3.setBounds(10, 279, 161, 23);
		PanelMyinfo.add(label_3);
		label_3.setForeground(Color.BLACK);
		label_3.setFont(new Font("Castellar", Font.BOLD, 14));
		
		JLabel label_1 = new JLabel("Surname");
		label_1.setBounds(10, 189, 161, 23);
		PanelMyinfo.add(label_1);
		label_1.setForeground(Color.BLACK);
		label_1.setFont(new Font("Castellar", Font.BOLD, 14));
		tfName.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent arg0) {
				tfName.setEnabled(true);
			}
		});
		tfEmail.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent arg0) {
				tfEmail.setEnabled(true);
			}
		});
		tfEmail.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent arg0) {
				tfEmail.setEnabled(false);
			}
		});
		tfEmail.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent arg0) {
				tfName.setEnabled(false);
			}
		});
		tfSurname.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent arg0) {
				tfSurname.setEnabled(true);
			}
		});
		tfSurname.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent arg0) {
				tfSurname.setEnabled(false);
			}
		});
		tfMobileNumber.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent arg0) {
				tfMobileNumber.setEnabled(true);
			}
		});
		tfMobileNumber.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent arg0) {
				tfMobileNumber.setEnabled(false);
			}
		});
		
		PanelCardInfo = new JPanel();
		layeredPane.add(PanelCardInfo, "name_448208466087212");
		PanelCardInfo.setBackground(new Color(0,0,0,0));
		PanelCardInfo.setLayout(null);
		
		lblCardPicture = new JLabel("");
		lblCardPicture.setBounds(30, 37, 219, 93);
		PanelCardInfo.add(lblCardPicture);
		
		tfCardNumber = new JTextField();
		tfCardNumber.setFont(new Font("Castellar", Font.BOLD, 14));
		tfCardNumber.setHorizontalAlignment(SwingConstants.CENTER);
		tfCardNumber.setEditable(false);
		tfCardNumber.setBounds(30, 180, 219, 36);
		PanelCardInfo.add(tfCardNumber);
		tfCardNumber.setColumns(10);
		
		tfExpirationDate = new JTextField();
		tfExpirationDate.setFont(new Font("Castellar", Font.BOLD, 14));
		tfExpirationDate.setHorizontalAlignment(SwingConstants.CENTER);
		tfExpirationDate.setEditable(false);
		tfExpirationDate.setColumns(10);
		tfExpirationDate.setBounds(30, 274, 219, 36);
		PanelCardInfo.add(tfExpirationDate);
		
		JLabel lblNewLabel = new JLabel("CARD NUMBER");
		lblNewLabel.setFont(new Font("Castellar", Font.BOLD, 11));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(57, 141, 168, 28);
		PanelCardInfo.add(lblNewLabel);
		
		JLabel lblExpirationDate = new JLabel("EXPIRATION DATE");
		lblExpirationDate.setFont(new Font("Castellar", Font.BOLD, 11));
		lblExpirationDate.setHorizontalAlignment(SwingConstants.CENTER);
		lblExpirationDate.setBounds(57, 227, 168, 28);
		PanelCardInfo.add(lblExpirationDate);
		
		btnAddNewCard = new JButton("ADD NEW CARD");
		btnAddNewCard.setForeground(Color.RED);
		btnAddNewCard.setFont(new Font("Castellar", Font.BOLD, 11));
		btnAddNewCard.setBackground(Color.WHITE);
		btnAddNewCard.setBounds(40, 361, 200, 36);
		CommonMethod.setNewPicutreOnButton(AbsolutePath.absolutePath()+URL.PICTURE_ADMIN_CARD.getValue(), btnAddNewCard);
		PanelCardInfo.add(btnAddNewCard);
		
		CreateCard = new JPanel();
		layeredPane.add(CreateCard, "name_450799144050384");
		CreateCard.setLayout(null);
		CreateCard.setBackground(new Color(0,0,0,0));
		btnAddNewCard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				layeredPane.removeAll();
				layeredPane.add(CreateCard);
				layeredPane.repaint();
				layeredPane.revalidate();
			}
		});
		
		cbType = new JComboBox<ComboBoxClass>();
		cbType.setFont(new Font("Castellar", Font.BOLD, 11));
		cbType.setBounds(58, 56, 159, 56);
		CreateCard.add(cbType);
		
		tfCardNumberRegistration = new JTextField();
		tfCardNumberRegistration.setHorizontalAlignment(SwingConstants.CENTER);
		tfCardNumberRegistration.setFont(new Font("Castellar", Font.BOLD, 11));
		tfCardNumberRegistration.setBounds(23, 166, 248, 34);
		CreateCard.add(tfCardNumberRegistration);
		tfCardNumberRegistration.setColumns(10);
		
		JDateChooser dcRegistration = new JDateChooser();
		dcRegistration.setDateFormatString("yyyy,MM dd");
		dcRegistration.getCalendarButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		dcRegistration.setBounds(70, 265, 147, 48);
		dcRegistration.setMinSelectableDate(Date.valueOf(LocalDate.now()));
		CreateCard.add(dcRegistration);
		
		JLabel lblNewLabel_1 = new JLabel("CARD TYPE");
		lblNewLabel_1.setFont(new Font("Castellar", Font.BOLD, 11));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(58, 23, 159, 27);
		CreateCard.add(lblNewLabel_1);
		
		JLabel lblExpirationDate_1 = new JLabel("EXPIRATION DATE");
		lblExpirationDate_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblExpirationDate_1.setFont(new Font("Castellar", Font.BOLD, 11));
		lblExpirationDate_1.setBounds(70, 227, 159, 27);
		CreateCard.add(lblExpirationDate_1);
		
		JLabel label_9 = new JLabel("CARD NUMBER");
		label_9.setFont(new Font("Castellar", Font.BOLD, 11));
		label_9.setHorizontalAlignment(SwingConstants.CENTER);
		label_9.setBounds(58, 123, 159, 27);
		CreateCard.add(label_9);
		
		btnNewButton_1 = new JButton("ADD ");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SimpleDateFormat sdf=new  SimpleDateFormat("yyyy-MM-dd");
				String type=((ComboBoxClass) cbType.getSelectedItem()).getCall();
				String numberCar=tfCardNumberRegistration.getText();
				Date date=Date.valueOf(sdf.format(dcRegistration.getDate()));
				boolean testFiled=testField(numberCar);
				boolean AllFiled=AllField(numberCar);
				if(AllFiled) {
					if(testFiled) {
						GenericList<GeneralDomain>list=new GenericList<GeneralDomain>();
						PaymentUserCard payment=new PaymentUserCard();
						payment.setId_user(user.getId());
						payment.setNumber(Long.parseLong(numberCar));
						payment.setType(type);
						payment.setExpiration_date(date);
						TransferClass transferClass;
						try {
							transferClass = ControlerKI.getInstance().enterPaymentCard(payment);
							String message=transferClass.getMessage();
							JOptionPane.showMessageDialog(null,message);
							layeredPane.removeAll();
							layeredPane.add(PanelCardInfo);
							layeredPane.repaint();
							layeredPane.revalidate();
							setDataOnTF(payment);
							user.enterDataOnTXTFle(user,Type_OF_Operation_TXT.ADD_PAYMENT_CARD.getValue(),String.valueOf(payment.getNumber()));
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
		btnNewButton_1.setBounds(92, 356, 113, 41);
		CommonMethod.setNewPicutreOnButton(AbsolutePath.absolutePath()+URL.PICTURE_ADMIN_CARD.getValue(), btnNewButton_1);
		CreateCard.add(btnNewButton_1);
		
		JLabel lblBackGround = new JLabel("");
		lblBackGround.setFont(new Font("Castellar", Font.BOLD, 9));
		lblBackGround.setBounds(0, 0, 1270, 771);
		CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+URL.PICTURE_USER_BACKGROUND.getValue(), lblBackGround);
		add(lblBackGround);
		//fillRoomComboBox();
		//fillJList();
		//fillCBType();
		putPictureOnLabel();
		backAllForThisProperty();
	}
	

	protected void setAllClear() {
		aduJSpinner.setValue(0);
		childrenSpinner.setValue(0);
		numberSpinner.setValue(0);
		checkIn.setDate(null);
		chckOut.setDate(null);
		
	}


	protected double checkDiscountIfExcist(Date dateFrom, Date dateTo, double amount_per_night2, int number_nights) {
		double amount_of_discount=0;
		double total;
		double amount=amount_per_night2*number_nights;
		for(int i=0;i<listDiscount.size();i++) {
			Discount discount=(Discount) listDiscount.get(i);
			int dateFromCompareCI=dateFrom.compareTo(discount.getFrom_Date());
			int dateToCompareCI=dateFrom.compareTo(discount.getTo_Date());
			int dateFromCompareCO=dateTo.compareTo(discount.getFrom_Date());
			int dateToCompareCO=dateTo.compareTo(discount.getTo_Date());
			if((dateFromCompareCI>=0 && dateToCompareCI<=0) || (dateFromCompareCO>=0 && dateToCompareCO<=0)){
				amount_of_discount+=discount.getAmount_of_dosicount();
			}
		}
		total=((100-amount_of_discount)/100)*amount;
		return total;
	}


	protected boolean checkReservation(int id_room, Date dateFrom, Date dateTo) {
		for(int i=0;i<listReservation.size();i++) {
			Reservation reservation=(Reservation) listReservation.get(i);
			int dateFromCompareCI=dateFrom.compareTo(reservation.getCheckIn());
			int dateToCompareCI=dateFrom.compareTo(reservation.getCheckOut());
			int dateFromCompareCO=dateTo.compareTo(reservation.getCheckIn());
			int dateToCompareCO=dateTo.compareTo(reservation.getCheckOut());
			if(id_room==reservation.getIdRoom() && ((dateFromCompareCI>=0 && dateToCompareCI<=0) || (dateFromCompareCO>=0 && dateToCompareCO<=0))) {
				return false;
			}
		}
		return true;
	}


	protected void setDataOnTF(PaymentUserCard payment) {
		btnAddNewCard.setVisible(false);
		tfExpirationDate.setText(String.valueOf(payment.getExpiration_date()));
		tfCardNumber.setText(String.valueOf(payment.getNumber()));
		CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+URL.PICTURE_USER_CARD.getValue()+"/"+payment.getType()+".png", lblCardPicture);
	}


	protected boolean AllField(String numberCar) {
		backBorderToGrayProperty();
		boolean numberCar1=Pattern.matches(Regular_Expression.ONLY_NUMBER_VALUES.getValue(),numberCar);
		if(numberCar1) {
			return true;
		}
		if(numberCar1==false) {
			tfCardNumberRegistration.setBorder(BorderFactory.createLineBorder(Color.RED,4));
		}
		return false;
	}


	private void backBorderToGrayProperty() {
		tfCardNumberRegistration.setBorder(BorderFactory.createLineBorder(Color.GRAY,1));
		
	}


	protected boolean testField(String numberCar) {
		if(numberCar.length() < 1) {
			return false;
		}
		return true;
	}


	private void fillCBType() {
		cbType.addItem(new ComboBoxClass("DINA",new ImageIcon(AbsolutePath.absolutePath()+URL.PICTURE_USER_CARD_DINA.getValue())));
		cbType.addItem(new ComboBoxClass("VISA",new ImageIcon(AbsolutePath.absolutePath()+URL.PICTURE_USER_CARD_VISA.getValue())));
		cbType.addItem(new ComboBoxClass("MASTER",new ImageIcon(AbsolutePath.absolutePath()+URL.PICTURE_USER_CARD_MASTER.getValue())));
		cbType.setRenderer(new RenderCB());
		
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


	protected void deletePictureForServer(String string) throws IOException {
		File file=new File(string);
		if(file.exists()) {
			file.delete();
			JOptionPane.showMessageDialog(null,TransferClass_Message.SUCCESSFUL_DELETE.getValue());
		}
		user.enterDataOnTXTFle(user,Type_OF_Operation_TXT.DELETE_PICTURE.getValue(),user.getUsername());
		
	}


	private void fillRoomComboBox() {
		Object[]columnsRooms= {
				Room_Constants.ROOM_CODE.getValue(),
				Room_Constants.ROOM_TYPE.getValue(),
				Room_Constants.NUMBER_OF_BEED.getValue(),
				Room_Constants.PRICE_PER_NIGHT.getValue(),
				
		  };
		
		dtmRoom.addColumn(columnsRooms[0]);
		dtmRoom.addColumn(columnsRooms[1]);
		dtmRoom.addColumn(columnsRooms[2]);
		dtmRoom.addColumn(columnsRooms[3]);
		
		
		cbTypeOfRoom.addItem(Room_Constants.ORDINARY_ROOM.getValue());
		cbTypeOfRoom.addItem(Room_Constants.LUXURY_ROOM.getValue());
		cbTypeOfRoom.addItem(Room_Constants.APARTMENT.getValue());
		
	}

	private void backAllForThisProperty() {
		 btnDiscount.setVisible(false);
		 refreshGlobalVariables();
		 refreshHotelPicture();
		 refreshUserInfoField();
		 refreshHotelInfoField();
		 refreshRoomAndDiscount();
		 EnterRoomInTable(listRoom);
		 setRatingToNull();
		 setRating();
	}

	
	private void setRatingToNull() {
		CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+URL.PICTURE_PROPERTY_EMPTY_STAR.getValue(),lbl1);
		CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+URL.PICTURE_PROPERTY_EMPTY_STAR.getValue(),lbl2);
		CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+URL.PICTURE_PROPERTY_EMPTY_STAR.getValue(),lbl3);
		CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+URL.PICTURE_PROPERTY_EMPTY_STAR.getValue(),lbl4);
		CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+URL.PICTURE_PROPERTY_EMPTY_STAR.getValue(),lbl5);
		
	}


	private void setRating() {
		if(property.getRating()==1) {
			CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+URL.PICTURE_PROPERTY_FULL_STAR.getValue(),lbl1);
		}else if(property.getRating()==2) {
			CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+URL.PICTURE_PROPERTY_FULL_STAR.getValue(),lbl1);
			CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+URL.PICTURE_PROPERTY_FULL_STAR.getValue(),lbl2);
		}else if(property.getRating()==3) {
			CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+URL.PICTURE_PROPERTY_FULL_STAR.getValue(),lbl1);
			CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+URL.PICTURE_PROPERTY_FULL_STAR.getValue(),lbl2);
			CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+URL.PICTURE_PROPERTY_FULL_STAR.getValue(),lbl3);
		}else if( property.getRating()==4) {
			CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+URL.PICTURE_PROPERTY_FULL_STAR.getValue(),lbl1);
			CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+URL.PICTURE_PROPERTY_FULL_STAR.getValue(),lbl2);
			CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+URL.PICTURE_PROPERTY_FULL_STAR.getValue(),lbl3);
			CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+URL.PICTURE_PROPERTY_FULL_STAR.getValue(),lbl4);
		}else if(property.getRating()==5) {
			CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+URL.PICTURE_PROPERTY_FULL_STAR.getValue(),lbl1);
			CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+URL.PICTURE_PROPERTY_FULL_STAR.getValue(),lbl2);
			CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+URL.PICTURE_PROPERTY_FULL_STAR.getValue(),lbl3);
			CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+URL.PICTURE_PROPERTY_FULL_STAR.getValue(),lbl4);
			CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+URL.PICTURE_PROPERTY_FULL_STAR.getValue(),lbl5);
		}
		
	}


	private void fillJList() {
		dm.clear();
		Property property1=new Property();
		Adress adress1=new Adress();
		DefaultComboBoxModel dm=new DefaultComboBoxModel<ComboBoxClass>();
		for(int i=0;i<listProperties.size()-1;i=i+2) {
			property1=(Property) listProperties.get(i);
			adress1=(Adress) listProperties.get(i+1);
			String url=AbsolutePath.absolutePath()+URL.PROFILE_PICTURE_USER_COUNTRYES.getValue()+"/"+adress1.getCountry()+".jpg";
			int number=0;
			listDiscount=null;
			listDiscount=new GenericList<GeneralDomain>();
			GenericList<GeneralDomain>list=new GenericList<GeneralDomain>();
			list=map.get(property1.getName());
			for(int k=0;k<list.size();k++) {
				if(list.get(k) instanceof Discount) {
					listDiscount.add(list.get(k));
				}
			}
			for(int j=0;j<listDiscount.size();j++) {
				Discount discount=(Discount) listDiscount.get(j);
				if(property1.getId()==discount.getId_property()) {
					number++;
				}
			}
			if(number>0) {
				dm.addElement(new ComboBoxClass(property1.getName()+" ("+property1.getType_Of_Property()+")  !!!(ACTION PRICES)!!!",new ImageIcon(url)));
			}else if(number==0){
				dm.addElement(new ComboBoxClass(property1.getName()+" ("+property1.getType_Of_Property()+")",new ImageIcon(url)));
			}
			number=0;
			
		}
		//dm.addElement(new ComboBoxClass(null,null));
		listProperty.setCellRenderer(new RenderCB());
		listProperty.setModel(dm);
		listProperty.setSelectedIndex(0);
	}

	
	

	private void EnterRoomInTable(GenericList<GeneralDomain> listRoom2) {
		Room room=new Room();
		dtmRoom.setRowCount(0);
		double discount1=0;
		for(int i=0;i<listRoom2.size();i=i+2) {
			room=(Room) listRoom2.get(i);
			String select=cbTypeOfRoom.getSelectedItem().toString();
			if(room.getType().equals(cbTypeOfRoom.getSelectedItem().toString())) {
					Object[]row= {room.getRoom_code(),room.getType(),room.getNumber_of_bed(),room.getPrice_per_night()};
					dtmRoom.addRow(row);
				
			}
		}
		
	}

	private void refreshRoomAndDiscount() {
		listRoom=null;
		listRoom=new GenericList<GeneralDomain>();
		listDiscount=null;
		listDiscount=new GenericList<GeneralDomain>();
		listReservation=null;
		listReservation=new GenericList<GeneralDomain>();
		int number=map.get(whichProperty).size();
		boolean excistDiscount=false;
		for(int i=8;i<number;i++) {
			if(map.get(whichProperty).get(i) instanceof Room || map.get(whichProperty).get(i) instanceof Room_Info) {
				listRoom.add(map.get(whichProperty).get(i));
			}else if(map.get(whichProperty).get(i) instanceof Discount){
				listDiscount.add(map.get(whichProperty).get(i));
				excistDiscount=true;
			}else if(map.get(whichProperty).get(i) instanceof Reservation) {
				listReservation.add(map.get(whichProperty).get(i));
			}
		}
		if(excistDiscount) {
			btnDiscount.setVisible(true);
		}
		
	}

	private void refreshHotelInfoField() {
		tfCountry.setText(adress.getCountry());
		tfCity.setText(adress.getCity());
		tfStreet.setText(adress.getStreet());
		tfNumber.setText(String.valueOf(adress.getHouseNumber()));
		
	}

	private void refreshUserInfoField() {
		tfName.setText(user_info.getName());
		tfSurname.setText(user_info.getSurname());
		tfEmail.setText(user_info.getEmail());
		tfMobileNumber.setText(user_info.getMobileNumber());
		
	}

	private void refreshHotelPicture() {
		CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+cyrcleList.current().getPicutre_URL(),lblmainHotelPicture);
		CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+cyrcleList.next().getPicutre_URL(), lblAftermain);
		CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+cyrcleList.previousX2().getPicutre_URL(), lblBeforeMain);
		cyrcleList.next();
		
	}

	private void refreshGlobalVariables() {
		 cyrcleList.remove();
		 String value=((ComboBoxClass)listProperty.getSelectedValue()).getCall();
		 whichProperty=value.substring(0,value.indexOf(' '));
		 GenericList<GeneralDomain>list=map.get(whichProperty);
		 
		 this.property=(Property) list.get(0);
		 this.adress=(Adress) list.get(1);
		 this.geoLocation=(GeoLocation) list.get(2);
		 
		 this.listAlbum.add((Property_Picutre_Album) list.get(3));
		 this.listAlbum.add((Property_Picutre_Album) list.get(4));
		 this.listAlbum.add((Property_Picutre_Album) list.get(5));
		 this.listAlbum.add((Property_Picutre_Album) list.get(6));
		 this.listAlbum.add((Property_Picutre_Album) list.get(7));
		 for(int i=0;i<5;i++) {
			 String url=URL.PROFILE_PICTURE_DEFAULT.getValue()+"/"+property.getClass().getSimpleName()+".jpg";
			 if(!listAlbum.get(i).getPicutre_URL().equals(url)) {
				 cyrcleList.append(listAlbum.get(i));
			 }
		 }
		 listAlbum=null;
		 listAlbum=new GenericList<Property_Picutre_Album>();
		 this.payment=(PaymentUserCard) list.get(list.size()-1);
		 setUrl(URL.PROFILE_PICTURE_USERS.getValue()+"/"+user.getUsername()+"/"+"ProfilePicture.jpg");
		 if(payment.getNumber() != 0) {
			 setDataOnTF(payment);
		 }
		
	}

	private void putPictureOnLabel() {
		CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+URL.PICTURE_PROPERTY_EMPTY_STAR.getValue(), lbl1);
		CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+URL.PICTURE_PROPERTY_EMPTY_STAR.getValue(), lbl2);
		CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+URL.PICTURE_PROPERTY_EMPTY_STAR.getValue(), lbl3);
		CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+URL.PICTURE_PROPERTY_EMPTY_STAR.getValue(), lbl4);
		CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+URL.PICTURE_PROPERTY_EMPTY_STAR.getValue(), lbl5);
		
	}


	


	private GenericList<GeneralDomain> fillList(GenericMap<String, GenericList<GeneralDomain>> map2, GenericList<GeneralDomain> listProperties2) {
		GenericList<GeneralDomain>list1=new GenericList<GeneralDomain>();
		Property property=new Property();
		Adress adress1=new Adress();
		String name;
		for(Map.Entry<String,GenericList<GeneralDomain>> entry : map.entrySet()) {
				list1=(GenericList<GeneralDomain>) entry.getValue();
				property=(Property) list1.get(0);
				adress1=(Adress) list1.get(1);
				listProperties2.add(property);
				listProperties2.add(adress1);
			}
			return sort(listProperties2);
		}
		


	private GenericList<GeneralDomain> sort(GenericList<GeneralDomain> listProperties2) {
		for(int i=0;i<listProperties2.size();i=i+2) {
			for(int j=i+2;j<listProperties2.size();j=j+2) {
				Property p1=(Property) listProperties2.get(i);
				Adress a1=(Adress) listProperties2.get(i+1);
				Property p2=(Property) listProperties2.get(j);
				Adress a2=(Adress) listProperties2.get(j+1);
				if(p1.getRating() < p2.getRating()) {
					Property temp= p1;
					Adress temp2=a1;
					listProperties2.set(i,listProperties2.get(j));
					listProperties2.set(i+1,listProperties2.get(j+1));
					listProperties2.set(j,temp);
					listProperties2.set(j+1,temp2);
				}
			}
		}
		return listProperties2;
	}


	public static void addNewPicture(User_Info user_info,User user,JLabel label, JButton btnNewButton) {
		JFileChooser fileChooser=new JFileChooser();
		fileChooser.showOpenDialog(btnNewButton);
		String newPictureURL=fileChooser.getSelectedFile().getAbsolutePath();
		if(newPictureURL.substring(newPictureURL.length()-3,newPictureURL.length()).equals("jpg") ||
		 newPictureURL.substring(newPictureURL.length()-3,newPictureURL.length()).equals("png")	) {
			createPictureForServer(newPictureURL,user);
			CommonMethod.setNewPicutreOnLabel(newPictureURL, label);
			user_info.setPictureURL(URL.PROFILE_PICTURE_USERS.getValue()+"/"+user.getUsername()+"/"+"ProfilePicture.jpg");
			if(user.getStatus().equals("admin")) {
				Admin_Panel.setUrl(URL.PROFILE_PICTURE_USERS.getValue()+"/"+user.getUsername()+"/"+"ProfilePicture.jpg");
			}else {
				setUrl(URL.PROFILE_PICTURE_USERS.getValue()+"/"+user.getUsername()+"/"+"ProfilePicture.jpg");
			}
			
		}
	}
	
	
	public static void createPictureForServer(String newPictureURL,User user){
		try {
			FileInputStream in=new FileInputStream(newPictureURL);
			FileOutputStream out=new FileOutputStream(AbsolutePath.absolutePath()+URL.PROFILE_PICTURE_USERS.getValue()+"/"+user.getUsername()+"/"+"ProfilePicture.jpg");
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
