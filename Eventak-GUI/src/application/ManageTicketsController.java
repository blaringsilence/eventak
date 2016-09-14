package application;

import javafx.fxml.FXML;

import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.scene.control.Label;

import javafx.scene.control.ListView;

import javafx.scene.input.MouseEvent;

import javafx.scene.input.KeyEvent;

public class ManageTicketsController {
	@FXML
	private Label concertName;
	@FXML
	private ListView<String> ticketList;
	@FXML
	private TextField newTierName;
	@FXML
	private TextField newTierPrice;
	@FXML
	private TextField newTierLimit;
	@FXML
	private Button createNewTier;
	private Concert concert;
	
	public void initData(Concert concert){
		this.concert = concert;
		concertName.setText(concert.getName());
		ObservableList<String> tList = FXCollections.observableArrayList(concert.getTierList());
		ticketList.setItems(tList);
	}
	// Event Listener on ListView[#ticketList].onMouseClicked
	// Event Listener on TextField[#newTierName].onKeyTyped
	@FXML
	public void validateKeys(KeyEvent event) {
		if(this.allNewFieldsPresent())
			createNewTier.setDisable(false);
		else
			createNewTier.setDisable(true);
	}
	// Event Listener on Button[#createNewTier].onAction
	@FXML
	public void createNewTier(ActionEvent event) {
		concert.addTicketTier(newTierName.getText(), Integer.parseInt(newTierPrice.getText()), Integer.parseInt(newTierLimit.getText()));
		this.initData(concert);
		Main.save();
	}
	// Event Listener on TextField[#selectedTierLimit].onKeyTyped

	// Event Listener on Button[#updateTier].onAction

	public boolean allNewFieldsPresent(){
		if(newTierName.getText().length()>0 && newTierPrice.getText().length()>0 && newTierLimit.getText().length()>0){
			return true;
		}
		return false;
	}

}
