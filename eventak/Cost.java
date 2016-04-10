package eventak;

import java.io.Serializable;

/**
 * Cost. Covers budget, etc.
 * Made instead of using an int for budget because calculations for cost/person / estimations/etc would be needed.
 * Has:
 * - total: total budget
 * - people: number of people (can also be used for any partition of things. Like number of booths, etc)
 * */

public class Cost implements Serializable{
	
	private static final long serialVersionUID = -1764428783520950965L;
	private int total = 0;
	private int people = 0;
	Cost(){
		// for serialization purposes
	}
	Cost(int cost){
		this.total = cost;
	}
	Cost(int cost, int numberOfPeople){
		this.people = numberOfPeople;
		this.total = cost;
	}
	public void setTotal(int total){
		this.total = total;
	}
	public int getCostPerPerson() throws NotEnoughInfoException{
		if(this.people!=0){
			return this.total/this.people;
		}
		else
			throw new NotEnoughInfoException("Number of people not specified.");
	}
	public int getTotal(){
		return this.total;
	}
	public void setNumberOfPeople(int people){
		this.people = people;
	}
	public void addPeople(int add){
		this.people+=add;
	}
	
}
