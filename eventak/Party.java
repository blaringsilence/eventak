package eventak;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.GregorianCalendar;

/**
 * <h2>Create, and manage, a Party <small>(private party, wedding, etc)</small></h2>
 * This class is one of the subclasses of Event, and should be directly accessible to main/user/etc. 
 * Apart from the default methods and attributes of Event, it has:
 *  - "Highlighted person(s)" (Bride, groom, etc)
 *  - "Entertainment" (DJ, show, etc)
 *  - "Type" (Wedding, birthday party, etc)
*/

public class Party extends Event implements Serializable { 	
	private static final long serialVersionUID = 7898805776979366139L;
	private ArrayList<Guest> highlighted = new ArrayList<Guest>(); // Highlighted people of the party. Example: bride, groom, birthday person, charity org (yes, also type Guest in this context) etc
	private ArrayList<Entertainment> entertainment = new ArrayList<Entertainment>();
	private String type;
	Party(){
		super();
	}
	Party(String name, String type){
		/**
		 * Create a new party.
		 * @param name Name of the event, for the organizers mostly
		 * @param type Type of the event (Wedding, birthday, private party, etc)
		 * */
		super(name);
		this.type=type;
	}
	public void addHighlight(Guest guest){
		/**
		 * Add a highlighted person (Bride, groom, etc)
		 * @param guest A Guest object
		 * */
		this.highlighted.add(guest);
	}
	public void addEntertainment(String name, GregorianCalendar time){
		/**
		 * Add Entertainment (Show, DJ, etc)
		 * @param name Name of band, etc
		 * @param time What time will they be appearing/performing?
		 * */
		Entertainment temp = new Entertainment(name, time);
		this.entertainment.add(temp);
	}
	public ArrayList<Entertainment> getEntertainment(){
		/**
		 * Get list of Entertainment for this party
		 * Not meant to be directly used.
		 * Be wary of null/empty lists.
		 * @return ArrayList<Entertainment>
		 * */
		return this.entertainment;
	}
	public String getEntertainmentString(){
		/**
		 * Get list of Entertainment for this party in the form of a String
		 * Null/empty lists handled.
		 * @return String
		 * */
		if(this.entertainment.size()>0){
			String ent ="";
			for(int i=0; i<this.entertainment.size(); i++){
				ent += this.entertainment.get(i).toString() + "\n-------\n";
			}
			return ent;
		}
		else
			return "No entertainment has been added to this party yet.";
	}
	@Override
	public String inviteGuest(Guest guest) {
		/**
		 * Invite Guest (add guest + generate invitation for them)
		 * Avoid errors by not inviting a previously added/invited guest and providing highlighted person(s) info
		 * @param guest the guest you want to invite as a Guest object
		 * @return String the invitation, or an error message if an exception was thrown.
		 * */
		try{
			super.addGuest(guest);
			try{
				return "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\nDear " + guest.getName() + ",\nYou have been invited to attend a " + this.type 
						+ " party by " + this.getHighlightedString() + ". RSVP, regrets only." 
						+ "\nVenue : " + super.getVenue().getName()
						+ "\nDate: " + super.getDate().getTime()
						+ "\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";
			}
			catch(NotEnoughInfoException ex){
				return "Cannot generate invite. " + ex.getMessage();
			}
			catch(NullPointerException ex){
				return "Date or venue have not been set for this " + this.type + ". Cannot invite guest. ";
			}
		}
		catch(AlreadyExistsException ex){
			return ex.getMessage();
		}
		
	}
	public ArrayList<Guest> getHighlighted(){
		/**
		 * Get ArrayList of highlighted person(s) for this party
		 * For internal use.
		 * @return ArrayList<Guest> 
		 * */
		return this.highlighted;
	}
	public String getHighlightedString() throws NotEnoughInfoException{
		/**
		 * Get String of highlighted person(s) for this party
		 * @except NotEnoughInfoException for when there are no highlighted person(s) added. 
		 * Usually functions that return a String of people/etc do not throw exceptions but return the exception message right away, but in this case, it's used in the invitation generation and we need to catch the exception.
		 * @return String
		 * */
		if(this.highlighted.size()>0){
			String high = "";
			for(int i=0; i<this.highlighted.size(); i++){
				high += this.highlighted.get(i).getName();
				if(i!=this.highlighted.size()-1)
					high+=", ";
			}
			return high;
		}
		else
			throw new NotEnoughInfoException("Highlighted person(s) not specified.");
	}

	@Override
	public String summarize() { 
		/**
		 * Produce a summary/report of the event.
		 * Catches exception in .getHighlighted().
		 * @return String summary/report
		 * */
		String highlighted;
		try{
			highlighted = this.getHighlightedString();
		}
		catch(NotEnoughInfoException ex){
			highlighted = ex.getMessage();
		}
		try{
		return "Party: " + super.getName() 
				+ "\nType: " + this.type 
				+ "\nFor: " + highlighted 
				+ "\nSponsor(s):\n" + super.getSponsorsString() 
				+ "\nDate: " + super.getDate().getTime() 
				+ "\nVenue:\n"+ super.getVenue().toString() 
				+ "\nBudget:" + super.getBudget()
				+ "\nEntertainment:\n" + this.getEntertainmentString()
				+ "\nNumber of guests: " + super.getGuestList().size();
		}
		catch(NullPointerException ex){
			return "Date or venue have not been set for this party.";
		}
				
	}
	

}
