package com.myteam.work.gui.pages;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import javax.swing.BorderFactory;
import javax.swing.SwingConstants;

import com.myteam.work.gui.Window;
import com.myteam.work.Configuration;

public class MenuPanel extends JPanel {
	private static final Color background = new Color(15, 23, 42);
	private static final JButton[] teacherBtns = new JButton[2];
	private static final JButton[] managerBtns = new JButton[4];
	private static MenuPanel mp;
	private Boolean current;

	private MenuPanel() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		var windowSize = Window.getWindow().getSize();
		this.setPreferredSize(new Dimension(windowSize.width / 7, windowSize.height / 10 * 9));
		this.setBackground(background);
		this.add(Box.createRigidArea(new Dimension(0, 20)));
		teacherBtns[0] = createMenuBtn("Student Management");
		teacherBtns[1] = createMenuBtn("Subjects");
		managerBtns[0] = createMenuBtn("Student Management");
		managerBtns[1] = createMenuBtn("Teacher Management");
		managerBtns[2] = createMenuBtn("Subject Management");
		managerBtns[3] = createMenuBtn("Classes");
	}

	public static JPanel getPage() {
		if(mp == null) mp = new MenuPanel();

		return mp;
	}

	public void changeMenu(boolean teacher) {
		if(teacher) {
			if(this.current != null && this.current) return;
			if(this.current != null) for(var i = 0; i < managerBtns.length << 1; i++) this.remove(this.getComponentCount() - 1);

			for(var i = 0; i < teacherBtns.length; i++) {
				this.add(teacherBtns[i]);
				this.add(Box.createRigidArea(new Dimension(0, 5)));
			}

			this.current = true;
		}else {
			if(this.current != null && !this.current) return;
			if(this.current != null) for(var i = 0; i < teacherBtns.length << 1; i++) this.remove(this.getComponentCount() - 1);

			for(var i = 0; i < managerBtns.length; i++) {
				this.add(managerBtns[i]);
				this.add(Box.createRigidArea(new Dimension(0, 5)));
			}

			this.current = false;
		}
	}

	private JButton createMenuBtn(String title) {
		var btn = new JButton(title);
		btn.setAlignmentX(Component.CENTER_ALIGNMENT);
		btn.setForeground(Color.WHITE);
		btn.setBackground(background);
		btn.setContentAreaFilled(false);
		btn.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
		btn.setFocusPainted(false);
		btn.setHorizontalAlignment(SwingConstants.LEFT);
		btn.setCursor(Configuration.getConfiguration().getHandCursor());

		return btn;
	}
}
