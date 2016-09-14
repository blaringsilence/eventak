package application;

/**
 * A glorified contact. Basically, a contact with payment methods.
 * Access payment options (addMethod, etc) directly as this.payment is public. 
 * Any methods regarding payment here would've been redundant/wouldn't have added anything to class Payment.
 * Payment was made into a separate class because it could be also used elsewhere.
 * */

public class Sponsor extends Contact {
	private static final long serialVersionUID = -2264765760565476571L;
	public String paymentMethod;
	public String paymentDetail; // public to make its methods accessible directly
	public Sponsor(String name) {
		super(name);
	}

	public Sponsor(String name, String phone) {
		super(name, phone);
	}

	public Sponsor(String name, String phone, String email) {
		super(name, phone, email);
	}
	public void setPaymentMethod(String str){
		this.paymentMethod = str;
	}
	public void setPaymentDetail(String str){
		this.paymentDetail = str;
	}
	public String getPaymentMethod(){
		if(this.paymentMethod!=null)
			return this.paymentMethod;
		else
			return "NONE";
	}
	public String getPaymentDetail(){
		if(this.paymentDetail!=null)
			return this.paymentDetail;
		else
			return "NONE";
	}
	public String toString(){
		return super.toString() + "\nPayment Method: " + this.paymentMethod + "\t" + this.paymentDetail;
	}

}
