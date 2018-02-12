package net.DeltaWings.Android.Hangman;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

	public static MainActivity instance;

	public static MainActivity getInstance() {
		return instance;
	}
	public boolean single = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
		instance = this;

	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_main);


		//Multiplayer button
	    findViewById(R.id.multiplayerButton).setOnClickListener(new View.OnClickListener() {
		    @Override
		    public void onClick(View v) {
			    startActivity(new Intent(instance, GameActivity.class));
			    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
		    }
	    });

	    findViewById(R.id.singleplayerButton).setOnClickListener(new View.OnClickListener() {
		    @Override
		    public void onClick(View v) {
			    Toast.makeText(instance, "WIP", Toast.LENGTH_LONG).show();
		    }
	    });

    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.home, menu);
		// return true so that the menu pop up is opened
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.navigation_options:
				// EITHER CALL THE METHOD HERE OR DO THE FUNCTION DIRECTLY
				startActivity(new Intent(instance, SettingsActivity.class));
				overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

				return true;

			default:
				return super.onOptionsItemSelected(item);
		}
	}
}
