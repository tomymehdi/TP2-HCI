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
	
	public ItemsAdapter(List<Item> items, Context context) {
		super();
		this.items = items;
		this.context = context;
	}

	@Override
	public int getCount() {
		System.out.println(items.size());
		return items.size();
	}

	@Override
	public Item getItem(int index) {
		if(items == null){
			return null;
		}
		return items.get(index);
	}

	@Override
	public long getItemId(int index) {
		return getItem(index).getId();
	}

	@Override
	public View getView(int index, View convertView, ViewGroup parent) {
		ItemListSlot ils;
		if(convertView == null){
			ils = (ItemListSlot)View.inflate(context, R.layout.item_list_slot, null);
		}else{
			ils = (ItemListSlot)convertView;
		}
		ils.setItem(items.get(index));
		return ils;
	}

	public void forceReload() {
		notifyDataSetChanged();
	}

	public Item getItemById(long id) {
		for(int i = 0; i < items.size(); i++){
			System.out.println("I have " + items.get(i).getId() + " and im looking for " + id);
			if(items.get(i).getId() == (int)id){
				return items.get(i);
			}
		}
		return null;
		//TODO: Lanzar error.
	}

}