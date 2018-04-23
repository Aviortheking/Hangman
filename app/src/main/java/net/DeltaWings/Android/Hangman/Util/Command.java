package net.DeltaWings.Android.Hangman.Util;

import android.os.AsyncTask;
import android.util.Log;

import net.DeltaWings.Android.Hangman.MainActivity;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Arrays;

public class Command extends AsyncTask<String, Integer, Long> {

	private String[][] replacements = {
			{"é", "<U69>"},
			{"è", "<U68>"},
			{"ê", "<U6A>"},
			{"î", "<U6E>"},
			{"ï", "<U6F>"},
			{"à", "<U60>"},
			{"ç", "<U67>"}
	};

    protected Long doInBackground(String... s) {

        Command c = new Command();

        try {
            c.envoyer_commande(s[0]);
        }
        catch ( UnknownHostException e ) {
            System.out.println("\nDEBUG:\texception UnknownHostException");
            System.out.println( e.getMessage() );
        } catch (IOException e){
            System.out.println("\nDEBUG:\texception IOException");
            System.out.println( e.getMessage() );
        }

        publishProgress((int) (50));
        long l = 0;
        return l;
    }

    protected void onProgressUpdate(Integer... progress) {

    }

    protected void onPostExecute(Long result) {

    }

    //----------------------------------------------------------------------------------------------
    public void envoyer_commande(String c) throws IOException {
        System.out.println("\nDEBUG:\tenvoyer_commande");
	    //Log.v("Command.java", MainActivity.getPref("setting_ip", MainActivity.instance.getApplicationContext()));
	    //Log.v("Command.java", ""+Integer.parseInt(MainActivity.getPref("setting_port", MainActivity.instance.getApplicationContext())));
	    Socket socket = new Socket("192.168.1.2", 53000);

	    DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());

	    for(String[] replacement: replacements) {
	    	Log.v("Command.java", Arrays.toString(replacement));
		    c = c.replace(replacement[0], replacement[1]);
	    }

	    System.out.println("\nDEBUG:\tConnexion ok");
	    System.out.println("\nDEBUG:\tdebut envoi");
	    Log.v("Command.java", "Sended String "+c);
	    dataOutputStream.writeBytes(c);
	    System.out.println("\nDEBUG:\tfinenvoi");

	    System.out.println("\nDEBUG:\tdebut reception");
	    System.out.println("\nDEBUG:\tfin réception");

	    socket.close();
	    dataOutputStream.close();

        System.out.println("\nDEBUG:\tFin envoyer_commande");
    }

}
