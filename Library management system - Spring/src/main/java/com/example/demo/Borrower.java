package com.example.demo;

import java.util.Date;

public class Borrower {
	private Long id;
	private String email;
	private String name;
	private String title;
	private int num_book;
	private Date registerDate;
	
	public Borrower(String email, String name, String title, int num_book, Date registerDate){
		this.email = email;
		this.name = name;
		this.title = title;
		this.num_book = num_book;
		this.registerDate = registerDate;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getNumbook() {
		return num_book;
	}

	public void setNumbook(int num_book) {
		this.num_book = num_book;
	}
	
	public void setMinusNumbook(int num_book, int add) {
		this.num_book = num_book - add; 
	}
	
	public void setPlusNumbook(int num_book, int add) {
		this.num_book = num_book + add; 
	}
	
	public Date getRegisterDate() {
		return registerDate;
	}
	
	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}
	
}