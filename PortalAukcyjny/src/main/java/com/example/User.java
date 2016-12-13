package com.example;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User{
	@Id
	//@GeneratedValue(strategy = GenerationType.AUTO)
	private String login;
	private String password;
	private String email;
	private String firstName;
	private String lastName;
	private int permissions;
	
	public int getPermissions() {
		return permissions;
	}

	public void setPermissions(int permissions) {
		this.permissions = permissions;
	}

	public User(){
		this.setPermissions(1);
	}
	
	public String getLogin() {
		return login;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
}
