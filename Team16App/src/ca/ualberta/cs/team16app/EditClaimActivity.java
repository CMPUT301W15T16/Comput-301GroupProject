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

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
//wittten by Di Meng 1358503
public class EditClaimActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_claim);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.edit_claim, menu);
		return true;
	}
	
	
	// written by Di Meng
	public void editClaim(View v){
		ClaimListManager.initManager(this.getApplicationContext());
		Bundle extras = getIntent().getExtras();
		int pos = extras.getInt("ide");
		Toast.makeText(this,"edit a expense", Toast.LENGTH_SHORT).show(); // show message
		ClaimListController cm = new ClaimListController();
		EditText textview = (EditText) findViewById(R.id.claimNameInfo);
		EditText startView = (EditText) findViewById(R.id.startdate);
		EditText endView = (EditText) findViewById(R.id.enddate);
		EditText destination = (EditText) findViewById(R.id.userName_create);
		EditText reason = (EditText) findViewById(R.id.reason);
		
		
		
		
		
		Claim target = ClaimListController.getClaimList().getPosition(pos);
		
		target.setName(textview.getText().toString());
		target.setStartDate(startView.getText().toString());
		//target.setDate(startdateView.toString());
		target.setDestination(destination.getText().toString());
		
						
		Intent intent = new Intent(EditClaimActivity.this,ClaimListActivity.class);
		ClaimListController.saveClaimList();
		startActivity(intent);
		
	}
	
	public void geolocation(MenuItem menu){
		Toast.makeText(this, "Geolocation", Toast.LENGTH_SHORT).show();
		Intent intent = new Intent(EditClaimActivity.this, GeolocationActivity.class);
		startActivity(intent);
	}

}
