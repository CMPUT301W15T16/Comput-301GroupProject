package com.example.team16app.test;

import android.test.ViewAsserts;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.team16app.ApproverClaimListActivity;
import com.example.team16app.ClaimantItemListActivity;
import com.example.team16app.Item;
import com.example.team16app.ItemListController;
import com.example.team16app.ItemListController;

import junit.framework.TestCase;
// written by Di Meng  US05.01.01 as a Claimant view the item expenses details and photo
public class Case5Test extends TestCase {
	
	public void SubmittedItemsTest() {
		// init the controller
		ItemListController list = new ItemListController();
		// set all values
		Item item = new Item();
		item.getStartDate(20121212);
		item.getEndDate(20130101);
		item.getCatagory("Meal");
		item.getName("approver test");
		item.getCurrency("EUR");
		item.getCost(1000);
		item.addDescribtion("test comment");
		ItemListController test_con = new ItemListController();
		test_con.add(item);
		Item output = list.get(0);
		
		// do the test
		assertEquals("start date not same",item.getStartDate(0),output.getStartDate(0));
		assertEquals("end date not same",item.getEndDate(0),output.getEndDate(0));
		assertEquals("catagory not same",item.getCatagory(null),output.getCatagory(null));
		assertEquals("name not same",item.getName,output.getName);
		assertEquals("currenty is not same",item.getCurrency("CYN"),output.getCurrency("CYN"));
		assertEquals("cost not same",item.getCost(0),output.getCost(0));
		assertEquals("Describtion not equal","hello world",Item.getDescribtion());
	}
	
	public void ViewPhotoTest() {
		ClaimantItemListActivity activity = getActivity();
		ImageButton view = (Button) activity.findViewById(R.id.photograph);
		ViewAsserts.assertOnScreen(activity.getWindow().getDecorView(),view);
		assertNotNull("not empty",view.getDrawable());
		assertTrue(View.VISIBLE == view.getVisibility());
	}
}

