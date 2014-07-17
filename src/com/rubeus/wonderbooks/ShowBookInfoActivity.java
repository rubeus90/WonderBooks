package com.rubeus.wonderbooks;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.util.Linkify;
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
	    	if(book.getSubtitle() != null){
	    		subtitle.setVisibility(View.VISIBLE);
	    		subtitle.setText(book.getSubtitle());
	    	}
	    	if(book.getDescription() != null){
	    		description.setVisibility(View.VISIBLE);
	    		description.setText("Description: " + book.getDescription());
	    	}
	    	if(book.getPublisher() != null){
	    		description.setVisibility(View.VISIBLE);
	    		description.setText("Publisher: "+ book.getPublisher());
	    	}
	    	if(book.getPublishedDate() != null){
	    		publishedDate.setVisibility(View.VISIBLE);
	    		publishedDate.setText("Published date: " + book.getPublishedDate());
	    	}
	    	if(book.getAverageRating() != 0.0){
	    		rating.setVisibility(View.VISIBLE);
	    		rating.setText("Rating: " + book.getAverageRating() + "/5");
	    	}
	    	if(book.getRatingCount() != 0){
	    		ratingCount.setVisibility(View.VISIBLE);
	    		ratingCount.setText("Rating count: " + book.getRatingCount() + " rating(s)");
	    	}
	    	if(book.getInfoLink() != null){
	    		infoLink.setVisibility(View.VISIBLE);
	    		infoLink.setText(book.getInfoLink());
	    		Linkify.addLinks(infoLink, Linkify.ALL);
	    	}
	    }
	}
	
	private void disableAllViews(){
		title.setVisibility(View.GONE);
		subtitle.setVisibility(View.GONE);
		description.setVisibility(View.GONE);
		publisher.setVisibility(View.GONE);
		publishedDate.setVisibility(View.GONE);
		rating.setVisibility(View.GONE);
		ratingCount.setVisibility(View.GONE);
		infoLink.setVisibility(View.GONE);
		thumbnail.setVisibility(View.GONE);
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
