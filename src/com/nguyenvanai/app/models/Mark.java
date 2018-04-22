package com.nguyenvanai.app.models;

public class Mark extends AbstractEntity {
	private String examId;
	private String studentId;

	public Mark(String id, String name, String examId, String studentId) {
		super.setId(id);
		super.setName(name);
		this.examId = examId;
		this.studentId = studentId;
	}

	public String getExamId() {
		return examId;
	}

	public void setExamId(String examId) {
		this.examId = examId;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

}
