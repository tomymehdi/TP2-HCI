package com.ar.art;

import com.ar.art.objects.App;

import services.Services;
import android.app.Application;

public class ArtApplication extends Application {
	Services services;
	App app;

	@Override
	public void onCreate() {
		super.onCreate();
		this.app = new App();
		this.services = new Services(this.app);
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
