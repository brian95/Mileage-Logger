package com.example.brianmote.mileagelogger.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.brianmote.mileagelogger.Helpers.ParseAuthHelper;
import com.example.brianmote.mileagelogger.Helpers.ProgressDialogHelper;
import com.example.brianmote.mileagelogger.Listeners.AuthListener;
import com.example.brianmote.mileagelogger.R;
import com.parse.ParseException;
import com.parse.ParseUser;

import butterknife.Bind;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "Login Activity";
    private ParseAuthHelper parseAuthHelper;
    private ProgressDialogHelper dialogHelper;
    private ProgressDialog dialog;

    @Bind(R.id.loginUsername)
    EditText loginUsername;
    @Bind(R.id.loginPassword)
    EditText loginPassword;
    @Bind(R.id.loginBtn)
    Button loginBtn;
    @Bind(R.id.registerBtn)
    Button registerBtn;

    private String username;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Login");

        registerBtn.setOnClickListener(this);
        loginBtn.setOnClickListener(this);

        if (parseAuthHelper == null) {
            parseAuthHelper = new ParseAuthHelper();
        }

        if (parseAuthHelper.isStillLoggedIn()) {
            Log.d(TAG, "ParseAuthHelper still logged in as " + ParseUser.getCurrentUser().getUsername());
            Intent intent = new Intent(LoginActivity.this, HomeScreenActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        switch (viewId) {
            case R.id.registerBtn:
                //Start Register Activity
                Log.d(TAG, "Register Btn Clicked");
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                break;
            case R.id.loginBtn:
                //Logging in
                if (dialog == null){
                    dialog = new ProgressDialog(LoginActivity.this);
                }
                if (dialogHelper == null){
                    dialogHelper = new ProgressDialogHelper(dialog);
                }
                if (!dialog.isShowing()){
                    dialogHelper.buildDialog();
                }
                Log.d(TAG, "Login Btn Clicked");

                username = loginUsername.getText().toString();
                password = loginPassword.getText().toString();
                parseAuthHelper.login(username, password, new AuthListener() {
                    @Override
                    public void completed(ParseException e) {
                        if (dialog.isShowing()){
                            dialog.dismiss();
                        }

                        String msg;
                        if (e == null){
                            msg = "Logging in";
                            startActivity(new Intent(LoginActivity.this, HomeScreenActivity.class));
                        } else {
                            msg = e.getMessage();
                        }
                        Toast.makeText(LoginActivity.this, msg, Toast.LENGTH_LONG).show();
                    }
                });

        }
    }
}
