package ca.ualberta.cs.team16app;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddExpenseActivity extends Activity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_expense);
		
        Button addreceipt = (Button)findViewById(R.id.addimg);
        addreceipt.setOnClickListener(new OnClickListener() {
        	
        	@Override
        	public void onClick(View v){
        		Intent intent=new Intent(AddExpenseActivity.this, AddPhotoActivity.class);
        		startActivity(intent);
        	}
        });	
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_expense, menu);
		return true;
	}

	public void saveExpense(View v){
		ClaimListManager.initManager(this.getApplicationContext());
		Bundle extras = getIntent().getExtras();
		int first = extras.getInt("claimPos");
		Toast.makeText(this,"added a expense", Toast.LENGTH_SHORT).show(); // show message
		ClaimListController cm = new ClaimListController();
		EditText textview = (EditText) findViewById(R.id.name);
		EditText startdateView = (EditText) findViewById(R.id.expenseDate);
		EditText categoryView = (EditText) findViewById(R.id.Category);
		EditText descriptView = (EditText) findViewById(R.id.expenseDescription);
		EditText costView = (EditText) findViewById(R.id.amountSpent);
		EditText currencyView = (EditText) findViewById(R.id.currency);
		ClaimListController.getClaimList().getPosition(first).addExpense(new Expense(textview.getText().toString(),categoryView.getText().toString(),costView.getText().toString()
				,currencyView.getText().toString(),descriptView.getText().toString()));
						
		Intent intent = new Intent(AddExpenseActivity.this,ExpenseListActivity.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.putExtra("claimPos", first);
		startActivity(intent);
	}
	
	public void geolocation(MenuItem menu){
		Toast.makeText(this, "Geolocation", Toast.LENGTH_SHORT).show();
		Intent intent = new Intent(AddExpenseActivity.this, GeolocationActivity.class);
		startActivity(intent);
	}
}
