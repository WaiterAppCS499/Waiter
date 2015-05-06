package my.app.noemi.waiter;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseObject;
import com.parse.ParseUser;

/**
 * Created by Noemi on 5/2/2015.
 */
public class ParseApplication extends Application{
    public void onCreate(){
        super.onCreate();

        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);
        ParseObject.registerSubclass(Waittime.class);

        Parse.initialize(this, "m0HyacjQDWz43cmKVPIe6r3EjqsZvWFhhN2afUx1", "PfUWz2mKBi57NKJxoubsTd8tdwyxBR0KwIVbaoL6");
        ParseUser.enableAutomaticUser();
        ParseACL defaultACL = new ParseACL();

        defaultACL.setPublicReadAccess(true);
        ParseACL.setDefaultACL(defaultACL, true);

    }
}
