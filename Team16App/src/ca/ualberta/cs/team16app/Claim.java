/**
 * This class is a model that represent claims
 * 
 * @author Chris Lin
 */

package ca.ualberta.cs.team16app;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
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
	protected transient ArrayList<Listener> itemListener;
	
	
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
	//protected ArrayList<Expense> items;
	/**
	 * end date of claims
	 */
	protected String enddate;
	/**
	 * description for a claim
	 */
	protected String description;
    /**
     * current status for a claim
     */
    protected Status status = Status.In_Progress;
    protected ArrayList<Expense> ItemList;
    /**
     * list of tags for claims
     */
    private ArrayList<Tag> TagList;
    /**
     * list of destinations for a claim
     */
    private String DestList;
    private String Destination;
	
    
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
    	ItemList = new ArrayList<Expense>();
		TagList = new ArrayList<Tag>();
    	this.claimName = claimName;
  
    	/*
    	this.description = description;
    	this.startdate = startdate;
    	this.enddate = enddate;
    	this.status = status;
    	*/
	}

    public Claim(String Name, String start, String end, String des,String dest,boolean overwrite) {
    	ItemList = new ArrayList<Expense>();
		TagList = new ArrayList<Tag>();
		this.claimName = Name;
		this.startdate = start;
		this.enddate = end;
		this.description = des;
		this.DestList = dest;
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
	 * this method is used for displaying description
	 * 
	 * @return claimName
	 */
	public String getDescription() {
		
		return description;
	}
	
	
	
	/**
	 * set up names for claims 
	 * 
	 * @param claimName
	 */
	public void setDescription(String des){
		this.description = des;
		
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
	public String getEndDate(){
	
		return enddate;
	}
	
	/**
	 * set up end dates for claims
	 * 
	 * @param enddate
	 */
	public void setEndDate(String enddate){
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
	
	
	public ArrayList<Expense> getItemList(){
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
	
	public String getDest(){
		return Destination;
		
	}
	
	public void setDest(String destination){
		this.Destination = destination;
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
		return ItemList.get(position);
	}


	public void addExpense(Expense expense) {
		ItemList.add(expense);
		//notifyListener();
		// TODO Auto-generated method stub
		
	}



	/**
	 * This function returns the id of a story. StoryId = <Title>-<Author> (no
	 * spaces)
	 * 
	 * @return StoryId
	 */
	public String getClaimId() {
		return this.claimName+ "-"
				+ this.startdate;
		}

	private void notifyListener() {
		for (Listener listener : itemListener) {
			listener.update();
		}
	}


	public Collection<Expense> getExpense() {
		// TODO Auto-generated method stub
		return ItemList;
	}


	public void addListener(Listener listener) {
		// TODO Auto-generated method stub
		getListeners().add(listener);
		
	}




	private ArrayList<Listener> getListeners() {
		if (itemListener == null) {
			itemListener = new ArrayList<Listener>();
		}
		return itemListener;
	}


	public void removeExpense(Expense expense) {
		ItemList.remove(expense);
		notifyListener();
		

	}
	
	/**
	 * This function sets the filename automatically and returns it.
	 * 
	 * @return filename the filename
	 */
	public String getFilename() {
		String filename = getClaimId() + ".sav";
		return filename;
	}
	
	/**
	 * This function gets the cache filename and returns it.
	 * 
	 * @return filename the filename
	 */
	public String getCacheFileName(){
		String filename = getClaimId() + ".cache";
		return filename;
	}

}
