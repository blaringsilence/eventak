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
import javafx.scene.control.ContextMenu;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ManageConcertController implements Initializable{
	@FXML
	private ListView<Concert> concertList;
	@FXML
	private ContextMenu rightClick;
	@FXML
	private MenuItem manageTicketOption;

	// Event Listener on ListView[#partyList].onMouseClicked
	@FXML
	public void openParty(MouseEvent click) {
		if(click.getClickCount()==2){ // double click
			Concert selectedConcert = concertList.getSelectionModel().getSelectedItem();
			if(selectedConcert != null){
				Stage partyStage = new Stage();
				try{
					  FXMLLoader loader = new FXMLLoader(getClass().getResource("ShowParty.fxml"));
					  AnchorPane page = (AnchorPane) loader.load();
					  ShowPartyController controller = loader.<ShowPartyController>getController();
					  controller.initData(selectedConcert);
					  Scene scene = new Scene(page);
					  partyStage.setScene(scene);
					  partyStage.setTitle(selectedConcert.getName());
					  partyStage.show();
				}
				catch (IOException e){
					e.printStackTrace();
				}
			}
		}
	}
	
	@FXML
	public void manageTicket(){
		Concert con = concertList.getSelectionModel().getSelectedItem();
		if(con!=null){
			Stage partyStage = new Stage();
			try{
				  FXMLLoader loader = new FXMLLoader(getClass().getResource("ManageTickets.fxml"));
				  AnchorPane page = (AnchorPane) loader.load();
				  ManageTicketsController controller = loader.<ManageTicketsController>getController();
				  controller.initData(con);
				  Scene scene = new Scene(page);
				  partyStage.setScene(scene);
				  partyStage.setTitle(con.getName() + " | Manage Tickets");
				  partyStage.show();
			}
			catch (IOException e){
				e.printStackTrace();
			}
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		ObservableList<Concert> concerts = FXCollections.observableArrayList(Main.CONCERT_LIST);
		concertList.setItems(concerts);
		
	}
}
