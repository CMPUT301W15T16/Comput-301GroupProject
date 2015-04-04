/**
 * this class is an activity View use for viewing claim informations
 * 
 *  @author Chris Lin
 */

package ca.ualberta.cs.team16app;





import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ClaimInfoActivity extends Activity {
	private Button tag_add;
	private Claim claim;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_claim_info);
		
		TextView nameView= (TextView) findViewById(R.id.editableClaimName);
		TextView beginView=(TextView) findViewById(R.id.editableSDate);
		TextView endView=(TextView) findViewById(R.id.editableEDate);
		tag_add = (Button) findViewById(R.id.tag);
		
		//nameView.setText(claim.getName());
			
		
			
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.claim_info, menu);
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
	
	public void itemlist(View v){
		Intent intent =new Intent(ClaimInfoActivity.this,ExpenseListActivity.class);
		startActivity(intent);	
	}
	public void tag_add(View v){
		Intent intent =new Intent(ClaimInfoActivity.this,Activity_Add_Tag.class);
		startActivity(intent);	
	}
	
	public void geolocation(MenuItem menu){
		Toast.makeText(this, "Geolocation", Toast.LENGTH_SHORT).show();
		Intent intent = new Intent(ClaimInfoActivity.this, GeolocationActivity.class);
		startActivity(intent);
	}

}
