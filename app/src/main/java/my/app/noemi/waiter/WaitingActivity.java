package my.app.noemi.waiter;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseUser;

/**
 * Created by Noemi on 4/14/2015.
 */
public class WaitingActivity extends ActionBarActivity {
    // For haptic feedback
    private Vibrator vb;

    // On create method for page
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dontkeepthemwaiting_layout);
        vb = (Vibrator) this.getSystemService(VIBRATOR_SERVICE);

        // Sets typeface for page
        Typeface tf = Typeface.createFromAsset(getAssets(),
                "Roboto-Light.ttf");
        TextView tv = (TextView) findViewById(R.id.title);
        tv.setTypeface(tf);

        // Adds action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Home");
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#3fa9f5")));

        // Code for buttons
        Button button1 = (Button) findViewById(R.id.option1);
        button1.setTypeface(tf);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vb.vibrate(50);
                Intent intent = new Intent(WaitingActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });

        Button button2 = (Button) findViewById(R.id.option2);
        button2.setTypeface(tf);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vb.vibrate(50);
                Intent intent = new Intent(WaitingActivity.this, PostActivity.class);
                startActivity(intent);
            }
        });
    }


    // Code for action bar
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.home_settings, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        switch(id){
            case R.id.action_settings:
                Intent intent = new Intent(WaitingActivity.this, AccountSettingsActivity.class);
                startActivity(intent);
                break;
            case R.id.action_logout:
                ParseUser.logOut();
                Toast.makeText(getApplicationContext(), "Logged out.", Toast.LENGTH_SHORT).show();
                Intent intent1 = new Intent(WaitingActivity.this, HomeActivity.class);
                startActivity(intent1);
                break;
            default:
                return false;
        }

        return true;
    }

    private ActionMode.Callback modeCallback = new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
            actionMode.setTitle("Username Settings");
            actionMode.getMenuInflater().inflate(R.menu.home_settings, menu);
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


    // Disables back button on device for navigation purposes
    @Override
    public void onBackPressed() {
    }
}
