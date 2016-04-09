package eventak;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public abstract class Event {
	private String name;
	private Venue venue;
	private ArrayList<Guest> guestList = new ArrayList<Guest>();
	private GregorianCalendar date;
	private Cost budget; // what it should cost, max
	private ArrayList<Sponsor> sponsors = new ArrayList<Sponsor>(); // so methods are accessible, including set and get.
	Event(String name){
		this.name = name;
	}
	public void addVenue(Venue venue){
		this.venue = venue;
	}
	public void addSponsor(Sponsor sponsor){
		this.sponsors.add(sponsor);
	}
	public void addGuest(Guest guest){
		this.guestList.add(guest);
	}
	public void setDate(int year, int month, int day, int hrs, int mins){
		this.date = new GregorianCalendar(year, month, day, hrs, mins);
	}
	public ArrayList<Guest> getGuestList(){
		return this.guestList;
	}
	public GregorianCalendar getDate(){
		return this.date;
	}
	public void setBudget(int total){
		budget = new Cost(total);
	}
	public int getBudgetPerPerson() throws NotEnoughInfoException{
		if(budget!=null && this.guestList.size()>0){
			budget.setNumberOfPeople(this.guestList.size());
			return budget.getCostPerPerson();
		}
		else
			throw new NotEnoughInfoException("Budget is not set, or guests have not been added yet.");
	}
	public Venue getVenue(){
		return this.venue;
	}
	public String getName(){
		return this.name;
	}
	public ArrayList<Sponsor> getSponsors(){
		return this.sponsors;
	}
	public String getSponsorsString(){
		if(this.sponsors.size()>0){
			String sp = "";
			for(int i=0; i<this.sponsors.size(); i++){
				sp+=this.sponsors.get(i).toString() + "\n";
			}
			return sp;
		}
		else
			return "This event doesn't have any sponsors yet.";
	}
	public abstract String inviteGuest(Guest guest); // adds the guest, and returns a String invite.
	public abstract String summarize(); // generates a report about the entire event, including number of guests
	
}
