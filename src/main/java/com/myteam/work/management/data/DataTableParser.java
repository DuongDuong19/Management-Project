package com.myteam.work.management.data;

import java.util.LinkedList;
import java.util.List;

import com.myteam.work.management.handler.SubjectHandler;

public class DataTableParser {

	private static SubjectHandler sh;

	public static Object[][] parseSubjectFetchPrerequisites(List<Subject> subjects) {
		List<Object[]> data = new LinkedList<Object[]>();

		for(Subject subject : subjects) {
			var id = subject.getId();
			if (sh == null) sh = new SubjectHandler();
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
			if (studentList == null || studentList.isEmpty()) {
				return new Object[0][0];
			}

			int rows = studentList.size();
			int cols = studentList.get(0) == null ? 0 : studentList.get(0).size();

			Object[][] data = new Object[rows][cols];

			for (int i = 0; i < rows; i++) {
				List<String> row = studentList.get(i);
				if (row == null) continue;
				for (int j = 0; j < cols; j++) {
					data[i][j] = j < row.size() ? row.get(j) : null;
				}
			}

			return data;
	}
}
