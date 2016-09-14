package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.scene.control.Label;

import javafx.scene.control.ListView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ManageContactController implements Initializable{
	@FXML
	private Label titleText;
	@FXML
	private ListView<Contact> contactList;
	@FXML
	private Button addContact;
	@FXML
	private Button editContact;
	@FXML
	private Button deleteContact;

	// Event Listener on ListView[#contactList].onMouseClicked
	@FXML
	public void contactSelect(MouseEvent event) {
		if(contactList.getSelectionModel().getSelectedItem()!=null){
			editContact.setDisable(false);
			deleteContact.setDisable(false);
		}
		else{
			editContact.setDisable(true);
			deleteContact.setDisable(true);
		}
	}
	// Event Listener on Button[#addContact].onAction
	@FXML
	public void addContact(ActionEvent event) {
		Stage contactStage = new Stage();
		try{
			  FXMLLoader loader = new FXMLLoader(getClass().getResource("AddContact.fxml"));
			  AnchorPane page = (AnchorPane) loader.load();
			  Scene scene = new Scene(page);
			  contactStage.setScene(scene);
			  contactStage.setTitle("Add Contact");
			  contactStage.show();
		}
		catch (IOException e){
			e.printStackTrace();
		}
	}
	// Event Listener on Button[#editContact].onAction
	@FXML
	public void editContact(ActionEvent event) {
		Contact con = contactList.getSelectionModel().getSelectedItem();
		Stage contactStage = new Stage();
		try{
			  FXMLLoader loader = new FXMLLoader(getClass().getResource("AddContact.fxml"));
			  AnchorPane page = (AnchorPane) loader.load();
			  AddContactController controller = loader.<AddContactController>getController();
			  controller.initData(con);
			  Scene scene = new Scene(page);
			  contactStage.setScene(scene);
			  contactStage.setTitle("Edit Contact");
			  contactStage.show();
		}
		catch (IOException e){
			e.printStackTrace();
		}
	}
	// Event Listener on Button[#deleteContact].onAction
	@FXML
	public void deleteContact(ActionEvent event) {
		Contact c = contactList.getSelectionModel().getSelectedItem();
		if(c!=null){
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Delete");
			alert.setHeaderText(c.toString());
			alert.setContentText("Are you sure you want to delete this contact?");

			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK){
				Main.CONTACT_LIST.remove(c);
				Main.save();
			} else {
			    // ... user chose CANCEL or closed the dialog
			}
		}
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ObservableList<Contact> cList = FXCollections.observableArrayList(Main.CONTACT_LIST);
		contactList.setItems(cList);
		
	}
}
