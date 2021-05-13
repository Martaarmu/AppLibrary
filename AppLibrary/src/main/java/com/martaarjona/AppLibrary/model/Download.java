package com.martaarjona.AppLibrary.model;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.Date;

public class Download {
	
	protected int id_user;
	protected int isbn_book;
	protected String date;
	
	public Download(int id_user, int isbn_book, String date) {
		this.id_user = id_user;
		this.isbn_book = isbn_book;
		this.date = date;
	}
	public Download(int id_user, int isbn_book) {
		this.id_user = id_user;
		this.isbn_book = isbn_book;
	}
	public Download(int isbn_book, String date) {
		this.isbn_book = isbn_book;
		this.date = date;
	}
	
	
	public Download() {
		
	}

	public int getId_user() {
		return id_user;
	}

	public void setId_user(int id_user) {
		this.id_user = id_user;
	}

	public int getIsbn_book() {
		return isbn_book;
	}

	public void setIsbn_book(int isbn_book) {
		this.isbn_book = isbn_book;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Download other = (Download) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (id_user != other.id_user)
			return false;
		if (isbn_book != other.isbn_book)
			return false;
		return true;
	}

}
