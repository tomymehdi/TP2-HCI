package com.ar.art;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


import tpAndroid.main.App;
import tpAndroid.main.Item;
import android.app.ListActivity;
import android.content.Intent;
import android.content.SharedPreferences;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;

import android.widget.ImageView;
import android.widget.TextView;

public class ItemDescriptionActivity extends ListActivity {
	private ItemInfoAdapter adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.itemdescriptionview);
		Bundle bundle = this.getIntent().getExtras();
		int id = bundle.getInt("id");
		ArtApplication app=((ArtApplication)getApplication());

		Item item = app.getApp().getItemById(id);
		TextView name = (TextView) findViewById(R.id.product_name);
		name.setText(item.getName());

		TextView pricetag = (TextView) findViewById(R.id.price);
		

		if(app.currency==1){
			
			

			pricetag.setText("Price: U$D ");

		}
		else{
			
			
			pricetag.setText("Price:  AR$");

		}
		TextView price = (TextView) findViewById(R.id.price);
		
		System.out.println("PASA POR ACA");
		
		price.setText(String.valueOf(item.getPrice()*(app.currency)));

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
		
		adapter = new ItemInfoAdapter(item, this, (ArtApplication)getApplication());
		setListAdapter(adapter);
		setConfig() ;
		setSearch();
		TextView link = (TextView) findViewById(R.id.webLink);
		link.setMovementMethod(LinkMovementMethod.getInstance());
		String text = "<a href='http://eiffel.itba.edu.ar/hci/2011Q2/g5/'>"+ getResources().getString(R.string.gotoweb)+"</a>";
		link.setText(Html.fromHtml(text));

	}
	
	@Override
	protected void onResume() {
		super.onResume();
		adapter.forceReload();
	}
	
	
	private void setSearch() {

		ImageView iv = (ImageView) findViewById(R.id.lupa);
		iv.setClickable(true);

		OnClickListener c = new LupaListener();
		iv.setOnClickListener(c);

	}

	private void setConfig() {

		ImageView iv = (ImageView) findViewById(R.id.setting_icon);
		iv.setClickable(true);

		OnClickListener c = new SettingsListener();
		iv.setOnClickListener(c);

		SharedPreferences prefs = getPreferences(0);

		String token = prefs.getString("token", null);
		String user = prefs.getString("username", null);

		if (token != null && user != null) {
			{
				ArtApplication app = (ArtApplication) getApplication();
				app.getApp().setToken(token);
				app.getApp().setUsername(user);

			}
		}

	}

	private class SettingsListener implements OnClickListener {

		
		public void onClick(View v) {
			Intent intent = new Intent(ItemDescriptionActivity.this,
					SettingsActivity.class);
			startActivity(intent);
		}
	}

	private class LupaListener implements OnClickListener {

		
		public void onClick(View v) {
			Intent intent = new Intent(ItemDescriptionActivity.this,
					OpenSearchActivity.class);
			startActivity(intent);
		}

	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event){
		if(keyCode == KeyEvent.KEYCODE_SEARCH && event.getRepeatCount() == 0){
			Intent intent = new Intent(ItemDescriptionActivity.this,
					OpenSearchActivity.class);
			startActivity(intent);			
			
			
		}
	
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
			this.finish();

	    }
		
		return true;
		}
	




}
	
	
	
	

