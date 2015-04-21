package com.example.noemi.waiter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by Noemi on 4/20/2015.
 */
public class PasswordSettingsActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.password_settings);

        Button button1 = (Button) findViewById(R.id.updatepassword);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PasswordSettingsActivity.this, AccountSettingsActivity.class);
                startActivity(intent);
            }
        });
    }
}
