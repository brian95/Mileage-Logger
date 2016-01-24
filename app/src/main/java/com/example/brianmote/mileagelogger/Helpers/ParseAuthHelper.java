package com.example.brianmote.mileagelogger.Helpers;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.example.brianmote.mileagelogger.Activities.HomeScreenActivity;
import com.example.brianmote.mileagelogger.Activities.LoginActivity;
import com.example.brianmote.mileagelogger.Interfaces.AuthHelper;
import com.example.brianmote.mileagelogger.Listeners.AuthListener;
import com.parse.LogInCallback;
import com.parse.LogOutCallback;
import com.parse.ParseClassName;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

/**
 * Created by Brian Mote on 1/21/2016.
 */
@ParseClassName("ParseAuthHelper")
public class ParseAuthHelper extends ParseUser implements AuthHelper<ParseAuthHelper> {
    private static final String TAG = "ParseAuthHelper";
    private String username;
    private String password;
    private String email;

    private AuthListener listener;

    public ParseAuthHelper(@NonNull String username, @NonNull String password, @NonNull String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public ParseAuthHelper(@NonNull String username, @NonNull String password) {
        this.username = username;
        this.password = password;
    }

    public ParseAuthHelper() {
        //Required by parse
    }

    public boolean isStillLoggedIn() {
        ParseUser currentUser = ParseUser.getCurrentUser();
        if (currentUser != null) {
            return true;
        }
        return false;
    }

    @Override
    public void register(final AuthListener listener) {
        this.listener = listener;
        signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                listener.completed(e);
            }
        });
    }

    @Override
    public void login(String username, String password, final AuthListener listener) {
        this.listener = listener;
        logInInBackground(username, password, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                listener.completed(e);
            }
        });
    }

    @Override
    public void logout(final AuthListener listener) {
        this.listener = listener;
        getCurrentUser().logOutInBackground(new LogOutCallback() {
            @Override
            public void done(ParseException e) {
                listener.completed(e);
            }
        });
    }
}
