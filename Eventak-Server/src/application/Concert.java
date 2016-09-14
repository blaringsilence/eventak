package application;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Hashtable;

public class Concert extends Event implements Ticketing, Serializable {
	
	private static final long serialVersionUID = 8353393366970395495L;
	private ArrayList<Entertainment> entertainment = new ArrayList<Entertainment>(); //TODO ADD THIS
	private Hashtable<String, int[]> tickets = new Hashtable<String, int[]>(); // tier: [price, available, limit]
	private ArrayList<ArrayList<String>> payments = new ArrayList<ArrayList<String>>(); // [Payment method (no details), amount paid, for how many tickets, type of tickets]
	Concert(){
		// for serialization purposes
	}
	public Concert(String name){
		super(name);
	}

	public void addEntertainment(Entertainment temp){
		this.entertainment.add(temp);
	}
	public ArrayList<Entertainment> getEntertainment(){
		/**
		 * Be wary of null/empty lists.
		 * */
		return this.entertainment;
	}
	public String getEntertainmentString(){
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
	public String toString(){
		return this.getDate() + "\t" + this.getName();
	}
	@Override
	public void addTicketTier(String tier, int price, int limit) {
		int[] vals = {price, limit, limit}; // limit = available at initialization, therefore 'available', the 2nd value, is inserted as limit
		this.tickets.put(tier, vals);
	}

	@Override
	public void changeTicketTierPrice(String tier, int newPrice) throws NotAddedException {
		int[] oldvals = this.tickets.get(tier);
		if(oldvals==null)
			throw new NotAddedException("This tier does not exist.");
		else{
			oldvals[0] = newPrice;
			tickets.put(tier, oldvals);
		}
	}

	@Override
	public void changeTicketTierLimit(String tier, int limitChange, boolean addToPrevious)
			throws NotAddedException, OutofLimitException {
		
			int[] oldvals = tickets.get(tier);
			if(oldvals==null)
				throw new NotAddedException("This tier does not exist.");
			else{
				if(addToPrevious){
					int temp = oldvals[2];
					temp+=limitChange;
					if(temp<0)
						throw new OutofLimitException("Limit cannot be negative. Current limit is " + oldvals[2] + ".");
					else
						oldvals[2]=temp;
				}
				else
					oldvals[2] = limitChange;
				tickets.put(tier, oldvals);
			}

	}

	@Override
	public int buyTicket(String tier, int number, String paymentMethod) throws OutofLimitException, NotAddedException {
		int[] oldvals = tickets.get(tier);
		if(oldvals==null)
			throw new NotAddedException("This tier does not exist.");
		else{
			int available = oldvals[1];
			if(number>available){
				throw new OutofLimitException("Number of requested tickets is more than the available ones.");
			}
			else{
				oldvals[1]-=number;
				tickets.put(tier, oldvals);
				int price = oldvals[0];
				int total = price*number;
				ArrayList<String> temp = new ArrayList<String>();
				temp.add(paymentMethod);
				temp.add(total+"");
				temp.add(number+"");
				temp.add(tier);
				this.payments.add(temp);
				return total;
			}
		}
	}

	@Override
	public Hashtable<String, int[]> getAvailableTickets() throws NotAddedException {
		if(this.tickets.isEmpty())
			throw new NotAddedException("No ticket tiers/details have been added yet.");
		else{
			return this.tickets;
		}
	}

	@Override
	public int getAvailableTickets(String tier) throws NotAddedException {
		int[] oldvals = this.tickets.get(tier);
		if(oldvals==null)
			throw new NotAddedException("This tier does not exist.");
		else
			return oldvals[1];
	}

	@Override
	public String generateTicket(String tier) throws NotAddedException, OutofLimitException {
		int[] oldvals = this.tickets.get(tier);
		if(oldvals==null){
			throw new NotAddedException("This tier does not exist.");
		}
		else{
			String price = oldvals[0]+"";
			return tier + " Ticket\n=============The holder of this ticket can attend " 
						+ this.getName() +" on " + this.getDate() + ".\n"
						+ "Price: " + price;
		}
	}
	
	public int getTicketsSold(){
		int ticketsSold = 0;
		for(int i=0; i<this.payments.size(); i++){
			ticketsSold+=Integer.parseInt(this.payments.get(i).get(2));
		}
		return ticketsSold;
	}
	public int getTicketsSold(String tier){
		int ticketsSold = 0;
		for(int i=0; i<this.payments.size(); i++){
			if(this.payments.get(i).get(3)==tier)
				ticketsSold+=Integer.parseInt(this.payments.get(i).get(2));
		}
		return ticketsSold;
	}
	public int getMoneyMade(){
		int moneyMade = 0;
		for(int i=0; i<this.payments.size(); i++){
			moneyMade+=Integer.parseInt(this.payments.get(i).get(1));
		}
		return moneyMade;
	}
	@Override
	public String inviteGuest(Guest guest){
		try{
			String inv = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\nDear " + guest.getName() 
					+ ",\nYou have been invited to attend " + super.getName() + " (a concert) at " + super.getVenue().getName()
					+ " on " + super.getDate();
			this.addGuest(guest);
			return inv;
		}
		catch(AlreadyExistsException ex){
			return "This guest has already been invited.";
		}
		catch(NullPointerException ex){
			return "Date or venue have not been set for this concert. Cannot invite " + guest.getName() +"."; 
		}
	}
	public ArrayList<String> getTierList(){
		ArrayList<String> temp = new ArrayList<String>();
		for(String key:tickets.keySet()){
			temp.add(key + "\t" + tickets.get(key)[0] + "\t" + tickets.get(key)[2]); // key\tPrice\tLimit
		}
		return temp;
	}
	@Override
	public String summarize() {
		try{
		return "Name: " + this.getName()
				+ "\nDate: " + this.getDate()
				+ "\nSponsor(s):\n" + super.getSponsorsString()
				+ "\nVenue:\n" + super.getVenue().toString()
				+ "\nNumber of tickets sold: " +  this.getTicketsSold()
				+ "\nRevenue: " + this.getMoneyMade()
				+ "\nNumber of guests invited: " + super.getGuestList().size();
		}
		catch(NullPointerException ex){
			return "Date or venue have not been set for this concert.";
		}
	}

}
