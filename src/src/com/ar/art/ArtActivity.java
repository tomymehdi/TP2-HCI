package com.ar.art;

import android.app.Activity;
import android.content.Intent;
import android.view.KeyEvent;

public class ArtActivity extends Activity {

	protected ArtApplication getArtApplication(){
		ArtApplication artApp = (ArtApplication)getApplication();
		return artApp;
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event){
		if(keyCode == KeyEvent.KEYCODE_SEARCH && event.getRepeatCount() == 0){
			Intent intent = new Intent(ArtActivity.this,
					OpenSearchActivity.class);
			startActivity(intent);			
			
			
		}
		return true;
		}
	
}
