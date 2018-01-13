package com.example.friender.fiender;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ContextThemeWrapper;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class FriendsDiary extends AppCompatActivity {

    com.example.friender.fiender.Parser parser;
    ListView listMain;
    SwipeRefreshLayout swipeRefreshLayout;
    DrawerLayout mDrawerLayout;
    NavigationView mNavigationView;
    public Toolbar toolbar;
    String TAG = "LOG RootActivity";

    ListView lv;
    Context context;

    ArrayList prgmName;
    public static int [] images={R.drawable.szymon,R.drawable.adam,R.drawable.patrycjusz,R.drawable.marek};
    public static String [] nameList={"Szymon Witkowski","Adam Kocałek","Patrycjusz Zając","Marek Czatka"};
    public static String [] ocenaList={"Ocena: 5,78","Ocena: 6,00","Ocena: 5,11","Ocena: 3,29"};
    public static String [] wiekList={"Wiek: 21","Wiek: 28","Wiek: 22","Wiek: 42"};

    @Override
    public void onBackPressed() {
        return;
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        //Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends_diary);
        overridePendingTransition(R.anim.right_in, R.anim.left_out);

        context=this;

        lv=(ListView) findViewById(R.id.listView);
        lv.setAdapter(new CustomAdapter(this, nameList,images,ocenaList,wiekList));

        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute("getFriends");

        try {
            Toast.makeText(getApplicationContext(), "Pobieram dane...", Toast.LENGTH_SHORT).show();
            Thread.sleep(1000);
        } catch (InterruptedException e) {

        }

        parser = new com.example.friender.fiender.Parser(getApplicationContext(), backgroundWorker.tempJSON);
        parser.parse();

        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(new ContextThemeWrapper(FriendsDiary.this, R.style.Dialog_Theme))
                .setTitle("Wystąpił błąd!")
                .setMessage("Problem z dostępem do internetu. Sprawdź połączenie i spróbuj ponownie później.")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout1);
        mNavigationView = (NavigationView) findViewById(R.id.shitstuff1);

        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                mDrawerLayout.closeDrawers();

                if (menuItem.getItemId() == R.id.nav_main) {
                    boolean networkCheck = isOnline();
                    if (!networkCheck) {
                        Log.d(TAG, "Błąd połączenia z internetem.");
                        alertDialog.show();
                        return false;
                    }

                    Intent intent = new Intent(FriendsDiary.this, FriendsDiary.class);
                    startActivity(intent);
                }

                if (menuItem.getItemId() == R.id.nav_idea) {
                    boolean networkCheck = isOnline();
                    if (!networkCheck) {
                        Log.d(TAG, "Błąd połączenia z internetem.");
                        alertDialog.show();
                        return false;
                    }
                    //Intent intent = new Intent(FriendsDiary.this, AddProjectActivity.class);
                    //startActivity(intent);

                } else if (menuItem.getItemId() == R.id.nav_my_account) {
                    boolean networkCheck = isOnline();
                    if (!networkCheck) {
                        Log.d(TAG, "Błąd połączenia z internetem.");
                        alertDialog.show();
                        return false;
                    }
                    //Intent intent = new Intent(FriendsDiary.this, UserAreaActivity.class);
                    //startActivity(intent);

                } else if (menuItem.getItemId() == R.id.nav_my_projects) {
                    boolean networkCheck = isOnline();
                    if (!networkCheck) {
                        Log.d(TAG, "Błąd połączenia z internetem.");
                        alertDialog.show();
                        return false;
                    }
                    //Intent intent = new Intent(RootActivity.this, UserProjectsActivity.class);
                    //startActivity(intent);

                } else if (menuItem.getItemId() == R.id.nav_rules) {
                    //Intent intent = new Intent(RootActivity.this, RulesActivity.class);
                    //startActivity(intent);

                } else if (menuItem.getItemId() == R.id.nav_help) {
                    //Intent intent = new Intent(RootActivity.this, HelpActivity.class);
                    //startActivity(intent);

                } else if (menuItem.getItemId() == R.id.nav_about) {
                    //Intent intent = new Intent(RootActivity.this, AboutActivity.class);
                    //startActivity(intent);

                } else if (menuItem.getItemId() == R.id.nav_logout) {
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(), "Wylogowano poprawnie.", Toast.LENGTH_SHORT).show();
                }

                return false;
            }

        });

        toolbar = (Toolbar) findViewById(R.id.toolbar1);
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.app_name, R.string.app_name);

        mDrawerLayout.setDrawerListener(mDrawerToggle);

        mDrawerToggle.syncState();


 //       ListViewAdapter lviewAdapter;
//        lviewAdapter = new ListViewAdapter(this, parser.ids, parser.names, parser.ages, parser.hobbies, parser.sexs, parser.pictures, parser.logins, parser.passwords, parser.cities, parser.reservations, parser.id_users, parser.ranks);


//        listView_new.setAdapter(lviewAdapter);

//        listView_new.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//                Intent intent = new Intent(getContext(), ProjectActivity.class);
//                TextView textView = (TextView) view.findViewById(R.id.textViewIds);
//
//                if (parser.ids.contains(textView.getText() + "")) {
//                    position = parser.ids.indexOf(textView.getText() + "");
//                } else {
//                    position = -1;
//                }
//                if (position != -1) {
//                    intent.putExtra("id", parser.ids.get(position));
//                    intent.putExtra("car", parser.cars.get(position));
//                    intent.putExtra("date", parser.dates.get(position));
//                    intent.putExtra("average", parser.averages.get(position));
//                    intent.putExtra("fuel", parser.fuels.get(position));
//                    intent.putExtra("kilometers", parser.kilometerss.get(position));
//                    getContext().startActivity(intent);
//                }
//            }
//        });


    }

    public boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        android.net.NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }
}
