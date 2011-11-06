package com.ar.art;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends ArtActivity {

	private Button categoriesButton;
	private Button hotProductsButton;
	private Button pageButton;
	private Button myAccountButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mainview);
		initialize();
	}

	private void initialize() {
		categoriesButton = (Button) findViewById(R.id.categories_button);
		categoriesButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {
				Intent intent = new Intent(MainActivity.this,
						CategoriesActivity.class);
				startActivity(intent);
			}
		});
		hotProductsButton = (Button) findViewById(R.id.hot_products_button);
		hotProductsButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {
				Intent intent = new Intent(MainActivity.this,
						HotProductsActivity.class);
				startActivity(intent);
			}
		});
		pageButton = (Button) findViewById(R.id.page_button);
		pageButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {
				//TODO hacer q vaya a nuestra pagina de eiffel grupo 5
			}
		});
		myAccountButton = (Button) findViewById(R.id.my_account_button);
		myAccountButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {
				Intent intent = new Intent(MainActivity.this,
						MyAccountActivity.class);
				startActivity(intent);
			}
		});
	}

}
