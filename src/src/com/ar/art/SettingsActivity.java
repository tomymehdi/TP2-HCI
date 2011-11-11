package com.ar.art;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



public class SettingsActivity  extends Activity {

	ArtApplication app;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.configview);
		initialize();
	}

	private void initialize() {
		
		app=(ArtApplication) getApplication();
		Button login = (Button) findViewById(R.id.login_button);		
		OnClickListener c=new LoginListener();
		login.setOnClickListener(c);
		Button logout = (Button) findViewById(R.id.logout_button);		
		OnClickListener lc=new LogoutListener();
		logout.setOnClickListener(lc);
		
	}
	

	private class LoginListener implements OnClickListener{

		@Override
		public void onClick(View v) {

			EditText username = (EditText) findViewById(R.id.username_text);		
			EditText pass = (EditText) findViewById(R.id.password_text);		

			
			app.getServices().signIn(username.getText().toString(), pass.getText().toString());
			
			
			if(app.getApp().getToken()!=null){
			
			
				SharedPreferences.Editor editor = getPreferences(0).edit();
				editor.putString("username", username.getText().toString());
				editor.putString("token", app.getApp().getToken());
				editor.commit();
				
				
				
				
				 showDialog(username.getText().toString()+" you have login correctly");
				
			
			}
			else{
				System.out.println("MAL LOGIN");
			}
			
			
		}
		

		
	}
	
	private class LogoutListener implements OnClickListener{
		@Override
		public void onClick(View v) {
			
			String user=app.getApp().getUsername();
			
			if(user!=null &&  app.getApp().getToken()!=null){
		
			app.getServices().signOut(app.getApp().getUsername(), app.getApp().getToken());
			
			 showDialog(user+" you have logout correctly");


			}
	
			else{
			 showDialog(user+" you were not loged in");
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
		return true;
		}



}
