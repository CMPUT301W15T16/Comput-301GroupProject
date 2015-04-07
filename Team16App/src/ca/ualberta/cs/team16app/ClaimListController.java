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
/**
 * this class is a Claim controller  use for telling models and views to update information 
 * for claims an claimlist whenever user make a change
 * 
 * @author Chris Lin
 */

package ca.ualberta.cs.team16app;

import java.io.IOException;

public class ClaimListController {

	//based on eclass video by Abram Hindle:https://www.youtube.com/watch?v=uat-8Z6U_Co
			
	private static ClaimList claimList = null;
			
	//Warning: throws a runTimeException
	
	static public ClaimList getClaimList(){
	
		if(claimList == null){
		
			try {
			
				claimList = ClaimListManager.getManager().loadClaimList();
				
				claimList.addListener(new Listener() {
								
					@Override
					
					public void update() {  //updating the claimlist to save it
					
						saveClaimList();					
						
					}
					
				});
					
			} catch (ClassNotFoundException e) {		
				e.printStackTrace();				
				throw new RuntimeException("Could not deserialize ClaimList from ClaimListManager");			
			} catch (IOException e) {
										
				e.printStackTrace();			
				throw new RuntimeException("Could not deserialize ClaimList from ClaimListManager");			
			}					
		}			
		return claimList;			
	}
	
	
	static public void saveClaimList(){
	
		try {
		
			ClaimListManager.getManager().saveClaimList(getClaimList());
			
		} catch (IOException e) {
		
			e.printStackTrace();
			
			throw new RuntimeException("Could not deserialize ClaimList from ClaimListManager");
			
		}
		
	}		
	
	
	
	public void addClaim(Claim claim) {
	
		getClaimList().addClaim(claim);	
		
	}
	
	
	public void removeClaim(Claim claim) {
	
		getClaimList().removeClaim(claim);
			
	}
}


