package com.myteam.work.gui.pages;

import javax.swing.JPanel;
import javax.swing.JButton;

public class TopPage extends JPanel {
	private static TopPage tp;

	TopPage() {
		var menuButton = new JButton("Menu");
	}

	public static JPanel getPage() {
		if(tp == null) tp = new TopPage();

		return tp;
	}
}
