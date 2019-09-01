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
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;
import java.sql.Date;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.Observer;
import java.util.regex.Pattern;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableRowSorter;

import com.comtrade.commonmethod.CommonMethod;
import com.comtrade.constants.AbsolutePath;
import com.comtrade.constants.CountryesTxt;
import com.comtrade.constants.Panel_Dimension;
import com.comtrade.constants.URLS;
import com.comtrade.controlerClient.ControlerDiscount;
import com.comtrade.controlerClient.ControlerMessage;
import com.comtrade.controlerClient.ControlerProperty;
import com.comtrade.controlerClient.ControlerRating;
import com.comtrade.controlerClient.ControlerReservation;
import com.comtrade.controlerClient.ControlerRoom;
import com.comtrade.controlerClient.ControlerUI;
import com.comtrade.controlerClient.ControlerUser;
import com.comtrade.constants.Regular_Expression;
import com.comtrade.constants.Reservation_Constant;
import com.comtrade.constants.Room_Constants;
import com.comtrade.constants.Server_Information;
import com.comtrade.constants.TransferClass_Message;
import com.comtrade.constants.Type_OF_Operation_TXT;
import com.comtrade.constants.Type_Of_Data;
import com.comtrade.constants.Type_Of_Operation;
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
import com.comtrade.domain.user.PaymentUserCard;
import com.comtrade.domain.user.User;
import com.comtrade.domain.user.User_Info;
import com.comtrade.doman.room.Room;
import com.comtrade.doman.room.Room_Info;
import com.comtrade.genericClasses.GenericList;
import com.comtrade.genericClasses.GenericMap;
import com.comtrade.message.Message;
import com.comtrade.panel.admin.Admin_Panel;
import com.comtrade.panel.common.Login;
import com.comtrade.rating.Client_Rating;
import com.comtrade.render.ComboBoxClass;
import com.comtrade.render.RenderCB;
import com.comtrade.reservation.Reservation;
import com.comtrade.threads.ClientThreadResponse;
import com.comtrade.threads.TimeThread;
import com.comtrade.transfer.TransferClass;
import com.comtrade.view.frame.Application;
import com.comtrade.view.frame.Rating;
import com.comtrade.view.frame.RoomType;

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
import java.awt.Container;
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
import javax.swing.border.EtchedBorder;
import javax.swing.JTextArea;

public class User_Panel extends JPanel {

	private boolean backForAll=true;
	private volatile User userGlobal;
	private volatile User_Info user_info;
	private volatile User chatUser;
	private volatile User_Info chatUser_info;
	private volatile Property property;
	private volatile Adress adress;
	private volatile GeoLocation geoLocation;
	private volatile PaymentUserCard paymentGlobal;
	private volatile String whichProperty;
	private JComboBox cbTypeOfRoom;
	private JComboBox<ComboBoxClass> cbType;
	private static String url;
	private int Code;
	private int room_code;
	private int number_of_bed;
	private double amount_per_night;
	private JTextArea textArea;
	
	private Date CurrentDate=Date.valueOf(LocalDate.now());
	private TransferClass transferClass=new TransferClass();
	
	private GenericList<Property_Picutre_Album>listAlbum=new GenericList<Property_Picutre_Album>();
	private GenericList<GeneralDomain>listRoom=new GenericList<GeneralDomain>();
	private GenericList<GeneralDomain>listDiscount=new GenericList<GeneralDomain>();
	private GenericList<GeneralDomain> listRating=new GenericList<GeneralDomain>();
	private GenericList<GeneralDomain> listReservation=new GenericList<GeneralDomain>();
	private CyrclularList cyrcleList=new CyrclularList();
	private Map<String,Double>mapRating=new HashMap<>();
	private Map<String,StringBuffer>chatMap=new HashMap<>();
	
	private GenericMap<String, GenericList<GeneralDomain>>map;
	private GenericList<GeneralDomain>listProperties=new GenericList<GeneralDomain>();
	private DefaultTableModel dtmRoom=new DefaultTableModel();
	private DefaultTableModel dtmReservation=new DefaultTableModel();
	private DefaultListModel dm=new DefaultListModel();
	private JList<Object> listProperty;
	
	private long startTime,EndTime;
	
	private JTable tableRoom,tableReservation;
	
	private JLayeredPane layeredPane_1,layeredPane,layeredPane_Location;
	
	private JLabel lbl1,lbl2,lbl3,lbl4,lbl5,lblAftermain,lblBeforeMain,lblmainHotelPicture,lblPicture,lblCardPicture,lblChatPicture,lblUsernameChat, label_8;
	
	private JTextField tfSurname,tfMobileNumber,tfEmail,tfName,tfCountry,tfCity,tfStreet,tfNumber,tfCardNumber, tfExpirationDate,tfCardNumberRegistration, tfMessage;
	
	private JPanel PanelMyinfo,PanelCardInfo,Offer,MyReservation,PanelPictures,PanelLocation,CreateCard,ChatPanel;
	
	private JRadioButton rbUserInfo,rbCardInfo,rbMyReservation,rbOffer;
	
	private JSpinner childrenSpinner,aduJSpinner;
	
	private JDateChooser checkIn,chckOut;
	
	private Date reservedFromDate,reservedToDate,Check_in,Check_out;
	
	private JRadioButton rbPictures,rbLocation,rbChat;
	
	private JButton btnDiscount,btnNewButton_1,btnAddNewCard,btnCancelReservation,btnDeactivate;
	
	private volatile JButton btnNewButton_3;
	
	private ButtonGroup bg1=new ButtonGroup();
	private ButtonGroup bg2=new ButtonGroup();
	private ButtonGroup bg=new ButtonGroup();
	private JLabel lblLocation;
	
	
	
	
	public static String getUrl() {
		return url;
	}


	public static void setUrl(String url) {
		User_Panel.url = url;
	}


	public User_Panel(User user) throws ClassNotFoundException, IOException {
		this.userGlobal=user;
		startTime=System.currentTimeMillis();
		user.enterDataOnTXTFle(user,Type_OF_Operation_TXT.LOGIN_USER.getValue(),user.getUsername());
		ControlerUI.getInstance().sendToServer(Type_Of_Operation.BACK_ALL_FOR_USER_PANEL,Type_Of_Data.PROPERTY,user);
		map=ControlerProperty.getInstance().getMap();
		ControlerProperty.getInstance().setNumber(0);
		this.user_info=(User_Info) map.get(user.getUsername()).get(1);
		setBounds(Panel_Dimension.X.getValue(),Panel_Dimension.Y.getValue(),Panel_Dimension.WIDTH.getValue(),Panel_Dimension.HEIGHT.getValue());
		setLayout(null);
		listProperties=fillList(map,listProperties);
		JButton btnUpload = new JButton("UPLOAD");
		btnUpload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GenericList<GeneralDomain>list=new GenericList<GeneralDomain>();	
				User_Panel.addNewPicture(user_info, user, lblPicture, btnUpload);
				list.add(user);
				list.add(user_info);
				try {
					ControlerUI.getInstance().sendToServer(Type_Of_Operation.CHANGE_PICTURE_URL_USER,Type_Of_Data.USER, list);
					User_Info user_info1=ControlerUser.getInstance().getUser_info();
					String URL=user_info1.getPictureURL();
					String message=ControlerUser.getInstance().getMessage();
					ControlerUser.getInstance().setNumber(0);
					((User_Info) map.get(user.getUsername()).get(1)).setPictureURL(URL);
					if(message != null) {
						JOptionPane.showMessageDialog(null,message);
					}
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
		CommonMethod.setNewPicutreOnButton(AbsolutePath.absolutePath()+URLS.PICTURE_ADMIN_UPLOAD.getValue(), btnUpload);
		add(btnUpload);
		
		JButton btnDelete = new JButton("DELETE");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!url.equals(AbsolutePath.absolutePath()+URLS.PROFILE_PICTURE_DEFAULT.getValue()+"/"+user_info.getGender()+".jpg")) {
					try {
						deletePictureForServer(AbsolutePath.absolutePath()+url);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					user_info.setPictureURL(URLS.PROFILE_PICTURE_DEFAULT.getValue()+"/"+user_info.getGender()+".jpg");
					CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+URLS.PROFILE_PICTURE_DEFAULT.getValue()+"/"+user_info.getGender()+".jpg",lblPicture);
					try {
						GenericList<GeneralDomain>list=new GenericList<GeneralDomain>();
						list.add(user);
						list.add(user_info);
						ControlerUI.getInstance().sendToServer(Type_Of_Operation.CHANGE_PICTURE_URL_USER,Type_Of_Data.USER, list);
						
						String URL=ControlerUser.getInstance().getUser_info().getPictureURL();
						String message=ControlerUser.getInstance().getMessage();
						ControlerUser.getInstance().setNumber(0);
						
						((User_Info) map.get(user.getUsername()).get(1)).setPictureURL(URL);
						if(message != null) {
							JOptionPane.showMessageDialog(null,message);
						}
						user.enterDataOnTXTFle(user,Type_OF_Operation_TXT.DELETE_PICTURE.getValue(),url);
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				User_Panel.setUrl(AbsolutePath.absolutePath()+URLS.PROFILE_PICTURE_DEFAULT.getValue()+"/"+user_info.getGender()+".jpg");
			}
		});
		btnDelete.setBackground(Color.WHITE);
		btnDelete.setForeground(Color.RED);
		btnDelete.setFont(new Font("Castellar", Font.BOLD, 11));
		btnDelete.setBounds(20, 184, 143, 32);
		CommonMethod.setNewPicutreOnButton(AbsolutePath.absolutePath()+URLS.PICTURE_ADMIN_DELETE.getValue(), btnDelete);
		add(btnDelete);
		
		JLabel lblDate = new JLabel("New label");
		lblDate.setForeground(Color.WHITE);
		lblDate.setBounds(250, 26, 131, 33);
		TimeThread time=new TimeThread(lblDate);
		time.start();
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(374, 430, 261, 277);
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
		CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+URLS.PICTURE_ADMIN_WATCH.getValue(),lblTime);
		add(lblTime);
		
		JLabel label_4 = new JLabel("Country");
		label_4.setForeground(Color.BLACK);
		label_4.setFont(new Font("Castellar", Font.BOLD, 14));
		label_4.setBounds(477, 240, 161, 23);
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
					GenericList<GeneralDomain>list=new GenericList<GeneralDomain>();
					Message message=new Message();
					message.setUser(chatUser);
					message.setMessage(null);
					list.add(message);
					list.add(user);
					user.enterDataOnTXTFle(user, Type_OF_Operation_TXT.USER_LOGOUT.getValue(),String.valueOf(duration));
					ControlerUI.getInstance().sendToServer(Type_Of_Operation.DELETE_FROM_ADMIN_CHAT, Type_Of_Data.MESSAGE, list);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
			}
		});
		label_8.setForeground(Color.WHITE);
		label_8.setBounds(250, 63, 131, 39);
		CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+URLS.PICTURE_ADMIN_LOG_OUT.getValue(), label_8);
		add(label_8);
		
		rbMyReservation = new JRadioButton("MY RESERVATION");
		rbMyReservation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				setPanelOnLayeredPane(layeredPane_1,MyReservation);
				chechIfExcistNoRatingReservation();
				
			}
		});
		
		rbPictures = new JRadioButton("PICTURES");
		rbPictures.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				setPanelOnLayeredPane(layeredPane_Location,PanelPictures);
				
			}
		});
		rbPictures.setSelected(true);
		rbPictures.setBounds(799, 7, 96, 20);
		add(rbPictures);
		bg2.add(rbPictures);
		rbLocation = new JRadioButton("LOCATION");
		rbLocation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				setPanelOnLayeredPane(layeredPane_Location,PanelLocation);
				
			}
		});
		rbLocation.setBounds(657, 7, 96, 20);
		add(rbLocation);
		bg2.add(rbLocation);
		rbMyReservation.setBounds(374, 355, 150, 23);
		add(rbMyReservation);
		bg1.add(rbMyReservation);
		
		rbOffer = new JRadioButton("ROOM OFFER");
		rbOffer.setSelected(true);
		rbOffer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				setPanelOnLayeredPane(layeredPane_1,Offer);
				
			}
		});
		rbOffer.setBounds(526, 355, 109, 23);
		add(rbOffer);
		bg1.add(rbOffer);
		
		tfCountry = new JTextField();
		tfCountry.setEditable(false);
		tfCountry.setText((String) null);
		tfCountry.setColumns(10);
		tfCountry.setBounds(627, 240, 186, 20);
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
				setPanelOnLayeredPane(layeredPane,PanelCardInfo);
				SimpleDateFormat sdf=new  SimpleDateFormat("yyyy-MM-dd");
				int idUser=user.getId();
				int code=room_code;
				int numberAdults=Integer.parseInt(aduJSpinner.getValue().toString());
				int numberChildren=Integer.parseInt(childrenSpinner.getValue().toString());
				if(checkIn.getDate() != null && chckOut.getDate() != null ) {
					Date dateFrom=Date.valueOf(sdf.format(checkIn.getDate()));
					Date dateTo=Date.valueOf(sdf.format(chckOut.getDate()));
					long numberN=Math.abs(dateTo.getTime() - dateFrom.getTime());
					long diff = TimeUnit.DAYS.convert(numberN, TimeUnit.MILLISECONDS);
					int number_nights=Integer.parseInt(String.valueOf(diff));
					if(numberAdults > 0 && number_nights > 0 && amount_per_night != 0 && room_code != 0) {
						boolean checkNumber=checkNumberofChildrenAndAdults(number_of_bed,numberAdults,numberChildren);
						if(checkNumber) {
							if(dateFrom.compareTo(dateTo)<0) {
								boolean checkReservation=checkReservation(room_code,dateFrom,dateTo);
								String reservatedText="(FROM "+reservedFromDate+" TO "+reservedToDate+")";
								if(checkReservation) {
									boolean checkPaymentCard=checkPaymentCard();
									if(checkPaymentCard) {
										double discount=checkDiscountIfExcist(dateFrom,dateTo,amount_per_night,number_nights);
										Reservation reservation=new Reservation();
										reservation.setUser_Username(user.getUsername());
										reservation.setRoom_code(room_code);
										reservation.setCheckIn(dateFrom);
										reservation.setCheckOut(dateTo);
										reservation.setAmount(discount);
										reservation.setNumberAdults(numberAdults);
										reservation.setNumberChildren(numberChildren);
										reservation.setNumberNights(number_nights);
										try {
											reservation.setReservation_code(Admin_Panel.setCode());
										} catch (ClassNotFoundException e1) {
											// TODO Auto-generated catch block
											e1.printStackTrace();
										} catch (IOException e1) {
											// TODO Auto-generated catch block
											e1.printStackTrace();
										}
										try {
											GenericList< GeneralDomain>list=new GenericList<GeneralDomain>();
											list.add(property);
											list.add(reservation);
											list.add(userGlobal);
											list.add(map.get(userGlobal.getUsername()).get(1));
											ControlerUI.getInstance().sendToServer(Type_Of_Operation.ADD_NEW_REGISTRATION, Type_Of_Data.RESERVATION,list);
											reservation=(Reservation) ControlerReservation.getInstance().getReservation();
											String message=ControlerReservation.getInstance().getMessage();
											ControlerReservation.getInstance().setNumber(0);
											if(message != null) {
												JOptionPane.showMessageDialog(null,message);
											}
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
										JOptionPane.showMessageDialog(null,TransferClass_Message.ACTIVATE_PAYMENT_CARD.getValue());
									}
								}else {
									JOptionPane.showMessageDialog(null,TransferClass_Message.RESERVED_ROOM.getValue()+"\n"+reservatedText);
								}
								
							}else {
								JOptionPane.showMessageDialog(null,TransferClass_Message.INCORECT_DATE.getValue());
							}
						}else {
							JOptionPane.showMessageDialog(null,TransferClass_Message.INCORECT_NUMBER_BED.getValue());
						}
					}else {
						JOptionPane.showMessageDialog(null,TransferClass_Message.SELECT_ROOM.getValue());
					}
				}else {
					JOptionPane.showMessageDialog(null,TransferClass_Message.SET_CHECKIN_AND_CHCK_OUT.getValue());
				}
				
					
			
					
			}
		});
		btnNewButton_2.setFont(new Font("Castellar", Font.BOLD, 9));
		CommonMethod.setNewPicutreOnButton(AbsolutePath.absolutePath()+URLS.PICTURE_USER_RESERVATION.getValue(), btnNewButton_2);
		
		chckOut = new JDateChooser();
		chckOut.setBounds(394, 225, 157, 24);
		Offer.add(chckOut);
		chckOut.setDateFormatString("yyyy,MM dd");
		chckOut.setMinSelectableDate(Date.valueOf(LocalDate.now()));
		chckOut.setMaxSelectableDate(Date.valueOf("2040-01-01"));
		
		checkIn = new JDateChooser();
		checkIn.setBounds(394, 157, 157, 23);
		Offer.add(checkIn);
		checkIn.setDateFormatString("yyyy,MM dd");
		checkIn.setMinSelectableDate(Date.valueOf(LocalDate.now()));
		checkIn.setMaxSelectableDate(Date.valueOf("2040-01-01"));
		
		JLabel lblCheckIn = new JLabel("CHECK IN");
		lblCheckIn.setBounds(394, 127, 148, 23);
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
		lblCheckOut_1.setBounds(415, 191, 109, 23);
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
				room_code=Integer.parseInt(tableRoom.getModel().getValueAt(row,0).toString());
				number_of_bed=Integer.parseInt(tableRoom.getModel().getValueAt(row,2).toString());
				for(int i=0;i<listRoom.size();i=i+2) {
					Room room=(Room) listRoom.get(i);
					if(room_code==room.getRoom_code()) {
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
		
		JButton btnNewButton_4 = new JButton("??");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Room_Info room_info =new Room_Info();
				Room_Info_Decorator room = null;
				if(cbTypeOfRoom.getSelectedItem().toString().equals(Room_Constants.ORDINARY_ROOM.getValue())) {
					
					room=new OrdinaryRoomDecorator(room_info);
					
				}else if(cbTypeOfRoom.getSelectedItem().toString().equals(Room_Constants.APARTMENT.getValue())) {

					room=new ApartmentDecorator(room_info);
					
				}else if(cbTypeOfRoom.getSelectedItem().toString().equals(Room_Constants.LUXURY_ROOM.getValue())) {
					
					room=new LuxuryRoomDecorator(room_info);
					
				}
				RoomType room_typType=new RoomType(user,room);
				room_typType.setVisible(true);
			}
		});
		btnNewButton_4.setForeground(Color.WHITE);
		btnNewButton_4.setBackground(Color.RED);
		btnNewButton_4.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton_4.setBounds(207, 12, 62, 28);
		Offer.add(btnNewButton_4);
		cbTypeOfRoom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EnterRoomInTable(listRoom);
			}
		});
		
		 MyReservation = new JPanel();
		layeredPane_1.add(MyReservation, "name_65696724573042");
		MyReservation.setLayout(null);
		MyReservation.setBackground(new Color(0,0,0,0));
		
		JScrollPane scrollPane_2 = new JScrollPane(tableReservation);
		scrollPane_2.setBounds(10, 11, 540, 252);
		MyReservation.add(scrollPane_2);
		
		tableReservation = new JTable(dtmReservation);
		tableReservation.setFillsViewportHeight(true);
		tableReservation.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		tableReservation.setBackground(new Color(0, 255, 255));
		tableReservation.setForeground(new Color(0, 0, 0));
		tableReservation.addMouseListener(new MouseListener() {
			
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
				int row=tableReservation.getSelectedRow();
				Check_in=Date.valueOf(tableReservation.getModel().getValueAt(row,0).toString());
				Check_out=Date.valueOf(tableReservation.getModel().getValueAt(row,1).toString());
				if(Check_out.compareTo(Date.valueOf(LocalDate.now())) <= 0) {
					btnCancelReservation.setEnabled(false);
				}else if(Check_out.compareTo(Date.valueOf(LocalDate.now())) > 0){
					btnCancelReservation.setEnabled(true);
				}
				room_code=Integer.parseInt(tableReservation.getModel().getValueAt(row,5).toString());
				
			}
		});
		scrollPane_2.setViewportView(tableReservation);
		
		btnCancelReservation = new JButton("CANCEL RESERVATION");
		btnCancelReservation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Check_in != null && Check_out != null) {
					LocalDate now=LocalDate.now();
					LocalDate checkIn=Check_in.toLocalDate();
					long number=now.until(checkIn,ChronoUnit.DAYS);
					if(number > 10) {
						Reservation reservation=new Reservation();
						reservation.setRoom_code(room_code);
						reservation.setCheckIn(Check_in);
						reservation.setCheckOut(Check_out);
						try {
							GenericList<GeneralDomain>list=new GenericList<GeneralDomain>();
							list.add(property);
							list.add(reservation);
							list.add(userGlobal);
							list.add(map.get(userGlobal.getUsername()).get(1));
							ControlerUI.getInstance().sendToServer(Type_Of_Operation.DELETE_RESERVATION, Type_Of_Data.RESERVATION, list);
							String message=ControlerReservation.getInstance().getMessage();
							ControlerReservation.getInstance().setNumber(0);
							if(message != null) {
								JOptionPane.showMessageDialog(null, message);
							}
							user.enterDataOnTXTFle(user,Type_OF_Operation_TXT.DELETE_RESERVATION.getValue(),String.valueOf(reservation.getCheckIn()+" "+reservation.getCheckOut()));
							map.get(property.getName()).delete(reservation);
						} catch (ClassNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}else {
						JOptionPane.showMessageDialog(null,TransferClass_Message.SORRY_RESERVATION.getValue());
					}
				}else {
					JOptionPane.showMessageDialog(null,TransferClass_Message.SELECT_RESERVATION.getValue());
				}
					
			}
		});
		btnCancelReservation.setFont(new Font("Castellar", Font.BOLD, 9));
		btnCancelReservation.setBackground(Color.WHITE);
		btnCancelReservation.setForeground(Color.RED);
		btnCancelReservation.setBounds(180, 286, 231, 51);
		CommonMethod.setNewPicutreOnButton(AbsolutePath.absolutePath()+URLS.PICTURE_CANCEL_RESERVATION.getValue(), btnCancelReservation);
		MyReservation.add(btnCancelReservation);
		
		ChatPanel = new JPanel();
		layeredPane_1.add(ChatPanel, "name_37702468242415");
		ChatPanel.setLayout(null);
		ChatPanel.setBackground(new Color(0,0,0,0));
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(10, 107, 540, 229);
		ChatPanel.add(scrollPane_3);
		
		textArea = new JTextArea();
		scrollPane_3.setViewportView(textArea);
		
		tfMessage = new JTextField();
		tfMessage.setBounds(305, 43, 128, 35);
		ChatPanel.add(tfMessage);
		tfMessage.setColumns(10);
		
		btnNewButton_3 = new JButton("SEND");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GenericList<GeneralDomain>list=new GenericList<GeneralDomain>();
				String message=tfMessage.getText();
				Message chatMessage=new Message();
				chatMessage.setMessage(message);
				chatMessage.setUser(chatUser);
				list.add(chatMessage);
				list.add(user);
				try {
					ControlerUI.getInstance().sendToServer(Type_Of_Operation.SENDMESSAGE, Type_Of_Data.MESSAGE, list);
					textArea.append("\n");
					textArea.append(user_info.getName()+" : "+message+"\n");
					if(chatMap.get(property.getUser_Username()) != null) {
						chatMap.get(property.getUser_Username()).append("\n");
						chatMap.get(property.getUser_Username()).append(user_info.getName()+" : "+message+"\n");
					}else {
						chatMap.put(property.getUser_Username(),new StringBuffer());
						chatMap.get(property.getUser_Username()).append("\n");
						chatMap.get(property.getUser_Username()).append(property.getUser_Username()+" : "+message+"\n");
					}
					
					ControlerUser.getInstance().setNumber(0);
					tfMessage.setText("");
				} catch (ClassNotFoundException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_3.setBounds(443, 43, 89, 35);
		ChatPanel.add(btnNewButton_3);
		
		lblChatPicture = new JLabel("New label");
		lblChatPicture.setBounds(10, 11, 76, 85);
		ChatPanel.add(lblChatPicture);
		
		lblUsernameChat = new JLabel("");
		lblUsernameChat.setBounds(96, 11, 116, 67);
		ChatPanel.add(lblUsernameChat);
		Object[]column= {
				Reservation_Constant.CHECK_IN.getValue(),
				Reservation_Constant.CHECK_OUT.getValue(),
				Reservation_Constant.NUMBER_NIGHTS.getValue(),
				Reservation_Constant.AMOUNT.getValue(),
				Reservation_Constant.RESERVATION_CODE.getValue(),
				Reservation_Constant.ROOM_CODE.getValue()
		};
		
		dtmReservation.addColumn(column[0]);
		dtmReservation.addColumn(column[1]);
		dtmReservation.addColumn(column[2]);
		dtmReservation.addColumn(column[3]);
		dtmReservation.addColumn(column[4]);
		dtmReservation.addColumn(column[5]);
		
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
	
				setPanelOnLayeredPane(layeredPane,PanelMyinfo);
				
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
				
				setPanelOnLayeredPane(layeredPane,PanelCardInfo);
				
			}
		});
		
		rbChat = new JRadioButton("CHAT WITH ADMIN");
		rbChat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				setPanelOnLayeredPane(layeredPane_1,ChatPanel);
				
			}
		});
		rbChat.setFont(new Font("Castellar", Font.BOLD, 12));
		rbChat.setBounds(825, 320, 186, 23);
		add(rbChat);
		bg1.add(rbChat);
		rbChat.setVisible(false);
		
		rbCardInfo.setFont(new Font("Castellar", Font.BOLD, 11));
		rbCardInfo.setBounds(145, 251, 136, 23);
		add(rbCardInfo);
		bg.add(rbCardInfo);
		
		layeredPane_Location = new JLayeredPane();
		
		layeredPane_Location.setBounds(477, 33, 724, 189);
		add(layeredPane_Location);
		layeredPane_Location.setLayout(new CardLayout(0, 0));
		layeredPane_Location.setBorder(BorderFactory.createEtchedBorder());
		
		PanelPictures = new JPanel();
		layeredPane_Location.add(PanelPictures, "name_75352240838726");
		PanelPictures.setLayout(null);
		PanelPictures.setBackground(new Color(0,0,0,0));
		
		lblBeforeMain = new JLabel("");
		lblBeforeMain.setBounds(10, 43, 91, 88);
		PanelPictures.add(lblBeforeMain);
		
		lbl1 = new JLabel("New label");
		lbl1.setBounds(436, 60, 54, 48);
		PanelPictures.add(lbl1);
		
		lbl2 = new JLabel("New label");
		lbl2.setBounds(521, 60, 54, 48);
		PanelPictures.add(lbl2);
		
		lbl5 = new JLabel("New label");
		lbl5.setBounds(481, 119, 54, 48);
		PanelPictures.add(lbl5);
		
		lblAftermain = new JLabel("");
		lblAftermain.setBounds(266, 43, 91, 88);
		PanelPictures.add(lblAftermain);
		
		lblmainHotelPicture = new JLabel("");
		lblmainHotelPicture.setBounds(105, 17, 152, 150);
		PanelPictures.add(lblmainHotelPicture);
		
		lbl4 = new JLabel("New label");
		lbl4.setBounds(568, 119, 54, 48);
		PanelPictures.add(lbl4);
		
		lbl3 = new JLabel("New label");
		lbl3.setBounds(603, 60, 54, 48);
		PanelPictures.add(lbl3);
		
		JButton button_1 = new JButton("<<<");
		button_1.setBounds(10, 134, 91, 33);
		PanelPictures.add(button_1);
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
		
		JButton button_4 = new JButton(">>>");
		button_4.setBounds(263, 134, 94, 33);
		PanelPictures.add(button_4);
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
		
		PanelLocation = new JPanel();
		layeredPane_Location.add(PanelLocation, "name_75352271453973");
		PanelLocation.setLayout(null);
		PanelLocation.setBackground(new Color(0,0,0,0));
		
		lblLocation = new JLabel("New label");
		lblLocation.setBounds(0, 0, 724, 189);
		PanelLocation.add(lblLocation);
		
		JLabel label_6 = new JLabel("Street");
		label_6.setForeground(Color.BLACK);
		label_6.setFont(new Font("Castellar", Font.BOLD, 14));
		label_6.setBounds(477, 274, 161, 23);
		add(label_6);
		
		tfStreet = new JTextField();
		tfStreet.setEditable(false);
		tfStreet.setText((String) null);
		tfStreet.setColumns(10);
		tfStreet.setBounds(627, 274, 186, 20);
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
		layeredPane.setBounds(20, 282, 296, 425);
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
		tfName.setEnabled(false);
		tfName.setBounds(10, 55, 201, 36);
		PanelMyinfo.add(tfName);
		tfName.setText((String) null);
		tfName.setColumns(10);
		
		JButton button = new JButton("UPDATE");
		button.setBounds(10, 364, 121, 33);
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
							user_info.setUser_Username(user.getUsername());
							user_info.setName(name);
							user_info.setSurname(surname);
							user_info.setGender(gender);
							user_info.setEmail(email);
							user_info.setPictureURL(pictureUrl);
							user_info.setMobileNumber(mobileNumber);
							GenericList<GeneralDomain>list=new GenericList<GeneralDomain>();
							list.add(user);
							list.add(user_info);
							ControlerUI.getInstance().sendToServer(Type_Of_Operation.UPDATE_USER,Type_Of_Data.USER,list);
							String message=ControlerUser.getInstance().getMessage();
							ControlerReservation.getInstance().setNumber(0);
							if(message != null) {
								JOptionPane.showMessageDialog(null,message);
							}
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
		button.setFont(new Font("Castellar", Font.BOLD, 9));
		CommonMethod.setNewPicutreOnButton(AbsolutePath.absolutePath()+URLS.PICTURE_ADMIN_UPDATE.getValue(), button);
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
		
		btnDeactivate = new JButton("DEACTIVATE");
		btnDeactivate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
				
					user.setStatus("deactivated");
					ControlerUI.getInstance().sendToServer(Type_Of_Operation.DEACTIVATE_USER, Type_Of_Data.USER,user);
					String message=ControlerUser.getInstance().getMessage();
					ControlerUser.getInstance().setNumber(0);
					JOptionPane.showMessageDialog(null, message);
					user.enterDataOnTXTFle(user, Type_OF_Operation_TXT.DEACTIVATE_USER.getValue(),user.getUsername());
					JPanel login=new Login();
					Application.setPanelOnLayeredPane(login);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnDeactivate.setBounds(139, 364, 143, 32);
		CommonMethod.setNewPicutreOnButton(AbsolutePath.absolutePath()+URLS.DEACTIVATE_USER.getValue(), btnDeactivate);
		PanelMyinfo.add(btnDeactivate);
		btnDeactivate.setForeground(Color.RED);
		btnDeactivate.setFont(new Font("Castellar", Font.BOLD, 8));
		btnDeactivate.setBackground(Color.WHITE);
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
		CommonMethod.setNewPicutreOnButton(AbsolutePath.absolutePath()+URLS.PICTURE_ADMIN_CARD.getValue(), btnAddNewCard);
		PanelCardInfo.add(btnAddNewCard);
		
		CreateCard = new JPanel();
		layeredPane.add(CreateCard, "name_450799144050384");
		CreateCard.setLayout(null);
		CreateCard.setBackground(new Color(0,0,0,0));
		btnAddNewCard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				setPanelOnLayeredPane(layeredPane,CreateCard);
				
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
				if(dcRegistration.getDate() != null) {
					Date date=Date.valueOf(sdf.format(dcRegistration.getDate()));
					boolean testFiled=testField(numberCar,date);
					boolean AllFiled=AllField(numberCar,date);
					if(AllFiled) {
						if(testFiled) {
							GenericList<GeneralDomain>list=new GenericList<GeneralDomain>();
							PaymentUserCard payment=new PaymentUserCard();
							payment.setUser_Username(user.getUsername());
							payment.setNumber(Long.parseLong(numberCar));
							payment.setType(type);
							payment.setExpiration_date(date);
							TransferClass transferClass;
							try {
								GenericList< GeneralDomain>list1=new GenericList<GeneralDomain>();
								list1.add(user);
								list1.add(payment);
								ControlerUI.getInstance().sendToServer(Type_Of_Operation.ADD_PAYMENT_CARD, Type_Of_Data.USER, list1);
								String message=ControlerUser.getInstance().getMessage();
								ControlerUser.getInstance().setNumber(0);
								if(message != null) {
									JOptionPane.showMessageDialog(null,message);
									layeredPane.removeAll();
									layeredPane.add(PanelCardInfo);
									layeredPane.repaint();
									layeredPane.revalidate();
									setDataOnTF(payment);
									map.get(user.getUsername()).add(payment);
									paymentGlobal=payment;
									user.enterDataOnTXTFle(user,Type_OF_Operation_TXT.ADD_PAYMENT_CARD.getValue(),String.valueOf(payment.getNumber()));
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
				}else {
					JOptionPane.showMessageDialog(null,TransferClass_Message.SET_DATE.getValue());
				}
				
			}
		});
		btnNewButton_1.setBounds(92, 356, 113, 41);
		CommonMethod.setNewPicutreOnButton(AbsolutePath.absolutePath()+URLS.PICTURE_ADMIN_CARD.getValue(), btnNewButton_1);
		CreateCard.add(btnNewButton_1);
		
		JLabel lblBackGround = new JLabel("");
		lblBackGround.setFont(new Font("Castellar", Font.BOLD, 9));
		lblBackGround.setBounds(0, 0, 1270, 771);
		CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+URLS.PICTURE_USER_BACKGROUND.getValue(), lblBackGround);
		add(lblBackGround);
		
		
		
		
		  fillRoomComboBox(); fillJList(); fillCBType(); putPictureOnLabel();
		  backAllForThisProperty(); sortAllTables(); setControlersForm();
		 		 
		 
		 
	}

	
	

	public static void setPanelOnLayeredPane(JLayeredPane layeredPane, JPanel panel) {
		layeredPane.removeAll();
		layeredPane.add(panel);
		layeredPane.repaint();
		layeredPane.revalidate();
		
	}


	private void setControlersForm() {
		ControlerDiscount.getInstance().setUserPanel(this);
		ControlerRoom.getInstance().setUserPanel(this);
		ControlerProperty.getInstance().setUserPanel(this);
		ControlerReservation.getInstance().setUserPanel(this);
		ControlerRating.getInstance().setUserPanel(this);
		ControlerUser.getInstance().setUserPanel(this);
		ControlerMessage.getInstance().setUserPanel(this);
		
	}


	protected boolean checkNumberofChildrenAndAdults(int number_of_bed2, int numberAdults, int numberChildren) {
		if(numberAdults+numberChildren > number_of_bed2) {
			return false;
		}
		return true;
	}


	private void sortAllTables() {
		sort(dtmReservation,tableReservation);
		sort(dtmRoom, tableRoom);
		
	}
	
	public void sort(DefaultTableModel dtm,JTable jtable) {
		dtm=(DefaultTableModel) jtable.getModel();
		TableRowSorter<DefaultTableModel>sorter =new TableRowSorter<DefaultTableModel>(dtm);
		jtable.setRowSorter(sorter);
	}


	  private void chechIfExcistNoRatingReservation(){
		boolean continueMethod=false;
		GenericList<GeneralDomain>list=new GenericList<GeneralDomain>();
		Reservation reservation=new Reservation();
		Room room=new Room();
		Property property=new Property();
		int differenceAll=0;
		boolean excist=true;
		if(listReservation.size() > 0) {
			forOn :for(int i=0;i<listReservation.size();i++) {
				reservation=(Reservation) listReservation.get(i);
				list=null;
				list=new GenericList<GeneralDomain>();
				if(reservation.getCheckOut().compareTo(CurrentDate) < 0 && reservation.getUser_Username().equals(userGlobal.getUsername())){
					list=findRomm(listRoom,reservation);
					room=(Room) list.get(0);
					property=(Property) list.get(1);
					if(listRating.size() == 0) {
						excist=false;
						break forOn;
					}
					for(int j=0;j<listRating.size();j++) {
						Client_Rating rating=(Client_Rating) listRating.get(j);
						if(rating.getReservation_code() == reservation.getReservation_code()) {
							excist=true;
						}else{
							differenceAll++;
						}
					}
					if(differenceAll == listRating.size()) {
						excist=false;
						break forOn;
					}
					differenceAll=0;
				}
			}
		if(excist==false) {
			Rating rating=new Rating(reservation,property,room);
			rating.setVisible(true);
			int rating1=Rating.getRating();
			if(rating1 > 0 ) {
				JOptionPane.showMessageDialog(null,TransferClass_Message.RATING.getValue()+" "+rating1);
				Rating.setRating(0);
				Client_Rating clientRating=new Client_Rating();
				clientRating.setClient_rating(rating1);
				clientRating.setProperty_code(property.getProperty_code());
				clientRating.setReservation_code(reservation.getReservation_code());
				clientRating.setUser_Username(userGlobal.getUsername());
				try {
					GenericList<GeneralDomain>list1=new GenericList<GeneralDomain>();
					list1.add(property);
					list1.add(clientRating);
					ControlerUI.getInstance().sendToServer(Type_Of_Operation.ADD_NEW_RATING, Type_Of_Data.RATING, list1);
					String message=ControlerRating.getInstance().getMessage();
					ControlerRating.getInstance().setNumber(0);
					if(message != null) {
						JOptionPane.showMessageDialog(null,message);
					}
					listRating.add(clientRating);
					userGlobal.enterDataOnTXTFle(userGlobal, Type_OF_Operation_TXT.ADD_RATING.getValue(),String.valueOf(reservation.getReservation_code()));
				} catch (ClassNotFoundException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}	
}


	private GenericList<GeneralDomain> findRomm(GenericList<GeneralDomain> listRoom2, Reservation reservation) {
		GenericList<GeneralDomain>list=new GenericList<GeneralDomain>();
		Room room=new Room();
		for(int i=0;i<listRoom2.size();i=i+2) {
			room=(Room) listRoom2.get(i);
			if(room.getRoom_code()==reservation.getRoom_code()) {
				break;
			}
		}
		list=findProperty(room);
		return list;
		
	}


	private GenericList<GeneralDomain> findProperty(Room room) {
		GenericList<GeneralDomain>list1=new GenericList<GeneralDomain>();
		GenericList<GeneralDomain>list2=new GenericList<GeneralDomain>();
		Property property=new Property();
		for(Map.Entry<String,GenericList<GeneralDomain>> entry : map.entrySet()) {
			if(entry.getValue().get(0) instanceof Property ) {
				list1=(GenericList<GeneralDomain>) entry.getValue();
				property=(Property) list1.get(0);
				if(property.getProperty_code()==room.getProperty_code() ) {
					break;
				}
			}
		}
		list2.add(room);
		list2.add(property);
		return list2;
	}


	protected boolean checkPaymentCard() {
		if(paymentGlobal == null) {
			return false;
		}
		long paymentNmuber=paymentGlobal.getNumber();
			String payment=JOptionPane.showInputDialog("DEPOSIT FOR RESERVATION IS 30% \nENTER YOUR PAYMENT CARD NUMBER :");
			if(payment != null) {
				if(payment.length() > 0) {
					long paymentNumberChooser=Long.parseLong(payment);
					if(paymentNmuber != paymentNumberChooser) {
						JOptionPane.showMessageDialog(null,TransferClass_Message.INCORECT_CARD_NUMBER.getValue());
						return false;
					}	
				}else {
					return false;
				}
			}else {
				return false;
			}
		return true;
	}


	protected void setAllClear() {
		aduJSpinner.setValue(0);
		childrenSpinner.setValue(0);
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


	protected boolean checkReservation(int room_code, Date dateFrom, Date dateTo) {
		for(int i=0;i<listReservation.size();i++) {
			Reservation reservation=(Reservation) listReservation.get(i);
			int dateFromCompareCI=dateFrom.compareTo(reservation.getCheckIn());
			int dateToCompareCI=dateFrom.compareTo(reservation.getCheckOut());
			int dateFromCompareCO=dateTo.compareTo(reservation.getCheckIn());
			int dateToCompareCO=dateTo.compareTo(reservation.getCheckOut());
			if(room_code==reservation.getRoom_code() && ((dateFromCompareCI>=0 && dateToCompareCI<=0) || (dateFromCompareCO>=0 && dateToCompareCO<=0) || (dateFromCompareCI<=0 && dateToCompareCO>=0))) {
				reservedFromDate=reservation.getCheckIn();
				reservedToDate=reservation.getCheckOut();
				return false;
			}
		}
		return true;
	}


	protected void setDataOnTF(PaymentUserCard payment) {
		btnAddNewCard.setVisible(false);
		tfExpirationDate.setText(String.valueOf(payment.getExpiration_date()));
		tfCardNumber.setText(String.valueOf(payment.getNumber()));
		CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+URLS.PICTURE_USER_CARD.getValue()+"/"+payment.getType()+".png", lblCardPicture);
	}


	protected boolean AllField(String numberCar, Date date) {
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


	protected boolean testField(String numberCar, Date date) {
		if(numberCar.length() < 1 || date.compareTo(CurrentDate) < 0) {
			return false;
		}
		return true;
	}


	private void fillCBType() {
		cbType.addItem(new ComboBoxClass("DINA",new ImageIcon(AbsolutePath.absolutePath()+URLS.PICTURE_USER_CARD_DINA.getValue())));
		cbType.addItem(new ComboBoxClass("VISA",new ImageIcon(AbsolutePath.absolutePath()+URLS.PICTURE_USER_CARD_VISA.getValue())));
		cbType.addItem(new ComboBoxClass("MASTER",new ImageIcon(AbsolutePath.absolutePath()+URLS.PICTURE_USER_CARD_MASTER.getValue())));
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
		}
		userGlobal.enterDataOnTXTFle(userGlobal,Type_OF_Operation_TXT.DELETE_PICTURE.getValue(),userGlobal.getUsername());
		
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
		 EnterReservationInTable(listReservation);
		 setRatingToNull();
		 setRating();
	}

	
	private void EnterReservationInTable(GenericList<GeneralDomain> listReservation2) {
		Reservation reservation=new Reservation();
		dtmReservation.setRowCount(0);
		for(int i=0;i<listReservation2.size();i++) {
			reservation=(Reservation) listReservation2.get(i);
			if(reservation.getUser_Username().equals(userGlobal.getUsername())) {
				Object[]row= {reservation.getCheckIn(),reservation.getCheckOut(),reservation.getNumberNights(),reservation.getAmount(),reservation.getReservation_code(),reservation.getRoom_code()};
				dtmReservation.addRow(row);
			}
		}
		
	}


	private void setRatingToNull() {
		CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+URLS.PICTURE_PROPERTY_EMPTY_STAR.getValue(),lbl1);
		CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+URLS.PICTURE_PROPERTY_EMPTY_STAR.getValue(),lbl2);
		CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+URLS.PICTURE_PROPERTY_EMPTY_STAR.getValue(),lbl3);
		CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+URLS.PICTURE_PROPERTY_EMPTY_STAR.getValue(),lbl4);
		CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+URLS.PICTURE_PROPERTY_EMPTY_STAR.getValue(),lbl5);
		
	}


	private void setRating() {
		if(property.getRating()==1) {
			CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+URLS.PICTURE_PROPERTY_FULL_STAR.getValue(),lbl1);
		}else if(property.getRating()==2) {
			CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+URLS.PICTURE_PROPERTY_FULL_STAR.getValue(),lbl1);
			CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+URLS.PICTURE_PROPERTY_FULL_STAR.getValue(),lbl2);
		}else if(property.getRating()==3) {
			CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+URLS.PICTURE_PROPERTY_FULL_STAR.getValue(),lbl1);
			CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+URLS.PICTURE_PROPERTY_FULL_STAR.getValue(),lbl2);
			CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+URLS.PICTURE_PROPERTY_FULL_STAR.getValue(),lbl3);
		}else if( property.getRating()==4) {
			CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+URLS.PICTURE_PROPERTY_FULL_STAR.getValue(),lbl1);
			CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+URLS.PICTURE_PROPERTY_FULL_STAR.getValue(),lbl2);
			CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+URLS.PICTURE_PROPERTY_FULL_STAR.getValue(),lbl3);
			CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+URLS.PICTURE_PROPERTY_FULL_STAR.getValue(),lbl4);
		}else if(property.getRating()==5) {
			CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+URLS.PICTURE_PROPERTY_FULL_STAR.getValue(),lbl1);
			CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+URLS.PICTURE_PROPERTY_FULL_STAR.getValue(),lbl2);
			CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+URLS.PICTURE_PROPERTY_FULL_STAR.getValue(),lbl3);
			CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+URLS.PICTURE_PROPERTY_FULL_STAR.getValue(),lbl4);
			CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+URLS.PICTURE_PROPERTY_FULL_STAR.getValue(),lbl5);
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
			String url=AbsolutePath.absolutePath()+URLS.PROFILE_PICTURE_USER_COUNTRYES.getValue()+"/"+adress1.getCountry()+".jpg";
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
				if(property1.getProperty_code()==discount.getProperty_code()) {
					number++;
				}
			}
			double rating=mapRating.get(property1.getName());
			String format=String.format("%.2f",rating);
			if(number>0) {
				dm.addElement(new ComboBoxClass(property1.getName()+" ("+property1.getType_Of_Property()+")  !!!(ACTION PRICES)!!! " +format+" "+"\u2B50",new ImageIcon(url)));
			}else if(number==0){
				dm.addElement(new ComboBoxClass(property1.getName()+" ("+property1.getType_Of_Property()+")         "+format+" "+"\u2B50",new ImageIcon(url)));
			}
			number=0;
			
		}
		//dm.addElement(new ComboBoxClass(null,null));
		listProperty.setCellRenderer(new RenderCB());
		listProperty.setModel(dm);
		listProperty.setSelectedIndex(0);
	}

	
	

	private void sortMapRating(Map<String, Double> mapRating2) {
		// TODO Auto-generated method stub
		
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
		int counter=0;
		listRoom=null;
		listRoom=new GenericList<GeneralDomain>();
		listDiscount=null;
		listDiscount=new GenericList<GeneralDomain>();
		listReservation=null;
		listReservation=new GenericList<GeneralDomain>();
		int number=map.get(whichProperty).size();
		boolean excistDiscount=false;
		for(int i=0;i<number;i++) {
			if(map.get(whichProperty).get(i) instanceof Room || map.get(whichProperty).get(i) instanceof Room_Info) {
				listRoom.add(map.get(whichProperty).get(i));
			}else if(map.get(whichProperty).get(i) instanceof Discount){
				listDiscount.add(map.get(whichProperty).get(i));
				excistDiscount=true;
			}else if(map.get(whichProperty).get(i) instanceof Reservation) {
				listReservation.add(map.get(whichProperty).get(i));
			}else if(map.get(whichProperty).get(i) instanceof Client_Rating && counter==0) {
				listRating.add(map.get(whichProperty).get(i));
			}
		}
		if(excistDiscount) {
			btnDiscount.setVisible(true);
		}
		counter++;
		
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
		 rbChat.setVisible(false);
		 cyrcleList.remove();
		 textArea.setText("");
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
			 String url=URLS.PROFILE_PICTURE_DEFAULT.getValue()+"/"+property.getClass().getSimpleName()+".jpg";
			 if(!listAlbum.get(i).getPicutre_URL().equals(url)) {
				 cyrcleList.append(listAlbum.get(i));
			 }
		 }
		 listAlbum=null;
		 listAlbum=new GenericList<Property_Picutre_Album>();
		 if(map.get(userGlobal.getUsername()).size() > 2 ) {
			 if(((PaymentUserCard) map.get(userGlobal.getUsername()).get(2)).getNumber() != 0) {
				 this.paymentGlobal=(PaymentUserCard) map.get(userGlobal.getUsername()).get(2);
				 setDataOnTF(paymentGlobal);
			 }
		 }
		 setUrl(URLS.PROFILE_PICTURE_USERS.getValue()+"/"+userGlobal.getUsername()+"/"+"ProfilePicture.jpg");
		 chatUser=(User) map.get(property.getUser_Username()).get(0);
		 chatUser_info=(User_Info) map.get(property.getUser_Username()).get(1);
		 CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+chatUser_info.getPictureURL(), lblChatPicture);
		 lblUsernameChat.setText(chatUser_info.getName());
		 try {
			 if(backForAll) {
				 ControlerUI.getInstance().sendToServer(Type_Of_Operation.CHECK_USER_ONLINE, Type_Of_Data.USER,map.get(property.getUser_Username()).get(0));
				 try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
					int number=ControlerUser.getInstance().getUserOnlineNumber();
					ControlerUser.getInstance().setNumber(0);
					if(number==1) {
						rbChat.setVisible(true);
						if(!chatMap.isEmpty()) {
							if(chatMap.get(property.getUser_Username()) != null) {
								textArea.setText(chatMap.get(property.getUser_Username()).toString());
							}
						}
					} 
			 }
			btnNewButton_3.setVisible(true);
			backForAll=true;
			addMapsOnPanel(PanelLocation,lblLocation);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void addMapsOnPanel(JPanel panelLocation2, JLabel lblLocation2) {
		
		 try {
			 
	            String imageUrl = "https://maps.googleapis.com/maps/api/staticmap?center="
	            		+geoLocation.getLatitude()+","+geoLocation.getLongitude()
	            		+ "&zoom=17&size=724x189&maptype=roadmap&markers=color:red%7Clabel:S%7C"
	            		+ geoLocation.getLatitude()+","+geoLocation.getLongitude()
	            		+ "&key=AIzaSyDawgzcko2JEyuIFt4_hOGRWxCSUn0qq2Y";
	            
	            String destinationFile = "image.jpg";
	            String str = destinationFile;
	            URL url = new URL(imageUrl);
	            InputStream is = url.openStream();
	            OutputStream os = new FileOutputStream(destinationFile);

	            byte[] b = new byte[2048];
	            int length;

	            while ((length = is.read(b)) != -1) {
	                os.write(b, 0, length);
	            }

	            is.close();
	            os.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	            System.exit(1);
	        }

	        lblLocation2.setIcon(new ImageIcon((new ImageIcon("image.jpg")).getImage().getScaledInstance(724,189,
	                java.awt.Image.SCALE_SMOOTH)));
		
	}


	private void putPictureOnLabel() {
		CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+URLS.PICTURE_PROPERTY_EMPTY_STAR.getValue(), lbl1);
		CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+URLS.PICTURE_PROPERTY_EMPTY_STAR.getValue(), lbl2);
		CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+URLS.PICTURE_PROPERTY_EMPTY_STAR.getValue(), lbl3);
		CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+URLS.PICTURE_PROPERTY_EMPTY_STAR.getValue(), lbl4);
		CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+URLS.PICTURE_PROPERTY_EMPTY_STAR.getValue(), lbl5);
		
	}


	


	private GenericList<GeneralDomain> fillList(GenericMap<String, GenericList<GeneralDomain>> map2, GenericList<GeneralDomain> listProperties2) {
		GenericList<GeneralDomain>list1=new GenericList<GeneralDomain>();
		Property property=new Property();
		Adress adress1=new Adress();
		String name;
		double Rating=0;
		int count=0;
		listProperties2=null;
		listProperties2=new GenericList<GeneralDomain>();
		mapRating=null;
		mapRating=new HashMap<String, Double>();
		User user=new User();
		for(Map.Entry<String,GenericList<GeneralDomain>> entry : map2.entrySet()) {
				if(!(entry.getValue().get(0) instanceof User)) {
					list1=(GenericList<GeneralDomain>) entry.getValue();
					property=(Property) list1.get(0);
					adress1=(Adress) list1.get(1);
					user=(User) map2.get(property.getUser_Username()).get(0);
					if(user.getStatus().equals("deactivated")) {
						continue;
					}else {
					listProperties2.add(property);
					listProperties2.add(adress1);
					for(int i=0;i<entry.getValue().size();i++) {
						if(entry.getValue().get(i) instanceof Client_Rating) {
							Rating+=((Client_Rating) entry.getValue().get(i)).getClient_rating();
							count++;
						}
					}
					if(Rating==0) {
						mapRating.put(property.getName(),0.0);
						count=0;
						Rating=0;
					}else {
						mapRating.put(property.getName(),Rating/count);
						count=0;
						Rating=0;
					}
				}
			}
			
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
				if(mapRating.get(p1.getName()) < mapRating.get(p2.getName())) {
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
		if(newPictureURL.substring(newPictureURL.length()-3,newPictureURL.length()).equalsIgnoreCase("jpg") ||
		 newPictureURL.substring(newPictureURL.length()-3,newPictureURL.length()).equals("png")	) {
			createPictureForServer(newPictureURL,user);
			CommonMethod.setNewPicutreOnLabel(newPictureURL, label);
			user_info.setPictureURL(URLS.PROFILE_PICTURE_USERS.getValue()+"/"+user.getUsername()+"/"+"ProfilePicture.jpg");
			if(user.getStatus().equals("admin")) {
				Admin_Panel.setUrl(URLS.PROFILE_PICTURE_USERS.getValue()+"/"+user.getUsername()+"/"+"ProfilePicture.jpg");
			}else {
				setUrl(URLS.PROFILE_PICTURE_USERS.getValue()+"/"+user.getUsername()+"/"+"ProfilePicture.jpg");
			}
			
		}
	}
	
	
	public static void createPictureForServer(String newPictureURL,User user){
		try {
			FileInputStream in=new FileInputStream(newPictureURL);
			FileOutputStream out=new FileOutputStream(AbsolutePath.absolutePath()+URLS.PROFILE_PICTURE_USERS.getValue()+"/"+user.getUsername()+"/"+"ProfilePicture.jpg");
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


	public void addToListRoom(Room room, Room_Info room_info) {
		listRoom.add(room);
		listRoom.add(room_info);
		EnterRoomInTable(listRoom);
	}



	public void updatePictureURL(User user1, String pictureURL) {
		((User_Info) map.get(user1.getUsername()).get(1)).setPictureURL(pictureURL);
		refreshGlobalVariables();
	}


	public void backAllForProperty(GenericMap<String, GenericList<GeneralDomain>> map2) {
		listProperties=fillList(map2, listProperties);
		fillJList();
		backForAll=false;
		backAllForThisProperty();
	}


	public void updateImageOnProperty(Property property2, Property_Picutre_Album propertyAlbum) {
		GenericList<GeneralDomain>list=map.get(property2.getName());
		for(int i=0;i<list.size();i++) {
			if(list.get(i) instanceof Property_Picutre_Album ) {
				if(((Property_Picutre_Album) list.get(i)).getNumber()==propertyAlbum.getNumber()) {
					((Property_Picutre_Album) map.get(property2.getName()).get(i)).setPicutre_URL(propertyAlbum.getPicutre_URL());
				}
			}
		}
		
	}


	public void addRoom(String name, Room room, Room_Info room_info) {
		map.get(name).add(room);
		map.get(name).add(room_info);
		backForAll=false;
		backAllForThisProperty();
	}


	public void updateRoom(String name, Room room) {
		Room room1=new Room();
		for(int i=0;i<map.get(name).size();i=i+2) {
			if(map.get(name).get(i) instanceof Room) {
				room1=(Room) map.get(name).get(i);
				if(room1.getRoom_code()==room.getRoom_code()) {
					map.get(name).set(i,room);
				}
			}
		}
		backForAll=false;
		backAllForThisProperty();
	}


	public void deleteRoom(String name, Room room, Room_Info room_info) {
		map.get(name).delete(room);
		map.get(name).delete(room_info);
		backForAll=false;
		backAllForThisProperty();
	}


	public void addDiscount(String name, Discount discount1) {
		map.get(name).add(discount1);
		fillJList();
		backForAll=false;
		backAllForThisProperty();
	}


	public void deleteDiscount(String name, Discount discount1) {
		map.removeInList(name,discount1);
		fillJList();
		backForAll=false;
		backAllForThisProperty();
	}


	public void addReservation(String name, Reservation reservation) {
		map.get(name).add(reservation);
		backForAll=false;
		backAllForThisProperty();
		
	}


	public void deleteReservation(String name, Reservation reservation) {
		map.get(name).delete(reservation);
		backForAll=false;
		backAllForThisProperty();
	}


	public void addRating(String name, Client_Rating rating) {
		map.get(name).add(rating);
		GenericList<GeneralDomain>sortListProp=fillList(map,listProperties);
		listProperties=sortListProp;
		fillJList();
		backForAll=false;
		backAllForThisProperty();
	}


	public void appendToTextArea(String message2,User user) {
		if(!chatMap.containsKey(user.getUsername())) {
			chatMap.put(user.getUsername(),new StringBuffer());
			chatMap.get(user.getUsername()).append("\n");
			chatMap.get(user.getUsername()).append(user.getUsername()+" : "+message2+"\n");
		}else {
			chatMap.get(user.getUsername()).append("\n");
			chatMap.get(user.getUsername()).append(user.getUsername()+" : "+message2+"\n");
		}
		
		
		if(chatUser.getUsername().equals(user.getUsername())) {
			textArea.append("\n");
			textArea.append(user.getUsername()+" : "+message2+"\n");
		}
		
	}


	public void removeOnlineUser(String message, User user2) {
		chatMap.remove(user2.getUsername());
		if(property.getUser_Username().equals(user2.getUsername())) {
			rbChat.setVisible(false);
			btnNewButton_3.setVisible(false);
			rbOffer.setSelected(true);
			layeredPane_1.removeAll();
			layeredPane_1.add(Offer);
			layeredPane_1.repaint();
			layeredPane_1.revalidate();
		}
	}


	public void addUser(GenericList<GeneralDomain> list) {
		User user=(User) list.get(0);
		map.put(user.getUsername(),list);
		
	}
}
	

