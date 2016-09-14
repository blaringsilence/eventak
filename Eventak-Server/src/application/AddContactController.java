package application;

import javafx.fxml.FXML;

import javafx.scene.control.Button;

import javafx.scene.control.TextField;

import javafx.event.ActionEvent;

import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class AddContactController {
	@FXML
	private TextField contactName;
	@FXML
	private TextField contactPhone;
	@FXML
	private TextField contactEmail;
	@FXML
	private Button createContact;
	private boolean isAdd = true;
	private Contact contact;
	
	public void initData(Contact c){
		this.contact = c;
		this.isAdd = false;
		contactName.setText(c.getName());
		contactEmail.setText(c.getEmail());
		contactPhone.setText(c.getPhone());
	}

	// Event Listener on TextField[#contactName].onKeyTyped
	@FXML
	public void validateKeys(KeyEvent event) {
		if(this.allFieldsPresent()){
			createContact.setDisable(false);
		}
		else
			createContact.setDisable(true);
	}
	// Event Listener on Button[#createContact].onAction
	@FXML
	public void createContact(ActionEvent event) {
		if(this.allFieldsPresent()){
			if(this.isAdd){
				Contact temp;
				if(contactPhone.getText().length()>0 && contactEmail.getText().length()>0){ // both email & phone
					temp = new Contact(contactName.getText(), contactPhone.getText(), contactEmail.getText());
				}
				else if(contactPhone.getText().length()>0){ // only phone
					temp = new Contact(contactName.getText(), contactPhone.getText());
				}
				else{ // only email
					temp = new Contact(contactName.getText());
					temp.setEmail(contactEmail.getText());
				}
				Main.CONTACT_LIST.add(temp);
				
			}
			else{
				contact.setName(contactName.getText());
				if(contactPhone.getText().length()>0)
					contact.setPhone(contactPhone.getText());
				if(contactEmail.getText().length()>0)
					contact.setEmail(contactEmail.getText());
			}
			Main.save();
			// get a handle to the stage
		    Stage stage = (Stage) createContact.getScene().getWindow();
		    // do what you have to do
		    stage.close();
			
		}
	}
	
	public boolean allFieldsPresent(){
		if(contactName.getText().length()>0 && (contactPhone.getText().length()>0 || contactEmail.getText().length()>0)){
			return true;
		}
		return false;
	}
}
