package com.example.brianmote.mileagelogger.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.brianmote.mileagelogger.Helpers.ParseAuthHelper;
import com.example.brianmote.mileagelogger.Helpers.ProgressDialogHelper;
import com.example.brianmote.mileagelogger.Listeners.AuthListener;
import com.example.brianmote.mileagelogger.R;

public class HomeScreenActivity extends AppCompatActivity {
    private static final String TAG = "Home Screen Activity";
    private ParseAuthHelper parseAuthHelper = new ParseAuthHelper();
    private ProgressDialogHelper dialogHelper;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_home_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_logout:
                if (dialog == null) {
                    dialog = new ProgressDialog(HomeScreenActivity.this);
                }
                if (dialogHelper == null) {
                    dialogHelper = new ProgressDialogHelper(dialog);
                }

                if (!dialog.isShowing()) {
                    dialogHelper.buildDialog();
                }

                parseAuthHelper.logout(new AuthListener() {
                    @Override
                    public void completed(com.parse.ParseException e) {
                        String msg;
                        if (dialog.isShowing()) {
                            dialog.dismiss();
                        }
                        if (e == null) {
                            msg = "Logging out";
                            startActivity(new Intent(HomeScreenActivity.this, LoginActivity.class));
                        } else {
                            msg = e.getMessage();
                        }
                        Toast.makeText(HomeScreenActivity.this, msg, Toast.LENGTH_LONG).show();
                    }
                });
                return true;

            case R.id.action_add:
                startActivity(new Intent(HomeScreenActivity.this, CreatePlaceActivity.class));

            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
