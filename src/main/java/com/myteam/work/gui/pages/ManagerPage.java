package com.myteam.work.gui.pages;

import java.awt.BorderLayout;

import javax.swing.JPanel;

public class ManagerPage extends JPanel {
	private static ManagerPage mp;

	private ManagerPage() {
		this.setLayout(new BorderLayout());
	}

	public static JPanel getPage() {
		if(mp == null) mp = new ManagerPage();

		return mp;
	}

	private JPanel studentManagementPage() {

	}

	private JPanel subjectManagementPage() {

	}

	private JPanel teacherManagementPage() {

	}

	private JPanel classPage () {

	}
}
