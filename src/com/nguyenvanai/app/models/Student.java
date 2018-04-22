package com.nguyenvanai.app.models;

import java.util.ArrayList;
import java.util.List;

import com.nguyenvanai.app.managers.RoleManager;

public class Student extends User {

	private List<String> courseIds;
	private String batchId;

	public Student(String id, String name, String email, String phone, String batchId, String courseId) {
		super(id, name, email, phone, RoleManager.STUDENT_ROLE);
		this.courseIds = new ArrayList<>();
		this.courseIds.add(courseId);
		this.batchId = batchId;
	}

	public Student(String id, String name, String email, String phone, String batchId) {
		super(id, name, email, phone, RoleManager.STUDENT_ROLE);
		this.courseIds = new ArrayList<>();
		this.batchId = batchId;
	}

	public String getBatchId() {
		return batchId;
	}

	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}

	public List<String> getCourseIds() {
		return courseIds;
	}

	public void setCourseIds(List<String> courseIds) {
		this.courseIds = courseIds;
	}

	public boolean addCourse(String courseId) {
		if (!this.courseIds.contains(courseId)) {
			this.courseIds.add(courseId);
			return true;
		} else
			return false;
	}

}
