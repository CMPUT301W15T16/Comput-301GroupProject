package ca.ualberta.cs.team16app.test;

import java.util.Collection;

import ca.ualberta.cs.team16app.Claim;

import ca.ualberta.cs.team16app.ClaimList;
import android.app.Activity;
import junit.framework.TestCase;


public class ClaimListingTest extends TestCase
{
	// Test for US02.01.01
	public void testListClaim(){ 
		ClaimList claimList = new ClaimList(); 
	
		String claimName = "A claim"; 
		String startdate = "Start Date"; 
		String enddate = "End Date"; 
		String Description = "Description"; 
		Claim testClaim = new Claim(claimName); 
		claimList.addClaim(testClaim); 
		Collection<Claim> claims =claimList.getClaims(); 
		assertTrue("claim list size",claims.size() == 1); 
		assertTrue("Test claim not contained",claims.contains(testClaim));
		}
			
	/*
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
			*/
	
	// Test for US01.06.01 
	public void testSaveClaim(){ 
		
		ClaimList claimList = new ClaimList();
		assertTrue("Empty claim list",claimList.size() == 0); 
		} 




}
