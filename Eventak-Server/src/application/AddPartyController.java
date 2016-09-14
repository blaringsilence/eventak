package application;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;
import java.time.LocalDate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;

public class AddPartyController implements Initializable{
	@FXML
	private TextField partyTitle;
	@FXML
	private ChoiceBox<String> partyType;
	@FXML
	private Button createParty;
	@FXML
	private Party thisParty;
	@FXML
	private TextField highlightedName;
	@FXML
	private TextField highlightedPhone;
	@FXML
	private TextField highlightedEmail;
	@FXML
	private DatePicker partyDate;
	
	
	ObservableList<String> partyTypes = FXCollections.observableArrayList("Wedding", "Engagement", "Birthday", "Other");

	// Event Listener on Button[#createParty].onAction
	@FXML
	public void createParty(ActionEvent event) {
		if(this.allFieldsPresent()){
			thisParty = new Party(partyTitle.getText(),partyType.getValue());
			Main.PARTY_LIST.add(thisParty);
			LocalDate pDate = partyDate.getValue();
			thisParty.setDate(pDate);
			Guest highlight;
			if(highlightedEmail.getText().length()>0){
				highlight = new Guest(highlightedName.getText(), highlightedPhone.getText(), highlightedEmail.getText());
			}
			else{
				highlight = new Guest(highlightedName.getText(), highlightedPhone.getText());
			}
			thisParty.addHighlight(highlight);
			Main.save();
			 // get a handle to the stage
		    Stage stage = (Stage) createParty.getScene().getWindow();
		    // do what you have to do
		    stage.close();
		}
	}
	
	@FXML
	public void validateForm(ActionEvent event){
		if(this.allFieldsPresent()){
			createParty.setDisable(false);
		}
		else{
			createParty.setDisable(true);
		}
	}
	
	@FXML
	public void validateKeys(KeyEvent event){
		if(this.allFieldsPresent()){
			createParty.setDisable(false);
		}
		else{
			createParty.setDisable(true);
		}
	}
	
	public boolean allFieldsPresent(){
		if(partyTitle.getText().length()>0 && partyType.getValue().length()>0 && partyDate.getValue()!= null && highlightedName.getText().length()>0 && highlightedPhone.getText().length()>0){
			return true;
		}
		return false;
	}
	
	@Override
	public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
		partyType.setValue("Wedding");
		partyType.setItems(partyTypes);
	}
}
