package com.myteam.work.controller;

import java.util.LinkedList;
import java.util.List;

import com.myteam.work.gui.pages.utilwin.SubjectWindow;
import com.myteam.work.management.data.DataTableParser;
import com.myteam.work.management.data.Subject;
import com.myteam.work.management.handler.SubjectHandler;

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
		List<Integer> existedPrerequisite = new LinkedList<>();

		for(var i = 0; i < existedTableModel.getRowCount(); i++) existedPrerequisite.add((Integer) existedTableModel.getValueAt(i, 0));

		table.addData(this.parser.parseSubjectFetchPrerequisites(subjects, existedPrerequisite));
	}

	public void searchSubject(SubjectWindow sw, String s) {
		var table = sw.getSubjectTable();
		table.clearData();
		var subjects = this.sh.getSubject(s);

		if(subjects == null) return;

		var existedTableModel = sw.getChoosenPrerequisitesTable().getIDModel();
		List<Integer> existedPrerequisite = new LinkedList<>();

		for(var i = 0; i < existedTableModel.getRowCount(); i++) existedPrerequisite.add((Integer) existedTableModel.getValueAt(i, 0));

		table.addData(this.parser.parseSubjectFetchPrerequisites(subjects, existedPrerequisite));
	}

	public void loadPrerequisites(Subject s, SubjectWindow sw) {
		var existedPrerequisiteTable = sw.getChoosenPrerequisitesTable();
		var prerequisites = this.sh.getPrerequisites(s.getId());

		if(prerequisites == null) return;

		existedPrerequisiteTable.addData(this.parser.parsePrerequisiteFetchName(prerequisites));
	}


	public void createSubject(String subjectName, String credits, boolean required, List<Integer> prerequisites) {
		var beforeExecute = this.sh.countExisted(subjectName, credits, required);
		this.sh.createSubject(credits, required, subjectName);
		
		if(this.sh.countExisted(subjectName, credits, required) > beforeExecute) {
			var createdSubjectId = this.sh.searchLatestSubjectId(subjectName, credits, required);
			for(var id : prerequisites) this.sh.addPrerequisite(createdSubjectId, id);
		}
	}

	public void updateSubject(Subject target, String subjectName, String credits, boolean required, List<Integer> prerequisites) {
		this.sh.editSubject(target.getId(), Short.parseShort(credits), required, subjectName);
		var currentPrerequisites = this.sh.getPrerequisites(target.getId());

		for(var id : prerequisites) if(currentPrerequisites.contains(id)) {
			prerequisites.remove(prerequisite.indexOf(id));
			currentPrerequisites.remove(currentPrerequisites.indexOf(id));
		}

		for(var id : prerequisites) this.sh.addPrerequisite(target.getId(), id);
		for(var id : currentPrerequisites) this.sh.removePrerequisites(target.getId(), id);
	}
}
