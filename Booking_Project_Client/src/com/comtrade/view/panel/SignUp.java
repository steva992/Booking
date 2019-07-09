package com.comtrade.view.panel;

import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.comtrade.constants.PicturesURL;
import com.comtrade.constants.Regular_Expression;
import com.comtrade.constants.TransferClass_Message;
import com.comtrade.controlerKI.ControlerKI;
import com.comtrade.domain.GeneralDomain;
import com.comtrade.domain.user.User;
import com.comtrade.domain.user.User_Info;
import com.comtrade.genericClasses.GenericList;
import com.comtrade.transfer.TransferClass;
import com.comtrade.view.frame.Begin;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Pattern;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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

	/**
	 * Create the panel.
	 */
	public SignUp() {
		setLayout(null);
		setBounds(100,100,800,600);
		
		JLabel label = new JLabel("Username *");
		label.setBounds(45, 23, 164, 28);
		add(label);
		
		tfUsernameSignUp = new JTextField();
		tfUsernameSignUp.setColumns(10);
		tfUsernameSignUp.setBounds(45, 54, 164, 28);
		add(tfUsernameSignUp);
		
		JLabel label_1 = new JLabel("example(151)");
		label_1.setForeground(Color.RED);
		label_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_1.setBounds(219, 54, 124, 28);
		add(label_1);
		
		JLabel label_2 = new JLabel("Password *");
		label_2.setBounds(45, 98, 164, 28);
		add(label_2);
		
		pfPasswordSignup = new JPasswordField();
		pfPasswordSignup.setBounds(45, 128, 164, 31);
		add(pfPasswordSignup);
		
		JLabel label_3 = new JLabel("example12");
		label_3.setForeground(Color.RED);
		label_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_3.setBounds(219, 128, 124, 28);
		add(label_3);
		
		JLabel label_4 = new JLabel("Confirm Password *");
		label_4.setBounds(45, 170, 164, 28);
		add(label_4);
		
		pfConfirmPasswordSignUp = new JPasswordField();
		pfConfirmPasswordSignUp.setBounds(45, 209, 164, 31);
		add(pfConfirmPasswordSignUp);
		
		JLabel label_5 = new JLabel("example12");
		label_5.setForeground(Color.RED);
		label_5.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_5.setBounds(219, 204, 124, 28);
		add(label_5);
		
		JLabel label_6 = new JLabel("Name *");
		label_6.setBounds(45, 243, 164, 28);
		add(label_6);
		
		tfName = new JTextField();
		tfName.setColumns(10);
		tfName.setBounds(45, 282, 164, 28);
		add(tfName);
		
		JLabel label_7 = new JLabel("Example");
		label_7.setForeground(Color.RED);
		label_7.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_7.setBounds(219, 282, 124, 28);
		add(label_7);
		
		JLabel label_8 = new JLabel("Surname *");
		label_8.setBounds(45, 327, 164, 28);
		add(label_8);
		
		tfSurname = new JTextField();
		tfSurname.setColumns(10);
		tfSurname.setBounds(45, 372, 164, 28);
		add(tfSurname);
		
		JLabel label_9 = new JLabel("Example");
		label_9.setForeground(Color.RED);
		label_9.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_9.setBounds(219, 372, 124, 28);
		add(label_9);
		
		JLabel label_10 = new JLabel("Gender *");
		label_10.setBounds(45, 411, 164, 28);
		add(label_10);
		
		rbMan = new JRadioButton("Man");
		rbMan.setBounds(45, 446, 53, 23);
		add(rbMan);
		buttonGroup.add(rbMan);
		
		rbWoman = new JRadioButton("Woman");
		rbWoman.setBounds(120, 446, 93, 23);
		add(rbWoman);
		buttonGroup.add(rbWoman);
		
		
		
		JLabel label_11 = new JLabel("Email *");
		label_11.setBounds(49, 493, 164, 28);
		add(label_11);
		
		tfEmail = new JTextField();
		tfEmail.setColumns(10);
		tfEmail.setBounds(45, 520, 164, 28);
		add(tfEmail);
		
		JLabel label_12 = new JLabel("exanple@gmail.com");
		label_12.setForeground(Color.RED);
		label_12.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_12.setBounds(219, 520, 124, 28);
		add(label_12);
		
		JLabel label_13 = new JLabel("Phone Number *");
		label_13.setBounds(45, 559, 164, 28);
		add(label_13);
		
		tfPhoneNumber = new JTextField();
		tfPhoneNumber.setColumns(10);
		tfPhoneNumber.setBounds(45, 590, 164, 28);
		add(tfPhoneNumber);
		
		JLabel label_14 = new JLabel("065333444");
		label_14.setForeground(Color.RED);
		label_14.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_14.setBounds(219, 590, 124, 28);
		add(label_14);
		
		JButton btnStepTwo = new JButton("Step 2 >>>");
		btnStepTwo.setVisible(false);
		btnStepTwo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GenericList<GeneralDomain>list=grabForPanelForALL();
				try {
					if(list !=null) {
						TransferClass transferClass=ControlerKI.getInstance().checkIfUserExcist(list.get(0));
						User user=(User) transferClass.getServer_Object_Response();
						if(user.getId() > 0) {
							JOptionPane.showMessageDialog(null,TransferClass_Message.EXCIST_USERNAME.getValue());
						}else {
							user=(User) list.get(0);
							User_Info user_Info=(User_Info) list.get(1);
							JPanel propertyCreate=new Property_Created(user,user_Info);
							Begin.setPanelOnLayeredPane(propertyCreate);
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
		btnStepTwo.setBounds(365, 520, 140, 41);
		add(btnStepTwo);
		
		JButton btnSignUpPanel = new JButton("Sign Up");
		btnSignUpPanel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GenericList<GeneralDomain>list=grabForPanelForALL();
				try {
					if(list.size()>0) {
						String poruka=ControlerKI.getInstance().enterTheUserAndAdditionalUser(list).getMessage();
						JOptionPane.showMessageDialog(null,poruka);
						JPanel login=new Login();
						Begin.setPanelOnLayeredPane(login);
						User user=(User) list.get(0);
						File userFile=new File(PicturesURL.PROFILE_PICTURE_USERS.getValue()+"/"+user.getUsername());
						if(!userFile.exists()) {
							userFile.mkdir();
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
		btnSignUpPanel.setBounds(365, 577, 140, 41);
		add(btnSignUpPanel);
		
		cbProperty = new JCheckBox("You have Property");
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
		cbProperty.setBounds(365, 446, 140, 23);
		add(cbProperty);

	}
	
	protected GenericList<GeneralDomain> grabForPanelForALL() {
		GenericList<GeneralDomain>list=new GenericList<GeneralDomain>();
		String username=tfUsernameSignUp.getText();
		String password=String.copyValueOf(pfPasswordSignup.getPassword());
		String confirmPassword=String.copyValueOf(pfConfirmPasswordSignUp.getPassword());
		String name=tfName.getText();
		String surname=tfSurname.getText();
		String email=tfEmail.getText();
		String mobileNumber=tfPhoneNumber.getText();
		String gender = null;
		if(rbMan.isSelected()) {
			gender=rbMan.getText();
		}else if(rbWoman.isSelected()) {
			gender=rbWoman.getText();
		}
		//Treba naci kako da dovlacis sve slike sa servera
		String picture=PicturesURL.PROFILE_PICTURE_DEFAULT.getValue()+"/"+gender+".jpg";
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
