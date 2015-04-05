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
