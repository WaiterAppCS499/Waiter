package com.example.noemi.waiter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by Noemi on 4/20/2015.
 */
public class AccountSettingsActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.accountsettings_layout);

        final Button button1 = (Button) findViewById(R.id.updateaccount);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AccountSettingsActivity.this, WaitingActivity.class);
                startActivity(intent);

                Context context = getApplicationContext();
                CharSequence text = "Updated!";
                int duration = Toast.LENGTH_SHORT;

                Toast t = Toast.makeText(context, text, duration);
                t.show();
                t.setGravity(Gravity.CENTER_HORIZONTAL, 0, 600);
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
    }
}
