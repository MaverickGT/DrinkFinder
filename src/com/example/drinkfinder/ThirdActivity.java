package com.example.drinkfinder;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Random;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;


public class ThirdActivity extends Activity {
	private TextView tv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_third);
		tv = (TextView) findViewById(R.id.textViewThird);
	
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void getDrinks() throws IOException{
		ArrayList<Cocktail> cocktails = new ArrayList<Cocktail>();
		InputStream is =this.getResources().openRawResource(R.raw.drinks);
	    InputStreamReader isr = new InputStreamReader(is);
	    BufferedReader br = new BufferedReader(isr, 512);
	    int x=0;
	    try {
	    String l=br.readLine();
		while(x!=1){
			Cocktail c = new Cocktail();
			if(l.equals("#")){
				l=br.readLine();
				if(l.equals("#")){
					x=1;
					}else{
					l=br.readLine();
					c.setName(l);l=br.readLine();l=br.readLine();
					while(!l.equals("")){
						c.addIngredients(l);
						l=br.readLine();
					}
					l=br.readLine();l=br.readLine();
					c.setMixing(l);
					cocktails.add(c);l=br.readLine();l=br.readLine();
			}
				}else{
					c.setName(l);l=br.readLine();l=br.readLine();
					while(!l.equals("")){
						c.addIngredients(l);
						l=br.readLine();
					}
					l=br.readLine();l=br.readLine();
					c.setMixing(l);
					cocktails.add(c);l=br.readLine();l=br.readLine();				
			}
	    }
		} catch (IOException e) {
			x=1;
			
		}
	    is.close();
	    isr.close();
	    br.close();
	    int size=cocktails.size();
	    Random rand = new Random();
	    int randomnumber = rand.nextInt(size);
	    Cocktail c = new Cocktail();
	    c=cocktails.get(randomnumber);
	    tv.setText(c.toString());
	   
	    
	}
	@Override
	protected void onResume(){
		try {
			getDrinks();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		super.onResume();
	}
	

}
