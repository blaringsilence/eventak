package eventak;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.GregorianCalendar;
/**
 * Event Superclass. Abstract. Mother of all event type classes (Party, etc)
 * Has:
 * - name: Name of the event, for organizers.
 * - venue: Venue where event is held, object of type Venue.
 * - guestList: ArrayList<Guest> of guests attending or invited to this event
 * - date: when the event is being held.
 * - budget: self-explanatory, of type Cost.
 * - sponsors: who's paying for the event. ArrayList<Sponsor>.
 * */
// TODO: add caterer and decorator
public abstract class Event implements Serializable{
	private static final long serialVersionUID = 2224782729373111289L;
	private String name;
	private Venue venue;
	private ArrayList<Guest> guestList = new ArrayList<Guest>();
	private GregorianCalendar date;
	private Cost budget; // what it should cost, max
	private ArrayList<Sponsor> sponsors = new ArrayList<Sponsor>(); // so methods are accessible, including set and get.
	Event(){
		// for serialization purposes only
	}
	Event(String name){
		this.name = name;
	}
	public void addVenue(Venue venue){
		this.venue = venue;
	}
	public void addSponsor(String name, String phone, String email){
		/**
		 * Add sponsor for the event.
		 * Instead of passing an object of type Sponsor, this seemed more sensible because this sponsor and their payment method(s)
		 * only/should only be limited to this particular event.
		 * If any fields are missing while being collected from the user they should be null, or an empty String
		 * @param name Name of sponsor
		 * @param phone 
		 * @param email 
		 * */
		// anything that doesn't exist should be defaulted to null
		Sponsor sponsor = new Sponsor(name, phone, email);
		this.sponsors.add(sponsor);
	}
	public void addGuest(Guest guest) throws AlreadyExistsException{
		/**
		 * Add guest to list of guests. 
		 * @param guest a Guest object
		 * @except AlreadyExistsException if guest was already added (to avoid endless additions of same object. Same name, etc is fine because it won't be the same object then.)
		 * */
		if(this.guestList.contains(guest))
			throw new AlreadyExistsException("This guest has already been added.");
		else
			this.guestList.add(guest);
	}
	public void setDate(int year, int month, int day, int hrs, int mins){
		/**
		 * Set date for the event.
		 * @param year
		 * @param month
		 * @param day
		 * @param hrs
		 * @param mins
		 * */
		this.date = new GregorianCalendar(year, month, day, hrs, mins);
	}
	public ArrayList<Guest> getGuestList(){
		/**
		 * Get guest list in ArrayList<Guest> format
		 * @return ArrayList<Guest>
		 * */
		return this.guestList;
	}
	public GregorianCalendar getDate(){
		/**
		 * Get date.
		 * @return GregorianCalendar
		 * */
		return this.date;
	}
	public void setBudget(int total){
		/**
		 * Set budget for entire thing.
		 * @param total 
		 * */
		budget = new Cost(total);
	}
	public int getBudgetPerPerson() throws NotEnoughInfoException{
		/**
		 * Get budget per person. 
		 * Depends on currently invited guests (not a future estimate).
		 * @return int
		 * @except NotEnoughInfoException if budget is not set or there are no added guests.
		 * */
		if(budget!=null && this.guestList.size()>0){
			budget.setNumberOfPeople(this.guestList.size());
			return budget.getCostPerPerson();
		}
		else
			throw new NotEnoughInfoException("Budget is not set, or guests have not been added yet.");
	}
	public int getBudget(){
		return this.budget.getTotal();
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
	public Sponsor getSponsorById(int ID){
		/**
		 * Get a certain sponsor by their ID.
		 * ID of a sponsor is their position in the sponsors list.
		 * Created to make it easier to edit a certain sponsor (name, payment methods, etc)
		 * @param ID
		 * @return Sponsor
		 * */
			return this.sponsors.get(ID);
	}
	public String getSponsorsString(){
		/**
		 * Get sponsors list in the form of a String.
		 * Includes ID (position) in the sponsors list for every one.
		 * @return String
		 * */
		if(this.sponsors.size()>0){
			String sp = "";
			for(int i=0; i<this.sponsors.size(); i++){
				sp+="ID: " + i + "\n" + this.sponsors.get(i).toString() + "\n";
			}
			return sp;
		}
		else
			return "This event doesn't have any sponsors yet.";
	}

	public abstract String inviteGuest(Guest guest); // adds the guest, and returns a String invite.
	public abstract String summarize(); // generates a report about the entire event, including number of guests
	
}
