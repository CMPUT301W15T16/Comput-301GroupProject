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
 * This is a Taglist Manager
 * @author Qi Tan
 */
package ca.ualberta.cs.team16app;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Base64;

public class TagListManager {

	static final String prefFile = "CtagList";  //const
	static final String elKey = "tagList";     //const
	Context context;
	
	
	//base on eclass youtube video by Abram Hindle:https://www.youtube.com/watch?v=uat-8Z6U_Co
	//allow us to get taglist manager	
	static private TagListManager tagListManager = null;
	/**
	 * 
	 * @param context the context 
	 */
	public static void initManager(Context context){
		if(tagListManager == null){
			if(context ==null){
				throw new RuntimeException("missing context for TagListManager");
			}
			tagListManager = new TagListManager(context);
		}		
	}
	/**
	 * call the manager
	 * @return
	 */
	
	public static TagListManager getManager(){
		if(tagListManager == null){			
				throw new RuntimeException("Did not initialize Manager");
		}
		return tagListManager;
	}
	/**
	 * call the page
	 * @param context
	 */
	
	public TagListManager(Context context) {
		
		this.context = context;
	}
	/**
	 * load tags
	 * @return
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public TagList loadTagList() throws ClassNotFoundException, IOException{
		SharedPreferences settings = context.getSharedPreferences(prefFile, Context.MODE_PRIVATE);
		String tagListData = settings.getString(elKey,"");
		// check if the list is empty
		if(tagListData.equals("")){
			return new TagList();  //if it's empty, create a new list
		}
		else{
			return tagListFromString(tagListData);  //return list
		}	 
	}
	/**
	 * get the taglist
	 * @param tagListData the taginfo
	 * @return
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	static public TagList tagListFromString(String tagListData) throws ClassNotFoundException, IOException {
		ByteArrayInputStream bi = new ByteArrayInputStream(Base64.decode(tagListData, Base64.DEFAULT));
		ObjectInputStream oi = new ObjectInputStream(bi);
		return (TagList) oi.readObject();
		
		
		
	}
	/**
	 * To chanage the tag in taglist to string
	 * @param el
	 * @return
	 * @throws IOException
	 */
	static public String tagListToString(TagList el) throws IOException {
	
		ByteArrayOutputStream bo = new ByteArrayOutputStream();
		ObjectOutputStream oo = new ObjectOutputStream(bo);
		oo.writeObject(el);
		oo.close();
		byte bytes[] = bo.toByteArray();
		
		return Base64.encodeToString(bytes,Base64.DEFAULT);
	}
	/**
	 * 
	 * @param el
	 * @throws IOException
	 * to save the laglist
	 */
	public void saveTagList(TagList el) throws IOException{
		SharedPreferences settings = context.getSharedPreferences(prefFile, Context.MODE_PRIVATE);
		Editor editor = settings.edit();
		editor.putString(elKey, tagListToString(el));
		editor.commit();
		
		
	}
}
