package com.hankarun.hankarun.mylibrary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

public class TellAJokeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);

        Intent intent = getIntent();

        TextView t = (TextView) findViewById(R.id.textview);

        try {
            JSONObject k = new JSONObject(intent.getStringExtra("joke"));
            JSONObject e = k.getJSONObject("value");
            t.setText(e.getString("joke"));
        } catch (JSONException e) {
            e.printStackTrace();
        }



    }

}
