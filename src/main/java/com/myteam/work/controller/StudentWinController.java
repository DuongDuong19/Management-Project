package com.myteam.work.controller;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import com.myteam.work.gui.pages.utilwin.StudentWindow;
import com.myteam.work.management.data.DataTableParser;
import com.myteam.work.management.data.Student;
import com.myteam.work.management.handler.StudentHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StudentWinController {
    private StudentHandler sth;
    private DataTableParser parser;

    public StudentWinController() {
        this.sth = new StudentHandler();
        this.parser = new DataTableParser();
    }


    public void createStudent(String name, LocalDate dateOfBirth, boolean sex, String birthPlace, String generation) {
		try {
			var generationParse = Short.parseShort(generation);

			this.sth.createStudent(name, dateOfBirth, sex, birthPlace, generationParse);
		} catch(NumberFormatException e) {
			log.error(e.toString());
		}
		
	}

    public void updateStudent(Student target, String name, LocalDate dateOfBirth, boolean sex, String birthPlace, String generation) {
		try {
			var generationParse = Short.parseShort(generation);

    		this.sth.updateStudent(target.getId(), name, dateOfBirth, sex, birthPlace, generationParse);
		} catch(NumberFormatException e) {
			log.error(e.toString());
		}
	}  
}
