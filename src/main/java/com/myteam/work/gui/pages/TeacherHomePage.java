package com.myteam.work.gui.pages;

import java.awt.Color;
import java.awt.BorderLayout;

import javax.swing.JPanel;

public class TeacherHomePage extends JPanel {
	private static TeacherHomePage thp;

	private TeacherHomePage() {
		this.setLayout(new BorderLayout());
	}

	public static JPanel getPage() {
		if(thp == null) thp = new TeacherHomePage();

		return thp;
	}
}
