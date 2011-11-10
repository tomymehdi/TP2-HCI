package com.ar.art;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;

public class CategoriesActivity extends ListActivity{
	
	public static final String[] fields = { "name" };

	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.categoriesview);
		initialize();
	}

	private void initialize() {
		ListAdapter adapter = new SimpleAdapter(this,
				((ArtApplication)getApplication()).getServices().getCategoriesAsMap(), R.layout.category,
				((ArtApplication)getApplication()).getServices().getCategoriesAsMapKeys(), new int[] { R.id.category_button });
		
		setListAdapter(adapter);
	}
	
	
}
