package ca.ualberta.cs.team16app;


import java.io.IOException;

import ca.ualberta.cs.team16app.Expense;
import ca.ualberta.cs.team16app.ExpenseList;
import ca.ualberta.cs.team16app.ExpenseListManager;
import ca.ualberta.cs.team16app.Listener;



public class ExpenseListController {

	
	//based on eclass video by Abram Hindle:https://www.youtube.com/watch?v=uat-8Z6U_Co
		private static ExpenseList expenseList = null;
		//Warning: throws a runTimeException
		static public ExpenseList getExpenseList(){
			if(expenseList == null){
				try {
					expenseList = ExpenseListManager.getManager().loadExpenseList();
					expenseList.addListener(new Listener() {
						
						@Override
						public void update() {  //updating the expenselist to save it
							saveExpenseList();					
						}
					});
					
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
					throw new RuntimeException("Could not deserialize ExpenseList from ExpenseListManager");
				} catch (IOException e) {
					
					e.printStackTrace();
					throw new RuntimeException("Could not deserialize ClaimList from ExpenseListManager");
				}			
			}		
			return expenseList;		
		}
		
		static public void saveExpenseList(){
			try {
				ExpenseListManager.getManager().saveExpenseList(getExpenseList());
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException("Could not deserialize ClaimList from ClaimListManager");
			}
		}		
		
		public void addExpense(Expense expense) {
			getExpenseList().addExpense(expense);
			
		}
	
	
}
