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
public class UserDAO extends User {

	private final static String GETBYID = "SELECT id,dni,name,address FROM user WHERE id=";
	private final static String INSERT = "INSERT INTO user (dni,name,address) " + "VALUES (?,?,?)";
	private final static String UPDATE = "UPDATE user SET name=?, address=? WHERE dni=?";
	private final static String DELETE = "DELETE FROM user WHERE id=?";
	private final static String GETBYUSER = "SELECT * FROM user, download, book WHERE "
			+ "user.id=download.id_user AND book.isbn=download.isbn_book AND user.id=?";
	private final static String USERS = "SELECT * FROM user";
	private final static String GETDOWNLOAD = "INSERT INTO download (id_user,isbn_book) VALUES (?,?)";
	private final static String  INSERTINTODOWNLOAD= "INSERT INTO download (id_user,isbn_book) VALUES (?,?)";
	

	public UserDAO(int id, String dni, String name, String address) {
		super(id, dni, name, address);
	}

	public UserDAO(int id, String dni, String name, String address, List<Book> mydownload) {
		super(id, dni, name, address, mydownload);
	}

	public UserDAO(String dni, String name, String address) {
		super(dni, name, address);
	}

	public UserDAO() {
		super();
	}

	public UserDAO(User u) {
		this.id = u.id;
		this.dni = u.dni;
		this.name = u.name;
		this.address = u.address;
		this.mydownload = u.mydownload;
	}

	public UserDAO(int id) {
		Connection con = Connect.getConnect();
		if (con != null) {
			try {
				Statement st = con.createStatement();
				String q = GETBYID + id;
				ResultSet rs = st.executeQuery(q);
				while (rs.next()) {
					this.id = rs.getInt("id");
					this.dni = rs.getString("dni");
					this.name = rs.getString("name");
					this.address = rs.getString("address");
				}
				this.mydownload = getDownloadByID(this.id);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	/**
	 * Inserta en la base de datos un nuevo usuario
	 * 
	 * @return
	 */
	public int save() {
		int rs = 0;
		Connection con = Connect.getConnect();
		if (con != null) {
			try {
				PreparedStatement q = con.prepareStatement(INSERT);
				q.setString(1, this.dni);
				q.setString(2, this.name);
				q.setString(3, this.address);
				rs = q.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return rs;
	}
	public int saveInDownload(Book b) {
		int rs = 0;
		Connection con = Connect.getConnect();
		if (con != null) {
			try {
				PreparedStatement q = con.prepareStatement(INSERTINTODOWNLOAD);
				q.setString(1, this.dni);
				q.setInt(2, b.getIsbn());
				rs = q.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return rs;
	}

	/**
	 * Actualiza en la BD los datos de un usuario
	 * 
	 * @return
	 */
	public int update() {
		int rs = 0;
		Connection con = Connect.getConnect();
		if (con != null) {
			try {
				PreparedStatement q = con.prepareStatement(UPDATE);
				q.setString(1, this.name);
				q.setString(2, this.address);
				q.setString(3, this.dni);
				rs = q.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return rs;
	}

	/**
	 * Obtiene las descargas de un usuario
	 * 
	 * @param id
	 * @return lista de descargas
	 */
	public static ObservableList<Book> getDownloadByID(int id) {
		ObservableList<Book> descargas = FXCollections.observableArrayList();
		Connection con = Connect.getConnect();
		if (con != null) {
			try {
				PreparedStatement q = con.prepareStatement(GETBYUSER);
				q.setInt(1, id);
				ResultSet rs = q.executeQuery();
				while (rs.next()) {
					// cada row
					User u = new User();
					u.setId(rs.getInt("id"));
					u.setDni(rs.getString("dni"));
					u.setName(rs.getString("name"));
					u.setAddress(rs.getString("address"));

					Book b = new Book();
					b.setIsbn(rs.getInt("isbn"));
					b.setAuthor(rs.getString("author"));
					b.setTitle(rs.getString("title"));
					b.setEditorial(rs.getString("editorial"));

					descargas.add(b);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return descargas;
	}

	/**
	 * Elimina un usuario
	 * 
	 * @return
	 */
	public int delete() {
		int rs = 0;
		Connection con = Connect.getConnect();
		if (con != null) {
			try {
				PreparedStatement q = con.prepareStatement(DELETE);
				q.setInt(1, this.id);
				rs = q.executeUpdate();
				this.id = -1;
				this.dni = "";
				this.name = "";
				this.address = "";
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return rs;
	}

	/**
	 * Obtiene una descarga
	 * 
	 * @return
	 */
	public int getDownload(int id, int isbn) {
		int rs = 0;
		Connection con = Connect.getConnect();
		if (con != null) {
			try {
				PreparedStatement q = con.prepareStatement(GETDOWNLOAD);
				
				q.setInt(1, id);
				q.setInt(2, isbn);
				rs = q.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return rs;
	}

	/**
	 * Obtiene todos los usuarios guardados en la BD
	 * 
	 * @return lista de usuarios
	 */
	public ObservableList<UserDAO> getUsers() {

		ObservableList<UserDAO> obs = FXCollections.observableArrayList();
		Connection con = Connect.getConnect();
		if (con != null) {
			try {
				Statement st = con.createStatement();
				String q = USERS;
				ResultSet rs = st.executeQuery(q);
				while (rs.next()) {
					this.id = rs.getInt("id");
					this.dni = rs.getString("dni");
					this.name = rs.getString("name");
					this.address = rs.getString("address");
					this.mydownload = getDownloadByID(this.id);

					UserDAO u = new UserDAO(id, dni, name, address, mydownload);
					obs.add(u);

				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return obs;
	}

}
