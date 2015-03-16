package ca.ualberta.cs.team16app;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.Button;

public class Activity_Add_Tag extends Activity {
	private Button saveTag;
	private Button deleteTag;
	private String tagName;	
		
		
	/*public void start(){
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
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_tag);
		//
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity__add__tag, menu);
		return true;
	}

}
