package com.ar.art;

import java.util.List;

import tpAndroid.main.App;
import tpAndroid.main.Category;
import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;

public class CategoriesActivity extends ListActivity {
	
	public static final String[] fields = { "name" };

	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.categoriesview);
		initialize();
	}

	private void initialize() {
		ListAdapter adapter = new SimpleAdapter(this,
				T.appvariabls.services.getCategoriesAsMap(), R.layout.category,
				T.appvariabls.services.getCategoriesAsMapKeys(), new int[] { R.id.category_button });
		
		setListAdapter(adapter);
	}
}
