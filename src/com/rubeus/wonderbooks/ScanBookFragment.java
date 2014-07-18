package com.rubeus.wonderbooks;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.rubeus.wonderbooks.asynctask.SearchBook;

public class ScanBookFragment extends Fragment{
	private static final String TAG = "ScanBookFragment";
	public static final int SHOW_BOOK_INFO = 1;
	private String apiKey = "AIzaSyBEl3tMJ3W0BwvAgp-CCjBjn6UosKc4MH8";
	private ImageView scanBookButton;
	private EditText enterISBN;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	@Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(1);
    }
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_scan_book, container, false);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		
		scanBookButton = (ImageView) view.findViewById(R.id.scanBook);
		enterISBN = (EditText) view.findViewById(R.id.enterISBN);
		
		scanBookButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				IntentIntegrator scanIntegrator = new IntentIntegrator(ScanBookFragment.this);
				scanIntegrator.initiateScan();
			}
		});
		
		enterISBN.setOnKeyListener(new OnKeyListener() {
			
			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)){
					String content = enterISBN.getText().toString();
					String bookSearchString = "https://www.googleapis.com/books/v1/volumes?q=isbn:" + content
							+ "&key=" + apiKey;
					Log.v(TAG, "Start asynctask SearchBook");
					new SearchBook(ScanBookFragment.this, content).execute(bookSearchString);
					return true;
				}
				return false;
			}
		});
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);	
		IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
		if(scanningResult != null){			
			String content = scanningResult.getContents();
			String format = scanningResult.getFormatName();
			Log.v(TAG, "Content scanned: " + content + " of format: " + format);
			if(content!=null && format!=null && format.equalsIgnoreCase("EAN_13")){				
				String bookSearchString = "https://www.googleapis.com/books/v1/volumes?q=isbn:" + content
						+ "&key=" + apiKey;
				Log.v(TAG, "Start asynctask SearchBook");
				new SearchBook(this, content).execute(bookSearchString);
			}
		}
	}
}
