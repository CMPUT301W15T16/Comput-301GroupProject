package com.example.team16app.test;
import java.util.Date;

import android.content.ClipData.Item;

import com.example.team16app.ItemList;

import junit.framework.TestCase;
public class Case4Test extends TestCase{
	//qtan
	//UC 04.01.01 Test Cases to test if one Item claim has a date,category,textual description,amount spent, and unit on its Item item
	public void testItemItems040101(){
	    //Data initial
	    Item Item = Case.newItem();
	    Item Item2 =Case.newItem();
	    Date date1= new Date(111);
	    Date date2= new Date(222);
	    String category1 = "aaa";
	    String category2 = "bbb";
	    String text1 = "ccc";
	    String text2 = "ddd";
	    int amount1 = 1;
	    int amount2 = 2;
	    String unit1 ="CAD";
	    String unit2= "USD";




	    //Add 2 different Item in one Claim Case 
	    Case.addItemDate(Item,date1);
	    Case.addItemDate(Item2,date2);
	    Case.addItemCategory(Item,category1);
	    Case.addItemCategory(Item2,category2);
	    Case.addItemDescription(Item,text1);
	    Case.addItemDescription(Item2,text2);
	    Case.addItemAmountSpent(Item,amount1);
	    Case.addItemAmountSpent(Item2,amount2);
	    Case.addItemUnit(Item,unit1);
	    Case.addItemUnit(Item2,unit2);




	    //test if it is work
	    assertTrue("Date1 is not wrote in", date1.equals(Case.getItemDate(Item)));
	    assertTrue("Date2 is not wrote in", date2.equals(Case.getItemDate(Item2)));
	    assertTrue("Category1 is not worte in", category1.equals(Case.getItemCategory(Item)));
	    assertTrue("Category2 is not worte in", category2.equals(Case.getItemCategory(Item2)));
	    assertTrue("Amount1 is not worte in", amount1.equals(Case.getItemAmount(Item)));
	    assertTrue("Amount2 is not worte in", amount2.equals(Case.getItemAmount(Item2)));
	    assertTrue("Unit1 is not worte in", unit1.equals(Case.getItemUnit(Item)));
	    assertTrue("Unit2 is not worte in", unit2.equals(Case.getItemUnit(Item2)));
	    assertTrue("Description1 is not worte in", text1.equals(Case.getItemDescription(Item)));
	    assertTrue("Description2 is not worte in", text2.equals(Case.getItemDescription(Item2)));
	    
	    
	    
	    //qtan
	    //UC 04.02.01 Test Cases:
	    //make sure the category is  one of air fare, ground transport, vehicle rental, private automobile, fuel, parking, registration, accommodation, meal, or supplies
	    public void testItemItems040102(Item item){
	        String[] myStringArray = {"air fare", "ground transport", "vehicle rental", "private automobile", "fuel", "parking", "registration", "accommodation", "meal","supplies"}
	        assertTrue("out of option", myStringArray.contains(item.getCategory()));
	         }
	    
	    
	    //qtan
	    //UC 04.03.01 Test Cases:
	    //make sure the currency is one of CAD, USD, EUR, GBP, CHF, JPY, or CNY
	    public void testItemItems040103(Item item){
	    	String[] myStringArray2 = {CAD, USD, EUR, GBP, CHF, JPY, CNY}
	    	assertTrue("out of option", myStringArray2.contains(item.getUnit()));
	    }
	    
	    
	    
	    
	    //qtan
	    //UC 04.05.01 Test Cases: view an Item item and its details
	    public void viewItemTest() {
            ItemActivity activity = ItemActivity();
            TextView ItemName = (TextView) activity.findViewById(R.id.ItemName);
            TextView ItemDate = (TextView) activity.findViewById(R.id.ItemDate);
            TextView ItemCategory = (TextView) activity.findViewById(R.id.ItemCategory);
            TextView ItemAmount = (TextView) activity.findViewById(R.id.ItemAmount);
            TextView ItemDescrption = (TextView) activity.findViewById(R.id.ItemDescrption);
            TextView ItemUnit = (TextView) activity.findViewById(R.id.ItemUnit);
            assertNotNull("activity",activity);
            assertNotNull("ItemName",ItemName);
            assertNotNull("ItemDate",ItemDate);
            assertNotNull("ItemCategory",ItemCategory);
            assertNotNull("ItemAmount",ItemAmount);
            assertNotNull("ItemUnit",ItemUnit);
            assertNotNull("ItemDescrption",ItemDescrption);
	    }
	    
	    
	    
	    
	    //qtan
	    //UC 04.06.01 Test Cases:
	    //make sure the edit works
	    public void editItemTest() {
	        Date date1= new Date(111);
	        Date date2= new Date(222);
	        String category1 = "aaa";
	        String category2 = "bbb";
	        String text1 = "ccc";
	        String text2 = "ddd";
	        int amount1 = 1;
	        int amount2 = 2;
	        String unit1 ="CAD";
	        String unit2= "USD";
	        Item Item = new Item(data1, category1, amount1,unit1,text1);
	        Item.setDate(data2);
	        Item.setCategory(category2);
	        Item.setText(amount2);
	        Item.setAmount(unit2);
	        Item.setUnit(text2);

	        assertTrue("Date did not change", date2.equals(Item.getDate()));
	        assertTrue("Category did not change", category2.equals(Item.getStartCategory()));
	        assertTrue("Unit did not change", unit2.equals(Item.getUnit()));
	        assertTrue("Amount did not change", amount2.equals(Item.getAmount()));
	        assertTrue("Description did not change", text2.equals(Item.getDescription()));
	    }
	    
	    
	    
	    //qtan
	     //UC 04.07.01 Test Cases
	    //make sure the delete works
	    public void deleteItemTest() {
	        Date date1= new Date(111);
	        Date date2= new Date(222);
	        String category1 = "aaa";
	        String text1 = "ccc";       
	        int amount1 = 1;
	        String unit1 ="CAD";
	        Item item = new item(data1, category1, amount1,unit1,text1);
	        ItemList itemList = new ItemList();
	        ItemList.addItem(item);
	        assertTrue("Item list size isn's big enough",ItemList.size() == 1);
	        assertTrue("not created",ItemList.contains(item));
	        claimList.deleteItem(item);
	        assertTrue("Item list size isn't small enough",ItemList.size() == 0);
	        assertFalse("Test Item still contained?",ItemList.contains(item));
	    


}


}
