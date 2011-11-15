package com.ar.art;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SlidingDrawer;
import android.widget.SlidingDrawer.OnDrawerOpenListener;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends ArtActivity {

	private Button categoriesButton;
	private Button myAccountButton;
	private ArtApplication app;
	private String categs[]=null;
	private String subcategs[]=null;
	private Spinner spinner=null;
	private ArrayAdapter<String> spinnerArrayAdapter=null;
	private SlidingDrawer sd=null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.mainview);
		initialize();
		sertSearch();
		setConfig();
		setSlidingDrawer();
		app=(ArtApplication)getApplication();
		app.getServices().loadCategories();
		app.getServices().loadSubcategories(1);
		
	}

	private void setSlidingDrawer() {
		sd = (SlidingDrawer) findViewById(R.id.main_sliding_drawer);

		sd.setOnDrawerOpenListener(new SlidingDrawerListener());
	}

	private class SlidingDrawerListener implements OnDrawerOpenListener {

		public void onDrawerOpened() {
			
			sd.bringToFront();
			
			app.getServices().loadSubcategories(1);

		

			
			categs= app.getApp().getCategoriesNames();
			subcategs=app.getApp().getSubCategoriesNames(1);
			
			
			spinner = (Spinner) findViewById(R.id.categories_spinner);
			spinnerArrayAdapter = new ArrayAdapter<String>(
					MainActivity.this, android.R.layout.simple_spinner_item,
					categs);
			spinner.setAdapter(spinnerArrayAdapter);
			
			
			spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
		
				
			    public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
			    			
			    		if(position==0){
			    			subcategs=app.getApp().getSubCategoriesNames(1);
			    			spinner = (Spinner) findViewById(R.id.subcategories_spinner);
			    			spinnerArrayAdapter = new ArrayAdapter<String>(MainActivity.this,
			    					android.R.layout.simple_spinner_item, subcategs);
			    			spinner.setAdapter(spinnerArrayAdapter);
			    			
			    		}
			    		else{
			    			app.getServices().loadSubcategories(2);
			    			subcategs=app.getApp().getSubCategoriesNames(2);
			    			spinner = (Spinner) findViewById(R.id.subcategories_spinner);
			    			spinnerArrayAdapter = new ArrayAdapter<String>(MainActivity.this,
			    					android.R.layout.simple_spinner_item, subcategs);
			    			spinner.setAdapter(spinnerArrayAdapter);
			    			
			    		}
			    }

			    public void onNothingSelected(AdapterView<?> parentView) {
			        // your code here
			    }

			});
			    

       
			
			
			
			
			spinner = (Spinner) findViewById(R.id.subcategories_spinner);
			spinnerArrayAdapter = new ArrayAdapter<String>(MainActivity.this,
					android.R.layout.simple_spinner_item, subcategs);
			spinner.setAdapter(spinnerArrayAdapter);
			
			
			Button apply = (Button) findViewById(R.id.apply_button);
		
			apply.setOnClickListener(new ApplyListener());
			
			
	
    
			
		}
	}

	private class ApplyListener implements OnClickListener {


		public void onClick(View v) {
			
			sd.close();
			
			String categories,subcategories;
			Spinner s = (Spinner)findViewById(R.id.categories_spinner);
			categories = s.getSelectedItem().toString();
			s = (Spinner)findViewById(R.id.subcategories_spinner);
			subcategories = s.getSelectedItem().toString();
			// en cada variable esta el string seleccionado de cada spinner
			
			int catid = 0;
			int scatid=0;
			
			for(int i=0;i<app.getApp().getCategories().size();i++){
				if(app.getApp().getCategories().get(i).getName() == categories){
					catid=app.getApp().getCategories().get(i).getId();
				}
			}
			
			app.getServices().loadSubcategories(catid);
			
						
			for(int i=0;i<app.getApp().getCategory(catid).getSubCategories().size();i++){
				
				
				if(app.getApp().getCategory(catid).getSubCategories().get(i).getName().equals(subcategories)){
					scatid=app.getApp().getCategory(catid).getSubCategories().get(i).getId();
				}
			}
			
			app.getServices().loadItems(catid,scatid);
			
		
			Intent intent = new Intent(MainActivity.this,
					ProductsActivity.class);
			startActivity(intent);
			
		}
	}

	private void setSearch() {

		ImageView iv = (ImageView) findViewById(R.id.lupa);
		iv.setClickable(true);

		OnClickListener c = new LupaListener();
		iv.setOnClickListener(c);

	}

	private void setConfig() {

		ImageView iv = (ImageView) findViewById(R.id.setting_icon);
		iv.setClickable(true);

		OnClickListener c = new SettingsListener();
		iv.setOnClickListener(c);

		SharedPreferences prefs = getPreferences(0);

		String token = prefs.getString("token", null);
		String user = prefs.getString("username", null);

		if (token != null && user != null) {
			{
				ArtApplication app = (ArtApplication) getApplication();
				app.getApp().setToken(token);
				app.getApp().setUsername(user);

			}
		}

	}

	private class SettingsListener implements OnClickListener {


		
		public void onClick(View v) {
			Intent intent = new Intent(MainActivity.this,
					SettingsActivity.class);
			startActivity(intent);
		}
	}

	private class LupaListener implements OnClickListener {

		public void onClick(View v) {
			Intent intent = new Intent(MainActivity.this,
					OpenSearchActivity.class);
			startActivity(intent);
		}

	}
	
	private void sertSearch(){
	
		ImageView iv = (ImageView) findViewById(R.id.lupa);
		iv.setClickable(true);
		
		OnClickListener c=new LupaListener();
		iv.setOnClickListener(c);
				

		
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

		
		myAccountButton = (Button) findViewById(R.id.my_account_button);
		myAccountButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {
				if(app.getApp().getUser() == null || app.getApp().getToken() == null){
					Intent intent = new Intent(MainActivity.this,
							SettingsActivity.class);
					startActivity(intent);
				}else{
					app.getServices().loadOrders(app.getApp().getUser().getUserName(), app.getApp().getToken());
					System.out.println(app.getApp().getToken());
					Intent intent = new Intent(MainActivity.this,
							OrdersActivity.class);
					startActivity(intent);
					
				}

			}
		});
		
		TextView link = (TextView) findViewById(R.id.webLink);
		link.setMovementMethod(LinkMovementMethod.getInstance());
		String text = "<a href='http://eiffel.itba.edu.ar/hci/2011Q2/g5/'>"+ getResources().getString(R.string.gotoweb)+"</a>";
		link.setText(Html.fromHtml(text));
		
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_SEARCH && event.getRepeatCount() == 0) {
			Intent intent = new Intent(MainActivity.this,
					OpenSearchActivity.class);
			startActivity(intent);

		}
		
	
	if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {

		this.finish();

    }


		return true;
	}
	
	


}
