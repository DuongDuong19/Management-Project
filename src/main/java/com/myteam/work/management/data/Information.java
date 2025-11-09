package com.myteam.work.management.data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import lombok.Getter;
import lombok.Setter;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@Getter
public class Information {
	@Setter
	@NonNull 
	private String name;
	private LocalDate birth;
	@Setter
	@NonNull
	private String placeOfBirth;
	@Setter
	private boolean sex;

	public Information(@NonNull String name, @NonNull String day, @NonNull String month, @NonNull String year, @NonNull String placeOfBirth, boolean sex) throws DateTimeParseException {
		this.name = name;
		this.birth = LocalDate.parse(day + "-" + month + "-" + year, DateTimeFormatter.ofPattern("dd-mm-yyyy"));
		this.placeOfBirth = placeOfBirth;
		this.sex = sex;
	}

	public void setBirth(@NonNull String day, @NonNull String month, @NonNull String year) throws DateTimeParseException {
		this.birth = LocalDate.parse(day + "-" + month + "-" + year, DateTimeFormatter.ofPattern("dd-mm-yyyy"));
	}
}
