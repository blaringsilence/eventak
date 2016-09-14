package application;

import javafx.collections.ObservableList;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ShowPartyController{
	private Party party;
	private Concert concert;
	private boolean isParty = false;
	@FXML
	private TextField partyTitle;
	@FXML
	private ChoiceBox<String> partyType;
	@FXML
	private Button saveParty;
	@FXML
	private Button manageOwners;
	@FXML
	private Button manageSponsers;
	@FXML
	private Button manageGuests;
	@FXML
	private Button manageEntertainment;
	@FXML
	private DatePicker partyDate;
	@FXML
	private Label typeLabel;
	
	
	public void initData(Party party){
		this.party = party;
		this.isParty = true;
		partyTitle.setText(party.getName());
		partyType.setValue(party.getType());
		ObservableList<String> partyTypes = FXCollections.observableArrayList("Wedding", "Engagement", "Birthday", "Other");
		partyType.setItems(partyTypes);
		partyDate.setValue(party.getDate());
	}
	
	public void initData(Concert concert){
		this.concert = concert;
		partyTitle.setText(concert.getName());
		typeLabel.setVisible(false);
		partyType.setVisible(false);
		partyDate.setValue(concert.getDate());
		manageOwners.setDisable(true);
	}
	
	public void validateForm(ActionEvent event){
		if(this.allFieldsPresent()){
			saveParty.setDisable(false);
		}
		else
			saveParty.setDisable(true);
	}
	
	
	// Event Listener on TextField[#partyTitle].onKeyTyped
	@FXML
	public void validateKeys(KeyEvent event) {
		if(this.allFieldsPresent()){
			saveParty.setDisable(false);
		}
		else
			saveParty.setDisable(true);
	}
// Event Listener on Button[#saveParty].onAction
	@FXML
	public void saveParty(ActionEvent event) {
		if(this.allFieldsPresent()){
			if(this.isParty){
				party.setName(partyTitle.getText());
				party.setType(partyType.getValue());
				party.setDate(partyDate.getValue());
			}
			else{
				concert.setName(partyTitle.getText());
				concert.setDate(partyDate.getValue());
			}
			Main.save();
			 // get a handle to the stage
		    Stage stage = (Stage) saveParty.getScene().getWindow();
		    // do what you have to do
		    stage.close();
		}
	}
	// Event Listener on Button[#manageOwners].onAction
	@FXML
	public void manageOwners(ActionEvent event) {
		Stage partyStage = new Stage();
		try{
			  FXMLLoader loader = new FXMLLoader(getClass().getResource("ManageOwners.fxml"));
			  AnchorPane page = (AnchorPane) loader.load();
			  ManageOwnersController controller = loader.<ManageOwnersController>getController();
			  controller.initData(party,true);
			  Scene scene = new Scene(page);
			  partyStage.setScene(scene);
			  String n = this.isParty ? party.getName() : concert.getName();
			  partyStage.setTitle(n+" | Manage Owner(s)");
			  partyStage.show();
		}
		catch (IOException e){
			e.printStackTrace();
		}
	}
	// Event Listener on Button[#manageSponsers].onAction
	@FXML
	public void manageSponsors(ActionEvent event) {
		Event ev = this.isParty ? (Event) party :  (Event) concert;
		Stage partyStage = new Stage();
		try{
			  FXMLLoader loader = new FXMLLoader(getClass().getResource("ManageSponsors.fxml"));
			  AnchorPane page = (AnchorPane) loader.load();
			  ManageSponsorsController controller = loader.<ManageSponsorsController>getController();
			  controller.initData(ev);
			  Scene scene = new Scene(page);
			  partyStage.setScene(scene);
			  String n = this.isParty ? party.getName() : concert.getName();
			  partyStage.setTitle(n+" | Manage Sponsor(s)");
			  partyStage.show();
		}
		catch (IOException e){
			e.printStackTrace();
		}
	}
	// Event Listener on Button[#manageGuests].onAction
	@FXML
	public void manageGuests(ActionEvent event) {
		Stage partyStage = new Stage();
		try{
			  FXMLLoader loader = new FXMLLoader(getClass().getResource("ManageOwners.fxml"));
			  AnchorPane page = (AnchorPane) loader.load();
			  ManageOwnersController controller = loader.<ManageOwnersController>getController();
			  if(this.isParty)
				  controller.initData(party,false);
			  else
				  controller.initData(concert);
			  Scene scene = new Scene(page);
			  partyStage.setScene(scene);
			  String n = this.isParty ? party.getName() : concert.getName();
			  partyStage.setTitle(n+" | Manage Guests");
			  partyStage.show();
		}
		catch (IOException e){
			e.printStackTrace();
		}
	}
	// Event Listener on Button[#manageEntertainment].onAction
	@FXML
	public void manageEntertainment(ActionEvent event) {
		Stage partyStage = new Stage();
		try{
			  FXMLLoader loader = new FXMLLoader(getClass().getResource("ManageEntertainment.fxml"));
			  AnchorPane page = (AnchorPane) loader.load();
			  ManageEntertainmentController controller = loader.<ManageEntertainmentController>getController();
			  if(this.isParty)
				  controller.initData(party);
			  else
				  controller.initData(concert);
			  Scene scene = new Scene(page);
			  partyStage.setScene(scene);
			  String n = this.isParty ? party.getName() : concert.getName();
			  partyStage.setTitle(n+" | Manage Entertainment");
			  partyStage.show();
		}
		catch (IOException e){
			e.printStackTrace();
		}
	}

	public boolean allFieldsPresent(){
			if(this.isParty && partyDate.getValue()!=null && partyTitle.getText().length()>0 && partyType.getValue()!=null){
				return true;
			}
			else if(!this.isParty && partyDate.getValue()!=null && partyTitle.getText().length()>0)
				return true;
			return false;
		
	}
	
}
