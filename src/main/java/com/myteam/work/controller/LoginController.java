package com.myteam.work.controller;

import java.util.List;
import java.util.ArrayList;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import com.myteam.work.gui.Window;
import com.myteam.work.gui.pages.LoginPage;
import com.myteam.work.management.data.User;
import com.myteam.work.management.handler.SQLHandler;

@Slf4j
public class LoginController {
	private static LoginController lgCtrl;
	@Getter
	private User currentUser;

	LoginController() {}

	public static LoginController getController() {
		if(lgCtrl == null) lgCtrl = new LoginController();

		return lgCtrl;
	}

	public boolean login(String username, String password) {
		var result = SQLHandler.getUserByAuthentication(username, password);
		var logInfo = "Incorrect login as " + username + " password " + password;

		if(result != null) {
			this.currentUser = result;
			var userRealName = this.currentUser.getInfo().getName();
			logInfo = "Login as " + userRealName;
			var pc = PageController.getController();
			pc.setUsername(userRealName);

			if(this.currentUser.isRole()) pc.getTeacherPage(userRealName);
			else pc.getManagerPage(userRealName);
		}

		log.info(logInfo);

		return result != null;
	}

	public void logout() {
		log.info("Logout as" + this.currentUser.getInfo().getName());
		this.currentUser = null;
		PageController.getController().getLoginPage();
	}
}
