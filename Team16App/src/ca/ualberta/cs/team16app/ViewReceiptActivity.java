/**
 * This class let users view the single photo of an expense item, and they can delete and retake photo here.
 * 
 * @author Tiancheng Shen
 */

package ca.ualberta.cs.team16app;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class ViewReceiptActivity extends Activity {
	private static final int PHOTO_ACTIVITY_REQUEST = 1001;
	ImageView image;
	String show_path;
	String imageByte;

	@Override
	protected void onCreate(Bundle savedInstanceState){
		
		super.onCreate(savedInstanceState);
		// set the layout this activity
		setContentView(R.layout.activity_show_image);
		
		/**
		 * View image here
		 */
		image = (ImageView) findViewById(R.id.imageView);
		image.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getApplicationContext(),
						AddPhotoActivity.class);
				startActivityForResult(intent, PHOTO_ACTIVITY_REQUEST);
			}
		});
	}
	
	/**
	 * Retrieve the uploaded image from TakePhotoActivity to be used as a thumbnail in the story associated with this activity
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == PHOTO_ACTIVITY_REQUEST && resultCode == RESULT_OK) {

			show_path = data.getStringExtra("path");
			imageByte = data.getStringExtra("imagebyte");

			byte[] decodedString = Base64.decode(imageByte, Base64.DEFAULT);
			Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString,
					0, decodedString.length);
			image.setImageBitmap(decodedByte);

		}
	}
	
}
