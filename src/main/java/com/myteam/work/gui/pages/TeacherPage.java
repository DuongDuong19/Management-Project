package com.myteam.work.gui.pages;

import java.util.List;
import java.util.Collections;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.CardLayout;
import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import lombok.Getter;

import com.myteam.work.Configuration;
import com.myteam.work.controller.TeacherPageEventController;

public class TeacherPage extends JPanel {
	private static final Configuration config = Configuration.getConfiguration();
	private static TeacherPage tp;
	private CardLayout pager;
	private JPanel contentPanel;
	@Getter
	private MSTable subjectTable;
	private TeacherPageEventController tpec;

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
		var contentPanel = new JPanel();
		contentPanel.add(new JLabel("student"));

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
		var searchField = new JTextField(defaultText);
		searchField.setBorder(null);
		searchField.setForeground(config.getFieldColor());
		searchField.addFocusListener(new DefaultTextDisplayer(defaultText));
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
		searchPanel.add(searchField, BorderLayout.CENTER);
		searchPanel.add(searchBtn, BorderLayout.EAST);
		this.subjectTable = new MSTable(new String[]{"ID", "Subject name", "Prerequisites", "Credits", "Required"}, 
				List.<Class<?>>of(String.class, String[].class, Short.class, String.class), Collections.EMPTY_LIST);
		this.subjectTable.setRowHeight(42);
		this.subjectTable.setShowGrid(true);
		this.subjectTable.setPreferredWidth(0, 200);
		this.subjectTable.setPreferredWidth(1, 1000);
		this.subjectTable.setPreferredWidth(2, 200);
		this.subjectTable.setPreferredWidth(3, 600);
		this.subjectTable.setIntercellSpacing(new Dimension(1, 1));
		this.subjectTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.subjectTable.setReorderingColumn(false);
		this.subjectTable.setResizingColumn(false);
		contentPanel.add(searchPanel, BorderLayout.NORTH);
		contentPanel.add(this.subjectTable.getDisplayer(), BorderLayout.CENTER);

		return contentPanel;
	}
}
