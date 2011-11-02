package tpAndroid.main;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import servidor.services;




public class TpAndroidActivity extends Activity {
	
	
	private App app;
	private services services;
	private  List<Category> categories=new ArrayList<Category>();
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        
        setContentView(R.layout.main);
        

        app=new App();
        services=new services(app);
        try{
        services.loadCategories();
        }
        catch (Exception e) {
        		System.out.println("error en load");
        }
        
        categories = app.getCategories();
        
        if(categories!=null){
        for(int i=0;i<categories.size();i++){
        	System.out.println(categories.get(i).toString());
        }
        }
        
    }
}