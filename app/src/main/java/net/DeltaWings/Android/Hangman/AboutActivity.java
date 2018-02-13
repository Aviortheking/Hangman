package net.DeltaWings.Android.Hangman;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class AboutActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		MainActivity.setTheme(this);
		setContentView(R.layout.activity_about);

		ActionBar actionBar = getSupportActionBar();
		if (actionBar != null) {
			actionBar.setHomeButtonEnabled(true);
			actionBar.setDisplayHomeAsUpEnabled(true);
			actionBar.setDisplayShowHomeEnabled(true);
		} else {
			Toast.makeText(MainActivity.getInstance(), "A problem occurred Please restart the application", Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	public void onBackPressed() {
		finish();
		overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
	}

	@Override
	public boolean onSupportNavigateUp(){
		finish();
		overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
		return true;
	}
}
