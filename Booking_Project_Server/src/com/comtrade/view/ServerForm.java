package com.comtrade.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.comtrade.commonmethod.CommonMethod;
import com.comtrade.constants.Panel_Dimension;
import com.comtrade.constants.PicturesURL;
import com.comtrade.controlerPL.ControlerPLServer;
import com.comtrade.threads.ServerThread;
import com.comtrade.threads.WatchThread;

import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;
import javax.swing.ImageIcon;

public class ServerForm extends JFrame {

	private JPanel contentPane;
	private JTextArea textArea;
	private JLabel lblBackGround;
	private JLabel lblWatch,lblDate;
	private JLabel lblNewLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ServerForm frame = new ServerForm();
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
	public ServerForm() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(Panel_Dimension.X.getValue(),Panel_Dimension.Y.getValue(),800,798);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Start Server");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ServerThread serverThread=new ServerThread(textArea);
				serverThread.start();
				WatchThread watchThread=new WatchThread(lblWatch);
				watchThread.start();
			}
		});
		btnNewButton.setBounds(277, 430, 200, 43);
		contentPane.add(btnNewButton);
		
		lblWatch = new JLabel("Time");
		lblWatch.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblWatch.setForeground(Color.WHITE);
		lblWatch.setHorizontalAlignment(SwingConstants.CENTER);
		lblWatch.setBounds(277, 216, 200, 97);
		contentPane.add(lblWatch);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 484, 753, 266);
		contentPane.add(scrollPane);
		
		textArea = new JTextArea();
		textArea.setBackground(Color.WHITE);
		textArea.setForeground(Color.BLACK);
		textArea.setEditable(false);
		textArea.setFont(new Font("Algerian", Font.PLAIN, 14));
		scrollPane.setViewportView(textArea);
		
		lblDate = new JLabel("Date");
		lblDate.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblDate.setForeground(Color.WHITE);
		lblDate.setHorizontalAlignment(SwingConstants.CENTER);
		lblDate.setBounds(47, 119, 194, 97);
		contentPane.add(lblDate);
		
		lblNewLabel = new JLabel("CURRENT DATE :");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel.setBounds(53, 11, 188, 97);
		contentPane.add(lblNewLabel);
		
		lblBackGround = new JLabel("New label");
		//lblBackGround.setIcon(new ImageIcon(ServerForm.class.getResource("/default1/Woman.jpg")));
		lblBackGround.setBounds(0,0,784,759);
		CommonMethod.setNewPicutreOnLabel(PicturesURL.PROJECT_PATH.getValue()+PicturesURL.PICTURE_FOR_SERVER.getValue()+"/Server.jpg",lblBackGround);
		contentPane.add(lblBackGround);
		ControlerPLServer.getInstance().setServerForm(this);
	}

	public void setTextOnTextArea(String string) {
		textArea.append(string);
		
	}
}
