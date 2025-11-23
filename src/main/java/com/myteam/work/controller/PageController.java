package com.myteam.work.controller;

import java.awt.CardLayout;
import java.awt.LayoutManager;

import com.myteam.work.gui.Window;
import com.myteam.work.gui.pages.PageHeader;

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
		((PageHeader) PageHeader.getPage()).updateUsername("");
	}

	public void getManagerHomePage(String username) {
		pageSwitcher.show(Window.getWindow().getContentPane(), "managerHome");
		((PageHeader) PageHeader.getPage()).updateUsername(username);
	}

	public void getTeacherHomePage(String username) {
		pageSwitcher.show(Window.getWindow().getContentPane(), "teacherHome");
		((PageHeader) PageHeader.getPage()).updateUsername(username);
	}
}
