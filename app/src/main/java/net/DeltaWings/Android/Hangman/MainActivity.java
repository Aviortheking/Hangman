package net.DeltaWings.Android.Hangman;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

	public static MainActivity instance;
	public static Boolean test = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
		instance = this;
	    test = true;
		final ProgressDialog progressDialog = new ProgressDialog(instance);

	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_main);
	    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
	    setSupportActionBar(toolbar);

		//Multiplayer button
	    findViewById(R.id.multiplayerButton).setOnClickListener(new View.OnClickListener() {
		    @Override
		    public void onClick(View v) {
				//setContentView(R.layout.activity_multiplayer);
			    progressDialog.setMessage("Connecting...");
			    //progressDialog.setCancelable(false);
			    progressDialog.setOnShowListener(new ProgressDialog.OnShowListener() {
				    @Override
				    public void onShow(DialogInterface dialog) {
					    Toast.makeText(instance, "test", Toast.LENGTH_LONG).show();
				    }
			    });
			    progressDialog.setOnCancelListener(new ProgressDialog.OnCancelListener() {
				    @Override
				    public void onCancel(DialogInterface dialog) {
					    progressDialog.setMessage("Cancelling...");
					    progressDialog.setCancelable(false);

					    //wait for canceletion.
				    }
			    });
			    progressDialog.show();
			    //Make Connection

			    //
		    }
	    });

	    findViewById(R.id.singleplayerButton).setOnClickListener(new View.OnClickListener() {
		    @Override
		    public void onClick(View v) {

		    }
	    });

	    findViewById(R.id.progressBar).setOnClickListener(new View.OnClickListener() {
		    @Override
		    public void onClick(View v) {
			    Toast.makeText(instance, test + "", Toast.LENGTH_LONG).show();
		    }
	    });
    }


}
