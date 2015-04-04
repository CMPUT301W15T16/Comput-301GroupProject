package ca.ualberta.cs.team16app.elasitcSearch;

/*
 * Copyright (C) <2013>  <Chenlei Zhang, Minhal Syed>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import android.util.Log;

import ca.ualberta.cs.team16app.Claim;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * This is the Elastic Search Client Class. This class is responsible for
 * communicating with WebServer. This class can be used to insert, delete,
 * modify, and search stories on the WebServer.
 * 
 * This code has been taken and modified from:
 * https://github.com/rayzhangcl/ESDemo
 * 
 * @author Chenlei Zhang - Original Owner
 * @author Minhal Syed - Modified Original Code
 */
public class ESClient {

	// Http Connector
	private HttpClient httpclient = new DefaultHttpClient();

	// JSON Utilities
	private Gson gson = new Gson();

	public static final String WEBSERVICE_URI = "http://cmput301.softwareprocess.es:8080/cmput301w15t10/";
	public static final String CLAIM_FOLDER = "claim/";
	public static final String SEARCH_PRETTY = "_search?pretty=1&q=";
	public static final int MAX_STORIES = 20;
	/**
	 * This function stores a claim on the WebServer based on the 's ID. If
	 * a new claim with the same Id is inserted, the previous one is
	 * overwritten.
	 * 
	 * Consumes the POST/Insert operation of the service
	 * 
	 * @throws IOException
	 * @throws IllegalStateException
	 */
	public void insertClaim(Claim claim) throws IOException {

		HttpPost httpPost = new HttpPost(WEBSERVICE_URI + CLAIM_FOLDER
				+ claim.getClaimId());

		StringEntity stringentity = null;

		try {
			stringentity = new StringEntity(gson.toJson(claim));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		httpPost.setHeader("Accept", "application/json");

		httpPost.setEntity(stringentity);

		HttpResponse response = null;
		try {
			response = httpclient.execute(httpPost);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		String status = response.getStatusLine().toString();
		System.out.println(status);
		HttpEntity entity = response.getEntity();
		BufferedReader br = new BufferedReader(new InputStreamReader(
				entity.getContent()));
		String output;
		System.err.println("Output from Server -> ");
		while ((output = br.readLine()) != null) {
			System.err.println(output);
		}

	}

	/**
	 * Consumes the Get operation of the service
	 * 
	 * Given a claimId, It returns a claimObject from The Server.
	 * 
	 * @param claimID
	 * @return claim -- Returns Null if no claim Found
	 */
	public Claim getClaim(String claimID) {

		Claim claim = null;
		try {
			HttpGet getRequest = new HttpGet(WEBSERVICE_URI + CLAIM_FOLDER
					+ claimID);

			getRequest.addHeader("Accept", "application/json");

			HttpResponse response = httpclient.execute(getRequest);

			String status = response.getStatusLine().toString();
			System.out.println(status);

			String json = getEntityContent(response);

			// We have to tell GSON what type we expect
			Type elasticSearchResponseType = new TypeToken<ElasticSearchResponse<Claim>>() {
			}.getType();
			// Now we expect to get a claim response
			ElasticSearchResponse<Claim> esResponse = gson.fromJson(json,
					elasticSearchResponseType);

			// We get the claim from it
			claim = esResponse.getSource();

			// getRequest.releaseConnection();

		} catch (ClientProtocolException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();
		}

		return claim;
	}

	/**
	 * This function is a private function and should not be called by anyone.
	 * This function generates a URI that is needed in order to query some claim
	 * information from the WebServer.
	 * 
	 * @param str
	 * @return URI
	 * @throws UnsupportedEncodingException
	 */
	private String getQueryHttpURI(String str)
			throws UnsupportedEncodingException {
		String queryHttpURI = null;
		queryHttpURI = WEBSERVICE_URI + CLAIM_FOLDER + SEARCH_PRETTY
				+ java.net.URLEncoder.encode(str, "UTF-8")+"&size=" + MAX_STORIES;
		return queryHttpURI;
	}

	/**
	 * This function returns all the stories on the server, as an ArrayList of
	 * Stories.
	 * 
	 * @return allStories
	 */
	public ArrayList<Claim> getAllClaims() {
		ArrayList<Claim> allClaims = null;
		allClaims = this.searchClaims("*");
		Log.d("DEBUG", "" + allClaims.size());
		return allClaims;
	}

	/**
	 * search by keywords
	 */
	public ArrayList<Claim> searchClaims(String Keyword) {
		try {
			ArrayList<Claim> filteredStories = new ArrayList<Claim>();

			HttpGet searchRequest = new HttpGet(this.getQueryHttpURI(Keyword));

			searchRequest.setHeader("Accept", "application/json");
			HttpResponse response = httpclient.execute(searchRequest);
			String status = response.getStatusLine().toString();
			System.out.println(status);

			String json = getEntityContent(response);

			Type elasticSearchSearchResponseType = new TypeToken<ElasticSearchSearchResponse<Claim>>() {
			}.getType();

			ElasticSearchSearchResponse<Claim> esResponse = gson.fromJson(json,
					elasticSearchSearchResponseType);
			System.err.println(esResponse);
			for (ElasticSearchResponse<Claim> r : esResponse.getHits()) {
				Claim claim = r.getSource();
				filteredStories.add(claim);
			}
			// searchRequest.releaseConnection();

			return filteredStories;
		} catch (Exception e) {
			return null;
		}
	}

	public void deleteURI(String URI) {
		try {

			HttpDelete httpDelete = new HttpDelete(URI);
			httpDelete.addHeader("Accept", "application/json");

			HttpResponse response = httpclient.execute(httpDelete);

			String status = response.getStatusLine().toString();
			System.out.println(status);

			HttpEntity entity = response.getEntity();
			BufferedReader br = new BufferedReader(new InputStreamReader(
					entity.getContent()));
			String output;
			System.err.println("Output from Server -> ");
			while ((output = br.readLine()) != null) {
				System.err.println(output);
			}

			// EntityUtils.consume(entity); httpDelete.releaseConnection();

		} catch (Exception e) {

		}
	}

	/**
	 * Delete a claim Object from WebServer. A claim is deleted based on its
	 * claimId.
	 * 
	 */
	public void deleteClaim(Claim claim) {
		if (this.claimExists(claim)) {
			String URI = WEBSERVICE_URI + CLAIM_FOLDER + claim.getClaimId();
			this.deleteURI(URI);
		}
	}

	/**
	 * get the http response and return json string
	 */
	String getEntityContent(HttpResponse response) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(
				(response.getEntity().getContent())));
		String output;
		System.err.println("Output from Server -> ");
		String json = "";
		while ((output = br.readLine()) != null) {
			System.err.println(output);
			json += output;
		}
		System.err.println("JSON:" + json);
		return json;
	}

	/**
	 * Given an input claim object, this function checks whether the input claim
	 * is present on the WebServer or not.
	 * 
	 * @param claim
	 * @return boolean claimExists
	 */
	private boolean claimExists(Claim claim) {
		boolean claimExists = false;

		try {
			HttpGet getRequest = new HttpGet(WEBSERVICE_URI + CLAIM_FOLDER
					+ claim.getClaimId());
			getRequest.addHeader("Accept", "application/json");
			HttpResponse response = httpclient.execute(getRequest);
			String json = getEntityContent(response);

			// We have to tell GSON what type we expect
			Type elasticSearchResponseType = new TypeToken<ElasticSearchResponse<Claim>>() {
			}.getType();
			// Now we expect to get a claim response
			ElasticSearchResponse<Claim> esResponse = gson.fromJson(json,
					elasticSearchResponseType);
			// We get the claim from it
			claimExists = esResponse.getExtists();
			// getRequest.releaseConnection();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return claimExists;
	}

}
