package com.nguyenvanai.app.managers;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.gson.reflect.TypeToken;
import com.nguyenvanai.app.models.Batch;

public class BatchManager extends AbstractManager{

	private static final BatchManager instance = new BatchManager();
	public static BatchManager getInstance() {
		return instance;
	}
	
	

	@Override
	public String getFileName() {
		return "batches.json";
	}
	
	@Override
	Type getListType() {
		Type listType = new TypeToken<Collection<Batch>>() {
		}.getType();
		return listType;
	}


	@Override
	boolean validateID(String id) {
		String pattern = "b[0-9]{2}$";
		Pattern p = Pattern.compile(pattern,Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(id);
		return m.find();
	}
}
