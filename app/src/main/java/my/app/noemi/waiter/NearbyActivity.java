package my.app.noemi.waiter;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.TextView;

/**
 * Created by Noemi on 4/21/2015.
 */
public class NearbyActivity extends Activity{
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        TextView tv=new TextView(this);
        tv.setTextSize(25);
        tv.setGravity(Gravity.CENTER);
        tv.setText("Will show nearby posts.");

        setContentView(tv);
    }
}
