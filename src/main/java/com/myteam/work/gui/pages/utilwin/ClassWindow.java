package com.myteam.work.gui.pages.utilwin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.w3c.dom.events.MouseEvent;

public class ClassWindow extends JFrame {
    
    public ClassWindow() {
        this.setTitle("Class Management");
        this.setSize(new Dimension(950, 600));
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(new Color(236, 240, 241));
        
        var mainPanel = new JPanel(new BorderLayout(15, 15));
        mainPanel.setBackground(new Color(236, 240, 241));
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        
        // Center Panel - Class List
        var centerPanel = new JPanel(new BorderLayout(10, 10));
        centerPanel.setBackground(Color.WHITE);
        centerPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(189, 195, 199), 1),
            new EmptyBorder(15, 15, 15, 15)
        ));
        
        var classListLabel = new JLabel("Class List");
        classListLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        classListLabel.setForeground(new Color(41, 128, 185));
        
        // Create Table
        String[] columnNames = {"Class ID", "Class Name", "Grade", "Teacher", "Total Students"};
        var tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        var classTable = new JTable(tableModel);
        classTable.setRowHeight(30);
        classTable.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        classTable.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        classTable.getTableHeader().setBackground(new Color(41, 128, 185));
        classTable.getTableHeader().setForeground(Color.WHITE);
        classTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        classTable.setSelectionBackground(new Color(52, 152, 219, 100));
        
        var scrollPane = new JScrollPane(classTable);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(189, 195, 199), 1));
        
        centerPanel.add(classListLabel, BorderLayout.NORTH);
        centerPanel.add(scrollPane, BorderLayout.CENTER);
        
        // Bottom Panel - Action Buttons
        var bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        bottomPanel.setBackground(new Color(236, 240, 241));
        
        var createClassBtn = new JButton("Create Class");
        createClassBtn.setForeground(Color.WHITE);
        createClassBtn.setBackground(new Color(39, 174, 96));
        createClassBtn.setFocusPainted(false);
        createClassBtn.setBorderPainted(false);
        createClassBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        createClassBtn.setPreferredSize(new Dimension(150, 40));
        createClassBtn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                createClassBtn.setBackground(new Color(39, 174, 96).brighter());
            }
            public void mouseExited(MouseEvent evt) {
                createClassBtn.setBackground(new Color(39, 174, 96));
            }
        });
        
        var editClassBtn = new JButton("Edit Class");
        editClassBtn.setForeground(Color.WHITE);
        editClassBtn.setBackground(new Color(52, 152, 219));
        editClassBtn.setFocusPainted(false);
        editClassBtn.setBorderPainted(false);
        editClassBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        editClassBtn.setPreferredSize(new Dimension(150, 40));
        editClassBtn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                editClassBtn.setBackground(new Color(52, 152, 219).brighter());
            }
            public void mouseExited(MouseEvent evt) {
                editClassBtn.setBackground(new Color(52, 152, 219));
            }
        });
        
        var deleteClassBtn = new JButton("Delete Class");
        deleteClassBtn.setForeground(Color.WHITE);
        deleteClassBtn.setBackground(new Color(231, 76, 60));
        deleteClassBtn.setFocusPainted(false);
        deleteClassBtn.setBorderPainted(false);
        deleteClassBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        deleteClassBtn.setPreferredSize(new Dimension(150, 40));
        deleteClassBtn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                deleteClassBtn.setBackground(new Color(231, 76, 60).brighter());
            }
            public void mouseExited(MouseEvent evt) {
                deleteClassBtn.setBackground(new Color(231, 76, 60));
            }
        });
        
        bottomPanel.add(createClassBtn);
        bottomPanel.add(editClassBtn);
        bottomPanel.add(deleteClassBtn);
        
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
        
        this.add(mainPanel);
        
        // Create Class Button Action
        createClassBtn.addActionListener(e -> {
            JDialog dialog = new JDialog(this, "Create New Class", true);
            dialog.setSize(450, 350);
            dialog.setLocationRelativeTo(this);
            dialog.setResizable(false);
            
            JPanel dialogMainPanel = new JPanel(new BorderLayout(10, 10));
            dialogMainPanel.setBackground(Color.WHITE);
            dialogMainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
            
            // Form Panel
            JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 15));
            formPanel.setBackground(Color.WHITE);
            
            // Class ID Field
            JLabel idLabel = new JLabel("Class ID:");
            idLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            var idField = new JTextField("");
            idField.setForeground(Color.BLACK);
            idField.setPreferredSize(new Dimension(200, 35));
            idField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(189, 195, 199), 1),
                new EmptyBorder(5, 10, 5, 10)
            ));
            
            // Class Name Field
            JLabel nameLabel = new JLabel("Class Name:");
            nameLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            var nameField = new JTextField("");
            nameField.setForeground(Color.BLACK);
            nameField.setPreferredSize(new Dimension(200, 35));
            nameField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(189, 195, 199), 1),
                new EmptyBorder(5, 10, 5, 10)
            ));
            
            // Grade Field
            JLabel gradeLabel = new JLabel("Grade:");
            gradeLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            var gradeField = new JTextField("");
            gradeField.setForeground(Color.BLACK);
            gradeField.setPreferredSize(new Dimension(200, 35));
            gradeField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(189, 195, 199), 1),
                new EmptyBorder(5, 10, 5, 10)
            ));
            
            // Teacher Field
            JLabel teacherLabel = new JLabel("Teacher:");
            teacherLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            var teacherField = new JTextField("");
            teacherField.setForeground(Color.BLACK);
            teacherField.setPreferredSize(new Dimension(200, 35));
            teacherField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(189, 195, 199), 1),
                new EmptyBorder(5, 10, 5, 10)
            ));
            
            // Total Students Field
            JLabel studentsLabel = new JLabel("Total Students:");
            studentsLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            var studentsField = new JTextField("0");
            studentsField.setForeground(Color.BLACK);
            studentsField.setPreferredSize(new Dimension(200, 35));
            studentsField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(189, 195, 199), 1),
                new EmptyBorder(5, 10, 5, 10)
            ));
            
            formPanel.add(idLabel);
            formPanel.add(idField);
            formPanel.add(nameLabel);
            formPanel.add(nameField);
            formPanel.add(gradeLabel);
            formPanel.add(gradeField);
            formPanel.add(teacherLabel);
            formPanel.add(teacherField);
            formPanel.add(studentsLabel);
            formPanel.add(studentsField);
            
            // Button Panel
            JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
            buttonPanel.setBackground(Color.WHITE);
            
            var saveBtn = new JButton("Create");
            saveBtn.setForeground(Color.WHITE);
            saveBtn.setBackground(new Color(39, 174, 96));
            saveBtn.setFocusPainted(false);
            saveBtn.setBorderPainted(false);
            saveBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
            saveBtn.setPreferredSize(new Dimension(120, 35));
            saveBtn.addMouseListener(new MouseAdapter() {
                public void mouseEntered(MouseEvent evt) {
                    saveBtn.setBackground(new Color(39, 174, 96).brighter());
                }
                public void mouseExited(MouseEvent evt) {
                    saveBtn.setBackground(new Color(39, 174, 96));
                }
            });
            
            var cancelBtn = new JButton("Cancel");
            cancelBtn.setForeground(Color.WHITE);
            cancelBtn.setBackground(new Color(149, 165, 166));
            cancelBtn.setFocusPainted(false);
            cancelBtn.setBorderPainted(false);
            cancelBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
            cancelBtn.setPreferredSize(new Dimension(120, 35));
            cancelBtn.addMouseListener(new MouseAdapter() {
                public void mouseEntered(MouseEvent evt) {
                    cancelBtn.setBackground(new Color(149, 165, 166).brighter());
                }
                public void mouseExited(MouseEvent evt) {
                    cancelBtn.setBackground(new Color(149, 165, 166));
                }
            });
            
            buttonPanel.add(saveBtn);
            buttonPanel.add(cancelBtn);
            
            dialogMainPanel.add(formPanel, BorderLayout.CENTER);
            dialogMainPanel.add(buttonPanel, BorderLayout.SOUTH);
            
            dialog.add(dialogMainPanel);
            
            // Save Button Action
            saveBtn.addActionListener(ev -> {
                String id = idField.getText().trim();
                String name = nameField.getText().trim();
                String grade = gradeField.getText().trim();
                String teacher = teacherField.getText().trim();
                String students = studentsField.getText().trim();
                
                if(id.isEmpty() || name.isEmpty() || grade.isEmpty() || teacher.isEmpty() || students.isEmpty()) {
                    JOptionPane.showMessageDialog(
                        dialog,
                        "Please fill in all fields!",
                        "Warning",
                        JOptionPane.WARNING_MESSAGE
                    );
                    return;
                }
                
                try {
                    Integer.parseInt(students);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(
                        dialog,
                        "Total Students must be a number!",
                        "Warning",
                        JOptionPane.WARNING_MESSAGE
                    );
                    return;
                }
                
                tableModel.addRow(new Object[]{id, name, grade, teacher, students});
                dialog.dispose();
                
                JOptionPane.showMessageDialog(
                    this,
                    "Class created successfully!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
                );
            });
            
            cancelBtn.addActionListener(ev -> dialog.dispose());
            
            dialog.setVisible(true);
        });
        
        // Edit Class Button Action
        editClassBtn.addActionListener(e -> {
            int selectedRow = classTable.getSelectedRow();
            
            if(selectedRow == -1) {
                JOptionPane.showMessageDialog(
                    this,
                    "Please select a class to edit!",
                    "Warning",
                    JOptionPane.WARNING_MESSAGE
                );
                return;
            }
            
            JDialog dialog = new JDialog(this, "Edit Class", true);
            dialog.setSize(450, 350);
            dialog.setLocationRelativeTo(this);
            dialog.setResizable(false);
            
            JPanel dialogMainPanel = new JPanel(new BorderLayout(10, 10));
            dialogMainPanel.setBackground(Color.WHITE);
            dialogMainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
            
            // Form Panel
            JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 15));
            formPanel.setBackground(Color.WHITE);
            
            String currentId = tableModel.getValueAt(selectedRow, 0).toString();
            String currentName = tableModel.getValueAt(selectedRow, 1).toString();
            String currentGrade = tableModel.getValueAt(selectedRow, 2).toString();
            String currentTeacher = tableModel.getValueAt(selectedRow, 3).toString();
            String currentStudents = tableModel.getValueAt(selectedRow, 4).toString();
            
            // Class ID Field (Read-only)
            JLabel idLabel = new JLabel("Class ID:");
            idLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            var idField = new JTextField(currentId);
            idField.setForeground(Color.BLACK);
            idField.setPreferredSize(new Dimension(200, 35));
            idField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(189, 195, 199), 1),
                new EmptyBorder(5, 10, 5, 10)
            ));
            idField.setEditable(false);
            idField.setBackground(new Color(240, 240, 240));
            
            // Class Name Field
            JLabel nameLabel = new JLabel("Class Name:");
            nameLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            var nameField = new JTextField(currentName);
            nameField.setForeground(Color.BLACK);
            nameField.setPreferredSize(new Dimension(200, 35));
            nameField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(189, 195, 199), 1),
                new EmptyBorder(5, 10, 5, 10)
            ));
            
            // Grade Field
            JLabel gradeLabel = new JLabel("Grade:");
            gradeLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            var gradeField = new JTextField(currentGrade);
            gradeField.setForeground(Color.BLACK);
            gradeField.setPreferredSize(new Dimension(200, 35));
            gradeField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(189, 195, 199), 1),
                new EmptyBorder(5, 10, 5, 10)
            ));
            
            // Teacher Field
            JLabel teacherLabel = new JLabel("Teacher:");
            teacherLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            var teacherField = new JTextField(currentTeacher);
            teacherField.setForeground(Color.BLACK);
            teacherField.setPreferredSize(new Dimension(200, 35));
            teacherField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(189, 195, 199), 1),
                new EmptyBorder(5, 10, 5, 10)
            ));
            
            // Total Students Field
            JLabel studentsLabel = new JLabel("Total Students:");
            studentsLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            var studentsField = new JTextField(currentStudents);
            studentsField.setForeground(Color.BLACK);
            studentsField.setPreferredSize(new Dimension(200, 35));
            studentsField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(189, 195, 199), 1),
                new EmptyBorder(5, 10, 5, 10)
            ));
            
            formPanel.add(idLabel);
            formPanel.add(idField);
            formPanel.add(nameLabel);
            formPanel.add(nameField);
            formPanel.add(gradeLabel);
            formPanel.add(gradeField);
            formPanel.add(teacherLabel);
            formPanel.add(teacherField);
            formPanel.add(studentsLabel);
            formPanel.add(studentsField);
            
            // Button Panel
            JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
            buttonPanel.setBackground(Color.WHITE);
            
            var updateBtn = new JButton("Update");
            updateBtn.setForeground(Color.WHITE);
            updateBtn.setBackground(new Color(52, 152, 219));
            updateBtn.setFocusPainted(false);
            updateBtn.setBorderPainted(false);
            updateBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
            updateBtn.setPreferredSize(new Dimension(120, 35));
            updateBtn.addMouseListener(new MouseAdapter() {
                public void mouseEntered(MouseEvent evt) {
                    updateBtn.setBackground(new Color(52, 152, 219).brighter());
                }
                public void mouseExited(MouseEvent evt) {
                    updateBtn.setBackground(new Color(52, 152, 219));
                }
            });
            
            var cancelBtn = new JButton("Cancel");
            cancelBtn.setForeground(Color.WHITE);
            cancelBtn.setBackground(new Color(149, 165, 166));
            cancelBtn.setFocusPainted(false);
            cancelBtn.setBorderPainted(false);
            cancelBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
            cancelBtn.setPreferredSize(new Dimension(120, 35));
            cancelBtn.addMouseListener(new MouseAdapter() {
                public void mouseEntered(MouseEvent evt) {
                    cancelBtn.setBackground(new Color(149, 165, 166).brighter());
                }
                public void mouseExited(MouseEvent evt) {
                    cancelBtn.setBackground(new Color(149, 165, 166));
                }
            });
            
            buttonPanel.add(updateBtn);
            buttonPanel.add(cancelBtn);
            
            dialogMainPanel.add(formPanel, BorderLayout.CENTER);
            dialogMainPanel.add(buttonPanel, BorderLayout.SOUTH);
            
            dialog.add(dialogMainPanel);
            
            // Update Button Action
            updateBtn.addActionListener(ev -> {
                String name = nameField.getText().trim();
                String grade = gradeField.getText().trim();
                String teacher = teacherField.getText().trim();
                String students = studentsField.getText().trim();
                
                if(name.isEmpty() || grade.isEmpty() || teacher.isEmpty() || students.isEmpty()) {
                    JOptionPane.showMessageDialog(
                        dialog,
                        "Please fill in all fields!",
                        "Warning",
                        JOptionPane.WARNING_MESSAGE
                    );
                    return;
                }
                
                try {
                    Integer.parseInt(students);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(
                        dialog,
                        "Total Students must be a number!",
                        "Warning",
                        JOptionPane.WARNING_MESSAGE
                    );
                    return;
                }
                
                tableModel.setValueAt(name, selectedRow, 1);
                tableModel.setValueAt(grade, selectedRow, 2);
                tableModel.setValueAt(teacher, selectedRow, 3);
                tableModel.setValueAt(students, selectedRow, 4);
                
                dialog.dispose();
                
                JOptionPane.showMessageDialog(
                    this,
                    "Class updated successfully!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
                );
            });
            
            cancelBtn.addActionListener(ev -> dialog.dispose());
            
            dialog.setVisible(true);
        });
        
        // Delete Class Button Action
        deleteClassBtn.addActionListener(e -> {
            int selectedRow = classTable.getSelectedRow();
            
            if(selectedRow == -1) {
                JOptionPane.showMessageDialog(
                    this,
                    "Please select a class to delete!",
                    "Warning",
                    JOptionPane.WARNING_MESSAGE
                );
                return;
            }
            
            String className = tableModel.getValueAt(selectedRow, 1).toString();
            
            int confirm = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to delete class '" + className + "'?",
                "Confirm Deletion",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
            );
            
            if(confirm == JOptionPane.YES_OPTION) {
                tableModel.removeRow(selectedRow);
                
                JOptionPane.showMessageDialog(
                    this,
                    "Class deleted successfully!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
                );
            }
        });
        
        this.setVisible(true);
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ClassWindow());
    }
}
