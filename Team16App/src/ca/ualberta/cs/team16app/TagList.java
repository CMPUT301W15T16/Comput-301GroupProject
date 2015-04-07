/**
 * This class is a model use for creating tag list
 * 
 */

package ca.ualberta.cs.team16app;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import android.content.Context;


public class TagList implements Serializable
{
	/**
	 * 
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
