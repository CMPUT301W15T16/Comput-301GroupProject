/**
 * This class allow user to take a photo from camera, or select a photo from phone's gallery.
 * It will resize proccess and resize picture to be suitable for the app.
 * 
 * @author Tiancheng Shen
 */

package ca.ualberta.cs.team16app;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class AddPhotoActivity extends Activity {
	
	private static final int SELECT_PHOTO = 100;
	private static final int TAKE_PHOTO = 101;
	static Uri capturedImageUri=null;
	private String show_path;
	private String imageByte;
	int select_result = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState){
		
		super.onCreate(savedInstanceState);
		// set the layout this activity
		setContentView(R.layout.activity_reciept_attachment);
		
		// initialize the buttons
		Button picfromcam = (Button)findViewById(R.id.camera);
		Button picfromphone = (Button)findViewById(R.id.choose_Approver);
		Button picconfirm = (Button)findViewById(R.id.uploadrecipt);
		
		//button for click camera
		picfromcam.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault());
				String curentDateandTime = sdf.format(new Date());
				String newFilePath = Environment.getExternalStorageDirectory() + "/"+curentDateandTime+".jpg"; 
				Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
				File file = new File(Environment.getExternalStorageDirectory(),  curentDateandTime+ ".jpg");
				show_path = newFilePath;
				try {
					file.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
				capturedImageUri = Uri.fromFile(file);
				cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, capturedImageUri);
				startActivityForResult(cameraIntent, TAKE_PHOTO);
			}
		});
		
		//button for click local album
		picfromphone.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				Intent photoPickerIntent = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);				
				startActivityForResult(photoPickerIntent, SELECT_PHOTO);				
			}
		});
		
		//button for confirm
		picconfirm.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				if(select_result == 1){
					saveAndFinish();
				}
			}
		});	
	}
	
	/**
	 * Saves the image byte and stores the data in the intent to pass to
	 * another activity.
	 */
	private void saveAndFinish() {
		Intent intent = new Intent();		
		intent.putExtra("imagebyte", imageByte);
		setResult(RESULT_OK, intent);
		finish();
	}
	
	/**
	 * This function processes an image into bytes by compressing and calling a scaling function upon it
	 * 
	 * @param the image's path and desired scaling size
	 * @return String a image's byte array
	 */
	public String processImage(String path, double size_scale){
		Bitmap bitmapOrg = resizeBitmap(path, size_scale);
		ByteArrayOutputStream imageByte = new ByteArrayOutputStream();

		bitmapOrg.compress(Bitmap.CompressFormat.JPEG, 100, imageByte);
		ImageView test = (ImageView) findViewById(R.id.reciptimg);
		test.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
		test.setImageBitmap(bitmapOrg);
		byte[] bytefile = imageByte.toByteArray();
		String bytefile64 = Base64.encodeToString(bytefile, Base64.DEFAULT);
		return bytefile64;
	}
	
	/**
	 * This function resizes Bitmaps based on the size_scale passed
	 * 
	 * @param the image's path and desired scaling size
	 * @return a resized Bitmap
	 */
	private Bitmap resizeBitmap(String path, double size_scale) {
		Bitmap bitmapOrg = BitmapFactory.decodeFile(path);
		double width = bitmapOrg.getWidth();
		double height = bitmapOrg.getHeight();
		//double ratio = 256.00 / width;
		//int newheight = (int) (ratio * height);
		int new_width = (int) (800.00 / size_scale);
		int new_height = (int) (800.00 / size_scale);
		bitmapOrg = Bitmap.createScaledBitmap(bitmapOrg, new_width, new_height,
				true);
		return bitmapOrg;
	}
	
	/**
	 * This displays the uploaded picture upon the activity screen on return from the Android webcam or mediaStore.
	 * 
	 * @param the requestCode, returnCode, and intent of the returning function
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnIntent) {
		super.onActivityResult(requestCode, resultCode, imageReturnIntent);
		switch(requestCode){			
		case SELECT_PHOTO:
			if(resultCode == RESULT_OK){
				if(imageReturnIntent != null){
					if(imageReturnIntent.getData() != null){
						try {
							Uri selectedImage = imageReturnIntent.getData();
							String[] filePathColumn = {MediaStore.Images.Media.DATA};
							Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
							cursor.moveToFirst();
							int columnIndex =  cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
							String filePath = cursor.getString(columnIndex);
							cursor.close();
							ImageView test = (ImageView) findViewById(R.id.reciptimg);
							test.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
							test.setImageBitmap(BitmapFactory.decodeFile(filePath));
							show_path = filePath;
							imageByte = processImage(show_path,2);
							select_result = 1;
							break;
						}
						catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
				break;

			}
		case TAKE_PHOTO:
			if(resultCode == RESULT_OK){
				try {
					ImageView test = (ImageView) findViewById(R.id.reciptimg);
					Bitmap bitmap = MediaStore.Images.Media.getBitmap( getApplicationContext().getContentResolver(),  capturedImageUri);
					test.setImageBitmap(bitmap);
					imageByte = processImage(show_path,2);
					select_result = 1;
					break;
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			else if (resultCode == RESULT_CANCELED){
                Toast.makeText(this, "Picture Was Not Taken", Toast.LENGTH_SHORT).show();
            }
			
		}
	}

	/**
	 * When the back button is pressed, finishes the current activity.
	 */
	public void onBackPressed() {
		finish();
	}
}
