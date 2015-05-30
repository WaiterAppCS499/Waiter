package my.app.noemi.waiter;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Noemi on 5/29/2015.
 */
public class ResultInfo extends ActionBarActivity{
    private Vibrator vb;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resultinfo_layout);

        String n;
        String c;
        String z;
        int s;
        int t;
        long d;

        Intent intent = getIntent();

        n = intent.getStringExtra("name");
        c = intent.getStringExtra("city");
        z = intent.getStringExtra("zipcode");
        s = intent.getExtras().getInt("size");
        t = intent.getExtras().getInt("time");
        d = intent.getLongExtra("date", -1);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Details");
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#3fa9f5")));

        vb = (Vibrator) this.getSystemService(VIBRATOR_SERVICE);

        Typeface tf = Typeface.createFromAsset(getAssets(),
                "Roboto-Light.ttf");

        Typeface tf2 = Typeface.createFromAsset(getAssets(), "Roboto-Regular.ttf");

        TextView tv = (TextView) findViewById(R.id.partyinfo);
        tv.setTypeface(tf2);
        TextView tv1 = (TextView) findViewById(R.id.waittimeinfo);
        tv1.setTypeface(tf2);
        TextView tv2 = (TextView) findViewById(R.id.postTime);
        tv2.setTypeface(tf2);

        final TextView name = (TextView) findViewById(R.id.title);
        name.setTypeface(tf2);
        final TextView city = (TextView) findViewById(R.id.city);
        city.setTypeface(tf);
        final TextView zip = (TextView) findViewById(R.id.zip);
        zip.setTypeface(tf);
        final TextView time = (TextView) findViewById(R.id.waittime);
        time.setTypeface(tf);
        final TextView size = (TextView) findViewById(R.id.partysize);
        size.setTypeface(tf);
        final TextView date = (TextView) findViewById(R.id.recentTime);
        date.setTypeface(tf);

        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy h:mm a");
        String dateString = sdf.format(d);

        name.setText(n);
        city.setText(c + ", " + z);
        time.setText("" + t + " minutes");
        size.setText("" + s);
        date.setText(dateString);

        Button button1 = (Button) findViewById(R.id.refine);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vb.vibrate(50);
                Intent intent = new Intent(ResultInfo.this, SearchActivity.class);
                startActivity(intent);
            }
        });
    }

}
