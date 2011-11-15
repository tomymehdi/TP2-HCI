package tpAndroid.main;

import java.util.List;

import com.ar.art.R;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class OrdersAdapter extends BaseAdapter {

	private List<Order> orders;
	private Context context;
	
	public OrdersAdapter(List<Order> orders, Context context) {
		super();
		this.orders = orders;
		this.context = context;
	}

	public int getCount() {
		if(orders == null){
			return 0;
		}
		return orders.size();
	}

	public Order getItem(int index) {
		if(orders == null){
			return null;
		}
		return orders.get(index);
	}

	public long getItemId(int index) {
		return getItem(index).getId();
	}

	public View getView(int index, View convertView, ViewGroup parent) {
		OrderListSlot ols;
		if(convertView == null){
			ols = (OrderListSlot)View.inflate(context, R.layout.order_list_slot, null);
		}else{
			ols = (OrderListSlot)convertView;
		}
		ols.setOrder(orders.get(index));
		return ols;
	}

	public void forceReload() {
		notifyDataSetChanged();
	}

	public Order getOrderById(long id) {
		for(int i = 0; i < orders.size(); i++){
			if(orders.get(i).getId() == (int)id){
				return orders.get(i);
			}
		}
		return null;
		//TODO: Lanzar error.
	}

}
