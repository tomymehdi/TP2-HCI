package com.ar.art;

import android.app.Activity;
import android.os.Bundle;

public class OpenSearchActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		onSearchRequested();
	}
	@Override
	public boolean onSearchRequested() {
		Bundle appData = new Bundle();
		// appData.putBoolean(SearchActivity.JARGON, true);
		startSearch(null, false, appData, false);
		return true;

	}
	
	



}
