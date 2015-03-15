package ca.ualberta.cs.team16app;

import java.util.ArrayList;
import java.util.Collection;




import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class ExpenseListActivity extends Activity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_expense_list);
		
		
		
ExpenseListManager.initManager(this.getApplicationContext());
		
		ListView listView = (ListView) findViewById(R.id.expense_listView);
	     Collection<Expense> expense = ExpenseListController.getExpenseList().getExpenses();
	   

	     final ArrayList<Expense> list = new ArrayList<Expense>(expense);
	     final ArrayAdapter<Expense> claimAdapter = new ArrayAdapter<Expense>(this, android.R.layout.simple_list_item_1, list);
	     listView.setAdapter(claimAdapter);
	     

	     ClaimListController.getClaimList().addListener(new Listener() {
	     	public void update() {
	     		list.clear();
	     		Collection<Expense> expense = ExpenseListController.getExpenseList().getExpenses();
	     		list.addAll(expense);
	     		claimAdapter.notifyDataSetChanged();
	     	}
	     });
		
		
		
		    
		
		
		listView.setOnItemClickListener(new OnItemClickListener(){


			@Override
			public void onItemClick(AdapterView<?> adapterView, View view, int position,
					long id) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(ExpenseListActivity.this,AddExpenseActivity.class);
				startActivity(intent);
				}
			});
		
		
		
		
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.expense_list, menu);
		return true;
	}

	public void newExpense(View v){
		Toast.makeText(this,"Adding a expense", Toast.LENGTH_SHORT).show(); // show message
		
		Intent intent = new Intent(ExpenseListActivity.this,AddExpenseActivity.class);
		startActivity(intent);
		}
}
