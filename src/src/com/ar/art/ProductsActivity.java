package com.ar.art;



import tpAndroid.main.Item;
import tpAndroid.main.ItemsAdapter;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SlidingDrawer;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.SlidingDrawer.OnDrawerOpenListener;


public class ProductsActivity extends ListActivity{
	private ArtApplication app	;
	private ItemsAdapter adapter;
	private SlidingDrawer sd=null;
	private String categs[]=null;
	private String subcategs[]=null;
	private Spinner spinner=null;
	private ArrayAdapter<String> spinnerArrayAdapter=null;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.productsview);
		initialize();
		setSlidingDrawer();
	}

	private void initialize() {
			
			app=(ArtApplication)getApplication();
		
			
		adapter = new ItemsAdapter(app.getApp().getItems(), this,app.currency);
		setListAdapter(adapter);

		setConfig() ;
		setSearch();
		TextView link = (TextView) findViewById(R.id.webLink);
		link.setMovementMethod(LinkMovementMethod.getInstance());
		String text = "<a href='http://eiffel.itba.edu.ar/hci/2011Q2/g5/'>"+ getResources().getString(R.string.gotoweb)+"</a>";
		link.setText(Html.fromHtml(text));

	}
	
	@Override
	protected void onResume() {
		super.onResume();
		adapter.forceReload();
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		Item item = ((ItemsAdapter)l.getAdapter()).getItemById(id);
		
		app.getServices().loadInfo(item);
		Intent intent = new Intent(ProductsActivity.this,
				ItemDescriptionActivity.class);
		Bundle bundle = new Bundle();
		bundle.putInt("id", item.getId());
		intent.putExtras(bundle);
		startActivity(intent);
	}
	
	private void setSearch() {

		ImageView iv = (ImageView) findViewById(R.id.lupa);
		iv.setClickable(true);

		OnClickListener c = new LupaListener();
		iv.setOnClickListener(c);

	}
	
	
	private class LupaListener implements OnClickListener {


		public void onClick(View v) {
			Intent intent = new Intent(ProductsActivity.this,
					OpenSearchActivity.class);
			startActivity(intent);
		}

	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event){
		if(keyCode == KeyEvent.KEYCODE_SEARCH && event.getRepeatCount() == 0){
			Intent intent = new Intent(ProductsActivity.this,
					OpenSearchActivity.class);
			startActivity(intent);			
			
			
		}
	
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
	        
			
			this.finish();


	    }
		
		return true;
		}
	

	
	private void setConfig() {

		ImageView iv = (ImageView) findViewById(R.id.setting_icon);
		iv.setClickable(true);

		OnClickListener c = new SettingsListener();
		iv.setOnClickListener(c);



	}
	private class SettingsListener implements OnClickListener {

		public void onClick(View v) {
			Intent intent = new Intent(ProductsActivity.this,
					SettingsActivity.class);
			startActivity(intent);
		}
	}
	
	
	private void setSlidingDrawer() {
		
		sd = (SlidingDrawer) findViewById(R.id.prod_sliding_drawer);

		sd.setOnDrawerOpenListener(new SlidingDrawerListener());
	}

	private class SlidingDrawerListener implements OnDrawerOpenListener {

		public void onDrawerOpened() {
			
			sd.bringToFront();
			


			
			categs= app.getApp().getCategoriesNames();
			subcategs=app.getApp().getSubCategoriesNames(1);
			
			
			spinner = (Spinner) findViewById(R.id.categories_spinner);
			spinnerArrayAdapter = new ArrayAdapter<String>(
					ProductsActivity.this, android.R.layout.simple_spinner_item,
					categs);
			spinner.setAdapter(spinnerArrayAdapter);
			
			spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
		
				
			    public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
	    			
		    		if(position==0){
		    			subcategs=app.getApp().getSubCategoriesNames(1);
		    			spinner = (Spinner) findViewById(R.id.subcategories_spinner);
		    			spinnerArrayAdapter = new ArrayAdapter<String>(ProductsActivity.this,
		    					android.R.layout.simple_spinner_item, subcategs);
		    			spinner.setAdapter(spinnerArrayAdapter);
		    			
		    		}
		    		else{
		    			app.getServices().loadSubcategories(2);
		    			subcategs=app.getApp().getSubCategoriesNames(2);
		    			spinner = (Spinner) findViewById(R.id.subcategories_spinner);
		    			spinnerArrayAdapter = new ArrayAdapter<String>(ProductsActivity.this,
		    					android.R.layout.simple_spinner_item, subcategs);
		    			spinner.setAdapter(spinnerArrayAdapter);
		    			
		    		}
		    }

		    public void onNothingSelected(AdapterView<?> parentView) {
		        // your code here
		    }

		});


			    
		
	
			
			spinner = (Spinner) findViewById(R.id.subcategories_spinner);
			spinnerArrayAdapter = new ArrayAdapter<String>(ProductsActivity.this,
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
			
			initialize();
				
		}
	}


}
