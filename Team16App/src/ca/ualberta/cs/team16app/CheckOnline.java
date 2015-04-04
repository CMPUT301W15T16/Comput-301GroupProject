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
