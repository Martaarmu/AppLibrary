package com.martaarjona.AppLibrary.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.martaarjona.AppLibrary.utils.Connect;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class BookDAO extends Book{
	
	public final static String GETBYISBN="SELECT isbn,title,author,editorial,date FROM book WHERE isbn=";
	public final static String GETMYDOWNLOAD="SELECT * FROM download, book, user "
			+ "WHERE download.isbn_book = book.isbn AND download.id_user = user.id AND book.isbn=?";
	public final static String BOOKS="SELECT * FROM book";
	
	public BookDAO (int isbn, String title, String author, String editorial) {
		super(isbn,title,author,editorial);
	}
	
	public BookDAO() {
		super();
	}
	/*
	public BookDAO (int isbn) {
		Connection con = Connect.getConnect();
		if (con!=null) {
			try {
				Statement st = con.createStatement();
				String q = GETBYISBN+isbn;
				ResultSet rs = st.executeQuery(q);
				while(rs.next()) {
					this.isbn=rs.getInt("isbn");
					this.title=rs.getString("title");
					this.author=rs.getString("author");
					this.editorial=rs.getString("editorial");
					this.date_edicion=rs.getString("date");
				}
				this.downloads=getMyDownloads(isbn);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	*/
	/*
	
	public static List<User> getMyDownloads(int isbn){
		List<User> descargas = new ArrayList<>();
		Connection con = Connect.getConnect();
		if (con != null) {
			try {
				PreparedStatement q = con.prepareStatement(GETMYDOWNLOAD);
				q.setInt(1, isbn);
				ResultSet rs = q.executeQuery();
				while (rs.next()) {
					// cada row
					User u = new User();
					u.setId(rs.getInt("id_user"));
					u.setDni(rs.getString("dni"));
					u.setName(rs.getString("name"));
					u.setAddress(rs.getString("address"));
					
					descargas.add(u);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return descargas;
	}
	*/
	public ObservableList<BookDAO> getBooks(){
		
		ObservableList<BookDAO> obs = FXCollections.observableArrayList();
		Connection con = Connect.getConnect();
		if (con!=null) {
			try {
				Statement st = con.createStatement();
				String q = BOOKS;
				ResultSet rs = st.executeQuery(q);
				while(rs.next()) {
					this.isbn=rs.getInt("isbn");
					this.title=rs.getString("title");
					this.author=rs.getString("author");
					this.editorial=rs.getString("editorial");
					
					BookDAO b = new BookDAO (isbn,title,author,editorial);
					obs.add(b);
					
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return obs;
	}
	
}
