package net.DeltaWings.Android.Hangman.Util;

import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.Random;

public class GameUtil {

	private String word = "example";
	private ArrayList<String> res = new ArrayList<>();
	private ArrayList<String> letters = new ArrayList<>();
	private HashMap<String, String> returning = new HashMap<>();
	private String tag = "GameUtil";

	public GameUtil() {
		//Generate Word

		ArrayList<String> list = new ArrayList<>();
		try {

			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("/sdcard/list.txt"), "UTF-8"));

			String line = br.readLine();

			while (line != null) {
				list.add(line);
				line = br.readLine();
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		//Log.v(tag, list.toString());
		word = list.get(randInt(0, list.size()-1));
		Log.v(tag, word);

		/*try {
			URI uri = new URI("/sdcard/list.txt");

			byte[] encoded = Files.readAllBytes(Paths.get("/sdcard/list.txt"));
			Files.readAllLines(Paths.get(uri));
			String[] strings = new String(encoded, Charset.defaultCharset()).split("\r");
			Log.v(tag, strings[randInt(0, strings.length-1)]);
			word = strings[randInt(0, strings.length-1)];
		} catch (Exception e) {
			//ntm
		}*/





		for (int i = 0; i < word.length(); i++) {
			res.add("_");
		}

		Log.v(tag, res.toString());
	}

	public boolean checkLetter(String letter) {
		if(this.word.contains(letter)) {
			Log.v(tag, "Letter Found !");
			for (int i = 0; i < this.word.length(); i++) {
				if(String.valueOf(this.word.charAt(i)).equals(letter)) {
					res.set(i, letter);
				}
			}
			return true;
		}
		return false;
	}

	public boolean checkWord(String word) {
		if(word.equals(this.word)) {
			for (int i = 0; i < word.length(); i++) {
				res.set(i, word.split("")[i]);
			}
			return true;
		}
		return false;
	}

	public boolean hasWon() {
		return !res.contains("_");
	}

	public static int randInt(int min, int max) {
		Random rand = new Random();
		int randomNum = rand.nextInt((max - min) + 1) + min;
		return randomNum;
	}

	public ArrayList<String> getUndescores() {
		return res;
	}

	public String solution() {
		return word;
	}
}