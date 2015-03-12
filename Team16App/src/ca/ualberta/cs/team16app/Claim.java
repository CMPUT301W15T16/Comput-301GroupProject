package ca.ualberta.cs.team16app;

import java.util.ArrayList;
import java.util.Date;

import android.content.ClipData.Item;
import android.nfc.Tag;



public class Claim
{
	protected String claimName;
	protected Date startdate;
	protected Date enddate;
	protected String description;
    protected String status;
    private ArrayList<Item> ItemList;
    private ArrayList<Tag> TagList;
    private ArrayList<Destination> DestList;
	
    
    public Claim(String claimName) {
    	super();
		ItemList = new ArrayList<Item>();
		TagList = new ArrayList<Tag>();
    	this.claimName = claimName;
	}

	public String getName() {
	
		return this.claimName;
	}
	
	public void setName(String claimName){
		this.claimName = claimName;
		
	}
	
	public Date getStartDate(){
		return startdate;
	}
	
	public void setStartDate(Date startdate){
		this.startdate = startdate;
		
	}

	public Date getEndDate(){
	
		return enddate;
	}
	
	public void setEndDate(Date enddate){
		this.enddate = enddate;
		
	}
	
	public String getStatus(){
		return status;
		
	}
	
	public void setStatus(String status){
		this.status = status;
	}
	public String toString(){
		return getName();
		
	}
	
	public ArrayList<Item> getItemList(){
		return ItemList;
	}
	
	public ArrayList<Destination> getDestList(){
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
	
}
