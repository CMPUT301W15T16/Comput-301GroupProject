// written by Di Meng 
package com.example.team16app.test;

import junit.framework.TestCase;
import android.widget.Toast;

public class Case7 extends TestCase {
	//US07.01.01
		public void submitExpenseTest() {
		//assuming Claim claim already has values in it
			claim.submit();
			claim.disableEdit();
			claim.setCurrentStatus("Submitted");
		
			assertTrue("Status not equal to submitted", claim.getCurrentStatus == "Submitted");
			assertFalse("Changes can still be made", findViewById() instanceof EditText);
		}
		/*This code below is adapted from http://developer.android.com/guide/topics/ui/controls/text.html*/
		EditText editText = (EditText) findViewById(R.id.search);
		editText.setOnEditorActionListener(new OnEditorActionListener() {
		    @Override
		    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
		        boolean handled = false;
		        if (actionId == EditorInfo.IME_ACTION_SUBMIT) {
		            disableEdit(); /*check this page for view only text: 
		            http://developer.android.com/reference/android/widget/TextView.html */
		            handled = true;
		        }
		        return handled;
		    }
		});
		
		
		//US07.02.01
		public void missingValuesTest() {
			if (submit == true){
				for (int i = 0; i < expenseList.size; i++){
					if (expenseList.getItem(i) == null){
						Toast.makeText(this, "Missing values!", Toast.LENGTH_LONG).show();
				}
			}
			}
			
			for (int i = 0; i < expenseList.size; i++){
				assertNotNull("There is an item in expenseList that is null", getItem(i));
			}
		}
		
		//US07.03.01
		public void returnedClaimTest() {
			if (claim.getApproverStatus == "Return"){
				claim.setCurrentStatus("Returned");
			}
			
			assertTrue("Claim status is not equal to approver's updated status", claim.getCurrentStatus.equals(claim.getApproverStatus));
			assertTrue("Changes can still not be made", findViewById() instanceof EditText);
		}
		
		//US07.04.01
		public void approvedClaimTest() {
			if (claim.getApproverStatus == "Approved"){
				claim.setCurrentStatus("Approved");
			}
			
			assertTrue("Claim status not updated", claim.getCurrentStatus.equals(claim.getApproverStatus));
			assertFalse("Still editable", findViewById() instanceof EditText);
		}
		
		//US07.05.01
		public void approverCommentsTest() {
			TextView approverName = (TextView) activity.findViewById(R.id.approverName);
			TextView approverComments = (TextView) activity.findViewById(R.id.approverComments);
			
			assertNotNull("ApproverName",approverName);
			assertNotNull("ApproverComments",approverComments);
		}
}
}
