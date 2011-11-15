package com.ar.art;

import java.util.Locale;

import android.app.Application;
import android.content.SharedPreferences;
import android.content.res.Configuration;

import servidor.Services;
import tpAndroid.main.App;


public class ArtApplication extends Application {
	Services services;
	App app;
	double currency;

	@Override
	public void onCreate() {

		super.onCreate();

		this.app = new App();

		
		if(Locale.getDefault().getLanguage().equals("en")){
			
			app.setLanguageId(1);
			currency=1;
		}
		else{

			app.setLanguageId(2);
			currency=4.2;
		}
		
		this.services = new Services(app);
		services.loadCategories();
		for (int i=0;i<app.getCategories().size();i++){
		services.loadSubcategories(app.getCategories().get(i).getId());
		
		
		SharedPreferences settings = getSharedPreferences("LoginData", 0);
		String username = settings.getString("username", null);
		String pass = settings.getString("pass", null);
		
		
		if(username != null && pass != null && app.getUser() == null){
			System.out.println(username);
			System.out.println(pass);
			services.signIn(username, pass);
		}

		
		}
		
	}

	public Services getServices() {
		return services;
	}

	public void setServices(Services services) {
		this.services = services;
	}

	public App getApp() {
		return app;
	}

	public void setApp(App app) {
		this.app = app;
	}
	


}
