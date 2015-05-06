package my.app.noemi.waiter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.parse.ParseObject;

/**
 * Created by Noemi on 4/21/2015.
 */
public class PostActivity extends Activity{

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.post_layout);

        final EditText rname = (EditText) findViewById(R.id.restaurantname);
        final EditText city = (EditText) findViewById(R.id.city);
        final EditText zip = (EditText) findViewById(R.id.zip);
        final NumberPicker partysize = (NumberPicker) findViewById(R.id.postingsize);
        final NumberPicker waittime = (NumberPicker) findViewById(R.id.postingtime);

        Button button1 = (Button) findViewById(R.id.post);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String rn = rname.getText().toString();
                String ct = city.getText().toString();
                String z = zip.getText().toString();

                if(rn.equals("") && ct.equals("") && z.equals("")){
                    Toast.makeText(getApplicationContext(), "All fields required!", Toast.LENGTH_SHORT).show();
                }else {
                    ParseObject data = ParseObject.create("Waittime");
                    data.put("name", rn);
                    data.put("city", ct);
                    data.put("zipcode", z);
                    data.put("partysize", partysize.getValue());
                    data.put("waittime", waittime.getValue());
                    data.saveInBackground();
                    Toast.makeText(getApplicationContext(), "Post successful!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(PostActivity.this, WaitingActivity.class);
                    startActivity(intent);
                }
            }
        });


        partysize.setMinValue(1);
        partysize.setMaxValue(10);
        partysize.setWrapSelectorWheel(true);

        partysize.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {

            }
        });

        waittime.setMinValue(1);
        waittime.setMaxValue(240);
        waittime.setWrapSelectorWheel(true);

        waittime.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {

            }
        });
    }
}
