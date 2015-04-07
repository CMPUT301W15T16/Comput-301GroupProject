package ca.ualberta.cs.team16app;

import java.util.ArrayList;
import java.util.Collection;

import android.os.Bundle;
import android.provider.Settings.Global;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;

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
		
		
		listView.setOnItemClickListener(new OnItemClickListener(){

			@SuppressWarnings("deprecation")
			@Override
			public void onItemClick(AdapterView<?> AdapterView, View view,
					int position, long id) {
				final int index = position;
				AlertDialog.Builder adb = new AlertDialog.Builder(
						ApproverExpenseListActivity.this);
				adb.setMessage("Expense: "
						+ list.get(index).getName().toString()
						+ "\nCategory: "
						+ list.get(index).getCategory().toString()
						//+ "\nDate: "
						//+ list.get(index).getDate().toString()
						+ "\nAmount: "
						+ list.get(index).getSpend().toString()
						+ "\nCurrency Type: "
						+ list.get(index).getCurrency().toString()
						+ "\nDescription: "
						+ list.get(index).getDescription().toString()

				);
				adb.setPositiveButton("View Photo", new DialogInterface.OnClickListener() {
			           public void onClick(DialogInterface dialog, int id) {
			        	   Intent intent = new Intent(ApproverExpenseListActivity.this,
									ViewReceiptActivity.class);
							startActivity(intent);
			           }
				});
				adb.show();
			//public void onItemClick(AdapterView<?> adapterView, View view, int position,
					//long id)
					
				// TODO Auto-generated method stub
				//Intent intent = new Intent(ExpenseListActivity.this,AddExpenseActivity.class);
				//startActivity(intent);
				}
			});
		
		
		
		
							
	}

	
	public void backToClaimList(View v){
		Toast.makeText(this,"back to the Claim list", Toast.LENGTH_SHORT).show(); // show message
		
		Intent intent = new Intent(ApproverExpenseListActivity.this,ApproverClaimListActivity.class);
		startActivity(intent);
		}
}
	
