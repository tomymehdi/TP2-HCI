package com.ar.art;

import servidor.services;
import tpAndroid.main.App;
import android.os.Bundle;
import android.widget.TextView;

public class ShopActivity extends ArtActivity {
	
	private TextView textView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.shop);
	}
}
