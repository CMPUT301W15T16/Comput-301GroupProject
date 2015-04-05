package ca.ualberta.cs.team16app;

import android.os.Bundle;
import android.app.Activity;
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
		Toast.makeText(this,"added a destination", Toast.LENGTH_SHORT).show(); // show message
		ClaimListController dest = new ClaimListController();
		EditText textview = (EditText) findViewById(R.id.destName);
		EditText destreasonview = (EditText) findViewById(R.id.reasonDest);
		
	}

}
