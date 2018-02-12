package net.DeltaWings.Android.Hangman;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.animation.DecelerateInterpolator;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import net.DeltaWings.Android.Hangman.Util.ConnectionUtil;

import java.util.ArrayList;
import java.util.List;

public class GameActivity extends AppCompatActivity {

	private AlertDialog.Builder builder;
	private ConnectionUtil co;
	private List<String> letters = new ArrayList<>();

	@Override
	public void onBackPressed() {
		builder.show();
	}

	@Override
	public boolean onSupportNavigateUp(){
		builder.show();
		return true;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.game_activity);

		ProgressBar progressBar = findViewById(R.id.progressBar);
		EditText input = findViewById(R.id.input);

		log("Logs");
		log("Connecting...");
		co = new ConnectionUtil();

		ActionBar actionBar = getSupportActionBar();
		if (actionBar != null) {
			actionBar.setHomeButtonEnabled(true);
			actionBar.setDisplayHomeAsUpEnabled(true);
			actionBar.setDisplayShowHomeEnabled(true);
		} else {
			log("A problem occurred Please restart the application");
		}





		DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				switch (which){
					case DialogInterface.BUTTON_POSITIVE:
						//Close Connection
						log("Closing connection");
						co.closeConnection();

						//send to main menu
						finish();
						overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
						break;
				}
			}
		};






		//Input Management
		input.setOnEditorActionListener(new TextView.OnEditorActionListener() {
			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				EditText txt = ((EditText) v);
				String text = txt.getText().toString();
				if(text.length() == 1) {
					log("Letter : " + text);
					if(letters.contains(text)) {
						log("Letter : " + text + "Already in");
					} else {
						log("Sending letter " + text);
						//Send Letter
						txt.setText("", TextView.BufferType.EDITABLE);
					}
				} else if(text.length() > 1) {
					log("Word : " + text);
					//Send Letter
					txt.setText("", TextView.BufferType.EDITABLE);
				}
				return false;
			}
		});
		//Input Management






		//Return Builder
		builder = new AlertDialog.Builder(this);

		builder.setMessage("Do you really want to quit your game ?")
				.setPositiveButton("Yes", dialogClickListener)
				.setNegativeButton("No", dialogClickListener);
		//Return Builder






		log("Connected !");
		setProgressAnimate(progressBar, 100);
	}


	private void setProgressAnimate(ProgressBar pb, int progressTo) {
		ObjectAnimator animation = ObjectAnimator.ofInt(pb, "progress", pb.getProgress(), progressTo);
		animation.setDuration(250);
		animation.setInterpolator(new DecelerateInterpolator());
		animation.start();
	}

	private void log(String message) {
		TextView logs = findViewById(R.id.logs);
		logs.setText(logs.getText().toString() + "\n" + message);
	}
}
