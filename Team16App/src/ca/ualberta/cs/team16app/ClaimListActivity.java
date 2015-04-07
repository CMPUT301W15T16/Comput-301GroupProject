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
/**
 * this class is an activity View for viewing claims that are created on claim list
 * 
 * @author Chris Lin
 */

package ca.ualberta.cs.team16app;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;



import ca.ualberta.cs.team16app.elasitcSearch.ESClient;






import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;

public class ClaimListActivity extends Activity {

	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_claimant_claim_list);
		ClaimListManager.initManager(this.getApplicationContext());
		
		
		//base on eclass youtube video by Abram Hindle: https://www.youtube.com/watch?v=7zKCuqScaRE
		//adding and update claims onto claim list
		ListView listView = (ListView) findViewById(R.id.clientclaimlist);		
		Collection<Claim> claims = ClaimListController.getClaimList().getClaims();			
		
		final ArrayList<Claim> list = new ArrayList<Claim>(claims);// share the list, not gonna change it		
		final ArrayAdapter<Claim> claimAdapter = new ArrayAdapter<Claim>(this, android.R.layout.simple_list_item_1, list);
	    listView.setAdapter(claimAdapter);
	
	    
	    //update to make our adapter now that list has been changed
	    ClaimListController.getClaimList().addListener(new Listener(){
	    	@Override
	    	public void update(){
	    		//list.clear();
	    		//Collection<Claim> claims = ClaimListController.getClaimList().getClaims();
	    		//list.addAll(claims);
	    		claimAdapter.notifyDataSetChanged();
	    	}
	    });
	    
	    //base on eclass youtube video by Abram Hindle: https://www.youtube.com/watch?v=7zKCuqScaRE
	    //delete a claim
	    listView.setOnItemLongClickListener(new OnItemLongClickListener() {


			@Override
			public boolean onItemLongClick(AdapterView<?> adapterView, View view,
					int position, long id) {
				//Toast.makeText(ClaimListActivity.this,"Delete "+ list.get(position).toString(), Toast.LENGTH_SHORT).show();
				
				AlertDialog.Builder adb = new AlertDialog.Builder(ClaimListActivity.this); //set alert dialog for deleting
				adb.setMessage("Delete "+list.get(position).toString()+ "?");
				adb.setCancelable(true);
				final int FinalPosition = position;
				//set options for delete claims or cancel
				adb.setPositiveButton("Delete", new OnClickListener(){
					@Override
					public void onClick(DialogInterface dialog, int which) {
						Claim claim = list.get(FinalPosition);
						ClaimListController.getClaimList().removeClaim(claim);						
					}								
				});
				
				adb.setNeutralButton("Add Expense", new OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						Intent intent = new Intent(ClaimListActivity.this,
								ExpenseListActivity.class);
						Toast.makeText(
								ClaimListActivity.this,
								"add new expenses ", Toast.LENGTH_SHORT).show();
						intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
						intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
						// sent the finalPosition to ActivityNewClaim
						intent.putExtra("claimPos", FinalPosition);
						startActivity(intent);

					}
				});
					adb.setNegativeButton("Destination List", new OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							Intent intent = new Intent(ClaimListActivity.this,
									DestListActivity.class);
							Toast.makeText(
									ClaimListActivity.this,
									"adding destinations ", Toast.LENGTH_SHORT).show();
							/*
							intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
							intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
							// sent the finalPosition to ActivityNewClaim
							intent.putExtra("claimPos", FinalPosition);
							*/
							startActivity(intent);

						}
				});

				adb.show();			
				return true;
			}	    	    	
		});
	    
	    listView.setOnItemClickListener(new OnItemClickListener(){
	    	@Override
	    	public void onItemClick(AdapterView<?> AdapterView, View view,
					int position, long id) {
				final int index = position;
				AlertDialog.Builder adb = new AlertDialog.Builder(
						ClaimListActivity.this);
				adb.setMessage("Claim: "
						+ list.get(index).getName().toString()
						+ "\nStart Date: "
						+ list.get(index).getStartDate().toString()
						+"\nEnd Date: "
						+ list.get(index).getEndDate().toString()
						+"\nDestination: "
						+list.get(index).getDestList().toString()
						+"\nDescribtion: "
						+ list.get(index).getDescription().toString()
						+"\nTotal Currency:\n "
						+ list.get(index).totalcurrency(list.get(index))
						
						);
				adb.setPositiveButton("Submit", new OnClickListener(){
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// nothing		
						Claim claim = list.get(index);
						pushClaim(claim);
						
						
					}								
				});
				
				adb.setNeutralButton("Edit", new OnClickListener(){
					@Override
					public void onClick(DialogInterface dialog, int which) {	
						
						Intent intent = new Intent(ClaimListActivity.this,EditClaimActivity.class);
						intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
						intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
						intent.putExtra("ide", index);
						startActivity(intent);
						
					}					
				});
				
				
				adb.setNegativeButton("Addtags", new OnClickListener(){
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// nothing		
						Intent intent = new Intent(ClaimListActivity.this,AddTagActivity.class);//
						intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
						intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
						String s = list.get(index).getName().toString();
						intent.putExtra("claimname",s);
						startActivity(intent);
					}								
				});
				
				adb.show();	
	    		}
	    	});

		
	    	
			//public void onItemClick(AdapterView<?> adapterView, View view, int position,
			//		long id) {
				
				
				
				// TODO Auto-generated method stub
				//Intent intent = new Intent(ClaimListActivity.this,ClaimInfoActivity.class);
				//startActivity(intent);
				//}
			//});
	    	
	}
	
	
	/**
	 * This function checks whether an Internet connection is available to the
	 * activity.
	 * 
	 * Tutorial from:
	 * http://stackoverflow.com/questions/9570237/android-check-internet
	 * -connection
	 * 
	 * @return boolean
	 */
	
	private boolean isNetworkConnected() {
		ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		return (cm.getActiveNetworkInfo() != null);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.claim_list, menu);
		return true;
	}


	
	 //click new claim button to add a claim 
	public void newclaim(View v){
		Toast.makeText(this,"Adding a claim", Toast.LENGTH_SHORT).show(); // show message
		
		Intent intent = new Intent(ClaimListActivity.this,AddClaimActivity.class);
		startActivity(intent);
		}
	

	
	private class pushClaimTask extends AsyncTask<String, String, String> {
		Claim claim;
		public pushClaimTask(Claim claimClicked) {
			this.claim = claimClicked;
		}
		@Override
		protected String doInBackground(String... arg0) {
			ESClient client = new ESClient();
			try {
				client.insertClaim(this.claim);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
		
		/**
		 * Toast message appears after the claim is published successfully.
		 */
		protected void onPostExecute(String result) {
			Toast.makeText(ClaimListActivity.this,
					"Pushed " + this.claim.getName(), Toast.LENGTH_LONG)
					.show();
		}
	}

	/**
	 * This function publishes a claim on the 
	 * WebServer.
	 * 
	 * 
	 */
	public void pushClaim(Claim claimClicked) {
		if (isNetworkConnected()) {
		//Since we need to give the HTTP client some time
		//to publish the story, we need to use AsyncTasks.
		new pushClaimTask(claimClicked).execute();
		}
		else {
			// Else Display Message that Internet is not available and
			createAlertDialog();
		}
	}
	
	private void createAlertDialog() {
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

		// set title
		alertDialogBuilder.setTitle("Network Error!");

		// set dialog message
		alertDialogBuilder
				.setMessage(
						"There's no network connection! Cannot Push a claim.")
				.setCancelable(false)
				.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						// close current activity and go back to Local Library.

					}
				});
		// create alert dialog
		AlertDialog alertDialog = alertDialogBuilder.create();

		// show it
		alertDialog.show();

	}
	
	

	public void geolocation(MenuItem menu){
		Toast.makeText(this, "Geolocation", Toast.LENGTH_SHORT).show();
		Intent intent = new Intent(ClaimListActivity.this, GeolocationActivity.class);
		startActivity(intent);
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		
		int id = item.getItemId();
		if (id == R.id.logoff) {
			Intent intent = new Intent(ClaimListActivity.this,
					MainActivity.class);
			Toast.makeText(
					ClaimListActivity.this,
					"logoff ", Toast.LENGTH_SHORT).show();
			startActivity(intent);
			
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
