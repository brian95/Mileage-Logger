package com.example.brianmote.mileagelogger.Helpers;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;
import com.parse.ParseUser;

/**
 * Created by Brian Mote on 1/21/2016.
 */
public class ParseInit extends Application {
    private static final String PARSE_APP_ID_KEY = "E9LGFA3QMg8AUpLpP6VpBMaKwBHGGYFh7n7saiiF";
    private static final String PARSE_CLIENT_KEY = "sqRQt4n62jqw5n8UQbkEeFcwc8OyF9Deh9FD1UYn";

    @Override
    public void onCreate(){
        super.onCreate();

        ParseObject.registerSubclass(Appointment.class);
        ParseUser.registerSubclass(ParseAuthHelper.class);
        Parse.initialize(this, PARSE_APP_ID_KEY, PARSE_CLIENT_KEY);
    }
}
