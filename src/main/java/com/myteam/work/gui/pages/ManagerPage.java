package com.myteam.work.gui.pages;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.myteam.work.Configuration;
import com.myteam.work.controller.ManagerPageEventController;
import com.myteam.work.gui.pages.utilwin.SubjectWindow;
import com.myteam.work.gui.pages.utilwin.SubmitWindow;
import com.myteam.work.management.data.Semester;
import com.myteam.work.management.data.Student;
import com.myteam.work.management.data.Subject;
import com.myteam.work.management.data.TeachClass;
import com.myteam.work.management.data.User;

import lombok.Getter;

public class ManagerPage extends JPanel {
	private static final String subjectTableDefaultText = "Search by subject name or subject id";
	private static final String teacherTableDefaultText = "Search by teacher name or teacher id";
	private static final String studentTableDefaultText = "Search by student name or student id";
	private static final Configuration config = Configuration.getConfiguration();
	private static ManagerPageEventController mpec;
	private static ManagerPage mp;
	private Subject subject = null;
	private CardLayout pager;
	private JPanel contentPanel;
	@Getter
	private MSTable studentTable;
	@Getter
	private MSTable subjectTable;
	@Getter
	private MSTable teacherTable;
	@Getter
	private MSTable classTable;
	@Getter
	private MSTable semesterTable;
	@Getter
	private MSTable studentClassTable;
	@Getter
	private JComboBox classSemesterSelector;
	@Getter
	private JComboBox<Semester> classManagementSemesterSelector;
	@Getter
	private JComboBox<Subject> classManagementSubjectSelector;
	@Getter
	private JComboBox<TeachClass> classManagementClassSelector;
	@Getter
	private JComboBox<User> classManagementTeacherSelector;
	@Getter
	private JComboBox<User> teacherSelector;
	private JTextField subjectSearchField;
	private JTextField teacherSearchField;
	private JTextField studentSearchField;

	private ManagerPage() {
		this.mpec = ManagerPageEventController.getController();
		this.setLayout(new BorderLayout());
		this.pager = new CardLayout();
		this.contentPanel = new JPanel(this.pager);
		this.contentPanel.add(studentManagementPage(), "student");
		this.contentPanel.add(subjectManagementPage(), "subject");
		this.contentPanel.add(teacherManagementPage(), "teacher");
		this.contentPanel.add(classPage(), "class");
		this.contentPanel.add(classManagement(), "classManagement");
		this.add(contentPanel, BorderLayout.CENTER);
	}

	public static JPanel getPage() {
		if(mp == null) mp = new ManagerPage();

		return mp;
	}

	public void changeContent(String content) {
		this.pager.show(this.contentPanel, content);
	}

	private JPanel studentManagementPage() {
		var contentPanel = new JPanel(new BorderLayout(15, 15));
		contentPanel.add(new JLabel("student"));
		contentPanel.setBorder(new EmptyBorder(20, 25, 20, 25));
		var searchPanel = new JPanel(new BorderLayout(15, 0));
		var selectorPanel = new JPanel(new BorderLayout(12, 0));
		var searchBtn = new JPanel(new BorderLayout());
		searchPanel.setOpaque(false);
		
		this.studentSearchField = new JTextField(studentTableDefaultText);
		this.studentSearchField.setBorder(null);
		this.studentSearchField.setForeground(config.getFieldColor());
		this.studentSearchField.addFocusListener(new DefaultTextDisplayer(studentTableDefaultText));
		this.studentSearchField.getDocument().addDocumentListener(new DocumentListener() {
			private Timer updater = new Timer(125, e -> {
				if(studentSearchField.getText().equals(studentTableDefaultText)) mpec.loadStudent();
				else mpec.searchStudent(studentSearchField.getText());
			});

			public void changedUpdate(DocumentEvent e) {
				updater.setRepeats(false);
				updater.restart();
			}

			public void insertUpdate(DocumentEvent e) {
				updater.setRepeats(false);
				updater.restart();
			}

			public void removeUpdate(DocumentEvent e) {
				updater.setRepeats(false);
				updater.restart();
			}
		});

		var addStudentBtn = new JButton("Add student");
		addStudentBtn.addActionListener(e -> createSubmitWindow());
		var removeStudentBtn = new JButton("Remove student");
		removeStudentBtn.addActionListener(e -> createSubmitWindow());
		searchBtn.add(addStudentBtn, BorderLayout.WEST);
		searchBtn.add(removeStudentBtn, BorderLayout.CENTER);
		
		selectorPanel.add(this.studentSearchField, BorderLayout.CENTER);

		searchPanel.add(selectorPanel, BorderLayout.CENTER);
		searchPanel.add(searchBtn, BorderLayout.EAST);

		this.studentTable = new MSTable(new String[]{"ID", "Student Name", "Birth", "Place of birth", "Sex", "Generation", "Gpa"},
				List.<Class<?>>of(Integer.class, String.class, String.class, String.class, String.class, Short.class, Float.class), Collections.EMPTY_LIST);

		contentPanel.add(searchPanel, BorderLayout.NORTH);
		contentPanel.add(this.studentTable.getDisplayer(), BorderLayout.CENTER);

		this.studentTable.setReorderingColumn(false);
		this.studentTable.setResizingColumn(false);
		return contentPanel;
	}

	private JPanel subjectManagementPage() {
		var contentPanel = new JPanel(new BorderLayout(15, 15));
		contentPanel.setBorder(new EmptyBorder(20, 25, 20, 25));
		contentPanel.setOpaque(false);
		var searchPanel = new JPanel(new BorderLayout(15, 0));
		searchPanel.setOpaque(false);
		var searchBtn = new JPanel(new BorderLayout());
		searchBtn.setOpaque(false);
		this.subjectSearchField = new JTextField(subjectTableDefaultText);
		this.subjectSearchField.setBorder(null);
		this.subjectSearchField.setForeground(config.getFieldColor());
		this.subjectSearchField.addFocusListener(new DefaultTextDisplayer(subjectTableDefaultText));
		this.subjectSearchField.getDocument().addDocumentListener(new DocumentListener() {
			private Timer updater = new Timer(125, e -> {
				if(subjectSearchField.getText().equals(subjectTableDefaultText)) mpec.loadAllSubject();
				else mpec.searchSubject(subjectSearchField.getText());

			});

			public void changedUpdate(DocumentEvent e) {
				updater.setRepeats(false);
				updater.restart();
			}

			public void insertUpdate(DocumentEvent e) {
				updater.setRepeats(false);
				updater.restart();
			}

			public void removeUpdate(DocumentEvent e) {
				updater.setRepeats(false);
				updater.restart();
			}
		});
		var subjectCreateBtn = new JButton("Create subject");
		subjectCreateBtn.addActionListener(e -> new SubjectWindow(null));
		var subjectEditBtn = new JButton("Edit subject");
		subjectEditBtn.addActionListener(e -> new SubjectWindow(subject));
		var subjectDeleteBtn = new JButton("Delete subject");

		searchBtn.add(subjectCreateBtn, BorderLayout.WEST);
		searchBtn.add(subjectEditBtn, BorderLayout.CENTER);
		searchBtn.add(subjectDeleteBtn, BorderLayout.EAST);
		searchPanel.add(this.subjectSearchField, BorderLayout.CENTER);
		searchPanel.add(searchBtn, BorderLayout.EAST);
		this.subjectTable = new MSTable(new String[]{"ID", "Subject name", "Prerequisites", "Credits", "Require"}, 
				List.<Class<?>>of(Integer.class, String.class, String[].class, Short.class, String.class), Collections.EMPTY_LIST);
		this.subjectTable.setRowHeight(42);
		this.subjectTable.setShowGrid(true);
		this.subjectTable.setPreferredWidth(0, 191);
		this.subjectTable.setPreferredWidth(3, 191);
		this.subjectTable.setPreferredWidth(4, 191);
		this.subjectTable.setIntercellSpacing(new Dimension(1, 1));
		this.subjectTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.subjectTable.setReorderingColumn(false);
		this.subjectTable.setResizingColumn(false);
		contentPanel.add(searchPanel, BorderLayout.NORTH);
		contentPanel.add(this.subjectTable.getDisplayer(), BorderLayout.CENTER);
		subjectDeleteBtn.addActionListener(_ -> createDeleteWindow(subjectTable, mpec::deleteSubject, subjectSearchField, subjectTableDefaultText, mpec::loadAllSubject, mpec::searchSubject));

		return contentPanel;
	}

	private JPanel teacherManagementPage() {
		var contentPanel = new JPanel(new BorderLayout(15, 15));
		contentPanel.add(new JLabel("teacher"));
		contentPanel.setBorder(new EmptyBorder(20, 25, 20, 25));
		var searchPanel = new JPanel(new BorderLayout(15, 0));
		var selectorPanel = new JPanel(new BorderLayout(12, 0));
		var searchBtn = new JPanel(new BorderLayout());
		searchPanel.setOpaque(false);

		this.teacherSelector = new JComboBox<>();
		this.teacherSelector.setRenderer(config.getComboBoxRenderer());
		this.teacherSelector.addActionListener(e -> loadManagementTeachClass());

		this.teacherSearchField = new JTextField(teacherTableDefaultText);
		this.teacherSearchField.setBorder(null);
		this.teacherSearchField.setForeground(config.getFieldColor());
		this.teacherSearchField.addFocusListener(new DefaultTextDisplayer(teacherTableDefaultText));
		this.teacherSearchField.getDocument().addDocumentListener(new DocumentListener() {
			private Timer updater = new Timer(125, e -> {
				if(teacherSearchField.getText().equals(teacherTableDefaultText)) mpec.loadTeacherSubject();
				else mpec.searchTeacher(teacherSearchField.getText());
			});

			public void changedUpdate(DocumentEvent e) {
				updater.setRepeats(false);
				updater.restart();
			}

			public void insertUpdate(DocumentEvent e) {
				updater.setRepeats(false);
				updater.restart();
			}

			public void removeUpdate(DocumentEvent e) {
				updater.setRepeats(false);
				updater.restart();
			}
		});

		var addTeacherBtn = new JButton("Add teacher");
		var removeTeacherBtn = new JButton("Remove teacher");
		searchBtn.add(addTeacherBtn, BorderLayout.WEST);
		searchBtn.add(removeTeacherBtn, BorderLayout.CENTER);
		
		selectorPanel.add(this.teacherSearchField, BorderLayout.CENTER);

		searchPanel.add(selectorPanel, BorderLayout.CENTER);
		searchPanel.add(searchBtn, BorderLayout.EAST);

		this.teacherTable = new MSTable(new String[]{"ID", "Teacher name", "Username", "Password", "Birth", "Place of birth", "Sex", "Subject", "Teach class"},
				List.<Class<?>>of(Integer.class, String.class, String.class, String.class, String.class, String.class, String.class, String[].class, String[].class), Collections.EMPTY_LIST);

		contentPanel.add(searchPanel, BorderLayout.NORTH);
		contentPanel.add(this.teacherTable.getDisplayer(), BorderLayout.CENTER);

		this.teacherTable.setReorderingColumn(false);
		this.teacherTable.setResizingColumn(false);
		this.teacherTable.setRowHeight(42);
		this.teacherTable.setShowGrid(true);
		this.teacherTable.setIntercellSpacing(new Dimension(1, 1));

		return contentPanel;
	}

	private JScrollPane classPage() {
		var contentPanel = new JPanel(new BorderLayout(15, 15));
		contentPanel.setBorder(new EmptyBorder(20, 25, 20, 25));
		contentPanel.setOpaque(false);
		var classPanel = new JPanel(new BorderLayout(15, 0));
		var classSearchPanel = new JPanel(new BorderLayout(15, 0));
		var classSearchBtn = new JPanel(new BorderLayout());
		this.classSemesterSelector = new JComboBox<>();
		var classSearchField = new JTextField();
		var classCreateBtn = new JButton("Create class");
		var classEditBtn = new JButton("Edit class");
		var classDeleteBtn = new JButton("Delete class");
		classSearchBtn.add(classCreateBtn, BorderLayout.WEST);
		classSearchBtn.add(classEditBtn, BorderLayout.CENTER);
		classSearchBtn.add(classDeleteBtn, BorderLayout.EAST);
		classSearchPanel.add(this.classSemesterSelector, BorderLayout.WEST);
		classSearchPanel.add(classSearchField, BorderLayout.CENTER);
		classSearchPanel.add(classSearchBtn, BorderLayout.EAST);
		this.classTable = new MSTable(new String[]{"ID", "Class name", "Semester", "Subject", "GPA", "Teacher"},
				List.<Class<?>>of(Integer.class, String.class, String.class, String.class, Float.class, String[].class), Collections.EMPTY_LIST);
		classPanel.add(classSearchPanel, BorderLayout.NORTH);
		classPanel.add(this.classTable.getDisplayer(), BorderLayout.CENTER);
		var semesterPanel = new JPanel(new BorderLayout(15, 0));
		var semesterSearchPanel = new JPanel(new BorderLayout(15, 0));
		var semesterSearchBtn = new JPanel(new BorderLayout());
		var semesterSearchField = new JTextField();
		var semesterCreateBtn = new JButton("Create semester");
		var semesterEditBtn = new JButton("Edit semester");
		var semesterDeleteBtn = new JButton("Delete semester");
		semesterSearchBtn.add(semesterCreateBtn, BorderLayout.WEST);
		semesterSearchBtn.add(semesterEditBtn, BorderLayout.CENTER);
		semesterSearchBtn.add(semesterDeleteBtn, BorderLayout.EAST);
		semesterSearchPanel.add(semesterSearchField, BorderLayout.CENTER);
		semesterSearchPanel.add(semesterSearchBtn, BorderLayout.EAST);
		this.semesterTable = new MSTable(new String[]{"ID", "Semester", "Year"}, List.<Class<?>>of(Integer.class, Short.class, Short.class), Collections.EMPTY_LIST);
		semesterPanel.add(semesterSearchPanel, BorderLayout.NORTH);
		semesterPanel.add(this.semesterTable.getDisplayer(), BorderLayout.CENTER);
		contentPanel.add(classPanel, BorderLayout.CENTER);
		contentPanel.add(semesterPanel, BorderLayout.SOUTH);

		return new JScrollPane(contentPanel);
	}

	private JPanel classManagement() {
		var contentPanel = new JPanel(new BorderLayout(15, 15));
		contentPanel.setBorder(new EmptyBorder(20, 25, 20, 25));
		var searchPanel = new JPanel(new BorderLayout(15, 0));
		var selectorPanel = new JPanel(new BorderLayout(12, 0));
		var searchBtn = new JPanel(new BorderLayout());
		searchPanel.setOpaque(false);
		this.classManagementSemesterSelector = new JComboBox<>();
		this.classManagementSemesterSelector.setRenderer(config.getComboBoxRenderer());
		this.classManagementSemesterSelector.addActionListener(e -> loadManagementTeachClass());
		this.classManagementSubjectSelector = new JComboBox<>();
		this.classManagementSubjectSelector.setRenderer(config.getComboBoxRenderer());
		this.classManagementSubjectSelector.addActionListener(e -> loadManagementTeachClass());
		this.classManagementClassSelector = new JComboBox<>();
		this.classManagementClassSelector.setRenderer(config.getComboBoxRenderer());
		this.classManagementClassSelector.addActionListener(e -> mpec.loadStudentInTeachClass((TeachClass) ((JComboBox) e.getSource()).getSelectedItem()));
		var searchField = new JTextField();
		var addStudentBtn = new JButton("Add student");
		var removeStudentBtn = new JButton("Remove student");
		searchBtn.add(addStudentBtn, BorderLayout.WEST);
		searchBtn.add(removeStudentBtn, BorderLayout.CENTER);
		selectorPanel.add(this.classManagementSemesterSelector, BorderLayout.WEST);
		selectorPanel.add(this.classManagementSubjectSelector, BorderLayout.CENTER);
		selectorPanel.add(this.classManagementClassSelector, BorderLayout.EAST);
		searchPanel.add(selectorPanel, BorderLayout.CENTER);
		searchPanel.add(searchBtn, BorderLayout.EAST);
		this.studentClassTable = new MSTable(new String[]{"ID", "Student name", "Sex", "Generation", "Test 1", "Test 2", "End test", "Total Score", "Normalized Score", "Rate"},
				List.<Class<?>>of(Integer.class, String.class, String.class, Short.class, Float.class, Float.class, Float.class, Float.class, Float.class, String.class), Collections.EMPTY_LIST);
		contentPanel.add(searchPanel, BorderLayout.NORTH);
		contentPanel.add(this.studentClassTable.getDisplayer(), BorderLayout.CENTER);
		this.studentClassTable.setReorderingColumn(false);
		this.studentClassTable.setResizingColumn(false);

		return contentPanel;
	}

	public void logout() {
		
	}

	private void loadManagementTeachClass() {
		mpec.loadTeachClass((Semester) this.classManagementSemesterSelector.getSelectedItem(), (Subject) this.classManagementSubjectSelector.getSelectedItem());
	}

	private void createDeleteWindow(MSTable table, Consumer<Integer> deleteFunc, JTextField searchBar, String defaultText, Runnable loadAll, Consumer<String> search) {
		var submitWin = new SubmitWindow(false);
		submitWin.setSubmitAction(_ -> {
			var selectedRow = table.getSelectedRow();

			if(selectedRow == -1) return;

			deleteFunc.accept(selectedRow);
			
			if(searchBar.getText().equals(defaultText)) loadAll.run();
			else search.accept(searchBar.getText());
		});
		submitWin.setCancelAction(_ -> submitWin.dispose());
	}
}
