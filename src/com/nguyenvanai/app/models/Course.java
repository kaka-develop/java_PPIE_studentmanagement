package com.nguyenvanai.app.models;

public class Course extends AbstractEntity {
	
	private String lecturerId;
	
		
	public Course(String id, String name,String lecturerId) {
		super.setId(id);
		super.setName(name);
		this.lecturerId = lecturerId;
	}
	
	

	
	public String getLecturerId() {
		return lecturerId;
	}

	public void setLecturerId(String lecturerId) {
		this.lecturerId = lecturerId;
	}
	
	
	
	
}
