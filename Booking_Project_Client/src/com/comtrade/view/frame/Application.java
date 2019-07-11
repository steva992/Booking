package com.comtrade.view.frame;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.comtrade.constants.Panel_Dimension;
import com.comtrade.panel.common.Login;
import com.comtrade.panel.common.SignUp;

import java.awt.CardLayout;
import javax.swing.JLayeredPane;

public class Application extends JFrame {

	private static JLayeredPane layeredPane=new JLayeredPane();
	private  JPanel login=new Login();
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Application frame = new Application();
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
	public Application() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(Panel_Dimension.X.getValue(),Panel_Dimension.Y.getValue(),Panel_Dimension.WIDTH.getValue(),Panel_Dimension.HEIGHT.getValue());
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
