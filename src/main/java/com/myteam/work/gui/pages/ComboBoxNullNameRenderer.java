package com.myteam.work.gui.pages;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.border.EmptyBorder;

public class ComboBoxNullNameRenderer extends JLabel implements ListCellRenderer<Object> {
	public ComboBoxNullNameRenderer() {
		this.setOpaque(true);
		this.setBorder(new EmptyBorder(2, 5, 2, 5));
	}	

	public Component getListCellRendererComponent(JList<? extends Object> list, Object value, int index,boolean selected, boolean hasCellFocused) {
		this.setText(value == null ? "--SELECTED--" : value.toString());
		this.setBackground(selected ? list.getSelectionBackground() : list.getBackground());
		this.setForeground(list.getForeground());

		return this;
	} 
}
