package com.myteam.work.controller;

import java.awt.CardLayout;
import java.awt.BorderLayout;

import javax.swing.JPanel;

import com.myteam.work.gui.Window;
import com.myteam.work.gui.pages.PageHeader;
import com.myteam.work.gui.pages.TeacherPage;
import com.myteam.work.gui.pages.ManagerPage;
import com.myteam.work.gui.pages.TeacherHomePage;
import com.myteam.work.gui.pages.ManagerHomePage;

public class PageController {
	private static PageController pc;
	private static CardLayout pageSwitcher;

	private PageController() {
		this.pageSwitcher = Window.getWindow().getPageSwitcher();
	}

	public static PageController getController() {
		if(pc == null) pc = new PageController();

		return pc;
	}

	public void getLoginPage() {
		pageSwitcher.show(Window.getWindow().getContentPane(), "login");
		setUsername("");
	}

	public void getManagerHomePage(String username) {
		pageSwitcher.show(Window.getWindow().getContentPane(), "managerHome");
		getHeader(ManagerHomePage.getPage());
		setMenu(false);
	}

	public void getTeacherHomePage(String username) {
		pageSwitcher.show(Window.getWindow().getContentPane(), "teacherHome");
		getHeader(TeacherHomePage.getPage());
		setMenu(false);
	}

	public void getTeacherPage() {
		pageSwitcher.show(Window.getWindow().getContentPane(), "teacher");
		getHeader(TeacherPage.getPage());
		setMenu(true);
	}

	public void getManagerPage() {
		pageSwitcher.show(Window.getWindow().getContentPane(), "manager");
		getHeader(ManagerPage.getPage());
		setMenu(true);
	}

	public void setUsername(String username) {
		((PageHeader) PageHeader.getPage()).updateUsername(username);
	}

	private void getHeader(JPanel needed) {
		needed.add(PageHeader.getPage(), BorderLayout.NORTH);
	}

	private void setMenu(boolean menu) {
		((PageHeader) PageHeader.getPage()).setMenu(menu);
	}
}
