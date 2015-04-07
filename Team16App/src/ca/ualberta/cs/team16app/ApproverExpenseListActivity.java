package ca.ualberta.cs.team16app;

import java.util.ArrayList;
import java.util.Collection;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ApproverExpenseListActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_approver_expense_list);
		Intent intent = getIntent();
		Claim claim = (Claim) intent.getSerializableExtra("claim");
		
		
		
		
		Collection<Expense> expense = claim.getExpense();
		
		 final ArrayList<Expense> list = new ArrayList<Expense>(expense);
		ListView listView = (ListView) findViewById(R.id.expense_listView);
		final ArrayAdapter<Expense> expenseAdapter = new ArrayAdapter<Expense>(this, android.R.layout.simple_list_item_1, list);
	     
		listView.setAdapter(expenseAdapter);

		
		
		
		
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
