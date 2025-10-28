package com.myteam.work.gui;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.myteam.work.gui.pages.LoginPage;

public class Window extends JFrame {
	private static Window window;

	private JPanel currentPage;

	private Window() {
		var minimumSize = new Dimension(800, 600);
		this.setTitle("Score Managing System");
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setSize(minimumSize);
		this.setMinimumSize(minimumSize);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(true);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	public static Window getWindow() {
		if(window == null) window = new Window();

		return window;
	}

	public void switchPage(JPanel nextPage) {
		if(this.currentPage != null) this.remove(this.currentPage);

		this.currentPage = nextPage;
		this.add(this.currentPage);
		this.revalidate();
		this.repaint();
	}
}
