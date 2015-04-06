package ca.ualberta.cs.team16app;

import java.io.IOException;


public class DestListController
{
	//based on eclass video by Abram Hindle:https://www.youtube.com/watch?v=uat-8Z6U_Co
	
		private static DestList destList = null;
				
		//Warning: throws a runTimeException
		
		static public DestList getDestList(){
		
			if(destList == null){
			
				try {
				
					destList = DestListManager.getManager().loadDestList();
					
					destList.addListener(new Listener() {
									
						@Override
						
						public void update() {  //updating the destlist to save it
						
							saveDestList();					
							
						}
						
					});
						
				} catch (ClassNotFoundException e) {		
					e.printStackTrace();				
					throw new RuntimeException("Could not deserialize DestList from DestListManager");			
				} catch (IOException e) {
											
					e.printStackTrace();			
					throw new RuntimeException("Could not deserialize DestList from DestListManager");			
				}					
			}			
			return destList;			
		}
		
		
		static public void saveDestList(){
		
			try {
			
				DestListManager.getManager().saveDestList(getDestList());
				
			} catch (IOException e) {
			
				e.printStackTrace();
				
				throw new RuntimeException("Could not deserialize DestList from DestListManager");
				
			}
			
		}		
		
		
		
		public void addDest(Destination dest) {
		
			getDestList().addDest(dest);	
			
		}
		
		
		public void removeDest(Destination dest) {
		
			getDestList().removeDest(dest);
				
		}
}
