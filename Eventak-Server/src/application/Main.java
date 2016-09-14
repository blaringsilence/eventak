package application;
	
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;



public class Main extends Application {
	protected static ArrayList<Party> PARTY_LIST = new ArrayList<Party>();
	protected static ArrayList<Concert> CONCERT_LIST = new ArrayList<Concert>();
	protected static ArrayList<Contact> CONTACT_LIST = new ArrayList<Contact>();
	@Override
	public void start(Stage primaryStage) {
		try {
			AnchorPane root = (AnchorPane) FXMLLoader.load(Main.class.getResource("Sample.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("EVENTAK");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void initFiles(){
		String[] paths = {"contacts.ser","concerts.ser","parties.ser"};
		try{
			for(int i=0; i<paths.length; i++){
				File f = new File(paths[i]);
				boolean created = f.createNewFile();
				if(!created){
					try{
						// parties
						Path p0 = Paths.get(paths[i]);
						byte[] b0 = Files.readAllBytes(p0);
						ByteArrayInputStream bais = new ByteArrayInputStream(b0);
						ObjectInputStream in = new ObjectInputStream(bais);
						switch(i){
						case 2:
							PARTY_LIST = (ArrayList<Party>) in.readObject();
							break;
						case 1:
							CONCERT_LIST = (ArrayList<Concert>) in.readObject();
							break;
						case 0:
							CONTACT_LIST = (ArrayList<Contact>) in.readObject();
							break;
						}
						in.close();
						bais.close();
					}
					catch(IOException ex){
						ex.printStackTrace();
						return;
					}
					catch(ClassNotFoundException ex){
						ex.printStackTrace();
						return;
						
					}
				}
			}
		}catch(IOException ex){
			ex.printStackTrace();
			return;
		}
		
	}
	
	public static void save(){
		String[] paths = {"contacts.ser","concerts.ser","parties.ser"};
		for(int i=0; i<paths.length; i++){
			try{
				// parties
				FileOutputStream f = new FileOutputStream(paths[i]);
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				ObjectOutputStream out = new ObjectOutputStream(baos);
				Object w = new Object();
				switch(i){
				case 0:
					w = CONTACT_LIST;
					break;
				case 1:
					w = CONCERT_LIST;
					break;
				case 2:
					w = PARTY_LIST;
					break;
				}
				out.writeObject(w);
				baos.writeTo(f);
				out.close();
				f.close();
				baos.close();
				
			}
			catch(IOException ex){
				ex.printStackTrace();
				return;
			}
		}
	
	}
	
	public static void main(String[] args) {
		initFiles();
		//launch(args);
		System.out.println("EVENTAK SEVRER HAS STARTED");
		int port = Integer.parseInt("8000");
	      try
	      {
	         Thread t = new GreetingServer(port);
	         t.start();
	      }catch(IOException e)
	      {
	         e.printStackTrace();
	      }
	}
}
