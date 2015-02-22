package com.example.team16app.test;

import junit.framework.TestCase;
import android.content.ClipData.Item;
public class ItemsRecepts extends TestCase {

//Edited by Tiancheng Shen
	// Test for US06.03.01
	public void DeletePhotoTest(){
		Item photo = new Item("A");
		photo.addPhoto();
		photo.deletePhoto();
		assertFalse("Photo deleted", photo.getPhoto().equals("A"));
	}

}
