/**
 * this class is an activity View for viewing claims that are created on claim list
 * 
 * @author Chris Lin
 */

package ca.ualberta.cs.team16app;

import java.util.ArrayList;
import java.util.Collection;




import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
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
	    		list.clear();
	    		Collection<Claim> claims = ClaimListController.getClaimList().getClaims();
	    		list.addAll(claims);
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
				adb.setNegativeButton("Edit", new OnClickListener(){
					@Override
					public void onClick(DialogInterface dialog, int which) {						
						Intent intent = new Intent(ClaimListActivity.this,ClaimInfoActivity.class);
						startActivity(intent);
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
						//intent.putExtra("id", finalPosition);
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
						//+ list.get(index).getStartDate().toString()
						);
				
				
				
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
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.claim_list, menu);
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
	
	 //click new claim button to add a claim 
	public void newclaim(View v){
		Toast.makeText(this,"Adding a claim", Toast.LENGTH_SHORT).show(); // show message
		
		Intent intent = new Intent(ClaimListActivity.this,AddClaimActivity.class);
		startActivity(intent);
		}
}
