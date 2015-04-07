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
 * This class is a model use for creating tag list
 * 
 * @author Qi Tan
 */

package ca.ualberta.cs.team16app;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import android.content.Context;


public class TagList implements Serializable
{
	/**
	 * This is a constrctor for Taglist
	 * gets and sets
	 */
	private static final long serialVersionUID = -8486735976955389878L;
	/**
	 * ClaimList serialization ID
	 */
	protected ArrayList<Tag> tagList = null;
	protected transient ArrayList<Listener> listeners = null;
	public TagList(){
		tagList = new ArrayList<Tag>();
		listeners = new ArrayList<Listener>();
	}
	
	private ArrayList<Listener> getListeners(){
		if(listeners == null){
			listeners = new ArrayList<Listener>();
		}
		return listeners;
	}
	
	public ArrayList<Tag> getTagList(){
		return tagList;
	}
	

	public Collection<Tag> getTags() {
		
		return tagList;
	}
	
	public void addTag(Tag testTag) {
		tagList.add(testTag);
		notifyListeners();
	}
	public void removeTag(Tag testTag) {
		tagList.remove(testTag);
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
		
		return tagList.size();
	}
	
	public boolean contains(Tag testTag) {
		
		return tagList.contains(testTag);
	}

	public Tag getTagname(int position) {
		return tagList.get(position);
	}

	

	
	
}
