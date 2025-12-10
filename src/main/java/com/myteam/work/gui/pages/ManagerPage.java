package com.myteam.work.gui.pages;

import java.awt.CardLayout;
import java.awt.BorderLayout;

import java.util.List;
import java.util.Collections;

import javax.swing.JPanel;
import javax.swing.JLabel;

import lombok.Getter;

public class ManagerPage extends JPanel {
	private static ManagerPage mp;
	private CardLayout pager;
	private JPanel contentPanel;
	@Getter
	private MSTable studentTable;
	@Getter
	private MSTable subjectTable;
	@Getter
	private MSTable teacherTable;
	@Getter
	private MSTable classTable;
	@Getter
	private MSTable semesterTable;
	@Getter
	private MSTable studentClassTable;

	private ManagerPage() {
		this.setLayout(new BorderLayout());
		this.pager = new CardLayout();
		this.contentPanel = new JPanel(this.pager);
		this.contentPanel.add(studentManagementPage(), "student");
		this.contentPanel.add(subjectManagementPage(), "subject");
		this.contentPanel.add(teacherManagementPage(), "teacher");
		this.contentPanel.add(classPage(), "class");
		this.contentPanel.add(classManagement(), "classManagement");
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

		this.studentTable = new MSTable(new String[]{"ID", "Student Name", "Birth", "Place of birth", "Sex", "Generation", "Gpa"},
				List.<Class<?>>of(Integer.class, String.class, String.class, String.class, String.class, Short.class, Float.class), Collections.EMPTY_LIST);

		return contentPanel;
	}

	private JPanel subjectManagementPage() {
		var contentPanel = new JPanel();
		contentPanel.add(new JLabel("subject"));

		this.subjectTable = new MSTable(new String[]{"ID", "Subject name", "Prerequisites", "Credits", "Require"},
				List.<Class<?>>of(Integer.class, String.class, String.class, Short.class, String.class), Collections.EMPTY_LIST);

		return contentPanel;
	}

	private JPanel teacherManagementPage() {
		var contentPanel = new JPanel();
		contentPanel.add(new JLabel("teacher"));

		this.teacherTable = new MSTable(new String[]{"ID", "Teacher name", "Username", "Password", "Birth", "Place of birth", "Sex", "Subject", "Teach class"},
				List.<Class<?>>of(Integer.class, String.class, String.class, String.class, String.class, String.class, String.class, String[].class, String[].class), Collections.EMPTY_LIST);

		return contentPanel;
	}

	private JPanel classPage() {
		var contentPanel = new JPanel();
		contentPanel.add(new JLabel("class"));

		this.semesterTable = new MSTable(new String[]{"ID", "Semester", "Year"}, List.<Class<?>>of(Integer.class, Short.class, Short.class), Collections.EMPTY_LIST);
		this.classTable = new MSTable(new String[]{"ID", "Class name", "Semester", "Subject", "GPA", "Teacher"},
				List.<Class<?>>of(Integer.class, String.class, String.class, String[].class, Float.class, String[].class), Collections.EMPTY_LIST);

		return contentPanel;
	}

	private JPanel classManagement() {
		var contentPanel = new JPanel();

		this.studentClassTable = new MSTable(new String[]{"ID", "Student name", "Sex", "Generation", "Test 1", "Test 2", "End test", "Total Score", "Normalized Score", "Rate"},
				List.<Class<?>>of(Integer.class, String.class, String.class, Short.class, Float.class, Float.class, Float.class, Float.class, Float.class, String.class), Collections.EMPTY_LIST);

		return contentPanel;
	}
}
