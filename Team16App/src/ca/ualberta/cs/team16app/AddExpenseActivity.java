/**
 * This is an Activity file for add Expense
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

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class AddExpenseActivity extends Activity
{

	private ArrayAdapter<String> categorySpinnerAdapter;
	private ArrayAdapter<String> currencySpinnerAdapter;
	
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
        /**
         * get the expense category items and add them into the category
         */
        
        // set category spinner
        Spinner expenseCategorySpinner = (Spinner) findViewById(R.id.categorySpinner);
		ArrayList<String> category = new ArrayList<String>();
		category.add("Air Fare");
		category.add("Ground Transpotation");
		category.add("Vehicle Rental");
		category.add("Private Automobile");
		category.add("Fuel");
		category.add("Parking");
		category.add("Registration");
		category.add("Accomodation");
		category.add("Meal");
		category.add("Supplies");
		categorySpinnerAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, category);
		categorySpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		expenseCategorySpinner.setAdapter(categorySpinnerAdapter);

		// set currency spinner
		Spinner expenseCurrencySpinner = (Spinner) findViewById(R.id.currencySpinner);
		ArrayList<String> currency = new ArrayList<String>();
		/**
		 * get currency type
		 */
		currency.add("USD");
		currency.add("CAD");
		currency.add("EUR");
		currency.add("GBP");
		currency.add("CHF");
		currency.add("JPY");
		currency.add("CNY");

		currencySpinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, currency);
		currencySpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		expenseCurrencySpinner.setAdapter(currencySpinnerAdapter);

		
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
		/**
		 * save the differnet element from the edit edit to show them in the claimlist page
		 */
		ClaimListController cm = new ClaimListController();
		
		
		EditText textview = (EditText) findViewById(R.id.name);
		EditText startdateView = (EditText) findViewById(R.id.expenseDate);
		Spinner expenseCategorySpinner = (Spinner) findViewById(R.id.categorySpinner);
		EditText descriptView = (EditText) findViewById(R.id.expenseDescription);
		EditText costView = (EditText) findViewById(R.id.amountSpent);
		Spinner expenseCurrencySpinner = (Spinner) findViewById(R.id.currencySpinner);
		
		
		ClaimListController.getClaimList().getPosition(first).addExpense(new Expense(textview.getText().toString(),expenseCategorySpinner.getSelectedItem().toString(),costView.getText().toString()
				,expenseCurrencySpinner.getSelectedItem().toString(),descriptView.getText().toString()));
		
		String name = textview.getText().toString();
		String start = startdateView.getText().toString();
		String  cat = expenseCategorySpinner.getSelectedItem().toString();
		String cost = costView.getText().toString();
		String cur = expenseCurrencySpinner.getSelectedItem().toString();
		String des = descriptView.getText().toString();
		/**
		 * make sure none of the input of the expense is null
		 */
		if(name.equals("")||start.equals("")||cat.equals("")||cost.equals("")||cur.equals("")||des.equals("")){
			Toast.makeText(getApplicationContext(), "Make sure every entries are filled!",
			Toast.LENGTH_SHORT).show();
			
		}
		/**
		 * for add a new expense
		 */
		
		else{
			Toast.makeText(this,"added a expense", Toast.LENGTH_SHORT).show(); // show message
			Intent intent = new Intent(AddExpenseActivity.this,ExpenseListActivity.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			intent.putExtra("claimPos", first);
			startActivity(intent);
		}
		
		
	}
	/**
	 * 
	 * @param menu
	 * jump to geolocation by click button
	 */
	
	public void geolocation(MenuItem menu){
		Toast.makeText(this, "Geolocation", Toast.LENGTH_SHORT).show();
		Intent intent = new Intent(AddExpenseActivity.this, GeolocationActivity.class);
		startActivity(intent);
	}
}
