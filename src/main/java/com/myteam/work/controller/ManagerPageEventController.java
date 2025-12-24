package com.myteam.work.controller;

import com.myteam.work.gui.pages.ManagerPage;
import com.myteam.work.management.data.DataTableParser;
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
public class ManagerPageEventController {
    private static ManagerPageEventController mpec;
    private SubjectHandler sh;
    private StudentHandler sth;
	private TeacherHandler th;
	private TeachClassHandler tch;
	private SemesterHandler seh;

    private ManagerPageEventController() {
		this.sh = new SubjectHandler();
		this.sth = new StudentHandler();
		this.th = new TeacherHandler();
		this.tch = new TeachClassHandler();
		this.seh = new SemesterHandler();
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

		table.addData(DataTableParser.parseSubjectFetchPrerequisites(subjects));
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
		var subjects = this.sh.getSubject(s);

		if(subjects == null) return;

		table.addData(DataTableParser.parseSubjectFetchPrerequisites(subjects));
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

	public void loadStudentInTeachClass(Semester s, TeachClass tc, Subject sj) {
		log.info("load student list in: " + tc.toString() + " of " + sj.toString() + " in " + s.toString());
		var studentTable = ((ManagerPage) ManagerPage.getPage()).getStudentClassTable();
		studentTable.clearData();
		var studentList = this.sth.loadStudentListInfo(s.getId(), tc.getId(), s.getId());

		if(studentList == null) return;
		
		studentTable.addData(DataTableParser.parseInfoFetchData(studentList));
	}

	public void loadAllSubject() {

	}

	public void loadTeachClass(Semester semester, Subject subject) {

	}
}
