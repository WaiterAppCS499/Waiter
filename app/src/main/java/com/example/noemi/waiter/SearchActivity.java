package com.example.noemi.waiter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.awt.font.NumericShaper;

/**
 * Created by Noemi on 4/20/2015.
 */
public class SearchActivity extends Activity {

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.search_layout);

        final EditText rname = (EditText) findViewById(R.id.searchname);
        final EditText ct = (EditText) findViewById(R.id.scity);
        final EditText z = (EditText) findViewById(R.id.szip);
        final NumberPicker partysize = (NumberPicker) findViewById(R.id.partysize);
        final NumberPicker waittime = (NumberPicker) findViewById(R.id.waittime);

        Button button1 = (Button) findViewById(R.id.search);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = rname.getText().toString();
                String city = ct.getText().toString();
                String zip = z.getText().toString();

                if(zip.equals("")){
                    Toast.makeText(getApplicationContext(),"Please specify a zip code!", Toast.LENGTH_SHORT).show();
                }else {
                    ParseQuery<ParseObject> query = ParseQuery.getQuery("Waittime");
                    query.whereEqualTo("zipcode", zip);
                    query.whereEqualTo("partysize", partysize.getValue());
                    query.whereEqualTo("waittime", waittime.getValue());
                    query.orderByDescending("waittime");
                    query.getFirstInBackground(new GetCallback<ParseObject>() {
                        @Override
                        public void done(ParseObject parseObject, ParseException e) {
                            if (parseObject == null) {
                                Toast.makeText(getApplicationContext(), "Didn't work", Toast.LENGTH_SHORT).show();
                                Log.d("waittime", "Request failed.");
                            } else {
                                Log.d("waittime", "Success.");
                                Toast.makeText(getApplicationContext(), "Worked", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(SearchActivity.this, ResultsActivity.class);
                                startActivity(intent);
                            }
                        }
                    });
                }
            }
        });

        partysize.setMinValue(1);
        partysize.setMaxValue(10);
        partysize.setWrapSelectorWheel(true);

        partysize.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {

            }
        });

        waittime.setMinValue(1);
        waittime.setMaxValue(240);
        waittime.setWrapSelectorWheel(true);

        waittime.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {

            }
        });
    }
}
