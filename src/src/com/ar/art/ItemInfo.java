package com.ar.art;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ar.art.R;

public class ItemInfo extends RelativeLayout {
	
	private TextView tag;
	private TextView value;

	public ItemInfo(Context context, AttributeSet attrs) {
		super(context, attrs);
		tag = (TextView) findViewById(R.id.tag);
		value = (TextView) findViewById(R.id.value);
	}
	
	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();
		tag = (TextView) findViewById(R.id.tag);
		value = (TextView) findViewById(R.id.value);
	}

	public void setInfo(String tag, String value) {
		this.tag.setText(tag);
		this.value.setText(value);
	}
	
}
