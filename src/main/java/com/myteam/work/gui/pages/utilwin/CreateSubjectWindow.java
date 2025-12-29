package com.myteam.work.gui.pages.utilwin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Collections;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.myteam.work.gui.pages.MSTable;

public class CreateSubjectWindow extends JFrame {

	private static String defaultSubjectText = "Please enter subject name here";
    private static String defaultSubjectCredits = "0";
    private static String defaultSearchText = "Search by subject name or subject id";
    
    // Color scheme
    private static final Color PRIMARY_COLOR = new Color(41, 128, 185);
    private static final Color PANEL_BACKGROUND = Color.WHITE;
    private static final Color BACKGROUND_COLOR = new Color(236, 240, 241);
    
    public CreateSubjectWindow() {
        this.setTitle("Create class");
        this.setSize(new Dimension(900, 500));
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(BACKGROUND_COLOR);
        
        var mainPanel = new JPanel(new BorderLayout(15, 15));
        mainPanel.setBackground(BACKGROUND_COLOR);
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        
        // Top Panel
        var topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 10));
        topPanel.setBackground(PANEL_BACKGROUND);
        topPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(189, 195, 199), 1),
            new EmptyBorder(15, 15, 15, 15)
        ));
        
        var subjectName = createStyledTextField(defaultSubjectText, 300);
        var credits = createStyledTextField(defaultSubjectCredits, 80);
        var required = new JCheckBox("Required");
        required.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        required.setBackground(PANEL_BACKGROUND);
        
        topPanel.add(new JLabel("Subject:"));
        topPanel.add(subjectName);
        topPanel.add(new JLabel("Credits:"));
        topPanel.add(credits);
        topPanel.add(required);
        
        // Center Panel
        var centerPanel = new JPanel(new GridLayout(1, 2, 15, 0));
        centerPanel.setBackground(BACKGROUND_COLOR);
        
        // Chosen Panel
        var choosenPanel = new JPanel(new BorderLayout(10, 10));
        choosenPanel.setBackground(PANEL_BACKGROUND);
        choosenPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(189, 195, 199), 1),
            new EmptyBorder(15, 15, 15, 15)
        ));
        
        var choosenLabel = new JLabel("Selected Prerequisites");
        choosenLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        choosenLabel.setForeground(PRIMARY_COLOR);
        
        var choosenPrerequisitesTable = new MSTable(
            new String[]{"Id", "Subject name"}, 
            List.<Class<?>>of(Integer.class, String.class), 
            Collections.EMPTY_LIST
        );
        
        choosenPanel.add(choosenLabel, BorderLayout.NORTH);
        choosenPanel.add(choosenPrerequisitesTable.getDisplayer(), BorderLayout.CENTER);
        
        // Prerequisites Panel
        var prerequisitesPanel = new JPanel(new BorderLayout(10, 10));
        prerequisitesPanel.setBackground(PANEL_BACKGROUND);
        prerequisitesPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(189, 195, 199), 1),
            new EmptyBorder(15, 15, 15, 15)
        ));
        
        var prereqLabel = new JLabel("Available Subjects");
        prereqLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        prereqLabel.setForeground(PRIMARY_COLOR);
        
        var subjectSearchPanel = new JPanel(new BorderLayout(10, 10));
        subjectSearchPanel.setBackground(PANEL_BACKGROUND);
        
        var subjectSearch = createStyledTextField(defaultSearchText, 0);
        
        var subjectTable = new MSTable(
            new String[]{"ID", "Subject name", "Prerequisites", "Credits", "Require"}, 
            List.<Class<?>>of(String.class, String.class, String[].class, Short.class, String.class), 
            Collections.EMPTY_LIST
        );
        
        var topSearchPanel = new JPanel(new BorderLayout(10, 10));
        topSearchPanel.setBackground(PANEL_BACKGROUND);
        topSearchPanel.add(prereqLabel, BorderLayout.NORTH);
        topSearchPanel.add(subjectSearch, BorderLayout.CENTER);
        
        subjectSearchPanel.add(topSearchPanel, BorderLayout.NORTH);
        subjectSearchPanel.add(subjectTable.getDisplayer(), BorderLayout.CENTER);
        
        var prerequisitesBtnPanel = new JPanel(new GridLayout(2, 1, 5, 10));
        prerequisitesBtnPanel.setBackground(PANEL_BACKGROUND);
        
        var addPrerequisitesBtn = createStyledButton("Add Prerequisite", PRIMARY_COLOR);
        var removePrerequisitesBtn = createStyledButton("Remove Prerequisite", new Color(231, 76, 60));
        
        prerequisitesBtnPanel.add(addPrerequisitesBtn);
        prerequisitesBtnPanel.add(removePrerequisitesBtn);
        
        prerequisitesPanel.add(subjectSearchPanel, BorderLayout.CENTER);
        prerequisitesPanel.add(prerequisitesBtnPanel, BorderLayout.SOUTH);
        
        centerPanel.add(choosenPanel);
        centerPanel.add(prerequisitesPanel);
        
        // Bottom Panel
        var bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.setBackground(BACKGROUND_COLOR);
        
        var submitBtn = createStyledButton("Submit", new Color(39, 174, 96));
        submitBtn.setPreferredSize(new Dimension(150, 40));
        
        bottomPanel.add(submitBtn);
        
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
        
        this.add(mainPanel);
        
        // Add placeholder behavior
        addPlaceholderBehavior(subjectName, defaultSubjectText);
        addPlaceholderBehavior(credits, defaultSubjectCredits);
        addPlaceholderBehavior(subjectSearch, defaultSearchText);
        
        this.setVisible(true);
    }
    
    private JTextField createStyledTextField(String text, int width) {
        var textField = new JTextField(text);
        textField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
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
        button.setFont(new Font("Segoe UI", Font.BOLD, 13));
        button.setForeground(Color.WHITE);
        button.setBackground(bgColor);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setPreferredSize(new Dimension(180, 35));
        
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor.brighter());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor);
            }
        });
        
        return button;
    }
    
    private void addPlaceholderBehavior(JTextField textField, String placeholder) {
        textField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (textField.getText().equals(placeholder)) {
                    textField.setText("");
                    textField.setForeground(Color.BLACK);
                }
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (textField.getText().isEmpty()) {
                    textField.setText(placeholder);
                    textField.setForeground(Color.GRAY);
                }
            }
        });
    }


}
