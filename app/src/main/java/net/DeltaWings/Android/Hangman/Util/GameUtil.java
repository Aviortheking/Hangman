package net.DeltaWings.Android.Hangman.Util;

import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.util.Xml;
import android.widget.Toast;

import net.DeltaWings.Android.Hangman.MainActivity;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class GameUtil {

	private String word = "example";
	private ArrayList<String> res = new ArrayList<>();
	private ArrayList<String> letters = new ArrayList<>();
	private HashMap<String, String> returning = new HashMap<>();
	private String tag = "GameUtil";

	public GameUtil() {
		//Generate Word

		JSONObject obj = null;

		String s = "https://raw.githubusercontent.com/dwyl/english-words/master/words_dictionary.json";


		try {

			byte[] encoded = Files.readAllBytes(Paths.get("/sdcard/list.txt"));
			String[] strings = new String(encoded, Charset.defaultCharset()).split("\r");
			Log.v(tag, strings[randInt(0, strings.length-1)]);
			word = strings[randInt(0, strings.length-1)];
		} catch (Exception e) {
			//ntm
		}





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
		//Toast.makeText(MainActivity.getInstance(), returning.get("newWord"), Toast.LENGTH_SHORT).show();
		return true;
	}

	public HashMap<String, String> datasSender() {
		return returning;
	}

	public static String generateRandomWords()
	{
		String randomStrings;
		Random random = new Random();
		char[] word = new char[random.nextInt(8)+3]; // words of length 3 through 10. (1 and 2 letter words are boring.)
		for(int j = 0; j < word.length; j++)
		{
			word[j] = (char)('a' + random.nextInt(26));
		}
		randomStrings = new String(word);

		return randomStrings;
	}

	public static int randInt(int min, int max) {
		Random rand = new Random();
		int randomNum = rand.nextInt((max - min) + 1) + min;
		return randomNum;
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