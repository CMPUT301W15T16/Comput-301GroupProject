/*
 * This file allows the approver to view the list of claims.
 * Also, when a claim is clicked allows the user to go to 
 * the claim view page to see claim in more detail*/



package ca.ualberta.cs.team16app;

import java.util.ArrayList;
import java.util.Collection;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class ApproverClaimListActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_approver_claim_list);
	ClaimListManager.initManager(this.getApplicationContext());
	
	//base on eclass youtube video by Abram Hindle: https://www.youtube.com/watch?v=7zKCuqScaRE
			//adding and update claims onto claim list
			ListView listView = (ListView) findViewById(R.id.approverclaimlist);		
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
		    
		    
		    listView.setOnItemClickListener(new OnItemClickListener(){


				@Override
				public void onItemClick(AdapterView<?> adapterView, View view, int position,
						long id) {
					// TODO Auto-generated method stub
					Intent intent = new Intent(ApproverClaimListActivity.this,ApproverClaimViewActivity.class);
					startActivity(intent);
					}
				});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.approver_claim_list, menu);
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
}
