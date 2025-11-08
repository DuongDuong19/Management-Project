package com.myteam.work.management.data;

import java.time.format.DateTimeParseException;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
	private int id;
	private String authName;
	private String authPassword;
	private Information info;
	private boolean role;

	public User(String name, String day, String month, String year, String placeOfBirth, boolean sex, String authName, String authPassword, boolean role) throws DateTimeParseException {
		this.authName = authName;
		this.authPassword = authPassword;
		this.info = new Information(name, day, month, year, placeOfBirth, sex);
		this.role = role;
	}
}
