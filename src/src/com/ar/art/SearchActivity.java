package com.ar.art;

import java.util.ArrayList;
import java.util.List;

import tpAndroid.main.Item;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;

public class SearchActivity  extends Activity {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    
		super.onCreate(savedInstanceState);
	    
	    setContentView(R.layout.productsview);

	    // Get the intent, verify the action and get the query
	    Intent intent = getIntent();
	    if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
	      String query = intent.getStringExtra(SearchManager.QUERY);
	      ((ArtApplication)getApplication()).getServices().loadSearchResults(query);
	      
	      
	      

	    }
	}

}
