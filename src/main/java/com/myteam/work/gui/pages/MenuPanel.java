package com.myteam.work.gui.pages;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.BoxLayout;

import com.myteam.work.gui.Window;

public class MenuPanel extends JPanel {
	private static final Color background = new Color(99, 102, 241);
	private static final JButton[] teacherBtns = new JButton[2];
	private static final JButton[] managerBtns = new JButton[4];
	private static MenuPanel mp;
	private Boolean current;

	private MenuPanel() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		var windowSize = Window.getWindow().getSize();
		this.setPreferredSize(new Dimension(windowSize.width / 5, windowSize.height / 10 * 9));
		this.setBackground(background);
		this.add(Box.createRigidArea(new Dimension(0, 20)));
		var studentBtn = new JButton("Tsudent Management");
		var subjectBtn = new JButton("Subjects");
		teacherBtns[0] = studentBtn;
		teacherBtns[1] = subjectBtn;
		var studentManagerBtn = new JButton("Tsudent Management");
		var teacherBtn = new JButton("Teacher Management");
		var subjectManagerBtn = new JButton("Subjects Management");
		var classBtn = new JButton("Classes");
		managerBtns[0] = studentManagerBtn;
		managerBtns[1] = teacherBtn;
		managerBtns[2] = subjectManagerBtn;
		managerBtns[3] = classBtn;
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
}
