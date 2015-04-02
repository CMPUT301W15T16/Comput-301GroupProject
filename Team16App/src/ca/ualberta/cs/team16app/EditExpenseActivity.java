package ca.ualberta.cs.team16app;

// wittten by Di Meng 1358503

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class EditExpenseActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_expense);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.edit_expense, menu);
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
	
	public void editExpense(View v){
		ClaimListManager.initManager(this.getApplicationContext());
		Bundle extras = getIntent().getExtras();
		int first = extras.getInt("claimPos");
		int pos = extras.getInt("pos");
		Toast.makeText(this,"edit a expense", Toast.LENGTH_SHORT).show(); // show message
		ClaimListController cm = new ClaimListController();
		EditText textview = (EditText) findViewById(R.id.name);
		EditText startdateView = (EditText) findViewById(R.id.expenseDate);
		EditText categoryView = (EditText) findViewById(R.id.Category);
		EditText descriptView = (EditText) findViewById(R.id.expenseDescription);
		EditText costView = (EditText) findViewById(R.id.amountSpent);
		EditText currencyView = (EditText) findViewById(R.id.currency);
		
		
		
		
		Expense target = ClaimListController.getClaimList().getPosition(first).getPosition(pos);
		
		target.setCategory(categoryView.getText().toString());
		target.setCurrency(currencyView.getText().toString());
		//target.setDate(startdateView.toString());
		target.setDescription(descriptView.getText().toString());
		target.setName(textview.getText().toString());
		target.setSpend(costView.getText().toString());
		
						
		Intent intent = new Intent(EditExpenseActivity.this,ExpenseListActivity.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.putExtra("claimPos", first);
		ClaimListController.saveClaimList();
		startActivity(intent);
	}
	
	public void geolocation(MenuItem menu){
		Toast.makeText(this, "Geolocation", Toast.LENGTH_SHORT).show();
		Intent intent = new Intent(EditExpenseActivity.this, GeolocationActivity.class);
		startActivity(intent);
	}
}
