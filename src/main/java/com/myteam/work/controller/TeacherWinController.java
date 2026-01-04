package com.myteam.work.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.LinkedList;

import com.myteam.work.management.data.User;
import com.myteam.work.management.data.Triple;
import com.myteam.work.management.data.Subject;
import com.myteam.work.gui.pages.utilwin.TeacherWindow;
import com.myteam.work.management.handler.SubjectHandler;
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

    public void createTeacher(String text, LocalDate localDate, boolean selected, String text2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createTeacher'");
    }

    public void updateTeacher(User target, String text, LocalDate localDate, boolean selected, String text2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateTeacher'");
    }
    
}
