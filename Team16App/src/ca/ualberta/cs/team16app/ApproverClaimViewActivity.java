/*
 * This file should allow the approver to see the claim details and 
 * approve or return the claim. 
 * The functions mentioned above are still being worked on. Also,
 * to do: approver should be transferred to claim page or comment page
 * depending on the option clicked */


package ca.ualberta.cs.team16app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ApproverClaimViewActivity extends Activity {

	private Button returnClaim;
	private Button approveClaim;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_approver_claim_view);
		
		returnClaim = (Button)findViewById(R.id.returnbutton);
		approveClaim = (Button)findViewById(R.id.approvebutton);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.approver_claim_view, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void returnClaim(View v){
		Toast.makeText(this,"Returning claim: comment", Toast.LENGTH_SHORT).show(); // show message
		
		Intent intent = new Intent(ApproverClaimViewActivity.this,ApproverCommentsActivity.class);//
		startActivity(intent);// go to approver comments
	}
}
