package net.DeltaWings.Android.Hangman.Util;

import java.util.List;
import net.DeltaWings.Android.Hangman.MainActivity;
import net.DeltaWings.Android.Hangman.Util.GameUtil;

public class ConnectionUtil {

	static public ConnectionUtil connection;

	private String ip = null;
	private String userID = null;
	private String jsp = "jsp";
	public int percent = 0;
	private boolean single = MainActivity.getInstance().single;
	private GameUtil gameUtil = null;

	public ConnectionUtil() {
		//check gamemode
		if(single) {
			gameUtil = new GameUtil();
		} else {
			//init connection
			//send user name, generated userID
		}

		
	}

	public void closeConnection() {
		if(single) {
			gameUtil = null;
		} else {
			//send null username and null userID
		}
		
	}

	public boolean sendData(List<Object> query) {
		if(single) return gameUtil.datasReader(query);
		else {
			//return state
		}
		return false;
	}

	public Object getData() {
		if(single) {
			return gameUtil.datasSender();
		} else {

		}
		return null;
	}
}
