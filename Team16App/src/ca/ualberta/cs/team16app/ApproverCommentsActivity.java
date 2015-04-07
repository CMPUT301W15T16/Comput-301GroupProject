/*
 * This file allows the approver to add a comment when returning a file
 * TO DO: connect the approver_comment UI to the rest of the app,
 * make sure that the claim that has been returned does not stay 
 * on the approver's claim list*/


package ca.ualberta.cs.team16app;

import java.io.IOException;

import ca.ualberta.cs.team16app.Claim.Status;

import ca.ualberta.cs.team16app.elasitcSearch.ESClient;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * 
 * @author Team16
 *
 */

/**
 * 
 * This file allows the approver to add a comment when returning a file
 * TO DO: connect the approver_comment UI to the rest of the app,
 * make sure that the claim that has been returned does not stay 
 * on the approver's claim list
 *
 */
public class ApproverCommentsActivity extends Activity {

	private EditText  comments=null;
	private EditText approverName=null;
	private Button saveCommentButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_approver_comments);
		
		comments = (EditText)findViewById(R.id.approvercomment);
		approverName = (EditText)findViewById(R.id.approverNameReturns);
		saveCommentButton = (Button)findViewById(R.id.cancel);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.approver_comments, menu);
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
	
	//click save button to return to claim list
	public void returned(View v){
		Intent intent1 = getIntent();
		Claim claim = (Claim) intent1.getSerializableExtra("claim");
		
		//user has to enter a comment to return a claim
		if(comments.getText().toString().equals("")
				||approverName.getText().toString().equals(""))
		{
		Toast.makeText(getApplicationContext(), "Make sure both entries are filled",
		Toast.LENGTH_SHORT).show();
		}
		
	 else{
		Toast.makeText(this,"Commented Saved and Claim Returned!", 
				Toast.LENGTH_SHORT).show(); // show message
		
		claim.approverName = approverName.getText().toString();
		claim.comment = comments.getText().toString();
		claim.setST(3); 
		pushClaim(claim);
		Intent intent = new Intent(ApproverCommentsActivity.this,
				ApproverClaimListActivity.class);
		//Claim.Status status = Status.Returned;   //change status of claim
		startActivity(intent);// move to claim list
		 }
	}
	
	
	
	
	public void approve(View v){
		Intent intent1 = getIntent();
		Claim claim = (Claim) intent1.getSerializableExtra("claim");
		
		//user has to enter a comment to return a claim
		if(comments.getText().toString().equals("")
				||approverName.getText().toString().equals(""))
		{
		Toast.makeText(getApplicationContext(), "Make sure both entries are filled",
		Toast.LENGTH_SHORT).show();
		}
		
	 else{
		Toast.makeText(this,"Commented Saved and Claim Approved!", 
				Toast.LENGTH_SHORT).show(); // show message
		
		claim.approverName = approverName.getText().toString();
		claim.comment = comments.getText().toString();
		claim.setST(2); 
		pushClaim(claim);
		Intent intent = new Intent(ApproverCommentsActivity.this,
				ApproverClaimListActivity.class);
		//Claim.Status status = Status.Returned;   //change status of claim
		startActivity(intent);// move to claim list
		 }
	}
	
	public void backToClaimList(View v){
		Toast.makeText(this,"canceled and go back to the Claim list", Toast.LENGTH_SHORT).show(); // show message
		
		Intent intent = new Intent(ApproverCommentsActivity.this,ApproverClaimListActivity.class);
		startActivity(intent);
		}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * This function publishes a claim on the 
	 * WebServer.
	 * 
	 * 
	 */
	public void pushClaim(Claim claimClicked) {
		if (isNetworkConnected()) {
		//Since we need to give the HTTP client some time
		//to publish the story, we need to use AsyncTasks.
		new pushClaimTask(claimClicked).execute();
		}
		else {
			// Else Display Message that Internet is not available and
			createAlertDialog();
		}
	}
	
	
	private boolean isNetworkConnected() {
		ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		return (cm.getActiveNetworkInfo() != null);
	}
	
	private void createAlertDialog() {
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

		// set title
		alertDialogBuilder.setTitle("Network Error!");

		// set dialog message
		alertDialogBuilder
				.setMessage(
						"There's no network connection! Cannot Push a claim.")
				.setCancelable(false)
				.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						// close current activity and go back to Local Library.

					}
				});
		// create alert dialog
		AlertDialog alertDialog = alertDialogBuilder.create();

		// show it
		alertDialog.show();

	}
	
	
	
	
	private class pushClaimTask extends AsyncTask<String, String, String> {
		Claim claim;
		public pushClaimTask(Claim claimClicked) {
			this.claim = claimClicked;
		}
		@Override
		protected String doInBackground(String... arg0) {
			ESClient client = new ESClient();
			try {
				client.deleteClaim(this.claim);
				client.insertClaim(this.claim);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
		/**
		 * Toast message appears after the claim is published successfully.
		 */
		protected void onPostExecute(String result) {
			Toast.makeText(ApproverCommentsActivity.this,
					"Pushed " + this.claim.getName(), Toast.LENGTH_LONG)
					.show();
		}
	}
	
	
}
