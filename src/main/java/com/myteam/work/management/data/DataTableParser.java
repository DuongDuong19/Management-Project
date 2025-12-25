package com.myteam.work.management.data;

import java.util.LinkedList;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

import com.myteam.work.management.handler.SubjectHandler;
import com.myteam.work.management.handler.StudentHandler;

@Slf4j
public class DataTableParser {
	private SubjectHandler sh;
	private StudentHandler sth;

	public DataTableParser() {
		this.sh = new SubjectHandler();
		this.sth = new StudentHandler();
	}

	public Object[][] parseSubjectFetchPrerequisites(List<Subject> subjects) {
		List<Object[]> data = new LinkedList<Object[]>();

		for(Subject subject : subjects) {
			var id = subject.getId();

			var prerequisites = this.sh.getPrerequistes(id);
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

	public Object[][] parseInfoFetchData(List<List<Object>> studentList) {
		List<Object[]> studentRows = new LinkedList<Object[]>();

		for(List<Object> scores : studentList) {
			var studentRow = new Object[10];
			var student = this.sth.getStudent((Integer) scores.get(0));

			if(student == null) {
				log.error("Cannot parse student score");

				return null;
			}

			studentRow[0] = student.getId();
			studentRow[1] = student.getInfo().getName();
			studentRow[2] = student.getInfo().isSex() ? "Male" : "Female";
			studentRow[3] = student.getGeneration();
			
			for(var i = 4; i < 10; i++) studentRow[i] = scores.get(i - 3);

			studentRows.add(studentRow);
		}

		return studentRows.toArray(Object[][]::new);
	}
}
