package com.example.sam.numbersapp;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by tanya on 05.06.16.
 */
public class UtilityClass {
    public static String extractJSONFromURL(final String urlString, String jsonRootName) {
        StringBuffer contentBuffer = new StringBuffer("");
        try {
            //Set up the URL
            URL url = new URL(urlString);

            // Open URL Connection
            URLConnection urlConn = url.openConnection();

            BufferedReader in = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));

            String text;
            while ((text = in.readLine()) != null) {
                text = text.replace("\\/","/"); //replaces \ with /
                text = text.replaceAll("\\\\r\\\\n", "");  //to make everything in one line
                contentBuffer.append(text);
            }
            String beginWith = "{ \"" + jsonRootName + "\":";
            contentBuffer.insert(0, beginWith); // To add a name to the JSON array
            contentBuffer.append("}"); // To end the brace
            in.close();
        } catch (MalformedURLException e) {
            Log.e("Malformed URL Exception","::");
            e.printStackTrace();
        } catch (IOException e) {
            Log.e("IO Exception","::");
            e.printStackTrace();
        }

        return contentBuffer.toString();
    }

}
