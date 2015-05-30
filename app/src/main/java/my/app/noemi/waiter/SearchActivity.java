package my.app.noemi.waiter;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Noemi on 4/20/2015.
 */
public class SearchActivity extends ActionBarActivity {
    // for haptic feedback
    private Vibrator vb;

    // to hold the result queries
    private ArrayList<String> queryResults = new ArrayList<>();

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.search_layout);
        vb = (Vibrator) this.getSystemService(VIBRATOR_SERVICE);

        // setting the typeface
        Typeface tf = Typeface.createFromAsset(getAssets(),
                "Roboto-Light.ttf");
        TextView tv = (TextView) findViewById(R.id.wait);
        tv.setTypeface(tf);
        TextView tv1 = (TextView) findViewById(R.id.size);
        tv1.setTypeface(tf);

        // setting typeface for edit text
        final EditText rname = (EditText) findViewById(R.id.searchname);
        rname.setTypeface(tf);
        final EditText ct = (EditText) findViewById(R.id.scity);
        ct.setTypeface(tf);
        final EditText z = (EditText) findViewById(R.id.szip);
        z.setTypeface(tf);
        final NumberPicker partysize = (NumberPicker) findViewById(R.id.partysize);
        final NumberPicker waittime = (NumberPicker) findViewById(R.id.waittime);

        // setting the action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Search");
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#3fa9f5")));

        // Code for search button, includes search algorithms and passing extras to the result page
        Button button1 = (Button) findViewById(R.id.search);
        button1.setTypeface(tf);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vb.vibrate(50);
                String name = rname.getText().toString();
                String city = ct.getText().toString();
                String zip = z.getText().toString();

                // Search by waittime and partysize in zipcode area
                if (name.equals("") && waittime.getValue() > 1){
                    ParseQuery<ParseObject> query = ParseQuery.getQuery("Waittime");
                    query.whereEqualTo("zipcode", zip);
                    query.whereEqualTo("partysize", partysize.getValue());
                    query.whereLessThanOrEqualTo("waittime", waittime.getValue());
                    query.orderByAscending("waittime");
                    query.findInBackground(new FindCallback<ParseObject>() {
                        public void done(List<ParseObject> list, ParseException e) {
                            if (list == null) {
                                Toast.makeText(getApplicationContext(), "No results to show.", Toast.LENGTH_SHORT).show();
                            } else {
                                for (ParseObject p : list){
                                    queryResults.add(p.getObjectId());
                                }
                                Intent intent = new Intent(SearchActivity.this, ResultsActivity.class);
                                intent.putExtra("queryResults", queryResults);
                                startActivity(intent);
                            }
                        }
                    });
                // Search by name and partysize
                }else if (city.equals("") && zip.equals("")){
                    ParseQuery<ParseObject> query = ParseQuery.getQuery("Waittime");
                    query.whereEqualTo("name", name);
                    query.whereEqualTo("partysize", partysize.getValue());
                    query.orderByAscending("waittime");
                    query.findInBackground(new FindCallback<ParseObject>() {
                        public void done(List<ParseObject> list, ParseException e) {
                            if (list == null) {
                                Toast.makeText(getApplicationContext(), "No results to show.", Toast.LENGTH_SHORT).show();
                            } else {
                                for (ParseObject p : list){
                                    queryResults.add(p.getObjectId());
                                }
                                Intent intent = new Intent(SearchActivity.this, ResultsActivity.class);
                                intent.putExtra("queryResults", queryResults);
                                startActivity(intent);
                            }
                        }
                    });
                // Search by city and partysize
                }else if (zip.equals("") && name.equals("")){
                    ParseQuery<ParseObject> query = ParseQuery.getQuery("Waittime");
                    query.whereEqualTo("city", city);
                    query.whereEqualTo("partysize", partysize.getValue());
                    query.orderByAscending("waittime");
                    query.findInBackground(new FindCallback<ParseObject>() {
                        public void done(List<ParseObject> list, ParseException e) {
                            if (list == null) {
                                Toast.makeText(getApplicationContext(), "No results to show.", Toast.LENGTH_SHORT).show();
                            } else {
                                for (ParseObject p : list){
                                    queryResults.add(p.getObjectId());
                                }
                                Intent intent = new Intent(SearchActivity.this, ResultsActivity.class);
                                intent.putExtra("queryResults", queryResults);
                                startActivity(intent);
                            }
                        }
                    });
                }
            }
        });

        // listeners for number pickers
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

    // Code for action bars
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.settings, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){

        int id = item.getItemId();

        switch(id){
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
            case R.id.action_home:
                Intent intent = new Intent(SearchActivity.this, WaitingActivity.class);
                startActivity(intent);
                break;
            case R.id.action_logout:
                ParseUser.logOut();
                Toast.makeText(getApplicationContext(), "Logged out.", Toast.LENGTH_SHORT).show();
                Intent intent1 = new Intent(SearchActivity.this, HomeActivity.class);
                startActivity(intent1);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private ActionMode.Callback modeCallback = new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
            actionMode.setTitle("Search");
            actionMode.getMenuInflater().inflate(R.menu.settings, menu);
            return false;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
            int id = menuItem.getItemId();


            return false;
        }

        @Override
        public void onDestroyActionMode(ActionMode actionMode) {

        }
    };

    // disables back button on device for navigation
    @Override
    public void onBackPressed() {
    }
}
