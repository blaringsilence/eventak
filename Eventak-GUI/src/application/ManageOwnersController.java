package application;

import java.io.IOException;
import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ManageOwnersController {
	private Party party;
	private Concert concert;
	private boolean isOwners;
	private boolean isParty = false;
	@FXML
	private ListView<Guest> ownerList;
	@FXML
	private Button addOwner;
	@FXML
	private Button editOwner;
	@FXML
	private Button deleteOwner;
	@FXML
	private Label titleText;
	
	public void initData(Party party, boolean owners){
		this.party = party;
		this.isParty = true;
		this.isOwners = owners;
		ObservableList<Guest> oList;
		if(this.isOwners){
			oList = FXCollections.observableArrayList(party.getHighlighted());
			titleText.setText("Manage Owner(s)");
		}
		else{
			addOwner.setText("Add Guest");
			titleText.setText("Manage Guests");
			oList = FXCollections.observableArrayList(party.getGuestList());
		}
		
		ownerList.setItems(oList);
	}
	
	public void initData(Concert con){
		this.concert = con;
		this.isOwners = false;
		ObservableList<Guest> oList;
		addOwner.setText("Add Guest");
		titleText.setText("Manage Guests");
		oList = FXCollections.observableArrayList(concert.getGuestList());
		ownerList.setItems(oList);
	}
	
	
	// Event Listener on ListView[#ownerList].onMouseClicked
	@FXML
	public void ownerSelect(MouseEvent event) {
		if(ownerList.getSelectionModel().getSelectedItem()!=null){
			deleteOwner.setDisable(false);
			editOwner.setDisable(false);
		}
	}
	// Event Listener on Button[#addOwner].onAction
	@FXML
	public void addOwner(ActionEvent event) {
		Stage partyStage = new Stage();
		try{
			  FXMLLoader loader = new FXMLLoader(getClass().getResource("AddGuest.fxml"));
			  AnchorPane page = (AnchorPane) loader.load();
			  AddGuestController controller = loader.<AddGuestController>getController();
			  if(this.isParty)
				  controller.initData(party,this.isOwners);
			  else
				  controller.initData(concert);
			  Scene scene = new Scene(page);
			  partyStage.setScene(scene);
			  String extra = this.isOwners ? "Add Owner" : "Add Guest";
			  String n = this.isParty ? party.getName() : concert.getName();
			  partyStage.setTitle(n+" | " + extra);
			  partyStage.show();
		}
		catch (IOException e){
			e.printStackTrace();
		}
	}
	// Event Listener on Button[#editOwner].onAction
	@FXML
	public void editOwner(ActionEvent event) {
		Guest owner = ownerList.getSelectionModel().getSelectedItem();
		Stage partyStage = new Stage();
		try{
			  FXMLLoader loader = new FXMLLoader(getClass().getResource("EditGuest.fxml"));
			  AnchorPane page = (AnchorPane) loader.load();
			  EditGuestController controller = loader.<EditGuestController>getController();
			  controller.initData(owner);
			  Scene scene = new Scene(page);
			  partyStage.setScene(scene);
			  String extra = this.isOwners ? "Edit Owner" : "Edit Guest";
			  String n = this.isParty ? party.getName() : concert.getName();
			  partyStage.setTitle(n+" | "+extra);
			  partyStage.show();
		}
		catch (IOException e){
			e.printStackTrace();
		}
		
	}
	// Event Listener on Button[#deleteOwner].onAction
	@FXML
	public void deleteOwner(ActionEvent event) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Delete");
		alert.setHeaderText(ownerList.getSelectionModel().getSelectedItem().toString());
		alert.setContentText("Are you sure you want to delete this?");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
			if(this.isOwners)
				party.getHighlighted().remove(ownerList.getSelectionModel().getSelectedItem());
			else if(this.isParty)
				party.getGuestList().remove(ownerList.getSelectionModel().getSelectedItem());
			else
				concert.getGuestList().remove(ownerList.getSelectionModel().getSelectedItem());
			Main.save();
		} else {
		    // ... user chose CANCEL or closed the dialog
		}
	}
}
