package ca.ualberta.cs.team16app;

import java.util.Date;



public class Claim
{
	protected String claimName;
	protected Date startdate = new Date();
	protected Date enddate = new Date();
	protected String description;
    protected String status;
   
	public Claim(String claimName) {
		this.claimName = claimName;
	}

	public String getName() {
	
		return this.claimName;
	}
	
	public Date getStartDate(){
		return this.startdate;
	}

	public Date getEndDate(){
	
		return this.enddate;
	}
	public String toString(){
		return getName();
		
	}
	
	public boolean equals(Object compareClaim){
		
		if(compareClaim != null && compareClaim.getClass() == this.getClass()){
			return this.equals((Claim)compareClaim);
		}
		else{
		return false;
		}
		
	}
	public boolean equals(Claim compareClaim){
		if(compareClaim == null){
			return false;
		}
		return getName().equals(compareClaim.getName());
	}
	public int hashCode(){
		return ("Claim"+getName()).hashCode();
	}
}
