package ca.ualberta.cs.team16app.test;

import java.util.Date;

import ca.ualberta.cs.team16app.Claim;
import ca.ualberta.cs.team16app.ViewReceiptActivity;
import android.content.ClipData.Item;
import android.graphics.Bitmap;
import android.test.ActivityInstrumentationTestCase2;
import junit.framework.TestCase;


public class ExpenseItemReceiptTest extends ActivityInstrumentationTestCase2<ViewReceiptActivity>
{
	public ExpenseItemReceiptTest() {
		super(ViewReceiptActivity.class);
	}



	//Chris Lin
		//view receipt for an expense item
		//US06.02.01
		public void testViewReceipt(){
			Bitmap img = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888 );
			
			assertTrue("Photo exist!!",img != null);
		}
		
		
		
		//qtan
		//US06.04.01
		public class testSizeoverlimit {
			//make sure the size of the image file is below 65536 bytes
			public void testLargeBitmap(){
				Bitmap img = Bitmap.createBitmap(1000, 1000, Bitmap.Config.ARGB_8888 );
				
				Claim claim = new Claim("test");
				
				//claim.addReceipt(img);
				Bitmap result = img; //= claim.getReceipt();
				if(result.getByteCount() > 65536){
					fail("over the limit");
				}
				assertFalse("", img == result);
			}
		}
		
		
		//Edited by Tiancheng Shen
			// Test for US06.03.01
		/*	public void DeletePhotoTest(){
				Item photo = new Item("A");
				photo.addPhoto();
				photo.deletePhoto();
				assertFalse("Photo deleted", photo.getPhoto().equals("A"));
			}
			*/


}
