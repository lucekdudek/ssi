package com.example;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import com.example.User;

@Entity
public class Offer {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	//private User user;
	private int zloty;
	private int groszy;
	private String photo;
	private String name;
	private String desc;
	
	public Offer(){}
	
	public Offer(String name, String photo, String desc){
		this.name = name;
		this.photo = photo;
		this.desc = desc;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}

//	public User getUser() {
//		return user;
//	}
//
//	public void setUser(User user) {
//		this.user = user;
//	}

	public int getZloty() {
		return zloty;
	}

	public void setZloty(int zloty) {
		this.zloty = zloty;
	}

	public int getGroszy() {
		return groszy;
	}

	public void setGroszy(int groszy) {
		this.groszy = groszy;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	
}
