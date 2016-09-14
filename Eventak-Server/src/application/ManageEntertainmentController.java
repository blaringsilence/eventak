package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

import java.io.IOException;
import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.scene.control.Label;

import javafx.scene.control.ListView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ManageEntertainmentController {
	private Party party;
	private Concert concert;
	private boolean isParty = false;
	@FXML
	private Label titleText;
	@FXML
	private ListView<Entertainment> entertainmentList;
	@FXML
	private Button addEntertainment;
	@FXML
	private Button editEntertainment;
	@FXML
	private Button deleteEntertainment;

	public void initData(Party ev){
		this.party = ev;
		this.isParty = true;
		ObservableList<Entertainment> eList = FXCollections.observableArrayList(party.getEntertainment());
		entertainmentList.setItems(eList);
	}
	public void initData(Concert con){
		this.concert = con;
		ObservableList<Entertainment> eList = FXCollections.observableArrayList(con.getEntertainment());
		entertainmentList.setItems(eList);
	}
	// Event Listener on ListView[#ownerList].onMouseClicked
	@FXML
	public void entertainmentSelect(MouseEvent event) {
		if(entertainmentList.getSelectionModel().getSelectedItem()!=null){
			editEntertainment.setDisable(false);
			deleteEntertainment.setDisable(false);
		}
		else{
			editEntertainment.setDisable(true);
			deleteEntertainment.setDisable(true);
		}
		
	}
	// Event Listener on Button[#addEntertainment].onAction
	@FXML
	public void addEntertainment(ActionEvent event) {
		Stage partyStage = new Stage();
		try{
			  FXMLLoader loader = new FXMLLoader(getClass().getResource("AddEntertainment.fxml"));
			  AnchorPane page = (AnchorPane) loader.load();
			  AddEntertainmentController controller = loader.<AddEntertainmentController>getController();
			  if(this.isParty)
				  controller.initData(party);
			  else
				  controller.initData(concert);
			  Scene scene = new Scene(page);
			  partyStage.setScene(scene);
			  String n = this.isParty ? party.getName() : concert.getName();
			  partyStage.setTitle(n+" | Add Entertainment");
			  partyStage.show();
		}
		catch (IOException e){
			e.printStackTrace();
		}
	}
	// Event Listener on Button[#editEntertainment].onAction
	@FXML
	public void editEntertainment(ActionEvent event) {
		Entertainment en = entertainmentList.getSelectionModel().getSelectedItem();
		Stage partyStage = new Stage();
		try{
			  FXMLLoader loader = new FXMLLoader(getClass().getResource("AddEntertainment.fxml"));
			  AnchorPane page = (AnchorPane) loader.load();
			  AddEntertainmentController controller = loader.<AddEntertainmentController>getController();
			  if(this.isParty)
				  controller.initData(party,en);
			  else
				  controller.initData(concert,en);
			  Scene scene = new Scene(page);
			  partyStage.setScene(scene);
			  String n = this.isParty ? party.getName() : concert.getName();
			  partyStage.setTitle(n+" | Edit Entertainment");
			  partyStage.show();
		}
		catch (IOException e){
			e.printStackTrace();
		}
		
	}
	// Event Listener on Button[#deleteEntertainment].onAction
	@FXML
	public void deleteEntertainment(ActionEvent event) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Delete");
		alert.setHeaderText(entertainmentList.getSelectionModel().getSelectedItem().toString());
		alert.setContentText("Are you sure you want to delete this?");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
			if(this.isParty)
				party.getEntertainment().remove(entertainmentList.getSelectionModel().getSelectedItem());
			else
				concert.getEntertainment().remove(entertainmentList.getSelectionModel().getSelectedItem());
			Main.save();
		} else {
		    // ... user chose CANCEL or closed the dialog
		}
	}
}
