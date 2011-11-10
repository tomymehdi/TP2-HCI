package com.ar.art;


import tpAndroid.main.ItemsAdapter;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ListView;

public class HotProductsActivity extends ListActivity{
	private ArtApplication app;
	private ItemsAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.productsview);
		initialize();
	}

	private void initialize() {
		app=(ArtApplication)getApplication();
		//app.getServices().loadItems(1, 0);
		adapter = new ItemsAdapter(app.getApp().getItems(), this);
		setListAdapter(adapter);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		adapter.forceReload();
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
//		Item item = app.getServices().getItemById(id);
//		Intent intent = new Intent(HotProductsActivity.this,
//				ItemDescriptionActivity.class);
//		startActivity(intent);
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event){
		if(keyCode == KeyEvent.KEYCODE_SEARCH && event.getRepeatCount() == 0){
			Intent intent = new Intent(HotProductsActivity.this,
					OpenSearchActivity.class);
			startActivity(intent);			
			
			
		}
		return true;
		}
}
