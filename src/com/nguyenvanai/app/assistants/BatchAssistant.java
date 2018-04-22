package com.nguyenvanai.app.assistants;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import com.nguyenvanai.app.GUI.dialogs.BatchDetailDialog;
import com.nguyenvanai.app.managers.BatchManager;
import com.nguyenvanai.app.models.AbstractEntity;
import com.nguyenvanai.app.models.Batch;

public class BatchAssistant extends AbstractAssistant {
	
	private static final String[] COLUMN_NAMES = new String[] { "ID", "Name", "update",
			"delete" };
	private static final Class<?>[] COLUMN_TYPES = new Class<?>[] { JButton.class, String.class, JButton.class, JButton.class };

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
		return assistant.batchsToArray();
	}

	@Override
	public int count() {
		// TODO Auto-generated method itemb
		return BatchManager.getInstance().count();
	}

	@Override
	public Object getValueAt(AbstractEntity entity, int rowIndex, int columnIndex) {
		final Batch item = (Batch) entity;

		switch (columnIndex) {
		case 0:
			JButton btn_info = new JButton(item.getId());
			btn_info.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					new BatchDetailDialog(item).show(item.getName(), btn_info);
				}
			});
			return btn_info;
		case 1:
			return item.getName();
		default:
			return null;
		}
	}

	@Override
	public boolean delete(String id) {
		return assistant.deleteBatch(id);
	}

}
