/**
 * This class is The Tag function activity
 * 
 * @author Qi Tan
 */
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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;

public class AddTagActivity extends Activity {
	/**
	 * CorrespondingClaimName is get from claim name for let user check the matched claim name when user use tag
	 * this page's interface is based on the activity_add_tag.xml
	 */
	
	private String CorrespondingClaimName;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_tag);
	
		/**
		 * save the claim name from ClaimListAcivity
		 * adding and update the tag into taglist and show that in textview
		 */
		Intent intent1 = getIntent();
		CorrespondingClaimName=intent1.getStringExtra("claimname");
		

		TagListManager.initManager(this.getApplicationContext());
		
		
		//base on eclass youtube video by Abram Hindle: https://www.youtube.com/watch?v=7zKCuqScaRE
		//adding and update tag onto tag list
		ListView listView = (ListView) findViewById(R.id.tagview);		
		Collection<Tag> tag = TagListController.getTagList().getTags();			
		
		final ArrayList<Tag> list = new ArrayList<Tag>(tag);// share the list, not gonna change it		
		final ArrayAdapter<Tag> tagAdapter = new ArrayAdapter<Tag>(this, android.R.layout.simple_list_item_1, list);
	    listView.setAdapter(tagAdapter);
	
	    
	    /**
	     * update to make our adapter 
	     * now that list has been changed
	     * 
	     */
	   TagListController.getTagList().addListener(new Listener(){
	    	@Override
	    	public void update(){
	    		list.clear();
	    		Collection<Tag> tag = TagListController.getTagList().getTags();
	    		list.addAll(tag);
	    		tagAdapter.notifyDataSetChanged();
	    	}
	    });
	
	 listView.setOnItemLongClickListener(new OnItemLongClickListener() {


			@Override
			public boolean onItemLongClick(AdapterView<?> adapterView, View view,
					int position, long id) {
				/**
				 * set the long click funtion to the claim list
				 * to make the Delete to delete and cancel to cancel
				 */
				//Toast.makeText(ClaimListActivity.this,"Delete "+ list.get(position).toString(), Toast.LENGTH_SHORT).show();
				
				AlertDialog.Builder adb = new AlertDialog.Builder(AddTagActivity.this); //set alert dialog for deleting
				adb.setMessage("Delete "+list.get(position).toString()+ "?");
				adb.setCancelable(true);
				final int FinalPosition = position;
				//set options for delete claims or cancel
				adb.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						Tag tags = list.get(FinalPosition);
						TagListController.getTagList().removeTag(tags);	
					}
				});
				adb.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// nothing						
					}								
				});
			
				adb.show();	
				return true;
			}	    	    	
		});
	}
	  //base on eclass youtube video by Abram Hindle: https://www.youtube.com/watch?v=7zKCuqScaRE
	    //delete a tag
	   
	    
	
	 

		@Override
		public boolean onCreateOptionsMenu(Menu menu) {
			/**
			 * Inflate the menu; this adds items to the action bar if it is present.
			 */
		  
			getMenuInflater().inflate(R.menu.activity__add__tag, menu);
			return true;
			}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		/**
		* Handle action bar item clicks here. The action bar will
		* automatically handle clicks on the Home/Up button, so long
		* as you specify a parent activity in AndroidManifest.xml.
		 */
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	/**clicking to add new tag
	 * 
	 * @param v addnewtag button
	 * and save the claimname and the tag name into tag for showing it in add tag page
	 */
	public void  Addnewtag(View v){
		Toast.makeText(this,"added a tag", Toast.LENGTH_SHORT).show(); // show message
		TagListController tc = new TagListController();
		EditText textview = (EditText) findViewById(R.id.tagname_add);
		tc.addTag(new Tag(textview.getText().toString(),CorrespondingClaimName));
		
			
	}
	/**
	 * 
	 * @param menu 
	 * click the geolocation in the topright coner to go to the geolocationacivity
	 */

	public void geolocation(MenuItem menu){
		Toast.makeText(this, "Geolocation", Toast.LENGTH_SHORT).show();
		Intent intent = new Intent(AddTagActivity.this, GeolocationActivity.class);
		startActivity(intent);
	}
}