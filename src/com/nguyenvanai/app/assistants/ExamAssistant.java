package com.nguyenvanai.app.assistants;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import com.nguyenvanai.app.GUI.dialogs.ExamDetailDialog;
import com.nguyenvanai.app.managers.ExamManager;
import com.nguyenvanai.app.models.AbstractEntity;
import com.nguyenvanai.app.models.Exam;

public class ExamAssistant extends AbstractAssistant {

	private static final String[] COLUMN_NAMES = new String[] { "ID", "Name", "Content", "Course", "update", "delete" };
	private static final Class<?>[] COLUMN_TYPES = new Class<?>[] { JButton.class, String.class, String.class,
			String.class, JButton.class, JButton.class };

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
		return assistant.examsToArray();
	}

	@Override
	public int count() {
		// TODO Auto-generated method itemb
		return ExamManager.getInstance().count();
	}

	@Override
	public Object getValueAt(AbstractEntity entity, int rowIndex, int columnIndex) {
		final Exam item = (Exam) entity;

		switch (columnIndex) {
		case 0:
			JButton btn_info = new JButton(item.getId());
			btn_info.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					new ExamDetailDialog(item).show(item.getName(), btn_info);
				}
			});
			return btn_info;
		case 1:
			return item.getName();
		case 2:
			return item.getContent();
		case 3:
			return assistant.getCourseByID(item.getCourseId()).getName();
		default:
			return null;
		}
	}

	@Override
	public boolean delete(String id) {
		return assistant.deleteExam(id);
	}

}
