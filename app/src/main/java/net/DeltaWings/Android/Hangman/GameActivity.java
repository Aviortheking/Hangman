package net.DeltaWings.Android.Hangman;

import android.animation.ObjectAnimator;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.DecelerateInterpolator;
import android.widget.ProgressBar;
import android.widget.Toast;

import static net.DeltaWings.Android.Hangman.MainActivity.instance;

public class GameActivity extends AppCompatActivity {

	ProgressBar progressBar;
	AlertDialog.Builder builder;

	@Override
	public void onBackPressed() {
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

		ActionBar actionBar = getSupportActionBar();
		if (actionBar != null) {
			actionBar.setHomeButtonEnabled(true);
			actionBar.setDisplayHomeAsUpEnabled(true);
			actionBar.setDisplayShowHomeEnabled(true);
		} else {
			Toast.makeText(instance, " There is a problem here let's see what it is", Toast.LENGTH_LONG).show();
		}

		progressBar = findViewById(R.id.progressBar);

		DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				switch (which){
					case DialogInterface.BUTTON_POSITIVE:
						Toast.makeText(instance, "Selected Yes", Toast.LENGTH_LONG).show();
						break;

					case DialogInterface.BUTTON_NEGATIVE:
						Toast.makeText(instance, "Selected No", Toast.LENGTH_LONG).show();
						break;
				}
			}
		};

		builder = new AlertDialog.Builder(this);

		builder.setMessage("Are you sure?").setPositiveButton("Yes", dialogClickListener)
				.setNegativeButton("No", dialogClickListener);
	}

	private void setProgressAnimate(ProgressBar pb, int progressTo)
	{
		ObjectAnimator animation = ObjectAnimator.ofInt(pb, "progress", pb.getProgress(), progressTo);
		animation.setDuration(250);
		animation.setInterpolator(new DecelerateInterpolator());
		animation.start();
	}
}
