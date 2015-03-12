package ca.ualberta.cs.team16app.test;

import junit.framework.TestCase;


public class ClaimTagsTest extends TestCase
{
	// Test for US03.01.01
			public void AddTagTest() {
			    TagList taglist = new TagList();
			    String Tag = "A";
			    taglist.addTag(Tag);
			    assertTrue("Tag added", Tag.equals(taglist.getTag()));
			}
			
			// Test for US03.02.01
			public void ManageTagTest() {
				TagList taglist = new TagList;
			    
			    // rename tag
				String Tag1 = "B";
				String Tag2 = "C";
			    taglist.addTag(Tag1);
			    taglist.renameTag(Tag1,Tag2);
			    assertFalse("Old tag deleted", Tag1.equals(taglist.getTag()));
			    assertTrue("Tag renamed", Tag2.equals(taglist.getTag()));
			    
			    // delete tag 
			    String Tag3 = "C";
			    taglist.addTag(Tag3);
			    taglist.deleteTag(Tag3);
			    assertFalse("Tag deleted", Tag3.equals(taglist.getTag()));
			}
			
			// Test for US03.03.01
			public void FilterByTagTest(){
				TagList taglist = new TagList();
				Claim claim = new Claim();
				ClaimList claimlist = new ClaimList();
				taglist.addTag("D");S
				String filter = "D";
				int tagCount = taglist.getTagCount();
				for (int i=0; i<tagCount; i++){
					if (filter==taglist.getTagPosition(i)){
						claimlist.add(claim);
					}
				}
				assertTrue("Filter works", claim.equal(claimlist.getClaim()));
			}
			
}
