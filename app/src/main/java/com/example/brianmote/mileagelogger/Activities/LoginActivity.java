package com.example.brianmote.mileagelogger.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.brianmote.mileagelogger.Helpers.User;
import com.example.brianmote.mileagelogger.R;
import com.parse.ParseUser;

import butterknife.Bind;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "Login Activity";
    private User user;

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

        if (user == null) {
            user = new User();
        }

        if (user.isStillLoggedIn()) {
            Log.d(TAG, "User still logged in as " + ParseUser.getCurrentUser().getUsername());
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
                //Logging In User
                username = loginUsername.getText().toString();
                password = loginPassword.getText().toString();

                if (user.getUsername() == null) {
                    user.setUsername(username);
                }

                if (user.getPassword() == null) {
                    user.setPassword(password);
                }

                user.login(LoginActivity.this, user);

                Log.d(TAG, "Login Btn Clicked");
                Log.d(TAG, "Username: " + username);
                Log.d(TAG, "Password: " + password);
                break;
        }
    }
}
