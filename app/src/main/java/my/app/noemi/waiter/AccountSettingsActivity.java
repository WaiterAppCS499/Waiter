package my.app.noemi.waiter;

import android.content.Context;
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
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseUser;

/**
 * Created by Noemi on 4/20/2015.
 */
public class AccountSettingsActivity extends ActionBarActivity {
    private Vibrator vb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.accountsettings_layout);

        Typeface tf = Typeface.createFromAsset(getAssets(),
                "Roboto-Light.ttf");

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Account Settings");
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#3fa9f5")));

        vb = (Vibrator) this.getSystemService(VIBRATOR_SERVICE);

        final EditText state = (EditText) findViewById(R.id.state);
        state.setTypeface(tf);
        final EditText city = (EditText) findViewById(R.id.city);
        city.setTypeface(tf);

        final Button button1 = (Button) findViewById(R.id.updateaccount);
        button1.setTypeface(tf);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vb.vibrate(50);
                String st = state.getText().toString();
                String ct = city.getText().toString();

                if(st.equals("") && ct.equals("")){
                    Toast.makeText(getApplicationContext(), "All fields required!", Toast.LENGTH_SHORT).show();
                }else{
                    ParseUser current = ParseUser.getCurrentUser();
                    current.put("state", st);
                    current.put("city", ct);
                    current.saveInBackground();

                    Intent intent = new Intent(AccountSettingsActivity.this, WaitingActivity.class);
                    startActivity(intent);

                    Context context = getApplicationContext();
                    CharSequence text = "Updated!";
                    int duration = Toast.LENGTH_SHORT;

                    Toast t = Toast.makeText(context, text, duration);
                    t.show();
                    t.setGravity(Gravity.CENTER_HORIZONTAL, 0, 600);
                }
            }
        });

        Button button2 = (Button) findViewById(R.id.changeusername);
        button2.setTypeface(tf);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vb.vibrate(50);
                Intent intent = new Intent(AccountSettingsActivity.this, UsernameSettingsActivity.class);
                startActivity(intent);
            }
        });

        Button button3 = (Button) findViewById(R.id.changepassword);
        button3.setTypeface(tf);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vb.vibrate(50);
                Intent intent = new Intent(AccountSettingsActivity.this, PasswordSettingsActivity.class);
                startActivity(intent);
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
                Intent intent = new Intent(AccountSettingsActivity.this, WaitingActivity.class);
                startActivity(intent);
                break;
            case R.id.action_logout:
                ParseUser.logOut();
                Toast.makeText(getApplicationContext(), "Logged out.", Toast.LENGTH_SHORT).show();
                Intent intent1 = new Intent(AccountSettingsActivity.this, HomeActivity.class);
                startActivity(intent1);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private ActionMode.Callback modeCallback = new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
            actionMode.setTitle("Account Settings");
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

    @Override
    public void onBackPressed() {
    }
}
