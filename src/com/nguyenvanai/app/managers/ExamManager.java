package com.nguyenvanai.app.managers;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.gson.reflect.TypeToken;
import com.nguyenvanai.app.models.Exam;

public class ExamManager extends AbstractManager {

	
	private static final ExamManager instance = new ExamManager();
	public static ExamManager getInstance() {
		return instance;
	}
	@Override
	public String getFileName() {
		// TODO Auto-generated method stub
		return "exams.json";
	}
	
	
	@Override
	Type getListType() {
		Type listType = new TypeToken<Collection<Exam>>() {
		}.getType();
		return listType;
	}

	@Override
	boolean validateID(String id) {
		String pattern = "e[0-9]{2}$";
		Pattern p = Pattern.compile(pattern,Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(id);
		return m.find();
	}
}
