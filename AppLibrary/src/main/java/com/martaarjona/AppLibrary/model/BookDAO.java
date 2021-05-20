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

/**
 * 
 * @author marta
 *
 */
public class BookDAO extends Book {

	public final static String BOOKS = "SELECT * FROM book";

	public BookDAO(int isbn, String title, String author, String editorial) {
		super(isbn, title, author, editorial);
	}

	public BookDAO() {
		super();
	}

	/**
	 * Obtiene todos los libros que hay en la base de datos
	 * @return lista de BookDAO
	 */
	public ObservableList<BookDAO> getBooks() {

		ObservableList<BookDAO> obs = FXCollections.observableArrayList();
		Connection con = Connect.getConnect();
		if (con != null) {
			try {
				Statement st = con.createStatement();
				String q = BOOKS;
				ResultSet rs = st.executeQuery(q);
				while (rs.next()) {
					this.isbn = rs.getInt("isbn");
					this.title = rs.getString("title");
					this.author = rs.getString("author");
					this.editorial = rs.getString("editorial");

					BookDAO b = new BookDAO(isbn, title, author, editorial);
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
