package com.rubeus.wonderbooks.asynctask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Fragment;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.rubeus.wonderbooks.entity.Book;

public class SearchBook extends AsyncTask<String, Void, String>{
	private static final String TAG = "SearchBook";
	private Fragment fragment;

	public SearchBook(Fragment fragment){
		this.fragment = fragment;
	}
	
	@Override
	protected String doInBackground(String... urls) {
		Log.v(TAG, "doInBackground starts");
		StringBuilder bookBuilder = new StringBuilder();
		for(String url: urls){
			HttpClient bookClient = new DefaultHttpClient();
			HttpGet bookGet = new HttpGet(url);
			try {
				HttpResponse bookResponse = bookClient.execute(bookGet);
				StatusLine bookSearchStatus = bookResponse.getStatusLine();
				if (bookSearchStatus.getStatusCode()== 200){
					HttpEntity bookEntity = bookResponse.getEntity();
					InputStream bookContent = bookEntity.getContent();
					InputStreamReader bookInput = new InputStreamReader(bookContent);
					BufferedReader bookReader = new BufferedReader(bookInput);
					String lineIn;
					while ((lineIn=bookReader.readLine())!=null) {
					    bookBuilder.append(lineIn);
					}
					Log.v(TAG, "doInBackground succeeded");
					return bookBuilder.toString();
				}
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	protected void onPostExecute(String result) {
		super.onPostExecute(result);
		try {
			JSONObject object = new JSONObject(result);
			JSONArray resultArray = object.getJSONArray("items");
			JSONObject firstObject = resultArray.getJSONObject(0);
			JSONObject book = firstObject.getJSONObject("volumeInfo");
			
			Book newBook = new Book();
			try{
				newBook.setTitle(book.getString("title"));
			}catch(JSONException e){}
			try{
				newBook.setSubtitle(book.getString("subtitle"));
			}catch(JSONException e){}
			try{
				JSONArray authorsArray = book.getJSONArray("authors");
				String authors = "";
				for(int i=authorsArray.length(); i>0; i--){
					if(i!=1){
						authors += authorsArray.getString(i) + ", ";
					}
				}
				newBook.setAuthors(authors);
			}catch(JSONException e){}
			try{
				newBook.setPublisher(book.getString("publisher"));
			}catch(JSONException e){}
			try{
				newBook.setDescription(book.getString("description"));
			}catch(JSONException e){}
			try{
				newBook.setPublishedDate(book.getString("publishedDate"));
			}catch(JSONException e){}
			
			Toast.makeText(fragment.getView().getContext(), "Title: "+newBook.getTitle()+"\n"
					+"Subtitle: "+newBook.getSubtitle()+"\n"
					+"Description: "+newBook.getDescription()+"\n"
					+"Publisher: "+newBook.getPublisher()+"\n"
					+"Published date: "+newBook.getPublishedDate(), Toast.LENGTH_LONG).show();
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
}
