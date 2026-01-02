package com.myteam.work.controller;

import java.util.LinkedList;
import java.util.List;

import javax.swing.JTextField;

import com.myteam.work.gui.pages.utilwin.StudentWindow;
import com.myteam.work.management.data.DataTableParser;
import com.myteam.work.management.data.Student;
import com.myteam.work.management.handler.StudentHandler;

public class StudentWinController {
    private StudentHandler sth;
    private DataTableParser parser;

    public StudentWinController() {
        this.sth = new StudentHandler();
        this.parser = new DataTableParser();
    }

    public void deleteStudent(int selectedRow) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteStudent'");
    }

    public void createStudent(String id, String name, String dateOfBirth, String studentClass) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createStudent'");
    }

    public void updateStudent(Student target, String id, String name, String dateOfBirth, String studentClass) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateStudent'");
    }

    public void loadAllStudents(StudentWindow studentWindow) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'loadAllStudents'");
    }

    public void searchStudent(StudentWindow sw, String s) {
        var table = sw.getStudentTable();
		table.clearData();
		var students = this.sth.loadStudent(s);

		if(students == null) return;

		var existedTableModel = sw.getChoosenPrerequisitesTable().getIDModel();
		List<Integer> existedStudent = new LinkedList<>();

		for(var i = 0; i < existedTableModel.getRowCount(); i++) existedStudent.add((Integer) existedTableModel.getValueAt(i, 0));

		table.addData(this.parser.parseStudentFetch(students));
    }

    public void loadTarget(Student target, JTextField studentId, JTextField studentName, JTextField dob,
            JTextField className) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'loadTarget'");
    }

}
