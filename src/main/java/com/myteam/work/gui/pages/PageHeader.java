package com.myteam.work.gui.pages;

import java.awt.Font;
import java.awt.Color;
import java.awt.Image;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JSeparator;
import javax.swing.BorderFactory;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import com.myteam.work.gui.Window;
import com.myteam.work.Configuration;
import com.myteam.work.controller.LoginController;

public class PageHeader extends JPanel {
	private static final Color background = new Color(231, 76, 60);
	private static final ImageIcon menuIcon = new ImageIcon(new ImageIcon(PageHeader.class.getClassLoader().getResource("menu.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
	private static final Font defaultFont = new JLabel().getFont();
	private static final Font headerFont = new Font(defaultFont.getName(), Font.BOLD | Font.ITALIC, defaultFont.getSize() * 2);
	private static Configuration config = Configuration.getConfiguration();
	private static PageHeader ph;
	private JButton menuBtn;
	private boolean menuOn;
	private JLabel userLabel;

	private PageHeader() {
		super(new BorderLayout(20, 0));
		var windowSize = Window.getWindow().getSize();
		this.setSize(new Dimension(windowSize.width, windowSize.height / 10));
		this.setBackground(background);
		this.setBorder(BorderFactory.createCompoundBorder(
			BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(211, 211, 211)),
			BorderFactory.createEmptyBorder(20, 30, 20, 30)
		));
		var btnPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 10));
		btnPanel.setOpaque(false);
		this.menuBtn = new JButton();
		this.menuBtn.setBackground(background);
		this.menuBtn.setIcon(null);
		this.menuBtn.setCursor(config.getHandCursor());
		this.menuOn = false;
		this.menuBtn.setBorder(new LineBorder(Color.BLACK));
		btnPanel.add(this.menuBtn);
		var titleLabel = new JLabel("Score Management System");
		titleLabel.setFont(headerFont);
		titleLabel.setForeground(Color.WHITE);
		var userPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 15));
		userPanel.setBackground(background);
		this.userLabel = new JLabel("");
		userLabel.setForeground(Color.WHITE);
		var logoutBtn = new JButton("Log out");
		logoutBtn.setBorder(new LineBorder(Color.WHITE));
		logoutBtn.setForeground(Color.WHITE);
		logoutBtn.setBackground(background);
		logoutBtn.setCursor(config.getHandCursor());
		logoutBtn.addActionListener(e -> LoginController.getController().logout());
		userPanel.add(this.userLabel);
		userPanel.add(new JSeparator(SwingConstants.VERTICAL));
		userPanel.add(logoutBtn);
		this.add(btnPanel, BorderLayout.WEST);
		this.add(titleLabel, BorderLayout.CENTER);
		this.add(userPanel, BorderLayout.EAST);
	}

	public static JPanel getPage() {
		if(ph == null) ph = new PageHeader();

		return ph;
	}

	public void updateUsername(String username) {
		this.userLabel.setText(username);
	}

	public void setMenu(boolean menu) {
		if(menu) {
			if(!this.menuOn) {
				this.menuOn = !this.menuOn;
				this.menuBtn.setIcon(menuIcon);
				this.menuBtn.setEnabled(true);
			}
		} else if(this.menuOn) {
			this.menuBtn.setIcon(null);
			this.menuBtn.setEnabled(false);
			this.menuOn = !this.menuOn;
		};
	}
}
