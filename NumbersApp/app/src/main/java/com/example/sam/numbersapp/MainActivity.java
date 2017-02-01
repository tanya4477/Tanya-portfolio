package com.example.sam.numbersapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * This is the MainActivity
 */
public class MainActivity extends FragmentActivity {
    private final String SOURCE_URL = "http://dev.tapptic.com/test/json.php";
    private static final String JSON_ROOT = "ImageMap";
    private final long WAITING_TIME = 2000;

    private List<MasterData> list;
    private GoogleApiClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ConnectionDetector.context = getApplicationContext();
        tryFetchingData();

    }

    /**
     * This method checks the internet connection and try fetching data
     */
    private void tryFetchingData(){
        // check for Internet status
        if (!ConnectionDetector.isConnectingToInternet()) {
            // Internet connection is not present
            // Ask user to connect to Internet
            showAlertDialog(this, "No Internet Connection",
                    "You do not have internet connection.", false);
        }else{
            populateData();
        }
    }

    /**
     * This fetches data and start data population
     */
    private void populateData(){
        final StringBuffer finalBuffer = new StringBuffer("");
        Thread jsonThread = new Thread(new Runnable() {
            @Override
            public void run() {
                String str = UtilityClass.extractJSONFromURL(SOURCE_URL, JSON_ROOT);
                finalBuffer.append(str);
            }
        });
        jsonThread.start();

        // To make the process synchronous
        try {
            jsonThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        this.list = fillWithData(finalBuffer.toString());
        createRecyclerView(list);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();

    }

    /**
     * This method creates Recycler View
     * @param list
     */
    private void createRecyclerView(List<MasterData> list){
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(list, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


    /** This fills the data from JSON Array
     * @param strJson
     * @return list of Data
     */
    private List<MasterData> fillWithData(String strJson) {
        List<MasterData> dataList = new ArrayList<>();

        try {
            //Create the root JSONObject from the JSON string.
            JSONObject jsonRootObject = new JSONObject(strJson);

            //Get the instance of JSONArray that contains JSONObjects
            JSONArray jsonArray = jsonRootObject.optJSONArray(JSON_ROOT);

            //Iterate the jsonArray and print the info of JSONObjects
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                String image = jsonObject.optString("image").toString();
                String name = jsonObject.optString("name").toString();

                dataList.add(new MasterData(image, name));
            }
        } catch (JSONException e) {
            Log.e("JSON Exception", "-----------------");
            e.printStackTrace();
        }
        return dataList;
    }

    @SuppressLint("LongLogTag")
    @Override
    public void onStart() {
        super.onStart();
        createRecyclerView(list);
    }

    @Override
    public void onStop() {
        super.onStop();
    }


    /**
     * This method shows AlertDialog if internet is not available and connects automatically
     * @param context
     * @param title
     * @param message
     * @param status
     */
    public void showAlertDialog(Context context, String title, String message, Boolean status) {
        AlertDialog alertDialog = new AlertDialog.Builder(context).create();

        // Setting Dialog Title
        alertDialog.setTitle(title);

        // Setting Dialog Message
        alertDialog.setMessage(message);

        // Setting alert dialog icon
        alertDialog.setIcon((status) ? R.drawable.icsuccess : R.drawable.icfail);

        // Setting OK Button
        alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "Retry", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                WifiManager wifiManager = (WifiManager) MainActivity.this.getSystemService(Context.WIFI_SERVICE);
                wifiManager.setWifiEnabled(true);
                synchronized (this) {
                    try {
                        this.wait(WAITING_TIME);
                    } catch (InterruptedException e) {
                        Log.e("Interrrupted Exception", "While Waiting");
                        e.printStackTrace();
                    }
                }
                tryFetchingData();
            }
        });

        // Showing Alert Message
        alertDialog.show();
    }

}