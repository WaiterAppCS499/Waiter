package my.app.noemi.waiter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;

/**
 * Created by Noemi on 4/21/2015.
 */
public class ResultsActivity extends Activity{

    private ParseQueryAdapter<Waittime> mainAdapter;
    private LayoutInflater inflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.results_layout);

        ParseQueryAdapter.QueryFactory<Waittime> factory = new ParseQueryAdapter.QueryFactory<Waittime>(){
            public ParseQuery<Waittime> create(){
                ParseQuery<Waittime> query = Waittime.getQuery();
                try {
                    query.setLimit(1);
                    query.getFirst();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return query;
            }
        };

        inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mainAdapter = new ResultListAdapter(this, factory);
        mainAdapter.setTextKey("name");

        ListView resultListView = (ListView) findViewById(R.id.resultlist);
        resultListView.setAdapter(mainAdapter);

        resultListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

        Button button1 = (Button) findViewById(R.id.refine);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultsActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });

    }
    private class ResultListAdapter extends ParseQueryAdapter<Waittime>{
        public ResultListAdapter(Context context, ParseQueryAdapter.QueryFactory<Waittime> queryFactory){
            super(context, queryFactory);
        }

        @Override
        public View getItemView(Waittime waittime, View view, ViewGroup parent){
            ViewHolder holder;
            if (view == null){
                view = inflater.inflate(R.layout.abc_list_menu_item_layout, parent, false);
                holder = new ViewHolder();
                holder.waittimeName = (TextView) view.findViewById(R.id.title);
                view.setTag(holder);
            }else {
                holder = (ViewHolder) view.getTag();
            }

            TextView name = holder.waittimeName;
            name.setText(waittime.getName());
            return view;
        }
    }

    private static class ViewHolder{
        TextView waittimeName;
    }
}


