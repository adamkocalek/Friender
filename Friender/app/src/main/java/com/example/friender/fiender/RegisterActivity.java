package com.example.friender.fiender;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ContextThemeWrapper;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Scanner;

public class RegisterActivity extends AppCompatActivity {
    EditText etRegisterAge, etRegisterName, etRegisterUsername, etRegisterPassword, etRegisterSurname, etRegisterTelephone, etRegisterEmail;
    TextView tvRegisterRules;
    String loginsDownloaded = null, loginsUptaded = null;
    private Validator validator = null;


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.in_from_left, R.anim.out_to_right);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        overridePendingTransition(R.anim.right_in, R.anim.left_out);

        etRegisterAge = (EditText) findViewById(R.id.etRegisterAge);
        etRegisterName = (EditText) findViewById(R.id.etRegisterName);
        etRegisterUsername = (EditText) findViewById(R.id.etRegisterUsername);
        etRegisterPassword = (EditText) findViewById(R.id.etRegisterPassword);
        etRegisterSurname = (EditText) findViewById(R.id.etRegisterSurname);
        etRegisterTelephone = (EditText) findViewById(R.id.editTextTelephone);
        etRegisterEmail =  (EditText) findViewById(R.id.editTextEmail);
        tvRegisterRules = (TextView) findViewById(R.id.tvRegisterRules);


        tvRegisterRules.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent rulesIntent = new Intent(RegisterActivity.this, RulesActivity.class);
//                RegisterActivity.this.startActivity(rulesIntent);
            }
        });
    }

    public void onRegisterButtonClick(View view) {

        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(new ContextThemeWrapper(RegisterActivity.this, R.style.AppTheme))
                .setTitle("Wystąpił błąd!")
                .setMessage("Problem z dostępem do internetu. Sprawdź połączenie i spróbuj ponownie później.")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

        boolean networkCheck = isOnline();
        if (!networkCheck) {
            Log.d("LOG", "Błąd połączenia z internetem.");
            alertDialog.show();
            return;
        }
        String type = "registerAsUser_url";
        String name = etRegisterName.getText().toString();
        String surname = etRegisterSurname.getText().toString();
        String age = etRegisterAge.getText().toString();
        String login = etRegisterUsername.getText().toString();
        String password = etRegisterPassword.getText().toString();

        String str_name = etRegisterName.getText().toString();
        String str_surname = etRegisterSurname.getText().toString();
        String str_age = etRegisterAge.getText().toString();
        String str_username = etRegisterUsername.getText().toString();
        String str_password = etRegisterPassword.getText().toString();
        String str_email = etRegisterEmail.getText().toString();
        String str_telephone = etRegisterTelephone.getText().toString();

        BackgroundWorker backgroundWorker = new BackgroundWorker(RegisterActivity.this);
        backgroundWorker.execute(type, name, surname, age, login, password);

            if (TextUtils.isEmpty(validator.trimSpaces(str_name))) {
                etRegisterName.setError("Pole nie może być puste!");
            }
            if (TextUtils.isEmpty(validator.trimSpaces(str_surname))) {
                etRegisterSurname.setError("Pole nie może być puste!");
            }
            if (TextUtils.isEmpty(validator.trimSpaces(str_age))) {
                etRegisterAge.setError("Pole nie może być puste!");
            }
            if (TextUtils.isEmpty(validator.trimSpaces(str_username))) {
                etRegisterUsername.setError("Pole nie może być puste!");
            }
            if (TextUtils.isEmpty(validator.trimSpaces(str_password))) {
                etRegisterPassword.setError("Pole nie może być puste!");
            }
            if (TextUtils.isEmpty(validator.trimSpaces(str_telephone))) {
                etRegisterTelephone.setError("Pole nie może być puste!");
            }
            if (TextUtils.isEmpty(validator.trimSpaces(str_email))) {
                etRegisterEmail.setError("Pole nie może być puste!");
            }

            Toast.makeText(getApplicationContext(), "Wszystkie pola muszą zostać uzupełnione.", Toast.LENGTH_SHORT).show();
        }

    public boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        android.net.NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }
}