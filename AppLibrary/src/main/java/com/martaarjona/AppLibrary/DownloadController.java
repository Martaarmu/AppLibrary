package com.martaarjona.AppLibrary;

import java.io.Serializable;
import java.net.URL;
import java.util.ResourceBundle;

import com.martaarjona.AppLibrary.model.Book;
import com.martaarjona.AppLibrary.model.BookDAO;
import com.martaarjona.AppLibrary.model.User;
import com.martaarjona.AppLibrary.model.UserDAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.StringConverter;
/**
 * 
 * @author marta
 *
 */
public class DownloadController implements Initializable {

	@FXML
	private ComboBox<BookDAO> cmbBook;
	@FXML
	private Button btnIniDownload;
	@FXML
	private TextArea txtInfo;
	@FXML
	private TextField txtDni;
	@FXML
	private TextField txtName;
	@FXML
	private TextField txtAddress;
	@FXML
	private TextField txtId;

	ObservableList<Book> downloads;
	private ObservableList<UserDAO> users;
	UserDAO user;
	BookDAO book;
	
	/**
	 * Inicializa la scena
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {

		downloads = FXCollections.observableArrayList();

		BookDAO b = new BookDAO();
		ObservableList<BookDAO> items = b.getBooks();
		cmbBook.getItems().addAll(items);
		cmbBook.setConverter(new StringConverter<BookDAO>() {
			@Override
			public String toString(BookDAO object) {
				return object == null ? null : object.getTitle();
			}

			@Override
			public BookDAO fromString(String string) {
				// TODO Auto-generated method stub
				return null;
			}
		});
	}
	
	/**
	 * Inicializa los atributos del usuario seleccionado
	 * @param users
	 * @param u
	 */
	public void iniAttributtes(ObservableList<UserDAO> users, UserDAO u) {
		this.users = users;
		this.user = u;
		this.txtId.setText(u.getId() + "");
		this.txtDni.setText(u.getDni());
		this.txtName.setText(u.getName());
		this.txtAddress.setText(u.getAddress());
	}
	
	/**
	 * Muestra información sobre el item señalado 
	 * en el combobox
	 * @param e
	 */
	@FXML
	private void comboEvent(ActionEvent e) {

		txtInfo.setText(cmbBook.getSelectionModel().getSelectedItem().toString());
	}
	
	/**
	 * Inserta en la tabla download un nuevo registro
	 * @param e
	 */
	@FXML
	private void save(ActionEvent e) {

		int id = Integer.parseInt(this.txtId.getText());
		BookDAO b = cmbBook.getSelectionModel().getSelectedItem();
		b = new BookDAO(b.getIsbn(),b.getTitle(),b.getAuthor(),b.getEditorial());
		//book.getBooks();
		
		downloads.add(b);
		user.getDownload(id,b.getIsbn());

		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setHeaderText(null);
		alert.setTitle("Info");
		alert.setContentText("Se ha descargado conrrectamente");
		alert.showAndWait();

		Stage stage = (Stage) this.btnIniDownload.getScene().getWindow();
		stage.close();
	}
	
	/**
	 * Devuelve un download
	 * @return
	 */
	public Book getDownload() {
		// TODO Auto-generated method stub
		return book;
	}
}
