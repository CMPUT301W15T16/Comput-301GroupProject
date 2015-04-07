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

// wittten by Di Meng 1358503

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class EditExpenseActivity extends Activity {
	
	private ArrayAdapter<String> categorySpinnerAdapter;
	private ArrayAdapter<String> currencySpinnerAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_expense);
		
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
		//EditText categoryView = (EditText) findViewById(R.id.category);
		Spinner expenseCategorySpinner = (Spinner) findViewById(R.id.categorySpinner);
		EditText descriptView = (EditText) findViewById(R.id.expenseDescription);
		EditText costView = (EditText) findViewById(R.id.amountSpent);
		//EditText currencyView = (EditText) findViewById(R.id.currencyText);
		Spinner expenseCurrencySpinner = (Spinner) findViewById(R.id.currencySpinner);
		
		
		
		Expense target = ClaimListController.getClaimList().getPosition(first).getPosition(pos);
		
		target.setCategory(expenseCategorySpinner.getSelectedItem().toString());
		target.setCurrency(expenseCurrencySpinner.getSelectedItem().toString());
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
