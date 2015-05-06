package my.app.noemi.waiter;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;

/**
 * Created by Noemi on 4/21/2015.
 */
public class PostsActivity extends TabActivity{
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.posts_layout);

        // create the TabHost that will contain the Tabs
        TabHost tabHost = (TabHost)findViewById(android.R.id.tabhost);


        TabHost.TabSpec tab1 = tabHost.newTabSpec("Posted");
        TabHost.TabSpec tab2 = tabHost.newTabSpec("Nearby");
        TabHost.TabSpec tab3 = tabHost.newTabSpec("Favorites");

        // Set the Tab name and Activity
        // that will be opened when particular Tab will be selected
        tab1.setIndicator("Posted");
        tab1.setContent(new Intent(this,PostedActivity.class));

        tab2.setIndicator("Nearby");
        tab2.setContent(new Intent(this,NearbyActivity.class));

        tab3.setIndicator("Favorites");
        tab3.setContent(new Intent(this,FavoritesActivity.class));

        /** Add the tabs  to the TabHost to display. */
        tabHost.addTab(tab1);
        tabHost.addTab(tab2);
        tabHost.addTab(tab3);

    }
}
