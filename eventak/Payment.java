package eventak;

import java.util.ArrayList;

public class Payment{
	private ArrayList<ArrayList<String>> details = new ArrayList<ArrayList<String>>(); 
	/* *
	 * A 2D ArrayList, each row is the name of the method of payment, and the details
	 * for example: 'Visa Card' '9181371678361523232' or 'Check' and nothing else
	 * */
	public void addMethod(String name){
		ArrayList<String> temp = new ArrayList<String>();
		temp.add(name);
		this.details.add(temp);
	}
	public void addMethod(String name, String detail){
		ArrayList<String> temp = new ArrayList<String>();
		temp.add(name);
		temp.add(detail);
		this.details.add(temp);
		
	}
	public String getMethods(){
		if(this.details.isEmpty()){ // if no methods added
			return "No payment methods have been added.";
		}
		else{
			String met = "\n";
			for(int i=0; i<this.details.size(); i++){
				for(int j=0; j<this.details.get(i).size(); j++){
					met += (this.details.get(i).get(j) + "\t");
				}
				met += "\n";
			}
			return met;
		}
	}
	
}
