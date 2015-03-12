package ca.ualberta.cs.team16app;




import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void login(View v){//click login button to proceed to claim list
		Toast.makeText(this,"Claim List", Toast.LENGTH_SHORT).show(); // show message
		
		Intent intent = new Intent(MainActivity.this,ClaimListActivity.class);
		startActivity(intent);// move to claim list
		}
}
