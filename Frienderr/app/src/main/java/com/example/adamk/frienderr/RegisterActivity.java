package com.example.adamk.frienderr;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ContextThemeWrapper;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class RegisterActivity extends AppCompatActivity {
    EditText etRegisterAge, etRegisterName, etRegisterUsername, etRegisterPassword, etRegisterSurname;
    TextView tvRegisterRules;
    String loginsDownloaded = null, loginsUptaded = null;
//    private Validator validator = null;


    @Override
    public void onBackPressed() {
        super.onBackPressed();
//        overridePendingTransition(R.anim.in_from_left, R.anim.out_to_right);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
//        overridePendingTransition(R.anim.right_in, R.anim.left_out);

        etRegisterAge = (EditText) findViewById(R.id.etRegisterAge);
        etRegisterName = (EditText) findViewById(R.id.etRegisterName);
        etRegisterUsername = (EditText) findViewById(R.id.etRegisterUsername);
        etRegisterPassword = (EditText) findViewById(R.id.etRegisterPassword);
        etRegisterSurname = (EditText) findViewById(R.id.etRegisterSurname);
        tvRegisterRules = (TextView) findViewById(R.id.tvRegisterRules);

//        validator = new Validator();

        // CZĘŚĆ PATRYKA
//        if (isOnline()) {
//            // POBIERANIE LOGINÓW
//            BackgroundWorker backgroundWorker = new BackgroundWorker(this);
//            backgroundWorker.execute("getLogins");
//        } else {
//            Toast.makeText(getApplicationContext(), "Brak połączenia z internetem.", Toast.LENGTH_SHORT).show();
//            onBackPressed();
//        }

        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute("getLogins");

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

        String str_name = etRegisterName.getText().toString();
        String str_surname = etRegisterSurname.getText().toString();
        String str_age = etRegisterAge.getText().toString();
        String str_username = etRegisterUsername.getText().toString();
        String str_password = etRegisterPassword.getText().toString();


    }

    public boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        android.net.NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }
}