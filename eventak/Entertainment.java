package eventak;

import java.util.GregorianCalendar;

public class Entertainment {
	private String name;
	private Contact contact;
	private GregorianCalendar time;
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
	public void setTime(GregorianCalendar time){
		this.time = time;
	}
	public GregorianCalendar getTime(){
		return this.time;
	}
	public String getName(){
		return this.name;
	}

}
