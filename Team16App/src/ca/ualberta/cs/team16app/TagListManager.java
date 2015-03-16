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
	
	public static void initManager(Context context){
		if(tagListManager == null){
			if(context ==null){
				throw new RuntimeException("missing context for TagListManager");
			}
			tagListManager = new TagListManager(context);
		}		
	}
	
	public static TagListManager getManager(){
		if(tagListManager == null){			
				throw new RuntimeException("Did not initialize Manager");
		}
		return tagListManager;
	}
	
	
	public TagListManager(Context context) {
		
		this.context = context;
	}
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
	
	static public TagList tagListFromString(String tagListData) throws ClassNotFoundException, IOException {
		ByteArrayInputStream bi = new ByteArrayInputStream(Base64.decode(tagListData, Base64.DEFAULT));
		ObjectInputStream oi = new ObjectInputStream(bi);
		return (TagList) oi.readObject();
		
		
		
	}
	static public String tagListToString(TagList el) throws IOException {
	
		ByteArrayOutputStream bo = new ByteArrayOutputStream();
		ObjectOutputStream oo = new ObjectOutputStream(bo);
		oo.writeObject(el);
		oo.close();
		byte bytes[] = bo.toByteArray();
		
		return Base64.encodeToString(bytes,Base64.DEFAULT);
	}
	
	public void saveTagList(TagList el) throws IOException{
		SharedPreferences settings = context.getSharedPreferences(prefFile, Context.MODE_PRIVATE);
		Editor editor = settings.edit();
		editor.putString(elKey, tagListToString(el));
		editor.commit();
		
		
	}
}
