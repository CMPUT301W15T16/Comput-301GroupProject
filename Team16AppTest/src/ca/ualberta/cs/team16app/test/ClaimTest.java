package ca.ualberta.cs.team16app.test;

import java.util.Date;


import ca.ualberta.cs.team16app.Claim;
import ca.ualberta.cs.team16app.ClaimInfoActivity;
import ca.ualberta.cs.team16app.ClaimList;
import ca.ualberta.cs.team16app.Destination;
import android.widget.TextView;
import junit.framework.TestCase;


public class ClaimTest extends TestCase
{
	//Chris Lin
			//record claimant's claim Name, start date and end date
			//US01.01.01
			public void testClaim() {
			    String claimName = "test";
			    Date startDate = new Date(111);
			    Date endDate = new Date(222);
			    //Claim claim = new Claim(claimName, startDate, endDate);
			    Claim claim = new Claim(claimName);
			    assertTrue("claimName is not equal", claimName.equals(claim.getName()));
			    //assertTrue("Start date is not equal", startDate.equals(claim.getstartDate()));
			    //assertTrue("End date is not equal", endDate.equals(claim.getendDate()));
			}
			//Chris Lin
			//add destinations and reasons to the claim
			//US01.02.01
			/*
			public void AddDestinationTest() {
			    String des = "Destination";
			    String des2 ="Destination2";
			    String reason1 = "reason1";
			    String reason2 = "reason2";
			    Destination destination = new Destination(des,reason1);
			    Destination destination2 = new Destination(des2,reason2);
			    assertTrue("Destination1 is not equal", destination.getDestination.equals(des));
			    assertTrue("Reason1 is not equal", destination.getReason.equals(reason1));
			    assertTrue("Destination2 is not equal", destination.getDestination.equals(des2));
			    assertTrue("Reason2 is not equal", destination.getReason.equals(reason2));
			    assertFalse("Two Destinations are equal", destination.equals(destination2));
			}
			*/
			
			//Chris Lin
			// view claimant's claim
			//US01.03.01
			public void viewClaimTest() {
			    //ClaimInfoActivity activity = claimInfoActivity();
			    TextView claimName = (TextView)findViewById(R.id.claimName);
			    TextView startDate = (TextView) findViewById(R.id.StartDate);
			    TextView endDate = (TextView) findViewById(R.id.EndDate);
			    //assertNotNull("activity",activity);
			    assertNotNull("ClaimName",claimName);
			    assertNotNull("StartDate",startDate);
			    assertNotNull("EndDate",endDate);
			}
			//Chris Lin
			//edit expense Claim  name, dates 
			//US01.04.01
			public void editClaimTest() {
			    String claimName = "test";
			    Date startDate = new Date(111);
			    Date endDate = new Date(222);
			    String editName ="different";
			    Date diffStart = new Date(333);
			    Date diffEnd = new Date(444);
			   // Claim claim = new Claim(claimName, startDate, endDate);
			    Claim claim = new Claim(claimName);
			    claim.setName(editName);
			    claim.setStartDate(diffStart);
			    claim.setEndDate(diffEnd);
			    assertTrue("Name did not change", claimName.equals(claim.getName()));
			    assertTrue("startDate did not change", startDate.equals(claim.getStartDate()));
			    assertTrue("endDate did not change", endDate.equals(claim.getEndDate()));
			}
			//Chris Lin
			//delete expense claim while creating it
			//US01.05.01
			public void deleteClaimTest() {
			    String claimName = "test";
			    Date startDate = new Date(111);
			    Date endDate = new Date(222);
			    //Claim claim = new Claim(claimName, startDate, endDate);
			    Claim claim = new Claim(claimName);
			    ClaimList claimList = new ClaimList();
			    claimList.addClaim(claim);
			    assertTrue("claim list size isn's big enough",claimList.size() == 1);
			    assertTrue("",claimList.contains(claim));
			    claimList.removeClaim(claim);
			    assertTrue("claim list size isn't small enough",claimList.size() == 0);
			    assertFalse("Test Claim still contained?",claimList.contains(claim));

			}
}
