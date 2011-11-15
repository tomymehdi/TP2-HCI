package com.ar.art;


import tpAndroid.main.OrdersAdapter;
import android.app.ListActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;


public class OrdersActivity extends ListActivity {
	private ArtApplication app;
	private OrdersAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.orderslayout);
		initialize();
	}

	private void initialize() {
		app=(ArtApplication)getApplication();
		adapter = new OrdersAdapter(app.getApp().getOrders(), this);
		setListAdapter(adapter);
		setSearch();
		setConfig();
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
			Intent intent = new Intent(OrdersActivity.this,
					SettingsActivity.class);
			startActivity(intent);
		}
	}

	private class LupaListener implements OnClickListener {

		public void onClick(View v) {
			Intent intent = new Intent(OrdersActivity.this,
					OpenSearchActivity.class);
			startActivity(intent);
		}

	}
	
	

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_SEARCH && event.getRepeatCount() == 0) {
			Intent intent = new Intent(OrdersActivity.this,
					OpenSearchActivity.class);
			startActivity(intent);

		}
		
	
	if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {

		this.finish();

    }


		return true;
	}

}
