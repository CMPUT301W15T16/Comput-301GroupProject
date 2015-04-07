package ca.ualberta.cs.team16app;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

public class ApproverExpenseListActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_approver_expense_list);
		Intent intent = getIntent();
		Claim claim = (Claim) intent.getSerializableExtra("claim");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.approver_expense_list, menu);
		return true;
	}
	
	public void backToClaimList(View v){
		Toast.makeText(this,"back to the Claim list", Toast.LENGTH_SHORT).show(); // show message
		
		Intent intent = new Intent(ApproverExpenseListActivity.this,ApproverClaimListActivity.class);
		startActivity(intent);
		}

}
