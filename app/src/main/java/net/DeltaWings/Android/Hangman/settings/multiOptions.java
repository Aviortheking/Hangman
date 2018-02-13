package net.DeltaWings.Android.Hangman.settings;

import android.os.Bundle;
import android.preference.PreferenceFragment;

import net.DeltaWings.Android.Hangman.R;

/**
 * Created by 2baze on 12/02/2018.
 */

public class multiOptions extends PreferenceFragment {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		addPreferencesFromResource(R.xml.settings_multiplayer);

	}

}
