package com.example.friender.fiender;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class UserAreaActivity extends AppCompatActivity {

    Button buttonEdit;
    TextView textViewUsername, tvDeleteUser;
    EditText editTextName, editTextSurname, editTextUsername, editTextPassword, editTextAge, editTextTelephone, editTextEmail;
    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_area);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_user_area);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        init();

        SharedPreferences myprefs = getSharedPreferences("user", MODE_PRIVATE);
        String username = myprefs.getString("username", null);
        String name = myprefs.getString("name", null);
        String surname = myprefs.getString("surname", null);
        String age = myprefs.getString("age", null);
        String password = myprefs.getString("password", null);
        String telephone = myprefs.getString("telephone", null);
        String email = myprefs.getString("email", null);
        userId = myprefs.getString("author_key", null);

        editTextUsername.setText(username);
        editTextAge.setText(age);
        editTextName.setText(name);
        editTextSurname.setText(surname);
        editTextPassword.setText(password);
        textViewUsername.setText(username);
        editTextTelephone.setText(telephone);
        editTextEmail.setText(email);
    }

    public void init() {
        buttonEdit = (Button) findViewById(R.id.buttonEdit);
        tvDeleteUser = (TextView) findViewById(R.id.tvDeleteUser);
        textViewUsername = (TextView) findViewById(R.id.textViewUsername);
        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextSurname = (EditText) findViewById(R.id.editTextSurname);
        editTextUsername = (EditText) findViewById(R.id.editTextUsername);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        editTextAge = (EditText) findViewById(R.id.editTextAge);
        editTextTelephone = (EditText) findViewById(R.id.editTextTelephone);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.in_from_left, R.anim.out_to_right);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            super.onBackPressed();
            overridePendingTransition(R.anim.in_from_left, R.anim.out_to_right);
        }
        return super.onOptionsItemSelected(item);
    }

}
