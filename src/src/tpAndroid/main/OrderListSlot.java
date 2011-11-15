package tpAndroid.main;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.TextView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ar.art.R;

public class OrderListSlot extends RelativeLayout  {
	
	private Order order;
	private TextView created_date;
	private TextView confirmed_date;
	private TextView address;

	public OrderListSlot(Context context, AttributeSet attrs) {
		super(context, attrs);
		created_date = (TextView) findViewById(R.id.created_date);
		confirmed_date = (TextView) findViewById(R.id.confirmed_date);
		address = (TextView) findViewById(R.id.address);
	}
	
	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();
		created_date = (TextView) findViewById(R.id.created_date);
		confirmed_date = (TextView) findViewById(R.id.confirmed_date);
		address = (TextView) findViewById(R.id.address);
	}

	public void setOrder(Order order) {
		this.order = order;
		this.created_date.setText(order.getCreated_date());
		this.confirmed_date.setText(order.getConfirmed_date());
		if(order.getAddress().getAddress_line_1() == null){
			this.address.setText(getResources().getString(R.string.no_address));
		}else{
			this.address.setText(order.getAddress().getAddress_line_1());
		}
		if(order.getAddress().getCity() == null){
			this.address.setText(this.address.getText() + ", "+ getResources().getString(R.string.no_city));
		}else{
			this.address.setText(this.address.getText()+ ", " + order.getAddress().getCity());
		}
	}
	
	public Order getOrder() {
		return order;
	}

}
