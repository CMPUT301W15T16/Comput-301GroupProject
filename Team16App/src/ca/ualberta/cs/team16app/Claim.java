/**
 * This class is a model that represent claims
 * 
 * @author Chris Lin
 */

package ca.ualberta.cs.team16app;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;



import android.content.ClipData.Item;
import android.nfc.Tag;



public class Claim implements Serializable
{
	/**
	 * Claim serializable ID
	 */
	private static final long serialVersionUID = -3335827024534601528L;
	
	/**
	 * claim Name
	 */
	protected String claimName;
	
	
	/**
	 * statuses for expense claims
	 *
	 */
	public enum Status{
		/**
		 * default status for expense claims
		 */
		In_Progress,
		
		/**
		 * claims that are submitted, changes are not allowed
		 */
		Submitted,
		
		/**
		 * subitted claim is returned
		 */
		Returned,
		
		/**
		 * claims are approved, no changes are allowed
		 */
		Approved
		
	}
	/**
	 * start date of claims
	 */
	protected String startdate;
	
	/**
	 * list of expense items
	 */
	protected ArrayList<Expense> items;
	/**
	 * end date of claims
	 */
	protected Date enddate;
	/**
	 * description for a claim
	 */
	protected String description;
    /**
     * current status for a claim
     */
    protected Status status = Status.In_Progress;
    private ArrayList<Item> ItemList;
    /**
     * list of tags for claims
     */
    private ArrayList<Tag> TagList;
    /**
     * list of destinations for a claim
     */
    private String DestList;
	
    
    // public Claim(String claimName, String description, Date startdate, Date enddate, Status status)
    /**
     * general construction for claims
     * 
     * @param claimName
     * @param description
     * @param startdate
     * @param enddate
     * @param status
     */
    public Claim(String claimName) {
    	ItemList = new ArrayList<Item>();
		TagList = new ArrayList<Tag>();
    	this.claimName = claimName;
  
    	/*
    	this.description = description;
    	this.startdate = startdate;
    	this.enddate = enddate;
    	this.status = status;
    	*/
	}

    
	/**
	 * this method is used for displaying names
	 * 
	 * @return claimName
	 */
	public String getName() {
	
		return claimName;
	}
	
	/**
	 * set up names for claims 
	 * 
	 * @param claimName
	 */
	public void setName(String claimName){
		this.claimName = claimName;
		
	}
	
	/**
	 * use for dispplaying start date
	 * 
	 * @return startdate
	 */
	public String getStartDate(){
		return startdate;
	}
	
	/**
	 * set up start date for claims
	 * 
	 * @param string
	 */
	public void setStartDate(String string){
		this.startdate = string;
		
	}

	/**
	 * display end date for claims
	 * 
	 * @return enddate
	 */
	public Date getEndDate(){
	
		return enddate;
	}
	
	/**
	 * set up end dates for claims
	 * 
	 * @param enddate
	 */
	public void setEndDate(Date enddate){
		this.enddate = enddate;
		
	}
	
	/**
	 * display status for claims
	 * 
	 * @return status
	 */
	public Status getStatus(){
		return status;
		
	}
	
	/**
	 * set up status for claims
	 * 
	 * @param status
	 */
	public void setStatus(Status status){
		this.status = status;
	}

	public String toString(){
		return getName();
		
	}
	
	
	public ArrayList<Item> getItemList(){
		return ItemList;
	}
	
	/**
	 * display a list of destination
	 * 
	 * @return DestList
	 */
	public String getDestList(){
		return DestList;
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


	public void setDestination(String string) {
		// TODO Auto-generated method stub
		this.DestList = string;
		
	}


	

	public Expense getPosition(int position) {
		return items.get(position);
	}
	

}
