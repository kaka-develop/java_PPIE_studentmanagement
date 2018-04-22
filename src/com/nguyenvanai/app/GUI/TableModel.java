
package com.nguyenvanai.app.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

import com.nguyenvanai.app.assistants.AbstractAssistant;
import com.nguyenvanai.app.assistants.ApplicationAssistant;
import com.nguyenvanai.app.models.AbstractEntity;

@SuppressWarnings("serial")
public class TableModel extends AbstractTableModel {

	private AbstractAssistant assistant;
	private AbstractEntity[] entities;

	public TableModel(AbstractAssistant assistant) {
		this.assistant = assistant;
		entities = assistant.entitiesToArray();
	}

	public void setEntities(AbstractEntity[] entities) {
		this.entities = entities;
		this.fireTableDataChanged();
	}

	public void notifyChanged() {
		this.entities = assistant.entitiesToArray();
		this.fireTableDataChanged();
	}

	@Override
	public int getRowCount() {
		return entities.length;
	}

	@Override
	public int getColumnCount() {
		return assistant.getColumnNames().length;

	}

	@Override
	public String getColumnName(int column) {
		return assistant.getColumnNames()[column]; // To change body of
													// generated methods,
		// choose Tools | Templates.
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return assistant.getColumnTypes()[columnIndex]; // To change body of
														// generated
		// methods, choose Tools |
		// Templates.
	}
	
	
	

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {

		final AbstractEntity ae = entities[rowIndex];

		Object obj = assistant.getValueAt(ae, rowIndex, columnIndex);
		int columnCount = getColumnCount();
		if (obj == null) {

			if (columnIndex == columnCount - 2) {
				JButton btn_update = new JButton("update");
				btn_update.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						if (DialogFactory.createEditableDialog(ae).show(ApplicationAssistant.UPDATE, btn_update)) {
							notifyChanged();
						}
					}
				});
				return btn_update;

			} else if (columnIndex == columnCount-1) {

				JButton btn_del = new JButton("delete");
				btn_del.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {

						if (assistant.delete(ae.getId())) {
							notifyChanged();
						}else {
						 JOptionPane.showMessageDialog(null, "Can not delete this one that is related others.", "Error!",
						 JOptionPane.ERROR_MESSAGE);
						}

					}
				});
				return btn_del;
			}

		}
		return obj;
	}
}
