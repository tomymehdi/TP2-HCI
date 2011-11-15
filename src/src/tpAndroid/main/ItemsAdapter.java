package tpAndroid.main;

import java.util.List;

import com.ar.art.R;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;


public class ItemsAdapter extends BaseAdapter {

	private List<Item> items;
	private Context context;
	private double factor;
	public ItemsAdapter(List<Item> items, Context context, double factor) {
		super();
		this.items = items;
		this.context = context;
		this.factor=factor;
	}

	public int getCount() {
		
		if(items==null){

			return 0;
		}
		return items.size();
	}
	
	public double getFactor(){
		return factor;
	}


	public Item getItem(int index) {
		if(items == null){
			return null;
		}
		return items.get(index);
	}


	public long getItemId(int index) {
		return getItem(index).getId();
	}


	public View getView(int index, View convertView, ViewGroup parent) {
		ItemListSlot ils;
		
				
		
		
		if(convertView == null){
			ils = (ItemListSlot)View.inflate(context, R.layout.item_list_slot, null);
		}else{
			ils = (ItemListSlot)convertView;
		}
		ils.setItem(items.get(index),factor);
		return ils;
	}

	public void forceReload() {
		notifyDataSetChanged();
	}

	public Item getItemById(long id) {
		for(int i = 0; i < items.size(); i++){
			if(items.get(i).getId() == (int)id){
				return items.get(i);
			}
		}
		return null;
		//TODO: Lanzar error.
	}

}