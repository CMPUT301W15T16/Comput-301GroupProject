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

import java.io.IOException;


public class DestListController
{
	//based on eclass video by Abram Hindle:https://www.youtube.com/watch?v=uat-8Z6U_Co
	
		private static DestList destList = null;
				
		//Warning: throws a runTimeException
		
		static public DestList getDestList(){
		
			if(destList == null){
			
				try {
				
					destList = DestListManager.getManager().loadDestList();
					
					destList.addListener(new Listener() {
									
						@Override
						
						public void update() {  //updating the destlist to save it
						
							saveDestList();					
							
						}
						
					});
						
				} catch (ClassNotFoundException e) {		
					e.printStackTrace();				
					throw new RuntimeException("Could not deserialize DestList from DestListManager");			
				} catch (IOException e) {
											
					e.printStackTrace();			
					throw new RuntimeException("Could not deserialize DestList from DestListManager");			
				}					
			}			
			return destList;			
		}
		
		
		static public void saveDestList(){
		
			try {
			
				DestListManager.getManager().saveDestList(getDestList());
				
			} catch (IOException e) {
			
				e.printStackTrace();
				
				throw new RuntimeException("Could not deserialize DestList from DestListManager");
				
			}
			
		}		
		
		
		
		public void addDest(Destination dest) {
		
			getDestList().addDest(dest);	
			
		}
		
		
		public void removeDest(Destination dest) {
		
			getDestList().removeDest(dest);
				
		}
}
