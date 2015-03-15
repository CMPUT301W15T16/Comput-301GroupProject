package ca.ualberta.cs.team16app;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;


public class ExpenseList implements Serializable {

	
	/**
	 * ExpenseList serialization ID
	 */
	private static final long serialVersionUID = -3035358862621688030L;
	protected ArrayList<Expense> expenseList = null;
	protected transient ArrayList<Listener> listeners = null;
	
	public ExpenseList(){
		expenseList = new ArrayList<Expense>();
		listeners = new ArrayList<Listener>();
	}
	
	private ArrayList<Listener> getListeners(){
		if(listeners == null){
			listeners = new ArrayList<Listener>();
		}
		return listeners;
	}

	public Collection<Expense> getExpenses() {
		
		return expenseList;
	}

	public Expense getPosition(int position) {
		return expenseList.get(position);
	}
	public void addExpense(Expense testExpense) {
		expenseList.add(testExpense);
		notifyListeners();
		
	}

	private void notifyListeners() {
		// TODO Auto-generated method stub
		for (Listener listener : getListeners()) {
			listener.update();			
		}		
	}
	


	public void deleteExpense(Expense testExpense) {
		expenseList.remove(testExpense);
		notifyListeners();
		
	}

	public int size() {
		
		return expenseList.size();
	}

	public boolean contains(Expense testExpense) {
	
		return expenseList.contains(testExpense);
	}
	public Expense chooseExpense() {
		int size = expenseList.size();
		/*
		if(size<=0){
			
		}
		*/
		int element = 0;
		return expenseList.get(element);
		
	}
	

	public void addListener(Listener l) {
	
		getListeners().add(l);
		
	}

	public void removeListener(Listener l) {
		
		getListeners().remove(l);
	}

	public void removeExpense(Expense expense) {
		
		expenseList.remove(expense);
		notifyListeners();
	}
	
}
