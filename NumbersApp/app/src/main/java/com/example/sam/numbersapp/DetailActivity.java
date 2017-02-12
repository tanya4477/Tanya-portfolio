package com.example.sam.numbersapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * This is the Activity class for detail view
 */
public class DetailActivity extends FragmentActivity {
    TextView tvName, tvText;
    ImageView ivImage;
    private static final String JSON_ROOT = "DetailMap";
    private final String DETAIL_URL = "http://dev.tapptic.com/test/json.php?name=";
    String str_url = "";
    String detailUrlContent = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent= getIntent();
        String name = intent.getStringExtra("key_name");

        tvName = (TextView)findViewById(R.id.tvName);
        tvText = (TextView)findViewById(R.id.tvText);
        ivImage = (ImageView)findViewById(R.id.ivImage);

        str_url= DETAIL_URL.concat(name);

        final StringBuffer finalBuffer = new StringBuffer("");
        Thread jsonThread = new Thread(new Runnable() {
            @Override
            public void run() {
                detailUrlContent = UtilityClass.extractJSONFromURL(str_url, JSON_ROOT);
                finalBuffer.append(detailUrlContent);
            }
        });
        jsonThread.start();

        try {
            jsonThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        fillWithData(finalBuffer.toString());
    }

    private void fillWithData(String strJson) {
        try {
            //Get the instance of JSONObject
            JSONObject detail=(new JSONObject(strJson)).getJSONObject(JSON_ROOT);
            String name = detail.getString("name");
            String text = detail.getString("text");
            String image = detail.getString("image");

            tvName.setText("Name : " + name);
            tvText.setText("Text : " + text);
            Picasso.with(this)
                    .load(image)
                    .placeholder(R.drawable.placeholder)
                    .error(R.drawable.icerror)
                    .into(ivImage);

        } catch (JSONException e) {
            Log.e("JSON Exception", "-----------------");
            e.printStackTrace();
        }
    }
}
