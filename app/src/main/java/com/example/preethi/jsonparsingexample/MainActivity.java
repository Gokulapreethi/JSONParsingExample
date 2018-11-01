package com.example.preethi.jsonparsingexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    // ArrayList for person names, email Id's and mobile numbers
    ArrayList<String> personNames = new ArrayList<>();
    ArrayList<String> emailIds = new ArrayList<>();
    ArrayList<String> mobileNumbers = new ArrayList<>();
    Button myclick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        myclick=(Button)findViewById(R.id.myJsonview);
        // set a LinearLayoutManager with default vertical orientation
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        myclick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    recyclerView.setVisibility(View.VISIBLE);
                    // get JSONObject from JSON file
                    JSONObject obj = new JSONObject(loadJSONFromAsset());
                    String country,sunrise,sunset,id="",main = "",description="",icon="",temp,pressure;
                    JSONObject system  = obj.getJSONObject("sys");
                    country=system.getString("country");
                    sunrise=system.getString("sunrise");
                    sunset=system.getString("sunset");
                    JSONArray user=obj.getJSONArray("weather");
                    for (int i = 0; i < user.length(); i++) {
                        JSONObject userDetail  = user.getJSONObject(i);
                        id=userDetail.getString("id");
                        main=userDetail.getString("main");
                        description=userDetail.getString("description");
                        icon=userDetail.getString("icon");
                    }
                    JSONObject value  = obj.getJSONObject("main");

                    temp=value.getString("temp");
                    pressure=value.getString("pressure");
                    Log.i("myvalue123","json parse values are===> ");
                    Log.i("myvalue123","country"+country);
                    Log.i("myvalue123","sunrise"+sunrise);
                    Log.i("myvalue123","id"+id);
                    Log.i("myvalue123","main"+main);
                    Log.i("myvalue123","description"+description);
                    Log.i("myvalue123","icon"+icon);
                    Log.i("myvalue123","temp"+temp);
                    Log.i("myvalue123","pressure"+pressure);
                    Log.i("myvalue123","sunset"+sunset);


                    // fetch JSONArray named users
                    /*JSONArray userArray = obj.getJSONArray("users");
                    // implement for loop for getting users list data
                    for (int i = 0; i < userArray.length(); i++) {
                        // create a JSONObject for fetching single user data
                        JSONObject userDetail = userArray.getJSONObject(i);
                        // fetch email and name and store it in arraylist
                        personNames.add(userDetail.getString("name"));
                        emailIds.add(userDetail.getString("email"));
                        // create a object for getting contact data from JSONObject
                        JSONObject contact = userDetail.getJSONObject("contact");
                        // fetch mobile number and store it in arraylist
                        mobileNumbers.add(contact.getString("mobile"));
                        sqlitedatabase.getDB(MainActivity.this).insertChat_history(userDetail.getString("name"),userDetail.getString("email"),contact.getString("mobile"));
                    }*/
                } catch (JSONException e) {
                    e.printStackTrace();
                }
/*
                String query="select * from eulaagree";
                ArrayList<jsonBean> chatList=sqlitedatabase.getDB(MainActivity.this).getAllRow(query);

                //  call the constructor of CustomAdapter to send the reference and data to Adapter
                CustomAdapter customAdapter = new CustomAdapter(MainActivity.this, chatList);
                recyclerView.setAdapter(customAdapter); // set the Adapter to RecyclerView*/
            }
        });

    }



    private String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getAssets().open("users_list.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}
