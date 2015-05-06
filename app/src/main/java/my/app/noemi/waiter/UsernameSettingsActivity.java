package my.app.noemi.waiter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseUser;

/**
 * Created by Noemi on 4/20/2015.
 */
public class UsernameSettingsActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.usernamesettings_layout);

        final EditText current = (EditText) findViewById(R.id.currentuser);
        final EditText newuser = (EditText) findViewById(R.id.newusername);
        final EditText pw = (EditText) findViewById(R.id.pw);

        Button button1 = (Button) findViewById(R.id.updateusername);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
}
