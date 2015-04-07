/**
 * This is a Activity file for add Destination 
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
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddDestActivity extends Activity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_dest);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_dest, menu);
		return true;
	}
	
	public void saveDest(View v){
		/**
		 * click saveDest to save destination and jump to the DestlistAcivity
		 */
		Toast.makeText(this,"added a destination", Toast.LENGTH_SHORT).show(); // show message
		DestListController dest = new DestListController();
		EditText textview = (EditText) findViewById(R.id.destName);
		EditText destreasonview = (EditText) findViewById(R.id.reasonDest);
		dest.addDest(new Destination(textview.getText().toString(),destreasonview.getText().toString()));
		
		Intent intent = new Intent(AddDestActivity.this,DestListActivity.class);
		startActivity(intent);
	}

}
