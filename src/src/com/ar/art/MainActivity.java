package com.ar.art;

import com.ar.art.R.string;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends ArtActivity {

	private Button categoriesButton;
	private Button hotProductsButton;
	private Button pageButton;
	private Button myAccountButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mainview);
		initialize();
		setSearch();
		setConfig();
	}
	
	private void setSearch(){
	
		ImageView iv = (ImageView) findViewById(R.id.lupa);
		iv.setClickable(true);
		
		OnClickListener c=new LupaListener();
		iv.setOnClickListener(c);
				
		
		
	}
	private void setConfig(){
		
		ImageView iv = (ImageView) findViewById(R.id.setting_icon);
		iv.setClickable(true);
		
		OnClickListener c=new SettingsListener();
		iv.setOnClickListener(c);
		
		
		
		SharedPreferences prefs = getPreferences(0); 
		
		String token = prefs.getString("token", null);
		String user=prefs.getString("username", null);
		
		

		
		if (token != null && user!=null){ 
		{
			ArtApplication app=(ArtApplication) getApplication();
			app.getApp().setToken(token);
			app.getApp().setUsername(user);
		
			
		}
		}
		
	}
	
	private class SettingsListener implements OnClickListener{
		

		@Override
		public void onClick(View v) {
			Intent intent = new Intent(MainActivity.this,
					SettingsActivity.class);
			startActivity(intent);			
		}
	}

	
	private class LupaListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			Intent intent = new Intent(MainActivity.this,
					OpenSearchActivity.class);
			startActivity(intent);			
		}
		
	}

	private void initialize() {
			
		categoriesButton = (Button) findViewById(R.id.categories_button);
		
		
		categoriesButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {
				Intent intent = new Intent(MainActivity.this,
						CategoriesActivity.class);
				startActivity(intent);
			}
		});
		hotProductsButton = (Button) findViewById(R.id.hot_products_button);
		hotProductsButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {
				Intent intent = new Intent(MainActivity.this,
						HotProductsActivity.class);
				startActivity(intent);
			}
		});
		pageButton = (Button) findViewById(R.id.page_button);
		pageButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {
				//TODO hacer q vaya a nuestra pagina de eiffel grupo 5
			}
		});
		myAccountButton = (Button) findViewById(R.id.my_account_button);
		myAccountButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {
				Intent intent = new Intent(MainActivity.this,
						MyAccountActivity.class);
				startActivity(intent);
			}
		});
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event){
		if(keyCode == KeyEvent.KEYCODE_SEARCH && event.getRepeatCount() == 0){
			Intent intent = new Intent(MainActivity.this,
					OpenSearchActivity.class);
			startActivity(intent);			
			
			
		}
		return true;
		}
	
}
