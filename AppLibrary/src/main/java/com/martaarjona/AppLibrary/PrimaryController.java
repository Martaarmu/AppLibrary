package com.martaarjona.AppLibrary;

import java.io.IOException;
import java.net.URL;
import java.sql.Time;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import com.martaarjona.AppLibrary.model.Book;
import com.martaarjona.AppLibrary.model.BookDAO;
import com.martaarjona.AppLibrary.model.Download;
import com.martaarjona.AppLibrary.model.DownloadDAO;
import com.martaarjona.AppLibrary.model.User;
import com.martaarjona.AppLibrary.model.UserDAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * 
 * @author marta
 *
 */
public class PrimaryController implements Initializable {

	@FXML
	private Button btnAdd;
	@FXML
	private Button btnModificar;
	@FXML
	private Button btnBorrar;
	@FXML
	private Button btnDownload;
	@FXML
	private TextField txtFiltrarNombre;
	@FXML
	private TableView<UserDAO> tblUser;
	@FXML
	private TableView<Download> tblDownload;
	@FXML
	private TableColumn<UserDAO, String> colName;
	@FXML
	private TableColumn<UserDAO, String> colDni;
	@FXML
	private TableColumn<UserDAO, String> colAddress;
	@FXML
	private TableColumn<UserDAO, Integer> colId;
	@FXML
	private TableColumn<Download, Integer> colDownload;
	@FXML
	private TableColumn<Download, String> colDate;

	private ObservableList<UserDAO> users;
	private ObservableList<UserDAO> filtrousers;
	private ObservableList<Download> books;

	/**
	 * Inicializa la escena
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {

		users = FXCollections.observableArrayList();
		filtrousers = FXCollections.observableArrayList();
		this.tblUser.setItems(users);

		this.colName.setCellValueFactory(new PropertyValueFactory("name"));
		this.colDni.setCellValueFactory(new PropertyValueFactory("dni"));
		this.colAddress.setCellValueFactory(new PropertyValueFactory("address"));
		this.colId.setCellValueFactory(new PropertyValueFactory("id"));

		UserDAO u = new UserDAO();
		ObservableList<UserDAO> items = u.getUsers();
		this.tblUser.setItems(items);
	}
	
	/**
	 * Método que abre otra ventana para insertar
	 * un nuevo usuario
	 * @param event
	 */
	@FXML
	private void addUser(ActionEvent event) {

		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("secondary.fxml"));
			Parent root = loader.load();
			SecondaryController controlador = loader.getController();
			controlador.iniAttributtes(users);

			Scene scene = new Scene(root);
			Stage stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setScene(scene);
			stage.showAndWait();

			UserDAO u = controlador.getUser();
			
			if (u != null) {
				this.users.add(u);
				ObservableList<UserDAO> items = u.getUsers();
				this.tblUser.setItems(items);
				this.tblUser.refresh();
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Método que abre otra ventana para iniciar
	 * la descarga de un libro
	 * @param event
	 */
	@FXML
	private void addDownload(ActionEvent event) {

		try {
			UserDAO u = this.tblUser.getSelectionModel().getSelectedItem();

			FXMLLoader loader = new FXMLLoader(getClass().getResource("download.fxml"));
			Parent root = loader.load();
			DownloadController controlador = loader.getController();
			controlador.iniAttributtes(users, u);

			Scene scene = new Scene(root);
			Stage stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setScene(scene);
			stage.showAndWait();

			Download d = controlador.getDownload();
			if (d != null) {
				this.tblDownload.refresh();
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Método que nos permite seleccionar un item
	 * @param event
	 */
	@FXML
	private void seleccionar(MouseEvent event) {

		UserDAO u = this.tblUser.getSelectionModel().getSelectedItem();
		books = FXCollections.observableArrayList();
		this.tblDownload.setItems(u.getDownloadByID(u.getId()));
		this.colDownload.setCellValueFactory(new PropertyValueFactory("isbn_book"));

		ObservableList<Download> items = u.getDownloadByID(u.getId());
		this.tblDownload.setItems(items);

	}
	
	/**
	 * Abre otra ventana para proceder a la modificación de datos
	 * @param event
	 */
	@FXML
	private void modificar(ActionEvent event) {

		User u = this.tblUser.getSelectionModel().getSelectedItem();

		if (u == null) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("Error");
			alert.setContentText("Debe seleccionar un usuario");
			alert.showAndWait();
		} else {
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("secondary.fxml"));
				Parent root = loader.load();
				SecondaryController controlador = loader.getController();
				controlador.iniAttributtes(users, (UserDAO) u);

				Scene scene = new Scene(root);
				Stage stage = new Stage();
				stage.initModality(Modality.APPLICATION_MODAL);
				stage.setScene(scene);
				stage.showAndWait();

				User aux = controlador.getUser();
				if (aux != null) {
					this.tblUser.refresh();
				}

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	/**
	 * Borra el usuario seleccionado
	 * @param event
	 */

	@FXML
	private void delete(ActionEvent event) {
		UserDAO u = this.tblUser.getSelectionModel().getSelectedItem();

		if (u == null) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("Error");
			alert.setContentText("Debe seleccionar un usuario");
			alert.showAndWait();
		} else {
			this.users.remove(u);
			u.delete();
			this.tblUser.refresh();

			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setHeaderText(null);
			alert.setTitle("Info");
			alert.setContentText("Usuario eliminado");
			alert.showAndWait();
		}
	}
	
	/**
	 * Filtra los usuarios por nombre
	 * @param event
	 */
	@FXML
	private void filtrarNombre(KeyEvent event) {
		String filtroName = this.txtFiltrarNombre.getText();
		UserDAO u = new UserDAO();
		ObservableList<UserDAO> items = u.getUsers();
		this.tblUser.setItems(items);
		if (filtroName.isEmpty()) {
			this.tblUser.setItems(items);
		} else {
			this.filtrousers.clear();
			for (UserDAO aux : items) {
				if (aux.getName().toLowerCase().contains(filtroName.toLowerCase())) {
					this.filtrousers.add(aux);
				}
			}
			this.tblUser.setItems(filtrousers);
		}
	}

}
