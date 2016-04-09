package eventak;

public class Cost {
	private int total = 0;
	private int people = 0;
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
