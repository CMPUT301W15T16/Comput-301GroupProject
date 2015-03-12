package ca.ualberta.cs.team16app.test;

import junit.framework.TestCase;


public class ItemReceipts extends TestCase
{
	//Edited by Tiancheng Shen
		// Test for US06.03.01
		public void DeletePhotoTest(){
			Item photo = new Item("A");
			photo.addPhoto();
			photo.deletePhoto();
			assertFalse("Photo deleted", photo.getPhoto().equals("A"));
		}


}
