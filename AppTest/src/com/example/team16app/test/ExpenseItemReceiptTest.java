package com.example.team16app.test;

import android.content.ClipData.Item;
import junit.framework.TestCase;

public class ExpenseItemReceiptTest extends TestCase {


	//Chris Lin
	//view receipt for an expense item
	public void viewReceiptTest(){
		Item photo = new Item("photo");
		assertTrue("Photo exist!!",photo.getPhoto() != null);
	}
}
