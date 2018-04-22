package com.nguyenvanai.app.managers;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.gson.reflect.TypeToken;
import com.nguyenvanai.app.models.Course;

public class CourseManager extends AbstractManager{

	
	private static final CourseManager instance = new CourseManager();

	public static CourseManager getInstance() {
		return instance;
	}

	@Override
	public String getFileName() {
		// TODO Auto-generated method stub
		return "courses.json";
	}
	
	@Override
	Type getListType() {
		Type listType = new TypeToken<Collection<Course>>() {
		}.getType();
		return listType;
	}

	@Override
	boolean validateID(String id) {
		String pattern = "c[0-9]{2}$";
		Pattern p = Pattern.compile(pattern,Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(id);
		return m.find();
	}
}
