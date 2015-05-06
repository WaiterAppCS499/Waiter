package my.app.noemi.waiter;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseQuery;

/**
 * Created by Noemi on 5/5/2015.
 */

@ParseClassName("Waittime")
public class Waittime extends ParseObject{

    public Waittime(){
        super();
    }

    public String getName(){
        return getString("name");
    }

    public void setName(String name){
        put("name", name);
    }

    public String getCity(){
        return getString("city");
    }

    public void setCity(String city){
        put("city", city);
    }

    public int getPartysize(){
        return getInt("partysize");
    }

    public void setPartysize(int partysize){
        put("partysize", partysize);
    }

    public int getWaittime(){
        return getInt("waittime");
    }

    public void getWaittime(int waittime){
        put("waittime", waittime);
    }

    public String getZip(){
        return getString("zipcode");
    }

    public void setZip(String zipcode){
        put("zipcode", zipcode);
    }

    public static ParseQuery<Waittime> getQuery(){
        return ParseQuery.getQuery(Waittime.class);
    }
}
