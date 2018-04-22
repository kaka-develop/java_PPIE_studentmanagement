package com.nguyenvanai.app.models;

public class User extends AbstractEntity{

	private String email;
	private String password;
	private String phone;
	private String role;
	

	
	public User(String id, String name,String email, String phone, String role) {
		super.setId(id);
		super.setName(name);
		this.email = email;
		this.phone = phone;
		this.role = role;
		this.password = "12345678";
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
}
