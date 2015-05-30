package my.app.noemi.waiter;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;

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
        String d;

        Intent intent = getIntent();

        n = intent.getStringExtra("name");
        c = intent.getStringExtra("city");
        z = intent.getStringExtra("zipcode");
        s = intent.getExtras().getInt("size");
        t = intent.getExtras().getInt("time");
        d = intent.getStringExtra("date");

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Details");
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#3fa9f5")));

        vb = (Vibrator) this.getSystemService(VIBRATOR_SERVICE);

        final TextView name = (TextView) findViewById(R.id.title);
        final TextView city = (TextView) findViewById(R.id.city);
        final TextView zip = (TextView) findViewById(R.id.zip);
        final TextView time = (TextView) findViewById(R.id.waittime);
        final TextView size = (TextView) findViewById(R.id.partysize);
        final TextView date = (TextView) findViewById(R.id.recentTime);

        name.setText(n);
        city.setText(c);
        zip.setText(z);
        time.setText("" + t);
        size.setText("" + s);
        date.setText(d);

    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.settings, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        switch(id){
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private ActionMode.Callback modeCallback = new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
            actionMode.setTitle("Details");
            actionMode.getMenuInflater().inflate(R.menu.settings, menu);
            return false;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
            int id = menuItem.getItemId();


            return false;
        }

        @Override
        public void onDestroyActionMode(ActionMode actionMode) {

        }
    };

    @Override
    public void onBackPressed() {
    }
}
