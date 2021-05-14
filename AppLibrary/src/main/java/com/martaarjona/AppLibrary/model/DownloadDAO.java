package com.martaarjona.AppLibrary.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.martaarjona.AppLibrary.utils.Connect;

/**
 * 
 * @author marta
 *
 */
public class DownloadDAO extends Download {

	private final static String GETDOWNLOAD = "INSERT INTO download (id_user,isbn_book) VALUES (?,?)";

	public DownloadDAO(int id_user, int isbn_book) {
		super(id_user, isbn_book);
	}

	/**
	 * Metodo para insertar en la BD una download
	 * 
	 * @return
	 */
	public int getDownload() {
		int rs = 0;
		Connection con = Connect.getConnect();
		if (con != null) {
			try {
				PreparedStatement q = con.prepareStatement(GETDOWNLOAD);
				q.setInt(1, this.id_user);
				q.setInt(2, this.isbn_book);
				rs = q.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return rs;
	}

}
