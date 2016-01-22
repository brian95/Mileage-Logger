package com.example.brianmote.mileagelogger.Activities;

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

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = "Register Activity";

    @Bind(R.id.registerUsername)
    EditText registerUsername;
    @Bind(R.id.registerEmail)
    EditText registerEmail;
    @Bind(R.id.registerPassword)
    EditText registerPassword;
    @Bind(R.id.submitBtn)
    Button submitBtn;

    private String username;
    private String email;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Register");

        submitBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        switch (viewId){
            case R.id.submitBtn:
                Log.d(TAG, "Submit Btn Clicked");
                username = registerUsername.getText().toString();
                email = registerEmail.getText().toString();
                password = registerPassword.getText().toString();
                User user = new User(username, password, email);
                user.register(RegisterActivity.this);
        }
    }
}
