package com.comtrade.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.comtrade.threads.ServerThread;

import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ServerForm extends JFrame {

	private JPanel contentPane;
	private JTextArea textArea;

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
		setBounds(100, 100, 789, 556);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Start Server");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ServerThread serverThread=new ServerThread();
				serverThread.start();
			}
		});
		btnNewButton.setBounds(10, 10, 753, 43);
		contentPane.add(btnNewButton);
		
		JLabel lblWatch = new JLabel("Watch");
		lblWatch.setHorizontalAlignment(SwingConstants.CENTER);
		lblWatch.setBounds(78, 64, 630, 97);
		contentPane.add(lblWatch);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 240, 753, 266);
		contentPane.add(scrollPane);
		
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
	}
}
