package application;

import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.time.LocalDate;

import javafx.event.ActionEvent;

import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class AddEntertainmentController {
	private Party party;
	private Concert concert;
	private boolean isAdd;
	private boolean isParty = false;
	private Entertainment ent;
	@FXML
	private TextField entertainmentName;
	@FXML
	private TextField entertainmentContact;
	@FXML
	private TextField entertainmentPhone;
	@FXML
	private TextField entertainmentEmail;
	@FXML
	private Button saveEntertainment;
	@FXML
	private Label titleText;

	public void initData(Party party){
		this.party = party;
		this.isAdd = true;
		this.isParty = true;
		titleText.setText("Add Entertainment");
	}
	public void initData(Concert concert){
		this.concert = concert;
		this.isAdd = true;
		titleText.setText("Add Entertainment");

	}
	public void initData(Party party, Entertainment ent){
		this.party = party;
		this.ent = ent;
		this.isParty = true;
		this.isAdd = false;
		titleText.setText("Edit Entertainment");
		entertainmentName.setText(ent.getName());
		entertainmentContact.setText(ent.getContact().getName());
		entertainmentPhone.setText(ent.getContact().getPhone());
		entertainmentEmail.setText(ent.getContact().getEmail());
	}
	public void initData(Concert concert, Entertainment ent){
		this.concert = concert;
		this.ent = ent;
		this.isAdd = false;
		titleText.setText("Edit Entertainment");
		entertainmentName.setText(ent.getName());
		if(ent.getContact()!=null){
			entertainmentContact.setText(ent.getContact().getName());
			entertainmentPhone.setText(ent.getContact().getPhone());
			entertainmentEmail.setText(ent.getContact().getEmail());
		}
	}
	// Event Listener on TextField[#entertainmentEmail].onKeyTyped
	@FXML
	public void validateKeys(KeyEvent event) {
		if(this.allFieldsSet()){
			saveEntertainment.setDisable(false);
		}
		else
			saveEntertainment.setDisable(true);
	}
	// Event Listener on Button[#saveEntertainment].onAction
	@FXML
	public void saveEntertainment(ActionEvent event) {
		if(this.allFieldsSet()){
			Contact c = new Contact(entertainmentContact.getText());
			if(entertainmentPhone.getText().length()>0)
				c.setPhone(entertainmentPhone.getText());
			if(entertainmentEmail.getText().length()>0)
				c.setEmail(entertainmentEmail.getText());
			if(this.isAdd){
				LocalDate d = this.isParty ? party.getDate() : concert.getDate();
				Entertainment en = new Entertainment(entertainmentName.getText(), d);
				en.setContact(c);
				if(this.isParty)
					party.addEntertainment(en);
				else
					concert.addEntertainment(en);
			}
			else{
				this.ent.setName(entertainmentName.getText());
				this.ent.setContact(c);
			}
			Main.save();
			// get a handle to the stage
		    Stage stage = (Stage) saveEntertainment.getScene().getWindow();
		    // do what you have to do
		    stage.close();
		}
	}
	public boolean allFieldsSet(){
		if(entertainmentName.getText().length()>0 && entertainmentContact.getText().length()>0){
			return true;
		}
		return false;
	}
}
