package application;

import javafx.fxml.FXML;

import javafx.scene.control.Button;

import javafx.scene.control.TextField;

import javafx.event.ActionEvent;

import javafx.scene.control.TextArea;

import javafx.scene.control.ToggleButton;

import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class AddGuestController {
	private Party party;
	private Concert concert;
	private boolean isParty = false;
	private boolean isHighlighted;
	@FXML
	private TextField guestName;
	@FXML
	private TextField guestPhone;
	@FXML
	private TextField guestEmail;
	@FXML
	private TextArea guestAddress;
	@FXML
	private TextArea guestComments;
	@FXML
	private ToggleButton guestAttending;
	@FXML
	private Button saveGuest;

	public void initData(Party party, boolean highlight){
		this.party = party;
		this.isHighlighted = highlight;
		this.isParty = true;
	}
	public void initData(Concert concert){
		this.isHighlighted =false;
		this.concert = concert;
	}
	// Event Listener on TextArea[#guestComments].onKeyTyped
	@FXML
	public void validateKeys(KeyEvent event) {
		if(this.allFieldsPresent()){
			saveGuest.setDisable(false);
		}
		else
			saveGuest.setDisable(true);
	}

	// Event Listener on Button[#saveGuest].onAction
	@FXML
	public void saveGuest(ActionEvent event) {
		Guest temp = new Guest(guestName.getText());
		if(this.isHighlighted)
			party.addHighlight(temp);
		else if(this.isParty)
			party.addGuest(temp);
		else
			concert.addGuest(temp);
		if(guestPhone.getText().length()>0)
			temp.setPhone(guestPhone.getText());
		if(guestEmail.getText().length()>0)
			temp.setEmail(guestEmail.getText());
		if(guestComments.getText().length()>0)
			temp.addComment(guestComments.getText());
		if(guestAddress.getText().length()>0)
			temp.setAddress(guestAddress.getText());
		if(guestAttending.selectedProperty().getValue()!=temp.getRSVP()){
			temp.RSVP();
		}
		Main.save();
		 // get a handle to the stage
	    Stage stage = (Stage)saveGuest.getScene().getWindow();
	    // do what you have to do
	    stage.close();
	}
	public boolean allFieldsPresent(){
		if(guestName.getText().length()>0){ // not all parties require sent invitations, just a guest list
			return true;
		}
		return false;
	}
}
