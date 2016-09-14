package application;

import java.io.IOException;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

public class MainController {
	@FXML
	private Button addParty;
	@FXML
	private Button addContact;
	@FXML
	private Button addConcert;
	@FXML
	private Button editParty;
	@FXML
	private Button editConcert;
	@FXML
	private Button editContact;



	// Event Listener on Button[#addParty].onAction
	@FXML
	public void openAddParty(ActionEvent event) {
		this.openWindow("AddParty", "Create Party");
	}
	// Event Listener on Button[#addContact].onAction
	@FXML
	public void openAddContact(ActionEvent event) {
		this.openWindow("AddContact", "Add Contact");
	}
	// Event Listener on Button[#addConcert].onAction
	@FXML
	public void openAddConcert(ActionEvent event) {
		this.openWindow("AddConcert", "Create Concert");
	}
	// Event Listener on Button[#editParty].onAction
	@FXML
	public void openEditParty(ActionEvent event) {
		this.openWindow("ManageParty", "Manage Party");
	}
	// Event Listener on Button[#editConcert].onAction
	@FXML
	public void openEditConcert(ActionEvent event) {
		this.openWindow("ManageConcert", "Manage Concerts");
	}
	// Event Listener on Button[#editContact].onAction
	@FXML
	public void openEditContact(ActionEvent event) {
		this.openWindow("ManageContact", "Manage Contacts");
	}
	
	public void openWindow(String name, String title){
		try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(name+".fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle(title);
            stage.setScene(new Scene(root1));  
            stage.show();
          } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
