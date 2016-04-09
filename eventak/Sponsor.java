package eventak;

public class Sponsor extends Contact {
	public Payment payment = new Payment(); // public to make its methods accessible directly
	public Sponsor(String name) {
		super(name);
	}

	public Sponsor(String name, String phone) {
		super(name, phone);
	}

	public Sponsor(String name, String phone, String email) {
		super(name, phone, email);
	}
	
	public String toString(){
		return super.toString() + "\nPayment Methods: " + this.payment.getMethods();
	}

}
