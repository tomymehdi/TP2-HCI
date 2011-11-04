package com.ar.art;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

public class ArtIntroActivity extends Activity {
    /** Called when the activity is first created. */
	public static final int MILLIS_TIME_TO_WAIT = 1000;
	public static final int STOP = 0;
	public static final int SEARCH = 1;
	
	private Handler splashHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case ArtIntroActivity.STOP:
				Intent intent = new Intent(ArtIntroActivity.this,
						MainActivity.class);
				startActivity(intent);
				ArtIntroActivity.this.finish();
				break;
			case ArtIntroActivity.SEARCH:
				Intent myIntent = new Intent(ArtIntroActivity.this,
						MainActivity.class);
				startActivity(myIntent);
				ArtIntroActivity.this.finish();
				break;
			}
			super.handleMessage(msg);
		}
	};
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  setContentView(R.layout.mainview);
        
        
        //VOY AGREGANDO LO MIO
        
      //  App app=new App();
     //   services serv=new services(app); 
        
     //  serv.loadCategories();
       
     //  serv.loadItems(2, 0);
        
     //   serv.signIn("valuts", "tomas123");
              
       
       
      // serv.loadOrders("valuts", app.getUser().getToken());
   
       
      

        
        
    }
}