package com.rubeus.wonderbooks;

import com.google.zxing.integration.android.IntentIntegrator;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;

public class ScanBookFragment extends Fragment{
	private static final String TAG = "ScanBookFragment";
	private static final int SCAN_BOOK = 1;
	private ImageView scanBookButton;

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
		scanBookButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				IntentIntegrator scanIntegrator = new IntentIntegrator(getActivity());
				scanIntegrator.initiateScan();
			}
		});
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		
		switch(requestCode){
			case SCAN_BOOK:
				break;
		}
	}
}
