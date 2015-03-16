/*
 * This file allows the approver to add a comment when returning a file
 * TO DO: connect the approver_comment UI to the rest of the app,
 * make sure that the claim that has been returned does not stay 
 * on the approver's claim list*/



package ca.ualberta.cs.team16app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ApproverCommentsActivity extends Activity {

	private EditText  comments=null;
	private Button saveComment;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_approver_comments);
		
		comments = (EditText)findViewById(R.id.approvercomment);
		saveComment = (Button)findViewById(R.id.saveapprovercomment);
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
	public void saveComment(View v){
		//user has to enter a comment to return a claim
		if(comments.getText().toString().equals(""))
		{
		Toast.makeText(getApplicationContext(), "Please enter a comment",
		Toast.LENGTH_SHORT).show();
		}
		
	 else{
		Toast.makeText(this,"Claim Returned! Proceeding to Claim List", 
				Toast.LENGTH_SHORT).show(); // show message
			
		Intent intent = new Intent(ApproverCommentsActivity.this,
				ApproverClaimListActivity.class);
		startActivity(intent);// move to claim list
		 }
	}
}
