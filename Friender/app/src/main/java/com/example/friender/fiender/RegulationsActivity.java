package com.example.friender.fiender;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class RegulationsActivity extends AppCompatActivity {

    TextView tvAboutText, tvAboutUsText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regulations);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_regulations);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        tvAboutText = (TextView) findViewById(R.id.tvAboutText);
        tvAboutUsText = (TextView) findViewById(R.id.tvAboutUs);

        String textAbout = "Z 20 miliardami przyjaciół na liczniku Friender jest najpopularniejszą aplikacją umożliwiającą zawieranie nowych znajomości. Traktuj nas jak niezawodne wsparcie —gdziekolwiek jesteś, my jesteśmy przy Tobie. Szukasz nowych znajomości? Chcesz poszerzyć swój krąg towarzyski, spotykać się z mieszkańcami miejsc, które odwiedzasz albo po prostu żyć chwilą? Jesteś we właściwym miejscu. Nie bez powodu mówi się o nas \"najgorętsza aplikacja świata\".\n" +
                "\n" +
                "Przeglądaj. Wybieraj. Rozmawiaj. Friender jest łatwy i przyjemny. Bez rozczarowań. Po prostu przeglądasz, wybierasz i zamwiasz przyjaciół. Później chowasz telefon i umawiasz się w realnym świecie, a nowa, wspaniała historia pisze się sama.\n" +
                "\n" +
                "Rozpocznij przeglądanie. Zaufaj nam, im więcej otwartych możliwości, tym więcej kolorów w Twoim życiu. \n" +
                "\n" +
                "Witamy w aplikacji Friender - największej na świecie społeczności przyjaciół. Nie wstydź się, zaczynaj.";

        String textAboutUs = "Pozdrawiamy, zespół Friender";

        tvAboutText.setMovementMethod(new ScrollingMovementMethod());
        tvAboutText.setText(textAbout);
        tvAboutUsText.setText(textAboutUs);
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
