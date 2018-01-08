package com.example.friender.fiender;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.Toast;

import org.xml.sax.Parser;

public class FriendsDiary extends AppCompatActivity {

    ListView listView_new;
    Parser parser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friendsdiary);
        listView_new = (ListView) findViewById(R.id.listView_new);

        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute("");

        try {
            Toast.makeText(getApplicationContext(), "Pobieram dane...", Toast.LENGTH_SHORT).show();
            Thread.sleep(1000);
        } catch (InterruptedException e) {

        }

//        parser = new Parser(getApplicationContext(), backgroundWorker.tempJSON);
//        parser.parseTanks();
//
//        ListViewAdapter lviewAdapter;
//        lviewAdapter = new ListViewAdapter(this, parser.ids, parser.cars, parser.dates, parser.averages, parser.fuels, parser.kilometerss);
//
//
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
//        parser.cars.clear();
//        parser.dates.clear();
//        parser.averages.clear();
//        parser.fuels.clear();
//        parser.kilometerss.clear();
//        parser.ids.clear();
        this.finish();
    }
}
