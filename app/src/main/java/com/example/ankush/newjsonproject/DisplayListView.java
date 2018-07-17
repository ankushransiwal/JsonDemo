package com.example.ankush.newjsonproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DisplayListView extends AppCompatActivity {

    String JSONString;
    JSONArray jsonArray;
    JSONObject jsonObject;
    ContactAdapter contactAdapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_list_view);

        contactAdapter = new ContactAdapter(this,R.layout.row_layout);
        listView = findViewById(R.id.listView);
        listView.setAdapter(contactAdapter);

        JSONString = getIntent().getStringExtra("jsonData");
        String name,password,contact,country;

        try {
            jsonObject = new JSONObject(JSONString);
            int count = 0;
            jsonArray=jsonObject.getJSONArray("server_response");
            while(count<jsonArray.length()){

                JSONObject JO = jsonArray.getJSONObject(count);
                name = JO.getString("name");
                password = JO.getString("password");
                contact = JO.getString("contact");
                country = JO.getString("country");

                Contacts contacts = new Contacts(name,password,contact,country);
                count++;
                contactAdapter.add(contacts);

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
}
