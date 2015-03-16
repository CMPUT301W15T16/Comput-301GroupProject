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



