package application;

import javafx.fxml.FXML;

import javafx.scene.control.TextField;

import javafx.event.ActionEvent;

import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;

public class AddConcertController {
	@FXML
	private TextField concertTitle;
	@FXML
	private DatePicker concertDate;
	@FXML
	private TextField bandName;
	@FXML
	private Button createConcert;

	// Event Listener on TextField[#concertTitle].onKeyTyped
	@FXML
	public void validateKeys(KeyEvent event) {
		if(this.allFieldsPresent()){
			createConcert.setDisable(false);
		}
		else
			createConcert.setDisable(true);
	}
	@FXML
	public void createConcert(ActionEvent event){
		if(this.allFieldsPresent()){
			Concert temp = new Concert(concertTitle.getText());
			Main.CONCERT_LIST.add(temp);
			temp.setDate(concertDate.getValue());
			Entertainment e = new Entertainment(bandName.getText());
			temp.addEntertainment(e);
			Main.save();
			 // get a handle to the stage
		    Stage stage = (Stage) createConcert.getScene().getWindow();
		    // do what you have to do
		    stage.close();
		}
	}
	
	// Event Listener on DatePicker[#concertDate].onAction
	@FXML
	public void validateForm(ActionEvent event) {
		if(this.allFieldsPresent()){
			createConcert.setDisable(false);
		}
		else{
			createConcert.setDisable(true);
		}
	}
	
	public boolean allFieldsPresent(){
		if(concertTitle.getText().length()>0 && concertDate.getValue()!=null && bandName.getText().length()>0){
			return true;
		}
		return false;
	}
}
