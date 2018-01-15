package com.example.friender.fiender;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
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
    private ProgressDialog progressDialog;

    BackgroundWorker(Context context) {
        this.context = context;
    }

    BackgroundWorker(Context context, Activity activity) {
        this.context = context;
        this.activity = activity;
    }

    String tempJSON;
    String registerAsUser_url = "https://serwer1743778.home.pl/insertUser.php";
    String loginAsUser_url = "https://serwer1743778.home.pl/loginAsUser.php";
    String friends_url = "https://serwer1743778.home.pl/getFriends.php";

    @Override
    protected String doInBackground(String... params) {
        String type = params[0];
        String NetworkException = "Błąd połączenia z internetem";
        String IOException = "Nieoczekiwany błąd, IOException";
        String TAG = "LOG";

        switch (type) {
            case "loginAsUser_url":
                try {
                    String login = params[1];
                    String password = params[2];
                    URL url = new URL(loginAsUser_url);
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
                    System.out.println("XXXXXXXXX" + result);
                    result = "1";
                    return result;

                } catch (MalformedURLException e) {
                    Log.d(TAG, NetworkException);
                } catch (IOException e) {
                    Log.d(TAG, IOException);
                }
                break;
            case "registerAsUser_url":
                try {
                    String name = params[1];
                    String surname = params[2];
                    String age = params[3];
                    String usename = params[4];
                    String password = params[5];
                    String phone = params[6];
                    String email = params[7];
                    URL url = new URL(registerAsUser_url);
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
                    result = "4";
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
                    result = "5";
                    return result;

                } catch (MalformedURLException e) {
                    Log.d(TAG, NetworkException);
                } catch (IOException e) {
                    Log.d(TAG, IOException);
                }
                break;
            default:
                return null;
        }
        return null;
    }
    @Override
    protected void onPostExecute(String result) {

        switch (result) {
            case "1":
                Intent intent = new Intent(context, FriendsDiary.class);
                context.startActivity(intent);
                Toast.makeText(context, "Zalogowano poprawnie.", Toast.LENGTH_SHORT).show();

                // ----------------------- BackgroundWorker Dane Użytkownika ----------------------------
//                SharedPreferences myprefs = context.getSharedPreferences("user", context.MODE_PRIVATE);
//                String username = myprefs.getString("username", null);
//                String type = "getUser";
//
//                BackgroundWorker backgroundWorker = new BackgroundWorker(context);
//                backgroundWorker.execute(type, username);
                // --------------------------------------------------------------------------------------
                break;
            case "4":
                Toast.makeText(context, "Zarejestrowano.", Toast.LENGTH_SHORT).show();
                intent = new Intent(context, LoginActivity.class);
                context.startActivity(intent);
                break;
            case "5":
                break;
//            case "User":
//                JSONArray ja = null;
//
//                try {
//                    ja = new JSONArray(tempJSON);
//                    JSONObject jo1 = null;
//                    for (int i = 0; i < ja.length(); i++) {
//                        SharedPreferences sharedPreferences = context.getSharedPreferences("user", context.MODE_PRIVATE);
//
//                        jo1 = ja.getJSONObject(i);
//                        String name = jo1.getString("name");
//                        String surname = jo1.getString("surname");
//                        String age = jo1.getString("age");
//                        String password = jo1.getString("password");
//                        String author_key = jo1.getString("id");
//                        String telephone = jo1.getString("telephone");
//                        String email = jo1.getString("email");
//
//                        sharedPreferences.edit().putString("name", name).apply();
//                        sharedPreferences.edit().putString("surname", surname).apply();
//                        sharedPreferences.edit().putString("age", age).apply();
//                        sharedPreferences.edit().putString("password", password).apply();
//                        sharedPreferences.edit().putString("author_key", author_key).apply();
//                        sharedPreferences.edit().putString("telephone", telephone).apply();
//                        sharedPreferences.edit().putString("email", email).apply();
//                    }
//
//                } catch (JSONException e) {
//                    Log.d("RootActivity", e + "");
//                }
//                break;
//
//            case "Logins":
//                ((RegisterActivity) context).loginsDownloaded = outputString;
//                break;
//
//            case "Image":
//                switch (outputString) {
//                    case "ProjectActivity":
//                        ((ProjectActivity) context).imageViewPicture.setImageBitmap(outputBitmap);
//                        break;
//
//                    case "UserProjectActivity":
//                        ((UserProjectActivity) context).imageViewPicture.setImageBitmap(outputBitmap);
//                        break;
//
//                    default:
//                }
//                break;
//
//            case "ImageNudity":
//                String api_user = "63501098", api_secret = "pwQhu5WbwEHUqc2S";
//                String API_URL = "https://api.sightengine.com/1.0/nudity.json?api_user=" + api_user + "&api_secret=" + api_secret + "&url=";
//
//                AsyncHttpClient client = new AsyncHttpClient();
//                String API_URL_COMPLETE = API_URL + "http://188.128.220.60/CheckImageNudity.jpg";
//                client.get(API_URL_COMPLETE, new AsyncHttpResponseHandler() {
//                    @Override
//                    public void onSuccess(String response) {
//                        Log.i("Połączenie nawiązane", "HTTP Sucess");
//                        ((AddProjectActivity) context).nudityResponse = response;
//                    }
//                });
//                break;
//
//            case "MyProjects":
//                //((UserProjectsActivity) context).progressBar.setVisibility(View.GONE);
//                ListViewAdapterUser lviewAdapter;
//                lviewAdapter = new ListViewAdapterUser(activity, ids, myProjects, likes, dates, imagesBitmaps);
//                ((UserProjectsActivity) context).listViewMyProjects.setAdapter(lviewAdapter);
//
//                ((UserProjectsActivity) context).listViewMyProjects.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                    @Override
//                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                        TextView textView = (TextView) view.findViewById(R.id.textViewIds);
//                        if (ids.contains(textView.getText() + "")) {
//                            position = ids.indexOf(textView.getText() + "");
//                        } else {
//                            position = -1;
//                        }
//                        if (position != -1) {
//                            Intent intent = new Intent(activity, UserProjectActivity.class);
//                            intent.putExtra("subject", parser.subjects.get(indexs.get(position)));
//                            intent.putExtra("description", parser.descriptions.get(indexs.get(position)));
//                            intent.putExtra("location", parser.locations.get(indexs.get(position)));
//                            intent.putExtra("date", parser.dates.get(indexs.get(position)));
//                            intent.putExtra("image", parser.images.get(indexs.get(position)));
//                            intent.putExtra("id", parser.ids.get(indexs.get(position)));
//                            intent.putExtra("likes", parser.likes.get(indexs.get(position)));
//                            intent.putExtra("likesids", parser.likesids.get(indexs.get(position)));
//                            intent.putExtra("author", parser.authors.get(indexs.get(position)));
//                            intent.putExtra("author_key", parser.authors_keys.get(indexs.get(position)));
//                            intent.putExtra("likesnames", parser.likesNames.get(indexs.get(position)));
//                            intent.putExtra("comments", parser.comments.get(indexs.get(position)));
//                            activity.startActivity(intent);
//                        }
//                    }
//                });
//                break;
            case "":

                break;

            default:
                result = result.replaceAll("<", "");
                Toast.makeText(context, result, Toast.LENGTH_SHORT).show();
        }

    }
}