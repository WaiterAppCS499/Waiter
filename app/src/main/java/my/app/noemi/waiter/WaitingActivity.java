package my.app.noemi.waiter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.parse.ParseUser;

/**
 * Created by Noemi on 4/14/2015.
 */
public class WaitingActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dontkeepthemwaiting_layout);

        Button button1 = (Button) findViewById(R.id.option1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WaitingActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });

        Button button2 = (Button) findViewById(R.id.option2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WaitingActivity.this, PostActivity.class);
                startActivity(intent);
            }
        });

        Button button3 = (Button) findViewById(R.id.settings);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WaitingActivity.this, AccountSettingsActivity.class);
                startActivity(intent);
            }
        });

        Button button4 = (Button) findViewById(R.id.logout);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseUser.logOut();
                Toast.makeText(getApplicationContext(), "Logged out.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(WaitingActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
    }
}
