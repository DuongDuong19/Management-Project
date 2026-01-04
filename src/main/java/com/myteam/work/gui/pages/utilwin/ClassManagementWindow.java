package com.myteam.work.gui.pages.utilwin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
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

/*public class ClassManagementWindow extends JFrame {
    
    private DefaultTableModel tableModel;
    private JTable studentTable;
    private JTextField nameField, emailField, phoneField, addressField;
    private JComboBox<String> genderComboBox;
    private JButton addBtn, submitBtn, deleteBtn;
    private int selectedRow = -1;
    
    public ClassManagementWindow() {
        this.setTitle("Student Management");
        this.setSize(new Dimension(1200, 700));
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(new Color(236, 240, 241));
        
        var mainPanel = new JPanel(new BorderLayout(15, 15));
        mainPanel.setBackground(new Color(236, 240, 241));
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        
        // Top Panel - Input Form
        var topPanel = new JPanel(new BorderLayout(10, 10));
        topPanel.setBackground(Color.WHITE);
        topPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(189, 195, 199), 1),
            new EmptyBorder(20, 20, 20, 20)
        ));
        
        var titleLabel = new JLabel("Student Information");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        titleLabel.setForeground(new Color(41, 128, 185));
        
        // Form Panel with input fields
        var formPanel = new JPanel(new GridLayout(3, 4, 15, 15));
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(new EmptyBorder(15, 0, 15, 0));
        
        // Row 1: Student Name and Email
        var nameLabel = new JLabel("Student Name:");
        nameLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        nameField = new JTextField();
        nameField.setPreferredSize(new Dimension(150, 35));
        nameField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(189, 195, 199), 1),
            new EmptyBorder(5, 10, 5, 10)
        ));
        
        var emailLabel = new JLabel("Email:");
        emailLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        emailField = new JTextField();
        emailField.setPreferredSize(new Dimension(150, 35));
        emailField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(189, 195, 199), 1),
            new EmptyBorder(5, 10, 5, 10)
        ));
        
        // Row 2: Phone and Gender
        var phoneLabel = new JLabel("Phone:");
        phoneLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        phoneField = new JTextField();
        phoneField.setPreferredSize(new Dimension(150, 35));
        phoneField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(189, 195, 199), 1),
            new EmptyBorder(5, 10, 5, 10)
        ));
        
        var genderLabel = new JLabel("Gender:");
        genderLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        String[] genders = {"Select Gender", "Male", "Female", "Other"};
        genderComboBox = new JComboBox<>(genders);
        genderComboBox.setPreferredSize(new Dimension(150, 35));
        genderComboBox.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        genderComboBox.setBackground(Color.WHITE);
        
        // Row 3: Address (spans 2 columns)
        var addressLabel = new JLabel("Address:");
        addressLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        addressField = new JTextField();
        addressField.setPreferredSize(new Dimension(150, 35));
        addressField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(189, 195, 199), 1),
            new EmptyBorder(5, 10, 5, 10)
        ));
        
        // Add components to form
        formPanel.add(nameLabel);
        formPanel.add(nameField);
        formPanel.add(emailLabel);
        formPanel.add(emailField);
        formPanel.add(phoneLabel);
        formPanel.add(phoneField);
        formPanel.add(genderLabel);
        formPanel.add(genderComboBox);
        formPanel.add(addressLabel);
        formPanel.add(addressField);
        formPanel.add(new JLabel("")); // Empty space
        formPanel.add(new JLabel("")); // Empty space
        
        topPanel.add(titleLabel, BorderLayout.NORTH);
        topPanel.add(formPanel, BorderLayout.CENTER);
        
        // Center Panel - Student Table
        var centerPanel = new JPanel(new BorderLayout(10, 10));
        centerPanel.setBackground(Color.WHITE);
        centerPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(189, 195, 199), 1),
            new EmptyBorder(15, 15, 15, 15)
        ));
        
        // Title panel with buttons
        var titlePanel = new JPanel(new BorderLayout());
        titlePanel.setBackground(Color.WHITE);
        
        var tableTitle = new JLabel("Student List");
        tableTitle.setFont(new Font("Segoe UI", Font.BOLD, 16));
        tableTitle.setForeground(new Color(41, 128, 185));
        
        // Button panel on the right
        var titleButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        titleButtonPanel.setBackground(Color.WHITE);
        
        addBtn = createStyledButton("Add", new Color(39, 174, 96));
        deleteBtn = createStyledButton("Delete", new Color(231, 76, 60));
        
        addBtn.setPreferredSize(new Dimension(100, 35));
        deleteBtn.setPreferredSize(new Dimension(100, 35));
        
        titleButtonPanel.add(addBtn);
        titleButtonPanel.add(deleteBtn);
        
        titlePanel.add(tableTitle, BorderLayout.WEST);
        titlePanel.add(titleButtonPanel, BorderLayout.EAST);
        
        // Create Table
        String[] columnNames = {"ID", "Name", "Email", "Phone", "Gender", "Address"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        studentTable = new JTable(tableModel);
        studentTable.setRowHeight(35);
        studentTable.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        studentTable.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        studentTable.getTableHeader().setBackground(new Color(41, 128, 185));
        studentTable.getTableHeader().setForeground(Color.WHITE);
        studentTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        studentTable.setSelectionBackground(new Color(52, 152, 219, 100));
        
        // Set column widths
        studentTable.getColumnModel().getColumn(0).setPreferredWidth(50);
        studentTable.getColumnModel().getColumn(1).setPreferredWidth(150);
        studentTable.getColumnModel().getColumn(2).setPreferredWidth(180);
        studentTable.getColumnModel().getColumn(3).setPreferredWidth(120);
        studentTable.getColumnModel().getColumn(4).setPreferredWidth(80);
        studentTable.getColumnModel().getColumn(5).setPreferredWidth(200);
        
        var scrollPane = new JScrollPane(studentTable);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(189, 195, 199), 1));
        
        centerPanel.add(titlePanel, BorderLayout.NORTH);
        centerPanel.add(scrollPane, BorderLayout.CENTER);
        
        // Bottom Panel - Submit Button
        var bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        bottomPanel.setBackground(new Color(236, 240, 241));
        
        submitBtn = createStyledButton("Submit", new Color(243, 156, 18));
        submitBtn.setPreferredSize(new Dimension(200, 45));
        
        bottomPanel.add(submitBtn);
        
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
        
        this.add(mainPanel);
        
        // Table Row Selection Listener
        studentTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                selectedRow = studentTable.getSelectedRow();
                if (selectedRow != -1) {
                    loadStudentToForm(selectedRow);
                }
            }
        });
        
        // Add Button Action
        addBtn.addActionListener(e -> addStudent());
        
        // Submit Button Action (Edit)
        submitBtn.addActionListener(e -> editStudent());
        
        // Delete Button Action
        deleteBtn.addActionListener(e -> deleteStudent());
        
        // Add sample data
        addSampleData();
        
        this.setVisible(true);
    }
    
    private void addStudent() {
        String name = nameField.getText().trim();
        String email = emailField.getText().trim();
        String phone = phoneField.getText().trim();
        String gender = (String) genderComboBox.getSelectedItem();
        String address = addressField.getText().trim();
        
        if (name.isEmpty() || email.isEmpty() || phone.isEmpty() || address.isEmpty()) {
            JOptionPane.showMessageDialog(
                this,
                "Please fill in all fields!",
                "Warning",
                JOptionPane.WARNING_MESSAGE
            );
            return;
        }
        
        if (gender.equals("Select Gender")) {
            JOptionPane.showMessageDialog(
                this,
                "Please select a gender!",
                "Warning",
                JOptionPane.WARNING_MESSAGE
            );
            return;
        }
        
        // Generate new ID
        int newId = tableModel.getRowCount() + 1;
        
        // Add to table
        tableModel.addRow(new Object[]{newId, name, email, phone, gender, address});
        
        JOptionPane.showMessageDialog(
            this,
            "Student added successfully!",
            "Success",
            JOptionPane.INFORMATION_MESSAGE
        );
        
        clearForm();
    }
    
    private void editStudent() {
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(
                this,
                "Please select a student to edit!",
                "Warning",
                JOptionPane.WARNING_MESSAGE
            );
            return;
        }
        
        String name = nameField.getText().trim();
        String email = emailField.getText().trim();
        String phone = phoneField.getText().trim();
        String gender = (String) genderComboBox.getSelectedItem();
        String address = addressField.getText().trim();
        
        if (name.isEmpty() || email.isEmpty() || phone.isEmpty() || address.isEmpty()) {
            JOptionPane.showMessageDialog(
                this,
                "Please fill in all fields!",
                "Warning",
                JOptionPane.WARNING_MESSAGE
            );
            return;
        }
        
        if (gender.equals("Select Gender")) {
            JOptionPane.showMessageDialog(
                this,
                "Please select a gender!",
                "Warning",
                JOptionPane.WARNING_MESSAGE
            );
            return;
        }
        
        // Update table row
        tableModel.setValueAt(name, selectedRow, 1);
        tableModel.setValueAt(email, selectedRow, 2);
        tableModel.setValueAt(phone, selectedRow, 3);
        tableModel.setValueAt(gender, selectedRow, 4);
        tableModel.setValueAt(address, selectedRow, 5);
        
        JOptionPane.showMessageDialog(
            this,
            "Student updated successfully!",
            "Success",
            JOptionPane.INFORMATION_MESSAGE
        );
        
        clearForm();
    }
    
    private void deleteStudent() {
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(
                this,
                "Please select a student to delete!",
                "Warning",
                JOptionPane.WARNING_MESSAGE
            );
            return;
        }
        
        int confirm = JOptionPane.showConfirmDialog(
            this,
            "Are you sure you want to delete this student?",
            "Confirm Delete",
            JOptionPane.YES_NO_OPTION
        );
        
        if (confirm == JOptionPane.YES_OPTION) {
            tableModel.removeRow(selectedRow);
            
            JOptionPane.showMessageDialog(
                this,
                "Student deleted successfully!",
                "Success",
                JOptionPane.INFORMATION_MESSAGE
            );
            
            clearForm();
        }
    }
    
    private void loadStudentToForm(int row) {
        nameField.setText(tableModel.getValueAt(row, 1).toString());
        emailField.setText(tableModel.getValueAt(row, 2).toString());
        phoneField.setText(tableModel.getValueAt(row, 3).toString());
        genderComboBox.setSelectedItem(tableModel.getValueAt(row, 4).toString());
        addressField.setText(tableModel.getValueAt(row, 5).toString());
    }
    
    private void clearForm() {
        nameField.setText("");
        emailField.setText("");
        phoneField.setText("");
        genderComboBox.setSelectedIndex(0);
        addressField.setText("");
        studentTable.clearSelection();
        selectedRow = -1;
    }
    
    private void addSampleData() {
        tableModel.addRow(new Object[]{1, "Nguyen Van A", "vana@email.com", "0123456789", "Male", "Hanoi"});
        tableModel.addRow(new Object[]{2, "Tran Thi B", "thib@email.com", "0987654321", "Female", "Ho Chi Minh"});
        tableModel.addRow(new Object[]{3, "Le Van C", "vanc@email.com", "0912345678", "Male", "Da Nang"});
    }
    
    private JButton createStyledButton(String text, Color bgColor) {
        var button = new JButton(text);
        button.setForeground(Color.WHITE);
        button.setBackground(bgColor);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        
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
        SwingUtilities.invokeLater(() -> new ClassManagementWindow());
    }
}*/
public class ClassManagementWindow extends JFrame {
    
    private DefaultTableModel tableModel;
    private JTable studentTable;
    private JTextField idField;
    private JButton addBtn, submitBtn, deleteBtn;
    private int selectedRow = -1;
    
    public ClassManagementWindow() {
        this.setTitle("Student Management");
        this.setSize(new Dimension(1200, 700));
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(new Color(236, 240, 241));
        
        var mainPanel = new JPanel(new BorderLayout(15, 15));
        mainPanel.setBackground(new Color(236, 240, 241));
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        
        // Top Panel - Input Form (only Student ID)
        var topPanel = new JPanel(new BorderLayout(10, 10));
        topPanel.setBackground(Color.WHITE);
        topPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(189, 195, 199), 1),
            new EmptyBorder(20, 20, 20, 20)
        ));
        
        var titleLabel = new JLabel("Student Information");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        titleLabel.setForeground(new Color(41, 128, 185));
        
        // Form Panel with only ID field
        var formPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 15));
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(new EmptyBorder(15, 0, 15, 0));
        
        var idLabel = new JLabel("Student ID:");
        idLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        idField = new JTextField();
        idField.setPreferredSize(new Dimension(250, 35));
        idField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(189, 195, 199), 1),
            new EmptyBorder(5, 10, 5, 10)
        ));
        
        formPanel.add(idLabel);
        formPanel.add(idField);
        
        topPanel.add(titleLabel, BorderLayout.NORTH);
        topPanel.add(formPanel, BorderLayout.CENTER);
        
        // Center Panel - Student Table
        var centerPanel = new JPanel(new BorderLayout(10, 10));
        centerPanel.setBackground(Color.WHITE);
        centerPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(189, 195, 199), 1),
            new EmptyBorder(15, 15, 15, 15)
        ));
        
        // Title panel with buttons
        var titlePanel = new JPanel(new BorderLayout());
        titlePanel.setBackground(Color.WHITE);
        
        var tableTitle = new JLabel("Student List");
        tableTitle.setFont(new Font("Segoe UI", Font.BOLD, 16));
        tableTitle.setForeground(new Color(41, 128, 185));
        
        // Button panel on the right
        var titleButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        titleButtonPanel.setBackground(Color.WHITE);
        
        addBtn = createStyledButton("Add", new Color(39, 174, 96));
        deleteBtn = createStyledButton("Delete", new Color(231, 76, 60));
        
        addBtn.setPreferredSize(new Dimension(100, 35));
        deleteBtn.setPreferredSize(new Dimension(100, 35));
        
        titleButtonPanel.add(addBtn);
        titleButtonPanel.add(deleteBtn);
        
        titlePanel.add(tableTitle, BorderLayout.WEST);
        titlePanel.add(titleButtonPanel, BorderLayout.EAST);
        
        // Create Table
        String[] columnNames = {"ID", "Name", "Email", "Phone", "Gender", "Address"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        studentTable = new JTable(tableModel);
        studentTable.setRowHeight(35);
        studentTable.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        studentTable.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        studentTable.getTableHeader().setBackground(new Color(41, 128, 185));
        studentTable.getTableHeader().setForeground(Color.WHITE);
        studentTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        studentTable.setSelectionBackground(new Color(52, 152, 219, 100));
        
        // Set column widths
        studentTable.getColumnModel().getColumn(0).setPreferredWidth(50);
        studentTable.getColumnModel().getColumn(1).setPreferredWidth(150);
        studentTable.getColumnModel().getColumn(2).setPreferredWidth(180);
        studentTable.getColumnModel().getColumn(3).setPreferredWidth(120);
        studentTable.getColumnModel().getColumn(4).setPreferredWidth(80);
        studentTable.getColumnModel().getColumn(5).setPreferredWidth(200);
        
        var scrollPane = new JScrollPane(studentTable);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(189, 195, 199), 1));
        
        centerPanel.add(titlePanel, BorderLayout.NORTH);
        centerPanel.add(scrollPane, BorderLayout.CENTER);
        
        // Bottom Panel - Submit Button
        var bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        bottomPanel.setBackground(new Color(236, 240, 241));
        
        submitBtn = createStyledButton("Submit", new Color(243, 156, 18));
        submitBtn.setPreferredSize(new Dimension(200, 45));
        
        bottomPanel.add(submitBtn);
        
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
        
        this.add(mainPanel);
        
        // Table Row Selection Listener
        studentTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                selectedRow = studentTable.getSelectedRow();
                if (selectedRow != -1) {
                    loadStudentToForm(selectedRow);
                }
            }
        });
        
        // Add Button Action
        addBtn.addActionListener(e -> addStudent());
        
        // Submit Button Action (Edit)
        submitBtn.addActionListener(e -> editStudent());
        
        // Delete Button Action
        deleteBtn.addActionListener(e -> deleteStudent());
        
        // Add sample data
        addSampleData();
        
        this.setVisible(true);
    }
    
    private void addStudent() {
        String id = idField.getText().trim();
        
        if (id.isEmpty()) {
            JOptionPane.showMessageDialog(
                this,
                "Please enter Student ID!",
                "Warning",
                JOptionPane.WARNING_MESSAGE
            );
            return;
        }
        
        // Check if ID already exists
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            if (tableModel.getValueAt(i, 0).toString().equals(id)) {
                JOptionPane.showMessageDialog(
                    this,
                    "Student ID already exists!",
                    "Warning",
                    JOptionPane.WARNING_MESSAGE
                );
                return;
            }
        }
        
        // Add to table with empty values for other fields
        tableModel.addRow(new Object[]{id, "", "", "", "", ""});
        
        JOptionPane.showMessageDialog(
            this,
            "Student added successfully!",
            "Success",
            JOptionPane.INFORMATION_MESSAGE
        );
        
        clearForm();
    }
    
    private void editStudent() {
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(
                this,
                "Please select a student to edit!",
                "Warning",
                JOptionPane.WARNING_MESSAGE
            );
            return;
        }
        
        String id = idField.getText().trim();
        
        if (id.isEmpty()) {
            JOptionPane.showMessageDialog(
                this,
                "Please enter Student ID!",
                "Warning",
                JOptionPane.WARNING_MESSAGE
            );
            return;
        }
        
        // Check if ID already exists in another row
        String currentId = tableModel.getValueAt(selectedRow, 0).toString();
        if (!id.equals(currentId)) {
            for (int i = 0; i < tableModel.getRowCount(); i++) {
                if (i != selectedRow && tableModel.getValueAt(i, 0).toString().equals(id)) {
                    JOptionPane.showMessageDialog(
                        this,
                        "Student ID already exists!",
                        "Warning",
                        JOptionPane.WARNING_MESSAGE
                    );
                    return;
                }
            }
        }
        
        // Update only the ID column
        tableModel.setValueAt(id, selectedRow, 0);
        
        JOptionPane.showMessageDialog(
            this,
            "Student updated successfully!",
            "Success",
            JOptionPane.INFORMATION_MESSAGE
        );
        
        clearForm();
    }
    
    private void deleteStudent() {
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(
                this,
                "Please select a student to delete!",
                "Warning",
                JOptionPane.WARNING_MESSAGE
            );
            return;
        }
        
        int confirm = JOptionPane.showConfirmDialog(
            this,
            "Are you sure you want to delete this student?",
            "Confirm Delete",
            JOptionPane.YES_NO_OPTION
        );
        
        if (confirm == JOptionPane.YES_OPTION) {
            tableModel.removeRow(selectedRow);
            
            JOptionPane.showMessageDialog(
                this,
                "Student deleted successfully!",
                "Success",
                JOptionPane.INFORMATION_MESSAGE
            );
            
            clearForm();
        }
    }
    
    private void loadStudentToForm(int row) {
        idField.setText(tableModel.getValueAt(row, 0).toString());
    }
    
    private void clearForm() {
        idField.setText("");
        studentTable.clearSelection();
        selectedRow = -1;
    }
    
    private void addSampleData() {
        tableModel.addRow(new Object[]{"SV001", "Nguyen Van A", "vana@email.com", "0123456789", "Male", "Hanoi"});
        tableModel.addRow(new Object[]{"SV002", "Tran Thi B", "thib@email.com", "0987654321", "Female", "Ho Chi Minh"});
        tableModel.addRow(new Object[]{"SV003", "Le Van C", "vanc@email.com", "0912345678", "Male", "Da Nang"});
    }
    
    private JButton createStyledButton(String text, Color bgColor) {
        var button = new JButton(text);
        button.setForeground(Color.WHITE);
        button.setBackground(bgColor);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        
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
        SwingUtilities.invokeLater(() -> new ClassManagementWindow());
    }
}