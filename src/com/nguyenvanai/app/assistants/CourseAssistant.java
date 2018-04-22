package com.nguyenvanai.app.assistants;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import com.nguyenvanai.app.GUI.dialogs.CourseDetailDialog;
import com.nguyenvanai.app.managers.CourseManager;
import com.nguyenvanai.app.models.AbstractEntity;
import com.nguyenvanai.app.models.Course;

public class CourseAssistant extends AbstractAssistant {

	private static final String[] COLUMN_NAMES = new String[] { "ID", "Name","Lecturer","update", "delete" };
	private static final Class<?>[] COLUMN_TYPES = new Class<?>[] { JButton.class, String.class, String.class, JButton.class,
			JButton.class };

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
		return assistant.coursesToArray();
	}

	@Override
	public int count() {
		// TODO Auto-generated method itemb
		return CourseManager.getInstance().count();
	}

	@Override
	public Object getValueAt(AbstractEntity entity, int rowIndex, int columnIndex) {
		final Course item = (Course) entity;

		switch (columnIndex) {
		case 0:
			JButton btn_info = new JButton(item.getId());
			btn_info.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					new CourseDetailDialog(item).show(item.getName(), btn_info);
				}
			});
			return btn_info;
		case 1:
			return item.getName();
		case 2:
			return assistant.getLecturerByID(item.getLecturerId()).getName();
		default:
			return null;
		}
	}

	@Override
	public boolean delete(String id) {
		return assistant.deleteCourse(id);
	}

}
