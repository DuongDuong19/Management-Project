package com.myteam.work.gui.pages.utilwin;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CreateClassWindow extends JFrame {
	public CreateClassWindow() {
		this.setTitle("Create class");
		this.setSize(new Dimension(800, 600));
this.setDefaultCloseOperation(JFRAME.DISPOSE_ON_CLOSE);
		this.setResizable(false);
		this.setLocationRelativeTo(null);

		var mainPanel = new JPanel();		

		this.add(mainPanel);
		this.setVisible(true);
	}
}
