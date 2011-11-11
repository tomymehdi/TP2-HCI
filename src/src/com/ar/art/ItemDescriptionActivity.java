package com.ar.art;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import tpAndroid.main.Item;
import tpAndroid.main.ItemsAdapter;
import android.app.Activity;
import android.app.ListActivity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class ItemDescriptionActivity extends ListActivity {
	private ArtApplication app;
	private ItemInfoAdapter adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.itemdescriptionview);
		Bundle bundle = this.getIntent().getExtras();
		int id = bundle.getInt("id");
		Item item = ((ArtApplication)getApplication()).getApp().getItemById(id);
		TextView name = (TextView) findViewById(R.id.product_name);
		name.setText(item.getName());
		TextView price = (TextView) findViewById(R.id.price);
		price.setText("Price: $" + item.getPrice());
		ImageView img = (ImageView) findViewById(R.id.image);
		Drawable draw = drawable_from_url(item.getImgUrl());
		img.setImageDrawable(draw);
		initialize(item);
	}
	
	private Drawable drawable_from_url(String url){
		try{
	    Bitmap x;

	    HttpURLConnection connection = (HttpURLConnection)new URL(url) .openConnection();
	    connection.setRequestProperty("User-agent","Mozilla/4.0");

	    connection.connect();
	    InputStream input = connection.getInputStream();

	    x = BitmapFactory.decodeStream(input);
	    Drawable asd = new BitmapDrawable(x);
	    return asd;
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}

	private void initialize(Item item) {
		app=(ArtApplication)getApplication();
		adapter = new ItemInfoAdapter(item, this, app);
		setListAdapter(adapter);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		adapter.forceReload();
	}

}
