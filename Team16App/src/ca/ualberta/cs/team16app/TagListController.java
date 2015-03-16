package ca.ualberta.cs.team16app;
import java.io.IOException;

public class TagListController {
	//based on eclass video by Abram Hindle:https://www.youtube.com/watch?v=uat-8Z6U_Co
	
		private static TagList tagList = null;
				
		//Warning: throws a runTimeException
		
		static public TagList getTagList(){
		
			if(tagList == null){
			
				try {
				
					tagList = TagListManager.getManager().loadTagList();
					
					tagList.addListener(new Listener() {
									
						@Override
						
						public void update() {  //updating the taglist to save it
						
							saveTagList();					
							
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
			return tagList;			
		}
		
		
		static public void saveTagList(){
		
			try {
			
				TagListManager.getManager().saveTagList(getTagList());
				
			} catch (IOException e) {
			
				e.printStackTrace();
				
				throw new RuntimeException("Could not deserialize ClaimList from ClaimListManager");
				
			}
			
		}		
		
		
		
		public void addTag(Tag tag) {
		
			getTagList().addTag(tag);	
			
		}
		
		
		
		
	}




