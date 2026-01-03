package com.myteam.work.gui.pages.utilwin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.util.Collections;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import org.w3c.dom.events.MouseEvent;

import com.myteam.work.Configuration;
import com.myteam.work.controller.StudentWinController;
import com.myteam.work.gui.pages.DefaultTextDisplayer;
import com.myteam.work.gui.pages.MSTable;
import com.myteam.work.management.data.Student;

import lombok.Getter;    

public class StudentWindow extends JFrame {
    public static final int CREATE = 1;
    public static final int EDIT = 2;
    private static final Configuration config = Configuration.getConfiguration();
    private static final String defaultStudentIdText = "Please enter student ID here";
    private static final String defaultStudentNameText = "Please enter student name here";
    private static final String defaultDobText = "dd/mm/yyyy";
    private static final String defaultClassText = "Please enter class here";
    private static final String defaultSearchText = "Search by student name or student id";
    private static final Color PRIMARY_COLOR = new Color(41, 128, 185);
    private static final Color BACKGROUND_COLOR = new Color(236, 240, 241);
    
    @Getter
    private MSTable studentTable;
    @Getter
    private Student target;
    @Getter
	private MSTable choosenPrerequisitesTable;
    private StudentWinController stwc;
    
    public StudentWindow(Student target) {
        this.setTitle("Student Management");
        this.setSize(new Dimension(900, 600));
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(BACKGROUND_COLOR);
        
        var mainPanel = new JPanel(new BorderLayout(15, 15));
        mainPanel.setBackground(BACKGROUND_COLOR);
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        
        // Top Panel - Student Information Input
        var topPanel = new JPanel(new GridLayout(2, 1, 10, 10));
        topPanel.setBackground(BACKGROUND_COLOR);
        
        var firstRowPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 10));
        firstRowPanel.setBackground(Color.WHITE);
        firstRowPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(189, 195, 199), 1),
            new EmptyBorder(15, 15, 15, 15)
        ));
        
        var studentId = createStyledTextField(defaultStudentIdText, 150);
        var studentName = createStyledTextField(defaultStudentNameText, 250);
        
        firstRowPanel.add(new JLabel("Student ID:"));
        firstRowPanel.add(studentId);
        firstRowPanel.add(new JLabel("Student Name:"));
        firstRowPanel.add(studentName);
        
        var secondRowPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 10));
        secondRowPanel.setBackground(Color.WHITE);
        secondRowPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(189, 195, 199), 1),
            new EmptyBorder(15, 15, 15, 15)
        ));
        
        var dob = createStyledTextField(defaultDobText, 150);
        var className = createStyledTextField(defaultClassText, 200);
        
        secondRowPanel.add(new JLabel("Date of Birth:"));
        secondRowPanel.add(dob);
        secondRowPanel.add(new JLabel("Class:"));
        secondRowPanel.add(className);
        
        topPanel.add(firstRowPanel);
        topPanel.add(secondRowPanel);
        
        // Center Panel - Student List
        var centerPanel = new JPanel(new BorderLayout(10, 10));
        centerPanel.setBackground(Color.WHITE);
        centerPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(189, 195, 199), 1),
            new EmptyBorder(15, 15, 15, 15)
        ));
        
        var studentListLabel = new JLabel("Student List");
        studentListLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        studentListLabel.setForeground(PRIMARY_COLOR);
        
        var searchPanel = new JPanel(new BorderLayout(10, 10));
        searchPanel.setBackground(Color.WHITE);
        
        var studentSearch = createStyledTextField(defaultSearchText, 0);
        
        this.studentTable = new MSTable(
            new String[]{"ID", "Student Name", "Date of Birth", "Class"},
            List.<Class<?>>of(String.class, String.class, String.class, String.class),
            Collections.EMPTY_LIST
        );
        this.studentTable.setReorderingColumn(false);
        this.studentTable.setResizingColumn(false);
        
        var topSearchPanel = new JPanel(new BorderLayout(10, 10));
        topSearchPanel.setBackground(Color.WHITE);
        topSearchPanel.add(studentListLabel, BorderLayout.NORTH);
        topSearchPanel.add(studentSearch, BorderLayout.CENTER);
        
        searchPanel.add(topSearchPanel, BorderLayout.NORTH);
        searchPanel.add(studentTable.getDisplayer(), BorderLayout.CENTER);
        
        centerPanel.add(searchPanel, BorderLayout.CENTER);
        
        // Bottom Panel - Action Buttons
        var bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        bottomPanel.setBackground(BACKGROUND_COLOR);
        
        var addStudentBtn = createStyledButton("Add Student", new Color(39, 174, 96));
        var removeStudentBtn = createStyledButton("Remove Student", new Color(231, 76, 60));
        var clearBtn = createStyledButton("Clear Fields", new Color(149, 165, 166));
        
        addStudentBtn.setPreferredSize(new Dimension(150, 40));
        removeStudentBtn.setPreferredSize(new Dimension(150, 40));
        clearBtn.setPreferredSize(new Dimension(150, 40));
        
        bottomPanel.add(addStudentBtn);
        bottomPanel.add(removeStudentBtn);
        bottomPanel.add(clearBtn);
        
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
        
        this.add(mainPanel);
        
        // Add Focus Listeners
        studentId.addFocusListener(new DefaultTextDisplayer(defaultStudentIdText));
        studentName.addFocusListener(new DefaultTextDisplayer(defaultStudentNameText));
        dob.addFocusListener(new DefaultTextDisplayer(defaultDobText));
        className.addFocusListener(new DefaultTextDisplayer(defaultClassText));
        studentSearch.addFocusListener(new DefaultTextDisplayer(defaultSearchText));
        
        this.target = target;
        this.stwc = new StudentWinController();
        
        if(this.target != null) {
            this.stwc.loadTarget(this.target, studentId, studentName, dob, className);
        }
        
        this.stwc.loadAllStudents(this);
        
        // Search functionality with delay
        studentSearch.getDocument().addDocumentListener(new DocumentListener() {
            private Timer updater = new Timer(125, e -> {
                if(studentSearch.getText().equals(defaultSearchText)) {
                    stwc.loadAllStudents(StudentWindow.this);
                } else {
                    stwc.searchStudent(StudentWindow.this, studentSearch.getText());
                }
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
        
        // Add Student Button Action
        addStudentBtn.addActionListener(e -> {
            addStudent(studentId.getText(), studentName.getText(), 
                      dob.getText(), className.getText());
        });
        
        // Remove Student Button Action
        removeStudentBtn.addActionListener(e -> {
            removeStudent();
        });
        
        // Clear Fields Button Action
        clearBtn.addActionListener(e -> {
            studentId.setText(defaultStudentIdText);
            studentId.setForeground(config.getFieldColor());
            studentName.setText(defaultStudentNameText);
            studentName.setForeground(config.getFieldColor());
            dob.setText(defaultDobText);
            dob.setForeground(config.getFieldColor());
            className.setText(defaultClassText);
            className.setForeground(config.getFieldColor());
            this.target = null;
        });
        
        this.setVisible(true);
    }
    
    public void addStudent(String id, String name, String dateOfBirth, String studentClass) {
        if(target == null) {
            stwc.createStudent(id, name, dateOfBirth, studentClass);
        } else {
            stwc.updateStudent(target, id, name, dateOfBirth, studentClass);
        }
    }
    
    public void removeStudent() {
        var selectedRow = studentTable.getSelectedRow();
        
        if(selectedRow == -1) {
            JOptionPane.showMessageDialog(
                this,
                "Please select a student to remove!",
                "Warning",
                JOptionPane.WARNING_MESSAGE
            );
            return;
        }
        
        int confirm = JOptionPane.showConfirmDialog(
            this,
            "Are you sure you want to remove this student?",
            "Confirm Removal",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE
        );
        
        if(confirm == JOptionPane.YES_OPTION) {
            stwc.deleteStudent(selectedRow);
        }
    }
    
    private JTextField createStyledTextField(String text, int width) {
        var textField = new JTextField(text);
        textField.setForeground(Configuration.getConfiguration().getFieldColor());
        if (width > 0) {
            textField.setPreferredSize(new Dimension(width, 35));
        } else {
            textField.setPreferredSize(new Dimension(textField.getPreferredSize().width, 35));
        }
        textField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(189, 195, 199), 1),
            new EmptyBorder(5, 10, 5, 10)
        ));
        return textField;
    }
    
    private JButton createStyledButton(String text, Color bgColor) {
        var button = new JButton(text);
        button.setForeground(Color.WHITE);
        button.setBackground(bgColor);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setCursor(config.getHandCursor());
        button.setPreferredSize(new Dimension(180, 35));
        
        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                button.setBackground(bgColor.brighter());
            }
            public void mouseExited(MouseEvent evt) {
                button.setBackground(bgColor);
            }
        });
        
        return button;
    }
}