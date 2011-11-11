package com.ar.art;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
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
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event){
		if(keyCode == KeyEvent.KEYCODE_SEARCH && event.getRepeatCount() == 0){
			Intent intent = new Intent(CategoriesActivity.this,
					OpenSearchActivity.class);
			startActivity(intent);			
			
			
		}
		return true;
		}
}
