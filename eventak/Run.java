package eventak;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

/*
 * TODO: add ability to change/delete things from parties/etc
 * TODO: add timeline report generation for events
 * TODO: make number of guests calculated from those with RSVP = true only
 */

/**
 * 
 * MAIN.
 * An application that helps an event organizing agency create and manage events, produce reports, and have contacts.
 * A user(employee) can create an event of any of the following types:
 * - Party:
 * 	~ Any private party, held for or by (a) specific person(s). For example: birthdays, weddings, fundraisers.
 * - Conference:
 * 	~ Any conference that will have (a) speaker(s) and attendees. This will also implement ticketing of one tier.
 * - Concert:
 *  ~ Any show/entertainment event with ticketing of one or more tiers (VIP, regular, etc).
 * 
 * A user can also add or browse contacts, which can be connected to venues/etc.
 * They can also create venues, and use them in specific events.
 * 
 * @author Mariam Maarouf
 * @version 0.1.0
 * 
 * */

public class Run {
	private static ArrayList<Party> parties = new ArrayList<Party>();
	private static ArrayList<Concert> concerts = new ArrayList<Concert>();
	private static ArrayList<Contact> contacts = new ArrayList<Contact>();
	private static ArrayList<Venue> venues = new ArrayList<Venue>();
	
	public static void main(String[] args) {
		// MAIN MENU
		System.out.println("Welcome!");
		initialize();

		dance: while(true){
			mainMenu();
			int x = prompt(1, 9);
				switch(x){ // here we are absolutely positive x is valid (between 1 and 9)
					case 1:
						createParty();
						break;
					case 2:
						createConcert();
						break;
					case 3:
						listParties();
						break;
					case 4:
						listConcerts();
						break;
					case 5:
						addContact();
						break;
					case 6:
						addVenue();
						break;
					case 7:
						listContacts();
						break;
					case 8:
						listVenues();
						break;
					case 9:
						break dance;
				}
		}
		save();
		System.out.println("Bye!");
		System.exit(0);
		
		
	}
	
	public static void initialize(){
		// create files if they do not exist
		String[] paths = {"contacts","concerts","parties","venues"};
		try{
			for(int i=0; i<paths.length; i++){
				File f = new File("/tmp/"+paths[i]+".ser");
				f.createNewFile();
			}
		}catch(IOException ex){
			ex.printStackTrace();
			return;
		}
		
		try{
			// parties
			FileInputStream f = new FileInputStream("/tmp/parties.ser");
			ObjectInputStream in = new ObjectInputStream(f);
			parties = (ArrayList<Party>) in.readObject();
			in.close();
			f.close();
			// contacts
			FileInputStream f2 = new FileInputStream("/tmp/contacts.ser");
			ObjectInputStream in2 = new ObjectInputStream(f2);
			contacts = (ArrayList<Contact>) in2.readObject();
			in2.close();
			f2.close();
			// concerts
			FileInputStream f3 = new FileInputStream("/tmp/concerts.ser");
			ObjectInputStream in3 = new ObjectInputStream(f3);
			concerts = (ArrayList<Concert>) in3.readObject();
			in3.close();
			f3.close();
			// venues
			FileInputStream f4 = new FileInputStream("/tmp/venues.ser");
			ObjectInputStream in4 = new ObjectInputStream(f4);
			venues = (ArrayList<Venue>) in4.readObject();
			in4.close();
			f4.close();
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
	
	public static void save(){
		try{
			// parties
			FileOutputStream f = new FileOutputStream("/tmp/parties.ser");
			ObjectOutputStream out = new ObjectOutputStream(f);
			out.writeObject(parties);
			out.close();
			f.close();
			// contacts
			FileOutputStream f2 = new FileOutputStream("/tmp/contacts.ser");
			ObjectOutputStream out2 = new ObjectOutputStream(f2);
			out2.writeObject(contacts);
			out2.close();
			f2.close();
			// concerts
			FileOutputStream f3 = new FileOutputStream("/tmp/concerts.ser");
			ObjectOutputStream out3 = new ObjectOutputStream(f3);
			out3.writeObject(concerts);
			out3.close();
			f3.close();
			// venues
			FileOutputStream f4 = new FileOutputStream("/tmp/venues.ser");
			ObjectOutputStream out4 = new ObjectOutputStream(f4);
			out4.writeObject(venues);
			out4.close();
			f4.close();
		}
		catch(IOException ex){
			ex.printStackTrace();
			return;
		}
	}
	
	
	public static void mainMenu(){
		System.out.println("MAIN MENU:");
		System.out.println("1. Create Party");
		System.out.println("2. Create Concert");
		System.out.println("3. List Parties");
		System.out.println("4. List Concerts");
		System.out.println("5. Add Contact");
		System.out.println("6. Add Venue");
		System.out.println("7. List Contacts");
		System.out.println("8. List Venues");
		System.out.println("9. Save and exit the program");
	}
	
	@SuppressWarnings("resource")
	public static int prompt(int min, int max){
		// NOW CHOOSE
		int choice;
		while(true){
			System.out.println("Enter a choice (number between " + min + " and " + max + "): ");
			Scanner scan = new Scanner(System.in);
			choice = scan.nextInt();
			if(choice>=min && choice<=max){
				break;
			}
			else
				System.out.println("Wrong choice.");
		}
		return choice;
	}
	@SuppressWarnings("resource")
	public static void createParty(){
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter the name of the party:");
		String name = scan.nextLine();
		System.out.println("Enter the type of the party (Wedding, birthday, etc):");
		String type = scan.nextLine();
		Party temp = new Party(name, type);
		parties.add(temp);
		System.out.println("Would you like to add more details? 1 for yes, 0 to return to main menu: ");
		int ch = prompt(0,1);
		switch(ch){
		case 1:
			// more details here
			break;
		case 0:
			return;
		}
	}
	@SuppressWarnings("resource")
	public static void createConcert(){
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter the name of the concert:");
		String name = scan.nextLine();
		Concert temp = new Concert(name);
		concerts.add(temp);
		System.out.println("Would you like to add more details? 1 for yes, 0 to return to main menu: ");
		int ch = prompt(0,1);
		switch(ch){
		case 1:
			// more details here
			break;
		case 0:
			return;
		}
	}
	@SuppressWarnings("resource")
	public static void addContact(){
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter the name of the contact:");
		String name = scan.nextLine();
		System.out.println("Enter their phone:");
		String phone = scan.nextLine();
		Contact temp = new Contact(name, phone);
		contacts.add(temp);
		System.out.println("Would you like to add more details? 1 for yes, 0 to return to main menu: ");
		int ch = prompt(0,1);
		switch(ch){
		case 1:
			// more details here
			break;
		case 0:
			return;
		}
	}
	@SuppressWarnings("resource")
	public static void addVenue(){
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter the name of the venue:");
		String name = scan.nextLine();
		System.out.println("Enter its address(in one line):");
		String address = scan.nextLine();
		Venue temp = new Venue(name, address);
		venues.add(temp);
		System.out.println("Would you like to add more details? 1 for yes, 0 to return to main menu: ");
		int ch = prompt(0,1);
		switch(ch){
		case 1:
			// more details here
			break;
		case 0:
			return;
		}
	}
	
	public static void listParties(){
		if(parties.size()>0){
			for(int i=0; i<parties.size(); i++){
				System.out.println("ID: " + i + "\nName: " + parties.get(i).getName() + "\n\n");
			}
		}
		else{
			System.out.println("No parties available.");
		}
		return;
	}
	
	public static void listConcerts(){
		if(concerts.size()>0){
			for(int i=0; i<concerts.size(); i++){
				System.out.println("ID: " + i + "\nName: " + concerts.get(i).getName() + "\n\n");
			}
		}
		else{
			System.out.println("No concerts available.");
		}
		return;
		
	}
	
	public static void listContacts(){
		if(contacts.size()>0){
			for(int i=0; i<contacts.size(); i++){
				System.out.println("ID: " + i);
				System.out.println(contacts.get(i).toString());
				System.out.println("======================================================");
			}
		}
		else
			System.out.println("No contacts available yet.");
		return;
	}
	
	public static void listVenues(){
		if(venues.size()>0){
			for(int i=0; i<venues.size(); i++){
				System.out.println("ID: " + i);
				System.out.println(venues.get(i).toString());
				System.out.println("======================================================");
			}
		}
		else
			System.out.println("No venues available yet.");
		return;
	}

}
