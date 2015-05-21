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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.parse.ParseObject;
import com.parse.ParseUser;

/**
 * Created by Noemi on 4/21/2015.
 */
public class PostActivity extends ActionBarActivity {
    private Vibrator vb;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.post_layout);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Post");
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#3fa9f5")));

        vb = (Vibrator) this.getSystemService(VIBRATOR_SERVICE);

        final EditText rname = (EditText) findViewById(R.id.restaurantname);
        final EditText city = (EditText) findViewById(R.id.city);
        final EditText zip = (EditText) findViewById(R.id.zip);
        final NumberPicker partysize = (NumberPicker) findViewById(R.id.postingsize);
        final NumberPicker waittime = (NumberPicker) findViewById(R.id.postingtime);

        Button button1 = (Button) findViewById(R.id.post);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vb.vibrate(50);
                String rn = rname.getText().toString();
                String ct = city.getText().toString();
                String z = zip.getText().toString();

                if(rn.equals("") && ct.equals("") && z.equals("")){
                    Toast.makeText(getApplicationContext(), "All fields required!", Toast.LENGTH_SHORT).show();
                }else {
                    ParseObject data = ParseObject.create("Waittime");
                    data.put("name", rn);
                    data.put("city", ct);
                    data.put("zipcode", z);
                    data.put("partysize", partysize.getValue());
                    data.put("waittime", waittime.getValue());
                    data.saveInBackground();
                    Toast.makeText(getApplicationContext(), "Post successful!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(PostActivity.this, WaitingActivity.class);
                    startActivity(intent);
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
                Intent intent = new Intent(PostActivity.this, WaitingActivity.class);
                startActivity(intent);
                break;
            case R.id.action_logout:
                ParseUser.logOut();
                Toast.makeText(getApplicationContext(), "Logged out.", Toast.LENGTH_SHORT).show();
                Intent intent1 = new Intent(PostActivity.this, HomeActivity.class);
                startActivity(intent1);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private ActionMode.Callback modeCallback = new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
            actionMode.setTitle("Post");
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
