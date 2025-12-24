package com.myteam.work.management.data;

import java.util.List;
import java.util.LinkedList;

public class DataTableParser {
	public static Object[][] parseSubjectFetchPrerequisites(List<Subject> subjects) {
		List<Object[]> data = new LinkedList<>();

		for(Subject subject : subjects) {
			var id = subject.getId();
			var prerequisites = sh.getPrerequistes(id);
			var prerequisitesName = new String[prerequisites == null ? 0 : prerequisites.size()];

			for(var i = 0; i < prerequisitesName.length; i++) prerequisitesName[i] = sh.getName(prerequisites.get(i));

			var subjectRow = new Object[5];
			subjectRow[0] = id;
			subjectRow[1] = subject.getSubjectName();
			subjectRow[2] = prerequisitesName;
			subjectRow[3] = subject.getCredits();
			subjectRow[4] = subject.isRequired() ? "yes" : "no";
			data.add(subjectRow);
		}

		return data.toArray(Object[][]::new);
	}

	public static Object[][] parseInfoFetchData(List<List<String>> studentList) {

	}
}
