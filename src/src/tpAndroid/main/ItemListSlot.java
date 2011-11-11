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
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ar.art.R;

public class ItemListSlot extends RelativeLayout {
	
	private Item item;
	private ImageView image;
	private TextView title;
	private TextView price;

	public ItemListSlot(Context context, AttributeSet attrs) {
		super(context, attrs);
		image = (ImageView) findViewById(R.id.image);
		title = (TextView) findViewById(R.id.title);
		price = (TextView) findViewById(R.id.price);
	}
	
	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();
		image = (ImageView) findViewById(R.id.image);
		title = (TextView) findViewById(R.id.title);
		price = (TextView) findViewById(R.id.price);
	}

	public void setItem(Item item) {
		this.item = item;
		Drawable draw = drawable_from_url(item.getImgUrl());
		this.image.setImageDrawable(draw);
		this.title.setText(item.getName());
		this.price.setText("$" + new Double((item.getPrice())).toString());
	}
	
	private Drawable drawable_from_url(String url){
		try{
	    Bitmap x;

	    HttpURLConnection connection = (HttpURLConnection)new URL(url) .openConnection();
	    connection.setRequestProperty("User-agent","Mozilla/4.0");

	    connection.connect();
	    InputStream input = connection.getInputStream();

	    x = BitmapFactory.decodeStream(input);
	    Drawable asd = new BitmapDrawable(x);
	    return asd;
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}

	public Item getItem() {
		return item;
	}

}
