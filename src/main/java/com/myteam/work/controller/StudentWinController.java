package com.myteam.work.controller;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

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

    public void createStudent(int id, String name, LocalDate dateOfBirth, Class<? extends Student> studentClass) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createStudent'");
    }

    public void updateStudent(Student target, int id, String name, LocalDate dateOfBirth, Class<? extends Student> studentClass) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateStudent'");
    }

    public void loadAllStudents(StudentWindow sw) {
        var table = sw.getStudentTable();
		table.clearData();
		var students = this.sth.getAllStudents();

		if(students == null) return;

        // If there's no chosen-student table (optional UI), treat as no exclusions
        var existedTable = sw.getChoosenStudentTable();
        List<Integer> existedStudent = new LinkedList<>();
        if (existedTable != null) {
            var existedTableModel = existedTable.getIDModel();
            for(var i = 0; i < existedTableModel.getRowCount(); i++) existedStudent.add((Integer) existedTableModel.getValueAt(i, 0));
        }

        table.addData(this.parser.parseStudentFetch(students, existedStudent));
    }

    public void searchStudent(StudentWindow sw, String s) {
        var table = sw.getStudentTable();
		table.clearData();
		var students = this.sth.loadStudent(s);

		if(students == null) return;

        var existedTable = sw.getChoosenStudentTable();
        List<Integer> existedStudent = new LinkedList<>();
        if (existedTable != null) {
            var existedTableModel = existedTable.getIDModel();
            for(var i = 0; i < existedTableModel.getRowCount(); i++) existedStudent.add((Integer) existedTableModel.getValueAt(i, 0));
        }

        table.addData(this.parser.parseStudentFetch(students, existedStudent));
    }

    public void loadTarget(Student target) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'loadTarget'");
    }

    public void deleteStudent(Object selectedRow) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteStudent'");
    }

    public void searchByBirthPlace(StudentWindow studentWindow, String text) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'searchByBirthPlace'");
    }

    public void loadTargetByRow(StudentWindow aThis, int selectedRow) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
