/**
 * this class is a model use for getting destinations
 * 
 * @author Chris Lin
 */

package ca.ualberta.cs.team16app;

import java.io.Serializable;

public class Destination implements Serializable
{

	/**
	 *  claim serializable ID
	 */
	private static final long serialVersionUID = 160313322756243038L;
	private String DestName;
	private String reason;
	
	public Destination(String DestName,String reason){
		super();
		this.DestName = DestName;
		this.reason = reason;
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
