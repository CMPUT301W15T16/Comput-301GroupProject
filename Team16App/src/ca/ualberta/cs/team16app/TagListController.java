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
					throw new RuntimeException("Could not deserialize TagList from TagListManager");			
				} catch (IOException e) {
											
					e.printStackTrace();			
					throw new RuntimeException("Could not deserialize TagList from TagListManager");			
				}					
			}			
			return tagList;			
		}
		
		
		static public void saveTagList(){
		
			try {
			
				TagListManager.getManager().saveTagList(getTagList());
				
			} catch (IOException e) {
			
				e.printStackTrace();
				
				throw new RuntimeException("Could not deserialize TagList from TagListManager");
				
			}
			
		}		
		
		
		
		public void addTag(Tag tag) {
		
			getTagList().addTag(tag);	
			
		}
		
		public void removeTag(Tag tag) {
			
			getTagList().removeTag(tag);
				
		}
		
		
	}




