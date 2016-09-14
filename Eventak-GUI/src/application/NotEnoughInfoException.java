package application;

public class NotEnoughInfoException extends RuntimeException{
	private static final long serialVersionUID = 1233690699711157070L;
	NotEnoughInfoException(String message){
		super(message);
	}

}
