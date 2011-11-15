package com.ar.art;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

public class MyAccountActivity extends Activity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.myaccountview);
		initialize();
	}

	private void initialize() {
		setConfig() ;
		 setSearch() ;
		 TextView link = (TextView) findViewById(R.id.webLink);
			link.setMovementMethod(LinkMovementMethod.getInstance());
			String text = "<a href='http://eiffel.itba.edu.ar/hci/2011Q2/g5/'>"+ getResources().getString(R.string.gotoweb)+"</a>";
			link.setText(Html.fromHtml(text));

	}
	
	
	//PARA QUE ANDEN LOS BOTONES, DE ACA AL FINAL
	private void setSearch() {

		ImageView iv = (ImageView) findViewById(R.id.lupa);
		iv.setClickable(true);

		OnClickListener c = new LupaListener();
		iv.setOnClickListener(c);

	}
	
	
	private class LupaListener implements OnClickListener {


		public void onClick(View v) {
			Intent intent = new Intent(MyAccountActivity.this,
					OpenSearchActivity.class);
			startActivity(intent);
		}

	}
	
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event){
		if(keyCode == KeyEvent.KEYCODE_SEARCH && event.getRepeatCount() == 0){
			Intent intent = new Intent(MyAccountActivity.this,
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
			Intent intent = new Intent(MyAccountActivity.this,
					SettingsActivity.class);
			startActivity(intent);
		}
	}


	
}
