package my.app.noemi.waiter;

import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.RequestPasswordResetCallback;

/**
 * Created by Noemi on 4/20/2015.
 */
public class ForgotPassword extends ActionBarActivity{
    // for haptic feedback
    private Vibrator vb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.password_settings);
        vb = (Vibrator) this.getSystemService(VIBRATOR_SERVICE);

        // setting the typeface
        Typeface tf = Typeface.createFromAsset(getAssets(),
                "Roboto-Light.ttf");
        final EditText reset = (EditText) findViewById(R.id.toreset);
        reset.setTypeface(tf);

        // adding the action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Reset Password");
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#3fa9f5")));

        // button code
        Button button1 = (Button) findViewById(R.id.updatepassword);
        button1.setTypeface(tf);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vb.vibrate(50);
                String email = reset.getText().toString();

                ParseUser.requestPasswordResetInBackground(email, new RequestPasswordResetCallback() {
                    @Override
                    public void done(ParseException e) {
                        if(e == null){
                            Toast.makeText(getApplicationContext(), "Email will be sent with reset instructions.", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(getApplicationContext(), "Cannot reset password at this time.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}
