package com.nguyenvanai.app.managers;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.gson.reflect.TypeToken;
import com.nguyenvanai.app.models.Lecturer;

public class LecturerManager extends AbstractManager {

	
	private static final LecturerManager instance = new LecturerManager();

	public static LecturerManager getInstance() {
		return instance;
	}

	
	@Override
	public String getFileName() {
		// TODO Auto-generated method stub
		return "lecturers.json";
	}

	@Override
	Type getListType() {
		Type listType = new TypeToken<Collection<Lecturer>>() {
		}.getType();
		return listType;
	}

	@Override
	boolean validateID(String id) {
		String pattern = "l[0-9]{2}$";
		Pattern p = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(id);
		return m.find();
	}

	// validate email of student
	public boolean validateEmail(String email) {
		return ValidationManager.validateEmail(email);
	}

	// validate phone number of student
	public boolean validatePhone(String phone) {
		return ValidationManager.validatePhone(phone);
	}

}
