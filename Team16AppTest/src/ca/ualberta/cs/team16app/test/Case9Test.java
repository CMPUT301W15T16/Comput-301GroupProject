package ca.ualberta.cs.team16app.test;

import junit.framework.TestCase;


public class Case9Test extends TestCase
{
	ClaimListController list = new ClaimListController();
	// set all values
	Claim claim = new Claim();
	claim.getStartDate(20121212);
	claim.getEndDate(20130101);
	claim.getStatus("submit");
	claim.getName("approver test");
	claim.addDestination("Beijing");
	claim.getCurrency("EUR");
	claim.getCost(1000);
	claim.addComment("test comment");
	ClaimListController test_con = new ClaimListController();
	test_con.add(claim);
	Claim output = list.get(0);
	
	public void OffLineTest()
	{
		boolean x = isNetworkAvailable();
		if(x == true)S
		{
			assertEquals("claim not equals",claim,output);
		}
		
	}
	// check if the internet is Available
	private boolean isNetworkAvailable() {
	    ConnectivityManager connectivityManager 
	          = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
	    return activeNetworkInfo != null && activeNetworkInfo.isConnected();
	}
}
