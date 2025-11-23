package com.myteam.work.gui.pages;

import java.awt.BorderLayout;

import javax.swing.JPanel;

public class ManagerHomePage extends JPanel {
	private static ManagerHomePage mhp;

	private ManagerHomePage() {
		this.setLayout(new BorderLayout());
	}

	public static JPanel getPage() {
		if(mhp == null) mhp = new ManagerHomePage();

		return mhp;
	}
}
