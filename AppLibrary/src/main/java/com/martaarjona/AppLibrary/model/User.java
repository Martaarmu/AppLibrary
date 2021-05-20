package com.martaarjona.AppLibrary.model;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.martaarjona.AppLibrary.utils.Connect;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * 
 * @author marta
 *
 */
public class User {
	
	protected int id;
	protected String dni;
	protected String name;
	protected String address;
	protected List<Book> mydownload;
	
	public User(int id, String dni, String name, String address, List<Book> mydownload) {
		this.id = id;
		this.dni = dni;
		this.name = name;
		this.address = address;
		this.mydownload=mydownload;
	}
	
	public User(int id,String dni, String name, String address) {
		this.id=id;
		this.dni=dni;
		this.name=name;
		this.address=address;
	}
	
	public User(String dni, String name, String address) {
		this.dni=dni;
		this.name=name;
		this.address=address;
	}
	
	public User() {
		this(-1,"","","",new ArrayList<Book>());
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<Book> getMybooks() {
		return mydownload;
	}

	public void setMybooks(List<Book> mydownload) {
		this.mydownload = mydownload;
	}
	
	

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (dni == null) {
			if (other.dni != null)
				return false;
		} else if (!dni.equals(other.dni))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return name ;
	}
	

	
}
