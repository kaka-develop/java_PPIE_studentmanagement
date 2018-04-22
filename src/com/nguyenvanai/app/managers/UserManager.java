package com.nguyenvanai.app.managers;

import java.lang.reflect.Type;
import java.util.Collection;

import com.google.gson.reflect.TypeToken;
import com.nguyenvanai.app.models.AbstractEntity;
import com.nguyenvanai.app.models.User;

public class UserManager extends AbstractManager {

	private static final UserManager instance = new UserManager();

	public static UserManager getInstance() {
		return instance;
	}
	
	@Override
	public String getFileName() {
		// TODO Auto-generated method stub
		return "users.json";
	}

	@Override
	Type getListType() {
		Type listType = new TypeToken<Collection<User>>() {
		}.getType();
		return listType;
		
	}

	@Override
	boolean validateID(String id) {
		
		return true;
	}
	
	public User getUserByEmailAndPassword(String email,String password){
		for(AbstractEntity ae : all()){
			User u = (User) ae;
			if(u.getEmail().equals(email) && u.getPassword().equals(password))
				return u;
		}
		return null;
	}

}
