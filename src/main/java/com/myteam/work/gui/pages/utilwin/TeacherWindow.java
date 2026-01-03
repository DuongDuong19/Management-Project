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

public class TeacherWindow extends JFrame {
    private static final Color PRIMARY_COLOR = new Color(41, 128, 185);
    private static final Color BACKGROUND_COLOR = new Color(236, 240, 241);
    
    private JTable teacherTable;
    private DefaultTableModel tableModel;
    
    public TeacherWindow() {
        this.setTitle("Teacher Management");
        this.setSize(new Dimension(950, 550));
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(BACKGROUND_COLOR);
        
        var mainPanel = new JPanel(new BorderLayout(15, 15));
        mainPanel.setBackground(BACKGROUND_COLOR);
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        
        // Center Panel - Teacher List
        var centerPanel = new JPanel(new BorderLayout(10, 10));
        centerPanel.setBackground(Color.WHITE);
        centerPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(189, 195, 199), 1),
            new EmptyBorder(15, 15, 15, 15)
        ));
        
        var teacherListLabel = new JLabel("Teacher List");
        teacherListLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        teacherListLabel.setForeground(PRIMARY_COLOR);
        
        // Create Table
        String[] columnNames = {"ID", "Teacher Name", "Date of Birth", "Subject", "Department"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        teacherTable = new JTable(tableModel);
        teacherTable.setRowHeight(30);
        teacherTable.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        teacherTable.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        teacherTable.getTableHeader().setBackground(PRIMARY_COLOR);
        teacherTable.getTableHeader().setForeground(Color.WHITE);
        teacherTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        teacherTable.setSelectionBackground(new Color(52, 152, 219, 100));
        
        var scrollPane = new JScrollPane(teacherTable);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(189, 195, 199), 1));
        
        centerPanel.add(teacherListLabel, BorderLayout.NORTH);
        centerPanel.add(scrollPane, BorderLayout.CENTER);
        
        // Bottom Panel - Action Buttons
        var bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        bottomPanel.setBackground(BACKGROUND_COLOR);
        
        var addTeacherBtn = createStyledButton("Add Teacher", new Color(39, 174, 96));
        var removeTeacherBtn = createStyledButton("Remove Teacher", new Color(231, 76, 60));
        
        addTeacherBtn.setPreferredSize(new Dimension(150, 40));
        removeTeacherBtn.setPreferredSize(new Dimension(150, 40));
        
        bottomPanel.add(addTeacherBtn);
        bottomPanel.add(removeTeacherBtn);
        
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
        
        this.add(mainPanel);
        
        // Add Teacher Button Action
        addTeacherBtn.addActionListener(e -> {
            openAddTeacherDialog();
        });
        
        // Remove Teacher Button Action
        removeTeacherBtn.addActionListener(e -> {
            removeTeacher();
        });
        
        this.setVisible(true);
    }
    
    private void openAddTeacherDialog() {
        JDialog dialog = new JDialog(this, "Add New Teacher", true);
        dialog.setSize(450, 350);
        dialog.setLocationRelativeTo(this);
        dialog.setResizable(false);
        
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        
        // Form Panel
        JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 15));
        formPanel.setBackground(Color.WHITE);
        
        // ID Field
        JLabel idLabel = new JLabel("Teacher ID:");
        idLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        JTextField idField = createStyledTextField("", 200);
        
        // Name Field
        JLabel nameLabel = new JLabel("Teacher Name:");
        nameLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        JTextField nameField = createStyledTextField("", 200);
        
        // Date of Birth Field
        JLabel dobLabel = new JLabel("Date of Birth:");
        dobLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        JTextField dobField = createStyledTextField("YYYY-MM-DD", 200);
        
        // Subject Field
        JLabel subjectLabel = new JLabel("Subject:");
        subjectLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        JTextField subjectField = createStyledTextField("", 200);
        
        // Department Field
        JLabel deptLabel = new JLabel("Department:");
        deptLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        JTextField deptField = createStyledTextField("", 200);
        
        formPanel.add(idLabel);
        formPanel.add(idField);
        formPanel.add(nameLabel);
        formPanel.add(nameField);
        formPanel.add(dobLabel);
        formPanel.add(dobField);
        formPanel.add(subjectLabel);
        formPanel.add(subjectField);
        formPanel.add(deptLabel);
        formPanel.add(deptField);
        
        // Button Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        buttonPanel.setBackground(Color.WHITE);
        
        JButton saveBtn = createStyledButton("Save", new Color(39, 174, 96));
        JButton cancelBtn = createStyledButton("Cancel", new Color(149, 165, 166));
        
        saveBtn.setPreferredSize(new Dimension(120, 35));
        cancelBtn.setPreferredSize(new Dimension(120, 35));
        
        buttonPanel.add(saveBtn);
        buttonPanel.add(cancelBtn);
        
        mainPanel.add(formPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        dialog.add(mainPanel);
        
        // Save Button Action
        saveBtn.addActionListener(e -> {
            String id = idField.getText().trim();
            String name = nameField.getText().trim();
            String dob = dobField.getText().trim();
            String subject = subjectField.getText().trim();
            String dept = deptField.getText().trim();
            
            if(id.isEmpty() || name.isEmpty() || dob.isEmpty() || subject.isEmpty() || dept.isEmpty()) {
                JOptionPane.showMessageDialog(
                    dialog,
                    "Please fill in all fields!",
                    "Warning",
                    JOptionPane.WARNING_MESSAGE
                );
                return;
            }
            
            // Add row to table
            tableModel.addRow(new Object[]{id, name, dob, subject, dept});
            dialog.dispose();
            
            JOptionPane.showMessageDialog(
                this,
                "Teacher added successfully!",
                "Success",
                JOptionPane.INFORMATION_MESSAGE
            );
        });
        
        // Cancel Button Action
        cancelBtn.addActionListener(e -> dialog.dispose());
        
        dialog.setVisible(true);
    }
    
    private void removeTeacher() {
        int selectedRow = teacherTable.getSelectedRow();
        
        if(selectedRow == -1) {
            JOptionPane.showMessageDialog(
                this,
                "Please select a teacher to remove!",
                "Warning",
                JOptionPane.WARNING_MESSAGE
            );
            return;
        }
        
        int confirm = JOptionPane.showConfirmDialog(
            this,
            "Are you sure you want to remove this teacher?",
            "Confirm Removal",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE
        );
        
        if(confirm == JOptionPane.YES_OPTION) {
            tableModel.removeRow(selectedRow);
            
            JOptionPane.showMessageDialog(
                this,
                "Teacher removed successfully!",
                "Success",
                JOptionPane.INFORMATION_MESSAGE
            );
        }
    }
    
    private JTextField createStyledTextField(String text, int width) {
        var textField = new JTextField(text);
        textField.setForeground(Color.GRAY);
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
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
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
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TeacherWindow());
    }
}