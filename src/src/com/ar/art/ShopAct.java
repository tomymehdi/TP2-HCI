package com.ar.art;

import java.util.ArrayList;
import java.util.List;

import servidor.Services;
import tpAndroid.main.App;
import tpAndroid.main.Category;
import android.app.Activity;
import android.os.Bundle;

public class ShopAct extends Activity {

	private App app;
	private Services services;
	private List<Category> categories = new ArrayList<Category>();

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mainview);
		app = new App();
		services = new Services(app);
		try {
			services.loadCategories();
		} catch (Exception e) {
			new Debug(ShopAct.this, "tira error al cargar");
			System.out.println("error en load");
		}
		categories = app.getCategories();
		if (categories != null) {
			for (int i = 0; i < categories.size(); i++) {
				new Debug(ShopAct.this, categories.get(i).toString());
			}
		}else{
			new Debug(ShopAct.this, "no hay categorias");
		}

	}

}