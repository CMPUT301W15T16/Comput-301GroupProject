package ca.ualberta.cs.team16app;



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
		Bundle extras = getIntent().getExtras();
		int pos = extras.getInt("id");
		Toast.makeText(this,"edit a expense", Toast.LENGTH_SHORT).show(); // show message
		ExpenseListController cm = new ExpenseListController();
		EditText textview = (EditText) findViewById(R.id.name);
		EditText startdateView = (EditText) findViewById(R.id.expenseDate);
		EditText categoryView = (EditText) findViewById(R.id.Category);
		EditText descriptView = (EditText) findViewById(R.id.expenseDescription);
		EditText costView = (EditText) findViewById(R.id.amountSpent);
		EditText currencyView = (EditText) findViewById(R.id.currency);
		
		
		
		
		Expense target = ExpenseListController.getExpenseList().getPosition(pos);
		
		target.setCategory(categoryView.getText().toString());
		target.setCurrency(currencyView.getText().toString());
		//target.setDate(startdateView.toString());
		target.setDescription(descriptView.getText().toString());
		target.setName(textview.getText().toString());
		target.setSpend(costView.getText().toString());
		
						
		Intent intent = new Intent(EditExpenseActivity.this,ExpenseListActivity.class);
		ExpenseListController.saveExpenseList();
		startActivity(intent);
		
	}
}
