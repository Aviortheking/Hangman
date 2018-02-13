package net.DeltaWings.Android.Hangman.settings;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import net.DeltaWings.Android.Hangman.MainActivity;
import net.DeltaWings.Android.Hangman.R;

import java.util.List;

public class SettingActivity extends PreferenceActivity {

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
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		MainActivity.setTheme(this);

		LinearLayout root = (LinearLayout)findViewById(android.R.id.list).getParent().getParent().getParent();
		Toolbar bar = (Toolbar) LayoutInflater.from(this).inflate(R.layout.preferences_toolbar, root, false);
		root.addView(bar, 0); // insert at top
		bar.setNavigationOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				MainActivity.setTheme(MainActivity.getInstance());
				finish();
				overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
			}
		});

	}

	@Override
	public boolean isValidFragment(String str) {
		overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
		return true;
	}

	@Override
	public void onBuildHeaders(List<Header> target) {
		MainActivity.setTheme(this);
		loadHeadersFromResource(R.xml.settings_headers, target);
		//getListView().setBackgroundColor(getResources().getColor(R.attr.per));

	}

	@Override
	public void onBackPressed() {
		finish();
		overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
	}

}
