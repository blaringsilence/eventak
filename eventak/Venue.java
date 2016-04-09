package eventak;

import java.util.ArrayList;

public class Venue {
	private String name;
	private String address;
	private ArrayList<String> phones = new ArrayList<String>();
	private Contact contact; // optional
	Venue(String name){
		this.name = name;
	}
	Venue(String name, String address){
		this.name = name;
		this.address = address;
	}
	Venue(String name, String address, String phone){
		this.name = name;
		this.address = address;
		this.phones.add(phone);
	}
	Venue(String name, String address, String phone, Contact contact){
		this.name = name;
		this.address = address;
		this.phones.add(phone);
		this.contact = contact;
	}
	public void setContact(Contact contact){
		this.contact = contact;
	}
	public Contact getContact() throws NotAddedException{
		if(this.contact != null){
			return this.contact;
		}
		else
			throw new NotAddedException("No contact has been added to this venue yet.");
	}
	public void setName(String name){
		this.name = name;
	}
	public String getName(){
		return this.name;
	}
	public void setAddress(String address){
		this.address = address;
	}
	public String getAddress(){
		if(this.address!=null)
			return this.address;
		else
			return "No address has been added to this venue yet.";
	}
	public void addPhone(String phone){
		this.phones.add(phone);
	}
	public String getPhones(){
		if(this.phones.isEmpty())
			return "No phones have been added to this venue yet.";
		else{
			String ph = "[ ";
			for(int i=0; i<this.phones.size(); i++){
				ph += (this.phones.get(i) + " ");
			}
			ph += "]";
			return ph;
		}
	}
	public String toString(){
		String con;
		try{
			Contact temp = this.getContact();
			con = "\n\n" + temp.toString();
		}
		catch(NotAddedException ex){
			con = ex.getMessage();
		}
		return "Venue name: " + this.name + "\nAddress: " + this.getAddress() + "\nPhones: " + this.getPhones() + "\nContact: " + con;
	}
	

}
