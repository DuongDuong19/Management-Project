package com.myteam.work.management.data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
public class Information {
	private String name;
	private LocalDate birth;
	private String placeOfBirth;
	private boolean sex;

	public Information(String name, String day, String month, String year, String placeOfBirth, boolean sex) throws DateTimeParseException {
		this.name = name;
		this.birth = LocalDate.parse(day + "-" + month + "-" + year, DateTimeFormatter.ofPattern("dd-mm-yyyy"));
		this.placeOfBirth = placeOfBirth;
		this.sex = sex;
	}
}
