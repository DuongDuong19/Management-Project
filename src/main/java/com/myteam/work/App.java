package com.myteam.work;

import com.myteam.work.gui.Window;
import com.myteam.work.controller.PageController;

public class App {
    public static void main(String[] args) {
		Window.getWindow().initializingPage();
		PageController.getController().getLoginPage();
    }
}
