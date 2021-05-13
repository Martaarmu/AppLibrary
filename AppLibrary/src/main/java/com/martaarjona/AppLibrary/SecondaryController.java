package com.martaarjona.AppLibrary;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.martaarjona.AppLibrary.model.User;
import com.martaarjona.AppLibrary.model.UserDAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class SecondaryController {
	
	@FXML
	private Button btnSave;
	@FXML
	private Button btnExit;
	@FXML
	private TextField txtDni;
	@FXML
	private TextField txtName;
	@FXML
	private TextField txtAddress;
	
	private UserDAO user;
	private ObservableList<UserDAO> users;
	
	public void iniAttributtes(ObservableList<UserDAO> users) {
		this.users=users;
	}
	
	public void iniAttributtes(ObservableList<UserDAO> users, UserDAO u) {
		this.users=users;
		this.user=u;
		this.txtDni.setText(u.getDni());
		this.txtName.setText(u.getName());
		this.txtAddress.setText(u.getAddress());
	}
	
	@FXML
	private void save (ActionEvent event) {
		
		String dni = this.txtDni.getText();
		String name = this.txtName.getText();
		String address = this.txtAddress.getText();
		
		UserDAO u = new UserDAO(dni,name,address);
		
		if(!users.contains(u)) {
			
			//Modificando
			if(this.user!=null) {
				this.user.setDni(dni);
				this.user.setName(name);
				this.user.setAddress(address);
				u.update();
				
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setHeaderText(null);
				alert.setTitle("Info");
				alert.setContentText("Se ha modificado conrrectamente");
				alert.showAndWait();
			}else {
				//Insertando
				this.user = u;
				users.add(u);
				u.save();
				
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setHeaderText(null);
				alert.setTitle("Info");
				alert.setContentText("Se ha añadido conrrectamente");
				alert.showAndWait();
			}
			
			Stage stage = (Stage) this.btnSave.getScene().getWindow();
			stage.close();
			
		}else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("Error");
			alert.setContentText("El usuario ya existe");
			alert.showAndWait();
		}
	}
	
	@FXML
	private void exit (ActionEvent event) {
		this.user=null;
		Stage stage = (Stage) this.btnSave.getScene().getWindow();
		stage.close();
	}
	
	public UserDAO getUser() {
		return user;
	}
	
}