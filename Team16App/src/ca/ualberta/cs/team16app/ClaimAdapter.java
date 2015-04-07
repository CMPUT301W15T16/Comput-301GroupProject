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
package ca.ualberta.cs.team16app;



import java.util.ArrayList;



import android.content.Context;
import android.graphics.Typeface;
import android.widget.ArrayAdapter;



/**
 * This is the CustomStoryAdapter Class. This custom adapter is used to populate
 * the ListView of the Offline and of the Online Library.
 * 
 * @author Di Meng
 * 
 */
public class ClaimAdapter extends ArrayAdapter<Claim> {
	Context context;
	int layout_row_id;
	Typeface font;
	Typeface font2;

	ArrayList<Claim> storedlibrary; // This is storedLibrary that is
									// initially passed to this class.
									// This library never changes. Unless
									// An element is deleted.

	ArrayList<Claim> library; // This is the FilteredLibrary that
								// is displayed on the ListView.
								// This library changes if stories
								// are deleted or if someone filters
								// the result.

	public ClaimAdapter(Context context, int layout_row_id,
			ArrayList<Claim> library) {

		super(context, layout_row_id, library);

		this.context = context;
		this.layout_row_id = layout_row_id;
		this.library = library;
		this.storedlibrary = new ArrayList<Claim>(library);
		font = Typeface.createFromAsset(context.getAssets(),
				"fonts/straightline.ttf");
	}

	/**
	 * Get the size of the library.
	 */
	@Override
	public int getCount() {
		return library.size();
	}

	/**
	 * Get the Story object at the given position.
	 */
	@Override
	public Claim getItem(int position) {
		return library.get(position);
	}

	/**
	 * Get the index of a Story given its position.
	 */
	@Override
	public long getItemId(int position) {
		return library.indexOf(getItem(position));
	}

}
