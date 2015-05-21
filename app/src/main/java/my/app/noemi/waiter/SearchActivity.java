package my.app.noemi.waiter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.view.ActionMode;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

/**
 * Created by Noemi on 4/20/2015.
 */
public class SearchActivity extends ActionBarActivity {
    private Vibrator vb;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.search_layout);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Search");
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#3fa9f5")));

        vb = (Vibrator) this.getSystemService(VIBRATOR_SERVICE);

        final EditText rname = (EditText) findViewById(R.id.searchname);
        final EditText ct = (EditText) findViewById(R.id.scity);
        final EditText z = (EditText) findViewById(R.id.szip);
        final NumberPicker partysize = (NumberPicker) findViewById(R.id.partysize);
        final NumberPicker waittime = (NumberPicker) findViewById(R.id.waittime);

        Button button1 = (Button) findViewById(R.id.search);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vb.vibrate(50);
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
                                ParseObject localstore = parseObject;
                                localstore.pinInBackground();
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
}
