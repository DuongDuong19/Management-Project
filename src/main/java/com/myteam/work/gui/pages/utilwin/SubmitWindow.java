package com.myteam.work.gui.pages.utilwin;

import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;

public class SubmitWindow extends JFrame {
	private JButton revoke;
	private JButton cancel;
	private JButton submit;

	public SubmitWindow(boolean isRevoke) {
		this.setTitle("Submit ?");
		this.setSize(new Dimension(800, 600));
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setResizable(false);
		this.setLocationRelativeTo(null);

		var mainPanel = new JPanel(new BorderLayout());
		mainPanel.setOpaque(true);
		var btn = new JPanel(new BorderLayout());
		btn.setOpaque(true);
		var question = new JLabel("Are you sure to proceed ?");
		this.revoke = new JButton("Revoke");
		this.cancel = new JButton("Cancel");
		this.submit = new JButton("Submit");
		if(isRevoke) btn.add(revoke, BorderLayout.WEST);
		btn.add(cancel, BorderLayout.CENTER);
		btn.add(submit, BorderLayout.EAST);
		mainPanel.add(question, BorderLayout.CENTER);
		mainPanel.add(btn, BorderLayout.SOUTH);
		this.add(mainPanel);
		this.setVisible(true);
	}

	public void setRevokeAction(ActionListener al) {
		this.revoke.addActionListener(al);
	}

	public void setCancelAction(ActionListener al) {
		this.cancel.addActionListener(al);
	}

	public void setSubmitAction(ActionListener al) {
		this.submit.addActionListener(al);
	}
}
