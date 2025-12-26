package com.myteam.work.gui.pages.utilwin;

import java.awt.Dimension;
import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTextField;

public class CreateSubjectWindow extends JFrame {
	public CreateSubjectWindow() {
		this.setTitle("Create class");
		this.setSize(new Dimension(800, 600));
this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setResizable(false);
		this.setLocationRelativeTo(null);

		var mainPanel = new JPanel(new BorderLayout());
		var topPanel = new JPanel();
		var choosenPanel = new JPanel();
		var prerequisitesPanel = new JPanel();
		var prerequisitesBtnPanel = new JPanel();
		var subjectName = new JTextField();
		var credits = new JTextField();
		var required = new JCheckBox("Required");
		topPanel.add(subjectName, BorderLayout.WEST);
		topPanel.add(credits, BorderLayout.CENTER);
		topPanel.add(required, BorderLayout.EAST);
		
		var submitBtn = new JButton("Submit");
		mainPanel.add(topPanel, BorderLayout.NORTH);
		mainPanel.add(choosenPanel, BorderLayout.WEST);
		mainPanel.add(prerequisitesPanel, BorderLayout.EAST);
		mainPanel.add(submitBtn, BorderLayout.SOUTH);
		this.add(mainPanel);
		this.setVisible(true);
	}
}
