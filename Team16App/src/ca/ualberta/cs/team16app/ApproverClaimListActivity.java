/**
 * This file allows the approver to view the list of claims.
 * Also, when a claim is clicked allows the user to go to 
 * the claim view page to see claim in more detail*/
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









import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import ca.ualberta.cs.team16app.elasitcSearch.ESClient;
/**
 * This file allows the approver to view the list of claims.
 * Also, when a claim is clicked allows the user to go to 
 * the claim view page to see claim in more detail
 */
public class ApproverClaimListActivity extends Activity {
	
	ArrayList<Claim> claimLibrary = new ArrayList<Claim>();

	boolean networkConnected;
	ESClient client = new ESClient(); 
	

	ArrayAdapter<Claim> adapter; // This is an adapter that will be used to
	// populate the listview.


	ClaimManager sManagerInst; // Controller for the Stories
	CacheManager cManagerInst;
	Typeface font;
	
	
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_approver_claim_list);
	ClaimListManager.initManager(this.getApplicationContext());
	
		initializeGlobals();
		attemptNetworkConnection();
		    
		   
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.approver_claim_list, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	
		int id = item.getItemId();
		if (id == R.id.logoff) {
			Intent intent = new Intent(ApproverClaimListActivity.this,
					MainActivity.class);
			Toast.makeText(
					ApproverClaimListActivity.this,
					"logoff ", Toast.LENGTH_SHORT).show();
			startActivity(intent);
			
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	/**
	 * Attempt to connect to the network. If there is no connection, shows a
	 * message and returns to the offline library.
	 */
	private void attemptNetworkConnection() {
		// First CheckWhether Internet is Connected:
		networkConnected = isNetworkConnected();
		if (networkConnected) {
			// Download all Stories from online then populate
			// the list view. Since we need to give the application
			// some time to connect to the Internet, we have to use
			// AsyncTask.
			new getStoriesAndDisplay().execute();
			Toast.makeText(this, "Downloaded: " ,
					Toast.LENGTH_LONG).show();
			
		} else {
			// Else Display Message that Internet is not available and
			// return to Local Library.
			createAlertDialog();
		}
		
		
	}
	
	/**
	 * Checks whether an Internet connection is available to the activity.
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
	

	/**
	 * Fetches data from the server.
	 * 
	 */
	private void fetchDataFromServer() {
		claimLibrary = client.getAllClaims();
		
		cManagerInst.cacheData(claimLibrary);
	}

	/**
	 * Initiate instances of the CacheManager and claimManager for the current
	 * activity.
	 */
	private void initializeGlobals() {
		cManagerInst = CacheManager.getInstance();
		cManagerInst.initApplicationContext(this.getApplicationContext());
		sManagerInst = ClaimManager.getInstance();
		sManagerInst.initContext(this);
	}

	
	private class getStoriesAndDisplay extends
	AsyncTask<String, String, String> {

		// Display Loading Spinner on the activity.
		// Tutorial:
		// http://stackoverflow.com/questions/12559461/how-to-show-progress-barcircle-in-an-activity-having-a-listview-before-loading
		LinearLayout linearLayoutForProgress = (LinearLayout) findViewById(R.id.linearLayoutForProgress);

		@Override
		protected void onPreExecute() {
			// SHOW THE SPINNER WHILE LOADING.
			linearLayoutForProgress.setVisibility(View.VISIBLE);
		}

		/**
		 * This function is executed first. This function contacts the server
		 * and downloads all the stories to the phone's memory.
		 */
		@Override
		protected String doInBackground(String... arg0) {
			if (networkConnected) {
				fetchDataFromServer();
			} else {
				claimLibrary = cManagerInst.getCacheLibrary();
			}
			return null;
		}

		/**
		 * This function is executed when the previous one finishes. Once we
		 * have downloaded all the online Claims, we can display them to the
		 * user.
		 */
		protected void onPostExecute(String result) {
			populateListView();

			// HIDE THE SPINNER AFTER LOADING.
			linearLayoutForProgress.setVisibility(View.GONE);
		}
	}
	
	
	/**
	 * Populates the list view for the online library and sets the click
	 * listener for viewing the stories.
	 */
	private void populateListView() {
		
		final ListView listView = (ListView) findViewById(R.id.approverclaimlist);	
		
		Collection<Claim> claims = ClaimListController.getClaimList().getClaims();	
		
		final ArrayList<Claim> list = claimLibrary;// share the list, not gonna change it		
		final ArrayAdapter<Claim> claimAdapter = new ArrayAdapter<Claim>(this, android.R.layout.simple_list_item_1, list);
	    listView.setAdapter(claimAdapter);
	    
	    
	  //update to make our adapter now that list has been changed
	    ClaimListController.getClaimList().addListener(new Listener(){
	    	@Override
	    	public void update(){
	    		list.clear();
	    		Collection<Claim> claims = ClaimListController.getClaimList().getClaims();
	    		list.addAll(claims);
	    		claimAdapter.notifyDataSetChanged();
	    	}
	    });

		// tutorial used =
		// http://stackoverflow.com/questions/9097723/adding-a-onclicklistener-to-listview-android

		listView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				// This is the claim object that is returned when a list item is
				// clicked.
				//Claim claim = (Claim) listView.getItemAtPosition(position);
				
				final int index = position;
				AlertDialog.Builder adb = new AlertDialog.Builder(
						ApproverClaimListActivity.this);
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
						);
				
				adb.setPositiveButton("view expense", new OnClickListener(){
					@Override
					public void onClick(DialogInterface dialog, int which) {
						Intent intent = new Intent(ApproverClaimListActivity.this,
								ApproverExpenseListActivity.class);
						Toast.makeText(
								ApproverClaimListActivity.this,
								"view expenses ", Toast.LENGTH_SHORT).show();
						intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
						intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
						// sent the finalPosition to ActivityNewClaim
						intent.putExtra("claim", list.get(index));
						startActivity(intent);

					}								
				});
				
				adb.setNegativeButton("Add Comments", new OnClickListener(){
					@Override
					public void onClick(DialogInterface dialog, int which) {
						Claim claim = list.get(index);
						Intent intent = new Intent(ApproverClaimListActivity.this,
								ApproverCommentsActivity.class);
						Toast.makeText(
								ApproverClaimListActivity.this,
								"add comments ", Toast.LENGTH_SHORT).show();
						intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
						intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
						// sent the finalPosition to ActivityNewClaim
						intent.putExtra("claim", list.get(index));
						startActivity(intent);					
					}								
				});
				
				//viewclaim(claim);
				adb.show();	
			}
		});
		
		
		
		
		
		
	}

	private void createAlertDialog() {
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

		// set title
		alertDialogBuilder.setTitle("Network Error!");

		// set dialog message
		alertDialogBuilder
				.setMessage(
						"There's no network connection! Library will now be Loaded from Cached Data.")
				.setCancelable(false)
				.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						// close current activity and go back to Local Library.
						new getStoriesAndDisplay().execute();
					}

				});
		// create alert dialog
		AlertDialog alertDialog = alertDialogBuilder.create();

		// show it
		alertDialog.show();

	}
	
	/**
	 * This function downloads the online claim to the phone's memory.
	 * 
	 * @param claimClicked
	 */
	private void downloadclaim(Claim claimClicked) {
		sManagerInst.saveclaim(claimClicked, true);
		Toast.makeText(this, "Downloaded: " + claimClicked.getName(),
				Toast.LENGTH_LONG).show();
	}
	

	
	
}
