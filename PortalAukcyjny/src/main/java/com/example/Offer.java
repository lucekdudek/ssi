package com.example;

import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class Offer {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column
	private String userLogin; //login wystawiającego
	@Column
	private int price;
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
	@Column
	private ArrayList<String> biddersList; //loginy licytujacych


	public Offer(){}
	
	public Offer(String name, String photo, int price, String desc, String date){
		this.name = name;
		this.photo = photo;
		this.price = price;
		this.desc = desc;
		this.endDate = date;
		this.biddersList = new ArrayList<String>();
	}
	
	
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

	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
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
	
	public ArrayList<String> getBiddersList() {
		return biddersList;
	}

	public void setBiddersList(ArrayList<String> biddersList) {
		this.biddersList = biddersList;
	}
	
	
}
