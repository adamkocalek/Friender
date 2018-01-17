package com.example.friender.fiender;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Parser extends AsyncTask<Void, Integer, Integer> {

    Context context;
    public static String data;
    String taskName;
    public static ArrayList<String> ids = new ArrayList<>(), names = new ArrayList<>(), ages = new ArrayList<>(), hobbies = new ArrayList<>(), sexs = new ArrayList<>(), pictures = new ArrayList<>(), logins = new ArrayList<>(), passwords = new ArrayList<>(), cities = new ArrayList<>(), reservations = new ArrayList<>(), id_users = new ArrayList<>(), ranks = new ArrayList<>(), frees = new ArrayList<>();

    ProgressDialog progressDialog;

    public Parser() {

    }

    public Parser(Context context, String data) {
        this.context = context;
        this.data = data;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Przetwarzanie danych");
        progressDialog.show();
    }

    @Override
    protected Integer doInBackground(Void... params) {
        return this.parse();
    }

    @Override
    protected void onPostExecute(Integer integer) {
        super.onPostExecute(integer);
        if (integer == 1) {
            if (taskName != null) {
                switch (taskName) {
                    case "RootActivityIntent":
                        Intent intent = new Intent(context, MainActivity.class);
                        context.startActivity(intent);
                        break;

                    default:
                }
            }
        } else {
            Toast.makeText(context, "Nie można przetworzyć danych.", Toast.LENGTH_SHORT).show();
        }
        progressDialog.dismiss();
    }

    // Parsowanie pobranego JSON Array
    int parse() {
        try {
            // Dodanie danych do JSON Array
            JSONArray ja = new JSONArray(data);

            // Tworzenie pojedynczego JSON Object to przechowywania pojedynczego elementu
            JSONObject jo = null;
            names.clear();
            ages.clear();
            hobbies.clear();
            sexs.clear();
            pictures.clear();
            logins.clear();
            passwords.clear();
            cities.clear();
            reservations.clear();
            id_users.clear();
            ranks.clear();
            frees.clear();


            for (int i = ja.length() - 1; i > -1; i--) {
                jo = ja.getJSONObject(i);

                // Odbieranie danych
                String id = jo.getString("id");
                String name = jo.getString("name");
                String age = jo.getString("age");
                String hobby = jo.getString("hobby");
                String sex = jo.getString("sex");
                String picture = jo.getString("pictures");
                String login = jo.getString("login");
                String password = jo.getString("password");
                String city = jo.getString("city");
                String reservation = jo.getString("reservation");
                String id_user = jo.getString("id_user");
                String rank = jo.getString("rank");
                String free = jo.getString("available");
                // Dodanie danych do normalnej listy
                ids.add(id);
                names.add(name);
                ages.add(age);
                hobbies.add(hobby);
                sexs.add(sex);
                pictures.add(picture);
                logins.add(login);
                passwords.add(password);
                cities.add(city);
                reservations.add(reservation);
                id_users.add(id_user);
                ranks.add(rank);
                frees.add(free);
            }
            return 1;

        } catch (JSONException e) {
            Log.d("LOG Parser", e + "");
        }
        return 0;
    }
}