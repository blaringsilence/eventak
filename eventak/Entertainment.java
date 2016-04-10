package eventak;

import java.io.Serializable;
import java.util.GregorianCalendar;

/**
 * What's an event without Entertainment? Band, show, DJ, etc.
 * Includes:
 * - name: Name of band, etc
 * - contact: who's their contact? 
 * - time: when are they appearing on stage in that particular event?
 * 
 * Entertainment is specific to events, and are not general.
 * */

public class Entertainment implements Serializable{
	private static final long serialVersionUID = 1493175500998608962L;
	private String name;
	private Contact contact;
	private GregorianCalendar time;
	Entertainment(){
		// for serialization purposes
	}
	Entertainment(String name){
		this.name = name;
	}
	Entertainment(String name, GregorianCalendar time){
		this.name = name;
		this.time = time;
	}
	public void setContact(Contact contact){
		this.contact = contact;
	}
	public Contact getContact(){
		return this.contact;
	}
	public String getContactString(){
		if(this.contact!=null)
			return this.contact.toString();
		else
			return "No contact has been added to this entertainment yet.";
	}
	public void setTime(GregorianCalendar time){
		this.time = time;
	}
	public GregorianCalendar getTime(){
		return this.time;
	}
	public String getName(){
		return this.name;
	}
	public String toString(){
		return "Name: " + this.name + "\nTime: " + this.time.getTime() +  "\nContact:\n" + this.getContactString();
	}

}
