package com.nguyenvanai.app.managers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationManager {
	
		// validate email of student
		public static boolean validateEmail(String email){
			String pattern = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
			Pattern p = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
			Matcher m = p.matcher(email);
			return m.find();
		}

		// validate phone number of student
		public static boolean validatePhone(String phone){
			String pattern = "^[0-9]{10,11}$";
			Pattern p = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
			Matcher m = p.matcher(phone);
			return m.find();
		}
}
