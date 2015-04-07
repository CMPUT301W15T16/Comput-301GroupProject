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

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;

public class tag_add extends Activity{
	private Button saveTag;
	private Button deleteTag;
	private String tagName;	
		
		
	/*public void init(){
		//we should get data from save file first, and show them on the screens
			
		saveTag= (Button)findViewById(R.id.addnewtag);
		deleteTag=(Button)findViewById(R.id.deletetag);
			
			
		saveTag.setOnClickListener(new View.OnClickListener() {
				
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
					
				tagName=saveTag.getText().toString();
				//saveTag(tagName);
			}
		});
			
	}
	*/	
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_tag);
	}
	
	
	}



