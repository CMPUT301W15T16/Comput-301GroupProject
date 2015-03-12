package ca.ualberta.cs.team16app.test;

import android.app.Activity;
import junit.framework.TestCase;


public class ClaimListingTest extends TestCase
{
	// Test for US02.01.01
			public void ListClaimTest(){
			    ClaimList claimlist = new ClaimList();
			    int claimcount = claimlist.getClaimCount();
			    Claim claim = new Claim(null);
			    claimlist.getclaim(claim);
			    assertTrue("List works", claim.equals(claimlist.getclaim(claimcount)));
			}
			
			// Test for US02.02.01
			public void ListSortTest(){
			    ClaimList claimlist = new ClaimList();
			    int claimcount = claimlist.getClaimCount();
			    if (claimcount > 1) {
			        Date date1 = claimlist.getclaim(0).getStartDate();
			        Date date2 = claimlist.getclaim(1).getStartDate();
			        for (int i=1; i<claimcount; i++) {
			            assertTrue("Claim sorts right", date1.before(date2));
			            date1 = date2;
			            date2 = claimlist.getclaim(i).getStartDate();
			        }
			    }
			}
			
			public class ClaimsOverall extends TestCase {
				// Test for US01.06.01
				public void SaveClaimTest(){
				    Activity activity = getActivity();
				    Claim claim = new Claim();S
				    claim.getName("A");
				    claim.setStartDate(new Date(123));
				    claim.setEndDate(new Date(321));
				    claim.addDestination("B");
				    claim.setReason("C");
				    activity.finish();
				    activity.recreate();
				    assertEquals("Name saved", claim.getName(), "A");
				    assertEquals("Start date saved", claim.getStartDate(), new Date(123));
				    assertEquals("End date saved", claim.getEndDate(), new Date(321));
				    assertEquals("Destination saved", claim.getDestination(), "B");
				    assertEquals("Reason saved", claim.getReason(), "C");
				}
			}


}
