package com.myteam.work.controller;

import java.util.List;
import java.util.LinkedList;

import com.myteam.work.gui.pages.utilwin.SubjectWindow;
import com.myteam.work.management.handler.SubjectHandler;
import com.myteam.work.management.data.DataTableParser;

public class SubjectWinController {
	private SubjectHandler sh;
	private DataTableParser parser;

	public SubjectWinController() {
		this.sh = new SubjectHandler();
		this.parser = new DataTableParser();
	}

	public void loadAllSubject(SubjectWindow sw) {
		var table = sw.getSubjectTable();
		table.clearData();
		var subjects = this.sh.getAllSubject();

		if(subjects == null) return;

		var existedTableModel = sw.getChoosenPrerequisitesTable().getIDModel();
		var rowCount = existedTableModel.getRowCount();
		List<Integer> existedPrerequisite = new LinkedList<>();

		for(var i = 0; i < rowCount; i++) existedPrerequisite.add((Integer) existedTableModel.getValueAt(i, 0));

		table.addData(this.parser.parseSubjectFetchPrerequisites(subjects, existedPrerequisite));
	}

	public void searchSubject(SubjectWindow sw, String s) {
		var table = sw.getSubjectTable();
		table.clearData();
		var subjects = this.sh.getSubject(s);

		if(subjects == null) return;

		var existedTableModel = sw.getChoosenPrerequisitesTable().getIDModel();
		var rowCount = existedTableModel.getRowCount();
		List<Integer> existedPrerequisite = new LinkedList<>();

		for(var i = 0; i < rowCount; i++) existedPrerequisite.add((Integer) existedTableModel.getValueAt(i, 0));

		table.addData(this.parser.parseSubjectFetchPrerequisites(subjects, existedPrerequisite));
	}
}
