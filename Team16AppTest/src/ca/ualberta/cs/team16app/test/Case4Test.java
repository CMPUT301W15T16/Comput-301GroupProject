package ca.ualberta.cs.team16app.test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import ca.ualberta.cs.team16app.Claim;
import ca.ualberta.cs.team16app.ClaimList;
import ca.ualberta.cs.team16app.Expense;
import ca.ualberta.cs.team16app.ExpenseList;
import android.content.ClipData.Item;
import android.widget.ListView;
import android.widget.TextView;
import junit.framework.TestCase;

//All tests in this class edited by omoyeni
public class Case4Test extends TestCase
{

    //qtan
    //UC 04.06.01 Test Cases:
    //make sure the edit works for Expenses
    public void testEditItem() {
    	Expense expense1 = new Expense("expenseName", "fuel", "spend", "CAN","des");
    	
    	assertTrue("Name not set", expense1.getName().toString().equals("expenseName"));
    	assertTrue("Category not set", expense1.getCategory().toString().equals("fuel"));
    	assertTrue("Spend not set", expense1.getSpend().toString().equals("spend"));
    	assertTrue("Currency not set", expense1.getCurrency().toString().equals("CAN"));
    	assertTrue("Description not set", expense1.getDescription().toString().equals("des"));

		expense1.setName("expense2");
		expense1.setCategory("parking");
		expense1.setSpend("12");
		expense1.setCurrency("USD");
		expense1.setDescription("des2");

		assertTrue("Name not changed", expense1.getName().toString().equals("expense2"));
    	assertTrue("Category not changed", expense1.getCategory().toString().equals("parking"));
    	assertTrue("Spend not changed", expense1.getSpend().toString().equals("12"));
    	assertTrue("Currency not changed", expense1.getCurrency().toString().equals("USD"));
    	assertTrue("Description not changed", expense1.getDescription().toString().equals("des2"));
    }
    
    
    
    //qtan
     //UC 04.07.01 Test Cases
    //make sure the delete works (EXPENSE ITEM LIST)
    public void testDeleteItem() {
    	Expense expense1 = new Expense("expenseName", "fuel", "spend", "USD","des");
    	
    	ExpenseList expenseList = new ExpenseList();
    	
    	expenseList.addExpense(expense1);
    	expenseList.deleteExpense(expense1);
        
    	assertTrue("Expense not deleted", expenseList.size()== 0);

    }

  //qtan
    //UC 04.08.01
    // we can test it by select the Claimant after log in as "claimant" 
    //then select a claim by clicking to selcet the ItemList
    //to see if it is work
    
    //qtan
//UC 04.01.01 Test Cases to test that you can make more than one expense item
	public void testClaimComplete(){
	    
		Expense expense1 = new Expense("expenseName", "fuel", "spend", "CAN","des");
		
		Expense expense2 = new Expense("expenseName2", "registration", "spend2", "USD","des2");
		
		ExpenseList expenseList = new ExpenseList();
		expenseList.addExpense(expense1);
		expenseList.addExpense(expense2);
		
		assertTrue("expense1 not part of expenseList", expenseList.contains(expense1));
		assertTrue("expense2 not in expenseList", expenseList.contains(expense2));
	}
	
 
    //qtan
    //UC 04.02.01 Test Cases:
    //make sure the category for expense item is  one of air fare, ground transport, 
	//vehicle rental, private automobile, fuel, parking, registration, accommodation, 
	//meal, or supplies ---- SPINNERS WERE ADDED TO SOLVE THIS
    
   
    //qtan
    //UC 04.03.01 Test Cases:
    //make sure the currency is one of CAD, USD, EUR, GBP, CHF, JPY, or CNY
	// ----SPINNERS WERE ADDED TO SOLVE THIS -------
    
    
	
    //qtan
    //UC 04.05.01 Test Cases: view an Item item and its details
    public void testViewItem() {
    	Expense expense1 = new Expense("expenseName", "fuel", "spend", "CAN","des");
    	
    	assertNotNull("Name not changed", expense1.getName());
    	assertNotNull("Category not changed", expense1.getCategory());
    	assertNotNull("Spend not changed", expense1.getSpend());
    	assertNotNull("Currency not changed", expense1.getCurrency());
    	assertNotNull("Description not changed", expense1.getDescription());
    }
    
    
    /*   
    //qtan
    //UC 04.04.01 Test Case: create a new expense item and flag as incomplete,
    //then test if we can get the flag
    public void testAddFlag() {
	// create an expense 
    Expense expense1 = new Expense("expenseName", "vehicle rental", "spend", "USD","des");
	
    //add flag
    Status status = Status.Incomplete;
	}
*/
			

    
    
}
    