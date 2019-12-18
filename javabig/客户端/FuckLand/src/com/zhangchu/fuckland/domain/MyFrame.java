package com.zhangchu.fuckland.domain;

import java.awt.Font;

import javax.swing.JFrame;

public class MyFrame extends JFrame{

	private static final long serialVersionUID = 3847092626391037154L;
	
	public MyFrame() {
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 20));
	}
}
