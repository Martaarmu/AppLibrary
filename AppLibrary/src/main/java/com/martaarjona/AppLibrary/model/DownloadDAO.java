package com.martaarjona.AppLibrary.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.martaarjona.AppLibrary.utils.Connect;

public class DownloadDAO extends Download{
	private final static String GETDOWNLOAD="INSERT INTO download (id_user,isbn_book,date) VALUES (?,?,?)";
			
	
	public DownloadDAO(int id_user,int isbn_book, String date) {
		super(id_user,isbn_book,date);
	}
	
	public int getDownload() {
		int rs=0;
		Connection con = Connect.getConnect();
			if (con!=null) {
				try {
					PreparedStatement q = con.prepareStatement(GETDOWNLOAD);
					q.setInt(1,this.id_user);
					q.setInt(2,this.isbn_book);
					q.setString(3,this.date);
					rs = q.executeUpdate();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		return rs;
	}
	
	
}
