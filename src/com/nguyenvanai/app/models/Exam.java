package com.nguyenvanai.app.models;

public class Exam extends AbstractEntity {

	private String content;
	private String courseId;
	
	
	public Exam(String id, String name, String content, String courseId) {
		super.setId(id);
		super.setName(name);
		this.content = content;
		this.courseId = courseId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}


	
	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

}
