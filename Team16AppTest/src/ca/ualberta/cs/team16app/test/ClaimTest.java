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
		String claimName = "Travel";
		String startDate ="1993-06-13";
		String endDate = "End Date";
		String des = "yoyoyoy";
		String dest = "Japan";
		   //Claim claim = new Claim(claimName, startDate, endDate);
		   Claim claim = new Claim(claimName,startDate,endDate, des, dest, false);
		   assertTrue("claimName is not equal", claimName.equals(claim.getName()));
		   assertTrue("Start date is not equal", startDate.equals(claim.getStartDate()));
		   assertTrue("End date is not equal", endDate.equals(claim.getEndDate()));
		}
		//Chris Lin
		//add destinations and reasons to the claim
		//US01.02.01
		public void testAddDestination() {
		   String des = "Destination";
		   String des2 ="Destination2";
		   String reason1 = "reason1";
		   String reason2 = "reason2";
		   Destination destination = new Destination(des,reason1);
		   Destination destination2 = new Destination(des2,reason2);
		   assertTrue("Destination1 is not equal", des.equals(destination.getDestName()));
		   assertTrue("Reason1 is not equal", reason1.equals(destination.getReason()));
		   assertTrue("Destination2 is not equal", des2.equals(destination2.getDestName()));
		   assertTrue("Reason2 is not equal", reason2.equals(destination2.getReason()));
		  assertFalse("Two Destinations are equal", destination.equals(destination2));
		}
		/*
		//Chris Lin
		// view claimant's claim
		//US01.03.01
		public void testviewClaim() {
		   ClaimListActivity activity = new ClaimListActivity();
		   TextView claimName = (TextView)findViewById(R.id.claimName);
		   TextView startDate = (TextView) findViewById(R.id.StartDate);
		   TextView endDate = (TextView) findViewById(R.id.EndDate);
		   //assertNotNull("activity",activity);
		   assertNotNull("ClaimName",claimName);
		   assertNotNull("StartDate",startDate);
		   assertNotNull("EndDate",endDate);
		}
		*/
		//Chris Lin
		//edit expense Claim  name, dates 
		//US01.04.01
		public void testeditClaimt() {
		   String claimName = "test";
		   String startDate = "1111-02-11";
		   String endDate ="1993-05-16";
		   String des= "yoyoyoy";
		
		   String dest = "Japan";
		   String editName ="different";
		   String diffStart = "1112-11-11";
		   String diffEnd ="1994-02-11";
		  // Claim claim = new Claim(claimName, startDate, endDate);
		   Claim claim = new Claim(claimName,startDate,endDate, des, dest, false);
		   claim.setName(editName);
		   claim.setStartDate(diffStart);
		   claim.setEndDate(diffEnd);
		   assertTrue("Name did not change", editName.equals(claim.getName()));
		   assertTrue("startDate did not change", diffStart.equals(claim.getStartDate()));
		   assertTrue("endDate did not change", diffEnd.equals(claim.getEndDate()));
		}
		//Chris Lin
		//delete expense claim while creating it
		//US01.05.01
		public void testdeleteClaim() {
		ClaimList claimList = new ClaimList();
		String claimName = "A Claim";
		String startdate = "Start Date";
		String enddate = "End Date";
		String Description = "Description";
		Claim testClaim = new Claim(claimName);
		claimList.addClaim(testClaim);
		assertTrue("claim list size isn's big enough",claimList.size() == 1);
		assertTrue("",claimList.contains(testClaim));
		claimList.removeClaim(testClaim);

		assertTrue("claim list size isn't small enough",claimList.size() == 0);
		assertFalse("Test Claim still contained?",claimList.contains(testClaim));

		}
}
