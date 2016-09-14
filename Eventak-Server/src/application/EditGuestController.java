package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class EditGuestController {
	public Guest guest;
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
	
	public void initData(Guest guest){
		this.guest = guest;
		guestName.setText(guest.getName());
		guestPhone.setText(guest.getPhone());
		guestEmail.setText(guest.getEmail());
		guestComments.setText(guest.getComment());
		guestAddress.setText(guest.getAddress());
		guestAttending.selectedProperty().setValue(guest.getRSVP());
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
	// Event Listener on ToggleButton[#guestAttending].onAction
	@FXML
	public void toggleAttending(ActionEvent event) {
		if(this.allFieldsPresent()){
			saveGuest.setDisable(false);
		}
		else
			saveGuest.setDisable(true);
		
		guest.RSVP();
	}
	// Event Listener on Button[#saveGuest].onAction
	@FXML
	public void saveGuest(ActionEvent event) {
		if(this.allFieldsPresent()){
			guest.setName(guestName.getText());
			if(guestPhone.getText().length()>0)
				guest.setPhone(guestPhone.getText());
			if(guestEmail.getText().length()>0)
				guest.setEmail(guestEmail.getText());
			if(guestComments.getText().length()>0)
				guest.addComment(guestComments.getText());
			if(guestAddress.getText().length()>0)
				guest.setAddress(guestAddress.getText());
			Main.save();
			 // get a handle to the stage
		    Stage stage = (Stage) saveGuest.getScene().getWindow();
		    // do what you have to do
		    stage.close();
		}
	}
	
	public boolean allFieldsPresent(){
		if(guestName.getText().length()>0 && (guestPhone.getText().length()>0 || guestAddress.getText().length()>0)){
			return true;
		}
		return false;
	}
}
