package net.DeltaWings.Android.Hangman;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

	public static MainActivity instance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
		instance = this;

	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_main);
	    Toolbar toolbar = findViewById(R.id.toolbar);
	    setSupportActionBar(toolbar);

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


}
