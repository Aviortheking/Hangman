package net.DeltaWings.Android.Hangman;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import net.DeltaWings.Android.Hangman.Util.Command;
import net.DeltaWings.Android.Hangman.settings.SettingActivity;

public class MainActivity extends AppCompatActivity {

	public static MainActivity instance;

	public static MainActivity getInstance() {
		return instance;
	}
	public boolean single = true;
	public static boolean themeChanged = false;

	@Override
	protected void onResume() {
		super.onResume();
		if(themeChanged) {
			themeChanged = false;
			Intent intent = getIntent();
			overridePendingTransition(0, 0);
			intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
			finish();

			overridePendingTransition(0, 0);
			startActivity(intent);
		}
	}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
		instance = this;

	    super.onCreate(savedInstanceState);

	    if(getPref("setting_theme", MainActivity.getInstance()) != "Color") {
	    	setPref("setting_theme", "Color", this.getApplicationContext());
	    }

	    setTheme(this);
	    setContentView(R.layout.activity_main);


		//Multiplayer button
	    findViewById(R.id.singleplayerButton).setOnClickListener(new View.OnClickListener() {
		    @Override
		    public void onClick(View v) {
			    startActivity(new Intent(instance, GameActivity.class));
			    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
		    }
	    });

	    findViewById(R.id.testsButton).setOnClickListener(new View.OnClickListener() {
		    @Override
		    public void onClick(View v) {
			    new Command().execute("AFFICHER|test");
			    Toast.makeText(instance, getPref("setting_theme", MainActivity.getInstance()), Toast.LENGTH_LONG).show();
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
				startActivity(new Intent(instance, SettingActivity.class));
				overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

				return true;
			case R.id.navigation_about:
				startActivity(new Intent(instance, AboutActivity.class));
				overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}

	public static void setPref(String key, String value, Context context) {
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
		SharedPreferences.Editor editor = prefs.edit();
		editor.putString(key, value);
		editor.apply();
	}

	public static String getPref(String key, Activity context) {
		SharedPreferences sharedPref=context.getPreferences(Context.MODE_PRIVATE);
		return sharedPref.getString(key, "pokemon");
	}

	public static void setTheme(Activity activity) {
		activity.setTheme(activity.getResources().getIdentifier("DeltaWings."+getPref("setting_theme", MainActivity.getInstance()), "style", activity.getPackageName()));
	}
}
