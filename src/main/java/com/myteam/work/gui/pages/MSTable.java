package com.myteam.work.gui.pages;

import java.util.List;
import java.util.Arrays;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.DefaultTableCellRenderer;

class MultipleLineRenderer extends JList<Object> implements TableCellRenderer {
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		if(value instanceof Object[] oa) setListData(oa);

		return this;
	}
}

public class MSTable {
	private JScrollPane sp;
	private JTable stickyTable;
	private JTable contentTable;

	public MSTable(String[] columnName, List<Class<?>> contentTypes, List<Integer> contentEditableColumn) {
		var stickyColumnName = new String[] {columnName[0]};
		var contentColumnName = new String[columnName.length - 1];

		for(var i = 0; i < contentColumnName.length; i++) contentColumnName[i] = columnName[i + 1];

		var stickyTableModel = new DefaultTableModel(new Object[][]{}, stickyColumnName) {
			public Class<?> getColumnClass(int column) {
				return Integer.class;
			}

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		var contentTableModel = new DefaultTableModel(new Object[][]{}, contentColumnName) {
			public Class<?> getColumnClass(int column) {
				return contentTypes.get(column);
			}

			public boolean isCellEditable(int row, int column) {
				return contentEditableColumn.contains(column);
			}
		};
		var centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		var mlr = new MultipleLineRenderer();
		this.stickyTable = new JTable(stickyTableModel);
		this.stickyTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		this.stickyTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		this.stickyTable.setDefaultRenderer(Object.class, mlr);
		this.contentTable = new JTable(contentTableModel);
		this.contentTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		this.contentTable.setSelectionModel(this.stickyTable.getSelectionModel());
		for(int i = 0; i < this.contentTable.getColumnCount(); i++) this.contentTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
		this.contentTable.setDefaultRenderer(Object.class, mlr);
		this.sp = new JScrollPane(contentTable);
		var vp = new JViewport() {
			public Dimension getPreferredSize() {
				var d = super.getPreferredSize();
				d.width = stickyTable.getPreferredSize().width;

				return d;
			}
		};
		vp.setView(this.stickyTable);
		this.sp.setRowHeader(vp);
		this.sp.setCorner(JScrollPane.UPPER_LEFT_CORNER, this.stickyTable.getTableHeader());
	}

	public JScrollPane getDisplayer() {
		return this.sp;
	}

	public void setRowHeight(int rowHeight) {
		this.stickyTable.setRowHeight(rowHeight);
		this.contentTable.setRowHeight(rowHeight);
	}

	public void setShowGrid(boolean grid) {
		this.stickyTable.setShowGrid(grid);
		this.contentTable.setShowGrid(grid);
	}

	public void setPreferredWidth(int column, int width) {
		if(column == 0) this.stickyTable.getColumnModel().getColumn(0).setPreferredWidth(width);
		else this.contentTable.getColumnModel().getColumn(column - 1).setPreferredWidth(width);
	}

	public void setIntercellSpacing(Dimension d) {
		this.stickyTable.setIntercellSpacing(d);
		this.contentTable.setIntercellSpacing(d);
	}

	public void setSelectionMode(int lsm) {
		this.stickyTable.setSelectionMode(lsm);
		this.contentTable.setSelectionMode(lsm);
	}

	public void setReorderingColumn(boolean rc) {
		this.stickyTable.getTableHeader().setReorderingAllowed(rc);
		this.contentTable.getTableHeader().setReorderingAllowed(rc);
	}

	public void setResizingColumn(boolean rc) {
		this.stickyTable.getTableHeader().setResizingAllowed(rc);
		this.contentTable.getTableHeader().setResizingAllowed(rc);
	}

	public void clearData() {
		((DefaultTableModel) this.stickyTable.getModel()).setRowCount(0);
		((DefaultTableModel) this.contentTable.getModel()).setRowCount(0);
	}

	public void addData(Object[][] data) {
		for(Object[] datum : data) {
			((DefaultTableModel) this.stickyTable.getModel()).addRow(new Object[]{datum[0]});
			((DefaultTableModel) this.contentTable.getModel()).addRow(Arrays.copyOfRange(datum, 1, datum.length));
		}
	}
}
