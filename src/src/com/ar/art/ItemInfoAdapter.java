package com.ar.art;

import java.util.ArrayList;
import java.util.HashMap;
import tpAndroid.main.BookItem;
import tpAndroid.main.Item;
import tpAndroid.main.MovieItem;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class ItemInfoAdapter extends BaseAdapter {

	private Item item;
	private Context context;
	private static int MOVIE = 1;
	private static int BOOK = 2;
	private int type;
	private HashMap<String, String> map;
	private ArrayList<String> list;
	private ArtApplication app;
	
	public ItemInfoAdapter(Item item, Context context, ArtApplication app){
		super();
		this.item = item;
		this.type = item.getCateg_id();
		this.context = context;
		int size = 0;
		if(type == MOVIE){
			size = 8;
		}else if(type == BOOK){
			size = 6; 
		}
		this.app = app;
		
		list = new ArrayList<String>(size);
		map = new HashMap<String, String>(size);
		if(app.getApp().getCategories() == null){
			app.getServices().loadCategories();
		}
		if(app.getApp().getCategory(type).getSubCategories() == null){
			app.getServices().loadSubcategories(item.getCateg_id());
		}
		initialize();
	}

	
	private void initialize() {
		if(type == MOVIE){
			MovieItem item = (MovieItem)this.item;
			add("Category: ", app.getApp().getCategory(item.getCateg_id()).getName().toString());

			add("Sub-Category: ", app.getApp().getSubcategory(item.getScateg_id()).getName());
			add("Actors: ", item.getActors());
			add("Formats: ", item.getFormats());
			add("Languages: ", item.getLang());
			add("Subtitles: ", item.getSubtitles());
			add("Release: ", item.getRelease().toString());
			add("Runtime: ", String.valueOf(item.getRuntime()));
		}else{
			BookItem item = (BookItem)this.item;
			add("Category: ", app.getApp().getCategory(item.getCateg_id()).getName().toString());
			add("Sub-Category: ", app.getApp().getSubcategory(item.getScateg_id()).getName().toString());
			add("Authors: ", item.getAuthors());
			add("Publisher: ", item.getPublisher());
			add("Publishing Date: ", item.getPub_date().toString());
			add("Language: ", item.getLanguage());
		}
	}


	private void add(String tag, String value) {
		list.add(tag);
		map.put(tag, value);
	}


	
	public int getCount() {
		return list.size();
	}

	
	public String getItem(int index) {
		return map.get(list.get(index));
	}

	
	public long getItemId(int index) {
		return index;
	}

	
	public View getView(int index, View convertView, ViewGroup parent) {
		ItemInfo ils;
		if(convertView == null){
			ils = (ItemInfo)View.inflate(context, R.layout.item_tag, null);
		}else{
			ils = (ItemInfo)convertView;
		}
		ils.setInfo(list.get(index), getItem(index));
		return ils;
	}

	public void forceReload() {
		notifyDataSetChanged();
	}

}
