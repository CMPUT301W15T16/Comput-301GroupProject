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
import java.util.Collection;


public class DestList implements Serializable
{
	/**
	 * DestList serializable ID
	 */
	private static final long serialVersionUID = -4491605910312284755L;
	protected ArrayList<Destination> destList = null;
	protected transient ArrayList<Listener> listeners = null;
	
	public DestList(){
		destList = new ArrayList<Destination>();
		listeners = new ArrayList<Listener>();
	}
	
	private ArrayList<Listener> getListeners(){
		if(listeners == null){
			listeners = new ArrayList<Listener>();
		}
		return listeners;
	}
	
	public ArrayList<Destination> getDestList(){
		return destList;
	}

	public Collection<Destination> getDest() {
		
		return destList;
	}
	
	public void addDest(Destination testDestination) {
		destList.add(testDestination);
		notifyListeners();
	}
	
	public void removeDest(Destination testDestination) {
		destList.remove(testDestination);
		notifyListeners();
	}

	private void notifyListeners() {
		
		for (Listener listener : getListeners()) {
			listener.update();			
		}
	}
	

	public void addListener(Listener l) {
		
		getListeners().add(l);
		
	}

	public void removeListener(Listener l) {
		
		getListeners().remove(l);
	}
	

	public int size() {
		
		return destList.size();
	}
	
	public boolean contains(Destination testDestination) {
		
		return destList.contains(testDestination);
	}

	public Destination getPosition(int position) {
		return destList.get(position);
	}
	
	
	
}
