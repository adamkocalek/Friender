package com.example.friender.fiender;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class OrdersActivity extends AppCompatActivity {

    TextView tvAboutText, tvAboutUsText;

    ListView lv;
    Context context;

    public static ArrayList<String> nameList = new ArrayList<>();
    public static ArrayList<String> ocenaList = new ArrayList<>();
    public static ArrayList<String> wiekList = new ArrayList<>();
    public static ArrayList<Integer> images = new ArrayList<>();
    public static ArrayList<String> ids = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_orders);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        context = this;

        // -------------------------------------------------- Pobieranie przyjaciół i ustawiwanie LV -----------------------------------------------------
        nameList.clear();
        ocenaList.clear();
        wiekList.clear();
        images.clear();
        ids.clear();

        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute("getFriends");

        try {
            Toast.makeText(getApplicationContext(), "Pobieram dane...", Toast.LENGTH_SHORT).show();
            Thread.sleep(1000);
        } catch (InterruptedException e) {

        }

        try {
            JSONArray JSON_A = new JSONArray(backgroundWorker.tempJSON);
            JSONObject JSON_O = null;

            SharedPreferences myprefs = context.getSharedPreferences("user", context.MODE_PRIVATE);
            String userID = myprefs.getString("id", null);

            for (int i = 0; i < JSON_A.length(); i++) {
                JSON_O = JSON_A.getJSONObject(i);

                String id_user = JSON_O.getString("id_user");

                if (id_user.equals(userID)) {
                    // Odbieranie danych
                    String id = JSON_O.getString("id");
                    String name = JSON_O.getString("name");
                    String age = JSON_O.getString("age");
                    String hobby = JSON_O.getString("hobby");
                    String sex = JSON_O.getString("sex");
                    String picture = JSON_O.getString("pictures");
                    String login = JSON_O.getString("login");
                    String password = JSON_O.getString("password");
                    String city = JSON_O.getString("city");
                    String reservation = JSON_O.getString("reservation");
                    //String id_user = JSON_O.getString("id_user");
                    String rank = JSON_O.getString("rank");
                    String free = JSON_O.getString("available");

                    // Dodanie danych do list
                    nameList.add(name);
                    ocenaList.add("Ocena: " + rank);
                    wiekList.add("Wiek: " + age);
                    if (name.equals("Szymon Witkowski")) {
                        images.add(R.drawable.szymon);
                    } else if (name.equals("Adam Kocalek")) {
                        images.add(R.drawable.adam);
                    } else if (name.equals("Patrycjusz Zajac")) {
                        images.add(R.drawable.patrycjusz);
                    } else if (name.equals("Marek Czatka")) {
                        images.add(R.drawable.marek);
                    }
                    ids.add(id);
                }
            }
        } catch (JSONException e) {
            Log.d("LOG Parser", e + "");
        }

        lv = (ListView) findViewById(R.id.listView);
        lv.setAdapter(new CustomAdapter(this, nameList, images, ocenaList, wiekList, ids));

        // ----------------------------------------------------------------------------------------------------------------------------------------------
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            super.onBackPressed();
            overridePendingTransition(R.anim.in_from_left, R.anim.out_to_right);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.in_from_left, R.anim.out_to_right);
    }

}
