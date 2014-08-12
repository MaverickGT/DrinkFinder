package com.example.drinkfinder;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class MainActivity extends Activity implements OnClickListener {
	private Button alphabetic,randomdrink;
	MediaPlayer mediaPlayer;
	AudioManager amanager;
	MenuItem mute;
	MenuItem play;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		alphabetic = (Button) findViewById(R.id.alphabet);
		randomdrink = (Button) findViewById(R.id.selecter);
		alphabetic.setOnClickListener(this);
		randomdrink.setOnClickListener(this);
		amanager=(AudioManager)getSystemService(Context.AUDIO_SERVICE);
		mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.wave);
		mediaPlayer.start();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_actions, menu);
		mute = menu.findItem(R.id.action_mute);
		play = menu.findItem(R.id.action_play);
		mute.setVisible(true);
		play.setVisible(false);
		return true;
	}

	@Override
	public void onClick(View v) {
		if(v==alphabetic){
			startSecondActivity();
			}else if(v==randomdrink){
				startThirdActivity();
			}
		
	}
	public void startSecondActivity(){
		Intent launchOtherActivity = new Intent(getApplicationContext(),SecondActivity.class);
		startActivity(launchOtherActivity);
	}
	public void startThirdActivity(){
		Intent launchOtherActivity = new Intent(getApplicationContext(),ThirdActivity.class);
		startActivity(launchOtherActivity);
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    
	    switch (item.getItemId()) {
	        case R.id.action_mute:
	            amanager.setStreamMute(AudioManager.STREAM_MUSIC, true);
	            mute.setVisible(false);
	    		play.setVisible(true);
	            return true;
	        case R.id.action_play:
	        	amanager.setStreamMute(AudioManager.STREAM_MUSIC, false);
	        	mute.setVisible(true);
	    		play.setVisible(false);
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

	@Override
	protected void onDestroy() {
		amanager.setStreamMute(AudioManager.STREAM_MUSIC, false);
		mediaPlayer.stop();
		mediaPlayer.release();
		super.onDestroy();
	}



}

