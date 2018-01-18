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

import static com.example.friender.fiender.R.id.editTextEmail;
import static com.example.friender.fiender.R.id.editTextTelephone;

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
        etRegisterTelephone = (EditText) findViewById(editTextTelephone);
        etRegisterEmail = (EditText) findViewById(editTextEmail);
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
        String type = "register";
        String name = etRegisterName.getText().toString();
        String surname = etRegisterSurname.getText().toString();
        String age = etRegisterAge.getText().toString();
        String login = etRegisterUsername.getText().toString();
        String password = etRegisterPassword.getText().toString();
        String phone = etRegisterTelephone.getText().toString();
        String email = etRegisterEmail.getText().toString();

//        if (!validator.isEmpty(name) && !validator.isEmpty(surname) && !validator.isEmpty(age) && !validator.isEmpty(login) && !validator.isEmpty(password) && !validator.isEmpty(phone) && !validator.isEmpty(email)) {
//            if (validator.passwordValidator(etRegisterPassword.getText() + "")) {
//                if (validator.emailValidator(etRegisterEmail.getText().toString() + "")) {
//                    if (validator.phoneNumberValidator(etRegisterTelephone.getText() + "")) {

        BackgroundWorker backgroundWorker = new BackgroundWorker(RegisterActivity.this);
//                        backgroundWorker.execute(type, validator.trimSpaces(name), validator.trimSpaces(surname), validator.trimSpaces(age), validator.trimSpaces(login), validator.trimSpaces(password), validator.trimSpaces(phone), validator.trimSpaces(email));
        backgroundWorker.execute(type, name, surname, age, login, password, phone, email);
        Intent myIntent = new Intent(RegisterActivity.this, LoginActivity.class);
        RegisterActivity.this.startActivity(myIntent);
//                        int i = 0;
//                        Scanner scanner = new Scanner(loginsDownloaded);
//                        while (scanner.hasNextLine()) {
//                            String line = scanner.nextLine();
//                            if (line.equals(str_username)) {
//                                i++;
//                            }
//                        }
//                        scanner.close();

//                        if (i == 0) {
//                            loginsUptaded = loginsDownloaded + "\n" + str_username;
//
//                            // UPLOAD LOGINÓW
//                            BackgroundWorker backgroundWorker1 = new BackgroundWorker(this);
//                            backgroundWorker1.execute("updateLogins", loginsUptaded);
//
//                            BackgroundWorker backgroundWorker2 = new BackgroundWorker(this);
//                            backgroundWorker2.execute("register", validator.trimSpaces(str_name), validator.trimSpaces(str_surname), validator.trimSpaces(str_age), validator.trimSpaces(str_username), validator.trimSpaces(str_password), validator.trimSpaces(str_telephone), validator.trimSpaces(str_email));
//
//                            Intent myIntent = new Intent(RegisterActivity.this, LoginActivity.class);
//                            RegisterActivity.this.startActivity(myIntent);
//
//
//                        } else {
//                            etRegisterUsername.setError("Wybrany login już istnieje.");
//                            Toast.makeText(getApplicationContext(), "Niepoprawnie wypełnione pola.", Toast.LENGTH_SHORT).show();
//                        }
//                    } else {
//                        etRegisterTelephone.setError("Numer telefonu musi być 9 cyfrowy (XXX-XXX-XXX).");
//                        Toast.makeText(getApplicationContext(), "Niepoprawnie wypełnione pola.", Toast.LENGTH_SHORT).show();
//                    }
//                } else {
//                    etRegisterEmail.setError("Niepoprawny adres email.");
//                    Toast.makeText(getApplicationContext(), "Niepoprawnie wypełnione pola.", Toast.LENGTH_SHORT).show();
//                }
//            } else {
//                etRegisterPassword.setError("Hasło musi mieć długość od 6 do 20 znaków i zawierać przynajmniej jedną cyfrę.");
//                Toast.makeText(getApplicationContext(), "Niepoprawnie wypełnione pola.", Toast.LENGTH_SHORT).show();
//            }
//        } else {
//            if (TextUtils.isEmpty(validator.trimSpaces(name))) {
//                etRegisterName.setError("Pole nie może być puste!");
//            }
//            if (TextUtils.isEmpty(validator.trimSpaces(surname))) {
//                etRegisterSurname.setError("Pole nie może być puste!");
//            }
//            if (TextUtils.isEmpty(validator.trimSpaces(age))) {
//                etRegisterAge.setError("Pole nie może być puste!");
//            }
//            if (TextUtils.isEmpty(validator.trimSpaces(login))) {
//                etRegisterUsername.setError("Pole nie może być puste!");
//            }
//            if (TextUtils.isEmpty(validator.trimSpaces(password))) {
//                etRegisterPassword.setError("Pole nie może być puste!");
//            }
//            if (TextUtils.isEmpty(validator.trimSpaces(phone))) {
//                etRegisterTelephone.setError("Pole nie może być puste!");
//            }
//            if (TextUtils.isEmpty(validator.trimSpaces(email))) {
//                etRegisterEmail.setError("Pole nie może być puste!");
//            }
//
//            Toast.makeText(getApplicationContext(), "Wszystkie pola muszą zostać uzupełnione.", Toast.LENGTH_SHORT).show();
//
//
//        }
    }

    public boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        android.net.NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }
}