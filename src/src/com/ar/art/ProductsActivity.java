package com.ar.art;


import java.util.ArrayList;
import java.util.List;

import tpAndroid.main.BookItem;
import tpAndroid.main.Item;
import tpAndroid.main.ItemsAdapter;
import tpAndroid.main.MovieItem;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

public class ProductsActivity extends ListActivity{
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
		Item item = ((ItemsAdapter)l.getAdapter()).getItemById(id);
		app.getServices().loadInfo(item);
		Intent intent = new Intent(ProductsActivity.this,
				ItemDescriptionActivity.class);
		Bundle bundle = new Bundle();
		bundle.putInt("id", item.getId());
		intent.putExtras(bundle);
		startActivity(intent);
	}
}
