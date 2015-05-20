package my.app.noemi.waiter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

/**
 * Created by Noemi on 4/14/2015.
 */
public class HomeActivity extends Activity{
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.home_layout);

        final EditText username = (EditText) findViewById(R.id.username);
        final EditText password = (EditText) findViewById(R.id.password);

        Button button1 = (Button) findViewById(R.id.signin);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uname = username.getText().toString();
                String pword = password.getText().toString();
                ParseUser.logInInBackground(uname, pword, new LogInCallback() {
                    @Override
                    public void done(ParseUser parseUser, ParseException e) {
                        if (parseUser != null){
                            Intent intent = new Intent(HomeActivity.this, WaitingActivity.class);
                            startActivity(intent);
                        }else {
                            Toast.makeText(getApplicationContext(),"User does not exist, please signup!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        Button button2 = (Button) findViewById(R.id.signup);
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                Intent intent = new Intent(HomeActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });

        Button button3 = (Button) findViewById(R.id.forgotpw);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, PasswordSettingsActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}
