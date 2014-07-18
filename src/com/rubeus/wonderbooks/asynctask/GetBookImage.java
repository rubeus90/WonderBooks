package com.rubeus.wonderbooks.asynctask;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import com.rubeus.wonderbooks.ShowBookInfoActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

public class GetBookImage extends AsyncTask<String, Void, Bitmap>{

	@Override
	protected Bitmap doInBackground(String... url) {
		URL thumbURL;
		try {
			thumbURL = new URL(url[0]);
			URLConnection thumbConn = thumbURL.openConnection(); 
			thumbConn.connect();
			InputStream thumbIn = thumbConn.getInputStream(); 
			BufferedInputStream thumbBuff = new BufferedInputStream(thumbIn);
			
			Bitmap thumb = BitmapFactory.decodeStream(thumbBuff);
			thumbBuff.close(); 
			thumbIn.close();
			
			return thumb;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	protected void onPostExecute(Bitmap result) {
		super.onPostExecute(result);
		if(result != null){
			ShowBookInfoActivity.setThumbnail(result);
		}
	}
}
