package com.ar.art;



import tpAndroid.main.App;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;



public class SettingsActivity  extends Activity {

	ArtApplication app;
	Spinner currency_spinner=null;
	Spinner language_spinner=null;
	ArrayAdapter<String> spinnerArrayAdapter=null;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initialize();
	}
	
	@Override
	protected void onResume() {
		super.onResume();
	}
	

	private void initialize() {
		
		app=(ArtApplication) getApplication();
		
		if(app.getApp().getUsername()!=null){
			startLogout();

		}
		else{
		
			startLogin();
		}
		
		
		currency_spinner = (Spinner) findViewById(R.id.currency_options);
		
		currency_spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
	
			
		    public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
		    			
		    		if(position==1 && app.getApp().getLanguageId()==1){
		    			
		    			
		    			ArtApplication app = ((ArtApplication)getApplicationContext());
		    			app.currency=4.9;
		    			
		    		}
		    		if(position==0 && app.getApp().getLanguageId()==2){
		    			
		    			ArtApplication app = ((ArtApplication)getApplicationContext());
		    			app.currency=4.9;
		    		}
		    		else{
		    			
		    			
		    			ArtApplication app = ((ArtApplication)getApplicationContext());
		    			app.currency=1;

		    		}
		    }

		    public void onNothingSelected(AdapterView<?> parentView) {
		        // your code here
		    }

		});
		    
		
	}
	
	private void startLogin(){
		setContentView(R.layout.configview);

		Button login = (Button) findViewById(R.id.login_button);		
		OnClickListener c=new LoginListener();
		login.setOnClickListener(c);
		setSearch() ;
		TextView link = (TextView) findViewById(R.id.webLink);
		link.setMovementMethod(LinkMovementMethod.getInstance());
		String text = "<a href='http://eiffel.itba.edu.ar/hci/2011Q2/g5/'>"+ getResources().getString(R.string.gotoweb)+"</a>";
		link.setText(Html.fromHtml(text));


	}
	private void startLogout(){
		
		setContentView(R.layout.loged_in_settings);

		Button logout = (Button) findViewById(R.id.logout_button);		
		OnClickListener lc=new LogoutListener();
		logout.setOnClickListener(lc);
		setSearch() ;
		TextView link = (TextView) findViewById(R.id.webLink);
		link.setMovementMethod(LinkMovementMethod.getInstance());
		String text = "<a href='http://eiffel.itba.edu.ar/hci/2011Q2/g5/'>"+ getResources().getString(R.string.gotoweb)+"</a>";
		link.setText(Html.fromHtml(text));


		
	}

	private class LoginListener implements OnClickListener{

		public void onClick(View v) {

			EditText username = (EditText) findViewById(R.id.username_text);		
			EditText pass = (EditText) findViewById(R.id.password_text);		

			
			app.getServices().signIn(username.getText().toString(), pass.getText().toString());
			
			
			if(app.getApp().getToken()!=null){
			
			
				SharedPreferences.Editor editor = getSharedPreferences("LoginData", 0).edit();
				editor.putString("username", username.getText().toString());
				editor.putString("pass", pass.getText().toString());
				editor.commit();
				
				
				

				 showDialog(username.getText().toString()+" "+getResources().getString(R.string.succes_login));
				
			
			}
			else{
				 showDialog(username.getText().toString()+" "+getResources().getString(R.string.fail_login));

			}
			
			
		}
		

		
	}
	
	private class LogoutListener implements OnClickListener{
		public void onClick(View v) {
			
			String user=app.getApp().getUsername();
			
			if(user!=null &&  app.getApp().getToken()!=null){
		
			app.getServices().signOut(app.getApp().getUsername(), app.getApp().getToken());
			
			 showDialog(user+" "+getResources().getString(R.string.succes_logout));


			}
	
			else{
				 showDialog(getResources().getString(R.string.fail_logout));
			}
		}
	}


	private void showDialog(String message){
	
	AlertDialog alertDialog = new AlertDialog.Builder(this).create();
	alertDialog.setMessage(message);
	alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
	   public void onClick(DialogInterface dialog, int which) {
		   finish();
	   }
	});
	alertDialog.setIcon(R.drawable.icon);
	alertDialog.show();		
	}

	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event){
		if(keyCode == KeyEvent.KEYCODE_SEARCH && event.getRepeatCount() == 0){
			Intent intent = new Intent(SettingsActivity.this,
					OpenSearchActivity.class);
			startActivity(intent);			
			
			
		}
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {

			this.finish();

	    }
		
		return true;
		}

	private void setSearch() {

		ImageView iv = (ImageView) findViewById(R.id.lupa);
		iv.setClickable(true);

		OnClickListener c = new LupaListener();
		iv.setOnClickListener(c);

	}
	
	
	private class LupaListener implements OnClickListener {

		
		public void onClick(View v) {
			Intent intent = new Intent(SettingsActivity.this,
					OpenSearchActivity.class);
			startActivity(intent);
		}

	}
	

}
