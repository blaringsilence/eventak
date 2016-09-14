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
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ManageSponsorsController {
	private Event ev;
	@FXML
	private ListView<Sponsor> sponsorList;
	@FXML
	private Button addSponsor;
	@FXML
	private Button editSponsor;
	@FXML
	private Button deleteSponsor;
	
	public void initData(Event ev){
		this.ev = ev;
		ObservableList<Sponsor> sList = FXCollections.observableArrayList(ev.getSponsors());
		sponsorList.setItems(sList);
	}
	
	// Event Listener on ListView[#sponsorList].onMouseClicked
	@FXML
	public void sponsorSelect(MouseEvent event) {
		if(sponsorList.getSelectionModel().getSelectedItem()!=null){
			editSponsor.setDisable(false);
			deleteSponsor.setDisable(false);
		}
		else{
			editSponsor.setDisable(true);
			deleteSponsor.setDisable(true);
		}
	}
	// Event Listener on Button[#addSponsor].onAction
	@FXML
	public void addSponsor(ActionEvent event) {
		Stage partyStage = new Stage();
		try{
			  FXMLLoader loader = new FXMLLoader(getClass().getResource("AddSponsor.fxml"));
			  AnchorPane page = (AnchorPane) loader.load();
			  AddSponsorController controller = loader.<AddSponsorController>getController();
			  controller.initData(ev);
			  Scene scene = new Scene(page);
			  partyStage.setScene(scene);
			  partyStage.setTitle(ev.getName()+" | Add Sponsor");
			  partyStage.show();
		}
		catch (IOException e){
			e.printStackTrace();
		}
	}
	// Event Listener on Button[#editSponsor].onAction
	@FXML
	public void editSponsor(ActionEvent event) {
		Stage partyStage = new Stage();
		try{
			  FXMLLoader loader = new FXMLLoader(getClass().getResource("AddSponsor.fxml"));
			  AnchorPane page = (AnchorPane) loader.load();
			  AddSponsorController controller = loader.<AddSponsorController>getController();
			  controller.initData(ev, sponsorList.getSelectionModel().getSelectedItem());
			  Scene scene = new Scene(page);
			  partyStage.setScene(scene);
			  partyStage.setTitle(ev.getName()+" | Edit Sponsor");
			  partyStage.show();
		}
		catch (IOException e){
			e.printStackTrace();
		}
	}
	// Event Listener on Button[#deleteSponsor].onAction
	@FXML
	public void deleteSponsor(ActionEvent event) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Delete");
		alert.setHeaderText(sponsorList.getSelectionModel().getSelectedItem().toString());
		alert.setContentText("Are you sure you want to delete this?");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
				ev.getSponsors().remove(sponsorList.getSelectionModel().getSelectedItem());
				Main.save();
		} else {
		    // ... user chose CANCEL or closed the dialog
		}
	}

}
