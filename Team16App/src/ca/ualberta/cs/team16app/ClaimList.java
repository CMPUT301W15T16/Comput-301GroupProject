package ca.ualberta.cs.team16app;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;



public class ClaimList implements Serializable
{
	/**
	 * ClaimList serialization ID
	 */
	private static final long serialVersionUID = -7219682629357177429L;
	protected ArrayList<Claim> claimList = null;
	protected transient ArrayList<Listener> listeners = null;
	
	public ClaimList(){
		claimList = new ArrayList<Claim>();
		listeners = new ArrayList<Listener>();
	}
	
	private ArrayList<Listener> getListeners(){
		if(listeners == null){
			listeners = new ArrayList<Listener>();
		}
		return listeners;
	}
	
	public ArrayList<Claim> getClaimList(){
		return claimList;
	}
	

	public Collection<Claim> getClaims() {
		
		return claimList;
	}
	
	public void addClaim(Claim testClaim) {
		claimList.add(testClaim);
		notifyListeners();
	}
	
	public void removeClaim(Claim testClaim) {
		claimList.remove(testClaim);
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
		
		return claimList.size();
	}
	
	public boolean contains(Claim testClaim) {
		
		return claimList.contains(testClaim);
	}
}
