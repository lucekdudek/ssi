package com.example;

import java.io.Serializable;

public class Bid implements Serializable{
public String login;
public int price;

public Bid(String login, int price){
	this.login = login;
	this.price = price;
}
}



