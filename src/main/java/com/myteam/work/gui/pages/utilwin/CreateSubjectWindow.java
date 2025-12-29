package com.myteam.work.gui.pages.utilwin;

import java.util.List;
import java.util.Collections;

import java.awt.Dimension;
import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTextField;

import com.myteam.work.gui.pages.MSTable;

public class CreateSubjectWindow extends JFrame {
	private static String defaultSubjectText = "Please enter subject name here";
	private static String defaultSubjectCredits = "0";
	private static String defaultSearchText = "Search by subject name or subject id";

	public CreateSubjectWindow() {
		this.setTitle("Create class");
		this.setSize(new Dimension(1000, 800));
this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setResizable(false);
		this.setLocationRelativeTo(null);

		var mainPanel = new JPanel(new BorderLayout());
		var topPanel = new JPanel();
		var choosenPanel = new JPanel();
		var choosenPrerequisitesTable = new MSTable(new String[]{"Id", "Subject name"}, List.<Class<?>>of(Integer.class, String.class), Collections.EMPTY_LIST);
		choosenPanel.add(choosenPrerequisitesTable.getDisplayer());
		var prerequisitesPanel = new JPanel();
		var subjectSearchPanel = new JPanel(new BorderLayout());
		var subjectSearch = new JTextField(defaultSearchText);
		var subjectTable = new MSTable(new String[]{"ID", "Subject name", "Prerequisites", "Credits", "Require"}, 
				List.<Class<?>>of(String.class, String[].class, Short.class, String.class), Collections.EMPTY_LIST);
		subjectSearchPanel.add(subjectSearch, BorderLayout.NORTH);
		subjectSearchPanel.add(subjectTable.getDisplayer(), BorderLayout.CENTER);
		var prerequisitesBtnPanel = new JPanel(new BorderLayout());
		var addPrerequisitesBtn = new JButton("Add Prerequisite");
		var removePrerequisitesBtn = new JButton("Remove Prerequisite");
		prerequisitesBtnPanel.add(addPrerequisitesBtn, BorderLayout.NORTH);
		prerequisitesBtnPanel.add(removePrerequisitesBtn, BorderLayout.SOUTH);
		prerequisitesPanel.add(subjectSearchPanel, BorderLayout.CENTER);
		prerequisitesPanel.add(prerequisitesBtnPanel, BorderLayout.SOUTH);
		var subjectName = new JTextField(defaultSubjectText);
		var credits = new JTextField(defaultSubjectCredits);
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
