package com.myteam.work.controller;

import java.util.HashMap;
import java.util.Map;

import com.myteam.work.gui.pages.TeacherPage;
import com.myteam.work.management.data.DataTableParser;
import com.myteam.work.management.data.Pair;
import com.myteam.work.management.data.Semester;
import com.myteam.work.management.data.Subject;
import com.myteam.work.management.data.TeachClass;
import com.myteam.work.management.handler.SemesterHandler;
import com.myteam.work.management.handler.StudentHandler;
import com.myteam.work.management.handler.SubjectHandler;
import com.myteam.work.management.handler.TeachClassHandler;
import com.myteam.work.management.handler.TeacherHandler;

import lombok.extern.slf4j.Slf4j;


@Slf4j
public class TeacherPageEventController {
	private static TeacherPageEventController tpec;
	private SubjectHandler sh;
	private StudentHandler sth;
	private TeacherHandler th;
	private TeachClassHandler tch;
	private SemesterHandler seh;
	private DataTableParser parser;
	private HashMap<Pair<Integer, Integer>, Object> changeRecorder;
	private int test1, test2, endtest;

	private TeacherPageEventController() {
		this.sh = new SubjectHandler();
		this.sth = new StudentHandler();
		this.th = new TeacherHandler();
		this.tch = new TeachClassHandler();
		this.seh = new SemesterHandler();
		this.parser = new DataTableParser();
		this.changeRecorder = new HashMap<>();
	}

	public static TeacherPageEventController getController() {
		if(tpec == null) tpec = new TeacherPageEventController();

		return tpec;
	}

	public void loadAllSubject() {
		log.info("Load all subject");
		var table = ((TeacherPage) TeacherPage.getPage()).getSubjectTable();
		table.clearData();
		var subjects = this.sh.getAllSubject();

		if(subjects == null) return;

		table.addData(this.parser.parseSubjectFetchPrerequisites(subjects));
	}

	public void searchSubject(String s) {
		var table = ((TeacherPage) TeacherPage.getPage()).getSubjectTable();
		table.clearData();
		var subjects = this.sh.getSubject(s);

		if(subjects == null) return;

		table.addData(this.parser.parseSubjectFetchPrerequisites(subjects));
	}

	public void loadTeacherSubject() {
		var subjects = this.sh.loadTeacherSubject(LoginController.getController().getCurrentUser().getId());
		var selector = ((TeacherPage) TeacherPage.getPage()).getSubjectSelector();
		selector.removeAllItems();
		selector.addItem(null);

		if(subjects == null) return;

		for(Subject subject : subjects) selector.addItem(subject);
	}

	public void loadSemester() {
		var semesters = this.seh.getAllSemester();
		var selector = ((TeacherPage) TeacherPage.getPage()).getSemesterSelector();
		selector.removeAllItems();
		selector.addItem(null);

		if(semesters == null) return;

		for(Semester sm : semesters) selector.addItem(sm);
	}

	public void loadTeachClass(Semester sm, Subject s) {
		var selector = ((TeacherPage) TeacherPage.getPage()).getClassSelector();
		selector.removeAllItems();
		selector.addItem(null);

		if(sm == null || s == null) return;

		var clazz = this.tch.getClass(sm.getId(), LoginController.getController().getCurrentUser().getId(), s.getId());

		if(clazz == null) return;

		for(TeachClass tc : clazz) selector.addItem(tc);
	}

	public void loadStudentInTeachClass(TeachClass tc) {
		var studentTable = ((TeacherPage) TeacherPage.getPage()).getStudentTable();
		studentTable.clearData();

		if(tc == null)	return;
		var studentList = this.sth.loadStudentListInfo(tc.getId());

		if(studentList == null) return;

		var data = this.parser.parseInfoFetchData(studentList);

		if(data == null) return;

		studentTable.addData(data);
	}

	public HashMap<Pair<Integer, Integer>, Object> getRecorder() {
		return this.changeRecorder;
	}	

	public void submit(TeachClass tc) {
		var iterator = this.changeRecorder.entrySet().iterator();

		while(iterator.hasNext()) {
			var entry = (Map.Entry) iterator.next();
			var key = (Pair<Integer, Integer>) entry.getKey();
			var value = entry.getValue();

			if(key.second() == 3) {
				this.sh.submit1(test1);
			} else if(key.second() == 4) {
				this.sh.submit2(test2);
			} else if(key.second() == 5) {
				this.sh.endtest(endtest);
			} else {
				log.error("System has a breach");
				System.exit(0);
			}
		}

		loadStudentInTeachClass(tc);
	}
}
