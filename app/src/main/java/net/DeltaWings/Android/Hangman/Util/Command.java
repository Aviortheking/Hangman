package net.DeltaWings.Android.Hangman.Util;

import android.os.AsyncTask;

import net.DeltaWings.Android.Hangman.MainActivity;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Command extends AsyncTask<String, Integer, Long> {

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

	    Socket socket = new Socket(MainActivity.getPref("setting_ip", MainActivity.instance.getApplicationContext()), Integer.parseInt(MainActivity.getPref("setting_port", MainActivity.instance.getApplicationContext())));

	    DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());

	    System.out.println("\nDEBUG:\tConnexion ok");
	    System.out.println("\nDEBUG:\tdebut envoi");
	    dataOutputStream.writeBytes(c);
	    System.out.println("\nDEBUG:\tfinenvoi");

	    System.out.println("\nDEBUG:\tdebut reception");
	    System.out.println("\nDEBUG:\tfin réception");

	    socket.close();
	    dataOutputStream.close();

        System.out.println("\nDEBUG:\tFin envoyer_commande");
    }

}
