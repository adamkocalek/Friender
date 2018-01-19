package com.example.friender.fiender;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class BackgroundWorker extends AsyncTask<String, Void, String> {

    private Context context;
    private Activity activity;

    BackgroundWorker(Context context) {
        this.context = context;
    }

    BackgroundWorker(Context context, Activity activity) {
        this.context = context;
        this.activity = activity;
    }

    String tempJSON;

    private Parser parser = new Parser();

    @Override
    protected String doInBackground(String... params) {
        String type = params[0];
        String login_url = "https://serwer1743778.home.pl/loginAsUser.php";
        String register_url = "https://serwer1743778.home.pl/insertUser.php";
        String update_url = "";
        String friends_url = "https://serwer1743778.home.pl/getFriends.php";

        String NetworkException = "Błąd połączenia z internetem";
        String IOException = "Nieoczekiwany błąd, IOException";
        String TAG = "LOG";

        switch (type) {
            case "login":
                try {
                    String login = params[1];
                    String password = params[2];
                    URL url = new URL(login_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    String post_data = URLEncoder.encode("login", "UTF-8") + "=" + URLEncoder.encode(login, "UTF-8") + "&"
                            + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");
                    bufferedWriter.write(post_data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    outputStream.close();
                    InputStream inputStream = httpURLConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));   //było iso-8859-1
                    String result = "";
                    String line = "";

                    while ((line = bufferedReader.readLine()) != null) {
                        result += line;
                    }

                    bufferedReader.close();
                    inputStream.close();
                    httpURLConnection.disconnect();
                    System.out.println(result);
                    return result;

                } catch (MalformedURLException e) {
                    Log.d(TAG, NetworkException);
                } catch (IOException e) {
                    Log.d(TAG, IOException);
                }
                break;
            case "register":
                try {
                    String name = params[1];
                    String surname = params[2];
                    String age = params[3];
                    String usename = params[4];
                    String password = params[5];
                    String phone = params[6];
                    String telephone = params[6];
                    String email = params[7];
                    URL url = new URL(register_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    String post_data = URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(name, "UTF-8") + "&"
                            + URLEncoder.encode("surname", "UTF-8") + "=" + URLEncoder.encode(surname, "UTF-8") + "&"
                            + URLEncoder.encode("age", "UTF-8") + "=" + URLEncoder.encode(age, "UTF-8") + "&"
                            + URLEncoder.encode("login", "UTF-8") + "=" + URLEncoder.encode(usename, "UTF-8") + "&"
                            + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8") + "&"
                            + URLEncoder.encode("phone", "UTF-8") + "=" + URLEncoder.encode(phone, "UTF-8") + "&"
                            + URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(email, "UTF-8");
                    bufferedWriter.write(post_data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    outputStream.close();
                    InputStream inputStream = httpURLConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));   ///było iso-8859-1
                    String result = "";
                    String line = "";

                    while ((line = bufferedReader.readLine()) != null) {
                        result += line;
                    }

                    bufferedReader.close();
                    inputStream.close();
                    httpURLConnection.disconnect();
                    System.out.println(result);
                    return result;
                } catch (MalformedURLException e) {
                    Log.d(TAG, NetworkException);
                } catch (IOException e) {
                    Log.d(TAG, IOException);
                }
                break;

            case "updateFriend":
                try {
                    String name = params[1];
                    String surname = params[2];
                    String age = params[3];
                    String usename = params[4];
                    String password = params[5];
                    String telephone = params[6];
                    String email = params[7];
                    URL url = new URL(update_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    String post_data = URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(name, "UTF-8") + "&"
                            + URLEncoder.encode("surname", "UTF-8") + "=" + URLEncoder.encode(surname, "UTF-8") + "&"
                            + URLEncoder.encode("age", "UTF-8") + "=" + URLEncoder.encode(age, "UTF-8") + "&"
                            + URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(usename, "UTF-8") + "&"
                            + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8") + "&"
                            + URLEncoder.encode("telephone", "UTF-8") + "=" + URLEncoder.encode(telephone, "UTF-8") + "&"
                            + URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(email, "UTF-8");
                    bufferedWriter.write(post_data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    outputStream.close();
                    InputStream inputStream = httpURLConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));   //było iso-8859-1
                    String result = "";
                    String line = "";

                    while ((line = bufferedReader.readLine()) != null) {
                        result += line;
                    }

                    bufferedReader.close();
                    inputStream.close();
                    httpURLConnection.disconnect();
                    System.out.println(result);
                    return result;

                } catch (MalformedURLException e) {
                    Log.d(TAG, NetworkException);
                } catch (IOException e) {
                    Log.d(TAG, IOException);
                }
                break;
            case "getFriends":
                try {
                    URL url = new URL(friends_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    String post_data = URLEncoder.encode("type", "UTF-8") + "=" + URLEncoder.encode(type, "UTF-8");
                    bufferedWriter.write(post_data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    outputStream.close();
                    InputStream inputStream = httpURLConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));   //było iso-8859-1
                    String result = "";
                    String line = "";
                    while ((line = bufferedReader.readLine()) != null) {
                        result += line;
                    }
                    bufferedReader.close();
                    inputStream.close();
                    httpURLConnection.disconnect();
                    System.out.println(result);
                    tempJSON = result;
                    return result;

                } catch (MalformedURLException e) {
                    Log.d(TAG, NetworkException);
                } catch (IOException e) {
                    Log.d(TAG, IOException);
                }
                break;

            case "getMyProjects":

                return "MyProjects";

            default:
        }
        return null;
    }

    @Override
    protected void onPreExecute() {

    }

    @Override
    protected void onPostExecute(String result) {
        if (result.contains("success")) {
            Intent intent = new Intent(context, FriendsDiary.class);
            context.startActivity(intent);
            Toast.makeText(context, "Zalogowano poprawnie.", Toast.LENGTH_SHORT).show();
        } else if (result.contains("failed")) {
            Toast.makeText(context, "Niepoprawne dane logowania.", Toast.LENGTH_SHORT).show();
        } else if (result.contains("sert")) {
            Intent intent = new Intent(context, LoginActivity.class);
            context.startActivity(intent);
            Toast.makeText(context, "Zarejestrowano.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}