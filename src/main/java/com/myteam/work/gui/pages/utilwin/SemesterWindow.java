package com.myteam.work.gui.pages.utilwin;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.myteam.work.controller.ManagerPageEventController;

public class SemesterWindow extends JFrame {

    private JTextField semesterField;
    private JTextField yearField;
    private JButton submitBtn;

    public SemesterWindow() {
        setTitle("Create New Semester");
        setSize(420, 220);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        add(buildForm(), BorderLayout.CENTER);
        add(buildFooter(), BorderLayout.SOUTH);

        setVisible(true);
    }

    private JPanel buildForm() {
        JPanel panel = new JPanel(new GridLayout(2, 2, 12, 12));
        panel.setBorder(new EmptyBorder(20, 25, 10, 25));

        panel.add(new JLabel("Semester"));
        semesterField = new JTextField();
        semesterField.setPreferredSize(new Dimension(200, 28));
        semesterField.setToolTipText("Example: HK1, HK2, HK3");
        panel.add(semesterField);

  
        panel.add(new JLabel("Year"));
        yearField = new JTextField();
        yearField.setPreferredSize(new Dimension(200, 28));
        yearField.setToolTipText("Example: 2024-2025");
        panel.add(yearField);

        return panel;
    }

    private JPanel buildFooter() {
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(0, 0, 15, 0));

        submitBtn = new JButton("Submit");
        submitBtn.addActionListener(e -> submit());

        panel.add(submitBtn);
        return panel;
    }

    private void submit() {
        String semester = semesterField.getText().trim();
        String year = yearField.getText().trim();

       
        if (semester.isEmpty()) {
            JOptionPane.showMessageDialog(
                this,
                "Semester must not be empty",
                "Validation Error",
                JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        if (year.isEmpty()) {
            JOptionPane.showMessageDialog(
                this,
                "Academic year must not be empty",
                "Validation Error",
                JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        ManagerPageEventController
            .getController()
            .createSemester(semester, year);

        dispose();
    }
}
