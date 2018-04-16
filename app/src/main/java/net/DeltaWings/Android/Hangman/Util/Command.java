package net.DeltaWings.Android.Hangman.Util;

import android.os.AsyncTask;

import java.io.DataInputStream;
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
        } catch (ClassNotFoundException e) {
            System.out.println("\nDEBUG:\texception ClassNotFoundException");
            System.out.println( e.getMessage() );
        } catch (InterruptedException e) {
            System.out.println("\nDEBUG:\texception InterruptedException");
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
    public void envoyer_commande(String c) throws IOException, ClassNotFoundException, InterruptedException {
        System.out.println("\nDEBUG:\tenvoyer_commande");

        Socket socket = null;
        DataOutputStream dataOutputStream = null;
        DataInputStream dataInputStream = null;

        try {
            //establish socket connection to server
            socket = new Socket("192.168.0.2", 53000);

            dataOutputStream = new DataOutputStream(socket.getOutputStream());

            System.out.println("\nDEBUG:\tConnexion ok");
            System.out.println("\nDEBUG:\tdebut envoi");
            dataOutputStream.writeBytes(c);
            System.out.println("\nDEBUG:\tfinenvoi");

            System.out.println("\nDEBUG:\tdebut reception");
            System.out.println("\nDEBUG:\tfin réception");
        }
        catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        if (dataOutputStream != null){
            try {
                dataOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (dataInputStream != null){
            try {
                dataInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        System.out.println("\nDEBUG:\tFin envoyer_commande");
    }

}
