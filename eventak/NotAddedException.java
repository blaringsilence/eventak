package eventak;

public class NotAddedException extends Exception{
	private static final long serialVersionUID = -3152816804175318656L; // to fix warning

	NotAddedException(String message){
		super(message);
	}
}
