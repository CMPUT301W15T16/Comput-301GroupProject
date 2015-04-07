/**
 * Team16App: travel expense tracking application
 * Copyright (C) 2015 peijen  Chris Lin 
 * dmeng  Di Meng 
 * tshen
 * qtan  Qi Tan 
 * yuentung  
 * omoyeni  Omoyeni Adeyemo 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.

 */
/**
 * this class is a model use for getting Tags
 * 
 * @author Qi Tan
 */

package ca.ualberta.cs.team16app;

import java.io.Serializable;

public class Tag implements Serializable{
	
	/**
	 * tag save and get function
	 */
	private static final long serialVersionUID = 6965660421769006976L;
	private String tagName;
	private String claimname;
	/**
	 * constructor for tag
	 * @param tagName the tag name
	 * @param claimname the claim name linked to the tag
	 * save as Tag
	 */
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
	// for later use in show claimname     tag:tagname
	public String toString(){
		return this.claimname+"         Tag:"+this.tagName;
		
	}
	
}