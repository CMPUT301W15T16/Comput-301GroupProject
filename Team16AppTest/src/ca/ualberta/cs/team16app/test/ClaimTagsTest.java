package ca.ualberta.cs.team16app.test;

import java.util.ArrayList;

import ca.ualberta.cs.team16app.Tag;
import ca.ualberta.cs.team16app.TagList;
import junit.framework.TestCase;


public class ClaimTagsTest extends TestCase
{
	// Test for US03.01.01
			public void testAddTag() {
			    TagList taglist = new TagList();
			    Tag tag = new Tag("A", "B");
			    taglist.addTag(tag);
			    assertTrue("Tag added", tag.equals(taglist.getTags()));
			}
			
			// Test for US03.02.01
			public void testManageTag() {
				TagList taglist = new TagList();
			    
			    //initialize first tag & make sure it is made
				Tag Tag1 = new Tag("A","B");
				assertNotNull("Tag was not made", Tag1);
				
				// rename tag
				Tag1.setTagName("tagName");
			    assertFalse("Old tag name deleted", Tag1.getTagName()
			    		.toString().equals("A"));
			    assertTrue("Tag renamed", Tag1.getTagName()
			    		.toString().equals("tagName"));
			    
			    // delete tag 
			    taglist.addTag(Tag1);
			    taglist.removeTag(Tag1);
			    assertFalse("Tag deleted",(taglist.size()== 0));
			}
			
			// Test for US03.03.01
			public void testFilterByTag(){
				TagList taglist = new TagList();
				Tag tag1 = new Tag("A1", "Claim1");
				Tag tag2 = new Tag ("A1","Claim2");
				Tag tag3 = new Tag ("A2","Claim3");
				
				taglist.addTag(tag1);
				taglist.addTag(tag2);
				taglist.addTag(tag3);
				
				ArrayList<String> claimNames = null;
				if (tag1.getTagName() == "A1"){
					claimNames.add(tag1.getClaimName()); 
				}
				if (tag2.getTagName() == "A1"){
					claimNames.add(tag2.getClaimName()); 
				}
				if (tag3.getTagName() == "A1"){
					claimNames.add(tag3.getClaimName()); 
				}
				
				assertTrue("Cannot filter claims", claimNames.size()== 2);
			}
			
}
