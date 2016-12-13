package com.example;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

import com.example.User;

@Entity
public class Offer {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column
	private String userLogin; //login wystawiającego
	@Column
	private int zloty;
	@Column
	private int groszy;
	@Column
	private String photo;
	@Column
	private String name; //tytuł oferty
	@Column
	@Lob
	private String desc; //opis
	@Column
	private String status; //to może przyjmować wartości: Aktywna, Zakończona, Anulowana
	@Column
	private String createDate; //data wystawienia oferty
	@Column
	private String endDate; //data zakończenia/anulacji oferty
	@Column
	private String buyerId; //login kupującego
	
	public String getUserLogin() {
		return userLogin;
	}

	public void setUserLogin(String userLogin) {
		this.userLogin = userLogin;
	}

	public String getStatus() {
		return status;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getBuyerId() {
		return buyerId;
	}

	public void setBuyerId(String buyerId) {
		this.buyerId = buyerId;
	}

	public void setStatus(String status) {
		this.status = status;
	}

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
