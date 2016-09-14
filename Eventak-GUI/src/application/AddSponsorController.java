package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class AddSponsorController {
	private Event ev;
	private boolean isAdd;
	private Sponsor sponsor; // if this is edit, this will not be null
	@FXML
	private Label titleText;
	@FXML
	private TextField sponsorName;
	@FXML
	private TextField sponsorPhone;
	@FXML
	private TextField sponsorEmail;
	@FXML
	private TextField sponsorPaymentMethod;
	@FXML
	private TextField sponsorPaymentDetail;
	@FXML
	private Button saveSponsor;
	
	public void initData(Event ev){
		this.ev = ev;
		this.isAdd = true;
		titleText.setText("Add Sponsor");
		
	}
	public void initData(Event ev, Sponsor sp){
		this.ev = ev;
		this.isAdd = false;
		this.sponsor = sp;
		titleText.setText("Edit Sponsor");
		sponsorName.setText(sponsor.getName());
		sponsorEmail.setText(sponsor.getEmail());
		sponsorPhone.setText(sponsor.getPhone());
		sponsorPaymentMethod.setText(sponsor.getPaymentMethod());
		sponsorPaymentDetail.setText(sponsor.getPaymentDetail());
	}
	
	// Event Listener on TextField[#sponsorPaymentDetail].onKeyTyped
		@FXML
		public void validateForm(KeyEvent event) {
			if(this.allFieldsPresent()){
				saveSponsor.setDisable(false);
			}
			else
				saveSponsor.setDisable(true);
		}
		// Event Listener on Button[#saveSponsor].onAction
		@FXML
		public void saveSponsor(ActionEvent event) {
			if(this.allFieldsPresent()){
				if(this.isAdd){
					Sponsor temp = new Sponsor(sponsorName.getText());
					ev.addSponsor(temp);
					if(sponsorEmail.getText().length()>0)
						temp.setEmail(sponsorEmail.getText());
					if(sponsorPhone.getText().length()>0)
						temp.setPhone(sponsorPhone.getText());
					if(sponsorPaymentMethod.getText().length()>0)
						temp.setPaymentMethod(sponsorPaymentMethod.getText());
					if(sponsorPaymentDetail.getText().length()>0)
						temp.setPaymentDetail(sponsorPaymentDetail.getText());
				}
				else{
					sponsor.setName(sponsorName.getText());
					if(sponsorEmail.getText().length()>0)
						sponsor.setEmail(sponsorEmail.getText());
					if(sponsorPhone.getText().length()>0)
						sponsor.setPhone(sponsorPhone.getText());
					if(sponsorPaymentMethod.getText().length()>0)
						sponsor.setPaymentMethod(sponsorPaymentMethod.getText());
					if(sponsorPaymentDetail.getText().length()>0)
						sponsor.setPaymentDetail(sponsorPaymentDetail.getText());
				}
				Main.save();
				 // get a handle to the stage
			    Stage stage = (Stage)saveSponsor.getScene().getWindow();
			    // do what you have to do
			    stage.close();
			}
		}
		
		public boolean allFieldsPresent(){
			if(sponsorName.getText().length()>0 && (sponsorEmail.getText().length()>0 || sponsorPhone.getText().length()>0)){
				return true;
			}
			return false;
		}
}
