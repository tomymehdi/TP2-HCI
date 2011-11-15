package com.ar.art;

import android.app.Activity;


public class ArtActivity extends Activity {

	
	public  ArtApplication getArtApplication(){
		
		ArtApplication artApp = (ArtApplication)getApplication();
		return artApp;
	}

}
