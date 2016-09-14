package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ListView;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ManagePartyController implements Initializable{
	@FXML
	private ListView<Party> partyList;

	// Event Listener on ListView[#partyList].onMouseClicked
	@FXML
	public void openParty(MouseEvent click) {
		if(click.getClickCount()==2){ // double click
			Party selectedParty = partyList.getSelectionModel().getSelectedItem();
			if(selectedParty != null){
				Stage partyStage = new Stage();
				try{
					  FXMLLoader loader = new FXMLLoader(getClass().getResource("ShowParty.fxml"));
					  AnchorPane page = (AnchorPane) loader.load();
					  ShowPartyController controller = loader.<ShowPartyController>getController();
					  controller.initData(selectedParty);
					  Scene scene = new Scene(page);
					  partyStage.setScene(scene);
					  partyStage.setTitle(selectedParty.getName());
					  partyStage.show();
				}
				catch (IOException e){
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		ObservableList<Party> parties = FXCollections.observableArrayList(Main.PARTY_LIST);
		partyList.setItems(parties);
		
	}
}
