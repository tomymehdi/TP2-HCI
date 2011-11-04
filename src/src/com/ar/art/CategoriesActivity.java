package com.ar.art;

import java.util.List;

import tpAndroid.main.App;
import tpAndroid.main.Category;
import android.app.Activity;
import android.os.Bundle;

public class CategoriesActivity extends Activity {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.categoriesview);
		initialize();
	}

	private void initialize() {
		T.appvariabls.services.loadCategories();
		List<Category> categories = T.appvariabls.app.getCategories();
	}
}
