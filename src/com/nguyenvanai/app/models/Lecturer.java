package com.nguyenvanai.app.models;

public class Lecturer extends AbstractEntity{
	
	private String email;
	private String phone;
	
	
	public Lecturer(String id, String name, String email, String phone) {
		super.setId(id);
		super.setName(name);
		this.email = email;
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}

	
}
