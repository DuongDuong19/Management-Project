package com.myteam.work.gui.pages;

import java.awt.CardLayout;
import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JLabel;

import lombok.Getter;

public class ManagerPage extends JPanel {
	private static ManagerPage mp;
	private CardLayout pager;
	private JPanel contentPanel;

	private ManagerPage() {
		this.setLayout(new BorderLayout());
		this.pager = new CardLayout();
		this.contentPanel = new JPanel(this.pager);
		this.contentPanel.add(studentManagementPage(), "student");
		this.contentPanel.add(subjectManagementPage(), "subject");
		this.contentPanel.add(teacherManagementPage(), "teacher");
		this.contentPanel.add(classPage(), "class");
		this.add(contentPanel, BorderLayout.CENTER);
	}

	public static JPanel getPage() {
		if(mp == null) mp = new ManagerPage();

		return mp;
	}

	public void changeContent(String content) {
		this.pager.show(this.contentPanel, content);
	}

	private JPanel studentManagementPage() {
		var contentPanel = new JPanel();
		contentPanel.add(new JLabel("student"));

		return contentPanel;
	}

	private JPanel subjectManagementPage() {
		var contentPanel = new JPanel();
		contentPanel.add(new JLabel("subject"));

		return contentPanel;
	}

	private JPanel teacherManagementPage() {
		var contentPanel = new JPanel();
		contentPanel.add(new JLabel("teacher"));

		return contentPanel;
	}

	private JPanel classPage() {
		var contentPanel = new JPanel();
		contentPanel.add(new JLabel("class"));

		return contentPanel;
	}
}
