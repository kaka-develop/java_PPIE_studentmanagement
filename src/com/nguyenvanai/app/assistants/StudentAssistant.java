package com.nguyenvanai.app.assistants;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import com.nguyenvanai.app.GUI.dialogs.StudentDetailDialog;
import com.nguyenvanai.app.managers.StudentManager;
import com.nguyenvanai.app.models.AbstractEntity;
import com.nguyenvanai.app.models.Student;

public class StudentAssistant extends AbstractAssistant {
	

	private static final String[] COLUMN_NAMES = new String[] { "ID", "Name", "Email", "Batch", "update",
			"delete" };
	private static final Class<?>[] COLUMN_TYPES = new Class<?>[] { JButton.class, String.class,
			String.class, String.class, JButton.class, JButton.class };

	@Override
	public String[] getColumnNames() {
		// TODO Auto-generated method itemb
		return COLUMN_NAMES;
	}

	@Override
	public Class<?>[] getColumnTypes() {
		// TODO Auto-generated method itemb
		return COLUMN_TYPES;
	}

	@Override
	public AbstractEntity[] entitiesToArray() {
		return assistant.studentsToArray();
	}

	@Override
	public int count() {
		// TODO Auto-generated method itemb
		return StudentManager.getInstance().count();
	}
	
	@Override
	public Object getValueAt(AbstractEntity entity, int rowIndex, int columnIndex) {
		final Student item = (Student) entity;

		switch (columnIndex) {
		case 0:
			JButton btn_info = new JButton(item.getId());
			btn_info.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					new StudentDetailDialog(item).show(item.getName(), btn_info);
				}
			});
			return btn_info;
		case 1:
			return item.getName();
		case 2:
			return item.getEmail();
		case 3:
			return assistant.getBatchByID(item.getBatchId()).getName();
		
		default:
			return null;
		}
	}

	@Override
	public boolean delete(String id) {
		return assistant.deleteStudent(id);
	}

	

}
