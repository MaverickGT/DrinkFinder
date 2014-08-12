package com.example.drinkfinder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;


public class SomeActivity extends Activity {
	TextView tv;
	Cocktail c;
	String text;
	String str = "";
	InputStream is;
	BufferedReader reader;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_some);
		tv = (TextView) findViewById(R.id.textViewSome);
		text = getIntent().getStringExtra("txt");
		is = this.getResources().openRawResource(R.raw.drinks);
		reader = new BufferedReader(
				new InputStreamReader(is));
	

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	@Override
	protected void onResume() {
		
		if (is != null) {
			try {
				while ((str = reader.readLine())!=null) {
					if (str.equals(text)) {
						c = new Cocktail();
						c.setName(str);
						reader.readLine();
						while(!(str = reader.readLine()).equals("")){
							c.addIngredients(str);
						}
						reader.readLine();
						while(!(str = reader.readLine()).equals("")){
							c.setMixing(str);
						}
						
					}
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			is.close();
			tv.setText(c.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		super.onResume();
	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

}
