package ca.ualberta.cs.team16app;




import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity
{
	private EditText  username=null;
	private Button claimant;
	private Button approver;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		username = (EditText)findViewById(R.id.username);
		claimant = (Button)findViewById(R.id.user_claimant);
		approver = (Button)findViewById(R.id.user_approver);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void claimant(View v){//click login button to proceed to claim list
		if(username.getText().toString().equals(""))
		{
		Toast.makeText(getApplicationContext(), "Please enter your username",
		Toast.LENGTH_SHORT).show();
		}
		//user cannot enter without a user name
		
	 else{
		Toast.makeText(this,"Claim List", Toast.LENGTH_SHORT).show(); // show message
			
		Intent intent = new Intent(MainActivity.this,ClaimListActivity.class);
		startActivity(intent);// move to claim list
		 }
	}
	public void approver(View v){//click login button to proceed to approver page
		if(username.getText().toString().equals(""))
		{
		Toast.makeText(getApplicationContext(), "Please enter your username",
		Toast.LENGTH_SHORT).show();
		}
		//user cannot enter without a user name
		
	 else{
		Toast.makeText(this,"Approver page", Toast.LENGTH_SHORT).show(); // show message
			
		Intent intent = new Intent(MainActivity.this,ClaimListActivity.class);//
		startActivity(intent);// move to claim list since the approver claim list have not been done
		 }
	}
}
	

	
