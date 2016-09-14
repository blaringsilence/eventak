package application;

import java.io.Serializable;

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
	private String email; 
	private String phone; 
	Contact(){
		// for serializable
	}
	Contact(String name){
		this.name=name;
	}
	Contact(String name, String phone){
		this.name=name;
		this.phone = phone;
	}
	Contact(String name, String phone, String email){
		this.name=name;
		this.phone = phone;
		this.email = email;
	}
	public void setName(String name){
		this.name = name;
	}
	public void setEmail(String email){
		this.email = email;
	}
	public void setPhone(String phone){
		this.phone  = phone;
	}
	
	public String getEmail(){
		// emails toString
		if (this.email != null){
			return this.email;
		}
		else{
			return "No email has been added to this contact.";
		}
		
	}
	public String getPhone(){
		// phones toString
		if(this.phone!=null){
			return this.phone;
		}
		else{
			return "No phone added to this contact.";
		}
		
	}
	public String getName(){
		return this.name;
	}
	public String toString(){
		return "Name: " + this.name + "\nEmail: " + this.getEmail() + "\nPhone: " + this.getPhone();
	}
}
