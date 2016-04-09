package eventak;

public class Run {

	public static void main(String[] args) {
		Party woo = new Party("Charity Pickle", "Fundraiser");
		Guest goey = new Guest("Harry Potter");
		Guest doey = new Guest("Donald Duck");
		Venue dooby = new Venue("La Plaza");
		Sponsor bigbad = new Sponsor("Big Bad");
		woo.addHighlight(goey);
		woo.addGuest(doey);
		woo.addGuest(doey);
		woo.setDate(2016, 2, 26, 21, 0);
		woo.addVenue(dooby);
		woo.addSponsor(bigbad);
		woo.addHighlight(doey);
		System.out.println(woo.inviteGuest(doey));
		try{
			System.out.println(woo.summarize());
		}
		catch(NotEnoughInfoException ex){
			System.out.println(ex.getMessage());
		}

	}

}
