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

import android.app.Application;



public class CheckOnline extends Application{

	private boolean IsOnline = false;
	
	/**
	 * Sets the flag according to the given boolean value.
	 * 
	 * @param bool
	 */
	public void setIsOnlineParameter(boolean bool){
		this.IsOnline = bool;
	}
	
	/**
	 * Get the flag state.
	 * 
	 * @return isOnline a boolean value
	 */
	public boolean getIsOnlineParameter(){
		return this.IsOnline;
	}
	
}
