package com.ar.art;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;

public class Debug {
	public Debug(Activity a, String s) {
		AlertDialog.Builder builder = new AlertDialog.Builder(a);
		builder.setMessage(s).setCancelable(
				false).setPositiveButton("Yes",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
					}
				}).setNegativeButton("No",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						dialog.cancel();
					}
				});
		AlertDialog alert = builder.create();
		alert.show();
	}
}
