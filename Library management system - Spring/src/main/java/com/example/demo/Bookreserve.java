package com.example.demo;

public class Bookreserve {
	private Long num;
	private String email;
	private String name;
	private String bookname;
	private int booknum;
	
	public Bookreserve(String email, String name, String bookname, int booknum){
		this.email = email;
		this.name = name;
		this.bookname = bookname;
		this.booknum = booknum;
	}
	
	public Long getNum() {
		return num;
	}
	
	public void setNum(Long num) {
		this.num = num;
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
	
	public String getBookname() {
		return bookname;
	}
	
	public void setBookname(String bookname) {
		this.bookname = bookname;
	}
	
	public int getBooknum() {
		return booknum;
	}
	
	public void setBooknum(int booknum) {
		this.booknum = booknum;
	}
}
