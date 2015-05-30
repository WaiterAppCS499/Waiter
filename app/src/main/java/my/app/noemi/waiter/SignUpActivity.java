package my.app.noemi.waiter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

/**
 * Created by Noemi on 4/20/2015.
 */
public class SignUpActivity extends Activity {
    private Vibrator vb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signuppage_layout);

        Typeface tf = Typeface.createFromAsset(getAssets(),
                "Roboto-Light.ttf");

        vb = (Vibrator) this.getSystemService(VIBRATOR_SERVICE);

        TextView tv = (TextView) findViewById(R.id.joint);
        tv.setTypeface(tf);

        final EditText firstname = (EditText) findViewById(R.id.firstname);
        firstname.setTypeface(tf);
        final EditText lastname = (EditText) findViewById(R.id.lastname);
        lastname.setTypeface(tf);
        final EditText email = (EditText) findViewById(R.id.email);
        email.setTypeface(tf);
        final EditText signname = (EditText) findViewById(R.id.susername);
        signname.setTypeface(tf);
        final EditText signpass = (EditText) findViewById(R.id.spassword);
        signpass.setTypeface(tf);

        Button button1 = (Button) findViewById(R.id.join);
        button1.setTypeface(tf);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vb.vibrate(50);
                String first = firstname.getText().toString();
                String last = lastname.getText().toString();
                String mail = email.getText().toString();
                String name = signname.getText().toString();
                String pass = signpass.getText().toString();

                if(first.equals("") && last.equals("") && mail.equals("") && name.equals("") && pass.equals("")){
                    Toast.makeText(getApplicationContext(), "All fields are required!", Toast.LENGTH_SHORT).show();
                }else {
                    ParseUser user = new ParseUser();
                    user.setUsername(name);
                    user.setPassword(pass);
                    user.setEmail(mail);
                    user.put("name", first + " " + last);
                    user.signUpInBackground(new SignUpCallback() {
                        @Override
                        public void done(ParseException e) {
                            if (e == null){
                                Toast.makeText(getApplicationContext(), "Signup successful!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(SignUpActivity.this, AccountSettingsActivity.class);
                                startActivity(intent);
                            }else {
                                Toast.makeText(getApplicationContext(), "Error occurred.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }

}
