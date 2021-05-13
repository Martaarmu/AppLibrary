package com.martaarjona.AppLibrary.model;

import java.util.ArrayList;
import java.util.List;

public class Book {
	
	protected int isbn;
	protected String title;
	protected String author;
	protected String editorial;
	protected List<User> downloads;

	public Book(int isbn, String title, String author, String editorial, List<User> downloads) {
		super();
		this.isbn = isbn;
		this.title = title;
		this.author = author;
		this.editorial = editorial;
		this.downloads = downloads;
	}
	
	public Book() {
		this(-1,"","","",new ArrayList<User>());
	}

	public Book(int isbn, String title, String author, String editorial) {
		this.isbn=isbn;
		this.title = title;
		this.author = author;
		this.editorial = editorial;
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

	public int getIsbn() {
		return isbn;
	}

	public void setIsbn(int isbn) {
		this.isbn = isbn;
	}

	public String getEditorial() {
		return editorial;
	}

	public void setEditorial(String editorial) {
		this.editorial = editorial;
	}

	public List<User> getDownloads() {
		return downloads;
	}

	public void setDownloads(List<User> downloads) {
		this.downloads = downloads;
	}
	
	
	
	

	@Override
	public String toString() {
		return "\n-- ISBN: " + isbn + "\n-- Títutlo: " + title + 
				"\n-- Autor: " + author + "\n-- Editorial: " + editorial;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		if (isbn != other.isbn)
			return false;
		return true;
	}
	
	
	
}
