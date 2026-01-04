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


    public void createStudent(String name, LocalDate dateOfBirth, boolean sex, String birthPlace) {
    	this.sth.createStudent(name, dateOfBirth, sex, birthPlace);
	}

    public void updateStudent(Student target, String name, LocalDate dateOfBirth, boolean sex, String birthPlace) {
    	this.sth.updateStudent(target.getId(), name, dateOfBirth, sex, birthPlace);
	}  
}
