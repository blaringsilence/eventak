package eventak;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Contacts are the mother/superclass of all Guests/Sponsors and are used anywhere that could be of use to the event agency.
 * Venues and Entertainment have contacts.
 * Useful because contact lists can exist.
 * Has:
 * - name: Name of contact
 * - emails: List of emails (because who has 1 email nowadays)
 * - phones: List of phones
 * 
 * .getEmails() and .getPhones() both return Strings/exception strings because they're lists of Strings and not Email/Phone objects.
 * */

public class Contact implements Serializable{
	
	private static final long serialVersionUID = 5078177864531652165L;
	private String name;
	private ArrayList<String> emails = new ArrayList<String>(); 
	private ArrayList<String> phones = new ArrayList<String>(); 
	Contact(){
		// for serializable
	}
	Contact(String name){
		this.name=name;
	}
	Contact(String name, String phone){
		this.name=name;
		this.phones.add(phone);
	}
	Contact(String name, String phone, String email){
		this.name=name;
		this.phones.add(phone);
		this.emails.add(email);
	}
	
	public void addEmail(String email){
		this.emails.add(email);
	}
	public void addPhone(String phone){
		this.phones.add(phone);
	}
	
	public String getEmails(){
		// emails toString
		if (this.emails.isEmpty()){
			return "No emails added to this contact.";
		}
		else{
			String em = "[ ";
			for(int i=0; i<this.emails.size(); i++){
				em += (this.emails.get(i) + " ");
			}
			em += "]";
			return em;
		}
		
	}
	public String getPhones(){
		// phones toString
		if(this.phones.isEmpty()){
			return "No phones added to this contact.";
		}
		else{
			String ph = "[ ";
			for(int i=0; i<this.phones.size(); i++){
				ph += (this.phones.get(i) + " ");
			}
			ph += "]";
			return ph;
		}
		
	}
	public String getName(){
		return this.name;
	}
	public String toString(){
		return "Name: " + this.name + "\nEmails: " + this.getEmails() + "\nPhones: " + this.getPhones();
	}
}
