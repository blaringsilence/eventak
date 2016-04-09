package eventak;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class Party extends Event {
	private ArrayList<Guest> highlighted = new ArrayList<Guest>(); // Highlighted people of the party. Example: bride, groom, birthday person, charity org (yes, also type Guest in this context) etc
	private ArrayList<Entertainment> entertainment = new ArrayList<Entertainment>();
	private String type;
	Party(String name, String type){
		super(name);
		this.type=type;
	}
	public void addHighlight(Guest guest){
		this.highlighted.add(guest);
	}
	public void addEntertainment(String name, GregorianCalendar time){
		Entertainment temp = new Entertainment(name, time);
		this.entertainment.add(temp);
	}
	public ArrayList<Entertainment> getEntertainment(){
		return this.entertainment;
	}
	@Override
	public String inviteGuest(Guest guest) {
		super.addGuest(guest);
		try{
			return "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\nDear " + guest.getName() + ",\nYou have been invited to attend a " + this.type + " party by " + this.getHighlighted() + ". RSVP, regrets only.\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";
		}
		catch(NotEnoughInfoException ex){
			return "Cannot generate invite. " + ex.getMessage();
		}
	}
	public String getHighlighted() throws NotEnoughInfoException{
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
	public String summarize() { // TODO: add more fields/a more comprehensive summary
		return "Party: " + super.getName() + "\nType: " + this.type + "\nFor: " + this.getHighlighted() + "\nSponsor(s):\n" + super.getSponsorsString() + "\nDate: " + super.getDate().getTime() + "\nVenue:\n"+ super.getVenue().toString() + "\nNumber of guests: " + super.getGuestList().size() ;
	}

}
