package net.DeltaWings.Android.Hangman;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.animation.DecelerateInterpolator;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import net.DeltaWings.Android.Hangman.Util.Command;
import net.DeltaWings.Android.Hangman.Util.GameUtil;

import java.util.ArrayList;

public class GameActivity extends AppCompatActivity {

	private AlertDialog.Builder builder;
	private TextView word;
	static private GameUtil gameUtil;
	private ArrayList<String> letters = new ArrayList<>();
	private Context context = this;

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
		MainActivity.setTheme(this);
		setContentView(R.layout.game_activity);

		word = findViewById(R.id.word);
		ProgressBar progressBar = findViewById(R.id.progressBar);
		EditText input = findViewById(R.id.input);

		log("Logs");

		gameUtil = new GameUtil();
		word.setText(gameUtil.getUndescores().toString().replace(",", ""));

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
						//send to main menu
						finish();
						overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
						break;
				}
			}
		};

		//Return Builder
		builder = new AlertDialog.Builder(context);

		builder.setMessage("Do you really want to quit the game ?")
				.setPositiveButton("Yes", dialogClickListener)
				.setNegativeButton("No", dialogClickListener);
		//Return Builder






		//Input Management
		input.setOnEditorActionListener(new TextView.OnEditorActionListener() {
			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

				EditText editText = ((EditText) v);
				String word = editText.getText().toString().toLowerCase();

				switch (word.length()) {
					case 0:
						Toast.makeText(MainActivity.getInstance(), "Please type something!", Toast.LENGTH_LONG).show();
						break;

					case 1:
						if(letters.contains(word)) { // Check si lettre déjà noté
							Toast.makeText(MainActivity.getInstance(), "letter already used!", Toast.LENGTH_LONG).show();
							editText.setText("");
							break;
						}
						Boolean res = gameUtil.checkLetter(word);
						if(res && gameUtil.hasWon()) { // check si joueur vainqueur

							win();
							break;
						} else { // si joueur non vainqueur
							letters.add(word);
							((TextView) findViewById(R.id.letters)).setText(letters.toString().toLowerCase());
							if(res) {
								Toast.makeText(MainActivity.getInstance(), "Correct Letter!", Toast.LENGTH_LONG).show();

								((TextView) findViewById(R.id.word)).setText(gameUtil.getUndescores().toString().replace(",", ""));
								new Command().execute("AFFICHER|"+gameUtil.getUndescores().toString().replace(",", ""));
							} else {
								Toast.makeText(MainActivity.getInstance(), "Incorrect letter!", Toast.LENGTH_LONG).show();
								//update image
								if(letters.size() == 10) {
									lost();
								}
							}
							editText.setText("");

							log("You used : " + word);
							break;
						}

					default:
						if(gameUtil.checkWord(word)) {
							win();
						} else {
							Toast.makeText(MainActivity.getInstance(), "Incorrect word!", Toast.LENGTH_LONG).show();
							//update image
							if(letters.size() == 10) {
								lost();
							}
						}
						break;
				}




/*
				if(word.length() == 1) { //is letter
					if(letters.contains(text)) {
						log("Letter : " + text + "Already in");
					} else {
						log("Sending letter : \"" + text +"\"");

						//preparing datas
						HashMap<String, String> temp = new HashMap<>();
						temp.put("query", "letter");
						temp.put("letter", text);
						co.sendData(temp);

						//get result
						HashMap<String, String> result = co.getDatas();
						Toast.makeText(MainActivity.getInstance(), result.toString(), Toast.LENGTH_SHORT).show();
						word.setText(result.get("newWord"));
						new Command().execute("AFFICHER|"+result.get("newWord"));
						if(Objects.equals(result.get("status"), "won")) { //check if player won
							//Game Won
							new Command().execute("AFFICHER|Tu as gagné");

							DialogInterface.OnClickListener clickListener = new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog, int which) {
									switch (which){
										case DialogInterface.BUTTON_POSITIVE:
											//Close Connection
											Intent intent = getIntent();
											overridePendingTransition(0, 0);
											intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
											finish();

											overridePendingTransition(0, 0);
											startActivity(intent);
											break;
										default:
											finish();
											overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
											break;
									}
								}
							};

							new AlertDialog.Builder(context)
									.setMessage("You won!")
									.setPositiveButton("Restart", clickListener)
									.setNegativeButton("Quit", clickListener)
									.show();
						} else {
							letters = Arrays.asList(result.get("lettersUsed"));
							log(letters.toString());
						}
						//Send Letter
						editText.setText("", TextView.BufferType.EDITABLE);
					}
				} else if(text.length() > 1) {
					log("Word : " + text);
					HashMap<String, String> temp = new HashMap<>();
					temp.put("query", "word");
					temp.put("letter", text);
					co.sendData(temp);

					editText.setText("", TextView.BufferType.EDITABLE);

				}*/
				return false;
			}
		});
		//Input Management













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
		Log.v("GameActivity", message);
		TextView logs = findViewById(R.id.logs);
		logs.setText(logs.getText().toString() + "\n" + message);
	}

	private void win() {
		new Command().execute("AFFICHER|Tu as gagné !!!");
		DialogInterface.OnClickListener clickListener = new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				switch (which){
					case DialogInterface.BUTTON_POSITIVE:
						//Close Connection
						Intent intent = getIntent();
						overridePendingTransition(0, 0);
						intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
						finish();

						overridePendingTransition(0, 0);
						startActivity(intent);
						break;
					default:
						finish();
						overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
						break;
				}
			}
		};

		new AlertDialog.Builder(context)
				.setMessage("Tu as gagné!")
				.setPositiveButton("Recommencer", clickListener)
				.setNegativeButton("Quitter", clickListener)
				.show();
	}

	private void lost() {
		new Command().execute("AFFICHER|Tu as perdu !!!");
		DialogInterface.OnClickListener clickListener = new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				switch (which){
					case DialogInterface.BUTTON_POSITIVE:
						//Close Connection
						Intent intent = getIntent();
						overridePendingTransition(0, 0);
						intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
						finish();

						overridePendingTransition(0, 0);
						startActivity(intent);
						break;
					default:
						finish();
						overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
						break;
				}
			}
		};

		new AlertDialog.Builder(context)
				.setMessage("Tu as perdu :( !")
				.setPositiveButton("Recommencer", clickListener)
				.setNegativeButton("Quitter", clickListener)
				.show();
	}
}
