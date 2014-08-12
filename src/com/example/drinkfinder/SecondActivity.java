package com.example.drinkfinder;


import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Gallery;
import android.widget.ImageView;

@SuppressWarnings("deprecation")
public class SecondActivity extends Activity {

	ImageView selectedImage;
	private int number;
	Gallery gallery;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);

		gallery = (Gallery) findViewById(R.id.gallery1);
		selectedImage = (ImageView) findViewById(R.id.imageView1);
		gallery.setSpacing(1);
		gallery.setAdapter(new GalleryImageAdapter(this));


	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		
		return true;
	}

	@Override
	protected void onResume() {

		// click listener for Gallery
		gallery.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
				number = position;
				Intent launchActivity = new Intent(getApplicationContext(),
						TextActivity.class);
				launchActivity.putExtra("number", number);
				startActivity(launchActivity);
			}

		});
		super.onResume();
	}

}
