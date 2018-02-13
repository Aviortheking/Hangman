package net.DeltaWings.Android.Hangman.settings;

import android.os.Bundle;
import android.preference.PreferenceFragment;

import net.DeltaWings.Android.Hangman.MainActivity;
import net.DeltaWings.Android.Hangman.R;

public class generalOptions extends PreferenceFragment {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.settings_general);

	}

	@Override
	public void onPause() {
		super.onPause();
		MainActivity.themeChanged = true;
		SettingActivity.themeChanged = true;
	}

}