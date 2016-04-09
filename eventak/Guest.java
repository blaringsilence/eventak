package eventak;

public class Guest extends Contact{
	private boolean RSVP = true; // Default: Regrets Only. Means: only respond (RSVP) if you're not coming. Therefore, default is true.
	private String address;
	private String comment; // optional. If Guest has a certain comment about meals, etc, it's stored here.
	public Guest(String name){
		super(name);
	}
	public Guest(String name, String phone){
		super(name, phone);
	}
	public Guest(String name, String phone, String email){
		super(name, phone, email);
	}
	public void setAddress(String address){
		this.address = address;
	}
	public String getAddress(){
		if(this.address!=null)
			return this.address;
		else
			return "No address has been added to this guest yet.";
	}
	public void RSVP(){ // use to switch between modes. Default/start is true.
		this.RSVP = !this.RSVP;
	}
	public void addComment(String comment){
		this.comment = comment;
	}
	public String getComment(){
		if(this.comment!=null)
			return this.comment;
		else
			return "NONE";
	}
	public String toString(){
		return super.toString() + "\nAddress: " + this.getAddress() + "\nAttending: " + this.RSVP + "\nGuest comment: " + this.getComment();
	}
}
