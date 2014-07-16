package com.rubeus.wonderbooks;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.rubeus.wonderbooks.entity.Book;

public class ShowBookInfoActivity extends Activity {
	private TextView title, subtitle, description, publisher, publishedDate, rating, ratingCount, infoLink;
	private ImageView thumbnail;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_book_info);
		
		title = (TextView) findViewById(R.id.bookTitle);
		subtitle = (TextView) findViewById(R.id.bookSubtitle);
		description = (TextView) findViewById(R.id.bookDescription);
		publisher = (TextView) findViewById(R.id.bookPublisher);
		publishedDate = (TextView) findViewById(R.id.bookPublishedDate);
		rating = (TextView) findViewById(R.id.bookRating);
		ratingCount = (TextView) findViewById(R.id.bookRatingCount);
		infoLink = (TextView) findViewById(R.id.bookInfoLink);
		thumbnail = (ImageView) findViewById(R.id.bookThumbnail);
		
		disableAllViews();
		
		Intent intent = getIntent();
		Bundle extras = intent.getExtras();
		Book book = new Book();
	    if(extras != null) {
	    	book = (Book) intent.getParcelableExtra("book");
	    	
	    	if(book.getTitle() != null){
	    		title.setVisibility(View.VISIBLE);
	    		title.setText(book.getTitle());
	    	}
	    }
	}
	
	private void disableAllViews(){
		title.setVisibility(View.INVISIBLE);
		subtitle.setVisibility(View.INVISIBLE);
		description.setVisibility(View.INVISIBLE);
		publisher.setVisibility(View.INVISIBLE);
		publishedDate.setVisibility(View.INVISIBLE);
		rating.setVisibility(View.INVISIBLE);
		ratingCount.setVisibility(View.INVISIBLE);
		infoLink.setVisibility(View.INVISIBLE);
		thumbnail.setVisibility(View.INVISIBLE);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.show_book_info, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
