package com.ar.art;



import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

import android.widget.TextView;


public class CategoriesActivity extends ArtActivity{

	public static final String[] fields = { "name" };


	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.categoriesview);
		initialize();
		
		setSearch() ;
		 setConfig();
		 TextView link = (TextView) findViewById(R.id.webLink);
			link.setMovementMethod(LinkMovementMethod.getInstance());
			String text = "<a href='http://eiffel.itba.edu.ar/hci/2011Q2/g5/'>"+ getResources().getString(R.string.gotoweb)+"</a>";
			link.setText(Html.fromHtml(text));


	}

	

	private void initialize() {

	
		Button dvdButton = (Button) findViewById(R.id.dvd_button);
		
		
		dvdButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {
				
				getArtApplication().getServices().loadItems(1, 0);
				Intent intent = new Intent(CategoriesActivity.this,
						ProductsActivity.class);
				startActivity(intent);
			}
		});

		
		Button bookButton = (Button) findViewById(R.id.books_button);
		
		
		bookButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {
				getArtApplication().getServices().loadItems(2, 0);

				Intent intent = new Intent(CategoriesActivity.this,
						ProductsActivity.class);
				startActivity(intent);
			}
		});

		
		TextView link = (TextView) findViewById(R.id.webLink);
		link.setMovementMethod(LinkMovementMethod.getInstance());
		String text = "<a href='http://eiffel.itba.edu.ar/hci/2011Q2/g5/'>"+ getResources().getString(R.string.gotoweb)+"</a>";
		link.setText(Html.fromHtml(text));
		 
		
	
	}

	private void setSearch() {

		ImageView iv = (ImageView) findViewById(R.id.lupa);
		iv.setClickable(true);

		OnClickListener c = new LupaListener();
		iv.setOnClickListener(c);

	}


	private class LupaListener implements OnClickListener {


		public void onClick(View v) {
			Intent intent = new Intent(CategoriesActivity.this,
					OpenSearchActivity.class);
			startActivity(intent);
		}

	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event){
		if(keyCode == KeyEvent.KEYCODE_SEARCH && event.getRepeatCount() == 0){
			Intent intent = new Intent(CategoriesActivity.this,
					OpenSearchActivity.class);
			startActivity(intent);			


		}

		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {

			this.finish();


		}

		return true;
	}


	private void setConfig() {

		ImageView iv = (ImageView) findViewById(R.id.setting_icon);
		iv.setClickable(true);

		OnClickListener c = new SettingsListener();
		iv.setOnClickListener(c);




	}

	private class SettingsListener implements OnClickListener {


		public void onClick(View v) {
			Intent intent = new Intent(CategoriesActivity.this,
					SettingsActivity.class);
			startActivity(intent);
		}
	}

}
