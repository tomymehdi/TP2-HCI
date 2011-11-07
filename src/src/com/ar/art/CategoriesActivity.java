package com.ar.art;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.ar.art.objects.Category;

public class CategoriesActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initialize();
	}

	private void initialize() {
		ScrollView sv = new ScrollView(this);
		LinearLayout ll = new LinearLayout(this);
		ll.setOrientation(LinearLayout.VERTICAL);
		sv.addView(ll);
		((ArtApplication) (this.getApplication())).services.loadCategories();
		List<Category> categories = ((ArtApplication) (this.getApplication())).app
				.getCategories();
		int i = 0;
		for (Category c : categories) {
			Button b = new Button(this);
			b.setText(c.getName());
			b.setBackgroundResource(R.drawable.main_button);
			b.setHeight(100);
			b.setWidth(LayoutParams.FILL_PARENT);
			b.setTextScaleX(24);
			b.setOnClickListener(new View.OnClickListener() {
				public void onClick(View arg0) {
					Intent intent = new Intent(CategoriesActivity.this,
							ProductsActivity.class);
					startActivity(intent);
				}
			});
			ll.addView(b);
			i++;
		}
		/*
		 *     <ImageView
        android:id="@+id/imageView1"
        android:layout_width="50px"
        android:layout_height="50px"
        android:layout_alignParentRight="true"
        android:layout_alignParentTop="true"
        android:src="@drawable/lupa" />
		 */
		
		ImageView lupa = new ImageView(this);
		lupa.setImageResource(R.drawable.lupa);
		lupa.setLayoutParams(new Gallery.LayoutParams(LayoutParams.WRAP_CONTENT,
				  LayoutParams.WRAP_CONTENT));
		RelativeLayout rl = new RelativeLayout(this);
		System.out.println(("1"));
		rl.addView(lupa);
		System.out.println("2");
		rl.addView(sv);
		System.out.println("3");
		setContentView(rl);
	}
}
