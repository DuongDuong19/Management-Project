package com.myteam.work.gui.pages;

import java.awt.BorderLayout;

import javax.swing.JPanel;

public class TeacherPage extends JPanel {
	private static TeacherPage tp;

	private TeacherPage() {
		this.setLayout(new BorderLayout());
	}

	public static JPanel getPage() {
		if(tp == null) tp = new TeacherPage();

		return tp;
	}

	private JPanel studentManagementPage() {

	}

	private JPanel subjectManagementPage() {

	}
}
