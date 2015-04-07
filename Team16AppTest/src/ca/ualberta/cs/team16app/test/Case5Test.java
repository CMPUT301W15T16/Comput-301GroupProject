package ca.ualberta.cs.team16app.test;

import android.app.Activity;
import android.test.ViewAsserts;
import android.text.Html.ImageGetter;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import ca.ualberta.cs.team16app.Claim;
import ca.ualberta.cs.team16app.ClaimList;
import ca.ualberta.cs.team16app.ClaimListController;
import ca.ualberta.cs.team16app.Expense;
import ca.ualberta.cs.team16app.ExpenseList;
import ca.ualberta.cs.team16app.ViewReceiptActivity;
import junit.framework.TestCase;


public class Case5Test extends TestCase
{
	// written by Di Meng  US05.01.01 as a Claimant view the item expenses details and photo
	public void testSubmittedItems() {
		// init the controller
		//ClaimListController list = new ItemListController();
		// set all values
		Expense expense = new Expense("view test", "fuel", "spend", "CAN","test comment");
		
		ExpenseList expenseList = new ExpenseList();
		expenseList.addExpense(expense);
		Expense output = expenseList.getPosition(0);
		
		// do the test
		assertEquals("start date not same",expense.getDate(),output.getDate());
		assertEquals("end date not same",expense.getCategory(),output.getCategory());
		assertEquals("catagory not same",expense.getCurrency(),output.getCurrency());
		assertEquals("name not same",expense.getName(),output.getName());
		assertEquals("Description not equal","test comment",expense.getDescription());
	}
	
	/*public void testViewPhoto() {
		ViewReceiptActivity activity = getActivity();
		ImageButton view = (ImageButton) activity.findViewById(R.id.tagview);
		ViewAsserts.assertOnScreen(activity.getWindow().getDecorView(),view);
		assertNotNull("not empty",view.getDrawable());
		assertTrue(View.VISIBLE == view.getVisibility());
		
	}*/


}
