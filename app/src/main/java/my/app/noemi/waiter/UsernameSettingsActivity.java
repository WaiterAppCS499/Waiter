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
import android.widget.Toast;

import com.parse.ParseUser;

/**
 * Created by Noemi on 4/20/2015.
 */
public class UsernameSettingsActivity extends ActionBarActivity {
    // For haptic feedback
    private Vibrator vb;

    // On create method for page
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.usernamesettings_layout);
        vb = (Vibrator) this.getSystemService(VIBRATOR_SERVICE);

        // Sets typeface
        Typeface tf = Typeface.createFromAsset(getAssets(),
                "Roboto-Light.ttf");

        // Sets typeface for edit text
        final EditText current = (EditText) findViewById(R.id.currentuser);
        current.setTypeface(tf);
        final EditText newuser = (EditText) findViewById(R.id.newusername);
        newuser.setTypeface(tf);
        final EditText pw = (EditText) findViewById(R.id.pw);
        pw.setTypeface(tf);

        // Adds action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Username Settings");
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#3fa9f5")));

        // Button codde
        Button button1 = (Button) findViewById(R.id.updateusername);
        button1.setTypeface(tf);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vb.vibrate(50);
                String cur = current.getText().toString();
                String nu = newuser.getText().toString();
                String p = pw.getText().toString();

                if(cur.equals("") && nu.equals("") && p.equals("")){
                    Toast.makeText(getApplicationContext(), "All fields required!", Toast.LENGTH_SHORT).show();
                }else{
                    ParseUser current = ParseUser.getCurrentUser();
                    if (cur.equals(current.getUsername())){
                        current.setUsername(nu);
                        current.saveInBackground();
                    }
                }
                Intent intent = new Intent(UsernameSettingsActivity.this, AccountSettingsActivity.class);
                startActivity(intent);
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
                Intent intent = new Intent(UsernameSettingsActivity.this, WaitingActivity.class);
                startActivity(intent);
                break;
            case R.id.action_logout:
                ParseUser.logOut();
                Toast.makeText(getApplicationContext(), "Logged out.", Toast.LENGTH_SHORT).show();
                Intent intent1 = new Intent(UsernameSettingsActivity.this, HomeActivity.class);
                startActivity(intent1);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private ActionMode.Callback modeCallback = new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
            actionMode.setTitle("Username Settings");
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
