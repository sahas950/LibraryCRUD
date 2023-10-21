package com.library.crud.LibraryCrudM.bean;

public class Product {
	
	private int id;
	private String bookCode;
	private String bookName;
	private String author;
	private String date;
	private String user;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBookCode() {
		return bookCode;
	}
	public void setBookCode(String bookCode) {
		this.bookCode = bookCode;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	
	public Product(int id, String bookCode, String bookName, String author, String date,String user) {
		super();
		this.id = id;
		this.bookCode = bookCode;
		this.bookName = bookName;
		this.author = author;
		this.date = date;
		this.user = user;
	}
	
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
		return "Product [ Book code=" + bookCode + ", Book name=" + bookName + ", author=" + author +", date="+date+ " User="+user+"]";
	}
	
}