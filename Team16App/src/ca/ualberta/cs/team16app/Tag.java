/**
 * this class is a model use for getting Tags
 * 
 * @author Qi Tan
 */

package ca.ualberta.cs.team16app;

import java.io.Serializable;

public class Tag implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6965660421769006976L;
	private String tagName;
	private String claimname;
	
	public Tag(String tagName,String claimname){
		super();
		this.tagName=tagName;
		this.claimname=claimname;
	}

	public String getTagName() {
		return this.tagName;
	}
	public  String getClaimName(){
		return claimname;
	}
	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
	public String toString(){
		return this.claimname+"         Tag:"+this.tagName;
		
	}
	
}