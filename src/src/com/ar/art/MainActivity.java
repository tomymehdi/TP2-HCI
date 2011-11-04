package com.ar.art;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends ArtActivity {

	private Button shopButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		initialize();
	}

	private void initialize() {
		shopButton = (Button) findViewById(R.id.shopButton);
		shopButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {
				Intent intent = new Intent(MainActivity.this,
						ShopAct.class);
				startActivity(intent);
			}
		});
	}

}
