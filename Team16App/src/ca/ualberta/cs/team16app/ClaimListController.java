package ca.ualberta.cs.team16app;

import java.io.IOException;

public class ClaimListController {

	//based on eclass video by Abram Hindle:https://www.youtube.com/watch?v=uat-8Z6U_Co
			
	private static ClaimList claimList = null;
			
	//Warning: throws a runTimeException
	
	static public ClaimList getClaimList(){
	
		if(claimList == null){
		
			try {
			
				claimList = ClaimListManager.getManager().loadClaimList();
				
				claimList.addListener(new Listener() {
								
					@Override
					
					public void update() {  //updating the claimlist to save it
					
						saveClaimList();					
						
					}
					
				});
					
			} catch (ClassNotFoundException e) {		
				e.printStackTrace();				
				throw new RuntimeException("Could not deserialize ClaimList from ClaimListManager");			
			} catch (IOException e) {
										
				e.printStackTrace();			
				throw new RuntimeException("Could not deserialize ClaimList from ClaimListManager");			
			}					
		}			
		return claimList;			
	}
	
	
	static public void saveClaimList(){
	
		try {
		
			ClaimListManager.getManager().saveClaimList(getClaimList());
			
		} catch (IOException e) {
		
			e.printStackTrace();
			
			throw new RuntimeException("Could not deserialize ClaimList from ClaimListManager");
			
		}
		
	}		
	
	
	
	public void addClaim(Claim claim) {
	
		getClaimList().addClaim(claim);	
		
	}
	
	
	public void removeClaim(Claim claim) {
	
		getClaimList().removeClaim(claim);
			
	}
}


