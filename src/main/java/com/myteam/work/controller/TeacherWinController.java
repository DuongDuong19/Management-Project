package com.myteam.work.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.LinkedList;

import com.myteam.work.management.data.User;
import com.myteam.work.management.data.Triple;
import com.myteam.work.management.data.Subject;
import com.myteam.work.gui.pages.utilwin.TeacherWindow;
import com.myteam.work.management.handler.SubjectHandler;
import com.myteam.work.management.handler.TeacherHandler;
import com.myteam.work.management.handler.TeachClassHandler;

public class TeacherWinController {
	public void loadAvailableSubject(TeacherWindow tw) {
		var table = tw.getAvailableSubjectTable();
		table.clearData();
		
		var subjects = new SubjectHandler().getAllSubject();

		if(subjects == null) return;
	
		var existedTableModel = tw.getChoosenTeacherTable().getIDModel();
		List<Integer> existedSubject = new LinkedList<>();

		for(var i = 0; i < existedTableModel.getRowCount(); i++) existedSubject.add((Integer) existedTableModel.getValueAt(i, 0));

		List<Object[]> objectList = new LinkedList<>();

		for(Subject s : subjects) if(!existedSubject.contains(s.getId())) {
			objectList.add(new Object[] {(Integer) s.getId(), s.getSubjectName()});
		}

		table.addData(objectList.toArray(Object[][]::new));
	}

	public void loadAvailableClass(TeacherWindow tw) {
		var table = tw.getAvailableClassTable();
		table.clearData();
		
		var classes = new TeachClassHandler().loadAllClasses();

		var existedSubjectTableModel = tw.getChoosenTeacherTable().getIDModel();
		List<Integer> existedSubject = new LinkedList<>();

		for(var i = 0; i < existedSubjectTableModel.getRowCount(); i++) existedSubject.add((Integer) existedSubjectTableModel.getValueAt(i, 0));

		var existedClassTableModel = tw.getTeacherTable().getIDModel();
		List<Integer> existedClass = new LinkedList<>();

		for(var i = 0; i < existedClassTableModel.getRowCount(); i++) existedClass.add((Integer) existedClassTableModel.getValueAt(i, 0));

		List<Object[]> objectList = new LinkedList<>();

		for(Triple<Integer, String, Integer> t : classes) {
			if(existedSubject.contains(t.third()) && !existedClass.contains(t.first())) {
				objectList.add(new Object[]{t.first(), t.second()});
			}
		}

		table.addData(objectList.toArray(Object[][]::new));
	}

    public void createTeacher(boolean sex, String name, LocalDate birth, String birthPlace, String username, String password, List<Integer> subjects, List<Integer> classes) {
		var th = new TeacherHandler();
		var beforeCreated = th.countTeacher();
		th.createTeacher(sex, name, birth, birthPlace, username, password);

		if(th.countTeacher() > beforeCreated) {
			var teacherId = th.getLatestId();

			if(teacherId == -1) return;

			for(Integer id : subjects) new SubjectHandler().addSubject(teacherId, id);
			for(Integer id : classes) new TeachClassHandler().addClass(teacherId, id);
		}
	}

    public void updateTeacher(User target, boolean sex, String name, LocalDate birth, String birthPlace, String username, String password, List<Integer> subjects, List<Integer> classes) {
    	var id = target.getId();
		new TeacherHandler().editTeacher(id, sex, name, birth, birthPlace, username, password);
		var currentSubject = new SubjectHandler().loadTeacherSubjectId(id);
		var currentClass = new TeachClassHandler().getTeachesClassIdWithId(id);

		if(subjects != null && currentSubject != null) {
			for(Integer ids : subjects) if(currentSubject.contains(ids)) {
				subjects.remove(subjects.indexOf(ids));
				currentSubject.remove(currentSubject.indexOf(ids));
			}
		}

		if(classes != null && currentClass != null) {
			for(Integer ids : classes) if(currentClass.contains(ids)) {
				classes.remove(classes.indexOf(ids));
				currentClass.remove(currentClass.indexOf(ids));
			}
		}

		if(subjects != null) for(Integer ids : subjects) new SubjectHandler().addSubject(id, ids);
		if(currentSubject != null) for(Integer ids : currentSubject) new SubjectHandler().removeSubject(id, ids);
		if(classes != null) for(Integer ids : classes) new TeachClassHandler().addClass(id, ids);
		if(currentClass != null) for(Integer ids : currentClass) new TeachClassHandler().removeClass(id, ids);
	}
    
}
