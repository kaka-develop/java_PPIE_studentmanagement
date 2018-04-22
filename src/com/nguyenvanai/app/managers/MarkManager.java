package com.nguyenvanai.app.managers;

import java.lang.reflect.Type;
import java.util.Collection;

import com.google.gson.reflect.TypeToken;
import com.nguyenvanai.app.models.Mark;

public class MarkManager extends AbstractManager {
	private static final MarkManager instance = new MarkManager();

	public static MarkManager getInstance() {
		return instance;
	}

	@Override
	public String getFileName() {
		// TODO Auto-generated method stub
		return "marks.json";
	}

	@Override
	Type getListType() {
		Type listType = new TypeToken<Collection<Mark>>() {
		}.getType();
		return listType;
	}

	@Override
	boolean validateID(String id) {
		// TODO Auto-generated method stub
		return true;
	}

}
