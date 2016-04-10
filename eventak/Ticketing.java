package eventak;

import java.util.Hashtable;

/**
 * The ability to charge/sell tickets of different tiers.
 * */

public interface Ticketing {
	public void addTicketTier(String tier, int price, int limit); 
	public void changeTicketTierPrice(String tier, int newPrice) throws NotAddedException;
	public void changeTicketTierLimit(String tier, int limitChange, boolean addToPrevious) throws NotAddedException, OutofLimitException; // set new limit/erase the old one, or add/subtract from existing limit
	public int buyTicket(String tier, int number, String paymentMethod) throws OutofLimitException, NotAddedException; // checks availability, if available, buy, return amount of money paid/to be paid.
	public Hashtable<String, int[]> getAvailableTickets() throws NotAddedException; // returns tier, number, and price
	public int getAvailableTickets(String tier) throws NotAddedException; // returns only the number
	public String generateTicket(String tier) throws NotAddedException, OutofLimitException;
}
