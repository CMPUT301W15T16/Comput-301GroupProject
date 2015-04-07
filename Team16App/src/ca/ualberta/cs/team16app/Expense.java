
/**
 * Team16App: travel expense tracking application
 * Copyright (C) 2015 peijen  Chris Lin 
 * dmeng  Di Meng 
 * tshen
 * qtan  Qi Tan 
 * yuentung  
 * omoyeni  Omoyeni Adeyemo 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.

 */

package ca.ualberta.cs.team16app;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;





public class Expense implements Serializable {

	
	/**
	 * Expense serializable ID
	 */
	private static final long serialVersionUID = 2342770718616942378L;
	protected String expenseName;
	protected String category;
	private String spend;
	private String description;
	private String currency;
	protected Date date = new Date();	
    protected String status;
    protected ArrayList<Expense> items;
    protected transient ArrayList<Listener> itemListener;
    private  ArrayList<Tag> tagList;
    
	public Expense(String expenseName, String cate, String spend, String cur,String des) {
		this.expenseName = expenseName;
		this.category = cate;
		this.spend = spend;
		this.currency = cur;
		this.description = des;
		
	}

	public String getName() {
	
		return this.expenseName;
	}
	
	public void setName(String name) {
		this.expenseName = name;
	}
	
	public Date getDate(){
		return this.date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	

	public String toString(){
		return getName();
		
	}
	

	public boolean equals(Object compareExpense){
		
		if(compareExpense != null && compareExpense.getClass() == this.getClass()){
			return this.equals((Expense)compareExpense);
		}
		else{
		return false;
		}
		
	}
	public boolean equals(Expense compareExpense){
		if(compareExpense == null){
			return false;
		}
		return getName().equals(compareExpense.getName());
	}
	
	
	public int hashCode(){
		return ("Expense"+getName()).hashCode();
	}

	public void removeExpense(Expense expense) {
			items.remove(expense);
			notifyListener();
	}

	private void notifyListener() {
		for (Listener listener : itemListener) {
			listener.update();
		}
	}

	
	public void setCategory(String cate) {
		this.category = cate;
	}
	 

	public String getSpend() {
		// TODO Auto-generated method stub
		return this.spend;
	}
	
	public void setSpend(String cost) {
		this.spend = cost;
	}
	

	public String getCurrency() {
		return this.currency;
	}
	
	public void setCurrency(String cur) {
		this.currency = cur;
	}
	

	public String getDescription() {
		// TODO Auto-generated method stub
		return this.description;
	}
	
	
	public void setDescription(String des) {
		this.description = des;
	}
	
	// get/set tags
	public ArrayList<Tag> getTagList() {
		return tagList;
	}
	public void setTags(ArrayList<Tag> tagList) {
		this.tagList = tagList;
	}

	public String getCategory() {
		// TODO Auto-generated method stub
		return this.category;
	}



}
