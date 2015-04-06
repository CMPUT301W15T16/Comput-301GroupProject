package ca.ualberta.cs.team16app;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Locale;
import java.util.UUID;

import android.content.Context;
import android.util.Log;


public class ClaimManager {
	private Context activityContext;
	private Claim currentclaim;
	//private Option currentOption;

	// Singleton
	private static ClaimManager instance = null;

	public static ClaimManager getInstance() {
		if (instance == null) {
			instance = new ClaimManager();
		}
		return instance;
	}

	/**
	 * Initiates the context that is using the claimManager.
	 * 
	 * @param someContext
	 *            the context of the activity
	 */
	public void initContext(Context someContext) {
		this.activityContext = someContext;
	}

	/**
	 * This sets the current claim
	 * 
	 * @param A
	 *            claim the current claim
	 */
	public void setCurrentclaim(Claim claim) {
		this.currentclaim = claim;
	}

	/**
	 * This gets the current claim
	 * 
	 * @return The current claim
	 */
	public Claim getCurrentclaim() {
		return currentclaim;
	}

	/**
	 * Create a claim in internal storage.
	 * 
	 * @param claim
	 *            the claim to be created
	 * @return false if claim already exists, true otherwise
	 */
	public boolean createclaim(String name, String start,
			String end, String des,boolean overwrite
			) {

		Claim newclaim = new Claim(name, start, end,
				des);

		boolean claimExists = saveclaim(newclaim, overwrite);
		return claimExists;
	}

	/**
	 * Save a claim to internal storage.
	 * 
	 * @param saveclaim
	 *            the claim to be saved
	 * @param overwrite
	 *            set overwrite to true(for editing purposes), where the
	 *            existing file in the storage is overwritten if it already
	 *            exists
	 * @return false if claim already exists, true otherwise
	 */
	public boolean saveclaim(Claim claim, Boolean overwrite) {

		String FILENAME = claim.getFilename();

		if (checkFileExists(FILENAME) == false || overwrite == true) {
			try {
				FileOutputStream fos = activityContext.openFileOutput(FILENAME,
						0);
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				oos.writeObject(claim);
				Log.d("Successful claim Save: ", claim.getName());
				fos.close();
				this.currentclaim = claim;
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return true;
		}
		return false;
	}

	/**
	 * Check if file with name FILENAME already exists in the app directory.
	 * 
	 * @param FILENAME
	 * @return true if a file already exists, false otherwise
	 */
	public boolean checkFileExists(String FILENAME) {

		String[] files = activityContext.getApplicationContext().fileList();

		for (String fileStr : files) {
			// check if file exists
			if (fileStr.toLowerCase(Locale.getDefault()).contains(".sav")) {
				if (fileStr.toLowerCase(Locale.getDefault()).equals(FILENAME)) {
					return true;
				}
			}
		}
		return false;
	}

	
}
