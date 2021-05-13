package com.martaarjona.AppLibrary;

import java.io.Serializable;
import java.net.URL;
import java.util.ResourceBundle;

import com.martaarjona.AppLibrary.model.BookDAO;
import com.martaarjona.AppLibrary.model.Download;
import com.martaarjona.AppLibrary.model.DownloadDAO;
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

public class DownloadController implements Initializable {
	
	@FXML
    private ComboBox<BookDAO> cmbBook;
	@FXML
	private Button btnIniDownload;
	@FXML
    private TextArea txtInfo;
	@FXML
	private TextField txtDate;

	@FXML
	private TextField txtDni;

	@FXML
    private TextField txtName;

	@FXML
	private TextField txtAddress;
	@FXML
	private TextField txtId;
	
	ObservableList<Download>downloads;
	private ObservableList<UserDAO> users;
	DownloadDAO download;
	UserDAO user;
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		
		downloads=FXCollections.observableArrayList();
		
		BookDAO b = new BookDAO();
		ObservableList<BookDAO>items=b.getBooks();
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
	
	
	@FXML
	private void comboEvent(ActionEvent e) {
		
		txtInfo.setText(cmbBook.getSelectionModel().getSelectedItem().toString());
		
	}
	
	@FXML
	private void save(ActionEvent e) {
		
		int id = Integer.parseInt(this.txtId.getText());
		String date = this.txtDate.getText();
		BookDAO b = cmbBook.getSelectionModel().getSelectedItem();
		this.download = new DownloadDAO (id,b.getIsbn(),date);
		download.getDownload();
		downloads.add(download);
			
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setHeaderText(null);
		alert.setTitle("Info");
		alert.setContentText("Se ha descargado conrrectamente");
		alert.showAndWait();
		
		Stage stage = (Stage) this.btnIniDownload.getScene().getWindow();
		stage.close();
	}
	

	
	public Download getDownload() {
		// TODO Auto-generated method stub
		return download;
	}

	
	public void iniAttributtes(ObservableList<UserDAO> users, UserDAO u) {
		this.users=users;
		this.user=u;
		this.txtId.setText(u.getId() + "");
		this.txtDni.setText(u.getDni());
		this.txtName.setText(u.getName());
		this.txtAddress.setText(u.getAddress());
	}
	
}
