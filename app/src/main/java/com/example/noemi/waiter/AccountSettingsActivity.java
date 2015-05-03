package com.example.noemi.waiter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseUser;

/**
 * Created by Noemi on 4/20/2015.
 */
public class AccountSettingsActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.accountsettings_layout);

        final EditText state = (EditText) findViewById(R.id.state);
        final EditText city = (EditText) findViewById(R.id.city);

        final Button button1 = (Button) findViewById(R.id.updateaccount);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AccountSettingsActivity.this, UsernameSettingsActivity.class);
                startActivity(intent);
            }
        });

        Button button3 = (Button) findViewById(R.id.changepassword);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AccountSettingsActivity.this, PasswordSettingsActivity.class);
                startActivity(intent);
            }
        });

        Button button4 = (Button) findViewById(R.id.home);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AccountSettingsActivity.this, WaitingActivity.class);
                startActivity(intent);
            }
        });
    }
}
