package com.example.friender.fiender;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ContextThemeWrapper;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class FriendsDiary extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ListView listMain;
    SwipeRefreshLayout swipeRefreshLayout;
    String TAG = "LOG RootActivity";

    ListView lv;
    Context context;

    public NavigationView mNavigationView;

    public static ArrayList<String> nameList = new ArrayList<>();
    public static ArrayList<String> ocenaList = new ArrayList<>();
    public static ArrayList<String> wiekList = new ArrayList<>();
    public static ArrayList<Integer> images = new ArrayList<>();
    public static ArrayList<String> ids = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends_diary);

        overridePendingTransition(R.anim.right_in, R.anim.left_out);
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

            for (int i = 0; i < JSON_A.length(); i++) {
                JSON_O = JSON_A.getJSONObject(i);

                String available = JSON_O.getString("available");

                if (available.equals("1")) {
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
                    String id_user = JSON_O.getString("id_user");
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
                    }
                    else if (name.equals("Marek Czatka")) {
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

        DrawerLayout mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout1);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar1);
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.app_name, R.string.app_name);
        mNavigationView = (NavigationView) findViewById(R.id.shitstuff1);

        mDrawerLayout.addDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
        mNavigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawerLayout1);
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);
            } else {
                super.onBackPressed();
            }
        }
        return false;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(new ContextThemeWrapper(FriendsDiary.this, R.style.Dialog_Theme))
                .setTitle("Wystąpił błąd!")
                .setMessage("Problem z dostępem do internetu. Sprawdź połączenie i spróbuj ponownie później.")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

        if (id == R.id.nav_main) {
            boolean networkCheck = isOnline();
            if (!networkCheck) {
                Log.d(TAG, "Błąd połączenia z internetem.");
                alertDialog.show();
                return false;
            }

            Intent intent = new Intent(FriendsDiary.this, FriendsDiary.class);
            startActivity(intent);
        }

        if (id == R.id.nav_idea) {
            boolean networkCheck = isOnline();
            if (!networkCheck) {
                Log.d(TAG, "Błąd połączenia z internetem.");
                alertDialog.show();
                return false;
            }
            Intent intent = new Intent(FriendsDiary.this, NewsActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_my_account) {
            boolean networkCheck = isOnline();
            if (!networkCheck) {
                Log.d(TAG, "Błąd połączenia z internetem.");
                alertDialog.show();
                return false;
            }
            Intent intent = new Intent(FriendsDiary.this, UserAreaActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_my_orders) {
            Intent intent = new Intent(FriendsDiary.this, OrdersActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_rules) {
            Intent intent = new Intent(FriendsDiary.this, RegulationsActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_help) {
            Intent intent = new Intent(FriendsDiary.this, HelpActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_about) {
            Intent intent = new Intent(FriendsDiary.this, AboutActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_logout) {
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            Toast.makeText(getApplicationContext(), "Wylogowano poprawnie.", Toast.LENGTH_SHORT).show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawerLayout1);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        android.net.NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }


    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    */
}
