package com.example.demo;

public class Book {
	private Long num;
	private String title;
	private String author;
	private int stock;
	
	public Book(String title, String author){
		this.title = title;
		this.author = author;
	}
	
	public Book(String title, String author, int stock){
		this.title = title;
		this.author = author;
		this.stock = stock;
	}
	
	public Long getNum() {
		return num;
	}
	
	public void setNum(Long num) {
		this.num = num;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public int getStock() {
		return stock;
	}
	
	public void setStock(int stock) {
		this.stock = stock;
	}
	
	public void setPlusStock(int stock, int add){
	       this.stock = stock + add;
	}
	
    public void setMinusStock(int stock, int add) {
    		this.stock = stock - add; 
    }
}