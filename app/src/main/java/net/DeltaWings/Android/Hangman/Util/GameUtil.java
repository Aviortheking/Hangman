package net.DeltaWings.Android.Hangman.Util;

import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import net.DeltaWings.Android.Hangman.MainActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class GameUtil {

	private String word = "example";
	private ArrayList<String> res = new ArrayList<>();
	private ArrayList<String> letters = new ArrayList<>();
	private HashMap<String, String> returning = new HashMap<>();
	private String tag = "GameUtil";

	public GameUtil() {
		//Generate Word
		for (int i = 0; i < word.length(); i++) {
			res.add("_");
		}
		Log.v(tag, res.toString());
	}

	public boolean datasReader(HashMap<String, String> datas) {
		returning.clear();
		String query = datas.get("query");
		if(Objects.equals(query, "letter")) {
			String letter = datas.get("letter");
			if(word.contains(letter)) {
				Log.v(tag, "2");
				for (int i = 0; i < word.length(); i++) {
					if(Objects.equals(String.valueOf(word.charAt(i)), letter)) {
						res.set(i, letter);
					}
				}
				if(!res.contains("_")) {
					//winner
					returning.put("status", "won");
				}
			} else {

			}
			letters.add(letter);
			returning.put("newWord", TextUtils.join("", res));
			returning.put("lettersUsed", TextUtils.join(",", letters));
		} else if (Objects.equals(query, "word")) {
			if(Objects.equals(datas.get("word"), word)) {
				returning.put("status", "won");

			}
		}
		Log.v(tag, returning.toString());
		Toast.makeText(MainActivity.getInstance(), returning.get("newWord"), Toast.LENGTH_SHORT).show();
		return true;
	}

	public HashMap<String, String> datasSender() {
		return returning;
	}
}

/*
Trames sended to app :
	status = status de la game (won, Integer of tries left, lost)
	lettersUsed = String of letters used (,)
	newWord = _________


Trames sended from app:
	word = word || letter = letter
	userId = userId
	username = username
	ip = ip
 */