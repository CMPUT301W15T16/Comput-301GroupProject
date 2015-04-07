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

public class DestListActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dest_list);
		

		DestListManager.initManager(this.getApplicationContext());
		
		
		//base on eclass youtube video by Abram Hindle: https://www.youtube.com/watch?v=7zKCuqScaRE
		//adding and update destinations onto claim list
		ListView listView = (ListView) findViewById(R.id.destList);		
		Collection<Destination> dest = DestListController.getDestList().getDest();			
		
		final ArrayList<Destination> list = new ArrayList<Destination>(dest);// share the list, not gonna change it		
		final ArrayAdapter<Destination> destAdapter = new ArrayAdapter<Destination>(this, android.R.layout.simple_list_item_1, list);
	    listView.setAdapter(destAdapter);
	
	    
	    //update to make our adapter now that list has been changed
	    DestListController.getDestList().addListener(new Listener(){
	    	@Override
	    	public void update(){
	    		list.clear();
	    		Collection<Destination> dest = DestListController.getDestList().getDest();
	    		list.addAll(dest);
	    		destAdapter.notifyDataSetChanged();
	    	}
	    });
	    
	  //base on eclass youtube video by Abram Hindle: https://www.youtube.com/watch?v=7zKCuqScaRE
	    //delete a destination
	    listView.setOnItemLongClickListener(new OnItemLongClickListener() {


			@Override
			public boolean onItemLongClick(AdapterView<?> adapterView, View view,
					int position, long id) {
				//Toast.makeText(ClaimListActivity.this,"Delete "+ list.get(position).toString(), Toast.LENGTH_SHORT).show();
				
				AlertDialog.Builder adb = new AlertDialog.Builder(DestListActivity.this); //set alert dialog for deleting
				adb.setMessage("Delete "+list.get(position).toString()+ "?");
				adb.setCancelable(true);
				final int FinalPosition = position;
				//set options for delete claims or cancel
				adb.setPositiveButton("Delete", new OnClickListener(){
					@Override
					public void onClick(DialogInterface dialog, int which) {
						Destination dest = list.get(FinalPosition);
						DestListController.getDestList().removeDest(dest);						
					}								
				});
				
				
				adb.setNegativeButton("Cancel", new OnClickListener(){
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// nothing						
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
						DestListActivity.this);
				adb.setMessage("Claim: "
						+ list.get(index).getDestName().toString()
						+"\nDescribtion: "
						+ list.get(index).getReason().toString()
						
						);
				adb.setNegativeButton("Close", new OnClickListener(){
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// nothing						
					}								
				});
				adb.show();				
	    	}	    	    	
	    });	
	}
		
	 

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.dest_list, menu);
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
	
	//clicking to add new destination
	public void addnewDest(View v){
		Toast.makeText(this,"Adding a destination", Toast.LENGTH_SHORT).show(); // show message
		
		Intent intent = new Intent(DestListActivity.this,AddDestActivity.class);
		startActivity(intent);
		}	
	public void backtoclaim(View v){

		Toast.makeText(this,"back to claim list", Toast.LENGTH_SHORT).show(); // show message
		
		Intent intent = new Intent(DestListActivity.this,ClaimListActivity.class);
		startActivity(intent);
	}
	   
}
	
	    
