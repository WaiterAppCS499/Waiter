package my.app.noemi.waiter;

import android.app.Activity;
import android.os.Bundle;
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
public class PasswordSettingsActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.password_settings);

        final EditText reset = (EditText) findViewById(R.id.toreset);

        Button button1 = (Button) findViewById(R.id.updatepassword);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
