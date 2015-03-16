/**
 * this class is a model use for getting destinations
 * 
 * @author Chris Lin
 */

package ca.ualberta.cs.team16app;

public class Destination {

	private String DestName;
	private String reason;
	
	public Destination(String DestName){
		super();
		this.DestName = DestName;
	}
	
	public String getDestName(){
		return DestName;
		
	}
	
	public void setDestName(String DestName){
		this.DestName = DestName;
		
	}
	
	public String getReason(){
		return reason;
	}
	
	public void setReason(String reason){
		this.reason = reason;
	}
}
