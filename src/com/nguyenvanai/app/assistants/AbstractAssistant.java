package com.nguyenvanai.app.assistants;

import com.nguyenvanai.app.models.AbstractEntity;

public abstract class AbstractAssistant {
	
	ApplicationAssistant assistant =ApplicationAssistant.getInstance();
	
	public abstract String[] getColumnNames();
	public abstract Class<?>[] getColumnTypes();
	public abstract AbstractEntity[] entitiesToArray();
	public abstract int count();
	public abstract boolean delete(String id);
	public abstract Object getValueAt(AbstractEntity entity, int rowIndex, int columnIndex);
}
