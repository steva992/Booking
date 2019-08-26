package com.comtrade.panel.common;

import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.omg.PortableInterceptor.SUCCESSFUL;

import com.comtrade.commonmethod.CommonMethod;
import com.comtrade.constants.AbsolutePath;
import com.comtrade.constants.Discount_Contstants;
import com.comtrade.constants.Panel_Dimension;
import com.comtrade.constants.URL;
import com.comtrade.controlerClient.ControlerComboBox;
import com.comtrade.controlerClient.ControlerUI;
import com.comtrade.controlerClient.ControlerUser;
import com.comtrade.constants.Regular_Expression;
import com.comtrade.constants.TransferClass_Message;
import com.comtrade.constants.Type_OF_Operation_TXT;
import com.comtrade.constants.Type_Of_Data;
import com.comtrade.constants.Type_Of_Operation;
import com.comtrade.domain.GeneralDomain;
import com.comtrade.domain.user.User;
import com.comtrade.domain.user.User_Info;
import com.comtrade.genericClasses.GenericList;
import com.comtrade.render.ComboBoxClass;
import com.comtrade.render.RenderCB;
import com.comtrade.transfer.TransferClass;
import com.comtrade.view.frame.Application;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSessionBindingEvent;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Pattern;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;

public class SignUp extends JPanel {
	private JTextField tfUsernameSignUp;
	private JPasswordField pfPasswordSignup;
	private JPasswordField pfConfirmPasswordSignUp;
	private JTextField tfName;
	private JTextField tfSurname;
	private JTextField tfEmail;
	private JTextField tfPhoneNumber;
	private JButton btnStepTwo,btnSignUpPanel;
	private JRadioButton rbMan,rbWoman;
	private ButtonGroup buttonGroup=new ButtonGroup();
	private JCheckBox cbProperty;
	private JComboBox<ComboBoxClass> cbCountries;
	private List<List<String>>listCountries;

	/**
	 * Create the panel.
	 */
	public SignUp() {
		listCountries=ControlerComboBox.getInstance().fillContryComboBox();
		setLayout(null);
		setBounds(Panel_Dimension.X.getValue(),Panel_Dimension.Y.getValue(),Panel_Dimension.WIDTH.getValue(),Panel_Dimension.HEIGHT.getValue());
		
		JLabel label = new JLabel("Username *");
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Castellar", Font.BOLD, 12));
		label.setBounds(45, 23, 164, 28);
		add(label);
		
		tfUsernameSignUp = new JTextField();
		tfUsernameSignUp.setColumns(10);
		tfUsernameSignUp.setBounds(41, 57, 224, 28);
		add(tfUsernameSignUp);
		
		JLabel label_1 = new JLabel("example(151)");
		label_1.setForeground(Color.RED);
		label_1.setFont(new Font("Castellar", Font.BOLD, 12));
		label_1.setBounds(292, 57, 231, 28);
		add(label_1);
		
		JLabel label_2 = new JLabel("Password *");
		label_2.setForeground(Color.WHITE);
		label_2.setFont(new Font("Castellar", Font.BOLD, 12));
		label_2.setBounds(45, 98, 164, 28);
		add(label_2);
		
		pfPasswordSignup = new JPasswordField();
		pfPasswordSignup.setFont(new Font("Castellar", Font.BOLD, 12));
		pfPasswordSignup.setBounds(41, 128, 224, 31);
		add(pfPasswordSignup);
		
		JLabel label_3 = new JLabel("example12");
		label_3.setForeground(Color.RED);
		label_3.setFont(new Font("Castellar", Font.BOLD, 12));
		label_3.setBounds(292, 131, 231, 28);
		add(label_3);
		
		JLabel label_4 = new JLabel("Confirm Password *");
		label_4.setForeground(Color.WHITE);
		label_4.setFont(new Font("Castellar", Font.BOLD, 12));
		label_4.setBounds(45, 170, 220, 28);
		add(label_4);
		
		pfConfirmPasswordSignUp = new JPasswordField();
		pfConfirmPasswordSignUp.setFont(new Font("Castellar", Font.BOLD, 12));
		pfConfirmPasswordSignUp.setBounds(45, 209, 220, 31);
		add(pfConfirmPasswordSignUp);
		
		JLabel label_5 = new JLabel("example12");
		label_5.setForeground(Color.RED);
		label_5.setFont(new Font("Castellar", Font.BOLD, 12));
		label_5.setBounds(292, 204, 231, 28);
		add(label_5);
		
		JLabel label_6 = new JLabel("Name *");
		label_6.setForeground(Color.WHITE);
		label_6.setFont(new Font("Castellar", Font.BOLD, 12));
		label_6.setBounds(45, 243, 164, 28);
		add(label_6);
		
		tfName = new JTextField();
		tfName.setFont(new Font("Castellar", Font.BOLD, 12));
		tfName.setColumns(10);
		tfName.setBounds(45, 282, 227, 28);
		add(tfName);
		
		JLabel label_7 = new JLabel("Example");
		label_7.setForeground(Color.RED);
		label_7.setFont(new Font("Castellar", Font.BOLD, 12));
		label_7.setBounds(292, 282, 231, 28);
		add(label_7);
		
		JLabel label_8 = new JLabel("Surname *");
		label_8.setForeground(Color.WHITE);
		label_8.setFont(new Font("Castellar", Font.BOLD, 12));
		label_8.setBounds(45, 327, 164, 28);
		add(label_8);
		
		tfSurname = new JTextField();
		tfSurname.setFont(new Font("Castellar", Font.BOLD, 12));
		tfSurname.setColumns(10);
		tfSurname.setBounds(45, 372, 227, 28);
		add(tfSurname);
		
		JLabel label_9 = new JLabel("Example");
		label_9.setForeground(Color.RED);
		label_9.setFont(new Font("Castellar", Font.BOLD, 12));
		label_9.setBounds(292, 372, 231, 28);
		add(label_9);
		
		JLabel label_10 = new JLabel("Gender *");
		label_10.setForeground(Color.WHITE);
		label_10.setFont(new Font("Castellar", Font.BOLD, 12));
		label_10.setBounds(45, 562, 164, 28);
		add(label_10);
		
		rbMan = new JRadioButton("Man");
		rbMan.setBackground(Color.LIGHT_GRAY);
		rbMan.setForeground(Color.WHITE);
		rbMan.setFont(new Font("Castellar", Font.BOLD, 12));
		rbMan.setBounds(45, 597, 93, 23);
		add(rbMan);
		buttonGroup.add(rbMan);
		
		rbWoman = new JRadioButton("Woman");
		rbWoman.setBackground(Color.LIGHT_GRAY);
		rbWoman.setForeground(Color.WHITE);
		rbWoman.setFont(new Font("Castellar", Font.BOLD, 12));
		rbWoman.setBounds(172, 597, 93, 23);
		add(rbWoman);
		buttonGroup.add(rbWoman);
		
		
		
		JLabel label_11 = new JLabel("Email *");
		label_11.setForeground(Color.WHITE);
		label_11.setFont(new Font("Castellar", Font.BOLD, 12));
		label_11.setBounds(49, 408, 164, 28);
		add(label_11);
		
		tfEmail = new JTextField();
		tfEmail.setFont(new Font("Castellar", Font.BOLD, 12));
		tfEmail.setColumns(10);
		tfEmail.setBounds(45, 435, 227, 28);
		add(tfEmail);
		
		JLabel label_12 = new JLabel("exanple@gmail.com");
		label_12.setForeground(Color.RED);
		label_12.setFont(new Font("Castellar", Font.BOLD, 12));
		label_12.setBounds(292, 435, 231, 28);
		add(label_12);
		
		JLabel label_13 = new JLabel("Phone Number *");
		label_13.setForeground(Color.WHITE);
		label_13.setFont(new Font("Castellar", Font.BOLD, 12));
		label_13.setBounds(45, 474, 220, 28);
		add(label_13);
		
		tfPhoneNumber = new JTextField();
		tfPhoneNumber.setFont(new Font("Castellar", Font.BOLD, 12));
		tfPhoneNumber.setColumns(10);
		tfPhoneNumber.setBounds(166, 513, 99, 38);
		add(tfPhoneNumber);
		
		JLabel label_14 = new JLabel("+381 065333444");
		label_14.setForeground(Color.RED);
		label_14.setFont(new Font("Castellar", Font.BOLD, 12));
		label_14.setBounds(282, 518, 231, 28);
		add(label_14);
		
		JButton btnStepTwo = new JButton("Step 2 >>>");
		btnStepTwo.setBackground(SystemColor.textHighlightText);
		btnStepTwo.setFont(new Font("Castellar", Font.BOLD, 12));
		btnStepTwo.setForeground(Color.RED);
		btnStepTwo.setVisible(false);
		btnStepTwo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GenericList<GeneralDomain>list=grabForPanelForALL();
				try {
					
					if(list.size() > 0) {
						ControlerUI.getInstance().sendToServer(Type_Of_Operation.CHECK_USER, Type_Of_Data.USER, list);
						User user=ControlerUser.getInstance().getUser();
						String message=ControlerUser.getInstance().getMessage();
						ControlerUser.getInstance().setNumber(0);
						if(message != null) {
							if(message.equals(TransferClass_Message.EXCIST_USERNAME.getValue())) {
								JOptionPane.showMessageDialog(null,message);
							}else {
								user=(User) list.get(0);
								User_Info user_Info=(User_Info) list.get(1);
								JPanel propertyCreate=new Property_Created(user,user_Info,"login");
								Application.setPanelOnLayeredPane(propertyCreate);
							}
						}
					}	
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnStepTwo.setBounds(262, 649, 195, 41);
		CommonMethod.setNewPicutreOnButton(AbsolutePath.absolutePath()+URL.PICTURE_SIGN_UP_BUTTON_STEP_TWO.getValue(),btnStepTwo);
		add(btnStepTwo);
		
		JButton btnSignUpPanel = new JButton("Sign Up");
		btnSignUpPanel.setBackground(SystemColor.textHighlightText);
		btnSignUpPanel.setFont(new Font("Castellar", Font.BOLD, 12));
		btnSignUpPanel.setForeground(Color.RED);
		btnSignUpPanel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GenericList<GeneralDomain>list=grabForPanelForALL();
					if(list.size()>0) {
						try {
							User user=(User) list.get(0);
							User_Info user_inf=(User_Info) list.get(1);
							int number=sendVerificationEmail(user_inf.getEmail());
							ControlerUI.getInstance().sendToServer(Type_Of_Operation.REGISTRATION_USER,Type_Of_Data.USER,list);
							String message=ControlerUser.getInstance().getMessage();
							ControlerUser.getInstance().setNumber(0);
							File userFile=new File(AbsolutePath.absolutePath()+URL.PROFILE_PICTURE_USERS.getValue()+"/"+user.getUsername());
							if(!userFile.exists()) {
								userFile.mkdir();
							}
							String messageEmail="We are sent verification code on your email adress!\nPlease Enter your verification code :";
							if(!(JOptionPane.showInputDialog(null, messageEmail) == null)) {
								
								int answer=Integer.parseInt(JOptionPane.showInputDialog(null, messageEmail));
								while(answer !=number) {
									JOptionPane.showMessageDialog(null,"INCORECT NUMBER,PLEASE CHECK YOUR EMAIL AGAIN");
									answer=Integer.parseInt(JOptionPane.showInputDialog(null, messageEmail));
								}
									JOptionPane.showMessageDialog(null,message);
									JPanel login=new Login();
									Application.setPanelOnLayeredPane(login);
									user.enterDataOnTXTFle(user, Type_OF_Operation_TXT.REGISTRATION_USER.getValue(),user.getUsername());
								
						}
							
						} catch (ClassNotFoundException | IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}	
						
					}
					
				
			}
		});
		btnSignUpPanel.setBounds(41, 649, 211, 41);
		CommonMethod.setNewPicutreOnButton(AbsolutePath.absolutePath()+URL.PICTURE_SIGN_UP_BUTTON.getValue(),btnSignUpPanel);
		add(btnSignUpPanel);
		
		cbProperty = new JCheckBox("You have Property");
		cbProperty.setBackground(SystemColor.textHighlightText);
		cbProperty.setFont(new Font("Castellar", Font.BOLD, 12));
		cbProperty.setForeground(Color.RED);
		cbProperty.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(cbProperty.isSelected()) {
					btnStepTwo.setVisible(true);
					btnSignUpPanel.setVisible(false);
				}else {
					btnStepTwo.setVisible(false);
					btnSignUpPanel.setVisible(true);
				}
			}
		});
		cbProperty.setBounds(466, 57, 242, 28);
		add(cbProperty);
		
		JButton btnNewButton = new JButton("<<< Back to LOGIN");
		btnNewButton.setBackground(SystemColor.textHighlightText);
		btnNewButton.setForeground(Color.RED);
		btnNewButton.setFont(new Font("Castellar", Font.BOLD, 12));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JPanel login=new Login();
				Application.setPanelOnLayeredPane(login);
			}
		});
		btnNewButton.setBounds(946, 51, 266, 41);
		CommonMethod.setNewPicutreOnButton(AbsolutePath.absolutePath()+URL.PICTURE_BACK_TO.getValue(), btnNewButton);
		add(btnNewButton);
		
		cbCountries = new JComboBox();
		cbCountries.setFont(new Font("Castellar", Font.BOLD, 12));
		cbCountries.setBounds(45, 513, 111, 38);
		add(cbCountries);
		
		JLabel lblBackGround = new JLabel("");
		lblBackGround.setFont(new Font("Castellar", Font.BOLD, 12));
		lblBackGround.setBounds(Panel_Dimension.X.getValue(),Panel_Dimension.Y.getValue(),Panel_Dimension.WIDTH.getValue(),Panel_Dimension.HEIGHT.getValue());
		add(lblBackGround);
		CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+URL.PICTURE_SIGN_UP_BACKGROUND.getValue(), lblBackGround);
		fillContryComboBox();
	}
	
	protected Integer sendVerificationEmail(String string) {
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
        		return new javax.mail.PasswordAuthentication(AbsolutePath.username, AbsolutePath.password);
        	}
		});

        Message message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(AbsolutePath.username));
            message.setRecipient( Message.RecipientType.TO,new InternetAddress(string));
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

	private void fillContryComboBox() {
			List<List<String>>list=ControlerComboBox.getInstance().fillContryComboBox();
			  for(int i=0;i<list.size();i++) {
				  String url=AbsolutePath.absolutePath()+URL.PROFILE_PICTURE_USER_COUNTRYES.getValue()+"/"+list.get(i).get(0)+".jpg";
				  cbCountries.addItem(new ComboBoxClass(list.get(i).get(1),new ImageIcon(url)));
			  }
			  cbCountries.setRenderer(new RenderCB());
	}

	protected GenericList<GeneralDomain> grabForPanelForALL() {
		GenericList<GeneralDomain>list=new GenericList<GeneralDomain>();
		String username=tfUsernameSignUp.getText();
		String password=String.copyValueOf(pfPasswordSignup.getPassword());
		String confirmPassword=String.copyValueOf(pfConfirmPasswordSignUp.getPassword());
		String name=tfName.getText();
		String surname=tfSurname.getText();
		String email=tfEmail.getText();
		String mobileNumber=((ComboBoxClass)cbCountries.getSelectedItem()).getCall()+tfPhoneNumber.getText();
		String gender = null;
		if(rbMan.isSelected()) {
			gender=rbMan.getText();
		}else if(rbWoman.isSelected()) {
			gender=rbWoman.getText();
		}
		String picture=URL.PROFILE_PICTURE_DEFAULT.getValue()+"/"+gender+".jpg";
		boolean testField=fieldValidityUser(username,name,surname,email,mobileNumber);
		boolean AllFieldCompleted=FieldComplete(username,password,confirmPassword,name,surname,email,mobileNumber,gender);
		if(testField) {
			if(AllFieldCompleted) {
				if(password.equals(confirmPassword)) {
					User user=new User();
					user.setUsername(username);
					user.setPassword(password);
					if(cbProperty.isSelected()) {
						user.setStatus("admin");
					}else {
						user.setStatus("user");
					}
					User_Info user_info=new User_Info();
					user_info.setUser_Username(user.getUsername());
					user_info.setName(name);
					user_info.setEmail(email);
					user_info.setGender(gender);
					user_info.setMobileNumber(mobileNumber);
					user_info.setPictureURL(picture);
					user_info.setSurname(surname);
					list.add(user);
					list.add(user_info);
					
				}else {
					JOptionPane.showMessageDialog(null,TransferClass_Message.INCORECT_ENTER_PASSWORD.getValue());
				}
			}else {
				JOptionPane.showMessageDialog(null,TransferClass_Message.ALL_FIELDS_FILL.getValue());
			}
		}else { 
			JOptionPane.showMessageDialog(null,TransferClass_Message.INCORECT_ENTER_DATA.getValue());
		}
		return list;
	}
	

	private boolean fieldValidityUser(String username, String name, String surname, String email, String mobileNumber) {
		backBorderToGray();
		boolean username1=Pattern.matches(Regular_Expression.USRERNAME.getValue(),username);
		boolean name1=Pattern.matches(Regular_Expression.ONLY_TEXT_VALUES.getValue(),name);
		boolean surname1=Pattern.matches(Regular_Expression.ONLY_TEXT_VALUES.getValue(),surname);
		boolean email1=Pattern.matches(Regular_Expression.EMAIL.getValue(),email);
		boolean mobileNumber1=Pattern.matches(Regular_Expression.PHONE_NUMBER.getValue(),mobileNumber);
		if(username1 &&  name1 && surname1 && email1 && mobileNumber1) {
			return true;
		}
		
		if(username1==false) {
			tfUsernameSignUp.setBorder(BorderFactory.createLineBorder(Color.RED,4));
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
		
		if(email1==false) {
			tfEmail.setBorder(BorderFactory.createLineBorder(Color.RED,4));
		}
		
		if(mobileNumber1==false) {
			tfPhoneNumber.setBorder(BorderFactory.createLineBorder(Color.RED,4));
		}
		return false;
	}

	
	private void backBorderToGray() {
		tfUsernameSignUp.setBorder(BorderFactory.createLineBorder(Color.GRAY,1));
		tfEmail.setBorder(BorderFactory.createLineBorder(Color.GRAY,1));
		tfName.setBorder(BorderFactory.createLineBorder(Color.GRAY,1));
		tfSurname.setBorder(BorderFactory.createLineBorder(Color.GRAY,1));
		tfPhoneNumber.setBorder(BorderFactory.createLineBorder(Color.GRAY,1));
		
	}

	protected boolean FieldComplete(String username, String password, String confirmPassword, String name,
			String surname, String email, String phoneNumber, String gender) {
		if(username.length()<1 || password.length()<1 || confirmPassword.length()<1 || name.length()<1 || surname.length()<1 || email.length()<1 || phoneNumber.length()<1 || gender==null) {
			return false;
		}
		return true;
	}
}
