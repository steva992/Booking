package com.comtrade.view.frame;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.comtrade.view.panel.Login;
import com.comtrade.view.panel.SignUp;

import java.awt.CardLayout;
import javax.swing.JLayeredPane;

public class Begin extends JFrame {

	private static JLayeredPane layeredPane=new JLayeredPane();
	private  JPanel login=new Login();
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Begin frame = new Begin();
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
	public Begin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100,900,700);
		setContentPane(layeredPane);
		layeredPane.setBounds(100, 100,800,600);
		layeredPane.setLayout(new CardLayout(0, 0));
		layeredPane.add(login);
		
	}

	public static void setPanelOnLayeredPane(JPanel panel) {
		layeredPane.removeAll();
		layeredPane.add(panel);
		layeredPane.repaint();
		layeredPane.revalidate();
	}
}
