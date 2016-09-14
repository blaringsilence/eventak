package application;

import javafx.fxml.FXML;
import java.net.*;
import java.io.*;

import javafx.scene.control.Button;

import javafx.scene.control.TextField;

import javafx.event.ActionEvent;

import javafx.scene.control.TextArea;

import javafx.scene.input.KeyEvent;

public class MainController {
	@FXML
	private TextArea outputArea;
	@FXML
	private TextField concertName;
	@FXML
	private TextField ticketTier;
	@FXML
	private TextField numerOfTickets;
	@FXML
	private Button buyButton;

	// Event Listener on TextField[#concertName].onKeyTyped
	@FXML
	public void validateKeys(KeyEvent event) {
		buyButton.setDisable(!this.allFieldsPresent());
	}
	// Event Listener on Button[#buyButton].onAction
	@FXML
	public void buyTicket(ActionEvent event) {
		if(this.allFieldsPresent()){
			try{
			int port = 8000;
			String serverName = "localhost";
			Socket client = new Socket(serverName,port);
			outputArea.setText("Just connected to "+serverName+":"+port);
			OutputStream outSt = client.getOutputStream();
			DataOutputStream out = new DataOutputStream(outSt);
			out.writeUTF(concertName.getText()+","+ticketTier.getText()+","+numerOfTickets.getText());
			InputStream inFromServer = client.getInputStream();
	         DataInputStream in =
	                        new DataInputStream(inFromServer);
	         outputArea.setText(outputArea.getText()+"\nSERVER REPLY: " + in.readUTF());
	         client.close();
			}
			catch (IOException ex){
				ex.printStackTrace();
			}
			
		}
	}
	public boolean allFieldsPresent(){
		try{
			int n = Integer.parseInt(numerOfTickets.getText());
			if(concertName.getText().length()>0 && ticketTier.getText().length()>0 && n>0){
				return true;
			}
			return false;
			
		}
		catch (NumberFormatException ex){
			return false;
		}
	}
}
