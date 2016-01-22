package com.example.brianmote.mileagelogger.Helpers;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.example.brianmote.mileagelogger.Activities.HomeScreenActivity;
import com.example.brianmote.mileagelogger.Activities.LoginActivity;
import com.parse.LogInCallback;
import com.parse.LogOutCallback;
import com.parse.ParseClassName;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

/**
 * Created by Brian Mote on 1/21/2016.
 */

public class User{
    private static final String TAG = "User";
    private String username;
    private String password;
    private String email;
    private ProgressDialog dialog;
    private ParseUser parseUser = new ParseUser();
    private ProgressDialogHelper dialogHelper;

    public User(@NonNull String username, @NonNull String password, @NonNull String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public User(@NonNull String username, @NonNull String password){
        this.username = username;
        this.password = password;
    }

    public User(){

    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public void register(@NonNull final Context context) {
        parseUser.setUsername(this.username);
        parseUser.setPassword(this.password);
        parseUser.setEmail(this.email);

        if (dialog == null) {
            dialog = new ProgressDialog(context);
        }
        if (dialogHelper == null){
            dialogHelper = new ProgressDialogHelper(dialog);
        }
        dialogHelper.buildDialog();

        parseUser.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if (dialog.isShowing()) {
                    dialog.dismiss();
                }

                String msg;
                if (e == null) {
                    msg = "User Created!";
                    Intent intent = new Intent(context, LoginActivity.class);
                    context.startActivity(intent);

                } else {
                    msg = e.getMessage();
                    Log.d(TAG, e.getMessage());
                }

                Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
            }
        });
    }

    public void login(@NonNull final Context context, User user){
        if (dialog == null){
            dialog = new ProgressDialog(context);
        }
        if (dialogHelper == null){
            dialogHelper = new ProgressDialogHelper(dialog);
        }
        dialogHelper.buildDialog();

        ParseUser.logInInBackground(user.getUsername(), user.getPassword(), new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if (dialog.isShowing()) {
                    dialog.dismiss();
                }

                String msg;
                if (e == null) {
                    msg = "Logging in";
                    Intent intent = new Intent(context, HomeScreenActivity.class);
                    context.startActivity(intent);
                } else {
                    msg = e.getMessage();
                    Log.d(TAG, e.getMessage());
                }
                Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
            }
        });
    }

    public void logout(final Context context){
        if (dialog == null){
            dialog = new ProgressDialog(context);
        }
        if (dialogHelper == null){
            dialogHelper = new ProgressDialogHelper(dialog);
        }
        dialogHelper.buildDialog();

        ParseUser.logOutInBackground(new LogOutCallback() {
            @Override
            public void done(ParseException e) {
                if (dialog.isShowing()){
                    dialog.dismiss();
                }

                String msg;
                if (e == null){
                    msg = "Logging out";
                    Intent intent = new Intent(context, LoginActivity.class);
                    context.startActivity(intent);
                } else {
                    msg = e.getMessage();
                }
                Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
            }
        });
    }

    public boolean isStillLoggedIn(){
        ParseUser currentUser = ParseUser.getCurrentUser();
        if (currentUser != null){
            return true;
        }
        return false;
    }

}
