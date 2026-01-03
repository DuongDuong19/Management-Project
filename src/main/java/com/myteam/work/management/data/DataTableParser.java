package com.myteam.work.management.data;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

import com.myteam.work.management.handler.SubjectHandler;
import com.myteam.work.management.handler.StudentHandler;
import com.myteam.work.management.handler.TeacherHandler;

@Slf4j
public class DataTableParser {
	private SubjectHandler sh;
	private StudentHandler sth;
	private TeacherHandler th;

	public DataTableParser() {
		this.sh = new SubjectHandler();
		this.sth = new StudentHandler();
		this.th = new TeacherHandler();
	}

	public Object[][] parseSubjectFetchPrerequisites(List<Subject> subjects) {
		List<Object[]> data = new LinkedList<Object[]>();

		for(Subject subject : subjects) data.add(parseSubjectWithPrerequisite(subject));

		return data.toArray(Object[][]::new);
	}

	public Object[][] parseTeacherFetch(List<User> users) {
		List<Object[]> data = new LinkedList<Object[]>();

		var unique = new HashMap<Integer, User>();
		for (User user : users) {
			if (!unique.containsKey(user.getId())) unique.put(user.getId(), user);
		}

		for (User user : unique.values()) data.add(parseTeacherWithInformation(user));

		return data.toArray(Object[][]::new);
	}

	public Object[][] parseStudent(List<Student> students) {
		List<Object[]> data = new LinkedList<Object[]>();

		for(Student student : students) data.add(parseStudent(student));

		return data.toArray(Object[][]::new);
	}

	public Object[][] parseStudentFetch(List<Student> students, List<Integer> excludes) {
		/*// List<Object[]> data = new LinkedList<Object[]>();

		// for(Student student : students) data.add(parseStudentWithInformation(student));

		// return data.toArray(Object[][]::new);
		List<Object[]> data = new LinkedList<Object[]>();

		// Deduplicate users by id to avoid repeated rows when SQL joins return multiple
		// result rows for the same teacher (one per subject/class).
		var unique = new HashMap<Integer, Student>();
		for (Student student : students) {
			if (!unique.containsKey(student.getId())) unique.put(student.getId(), student);
		}

		for (Student student : unique.values()) data.add(parseStudentWithInformation(student));

		return data.toArray(Object[][]::new);*/
		List<Object[]> data = new LinkedList<Object[]>();
		for(Student student : students) {
			if(excludes.contains(student.getId()))	continue;
			data.add(parseStudent(student));
		}
		return data.toArray(Object[][]::new);
	}

	public Object[][] parseSubjectFetchPrerequisites(List<Subject> subjects, List<Integer> excludes) {
		List<Object[]> data = new LinkedList<Object[]>();

		for(Subject subject : subjects) {
			if(excludes.contains(subject.getId())) continue;

			data.add(parseSubjectWithPrerequisite(subject));
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

	private Object[] parseSubjectWithPrerequisite(Subject subject) {
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
			
		return subjectRow;
	}

	private Object[] parseTeacherWithInformation(User user) {
		var id = user.getId();
		var subjects = this.sh.loadTeacherSubject(id);
		var teacherName = new String[subjects == null ? 0 : subjects.size()];

		for(var i = 0; i < teacherName.length; i++) teacherName[i] = subjects.get(i).getSubjectName();

		var teacherRow = new Object[9];
		teacherRow[0] = id;
		teacherRow[1] = user.getInfo().getName();
		teacherRow[2] = user.getAuthName();
		teacherRow[3] = user.getAuthPass();
		teacherRow[4] = user.getInfo().getBirth();
		teacherRow[5] = user.getInfo().getPlaceOfBirth();
		teacherRow[6] = user.getInfo().isSex() ? "Male" : "Female";
		teacherRow[7] = teacherName;
		teacherRow[8] = new String[0];
			
		return teacherRow;
	}

	private Object[] parseStudent(Student student) {
		var studentRow = new Object[7];
		studentRow[0] = student.getId();
		studentRow[1] = student.getInfo().getName();
		studentRow[2] = student.getInfo().getBirth();
		studentRow[3] = student.getInfo().getPlaceOfBirth();
		studentRow[4] = student.getInfo().isSex() ? "Male" : "Female";
		studentRow[5] = student.getGeneration();
		studentRow[6] = student.getGpa();
			
		return studentRow;
	}
}
