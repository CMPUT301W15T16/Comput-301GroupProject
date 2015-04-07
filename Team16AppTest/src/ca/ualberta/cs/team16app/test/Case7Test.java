package ca.ualberta.cs.team16app.test;

import java.security.InvalidParameterException;

import android.app.Activity;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import ca.ualberta.cs.team16app.Claim;
import ca.ualberta.cs.team16app.ClaimList;
import ca.ualberta.cs.team16app.Expense;
import ca.ualberta.cs.team16app.Claim.Status;
import ca.ualberta.cs.team16app.ExpenseList;
import junit.framework.TestCase;


public class Case7Test extends TestCase
{

	//Written by Omoyeni 
	//US07.01.01
	//Test that claim has been submitted
			public void testSubmitExpense() {
				Claim claim = new Claim("Claim1");
				Status status = Status.Submitted;
				
				claim.setStartDate("20121212");
				claim.setEndDate("20130101");
				claim.setDest("Laos");
				claim.setDescription("test comment");
				ClaimList claimlist = new ClaimList();
				claimlist.addClaim(claim);

				claim.setStatus(status);
			
				assertTrue("Status not equal to submitted", claim.getStatus().equals(status));
			}
			
			
			//US07.02.01
			//Test that claim info (that is, expense) is not missing any values 
			// ---TOAST ADDED TO APP FOR VISUAL WARNING---

			
			//US07.03.01
			//Test that claim has been returned
			public void testReturnedClaim() {
				Claim claim = new Claim("Claim1");
				Status status = Status.Submitted;
				
				claim.setStartDate("20121212");
				claim.setEndDate("20130101");
				claim.setDest("Laos");
				claim.setDescription("test comment");
				
				claim.setStatus(status);
				
				Status status1 = Status.Returned;
				claim.setStatus(status1);
				
				assertTrue("Claim status is not equal to approver's updated status",
						claim.getStatus().equals(status1));
				//assertTrue("Changes can still not be made", findViewById() instanceof EditText);
			}
			
			//US07.04.01
			//Test that approved claim has been updated as approved
			public void approvedClaimTest() {
				Claim claim = new Claim("Claim1");
				Status status = Status.Submitted;
				claim.setStatus(status);
				
				claim.setStartDate("20121212");
				claim.setEndDate("20130101");
				claim.setDest("Laos");
				claim.setDescription("test comment");
				ClaimList claimlist = new ClaimList();
				claimlist.addClaim(claim);
				
				
				Status status2 = Status.Approved;
				claim.setStatus(status2);
						
				assertTrue("Claim status not updated", claim.getStatus().equals(status2));
				//assertFalse("Still editable", findViewById() instanceof EditText);
			}
			
		/*	//US07.05.01
			//Test that approver can comment on returned claim
			public void testApproverComments(){
				Claim claim = new Claim("Claim1");
				Status status = Status.Returned;
				
				claim.setStartDate("20121212");
				claim.setEndDate("20130101");
				claim.setDest("Laos");
				claim.setDescription("test comment");
				ClaimList claimlist = new ClaimList();
				claimlist.addClaim(claim);
				
				if ( claim.getStatus()== status){
					TextView approverName = (TextView) Activity.findViewById(R.id.approverName);
					TextView approverComments = (TextView) Activity.findViewById(R.id.approverComments);
					
					assertNotNull("ApproverName is empty",approverName);
					if (approverName != null){
						assertEquals("ApproverName is not correct",approverComments);}
					assertNotNull("ApproverComment is empty",approverComments);
					if (approverComments != null){
						assertEquals("ApproverComments is not correct",approverComments);}		
	
				}

			}*/

}
