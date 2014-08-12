package com.example.drinkfinder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TextActivity extends Activity implements OnClickListener {
	private LinearLayout list;
	private int number;
	Bundle bundle;
	private String[] letters = { "(0-9)", "(A)", "(B)", "(C)", "(D)", "(E)",
			"(F)", "(G)", "(H)", "(I)", "(J)", "(K)", "(L)", "(M)", "(N)",
			"(O)", "(P)", "(Q)", "(R)", "(S)", "(T)", "(U)", "(V)", "(W)",
			"(X)", "", "(Z)" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_text);
		list = (LinearLayout) findViewById(R.id.LinearLayoutText);
		bundle = getIntent().getExtras();
		if (bundle != null) {
			number = bundle.getInt("number");
			String str = "";
			InputStream is = this.getResources().openRawResource(
					R.raw.drinknames);
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(is));
			if (is != null) {
				try {
					while ((str = reader.readLine()) != null) {
						if (str.equals(letters[number])) {
							String replacer = str.replaceAll("[()]", "--");
							TextView tv = new TextView(this);
							tv.setText(replacer + "\n\n");
							tv.setBackgroundColor(Color.TRANSPARENT);
							tv.setTextColor(Color.CYAN);
							tv.setGravity(Gravity.CENTER);
							tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
							list.addView(tv);
							while (!str.equals("#")) {
								if (!(str = reader.readLine()).equals("#")) {
									TextView text = new TextView(this);
									text.setText("\n" + str + "\n");
									text.setOnClickListener(this);
									text.setBackgroundColor(Color.TRANSPARENT);
									text.setTextColor(Color.CYAN);
									text.setGravity(Gravity.CENTER);
									list.addView(text);
								}

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
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

			}

		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		String currentText = ((TextView) v).getText().toString();
		String refactoredString = currentText.replaceAll("[\n]", "");
		Intent launchOtherActivity = new Intent(getApplicationContext(),
				SomeActivity.class);
		launchOtherActivity.putExtra("txt", refactoredString);
		startActivity(launchOtherActivity);
		
	}

}