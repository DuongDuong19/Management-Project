package com.myteam.work.management.handler;

import java.sql.Connection;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StudentHandler {
	private Connection connection;

	public StudentHandler() {
		this.connection = SQLHandler.getConnection();
	}
}
