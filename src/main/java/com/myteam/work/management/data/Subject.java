package com.myteam.work.management.data;

import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collection;

import lombok.Getter;
import lombok.Setter;
import lombok.NonNull;
import lombok.EqualsAndHashCode;

@Getter
@EqualsAndHashCode
public class Subject {
	private int id;
	private short credits;
	@Setter
	private boolean required;
	@NonNull
	@Setter
	private String subjectName;

	public Subject(int id, short credits, boolean required, @NonNull String subjectName) {
		this.id = id;
		this.credits = credits;
		this.required = required;
		this.subjectName = subjectName;	
	}

	public void setId(int id) {
		if(id < 0) throw new IllegalArgumentException("ID cannot be less than 0");

		this.id = id;
	}

	public void setCredits(short credits) {
		if(credits < 1) throw new IllegalArgumentException("Credits cannot be less than 1");

		this.credits = credits;
	}

	public String toString() {
		return this.subjectName;
	}
}
