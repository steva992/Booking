package com.comtrade.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.comtrade.constants.Regular_Expression;
import com.comtrade.controlerKI.ControlerKI;
import com.comtrade.domain.GeneralDomain;
import com.comtrade.domain.User;
import com.comtrade.domain.User_Info;
import com.comtrade.genericClasses.GenericList;
import com.comtrade.proxy.IProxy;
import com.comtrade.proxy.ProxyLogin;
import com.comtrade.transfer.TransferClass;
import com.comtrade.view.propertyCreated.PropertyForm;
import com.comtrade.view.user.forgotPassword.ForgotPassword;

import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;

import java.awt.CardLayout;
import javax.swing.JSpinner;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import java.awt.Font;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JComboBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingConstants;

public class LoginForm extends JFrame {

	private JPanel contentPane;
	private JTextField tfUsernameLogin;
	private JPasswordField pfPasswordLogin;
	private JTextField tfUsernameSignup;
	private JTextField tfName;
	private JTextField tfSurname;
	private JTextField tfEmail;
	private JTextField tfPhoneNumber;
	private JPasswordField pfConfirmPasswordSignUp;
	private JPasswordField pfPasswordSignUp;
	private JPanel panelForAll;
	private JLayeredPane layeredPane;
	private JCheckBox cbProperty;
	private JButton btnStepTwo,btnSignUpPanel;
	private JRadioButton rbMan,rbWoman;
	private ButtonGroup buttonGroup=new ButtonGroup();
	private JCheckBox chckbxNewCheckBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginForm frame = new LoginForm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginForm() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100,881, 659);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(27, 119, 328, 33);
		contentPane.add(lblNewLabel);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassword.setBounds(27, 203, 328, 33);
		contentPane.add(lblPassword);
		
		tfUsernameLogin = new JTextField();
		tfUsernameLogin.setBounds(27, 156, 328, 36);
		contentPane.add(tfUsernameLogin);
		tfUsernameLogin.setColumns(10);
		
		JButton btnLogIn = new JButton("Log In");
		btnLogIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username=tfUsernameLogin.getText();
				String password=String.valueOf(pfPasswordLogin.getPassword());
				User user=new User();
				user.setUsername(username);
				user.setPassword(password);
				IProxy iProxy=new ProxyLogin();
				try {
					iProxy.login(user);
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnLogIn.setForeground(Color.RED);
		btnLogIn.setBounds(108, 411, 157, 41);
		contentPane.add(btnLogIn);
		
		JButton btnSignUp = new JButton("Sing Up");
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				layeredPane.removeAll();
				layeredPane.add(panelForAll);
				layeredPane.revalidate();
			}
		});
		btnSignUp.setBounds(108, 476, 157, 41);
		contentPane.add(btnSignUp);
		
		layeredPane = new JLayeredPane();
		layeredPane.setBounds(390, 11, 476, 604);
		contentPane.add(layeredPane);
		layeredPane.setLayout(new CardLayout(0, 0));
		
		JPanel panel = new JPanel();
		layeredPane.add(panel, "name_15413900909851");
		
		panelForAll = new JPanel();
		panelForAll.setBackground(Color.CYAN);
		layeredPane.add(panelForAll, "name_11973441013692");
		panelForAll.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Username *");
		lblNewLabel_1.setBounds(10, 11, 164, 28);
		panelForAll.add(lblNewLabel_1);
		
		JLabel lblPassword_1 = new JLabel("Password *");
		lblPassword_1.setBounds(10, 86, 164, 28);
		panelForAll.add(lblPassword_1);
		
		JLabel lblConfirmPassword = new JLabel("Confirm Password *");
		lblConfirmPassword.setBounds(10, 158, 164, 28);
		panelForAll.add(lblConfirmPassword);
		
		JLabel lblName = new JLabel("Name *");
		lblName.setBounds(10, 231, 164, 28);
		panelForAll.add(lblName);
		
		JLabel lblSurname = new JLabel("Surname *");
		lblSurname.setBounds(10, 319, 164, 28);
		panelForAll.add(lblSurname);
		
		JLabel lblGender = new JLabel("Gender *");
		lblGender.setBounds(10, 403, 164, 28);
		panelForAll.add(lblGender);
		
		JLabel lblEmail = new JLabel("Email *");
		lblEmail.setBounds(14, 468, 164, 28);
		panelForAll.add(lblEmail);
		
		JLabel lblPhoneNumber = new JLabel("Phone Number *");
		lblPhoneNumber.setBounds(10, 534, 164, 28);
		panelForAll.add(lblPhoneNumber);
		
		JLabel lblNewLabel_2 = new JLabel("Picture");
		lblNewLabel_2.setBounds(255, 122, 164, 135);
		panelForAll.add(lblNewLabel_2);
		
		JButton btnNewButton_2 = new JButton("<");
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton_2.setBounds(268, 302, 67, 23);
		panelForAll.add(btnNewButton_2);
		
		JButton button = new JButton(">");
		button.setFont(new Font("Tahoma", Font.BOLD, 13));
		button.setBounds(352, 302, 67, 23);
		panelForAll.add(button);
		
		tfUsernameSignup = new JTextField();
		tfUsernameSignup.setBounds(10, 42, 164, 28);
		panelForAll.add(tfUsernameSignup);
		tfUsernameSignup.setColumns(10);
		
		tfName = new JTextField();
		tfName.setColumns(10);
		tfName.setBounds(10, 270, 164, 28);
		panelForAll.add(tfName);
		
		tfSurname = new JTextField();
		tfSurname.setColumns(10);
		tfSurname.setBounds(10, 364, 164, 28);
		panelForAll.add(tfSurname);
		
		tfEmail = new JTextField();
		tfEmail.setColumns(10);
		tfEmail.setBounds(14, 495, 164, 28);
		panelForAll.add(tfEmail);
		
		tfPhoneNumber = new JTextField();
		tfPhoneNumber.setColumns(10);
		tfPhoneNumber.setBounds(10, 565, 164, 28);
		panelForAll.add(tfPhoneNumber);
		
		pfConfirmPasswordSignUp = new JPasswordField();
		pfConfirmPasswordSignUp.setBounds(10, 197, 164, 23);
		panelForAll.add(pfConfirmPasswordSignUp);
		
		pfPasswordSignUp = new JPasswordField();
		pfPasswordSignUp.setBounds(10, 116, 164, 31);
		panelForAll.add(pfPasswordSignUp);
		
		rbMan = new JRadioButton("Man");
		rbMan.setBounds(10, 438, 53, 23);
		panelForAll.add(rbMan);
		buttonGroup.add(rbMan);
		
		rbWoman = new JRadioButton("Woman");
		rbWoman.setBounds(85, 438, 93, 23);
		panelForAll.add(rbWoman);
		buttonGroup.add(rbWoman);
		
		cbProperty = new JCheckBox("You Have A Property");
		cbProperty.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cbProperty.isSelected()) {
					btnStepTwo.setVisible(true);
					btnSignUpPanel.setVisible(false);
					
				}else {
					btnStepTwo.setVisible(false);
					btnSignUpPanel.setVisible(true);
				}
				
			}
		});
		cbProperty.setBounds(281, 11, 152, 41);
		panelForAll.add(cbProperty);
		
		btnSignUpPanel = new JButton("Sign Up");
		btnSignUpPanel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GenericList<GeneralDomain>list=grabForPanelForALL();
				try {
					String poruka=ControlerKI.getInstance().enterTheUserAndAdditionalUser(list).getMessage();
					JOptionPane.showMessageDialog(null,poruka);
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnSignUpPanel.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnSignUpPanel.setBounds(255, 505, 164, 45);
		panelForAll.add(btnSignUpPanel);
		
		btnStepTwo = new JButton("Go to Step 2 >>");
		btnStepTwo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GenericList<GeneralDomain>list=grabForPanelForALL();
				try {
					TransferClass transferClass=ControlerKI.getInstance().checkIfUserExcist(list.get(0));
					User user=(User) transferClass.getServer_Object_Response();
					if(user.getId() > 0) {
						JOptionPane.showMessageDialog(null,"!!! That Username Allready Excist !!!");
					}else {
						PropertyForm propertyForm=new PropertyForm(list.get(0),list.get(1));
						propertyForm.setVisible(true);
						dispose();
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
		btnStepTwo.setForeground(Color.BLACK);
		btnStepTwo.setVisible(false);
		btnStepTwo.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnStepTwo.setBounds(255, 455, 164, 45);
		panelForAll.add(btnStepTwo);
		
		pfPasswordLogin = new JPasswordField();
		pfPasswordLogin.setBounds(27, 239, 328, 36);
		contentPane.add(pfPasswordLogin);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("Hide / Unhide");
		chckbxNewCheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(chckbxNewCheckBox.isSelected()) {
					pfPasswordLogin.setEchoChar((char) 0);
				}else {
					pfPasswordLogin.setEchoChar('*');
				}
			}
		});
		chckbxNewCheckBox.setBounds(253, 304, 102, 23);
		contentPane.add(chckbxNewCheckBox);
		
		JButton btnNewButton = new JButton("Forgot Password ?");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ForgotPassword forgotPassword=new ForgotPassword();
				forgotPassword.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton.setBackground(Color.RED);
		btnNewButton.setBounds(27, 304, 143, 23);
		contentPane.add(btnNewButton);
	}

	protected GenericList<GeneralDomain> grabForPanelForALL() {
		GenericList<GeneralDomain>list=new GenericList<GeneralDomain>();
		String username=tfUsernameSignup.getText();
		String password=String.copyValueOf(pfPasswordSignUp.getPassword());
		String confirmPassword=String.copyValueOf(pfConfirmPasswordSignUp.getPassword());
		String name=tfName.getText();
		String surname=tfSurname.getText();
		String email=tfEmail.getText();
		String mobileNumber=tfPhoneNumber.getText();
		String gender = null;
		if(rbMan.isSelected()) {
			gender="Man";
		}else if(rbWoman.isSelected()) {
			gender="Woman";
		}
		//Treba naci kako da dovlacis sve slike sa servera
		String picture="Stevan";
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
					User_Info userAdditional=new User_Info();
					userAdditional.setName(name);
					userAdditional.setEmail(email);
					userAdditional.setGender(gender);
					userAdditional.setMobileNumber(mobileNumber);
					userAdditional.setPictureURL(picture);
					userAdditional.setSurname(surname);
					list.add(user);
					list.add(userAdditional);
				}else {
					JOptionPane.showMessageDialog(null,"!!! Incorrect password entry !!!");
				}
			}else {
				JOptionPane.showMessageDialog(null,"!!! All fields must be filled !!!");
			}
		}else { 
			JOptionPane.showMessageDialog(null,"!!! Incorrectly entered data !!!");
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
			tfUsernameSignup.setBorder(BorderFactory.createLineBorder(Color.RED,4));
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
		tfUsernameSignup.setBorder(BorderFactory.createLineBorder(Color.GRAY,1));
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
