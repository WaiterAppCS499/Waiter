package my.app.noemi.waiter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;

import java.util.ArrayList;

/**
 * Created by Noemi on 4/21/2015.
 */
public class ResultsActivity extends ActionBarActivity {
    private Vibrator vb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.results_layout);

        ArrayList<String> queryResults;
        if (savedInstanceState == null){
            Bundle extras = getIntent().getExtras();
            if (extras == null){
                queryResults = null;
            }else {
                queryResults = extras.getStringArrayList("queryResults");
            }
        }else {
            queryResults = (ArrayList<String>) savedInstanceState.getSerializable("queryResults");
        }

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Results");
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#3fa9f5")));

        vb = (Vibrator) this.getSystemService(VIBRATOR_SERVICE);

        final ArrayList<String> names = new ArrayList<>();

        for (String id: queryResults){
            ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Waittime");
            query.getInBackground(id, new GetCallback<ParseObject>() {
                @Override
                public void done(final ParseObject parseObject, ParseException e) {
                    if (e == null) {
                        names.add(parseObject.getString("name"));
                        ListView resultListView = (ListView) findViewById(R.id.resultlist);
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_list_item_1, names);
                        resultListView.setAdapter(adapter);

                        resultListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                vb.vibrate(50);
                                Intent intent = new Intent(ResultsActivity.this, ResultInfo.class);
                                intent.putExtra("name", parseObject.getString("name"));
                                intent.putExtra("city", parseObject.getString("city"));
                                intent.putExtra("zipcode", parseObject.getString("zipcode"));
                                intent.putExtra("size", parseObject.getNumber("partysize"));
                                intent.putExtra("time", parseObject.getNumber("waittime"));
                                intent.putExtra("date", parseObject.getDate("updatedAt"));
                                startActivity(intent);
                            }
                        });
                    } else {
                        Toast.makeText(getApplicationContext(), "Something went wrong.", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

        Button button1 = (Button) findViewById(R.id.refine);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vb.vibrate(50);
                Intent intent = new Intent(ResultsActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });

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
            actionMode.setTitle("Results");
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


