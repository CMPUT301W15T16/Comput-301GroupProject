/**
 * Team16App: travel expense tracking application
 * Copyright (C) 2015 peijen  Chris Lin 
 * dmeng  Di Meng 
 * tshen
 * qtan  Qi Tan 
 * yuentung  
 * omoyeni  Omoyeni Adeyemo 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.

 */
package ca.ualberta.cs.team16app;

import java.util.ArrayList;
import java.util.Collection;


import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;

public class ExpenseListActivity extends Activity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)

	{

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_expense_list);
		
		Bundle extras = getIntent().getExtras();
	     final int temp = extras.getInt("claimPos");
		ExpenseListManager.initManager(this.getApplicationContext());
		
		ListView listView = (ListView) findViewById(R.id.expense_listView);
	     //Collection<Expense> expense = ExpenseListController.getExpenseList().getExpenses();
	     
	     Collection<Expense> expense = ClaimListController.getClaimList()
					.getPosition(temp).getExpense();
	     

	     final ArrayList<Expense> list = new ArrayList<Expense>(expense);
	     final ArrayAdapter<Expense> expenseAdapter = new ArrayAdapter<Expense>(this, android.R.layout.simple_list_item_1, list);
	     listView.setAdapter(expenseAdapter);
	     

	
	     
	     ClaimListController.getClaimList().getPosition(temp).addListener(new Listener() {
	     	public void update() {
	     		list.clear();
				Collection<Expense> expenseCol = ClaimListController.getClaimList()
						.getPosition(temp).getExpense();
				list.addAll(expenseCol);
				//http://developer.android.com/reference/android/widget/ArrayAdapter.html
				expenseAdapter.notifyDataSetChanged();
	     	}
	     });
		
	   //base on eclass youtube video by Abram Hindle: https://www.youtube.com/watch?v=7zKCuqScaRE
		    //delete a claim
		    listView.setOnItemLongClickListener(new OnItemLongClickListener() {

				@Override
				public boolean onItemLongClick(AdapterView<?> adapterView, View view,
						int position, long id) {
					//Toast.makeText(ClaimListActivity.this,"Delete "+ list.get(position).toString(), Toast.LENGTH_SHORT).show();
					final int finalPosition = position;
					AlertDialog.Builder adb = new AlertDialog.Builder(ExpenseListActivity.this); //set alert dialog for deleting
					adb.setMessage("Delete "+list.get(position).toString()+ "?");
					adb.setCancelable(true);
					final int FinalPosition = position;
					//set options for delete claims or cancel
					adb.setPositiveButton("Delete", new OnClickListener(){
						@Override
						public void onClick(DialogInterface dialog, int which) {
							Expense expense = list.get(FinalPosition);
							ClaimListController.getClaimList().getPosition(temp)
							.removeExpense(expense);
													
						}								
					});
					
					
					
					
					adb.setNegativeButton("Edit", new OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {

							Toast.makeText(
									ExpenseListActivity.this,
									"Open Item: "
											+ list.get(FinalPosition)
													.getName(), Toast.LENGTH_SHORT)
									.show();
							Intent intent = new Intent(ExpenseListActivity.this,
									EditExpenseActivity.class);
							intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
							intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
							intent.putExtra("pos", finalPosition);
							intent.putExtra("claimPos",temp);
							startActivity(intent);
						}
					});
					
					adb.show();			
					return true;
				}	    	    	
			});
		
		listView.setOnItemClickListener(new OnItemClickListener(){

			@SuppressWarnings("deprecation")
			@Override
			public void onItemClick(AdapterView<?> AdapterView, View view,
					int position, long id) {
				final int index = position;
				AlertDialog.Builder adb = new AlertDialog.Builder(
						ExpenseListActivity.this);
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
			        	   Intent intent = new Intent(ExpenseListActivity.this,
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
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.expense_list, menu);
		return true;
	}

	public void newExpense(View v){
		Bundle extras = getIntent().getExtras();
	     final int temp = extras.getInt("claimPos");
		Toast.makeText(this,"Adding a expense", Toast.LENGTH_SHORT).show(); // show message
		
		Intent intent = new Intent(ExpenseListActivity.this,AddExpenseActivity.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.putExtra("claimPos", temp);
		startActivity(intent);
		}
	
	public void backToClaimList(View v){
	Toast.makeText(this,"back to the Claim list", Toast.LENGTH_SHORT).show(); // show message
	
	Intent intent = new Intent(ExpenseListActivity.this,ClaimListActivity.class);
	startActivity(intent);
	}

	public void geolocation(MenuItem menu){
		Toast.makeText(this, "Geolocation", Toast.LENGTH_SHORT).show();
		Intent intent = new Intent(ExpenseListActivity.this, GeolocationActivity.class);
		startActivity(intent);
	}
	
}
	  
