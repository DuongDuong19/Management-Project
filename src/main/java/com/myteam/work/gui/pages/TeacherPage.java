package com.myteam.work.gui.pages;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.Collections;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

import com.myteam.work.Configuration;
import com.myteam.work.controller.TeacherPageEventController;
import com.myteam.work.management.data.Semester;

import lombok.Getter;

public class TeacherPage extends JPanel {
	private static final Configuration config = Configuration.getConfiguration();
	private static TeacherPage tp;
	private CardLayout pager;
	private JPanel contentPanel;
	@Getter
	private MSTable subjectTable;
	private TeacherPageEventController tpec;
	@Getter
	private JComboBox<Semester> semesterSelector;
	@Getter
	private JComboBox classSelector;
	@Getter
	private MSTable studentTable;
	private JTextField searchField;
	String defaultText;

	private TeacherPage() {
		this.tpec = TeacherPageEventController.getController();
		this.setLayout(new BorderLayout());
		this.pager = new CardLayout();
		this.contentPanel = new JPanel(this.pager);
		this.contentPanel.add(studentManagementPage(), "student");
		this.contentPanel.add(subjectPage(), "subject");
		this.add(this.contentPanel, BorderLayout.CENTER);
	}

	public static JPanel getPage() {
		if(tp == null) tp = new TeacherPage();

		return tp;
	}

	public void changeContent(String content) {
		this.pager.show(this.contentPanel, content);
	}

	private JPanel studentManagementPage() {
		var contentPanel = new JPanel(new BorderLayout(15, 15));
		contentPanel.setBorder(new EmptyBorder(20, 25, 20, 25));
		contentPanel.setOpaque(false);
		var searchPanel = new JPanel(new BorderLayout(15, 0));
		searchPanel.setOpaque(false);
		searchPanel.setBorder(new EmptyBorder(0, 0, 15, 0));
		this.semesterSelector = new JComboBox();
		this.semesterSelector.setPreferredSize(new Dimension(500, 0));
		this.classSelector = new JComboBox();
		var submitBtn = new JButton("Submit Change");
		searchPanel.add(this.semesterSelector, BorderLayout.WEST);
		searchPanel.add(this.classSelector, BorderLayout.CENTER);
		searchPanel.add(submitBtn, BorderLayout.EAST);
		this.studentTable = new MSTable(new String[]{"ID", "Student Name", "Sex", "Generation", "Test 1", "Test 2", "End test", "Total Score", "Normalized Score", "Rate"},
				List.<Class<?>>of(Integer.class, String.class, String.class, Short.class, Float.class, Float.class, Float.class, Float.class, Float.class, String.class),
				List.of(4, 5));
		this.studentTable.setRowHeight(42);
		this.studentTable.setShowGrid(true);
		this.studentTable.setPreferredWidth(0, 100);
		this.studentTable.setPreferredWidth(1, 492);
		this.studentTable.setPreferredWidth(2, 110);
		this.studentTable.setPreferredWidth(3, 110);
		this.studentTable.setPreferredWidth(4, 112);
		this.studentTable.setPreferredWidth(5, 112);
		this.studentTable.setPreferredWidth(6, 112);
		this.studentTable.setPreferredWidth(7, 112);
		this.studentTable.setPreferredWidth(8, 112);
		this.studentTable.setPreferredWidth(9, 111);
		this.studentTable.setIntercellSpacing(new Dimension(1, 1));
		this.studentTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.studentTable.setReorderingColumn(false);
		this.studentTable.setResizingColumn(false);
		contentPanel.add(searchPanel, BorderLayout.NORTH);
		contentPanel.add(this.studentTable.getDisplayer(), BorderLayout.CENTER);

		return contentPanel;
	}

	private JPanel subjectPage() {
		var contentPanel = new JPanel(new BorderLayout(15, 15));
		contentPanel.setBorder(new EmptyBorder(20, 25, 20, 25));
		contentPanel.setOpaque(false);
		var searchPanel = new JPanel(new BorderLayout(15, 0));
		searchPanel.setOpaque(false);
		searchPanel.setBorder(new EmptyBorder(0, 0, 15, 0));
		var defaultText = "Search by subject name or subject id";
		this.searchField = new JTextField(defaultText);
		this.searchField.setBorder(null);
		this.searchField.setForeground(config.getFieldColor());
		this.searchField.addFocusListener(new DefaultTextDisplayer(defaultText));
		//replace with dynamic update with timer when have time
		var searchBtn = new JButton("Search");
		searchBtn.setFocusPainted(false);
		searchBtn.setBorderPainted(false);
		searchBtn.setCursor(config.getHandCursor());
		searchBtn.setBackground(Color.BLUE);
		searchBtn.setForeground(Color.WHITE);
		searchBtn.addActionListener(e -> {
				if(searchField.getText().equals(defaultText)) tpec.loadAllSubject();
				else tpec.searchSubject(searchField.getText());
		});
		searchPanel.add(this.searchField, BorderLayout.CENTER);
		searchPanel.add(searchBtn, BorderLayout.EAST);
		this.subjectTable = new MSTable(new String[]{"ID", "Subject name", "Prerequisites", "Credits", "Required"}, 
				List.<Class<?>>of(String.class, String[].class, Short.class, String.class), Collections.EMPTY_LIST);
		this.subjectTable.setRowHeight(42);
		this.subjectTable.setShowGrid(true);
		this.subjectTable.setPreferredWidth(0, 100);
		this.subjectTable.setPreferredWidth(1, 400);
		this.subjectTable.setPreferredWidth(2, 380);
		this.subjectTable.setPreferredWidth(3, 191);
		this.subjectTable.setPreferredWidth(4, 191);
		this.subjectTable.setIntercellSpacing(new Dimension(1, 1));
		this.subjectTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.subjectTable.setReorderingColumn(false);
		this.subjectTable.setResizingColumn(false);
		contentPanel.add(searchPanel, BorderLayout.NORTH);
		contentPanel.add(this.subjectTable.getDisplayer(), BorderLayout.CENTER);

		return contentPanel;
	}

	public void logout() {
		this.subjectTable.clearData();
		this.searchField.setText(defaultText);
		this.searchField.setForeground(config.getFieldColor());
	}
}
