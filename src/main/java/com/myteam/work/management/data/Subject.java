package com.myteam.work.management.data;

import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;

@Getter
@Setter
@EqualsAndHashCode
public class Subject {
	private int id;
	private short credits;
	private boolean required;
	private String subjectName;
	private List<Subject> prerequisites;

	public Subject(short credits, boolean required, String subjectName) {
		this.credits = credits;
		this.required = required;
		this.subjectName = subjectName;
		this.prerequisites = new ArrayList<>();
	}

	public Subject(short credits, boolean required, String subjectName, Subject... prerequisites) {
		this.credits = credits;
		this.required = required;
		this.subjectName = subjectName;
		this.prerequisites = new ArrayList<Subject>(Arrays.asList(prerequisites));
	}
}
