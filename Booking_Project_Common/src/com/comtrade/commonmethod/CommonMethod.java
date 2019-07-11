package com.comtrade.commonmethod;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.tools.JavaFileObject;

import com.comtrade.constants.PicturesURL;

public class CommonMethod {
	
	private static Image refactoringSetPictureMethod(String URL) {
		ImageIcon image= new ImageIcon(URL);
		image.getImage().flush();
		return image.getImage();
	}
	
	public static void setNewPicutreOnLabel(String pictureURL, JLabel lblPicture) {
		Image image1=refactoringSetPictureMethod(pictureURL);
		Image newImg=image1.getScaledInstance(lblPicture.getWidth(),lblPicture.getHeight(),Image.SCALE_SMOOTH);
		lblPicture.setIcon(new ImageIcon(newImg));
	}

	

	public static void setNewPicutreOnButton(String pictureURL, JButton btnNewButton_3) {
		Image image1=refactoringSetPictureMethod(pictureURL);
		Image newImg=image1.getScaledInstance(btnNewButton_3.getWidth()/2,btnNewButton_3.getHeight()/2,Image.SCALE_SMOOTH);
		btnNewButton_3.setIcon(new ImageIcon(newImg));
		
	}
}
