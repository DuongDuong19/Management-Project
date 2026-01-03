package com.myteam.work.controller;

import java.util.HashMap;
import java.util.Map;

import com.myteam.work.gui.pages.ManagerPage;
import com.myteam.work.management.data.DataTableParser;
import com.myteam.work.management.data.Pair;
import com.myteam.work.management.data.Semester;
import com.myteam.work.management.data.Student;
import com.myteam.work.management.data.Subject;
import com.myteam.work.management.data.TeachClass;
import com.myteam.work.management.data.User;
import com.myteam.work.management.handler.SemesterHandler;
import com.myteam.work.management.handler.StudentHandler;
import com.myteam.work.management.handler.SubjectHandler;
import com.myteam.work.management.handler.TeachClassHandler;
import com.myteam.work.management.handler.TeacherHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ManagerPageEventController {
    private static ManagerPageEventController mpec;
    private SubjectHandler sh;
    private StudentHandler sth;
	private TeacherHandler th;
	private TeachClassHandler tch;
	private SemesterHandler seh;
	private DataTableParser parser;
	private HashMap<Pair<Integer, Integer>, Object> changeRecorder;

    private ManagerPageEventController() {
		this.sh = new SubjectHandler();
		this.sth = new StudentHandler();
		this.th = new TeacherHandler();
		this.tch = new TeachClassHandler();
		this.seh = new SemesterHandler();
		this.parser = new DataTableParser();
	}

    public static ManagerPageEventController getController() {
		if(mpec == null) mpec = new ManagerPageEventController();

		return mpec;
	}


    public void loadSubjectAllSubject() {
        log.info("Load all subject");

        var table = ((ManagerPage) ManagerPage.getPage()).getSubjectTable();
		table.clearData();
		var subjects = this.sh.getAllSubject();

		if(subjects == null) return;

		table.addData(this.parser.parseSubjectFetchPrerequisites(subjects));
    }

	public void loadManagementAllSubject() {
		var subjects = this.sh.getAllSubject();
		var selector = ((ManagerPage) ManagerPage.getPage()).getClassManagementSubjectSelector();
		selector.removeAllItems();
		selector.addItem(null);

		if(subjects == null) return;

		for(Subject subject : subjects) selector.addItem(subject);
	}

    public void searchSubject(String s) {
        log.info("Search subject: " + s);
		var table = ((ManagerPage) ManagerPage.getPage()).getSubjectTable();
		table.clearData();
		var teachers = this.sh.getSubject(s);

		if(teachers == null) return;

		table.addData(this.parser.parseSubjectFetchPrerequisites(teachers));
    }

	public void loadTeacherSubject() {
		var teachers = this.th.loadTeacher("");
		var selector = ((ManagerPage) ManagerPage.getPage()).getTeacherSelector();
		selector.removeAllItems();
		selector.addItem(null);

		if(teachers == null) return;

		for(User teacher : teachers) selector.addItem(teacher);
	}

	public void loadStudent() {
		var students = this.sth.loadStudent("");
		var selector = ((ManagerPage) ManagerPage.getPage()).getStudentSelector();
		if(selector == null)	return;
		selector.removeAllItems();
		selector.addItem(null);

		if(students == null) return;

		for(Student student : students) selector.addItem(student);
	}

	public void searchTeacher(String s) {
		log.info("Search teacher: " + s);
		var table = ((ManagerPage) ManagerPage.getPage()).getTeacherTable();
		table.clearData();
		var teachers = this.th.loadTeacher(s);

		if(teachers == null) return;

		table.addData(this.parser.parseTeacherFetch(teachers));
	}

	public void searchStudent(String s) {
		var table = ((ManagerPage) ManagerPage.getPage()).getStudentTable();
		table.clearData();
		var students = this.sth.loadStudent(s);

		if(students == null) return;

		table.addData(this.parser.parseStudentFetch(students));
	}

	public void loadClassSemester() {
		var semesters = this.seh.getAllSemester();
		var selector = ((ManagerPage) ManagerPage.getPage()).getClassSemesterSelector();
		selector.removeAllItems();
		selector.addItem(null);

		if(semesters == null) return;

		for(Semester sm : semesters) selector.addItem(sm);
	}

	public void loadManagementSemester() {
		var semesters = this.seh.getAllSemester();
		var selector = ((ManagerPage) ManagerPage.getPage()).getClassManagementSemesterSelector();
		selector.removeAllItems();
		selector.addItem(null);

		if(semesters == null) return;

		for(Semester sm : semesters) selector.addItem(sm);
	}

	public void loadManagementTeachClass(Semester sm, Subject s, TeachClass tc) {
		var selector = ((ManagerPage) ManagerPage.getPage()).getClassManagementClassSelector();
		selector.removeAllItems();
		selector.addItem(null);

		if(sm == null || s == null) return;

		var clazz = this.tch.getClass(sm.getId(), s.getId(), tc.getId());

		if(clazz == null) return;

		for(TeachClass tcs : clazz) selector.addItem(tcs);
	}

	public void loadStudentInTeachClass(TeachClass tc) {
		var studentTable = ((ManagerPage) ManagerPage.getPage()).getStudentClassTable();
		studentTable.clearData();

		if(tc == null)	return;
		var studentList = this.sth.loadStudentListInfo(tc.getId());

		if(studentList == null) return;

		var data = this.parser.parseInfoFetchData(studentList);
		
		if(data == null) return;

		studentTable.addData(data);
	}

	public void loadAllSubject() {
		log.info("Load all subject");
		var table = ((ManagerPage) ManagerPage.getPage()).getSubjectTable();
		table.clearData();
		var subjects = this.sh.getAllSubject();

		if(subjects == null) return;

		table.addData(this.parser.parseSubjectFetchPrerequisites(subjects));
	}

	public void loadTeachClass(Semester semester, Subject subject) {
		var selector = ((ManagerPage) ManagerPage.getPage()).getClassManagementClassSelector();
		selector.removeAllItems();
		selector.addItem(null);

		if(semester == null || subject == null) return;

		var clazz = this.tch.getClass(semester.getId(), subject.getId());

		if(clazz == null) return;

		for(TeachClass tc : clazz) selector.addItem(tc);
	}

	public void deleteSubject(int id) {
		this.sh.deleteSubject(id);
	}	

	public void submit(TeachClass tc) {
		var iterator = this.changeRecorder.entrySet().iterator();

		while(iterator.hasNext()) {
			var entry = (Map.Entry) iterator.next();
			var key = (Pair<Integer, Integer>) entry.getKey();
			var value = entry.getValue();

			if(key.second() == 3) {

			} else if(key.second() == 4) {
				
			} else if(key.second() == 5) {
				
			} else {
				log.error("System has a breach");
				System.exit(0);
			}
		}

		loadStudentInTeachClass(tc);
	}

	public void newSubject(Subject s) {
	}

	public void deleteSubject(String s) {
		
	}

	public void AddStudent() {

	}

	public void RemoveStudent() {

	}
	

}
